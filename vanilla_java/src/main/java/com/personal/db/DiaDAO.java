package com.personal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.personal.db.util.DBUtil;

public class DiaDAO {
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    private DiaDTO makeDia(ResultSet rs) throws SQLException {
        DiaDTO dia = new DiaDTO();
        dia.setCarat(rs.getDouble("CARAT"));
        dia.setClarity(rs.getString("CLARITY"));
        dia.setColor(rs.getString("COLOR"));
        dia.setCut(rs.getString("CUT"));
        dia.setDepth(rs.getDouble("DEPTH"));
        dia.setDia_id(rs.getInt("DIA_ID"));
        dia.setPrice(rs.getInt("PRICE"));
        dia.setX(rs.getDouble("X"));
        dia.setY(rs.getDouble("Y"));
        dia.setZ(rs.getDouble("Z"));
        dia.setTable(rs.getDouble("DIA_TABLE"));
        dia.setOwner(rs.getString("OWNER"));
        return dia;
    }

    public boolean dia_id_dup_chk(int id) {
        DiaDTO dia = null;
        String sql = "SELECT * FROM STORE_ WHERE DIA_ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                dia = makeDia(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(dia==null) {
            return true;
        } else return false;
    }

    public boolean is_owner(int dia_id, String mem_id) {
        DiaDTO dia = null;
        String sql = "SELECT * FROM STORE_ WHERE DIA_ID=? AND OWNER=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dia_id);
            pst.setString(2, mem_id);
            rs = pst.executeQuery();
            if(rs.next()) {
                dia = makeDia(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(dia==null) {
            return false;
        } else return true;
    }

    public String makeSql(String price, String carat, String cut, String color,
    String clarity, String depth, String table) {
        String sql = "SELECT * FROM STORE_ WHERE", blank=" ", and="AND";
        int count = 7;

        if(price.equals("")) count-=1;
        else if(price.equals("$2000 ↓")) price = "PRICE <= 2000";
        else if(price.equals("$4000 ↓")) price = "PRICE <= 4000";
        else if(price.equals("$6000 ↓")) price = "PRICE <= 6000";
        else if(price.equals("$8000 ↓")) price = "PRICE <= 8000";
        else if(price.equals("$10000 ↓")) price = "PRICE <= 10000";
        else if(price.equals("$12000 ↓")) price = "PRICE <= 12000";
        else if(price.equals("$14000 ↓")) price = "PRICE <= 14000";
        else if(price.equals("$16000 ↓")) price = "PRICE <= 16000";
        else if(price.equals("$16000 ↑")) price = "PRICE >= 16000";

        if(carat.equals("")) count-=1;
        else if(carat.equals("0.5 ↓")) carat = "CARAT <= 0.5";
        else if(carat.equals("1.0 ↓")) carat = "CARAT <= 1.0";
        else if(carat.equals("1.5 ↓")) carat = "CARAT <= 1.5";
        else if(carat.equals("2.0 ↓")) carat = "CARAT <= 2.0";
        else if(carat.equals("2.5 ↓")) carat = "CARAT <= 2.5";
        else if(carat.equals("3.0 ↓")) carat = "CARAT <= 3.0";
        else if(carat.equals("3.5 ↓")) carat = "CARAT <= 3.5";
        else if(carat.equals("4.0 ↓")) carat = "CARAT <= 4.0";
        else if(carat.equals("4.0 ↑")) carat = "CARAT >= 4.0";

        if(cut.equals("")) count-=1;
        else if(cut.equals("Fair √")) cut = "CUT = 'Fair'";
        else if(cut.equals("Good √")) cut = "CUT = 'Good'";
        else if(cut.equals("Very Good √")) cut = "CUT = 'Very Good'";
        else if(cut.equals("Premium √")) cut = "CUT = 'Premium'";
        else if(cut.equals("Ideal √")) cut = "CUT = 'Ideal'";

        if(color.equals("")) count-=1;
        else if(color.equals("J √")) color = "COLOR = 'J'";
        else if(color.equals("I √")) color = "COLOR = 'I'";
        else if(color.equals("H √")) color = "COLOR = 'H'";
        else if(color.equals("G √")) color = "COLOR = 'G'";
        else if(color.equals("F √")) color = "COLOR = 'F'";
        else if(color.equals("E √")) color = "COLOR = 'E'";
        else if(color.equals("D √")) color = "COLOR = 'D'";

        if(clarity.equals("")) count-=1;
        else if(clarity.equals("I1 √")) clarity = "CLARITY = 'I1'";
        else if(clarity.equals("SI2 √")) clarity = "CLARITY = 'SI2'";
        else if(clarity.equals("SI1 √")) clarity = "CLARITY = 'SI1'";
        else if(clarity.equals("VS2 √")) clarity = "CLARITY = 'VS2'";
        else if(clarity.equals("VS1 √")) clarity = "CLARITY = 'VS1'";
        else if(clarity.equals("VVS2 √")) clarity = "CLARITY = 'VVS2'";
        else if(clarity.equals("VVS1 √")) clarity = "CLARITY = 'VVS1'";
        else if(clarity.equals("IF √")) clarity = "CLARITY = 'IF'";

        if(depth.equals("")) count-=1;
        else if(depth.equals("45% ↓")) depth = "DEPTH <= 45";
        else if(depth.equals("50% ↓")) depth = "DEPTH <= 50";
        else if(depth.equals("55% ↓")) depth = "DEPTH <= 55";
        else if(depth.equals("60% ↓")) depth = "DEPTH <= 60";
        else if(depth.equals("65% ↓")) depth = "DEPTH <= 65";
        else if(depth.equals("70% ↓")) depth = "DEPTH <= 70";
        else if(depth.equals("75% ↓")) depth = "DEPTH <= 75";
        else if(depth.equals("79% ↓")) depth = "DEPTH <= 79";
        else if(depth.equals("79% ↑")) depth = "DEPTH >= 79";

        if(table.equals("")) count-=1;
        else if(table.equals("45% ↓")) table = "DIA_TABLE <= 45";
        else if(table.equals("50% ↓")) table = "DIA_TABLE <= 50";
        else if(table.equals("55% ↓")) table = "DIA_TABLE <= 55";
        else if(table.equals("60% ↓")) table = "DIA_TABLE <= 60";
        else if(table.equals("65% ↓")) table = "DIA_TABLE <= 65";
        else if(table.equals("70% ↓")) table = "DIA_TABLE <= 70";
        else if(table.equals("75% ↓")) table = "DIA_TABLE <= 75";
        else if(table.equals("80% ↓")) table = "DIA_TABLE <= 80";
        else if(table.equals("80% ↑")) table = "DIA_TABLE >= 80";

        List<String> cds = new ArrayList<>();
        cds.add(price); cds.add(carat); cds.add(cut); cds.add(color); cds.add(clarity); cds.add(depth); cds.add(table);
        
        if(count==0) sql = "FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM STORE_ ORDER BY PRICE DESC) S )";
        else {
            count = 0;
            for(int i=0;i<7;i++) {
                if(cds.get(i).equals("")) continue;
                else if(count==0) {
                    count+=1;
                    sql = sql+blank+cds.get(i);
                }
                else sql = sql+blank+and+blank+cds.get(i);
            }
            sql = "FROM (SELECT ROWNUM RN, S.* FROM ("+
            sql+blank+"ORDER BY PRICE DESC) S )";
        }
        return sql;
    }

    public int totalDia() {
        int total=0;
        String sql = "SELECT COUNT(DIA_ID) FROM STORE_";
        conn = DBUtil.dbConnnect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            total = rs.getInt("COUNT(DIA_ID)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return total;
    }

    public int totalDia(String sql) {
        int total=0;
        sql = "SELECT COUNT(DIA_ID)"+" "+sql;
        conn = DBUtil.dbConnnect();
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            rs.next();
            total = rs.getInt("COUNT(DIA_ID)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return total;
    }

    public int inquiry_total(String owner_id) {
        int total = 0;
        String sql = "SELECT COUNT(DIA_ID) FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM STORE_ WHERE OWNER=? ORDER BY PRICE DESC) S )";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, owner_id);
            rs = pst.executeQuery();
            rs.next();
            total = rs.getInt("COUNT(DIA_ID)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return total;
    }

    public int dibs_total(String cur_id) {
        int total = 0;
        String sql = "SELECT COUNT(DIA_ID) FROM STORE_ S JOIN (SELECT DIA_ID FROM DIBS WHERE MEM_ID=?) X USING(DIA_ID)";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, cur_id);
            rs = pst.executeQuery();
            rs.next();
            total = rs.getInt("COUNT(DIA_ID)");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return total;
    }

    public List<DiaDTO> table_set(int table_sel, int total) {
        List<DiaDTO> dialist = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM STORE_ ORDER BY PRICE DESC) S ) WHERE ?<=RN AND RN<=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            if(table_sel+6>total) {
                pst.setInt(1, table_sel);
                pst.setInt(2, total);
            } else {
                pst.setInt(1, table_sel);
                pst.setInt(2, table_sel+6);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                DiaDTO dia = makeDia(rs);
                dialist.add(dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return dialist;
    }

    public List<DiaDTO> table_set(int table_sel2, int total, String sql) {
        List<DiaDTO> dialist = new ArrayList<>();
        sql = "SELECT *"+" "+sql+" "+"WHERE ?<=RN AND RN<=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            if(table_sel2+6>total) {
                pst.setInt(1, table_sel2);
                pst.setInt(2, total);
            } else {
                pst.setInt(1, table_sel2);
                pst.setInt(2, table_sel2+6);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                DiaDTO dia = makeDia(rs);
                dialist.add(dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dialist;
    }

    public List<DiaDTO> inquiry(int table_sel3, int total, String owner_id) {
        List<DiaDTO> dialist = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM STORE_ WHERE OWNER=? ORDER BY PRICE DESC) S ) WHERE ?<=RN AND RN<=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, owner_id);
            if(table_sel3+6>total) {
                pst.setInt(2, table_sel3);
                pst.setInt(3, total);
            } else {
                pst.setInt(2, table_sel3);
                pst.setInt(3, table_sel3+6);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                DiaDTO dia = makeDia(rs);
                dialist.add(dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return dialist;
    }

    public List<DiaDTO> dibs_table(int table_sel4, int total, String cur_id) {
        List<DiaDTO> dialist = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT ROWNUM RN, S.* FROM (SELECT * FROM STORE_ JOIN (SELECT DIA_ID FROM DIBS WHERE MEM_ID=?) X USING(DIA_ID)) S ) WHERE ?<=RN AND RN<=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, cur_id);
            if(table_sel4+6>total) {
                pst.setInt(2, table_sel4);
                pst.setInt(3, total);
            } else {
                pst.setInt(2, table_sel4);
                pst.setInt(3, table_sel4+6);
            }
            rs = pst.executeQuery();
            while(rs.next()) {
                DiaDTO dia = makeDia(rs);
                dialist.add(dia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return dialist;
    }

    public int del_dia(int dia_id) {
        int result = 0;
        String sql = "DELETE FROM STORE_ WHERE DIA_ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dia_id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
        return result;
    }

    public int del_from_dibs(int dia_id, String mem_id) {
        int result = 0;
        String sql = "DELETE FROM DIBS WHERE DIA_ID=? AND MEM_ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dia_id);
            pst.setString(2, mem_id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			DBUtil.dbDisconnect(conn, pst, rs);
		}
        return result;
    }

    public int diaInsert(int dia_id, double carat, String cut, String color, String clarity,
    double depth, double table, double x, double y, double z, int price, String owner) {
        int result = 0;
        String sql = "INSERT INTO STORE_ VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        conn = DBUtil.dbConnnect();

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, dia_id);
            pst.setDouble(2, carat);
            pst.setString(3, cut);
            pst.setString(4, color);
            pst.setString(5, clarity);
            pst.setDouble(6, depth);
            pst.setDouble(7, table);
            pst.setDouble(8, x);
            pst.setDouble(9, y);
            pst.setDouble(10, z);
            pst.setInt(11, price);
            pst.setString(12, owner);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }
}
