package com.personal;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.zeroturnaround.exec.InvalidExitValueException;
import org.zeroturnaround.exec.ProcessExecutor;
import org.zeroturnaround.exec.stream.LogOutputStream;

import com.personal.db.DiaDTO;
import com.personal.db.MemDTO;

class View {
    private Model model = new Model();
    private int total;
    private List<DiaDTO> dialist;
    String mem_id;
    int dia_id;

    private Template template = new Template();
    private KeyHooker keyHooker;
    private boolean exit = false;

    private void init() {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
        for(Handler handler:Logger.getLogger("").getHandlers()) {
            handler.setLevel(Level.OFF);
        }

        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.exit(1);
        }
        
        keyHooker = new KeyHooker();
        GlobalScreen.addNativeKeyListener(keyHooker);
    }
    
    void run() {
        template.init();
        init();

        while(!exit) {
            template.run(keyHooker.getKey());

            switch (template.getCommand().ordinal()) {
                case 1: // EXIT
                exit = true;
                template.graphic();
                break;
            
                case 2: // GET_PW FROM DB
                model.mem = new MemDTO();
                model.mem = model.getById(template.getTemporary(1, 0));
                if(model.mem!=null&&model.mem.getPw().equals(template.getTemporary(1, 1))) {
                    template.setSel(0);
                    template.setUserId(model.mem.getId());
                    template.getUser().setCredit(model.mem.getCredit());
                    template.getUser().setEmail(model.mem.getEmail());
                    template.getUser().setPhone_num(model.mem.getPhone_num());
                    template.getUser().setMem_name(model.mem.getMem_name());
                    if(model.mem.getCard_id()!=null) template.getUser().setCard_id(model.mem.getCard_id());
                } else {
                    template.setState(1);
                    template.setMessage("Failed");
                }
                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 3: // DUP_CHK
                template.setState(2);

                String mem_name = template.getTemporary(2, 0);
                String phone_num = template.getTemporary(2, 1);
                String email = template.getTemporary(2, 2);
                String id = template.getTemporary(2, 3);
                String pw = template.getTemporary(2, 4);

                if(!mem_name.matches("\\w+")) {
                    template.setMessage("Error : Invalid format of Name");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!phone_num.matches("[01]{3}-[0-9]{3,4}-\\d{4}")) {
                    template.setMessage("Error : Invalid format of PN");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!email.matches("\\w+@\\w+\\.\\w+")) {
                    template.setMessage("Error : Invalid format of Email");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!id.matches("\\w+")) {
                    template.setMessage("Error : Invalid format of ID");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!pw.matches("\\w+")) {
                    template.setMessage("Error : Invalid format of PW");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                }

                if(!model.pn_dup_chk(phone_num)) {
                    template.setMessage("Error : PN is duplicated");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!model.email_dup_chk(email)) {
                    template.setMessage("Error : Email is duplicated");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!model.id_dup_chk(id)) {
                    template.setMessage("Error : ID is duplicated");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                }

                model.mem = new MemDTO();
                model.mem.setMem_name(mem_name);
                model.mem.setPhone_num(phone_num);
                model.mem.setEmail(email);
                model.mem.setId(id);
                model.mem.setPw(pw);
                
                if(model.memInsert(model.mem)==1) {
                    template.run("Escape");
                    template.setMessage("Signed up successfully !");
                } else {
                    template.setMessage("Warning : unexpected Error !");
                }
                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 4: // SETUP_TABLE
                total = model.totalDia();
                template.setPage((int)(Math.ceil(total/7.0)));
                if(template.getPage()==0) template.setPage(1);
                if(template.getTable_sel()<0) {
                    template.setTable_sel(1);
                } else if(template.getTable_sel() > total) {
                    template.setTable_sel(template.getTable_sel()-7);
                }
                dialist = model.table_set(template.getTable_sel(), total);
                for(int i=0;i<dialist.size();i++) {
                    template.getDia_table()[i].setCarat(dialist.get(i).getCarat());
                    template.getDia_table()[i].setColor(dialist.get(i).getColor());
                    template.getDia_table()[i].setClarity(dialist.get(i).getClarity());
                    template.getDia_table()[i].setCut(dialist.get(i).getCut());
                    template.getDia_table()[i].setDepth(dialist.get(i).getDepth());
                    template.getDia_table()[i].setDia_id(dialist.get(i).getDia_id());
                    template.getDia_table()[i].setPrice(dialist.get(i).getPrice());
                    template.getDia_table()[i].setTable(dialist.get(i).getTable());
                    template.getDia_table()[i].setX(dialist.get(i).getX());
                    template.getDia_table()[i].setY(dialist.get(i).getY());
                    template.getDia_table()[i].setZ(dialist.get(i).getZ());
                    template.getDia_table()[i].setOwner(dialist.get(i).getOwner());
                }
                for(int i=dialist.size();i<7;i++) {
                    template.getDia_table()[i].setCarat(0);
                    template.getDia_table()[i].setColor("");
                    template.getDia_table()[i].setClarity("");
                    template.getDia_table()[i].setCut("No-Data");
                    template.getDia_table()[i].setDepth(0);
                    template.getDia_table()[i].setDia_id(0);
                    template.getDia_table()[i].setPrice(0);
                    template.getDia_table()[i].setTable(0);
                    template.getDia_table()[i].setX(0);
                    template.getDia_table()[i].setY(0);
                    template.getDia_table()[i].setZ(0);
                    template.getDia_table()[i].setOwner("");
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                break;

                case 5: // DIA_TO_DIBS
                template.setState(template.getExstate());
                int fix=0;
                if(template.getState()==6) fix=1;
                if(template.getDia_table()[template.getSel()-fix].getCut().equals("No-Data")) {
                    template.setCommand(Command.DEFAULT);
                    template.setMessage("There is no diamond.");
                    template.graphic();
                    template.setMessage("");
                    break;
                }

                mem_id = template.getUserId();
                dia_id = template.getDia_table()[template.getSel()-fix].getDia_id();
                if(model.dibs_dup_chk(mem_id, dia_id)) {
                    if(model.is_owner(dia_id, mem_id)) template.setMessage("You are already owner of this diamond.");
                    else if(model.dibs_insert(mem_id, dia_id)==1) {
                        template.setMessage("Successfully inserted in your dibs.");
                    } else template.setMessage("Unexpected error, please try again.");
                } else template.setMessage("You already have a same thing in your dibs.");

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 6: // SEARCH
                String sql = model.makeSql(template.getTemporary(7, 0), template.getTemporary(7, 1),
                template.getTemporary(7, 2), template.getTemporary(7, 3), template.getTemporary(7, 4),
                template.getTemporary(7, 5), template.getTemporary(7, 6));

                total = model.totalDia(sql);
                template.setPage2((int)(Math.ceil(total/7.0)));
                if(template.getPage2()==0) template.setPage2(1);
                if(template.getTable_sel2()<0) {
                    template.setTable_sel2(1);
                } else if(template.getTable_sel2() > total) {
                    template.setTable_sel2(template.getTable_sel2()-7);
                }
                dialist = model.table_set(template.getTable_sel2(), total, sql);
                for(int i=0;i<dialist.size();i++) {
                    template.getDia_table()[i].setCarat(dialist.get(i).getCarat());
                    template.getDia_table()[i].setColor(dialist.get(i).getColor());
                    template.getDia_table()[i].setClarity(dialist.get(i).getClarity());
                    template.getDia_table()[i].setCut(dialist.get(i).getCut());
                    template.getDia_table()[i].setDepth(dialist.get(i).getDepth());
                    template.getDia_table()[i].setDia_id(dialist.get(i).getDia_id());
                    template.getDia_table()[i].setPrice(dialist.get(i).getPrice());
                    template.getDia_table()[i].setTable(dialist.get(i).getTable());
                    template.getDia_table()[i].setX(dialist.get(i).getX());
                    template.getDia_table()[i].setY(dialist.get(i).getY());
                    template.getDia_table()[i].setZ(dialist.get(i).getZ());
                    template.getDia_table()[i].setOwner(dialist.get(i).getOwner());
                }
                for(int i=dialist.size();i<7;i++) {
                    template.getDia_table()[i].setCarat(0);
                    template.getDia_table()[i].setColor("");
                    template.getDia_table()[i].setClarity("");
                    template.getDia_table()[i].setCut("No-Data");
                    template.getDia_table()[i].setDepth(0);
                    template.getDia_table()[i].setDia_id(0);
                    template.getDia_table()[i].setPrice(0);
                    template.getDia_table()[i].setTable(0);
                    template.getDia_table()[i].setX(0);
                    template.getDia_table()[i].setY(0);
                    template.getDia_table()[i].setZ(0);
                    template.getDia_table()[i].setOwner("");
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                break;

                case 7:
                switch (template.getExstate()) {
                    case 9:
                    template.setState(9);
                    template.setTemporary(7, 0, template.getName(9, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 10:
                    template.setState(10);
                    template.setTemporary(7, 1, template.getName(10, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;
                
                    case 11:
                    template.setState(11);
                    template.setTemporary(7, 2, template.getName(11, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 12:
                    template.setState(12);
                    template.setTemporary(7, 3, template.getName(12, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 13:
                    template.setState(13);
                    template.setTemporary(7, 4, template.getName(13, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 14:
                    template.setState(14);
                    template.setTemporary(7, 5, template.getName(14, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 15:
                    template.setState(15);
                    template.setTemporary(7, 6, template.getName(15, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    default:
                    break;
                }
                template.setCommand(Command.DEFAULT);
                template.graphic();
                break;

                case 8:
                switch (template.getExstate()) {
                    case 19:
                    template.setState(19);

                    if(!template.getTemporary(template.getState(), 0).matches("\\w+")) {
                        template.setMessage("Error : Invalid format of Name");
                        break;
                    }
                    if(model.memUpdate_name(template.getTemporary(template.getState(), 0), template.getUserId())==1) {
                        template.run("Escape");
                        template.setMessage("Your name has been changed.");
                    } else template.setMessage("Changing Failed.");
                    break;

                    case 20:
                    template.setState(20);

                    if(!template.getTemporary(template.getState(), 0).matches("[01]{3}-[0-9]{3,4}-\\d{4}")) {
                        template.setMessage("Error : Invalid format of PN");
                        break;
                    }

                    if(model.pn_dup_chk(template.getTemporary(template.getState(), 0))) {
                        if(model.memUpdate_pn(template.getTemporary(template.getState(), 0), template.getUserId())==1) {
                            template.run("Escape");
                            template.setMessage("Your pn has been changed.");
                        }else template.setMessage("Changing Failed.");
                    }else template.setMessage("It is duplicated value.");
                    break;

                    case 21:
                    template.setState(21);

                    if(!template.getTemporary(template.getState(), 0).matches("\\w+@\\w+\\.\\w+")) {
                        template.setMessage("Error : Invalid format of Email");
                        break;
                    }

                    if(model.email_dup_chk(template.getTemporary(template.getState(), 0))) {
                        if(model.memUpdate_email(template.getTemporary(template.getState(), 0), template.getUserId())==1) {
                            template.run("Escape");
                            template.setMessage("Your email has been changed.");
                        }else template.setMessage("Changing Failed.");
                    }else template.setMessage("It is duplicated value.");
                    break;

                    case 22:
                    template.setState(22);

                    if(!template.getTemporary(template.getState(), 0).matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}")) {
                        template.setMessage("Error : Invalid format of Card id");
                        break;
                    }

                    if(model.card_dup_chk(template.getTemporary(template.getState(), 0))) {
                        if(model.memUpdate_card(template.getTemporary(template.getState(), 0), template.getUserId())==1) {
                            template.run("Escape");
                            template.setMessage("Your card id has been changed.");
                        }else template.setMessage("Changing Failed.");
                    }else template.setMessage("It is duplicated value.");
                    break;

                    case 23:
                    template.setState(23);
                    if(template.getUser().getCard_id().equals("")) {
                        template.setMessage("You have to get valid card id.");
                        break;
                    } else if(!template.getTemporary(template.getState(), 0).matches("\\d+")) {
                        template.setMessage("Error : Invalid format of credit");
                        break;
                    }

                    if(model.memUpdate_credit(template.getUser().getCredit()+Integer.parseInt(template.getTemporary(template.getState(), 0)), template.getUserId())==1) {
                        template.run("Escape");
                        template.setMessage("Credit charge complete.");
                    } else template.setMessage("Charge Failed.");

                    break;
                
                    default:
                    break;
                }

                model.mem = new MemDTO();
                model.mem = model.getById(template.getUserId());
                template.setUserId(model.mem.getId());
                template.getUser().setCredit(model.mem.getCredit());
                template.getUser().setEmail(model.mem.getEmail());
                template.getUser().setPhone_num(model.mem.getPhone_num());
                template.getUser().setMem_name(model.mem.getMem_name());
                if(model.mem.getCard_id()!=null) template.getUser().setCard_id(model.mem.getCard_id());

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 9:
                total = model.inquiry_total(template.getUserId());
                template.setPage3((int)(Math.ceil(total/7.0)));
                if(template.getPage3()==0) template.setPage3(1);
                if(template.getTable_sel3()<0) {
                    template.setTable_sel3(1);
                } else if(template.getTable_sel3() > total) {
                    template.setTable_sel3(template.getTable_sel3()-7);
                }
                dialist = model.inquiry(template.getTable_sel3(), total, template.getUserId());
                for(int i=0;i<dialist.size();i++) {
                    template.getDia_table()[i].setCarat(dialist.get(i).getCarat());
                    template.getDia_table()[i].setColor(dialist.get(i).getColor());
                    template.getDia_table()[i].setClarity(dialist.get(i).getClarity());
                    template.getDia_table()[i].setCut(dialist.get(i).getCut());
                    template.getDia_table()[i].setDepth(dialist.get(i).getDepth());
                    template.getDia_table()[i].setDia_id(dialist.get(i).getDia_id());
                    template.getDia_table()[i].setPrice(dialist.get(i).getPrice());
                    template.getDia_table()[i].setTable(dialist.get(i).getTable());
                    template.getDia_table()[i].setX(dialist.get(i).getX());
                    template.getDia_table()[i].setY(dialist.get(i).getY());
                    template.getDia_table()[i].setZ(dialist.get(i).getZ());
                    template.getDia_table()[i].setOwner(dialist.get(i).getOwner());
                }
                for(int i=dialist.size();i<7;i++) {
                    template.getDia_table()[i].setCarat(0);
                    template.getDia_table()[i].setColor("");
                    template.getDia_table()[i].setClarity("");
                    template.getDia_table()[i].setCut("No-Data");
                    template.getDia_table()[i].setDepth(0);
                    template.getDia_table()[i].setDia_id(0);
                    template.getDia_table()[i].setPrice(0);
                    template.getDia_table()[i].setTable(0);
                    template.getDia_table()[i].setX(0);
                    template.getDia_table()[i].setY(0);
                    template.getDia_table()[i].setZ(0);
                    template.getDia_table()[i].setOwner("");
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 10:
                template.setState(24);
                if(template.getDia_table()[template.getSel()].getCut().equals("No-Data")) {
                    template.setMessage("There is no diamond.");
                } else {
                    if(model.del_dia(template.getDia_table()[template.getSel()].getDia_id())==1) {
                        template.setMessage("Dropped Successfully.");
                    } else {
                        template.setMessage("Dropped Failed.");
                    }
                }

                template.setCommand(Command.INQUIRY);
                break;

                case 11:
                switch (template.getExstate()) {
                    case 28:
                    template.setState(28);
                    template.setTemporary(27, 1, template.getName(28, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;

                    case 29:
                    template.setState(29);
                    template.setTemporary(27, 2, template.getName(29, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;
                
                    case 30:
                    template.setState(30);
                    template.setTemporary(27, 3, template.getName(30, template.getSel()));
                    template.run("Escape");
                    template.setExKey("Enter");
                    break;
                
                    default:
                    break;
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                break;

                case 12:
                template.setState(27);
                String carat = template.getTemporary(27, 0);
                String cut = template.getTemporary(27, 1);
                String color = template.getTemporary(27, 2);
                String clarity = template.getTemporary(27, 3);
                String depth = template.getTemporary(27, 4);
                String table = template.getTemporary(27, 5);
                String x = template.getTemporary(27, 6);
                String y = template.getTemporary(27, 7);
                String z = template.getTemporary(27, 8);

                if(!carat.matches("\\d{1}\\.\\d{1,2}")&&!carat.matches("\\d{1}")) {
                    template.setMessage("Invalid format of carat");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!depth.matches("\\d{2}\\.\\d{1,2}")&&!depth.matches("\\d{2}")) {
                    template.setMessage("Invalid format of depth");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!table.matches("\\d{2}\\.\\d{1,2}")&&!table.matches("\\d{2}")) {
                    template.setMessage("Invalid format of table");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!x.matches("\\d{1,2}\\.\\d{1,2}")&&!x.matches("\\d{1,2}")) {
                    template.setMessage("Invalid format of x");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!y.matches("\\d{1,2}\\.\\d{1,2}")&&!y.matches("\\d{1,2}")) {
                    template.setMessage("Invalid format of y");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                } else if(!z.matches("\\d{1,2}\\.\\d{1,2}")&&!z.matches("\\d{1,2}")) {
                    template.setMessage("Invalid format of z");
                    template.setCommand(Command.DEFAULT);
                    template.graphic();
                    template.setMessage("");
                    break;
                }
                try {
                    template.setTemporary(27, 9, "Evaluating...");
                    template.graphic();
                    new ProcessExecutor()
                    .command("python", "C:/close/shds/firstproject/LinearRegression.py", carat, cut, color, clarity, depth, table, x, y, z)
                    .redirectOutput(new LogOutputStream() {
                        @Override
                        protected void processLine(String line) {
                            template.setTemporary(27, 9, line);
                        }
                    }).execute();
                } catch (InvalidExitValueException | IOException | InterruptedException | TimeoutException e) {
                    template.setMessage("Error. try again.");
                    template.setTemporary(27, 9, "");
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 13:
                template.setState(27);
                if(template.getTemporary(27, 9).equals("")) {
                    template.setMessage("Evaluate First.");
                } else {
                    int dia_id=0;
                    while(true) {
                        dia_id = (int)(Math.random() * 100000);
                        if(model.dia_id_dup_chk(dia_id)) break;
                    }
                    if(model.diaInsert(dia_id, Double.parseDouble(template.getTemporary(27, 0)),
                    template.getTemporary(27, 1), template.getTemporary(27, 2),
                    template.getTemporary(27, 3), Double.parseDouble(template.getTemporary(27, 4)),
                    Double.parseDouble(template.getTemporary(27, 5)), Double.parseDouble(template.getTemporary(27, 6)),
                    Double.parseDouble(template.getTemporary(27, 7)), Double.parseDouble(template.getTemporary(27, 8)),
                    Integer.parseInt(template.getTemporary(27, 9)), template.getUserId())==1) {
                        template.screen_init(27);
                        template.setMessage("Registered.");
                    } else {
                        template.setMessage("Registering Failed.");
                    }
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setSel(0);
                template.setMessage("");
                break;

                case 14:
                total = model.dibs_total(template.getUserId());
                template.setPage4((int)(Math.ceil(total/7.0)));
                if(template.getPage4()==0) template.setPage4(1);
                if(template.getTable_sel4()<0) {
                    template.setTable_sel4(1);
                } else if(template.getTable_sel4() > total) {
                    template.setTable_sel4(template.getTable_sel4()-7);
                }
                dialist = model.dibs_table(template.getTable_sel4(), total, template.getUserId());
                for(int i=0;i<dialist.size();i++) {
                    template.getDia_table()[i].setCarat(dialist.get(i).getCarat());
                    template.getDia_table()[i].setColor(dialist.get(i).getColor());
                    template.getDia_table()[i].setClarity(dialist.get(i).getClarity());
                    template.getDia_table()[i].setCut(dialist.get(i).getCut());
                    template.getDia_table()[i].setDepth(dialist.get(i).getDepth());
                    template.getDia_table()[i].setDia_id(dialist.get(i).getDia_id());
                    template.getDia_table()[i].setPrice(dialist.get(i).getPrice());
                    template.getDia_table()[i].setTable(dialist.get(i).getTable());
                    template.getDia_table()[i].setX(dialist.get(i).getX());
                    template.getDia_table()[i].setY(dialist.get(i).getY());
                    template.getDia_table()[i].setZ(dialist.get(i).getZ());
                    template.getDia_table()[i].setOwner(dialist.get(i).getOwner());
                }
                for(int i=dialist.size();i<7;i++) {
                    template.getDia_table()[i].setCarat(0);
                    template.getDia_table()[i].setColor("");
                    template.getDia_table()[i].setClarity("");
                    template.getDia_table()[i].setCut("No-Data");
                    template.getDia_table()[i].setDepth(0);
                    template.getDia_table()[i].setDia_id(0);
                    template.getDia_table()[i].setPrice(0);
                    template.getDia_table()[i].setTable(0);
                    template.getDia_table()[i].setX(0);
                    template.getDia_table()[i].setY(0);
                    template.getDia_table()[i].setZ(0);
                    template.getDia_table()[i].setOwner("");
                }

                template.setCommand(Command.DEFAULT);
                template.graphic();
                template.setMessage("");
                break;

                case 15:
                template.setState(34);
                if(template.getDia_table()[template.getExsel()].getCut().equals("No-Data")) {
                    template.setMessage("There is no diamond.");
                } else {
                    MemDTO owner = model.getById(template.getDia_table()[template.getExsel()].getOwner());
                    
                    if(template.getUser().getCredit()<template.getDia_table()[template.getExsel()].getPrice()){
                        template.setMessage("Not enough credit.");
                    } else if(model.memUpdate_credit(template.getUser().getCredit()-template.getDia_table()[template.getExsel()].getPrice(), template.getUserId())==1) {
                        if(model.memUpdate_credit(owner.getCredit()+template.getDia_table()[template.getExsel()].getPrice()*9/10, template.getDia_table()[template.getExsel()].getOwner())==1) {
                            if(model.del_dia(template.getDia_table()[template.getExsel()].getDia_id())==1) {
                                template.setMessage("Purchased Successfully.");
                            } else template.setMessage("Purchase Failed.!");
                        } else template.setMessage("Purchase Failed.!!");
                    } else {
                        template.setMessage("Purchase Failed.!!!");
                    }
                }
                model.mem = new MemDTO();
                model.mem = model.getById(template.getUserId());
                template.setUserId(model.mem.getId());
                template.getUser().setCredit(model.mem.getCredit());
                template.getUser().setEmail(model.mem.getEmail());
                template.getUser().setPhone_num(model.mem.getPhone_num());
                template.getUser().setMem_name(model.mem.getMem_name());
                if(model.mem.getCard_id()!=null) template.getUser().setCard_id(model.mem.getCard_id());

                template.setCommand(Command.DIB_TABLE);
                template.screen_init(35);
                template.setSel(template.getExsel());
                break;

                case 16:
                template.setState(34);
                if(template.getDia_table()[template.getExsel()].getCut().equals("No-Data")) {
                    template.setMessage("There is no diamond.");
                } else {
                    if(model.del_from_dibs(template.getDia_table()[template.getExsel()].getDia_id(), template.getUserId())==1) {
                        template.setMessage("Dropped Successfully.");
                    } else {
                        template.setMessage("Dropped Failed.");
                    }
                }

                template.setCommand(Command.DIB_TABLE);
                template.screen_init(35);
                template.setSel(template.getExsel());
                break;

                default: //
                break;
            }
        }
        System.exit(0);
    }
}