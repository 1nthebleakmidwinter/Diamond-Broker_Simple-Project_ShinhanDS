package com.personal;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
class Template {
    private PrintStream w = System.out;
    private int exstate;
    private int state;   // 0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35
    private int[] selnum = {3, 3, 6, 0, 6, 0, 8, 8, 0, 9, 9, 5, 7, 8, 9, 9, 7, 0, 6, 2, 2, 2, 2, 2, 7, 0, 0,11, 5, 7, 8, 0, 0, 0, 7, 2};
    private ArrayList<char[]> sels = new ArrayList<>();
    private int sel, exsel=0;
    private String exKey = "";
    private ArrayList<Screen[]> enter_to_scr = new ArrayList<>();
    private Screen[] esc_to_scr = new Screen[selnum.length];
    private Properties[][] properties = new Properties[selnum.length][];
    private Command command = Command.DEFAULT;
    private User user = new User();
    private String message = "";
    private Diamond[] dia_table = new Diamond[] {
        new Diamond(),
        new Diamond(),
        new Diamond(),
        new Diamond(),
        new Diamond(),
        new Diamond(),
        new Diamond()
    };
    private int table_sel = 1;private int table_sel2 = 1;private int table_sel3 = 1;private int table_sel4 = 1;
    private int page;private int page2;private int page3;private int page4;

    void setTemporary(int state, int sel, String str) {
        properties[state][sel].temporary = str;
    }

    void setUserId(String id) {
        user.setId(id);
    }

    Diamond[] getDia_table() {
        return dia_table;
    }

    String getName(int state, int sel) {
        return properties[state][sel].name;
    }

    String getTemporary(int state, int sel) {
        return properties[state][sel].temporary;
    }

    String getUserId() {
        return user.getId();
    }
    
    class Properties {
        private boolean enable_enter=false, enable_input=false;
        private String name="", temporary="";
        public Properties(boolean enable_enter, boolean enable_input, String name) {
            this.enable_enter = enable_enter;
            this.enable_input = enable_input;
            this.name = name;
        }
    }

    @Getter@Setter
    class User {
        private String mem_name;
        private String phone_num;
        private String email;
        private String id;
        private String card_id="";
        private int credit=0;
    }

    @Getter@Setter@NoArgsConstructor
    class Diamond {
        int dia_id;
        double carat;
        String cut;
        String color;
        String clarity;
        double depth;
        double table;
        double x,y,z;
        int price;
        String owner;
    }

