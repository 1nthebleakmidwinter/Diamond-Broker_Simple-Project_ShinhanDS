package com.personal.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.personal.db.util.DBUtil;

public class MemDAO {
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    private MemDTO makeUser(ResultSet rs) throws SQLException {
        MemDTO mem = new MemDTO();
        mem.setCard_id(rs.getString("CARD_ID"));
        mem.setCredit(rs.getInt("CREDIT"));
        mem.setEmail(rs.getString("EMAIL"));
        mem.setId(rs.getString("ID"));
        mem.setMem_name(rs.getString("MEM_NAME"));
        mem.setPhone_num(rs.getString("PHONE_NUM"));
        mem.setPw(rs.getString("PW"));
        mem.setSigned_date(rs.getDate("SIGNED_DATE"));
        return mem;
    }

    public boolean card_dup_chk(String card) {
        MemDTO mem = null;
        String sql = "SELECT * FROM MEMBERS WHERE CARD_ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, card);
            rs = pst.executeQuery();
            if(rs.next()) {
                mem = makeUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(mem==null) {
            return true;
        } else return false;
    }

    public boolean pn_dup_chk(String pn) {
        MemDTO mem = null;
        String sql = "SELECT * FROM MEMBERS WHERE PHONE_NUM=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, pn);
            rs = pst.executeQuery();
            if(rs.next()) {
                mem = makeUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(mem==null) {
            return true;
        } else return false;
    }

    public boolean email_dup_chk(String email) {
        MemDTO mem = null;
        String sql = "SELECT * FROM MEMBERS WHERE EMAIL=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            rs = pst.executeQuery();
            if(rs.next()) {
                mem = makeUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(mem==null) {
            return true;
        } else return false;
    }

    public boolean id_dup_chk(String id) {
        MemDTO mem = null;
        String sql = "SELECT * FROM MEMBERS WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                mem = makeUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(mem==null) {
            return true;
        } else return false;
    }

    public boolean dibs_dup_chk(String mem_id, int dia_id) {
        String id=null;
        String sql = "SELECT * FROM DIBS WHERE MEM_ID=? AND DIA_ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, mem_id);
            pst.setInt(2, dia_id);
            rs = pst.executeQuery();
            rs.next();
            id = rs.getString(1);
        } catch (SQLException e) {
            // e.printStackTrace();
        }   finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        if(id==null) {
            return true;
        } else return false;
    } 

    public MemDTO getById(String id) {
        MemDTO mem = null;
        String sql = "SELECT * FROM MEMBERS WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if(rs.next()) {
                mem = makeUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return mem;
    }

    public int memInsert(MemDTO mem) {
        int result=0;
        String sql = "INSERT INTO MEMBERS(MEM_NAME, PHONE_NUM, EMAIL, ID, PW, SIGNED_DATE) VALUES(?, ?, ?, ?, ?, SYSDATE)";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, mem.getMem_name());
            pst.setString(2, mem.getPhone_num());
            pst.setString(3, mem.getEmail());
            pst.setString(4, mem.getId());
            pst.setString(5, mem.getPw());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int dibs_insert(String mem_id, int dia_id) {
        int result=0;
        String sql = "INSERT INTO DIBS(MEM_ID, DIA_ID) VALUES(?, ?)";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, mem_id);
            pst.setInt(2, dia_id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int memUpdate_name(String name, String id) {
        int result = 0;
        String sql = "UPDATE MEMBERS SET MEM_NAME=? WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, name);
            pst.setString(2, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int memUpdate_pn(String pn, String id) {
        int result = 0;
        String sql = "UPDATE MEMBERS SET PHONE_NUM=? WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, pn);
            pst.setString(2, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int memUpdate_email(String email, String id) {
        int result = 0;
        String sql = "UPDATE MEMBERS SET EMAIL=? WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int memUpdate_card(String card, String id) {
        int result = 0;
        String sql = "UPDATE MEMBERS SET CARD_ID=? WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, card);
            pst.setString(2, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }

    public int memUpdate_credit(int credit, String id) {
        int result = 0;
        String sql = "UPDATE MEMBERS SET CREDIT=? WHERE ID=?";
        conn = DBUtil.dbConnnect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, credit);
            pst.setString(2, id);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbDisconnect(conn, pst, rs);
        }
        return result;
    }
}