    void init() {
        for(int i=0;i<selnum.length;i++) {
            char[] temp = new char[selnum[i]];
            for(int j=0;j<selnum[i];j++) {
                if(j==0) temp[j] = '◀';
                else temp[j] = ' ';
            } 
            sels.add(temp);
        }

        properties[0]= new Properties[] {
            new Properties(true, false, "Sign in"),
            new Properties(true, false, "Sign up"),
            new Properties(true, false, "Exit")
        };
        properties[1]= new Properties[] {
            new Properties(false, true, "ID"),
            new Properties(false, true, "PW"),
            new Properties(true, false, "Sign in")
        };
        properties[2]= new Properties[] {
            new Properties(false, true, "Name"),
            new Properties(false, true, "PhoneNum"),
            new Properties(false, true, "E-mail"),
            new Properties(false, true, "ID"),
            new Properties(false, true, "PW"),
            new Properties(true, false, "Sign up")
        };
        properties[3]= new Properties[] {};
        
        properties[4]= new Properties[] {
            new Properties(true, false, "◎ Enter the Store"),
            new Properties(true, false, "◎ Manage Profile"),
            new Properties(true, false, "◎ Register Diamond"),
            new Properties(true, false, "◎ Check Your Dibs"),
            new Properties(true, false, "◎ Back to Login"),
            new Properties(true, false, "◎ Exit")
        };
        properties[5]= new Properties[] {};
        properties[6]= new Properties[] {
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, "")
        };
        properties[7]= new Properties[] {
            new Properties(true, false, "Price : "),
            new Properties(true, false, "Carat : "),
            new Properties(true, false, "Cut : "),
            new Properties(true, false, "Color : "),
            new Properties(true, false, "Clarity : "),
            new Properties(true, false, "Depth : "),
            new Properties(true, false, "Table : "),
            new Properties(true, false, "Search")
        };
        properties[8]= new Properties[] {};
        properties[9]= new Properties[] {
            new Properties(true, false, "$2000 ↓"),
            new Properties(true, false, "$4000 ↓"),
            new Properties(true, false, "$6000 ↓"),
            new Properties(true, false, "$8000 ↓"),
            new Properties(true, false, "$10000 ↓"),
            new Properties(true, false, "$12000 ↓"),
            new Properties(true, false, "$14000 ↓"),
            new Properties(true, false, "$16000 ↓"),
            new Properties(true, false, "$16000 ↑")
        };
        properties[10]= new Properties[] {
            new Properties(true, false, "0.5 ↓"),
            new Properties(true, false, "1.0 ↓"),
            new Properties(true, false, "1.5 ↓"),
            new Properties(true, false, "2.0 ↓"),
            new Properties(true, false, "2.5 ↓"),
            new Properties(true, false, "3.0 ↓"),
            new Properties(true, false, "3.5 ↓"),
            new Properties(true, false, "4.0 ↓"),
            new Properties(true, false, "4.0 ↑")
        };
        properties[11]= new Properties[] {
            new Properties(true, false, "Fair √"),
            new Properties(true, false, "Good √"),
            new Properties(true, false, "Very Good √"),
            new Properties(true, false, "Premium √"),
            new Properties(true, false, "Ideal √")
        };
        properties[12]= new Properties[] {
            new Properties(true, false, "J √"),
            new Properties(true, false, "I √"),
            new Properties(true, false, "H √"),
            new Properties(true, false, "G √"),
            new Properties(true, false, "F √"),
            new Properties(true, false, "E √"),
            new Properties(true, false, "D √")
        };
        properties[13]= new Properties[] {
            new Properties(true, false, "I1 √"),
            new Properties(true, false, "SI2 √"),
            new Properties(true, false, "SI1 √"),
            new Properties(true, false, "VS2 √"),
            new Properties(true, false, "VS1 √"),
            new Properties(true, false, "VVS2 √"),
            new Properties(true, false, "VVS1 √"),
            new Properties(true, false, "IF √")
        };
        properties[14]= new Properties[] {
            new Properties(true, false, "45% ↓"),
            new Properties(true, false, "50% ↓"),
            new Properties(true, false, "55% ↓"),
            new Properties(true, false, "60% ↓"),
            new Properties(true, false, "65% ↓"),
            new Properties(true, false, "70% ↓"),
            new Properties(true, false, "75% ↓"),
            new Properties(true, false, "79% ↓"),
            new Properties(true, false, "79% ↑")
        };
        properties[15]= new Properties[] {
            new Properties(true, false, "45% ↓"),
            new Properties(true, false, "50% ↓"),
            new Properties(true, false, "55% ↓"),
            new Properties(true, false, "60% ↓"),
            new Properties(true, false, "65% ↓"),
            new Properties(true, false, "70% ↓"),
            new Properties(true, false, "75% ↓"),
            new Properties(true, false, "80% ↓"),
            new Properties(true, false, "80% ↑")
        };
        properties[16]= new Properties[] {
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, "")
        };
        properties[17]= new Properties[] {};
        properties[18]= new Properties[] {
            new Properties(true, false, "Name"),
            new Properties(true, false, "PN"),
            new Properties(true, false, "Email"),
            new Properties(true, false, "Card ID"),
            new Properties(true, false, "Credit"),
            new Properties(true, false, "Inquiry your diamond in the Store")
        };
        properties[19]= new Properties[] {
            new Properties(false, true, "Enter New Name"),
            new Properties(true, false, "Enter")
        };
        properties[20]= new Properties[] {
            new Properties(false, true, "Enter New PN"),
            new Properties(true, false, "Enter")
        };
        properties[21]= new Properties[] {
            new Properties(false, true, "Enter New Email"),
            new Properties(true, false, "Enter")
        };
        properties[22]= new Properties[] {
            new Properties(false, true, "Enter New Card"),
            new Properties(true, false, "Enter")
        };
        properties[23]= new Properties[] {
            new Properties(false, true, "Enter How Charge"),
            new Properties(true, false, "Enter")
        };
        properties[24]= new Properties[] {
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, "")
        };
        properties[25]= new Properties[] {};
        properties[26]= new Properties[] {};
        properties[27]= new Properties[] {
            new Properties(false, true, "Carat : "),
            new Properties(true, false, "Cut : "),
            new Properties(true, false, "Color : "),
            new Properties(true, false, "Clarity : "),
            new Properties(false, true, "Depth : "),
            new Properties(false, true, "Table : "),
            new Properties(false, true, "x : "),
            new Properties(false, true, "y : "),
            new Properties(false, true, "z : "),
            new Properties(true, false, "Evaluated : "),
            new Properties(true, false, "Register")
        };
        properties[28]= new Properties[] {
            new Properties(true, false, "Fair"),
            new Properties(true, false, "Good"),
            new Properties(true, false, "Very Good"),
            new Properties(true, false, "Premium"),
            new Properties(true, false, "Ideal")
        };
        properties[29]= new Properties[] {
            new Properties(true, false, "J"),
            new Properties(true, false, "I"),
            new Properties(true, false, "H"),
            new Properties(true, false, "G"),
            new Properties(true, false, "F"),
            new Properties(true, false, "E"),
            new Properties(true, false, "D")
        };
        properties[30]= new Properties[] {
            new Properties(true, false, "I1"),
            new Properties(true, false, "SI2"),
            new Properties(true, false, "SI1"),
            new Properties(true, false, "VS2"),
            new Properties(true, false, "VS1"),
            new Properties(true, false, "VVS2"),
            new Properties(true, false, "VVS1"),
            new Properties(true, false, "IF")
        };
        properties[31]= new Properties[] {};
        properties[32]= new Properties[] {};
        properties[33]= new Properties[] {};
        properties[34]= new Properties[] {
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, ""),
            new Properties(true, false, "")
        };
        properties[35]= new Properties[] {
            new Properties(true, false, "Buy"),
            new Properties(true, false, "Drop")
        };

        // per number of properties
        enter_to_scr.add(Arrays.copyOfRange(Screen.values(), 1, 4));    // access from state=0
        enter_to_scr.add(new Screen[] {null, null, Screen.MAIN});               //     //      state=1
        enter_to_scr.add(new Screen[] {null, null, null, null, null, Screen.SINGED_UP}); //    state=2
        enter_to_scr.add(new Screen[] {});                                               //    state=3
        enter_to_scr.add(new Screen[] {Screen.STORE, Screen.PROFILE, Screen.REG_DIA, Screen.DIB_TABLE, Screen.INIT, Screen.EXIT});     //    state=4
        enter_to_scr.add(new Screen[] {});                                               //    state=5
        enter_to_scr.add(new Screen[] {Screen.SEARCH, Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS,//state=6
            Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS,
            Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS});
        enter_to_scr.add(new Screen[] {Screen.SEL_PRICE, Screen.SEL_CARAT, Screen.SEL_CUT,   //state=7
            Screen.SEL_COLOR, Screen.SEL_CLAR, Screen.SEL_DEPTH, Screen.SEL_TABLE,
            Screen.EXEC_SCH});
        enter_to_scr.add(new Screen[] {});                                               //    state=8                                 
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=9
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,
            Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=10
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,
            Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=11
            Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=12
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=13
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,
            Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=14
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,
            Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,//    state=15
            Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT, Screen.SET_FEAT,
            Screen.SET_FEAT, Screen.SET_FEAT});
        enter_to_scr.add(new Screen[] {Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS,// state=16
            Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS,
            Screen.DIA_TO_DIBS, Screen.DIA_TO_DIBS});
        enter_to_scr.add(new Screen[] {});                                    // state=17
        enter_to_scr.add(new Screen[] {Screen.CHG_NAME, Screen.CHG_PN,        // state=18
            Screen.CHG_EMAIL, Screen.CHG_CARD, Screen.CHARGE, Screen.INQUIRY});
        enter_to_scr.add(new Screen[] {null, Screen.CHANGE});                 // state=19
        enter_to_scr.add(new Screen[] {null, Screen.CHANGE});                 // state=20
        enter_to_scr.add(new Screen[] {null, Screen.CHANGE});                 // state=21
        enter_to_scr.add(new Screen[] {null, Screen.CHANGE});                 // state=22
        enter_to_scr.add(new Screen[] {null, Screen.CHANGE});                 // state=23
        enter_to_scr.add(new Screen[] {                                       // state=24
            Screen.CANCEL, Screen.CANCEL, Screen.CANCEL, Screen.CANCEL,
            Screen.CANCEL, Screen.CANCEL, Screen.CANCEL
        });
        enter_to_scr.add(new Screen[] {});                                    // state=25
        enter_to_scr.add(new Screen[] {});                                    // state=26
        enter_to_scr.add(new Screen[] {                                       // state=27
            null, Screen.REG_CUT, Screen.REG_COLOR, Screen.REG_CLARITY, null,
            null, null, null, null, Screen.EVAL, Screen.REGISTER
        });
        enter_to_scr.add(new Screen[] {                                       // state=28
            Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT,
            Screen.REG_FEAT
        });
        enter_to_scr.add(new Screen[] {                                       // state=29
            Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT,
            Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT
        });
        enter_to_scr.add(new Screen[] {                                       // state=30
            Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT,
            Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT, Screen.REG_FEAT
        });
        enter_to_scr.add(new Screen[] {});                                    // state=31
        enter_to_scr.add(new Screen[] {});                                    // state=32
        enter_to_scr.add(new Screen[] {});                                    // state=33
        enter_to_scr.add(new Screen[] {                                       // state=34
            Screen.BUYORDROP, Screen.BUYORDROP, Screen.BUYORDROP, Screen.BUYORDROP,
            Screen.BUYORDROP, Screen.BUYORDROP, Screen.BUYORDROP
        });
        enter_to_scr.add(new Screen[] {                                       // state=35
            Screen.BUY, Screen.DROP
        });
        
        esc_to_scr[0]=null;
        esc_to_scr[1]=Screen.INIT; esc_to_scr[2]=Screen.INIT; esc_to_scr[3]=Screen.INIT;
        esc_to_scr[4]=null;
        esc_to_scr[5]=null;
        esc_to_scr[6]=Screen.MAIN;
        esc_to_scr[7]=Screen.STORE;
        esc_to_scr[8]=null;
        esc_to_scr[9]=Screen.SEARCH;
        esc_to_scr[10]=Screen.SEARCH;
        esc_to_scr[11]=Screen.SEARCH;
        esc_to_scr[12]=Screen.SEARCH;
        esc_to_scr[13]=Screen.SEARCH;
        esc_to_scr[14]=Screen.SEARCH;
        esc_to_scr[15]=Screen.SEARCH;
        esc_to_scr[16]=Screen.STORE;
        esc_to_scr[17]=null;
        esc_to_scr[18]=Screen.MAIN;
        esc_to_scr[19]=Screen.PROFILE;
        esc_to_scr[20]=Screen.PROFILE;
        esc_to_scr[21]=Screen.PROFILE;
        esc_to_scr[22]=Screen.PROFILE;
        esc_to_scr[23]=Screen.PROFILE;
        esc_to_scr[24]=Screen.PROFILE;
        esc_to_scr[25]=null;
        esc_to_scr[26]=null;
        esc_to_scr[27]=Screen.MAIN;
        esc_to_scr[28]=Screen.REG_DIA;
        esc_to_scr[29]=Screen.REG_DIA;
        esc_to_scr[30]=Screen.REG_DIA;
        esc_to_scr[31]=null;
        esc_to_scr[32]=null;
        esc_to_scr[33]=null;
        esc_to_scr[34]=Screen.MAIN;
        esc_to_scr[35]=Screen.DIB_TABLE;

        graphic();
    }

    void run(String key) {
        System.out.print("");
        if(key.equals("")) {
            exKey = "";
        } else if(key.equals(exKey)) {
        } else if(key.equals("Up")) {
            exKey = key;
            sels.get(state)[sel]=' ';
            if(sel-1<0) sel=selnum[state]-1;
            else sel-=1;
            sels.get(state)[sel]='◀';
            graphic();
        } else if(key.equals("Down")) {
            exKey = key;
            sels.get(state)[sel]=' ';
            sel=(sel+1)%selnum[state];
            sels.get(state)[sel]='◀';
            graphic();
        } else if(key.equals("Enter") && properties[state][sel].enable_enter) { // Enter
            exKey = key;
            exstate = state;
            state = enter_to_scr.get(state)[sel].ordinal();
            switch (state) {
                case 0:
                screen_init(1);
                screen_init(2);
                screen_init(4);
                graphic();
                sel = 0;
                break;

                case 3:
                command = Command.EXIT;
                break;

                case 4:
                command = Command.GET_PW;
                break;

                case 5:
                command = Command.DUP_CHK;
                break;

                case 6:
                sel=0;
                command = Command.SETUP_TABLE;
                break;

                case 7:
                graphic();
                sel=0;
                table_sel2=1;
                break;

                case 8:
                command = Command.DIA_TO_DIBS;
                break;

                case 16:
                sel=0;
                command = Command.SEARCH;
                break;

                case 17:
                command = Command.SET_FEAT;
                break;

                case 24:
                sel=0;
                command = Command.INQUIRY;
                break;

                case 25:
                command = Command.CHANGE;
                break;

                case 26:
                command = Command.CANCEL;
                break;

                case 31:
                command = Command.REG_FEAT;
                break;

                case 32:
                command = Command.EVAL;
                break;

                case 33:
                command = Command.REG_DIA;
                break;

                case 34:
                sel = 0;
                command = Command.DIB_TABLE;
                break;

                case 35:
                exsel = sel;
                graphic();
                sel = 0;
                break;

                case 36:
                command = Command.BUY;
                break;

                case 37:
                command = Command.DROP;
                break;

                default:
                graphic();
                sel = 0;
                break;
            }
            
        } else if(key.equals("Escape") && esc_to_scr[state] != null) {
            esc_init();
            exKey = key;
            if(state==16) {
                screen_init(7);
                command = Command.SETUP_TABLE;
            }
            state = esc_to_scr[state].ordinal();
            for(int i=0;i<sels.get(state).length;i++) {
                if(sels.get(state)[i]=='◀') {
                    sel=i;break;
                }
            }

            graphic();
        } else if((key.length() == 1 || key.equals("Shift") || key.equals("Period") || key.equals("Minus")) && properties[state][sel].enable_input) {
            exKey = key;
            if(key.equals("Shift")) key = "@";
            else if(key.equals("Period")) key=".";
            else if(key.equals("Minus")) key="-";
            
            if(properties[state][sel].temporary.length() < 60)
            properties[state][sel].temporary += key.toLowerCase();
            graphic();
        } else if(key.equals("Backspace") && properties[state][sel].enable_input) {
            exKey = key;
            if(properties[state][sel].temporary.length() != 0)
            properties[state][sel].temporary = properties[state][sel].temporary.substring(0, properties[state][sel].temporary.length()-1);
            graphic();
        } else if(key.equals("Right")) {
            switch (state) {
                case 6:
                table_sel+=7;
                command = Command.SETUP_TABLE;    
                break;

                case 16:
                table_sel2+=7;
                command = Command.SEARCH;
                break;

                case 24:
                table_sel3+=7;
                command = Command.INQUIRY;
                break;

                case 34:
                table_sel4+=7;
                command = Command.DIB_TABLE;
                break;

                default:
                break;
            }
        } else if(key.equals("Left")) {
            switch (state) {
                case 6:
                table_sel-=7;
                command = Command.SETUP_TABLE;    
                break;

                case 16:
                table_sel2-=7;
                command = Command.SEARCH;
                break;

                case 24:
                table_sel3-=7;
                command = Command.INQUIRY;
                break;

                case 34:
                table_sel4-=7;
                command = Command.DIB_TABLE;
                break;

                default:
                break;
            }
        }
    }
    
    public void screen_init(int state) {
        char[] temp = new char[selnum[state]];
        for(int j=0;j<selnum[state];j++) {
            if(j==0) temp[j] = '◀';
            else temp[j] = ' ';
        }
        for(Properties property:properties[state]) property.temporary = "";
        sels.set(state, temp);
    }

    private void esc_init() {
        screen_init(state);
        message = "";
        table_sel2=1;
    }

    private String invisible(String pw) {
        String inv="";
        for(int i=0;i<pw.length();i++) inv+="*";
        return inv;
    }
    
    private void clearScreen() {
        w.println("\033[H\033[2J");
        w.flush();
    }

    private String cut(String str) {
        int len = str.length();
        if(len<15) {
            return str;
        } else {
            return str.substring(len-15, len);
        }
    }
    
    void graphic() {
        clearScreen();
        char bar = '|', blank =' ';
        String title = "Diamond Broker";
        switch (state) {
            case 0:
                title = "Diamond Broker - Login";
                String welcome = "Welcome to the Diamond Broker!";
                w.printf(" ================================================================\n");
                w.printf("%c%44s%21c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%48s%17c\n", bar, welcome, bar);
                w.printf("%c%16c￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c%33c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c%19s%2c%12c%16c\n", bar, bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c%16c%12c￣￣￣￣%13c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%19s%2c%12c%16c\n", bar, bar, properties[state][1].name, sels.get(state)[1], bar, bar);
                w.printf("%c%16c%12c￣￣￣￣%13c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%18s%2c%13c%16c\n", bar, bar, properties[state][2].name, sels.get(state)[2], bar, bar);
                w.printf("%c%16c%12c￣￣￣￣%13c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%33c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%17c\n", bar, blank, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%-22c%-25s%19c\n", bar, message, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;
            
            case 1:
                title = "Diamond Broker - Login";
                w.printf(" ================================================================\n");
                w.printf("%c%44s %-19s%c\n", bar, title, message, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%16c_ Welcome back _________________%17c\n", bar, blank, bar);
                w.printf("%c%16c%33c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][0].name, cut(properties[state][0].temporary), sels.get(state)[0], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][1].name, cut(invisible(properties[state][1].temporary)), sels.get(state)[1], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%19s%2c%12c%16c\n", bar, bar, properties[state][2].name, sels.get(state)[2], bar, bar);
                w.printf("%c%16c%12c￣￣￣￣%13c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%33c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%17c\n", bar, blank, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;
            
            case 2:
                title = "Diamond Broker - Login";
                w.printf(" ================================================================\n");
                w.printf("%c%44s%21c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%16c_ Nice to meet you ! ___________%17c\n", bar, blank, bar);
                w.printf("%c%16c%33c%16c\n", bar, bar, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][0].name, cut(properties[state][0].temporary), sels.get(state)[0], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%7s %-16s%2c%6c%16c\n", bar, bar, properties[state][1].name, cut(properties[state][1].temporary), sels.get(state)[1], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][2].name, cut(properties[state][2].temporary), sels.get(state)[2], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][3].name, cut(properties[state][3].temporary), sels.get(state)[3], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%7s  %-16s%2c%6c%16c\n", bar, bar, properties[state][4].name, cut(invisible(properties[state][4].temporary)), sels.get(state)[4], bar, bar);
                w.printf("%c%16c%9c￣￣￣￣￣￣￣￣%8c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c%19s%2c%12c%16c\n", bar, bar, properties[state][5].name, sels.get(state)[5], bar, bar);
                w.printf("%c%16c%12c￣￣￣￣%13c%16c\n", bar, bar, blank, bar, bar);
                w.printf("%c%16c￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%17c\n", bar, blank, bar);
                w.printf("%-15c%-40s%11c\n", bar, message, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 3:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%28cGood Bye.%28c\n", bar, blank, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf("%c%65c\n", bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 4:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c Welcome back,%6c%45c\n", bar, bar, bar);
                w.printf("%c %-18s%c        %-20s%2c%15c\n", bar, user.getId(), bar, properties[state][0].name, sels.get(state)[0], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c        %-20s%2c%15c\n", bar, bar, properties[state][1].name, sels.get(state)[1], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c        %-20s%2c%15c\n", bar, bar, properties[state][2].name, sels.get(state)[2], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c        %-20s%2c%15c\n", bar, bar, properties[state][3].name, sels.get(state)[3], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c        %-20s%2c%15c\n", bar, bar, properties[state][4].name, sels.get(state)[4], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c        %-20s%2c%15c\n", bar, bar, properties[state][5].name, sels.get(state)[5], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            // no case 5

            case 6:
                w.printf(" =============================================================================================\n");
                w.printf("%c%40s%54c\n", bar, title, bar);
                w.printf(" =============================================================================================\n");
                w.printf("%c%20c Search%2c%50s%15c\n", bar, bar, sels.get(state)[0], message, bar);
                w.printf("%c%20c ￣￣￣%67c\n", bar, bar, bar);
                w.printf("%c%20c |%5s|%9s|%5s|%7s|%5s|%5s|%4s|%4s|%4s|%7s|%4s%3c\n", bar, bar, "Carat", "Cut", "Color", "Clarity", "Depth", "Table", "X", "Y", "Z", "Price", "pick", bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[0].getCarat(), dia_table[0].getCut(), dia_table[0].getColor(), dia_table[0].getClarity(), dia_table[0].getDepth(), dia_table[0].getTable(), dia_table[0].getX(), dia_table[0].getY(), dia_table[0].getZ(), dia_table[0].getPrice(), sels.get(state)[1], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[1].getCarat(), dia_table[1].getCut(), dia_table[1].getColor(), dia_table[1].getClarity(), dia_table[1].getDepth(), dia_table[1].getTable(), dia_table[1].getX(), dia_table[1].getY(), dia_table[1].getZ(), dia_table[1].getPrice(), sels.get(state)[2], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[2].getCarat(), dia_table[2].getCut(), dia_table[2].getColor(), dia_table[2].getClarity(), dia_table[2].getDepth(), dia_table[2].getTable(), dia_table[2].getX(), dia_table[2].getY(), dia_table[2].getZ(), dia_table[2].getPrice(), sels.get(state)[3], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[3].getCarat(), dia_table[3].getCut(), dia_table[3].getColor(), dia_table[3].getClarity(), dia_table[3].getDepth(), dia_table[3].getTable(), dia_table[3].getX(), dia_table[3].getY(), dia_table[3].getZ(), dia_table[3].getPrice(), sels.get(state)[4], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[4].getCarat(), dia_table[4].getCut(), dia_table[4].getColor(), dia_table[4].getClarity(), dia_table[4].getDepth(), dia_table[4].getTable(), dia_table[4].getX(), dia_table[4].getY(), dia_table[4].getZ(), dia_table[4].getPrice(), sels.get(state)[5], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[5].getCarat(), dia_table[5].getCut(), dia_table[5].getColor(), dia_table[5].getClarity(), dia_table[5].getDepth(), dia_table[5].getTable(), dia_table[5].getX(), dia_table[5].getY(), dia_table[5].getZ(), dia_table[5].getPrice(), sels.get(state)[6], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[6].getCarat(), dia_table[6].getCut(), dia_table[6].getColor(), dia_table[6].getClarity(), dia_table[6].getDepth(), dia_table[6].getTable(), dia_table[6].getX(), dia_table[6].getY(), dia_table[6].getZ(), dia_table[6].getPrice(), sels.get(state)[7], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c%32c%5c   %3d / %3d%25c\n", bar, bar, '◁', '▷', table_sel/7+1, page, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 7:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%20c  Search Options%29c\n", bar, bar, bar);
                w.printf("%c%20c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][0].name, properties[state][0].temporary, sels.get(state)[0], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][1].name, properties[state][1].temporary, sels.get(state)[1], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][2].name, properties[state][2].temporary, sels.get(state)[2], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][3].name, properties[state][3].temporary, sels.get(state)[3], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][4].name, properties[state][4].temporary, sels.get(state)[4], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][5].name, properties[state][5].temporary, sels.get(state)[5], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c          %-11s%13s%3c%8c\n", bar, bar, properties[state][6].name, properties[state][6].temporary, sels.get(state)[6], bar);
                w.printf("%c%20c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c%20c%23s%3c%19c\n", bar, bar, properties[state][7].name, sels.get(state)[7], bar);
                w.printf("%c%20c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            // no case 8

            case 9:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c%23s%3c%19c\n", bar, properties[state][8].name, sels.get(state)[8], bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;
            
            case 10:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c%23s%3c%19c\n", bar, properties[state][8].name, sels.get(state)[8], bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 11:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-11s%2c%6c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c          %-11s%13s%3c%8c\n", bar, blank, blank, bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c          %-11s%13s%3c%8c\n", bar, blank, blank, bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c          %-11s%13s%3c%8c\n", bar, blank, blank, bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c%23s%3c%19c\n", bar, blank, blank, bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 12:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c          %-11s%13s%3c%8c\n", bar, blank, blank, bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c%23s%3c%19c\n", bar, blank, blank, bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 13:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9c%2c%8c%23s%3c%19c\n", bar, blank, blank, bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 14:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c%23s%3c%19c\n", bar, properties[state][8].name, sels.get(state)[8], bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 15:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Search Options%29c\n", bar, properties[state][0].name, sels.get(state)[0], bar, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣%29c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[7][0].name, properties[7][0].temporary, sels.get(7)[0], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[7][1].name, properties[7][1].temporary, sels.get(7)[1], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[7][2].name, properties[7][2].temporary, sels.get(7)[2], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[7][3].name, properties[7][3].temporary, sels.get(7)[3], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[7][4].name, properties[7][4].temporary, sels.get(7)[4], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[7][5].name, properties[7][5].temporary, sels.get(7)[5], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c          %-11s%13s%3c%8c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[7][6].name, properties[7][6].temporary, sels.get(7)[6], bar);
                w.printf("%c ￣￣￣%13c                    ￣￣￣￣￣￣%13c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c%23s%3c%19c\n", bar, properties[state][8].name, sels.get(state)[8], bar, properties[7][7].name, sels.get(7)[7], bar);
                w.printf("%c ￣￣￣%13c                ￣￣￣￣%21c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 16:
                w.printf(" =============================================================================================\n");
                w.printf("%c%40s%54c\n", bar, title, bar);
                w.printf(" =============================================================================================\n");
                w.printf("%c%20c Search Result%50s%10c\n", bar, bar, message, bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣%59c\n", bar, bar, bar);
                w.printf("%c%20c |%5s|%9s|%5s|%7s|%5s|%5s|%4s|%4s|%4s|%7s|%4s%3c\n", bar, bar, "Carat", "Cut", "Color", "Clarity", "Depth", "Table", "X", "Y", "Z", "Price", "pick", bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[0].getCarat(), dia_table[0].getCut(), dia_table[0].getColor(), dia_table[0].getClarity(), dia_table[0].getDepth(), dia_table[0].getTable(), dia_table[0].getX(), dia_table[0].getY(), dia_table[0].getZ(), dia_table[0].getPrice(), sels.get(state)[0], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[1].getCarat(), dia_table[1].getCut(), dia_table[1].getColor(), dia_table[1].getClarity(), dia_table[1].getDepth(), dia_table[1].getTable(), dia_table[1].getX(), dia_table[1].getY(), dia_table[1].getZ(), dia_table[1].getPrice(), sels.get(state)[1], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[2].getCarat(), dia_table[2].getCut(), dia_table[2].getColor(), dia_table[2].getClarity(), dia_table[2].getDepth(), dia_table[2].getTable(), dia_table[2].getX(), dia_table[2].getY(), dia_table[2].getZ(), dia_table[2].getPrice(), sels.get(state)[2], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[3].getCarat(), dia_table[3].getCut(), dia_table[3].getColor(), dia_table[3].getClarity(), dia_table[3].getDepth(), dia_table[3].getTable(), dia_table[3].getX(), dia_table[3].getY(), dia_table[3].getZ(), dia_table[3].getPrice(), sels.get(state)[3], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[4].getCarat(), dia_table[4].getCut(), dia_table[4].getColor(), dia_table[4].getClarity(), dia_table[4].getDepth(), dia_table[4].getTable(), dia_table[4].getX(), dia_table[4].getY(), dia_table[4].getZ(), dia_table[4].getPrice(), sels.get(state)[4], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[5].getCarat(), dia_table[5].getCut(), dia_table[5].getColor(), dia_table[5].getClarity(), dia_table[5].getDepth(), dia_table[5].getTable(), dia_table[5].getX(), dia_table[5].getY(), dia_table[5].getZ(), dia_table[5].getPrice(), sels.get(state)[5], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[6].getCarat(), dia_table[6].getCut(), dia_table[6].getColor(), dia_table[6].getClarity(), dia_table[6].getDepth(), dia_table[6].getTable(), dia_table[6].getX(), dia_table[6].getY(), dia_table[6].getZ(), dia_table[6].getPrice(), sels.get(state)[6], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c%32c%5c   %3d / %3d%25c\n", bar, bar, '◁', '▷', table_sel2/7+1, page2, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            // no case 17

            case 18:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%20c Profile of %-32s%c\n", bar, bar, user.id, bar);
                w.printf("%c%20c ID : %-32s%7c\n", bar, bar, user.id, bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[state][0].name, user.getMem_name(), sels.get(state)[0], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[state][1].name, user.getPhone_num(), sels.get(state)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[state][2].name, user.getEmail(), sels.get(state)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[state][3].name, user.getCard_id(), sels.get(state)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[state][4].name, user.getCredit(), sels.get(state)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[state][5].name, sels.get(state)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 19:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-18s%c Profile of %-32s%c\n", bar, properties[state][0].name, bar, user.id, bar);
                w.printf("%c %-15s%2c%2c ID : %-32s%7c\n", bar, cut(properties[state][0].temporary), sels.get(state)[0], bar, user.id, bar);
                w.printf("%c ￣￣￣￣￣￣￣￣%3c ￣%42c\n", bar, bar, bar);
                w.printf("%c %10s%2s%7c %-8s : %-25s%2c%6c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[18][0].name, user.getMem_name(), sels.get(18)[0], bar);
                w.printf("%c      ￣￣￣%8c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][1].name, user.getPhone_num(), sels.get(18)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[18][2].name, user.getEmail(), sels.get(18)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][3].name, user.getCard_id(), sels.get(18)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][4].name, user.getCredit(), sels.get(18)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[18][5].name, sels.get(18)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 20:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-18s%c Profile of %-32s%c\n", bar, properties[state][0].name, bar, user.id, bar);
                w.printf("%c %-15s%2c%2c ID : %-32s%7c\n", bar, cut(properties[state][0].temporary), sels.get(state)[0], bar, user.id, bar);
                w.printf("%c ￣￣￣￣￣￣￣￣%3c ￣%42c\n", bar, bar, bar);
                w.printf("%c %10s%2s%7c %-8s : %-25s%2c%6c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[18][0].name, user.getMem_name(), sels.get(18)[0], bar);
                w.printf("%c      ￣￣￣%8c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][1].name, user.getPhone_num(), sels.get(18)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[18][2].name, user.getEmail(), sels.get(18)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][3].name, user.getCard_id(), sels.get(18)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][4].name, user.getCredit(), sels.get(18)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[18][5].name, sels.get(18)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 21:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-18s%c Profile of %-32s%c\n", bar, properties[state][0].name, bar, user.id, bar);
                w.printf("%c %-15s%2c%2c ID : %-32s%7c\n", bar, cut(properties[state][0].temporary), sels.get(state)[0], bar, user.id, bar);
                w.printf("%c ￣￣￣￣￣￣￣￣%3c ￣%42c\n", bar, bar, bar);
                w.printf("%c %10s%2s%7c %-8s : %-25s%2c%6c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[18][0].name, user.getMem_name(), sels.get(18)[0], bar);
                w.printf("%c      ￣￣￣%8c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][1].name, user.getPhone_num(), sels.get(18)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[18][2].name, user.getEmail(), sels.get(18)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][3].name, user.getCard_id(), sels.get(18)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][4].name, user.getCredit(), sels.get(18)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[18][5].name, sels.get(18)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 22:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-18s%c Profile of %-32s%c\n", bar, properties[state][0].name, bar, user.id, bar);
                w.printf("%c %-15s%2c%2c ID : %-32s%7c\n", bar, cut(properties[state][0].temporary), sels.get(state)[0], bar, user.id, bar);
                w.printf("%c ￣￣￣￣￣￣￣￣%3c ￣%42c\n", bar, bar, bar);
                w.printf("%c %10s%2s%7c %-8s : %-25s%2c%6c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[18][0].name, user.getMem_name(), sels.get(18)[0], bar);
                w.printf("%c      ￣￣￣%8c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][1].name, user.getPhone_num(), sels.get(18)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[18][2].name, user.getEmail(), sels.get(18)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][3].name, user.getCard_id(), sels.get(18)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][4].name, user.getCredit(), sels.get(18)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[18][5].name, sels.get(18)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 23:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-18s%c Profile of %-32s%c\n", bar, properties[state][0].name, bar, user.id, bar);
                w.printf("%c %-15s%2c%2c ID : %-32s%7c\n", bar, cut(properties[state][0].temporary), sels.get(state)[0], bar, user.id, bar);
                w.printf("%c ￣￣￣￣￣￣￣￣%3c ￣%42c\n", bar, bar, bar);
                w.printf("%c %10s%2s%7c %-8s : %-25s%2c%6c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[18][0].name, user.getMem_name(), sels.get(18)[0], bar);
                w.printf("%c      ￣￣￣%8c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][1].name, user.getPhone_num(), sels.get(18)[1], bar);
                w.printf("%c%20c ￣%42c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-30s%c%2c\n", bar, bar, properties[18][2].name, user.getEmail(), sels.get(18)[2], bar);
                w.printf("%c%20c ￣￣%40c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][3].name, user.getCard_id(), sels.get(18)[3], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-8s : %-25s%2c%6c\n", bar, bar, properties[18][4].name, user.getCredit(), sels.get(18)[4], bar);
                w.printf("%c%20c ￣￣￣%38c\n", bar, bar, bar);
                w.printf("%c%20c %-30s%2c%9c\n", bar, bar, properties[18][5].name, sels.get(18)[5], bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣%10c\n", bar, bar, bar);
                w.printf("%c%20c %39s%5c\n", bar, bar, message, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 24:
                w.printf(" =============================================================================================\n");
                w.printf("%c%40s%54c\n", bar, title, bar);
                w.printf(" =============================================================================================\n");
                w.printf("%c%20c Your Diamonds%50s%10c\n", bar, bar, message, bar);
                w.printf("%c%20c ￣￣￣￣￣￣￣%59c\n", bar, bar, bar);
                w.printf("%c%20c |%5s|%9s|%5s|%7s|%5s|%5s|%4s|%4s|%4s|%7s|%4s%c\n", bar, bar, "Carat", "Cut", "Color", "Clarity", "Depth", "Table", "X", "Y", "Z", "Price", "cancel", bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[0].getCarat(), dia_table[0].getCut(), dia_table[0].getColor(), dia_table[0].getClarity(), dia_table[0].getDepth(), dia_table[0].getTable(), dia_table[0].getX(), dia_table[0].getY(), dia_table[0].getZ(), dia_table[0].getPrice(), sels.get(state)[0], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[1].getCarat(), dia_table[1].getCut(), dia_table[1].getColor(), dia_table[1].getClarity(), dia_table[1].getDepth(), dia_table[1].getTable(), dia_table[1].getX(), dia_table[1].getY(), dia_table[1].getZ(), dia_table[1].getPrice(), sels.get(state)[1], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[2].getCarat(), dia_table[2].getCut(), dia_table[2].getColor(), dia_table[2].getClarity(), dia_table[2].getDepth(), dia_table[2].getTable(), dia_table[2].getX(), dia_table[2].getY(), dia_table[2].getZ(), dia_table[2].getPrice(), sels.get(state)[2], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[3].getCarat(), dia_table[3].getCut(), dia_table[3].getColor(), dia_table[3].getClarity(), dia_table[3].getDepth(), dia_table[3].getTable(), dia_table[3].getX(), dia_table[3].getY(), dia_table[3].getZ(), dia_table[3].getPrice(), sels.get(state)[3], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[4].getCarat(), dia_table[4].getCut(), dia_table[4].getColor(), dia_table[4].getClarity(), dia_table[4].getDepth(), dia_table[4].getTable(), dia_table[4].getX(), dia_table[4].getY(), dia_table[4].getZ(), dia_table[4].getPrice(), sels.get(state)[4], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[5].getCarat(), dia_table[5].getCut(), dia_table[5].getColor(), dia_table[5].getClarity(), dia_table[5].getDepth(), dia_table[5].getTable(), dia_table[5].getX(), dia_table[5].getY(), dia_table[5].getZ(), dia_table[5].getPrice(), sels.get(state)[5], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[6].getCarat(), dia_table[6].getCut(), dia_table[6].getColor(), dia_table[6].getClarity(), dia_table[6].getDepth(), dia_table[6].getTable(), dia_table[6].getX(), dia_table[6].getY(), dia_table[6].getZ(), dia_table[6].getPrice(), sels.get(state)[6], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c%32c%5c   %3d / %3d%25c\n", bar, bar, '◁', '▷', table_sel3/7+1, page3, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            // no case 25

            // no case 26

            case 27:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%20c  Register Diamond %25s%c\n", bar, bar, message, bar);
                w.printf("%c%20c  ￣￣￣￣￣￣￣￣￣%25c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][0].name, properties[state][0].temporary, sels.get(state)[0], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][1].name, properties[state][1].temporary, sels.get(state)[1], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][2].name, properties[state][2].temporary, sels.get(state)[2], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][3].name, properties[state][3].temporary, sels.get(state)[3], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][4].name, properties[state][4].temporary, sels.get(state)[4], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][5].name, properties[state][5].temporary, sels.get(state)[5], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][6].name, properties[state][6].temporary, sels.get(state)[6], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][7].name, properties[state][7].temporary, sels.get(state)[7], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[state][8].name, properties[state][8].temporary, sels.get(state)[8], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%-13s%2c%11c\n", bar, bar, properties[state][9].name, properties[state][9].temporary, sels.get(state)[9], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%23s%3c%19c\n", bar, bar, properties[state][10].name, sels.get(state)[10], bar);
                w.printf("%c%20c               ￣￣￣￣%22c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 28:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Register Diamond %25s%c\n", bar, properties[state][0].name, sels.get(state)[0], bar, message, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣￣￣%25c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[27][0].name, properties[27][0].temporary, sels.get(27)[0], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][1].name, properties[27][1].temporary, sels.get(27)[1], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[27][2].name, properties[27][2].temporary, sels.get(27)[2], bar);
                w.printf("%c ￣￣￣￣￣%9c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[27][3].name, properties[27][3].temporary, sels.get(27)[3], bar);
                w.printf("%c ￣￣￣￣%11c       %-10s%9s%3c%16c\n", bar, bar, properties[27][4].name, properties[27][4].temporary, sels.get(27)[4], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[27][5].name, properties[27][5].temporary, sels.get(27)[5], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[27][6].name, properties[27][6].temporary, sels.get(27)[6], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[27][7].name, properties[27][7].temporary, sels.get(27)[7], bar);
                w.printf("%c%20c       %-10s%9s%3c%16c\n", bar, bar, properties[27][8].name, properties[27][8].temporary, sels.get(27)[8], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%14c\n", bar, bar, properties[27][9].name, properties[27][9].temporary, sels.get(27)[9], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%23s%3c%19c\n", bar, bar, properties[27][10].name, sels.get(27)[10], bar);
                w.printf("%c%20c               ￣￣￣￣%22c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 29:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Register Diamond %25s%c\n", bar, properties[state][0].name, sels.get(state)[0], bar, message, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣￣￣%25c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[27][0].name, properties[27][0].temporary, sels.get(27)[0], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][1].name, properties[27][1].temporary, sels.get(27)[1], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[27][2].name, properties[27][2].temporary, sels.get(27)[2], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[27][3].name, properties[27][3].temporary, sels.get(27)[3], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][4].name, properties[27][4].temporary, sels.get(27)[4], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[27][5].name, properties[27][5].temporary, sels.get(27)[5], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[27][6].name, properties[27][6].temporary, sels.get(27)[6], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][7].name, properties[27][7].temporary, sels.get(27)[7], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[27][8].name, properties[27][8].temporary, sels.get(27)[8], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c%20c       %-10s%9s%3c%14c\n", bar, bar, properties[27][9].name, properties[27][9].temporary, sels.get(27)[9], bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%23s%3c%19c\n", bar, bar, properties[27][10].name, sels.get(27)[10], bar);
                w.printf("%c%20c               ￣￣￣￣%22c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 30:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c %-9s%2c%8c  Register Diamond %25s%c\n", bar, properties[state][0].name, sels.get(state)[0], bar, message, bar);
                w.printf("%c ￣￣￣%13c  ￣￣￣￣￣￣￣￣￣%25c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][1].name, sels.get(state)[1], bar, properties[27][0].name, properties[27][0].temporary, sels.get(27)[0], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][1].name, properties[27][1].temporary, sels.get(27)[1], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][2].name, sels.get(state)[2], bar, properties[27][2].name, properties[27][2].temporary, sels.get(27)[2], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][3].name, sels.get(state)[3], bar, properties[27][3].name, properties[27][3].temporary, sels.get(27)[3], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][4].name, properties[27][4].temporary, sels.get(27)[4], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][4].name, sels.get(state)[4], bar, properties[27][5].name, properties[27][5].temporary, sels.get(27)[5], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][5].name, sels.get(state)[5], bar, properties[27][6].name, properties[27][6].temporary, sels.get(27)[6], bar);
                w.printf("%c ￣￣￣%13c       %-10s%9s%3c%16c\n", bar, bar, properties[27][7].name, properties[27][7].temporary, sels.get(27)[7], bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%16c\n", bar, properties[state][6].name, sels.get(state)[6], bar, properties[27][8].name, properties[27][8].temporary, sels.get(27)[8], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c %-9s%2c%8c       %-10s%9s%3c%14c\n", bar, properties[state][7].name, sels.get(state)[7], bar, properties[27][9].name, properties[27][9].temporary, sels.get(27)[9], bar);
                w.printf("%c ￣￣￣%13c%45c\n", bar, bar, bar);
                w.printf("%c%20c%23s%3c%19c\n", bar, bar, properties[27][10].name, sels.get(27)[10], bar);
                w.printf("%c%20c               ￣￣￣￣%22c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            // no case 31,32,33

            case 34:
                w.printf(" =============================================================================================\n");
                w.printf("%c%40s%54c\n", bar, title, bar);
                w.printf(" =============================================================================================\n");
                w.printf("%c%20c Your Dibs%50s%14c\n", bar, bar, message, bar);
                w.printf("%c%20c ￣￣￣￣￣%63c\n", bar, bar, bar);
                w.printf("%c%20c |%5s|%9s|%5s|%7s|%5s|%5s|%4s|%4s|%4s|%7s|%4s%3c\n", bar, bar, "Carat", "Cut", "Color", "Clarity", "Depth", "Table", "X", "Y", "Z", "Price", "Menu", bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[0].getCarat(), dia_table[0].getCut(), dia_table[0].getColor(), dia_table[0].getClarity(), dia_table[0].getDepth(), dia_table[0].getTable(), dia_table[0].getX(), dia_table[0].getY(), dia_table[0].getZ(), dia_table[0].getPrice(), sels.get(state)[0], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[1].getCarat(), dia_table[1].getCut(), dia_table[1].getColor(), dia_table[1].getClarity(), dia_table[1].getDepth(), dia_table[1].getTable(), dia_table[1].getX(), dia_table[1].getY(), dia_table[1].getZ(), dia_table[1].getPrice(), sels.get(state)[1], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[2].getCarat(), dia_table[2].getCut(), dia_table[2].getColor(), dia_table[2].getClarity(), dia_table[2].getDepth(), dia_table[2].getTable(), dia_table[2].getX(), dia_table[2].getY(), dia_table[2].getZ(), dia_table[2].getPrice(), sels.get(state)[2], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[3].getCarat(), dia_table[3].getCut(), dia_table[3].getColor(), dia_table[3].getClarity(), dia_table[3].getDepth(), dia_table[3].getTable(), dia_table[3].getX(), dia_table[3].getY(), dia_table[3].getZ(), dia_table[3].getPrice(), sels.get(state)[3], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[4].getCarat(), dia_table[4].getCut(), dia_table[4].getColor(), dia_table[4].getClarity(), dia_table[4].getDepth(), dia_table[4].getTable(), dia_table[4].getX(), dia_table[4].getY(), dia_table[4].getZ(), dia_table[4].getPrice(), sels.get(state)[4], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[5].getCarat(), dia_table[5].getCut(), dia_table[5].getColor(), dia_table[5].getClarity(), dia_table[5].getDepth(), dia_table[5].getTable(), dia_table[5].getX(), dia_table[5].getY(), dia_table[5].getZ(), dia_table[5].getPrice(), sels.get(state)[5], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[6].getCarat(), dia_table[6].getCut(), dia_table[6].getColor(), dia_table[6].getClarity(), dia_table[6].getDepth(), dia_table[6].getTable(), dia_table[6].getX(), dia_table[6].getY(), dia_table[6].getZ(), dia_table[6].getPrice(), sels.get(state)[6], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c%32c%5c   %3d / %3d%25c\n", bar, bar, '◁', '▷', table_sel4/7+1, page4, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            case 35:
                w.printf(" =============================================================================================\n");
                w.printf("%c%40s%54c\n", bar, title, bar);
                w.printf(" =============================================================================================\n");
                w.printf("%c%10s%2c%8c Your Dibs%50s%14c\n", bar, properties[state][0].name, sels.get(state)[0], bar, message, bar);
                w.printf("%c      ￣￣%10c ￣￣￣￣￣%63c\n", bar, bar, bar);
                w.printf("%c%10s%2c%8c |%5s|%9s|%5s|%7s|%5s|%5s|%4s|%4s|%4s|%7s|%4s%3c\n", bar, properties[state][1].name, sels.get(state)[1], bar, "Carat", "Cut", "Color", "Clarity", "Depth", "Table", "X", "Y", "Z", "Price", "Menu", bar);
                w.printf("%c      ￣￣%10c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[0].getCarat(), dia_table[0].getCut(), dia_table[0].getColor(), dia_table[0].getClarity(), dia_table[0].getDepth(), dia_table[0].getTable(), dia_table[0].getX(), dia_table[0].getY(), dia_table[0].getZ(), dia_table[0].getPrice(), sels.get(34)[0], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[1].getCarat(), dia_table[1].getCut(), dia_table[1].getColor(), dia_table[1].getClarity(), dia_table[1].getDepth(), dia_table[1].getTable(), dia_table[1].getX(), dia_table[1].getY(), dia_table[1].getZ(), dia_table[1].getPrice(), sels.get(34)[1], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[2].getCarat(), dia_table[2].getCut(), dia_table[2].getColor(), dia_table[2].getClarity(), dia_table[2].getDepth(), dia_table[2].getTable(), dia_table[2].getX(), dia_table[2].getY(), dia_table[2].getZ(), dia_table[2].getPrice(), sels.get(34)[2], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[3].getCarat(), dia_table[3].getCut(), dia_table[3].getColor(), dia_table[3].getClarity(), dia_table[3].getDepth(), dia_table[3].getTable(), dia_table[3].getX(), dia_table[3].getY(), dia_table[3].getZ(), dia_table[3].getPrice(), sels.get(34)[3], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[4].getCarat(), dia_table[4].getCut(), dia_table[4].getColor(), dia_table[4].getClarity(), dia_table[4].getDepth(), dia_table[4].getTable(), dia_table[4].getX(), dia_table[4].getY(), dia_table[4].getZ(), dia_table[4].getPrice(), sels.get(34)[4], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[5].getCarat(), dia_table[5].getCut(), dia_table[5].getColor(), dia_table[5].getClarity(), dia_table[5].getDepth(), dia_table[5].getTable(), dia_table[5].getX(), dia_table[5].getY(), dia_table[5].getZ(), dia_table[5].getPrice(), sels.get(34)[5], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c |%5.2f|%9s|%5s|%7s|%5.2f|%5.2f|%4.2f|%4.2f|%4.2f|$%6d|%4s%3c\n", bar, bar, dia_table[6].getCarat(), dia_table[6].getCut(), dia_table[6].getColor(), dia_table[6].getClarity(), dia_table[6].getDepth(), dia_table[6].getTable(), dia_table[6].getX(), dia_table[6].getY(), dia_table[6].getZ(), dia_table[6].getPrice(), sels.get(34)[6], bar);
                w.printf("%c%20c%74c\n", bar, bar, bar);
                w.printf("%c%20c%32c%5c   %3d / %3d%25c\n", bar, bar, '◁', '▷', table_sel4/7+1, page4, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;

            default:
                w.printf(" ================================================================\n");
                w.printf("%c%40s%25c\n", bar, title, bar);
                w.printf(" ================================================================\n");
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf("%c%20c%45c\n", bar, bar, bar);
                w.printf(" ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣\n");
                break;
        }
    }
}