package com.personal;

import java.util.List;

import com.personal.db.DiaDAO;
import com.personal.db.DiaDTO;
import com.personal.db.MemDAO;
import com.personal.db.MemDTO;

class Model {
    MemDAO memDAO = new MemDAO();
    DiaDAO diaDAO = new DiaDAO();
    MemDTO mem;
    DiaDTO dia;

    public boolean card_dup_chk(String card) {
        return memDAO.card_dup_chk(card);
    }

    public boolean pn_dup_chk(String pn) {
        return memDAO.pn_dup_chk(pn);
    }

    public boolean email_dup_chk(String email) {
        return memDAO.email_dup_chk(email);
    }

    public boolean id_dup_chk(String id) {
        return memDAO.id_dup_chk(id);
    }

    public boolean dibs_dup_chk(String mem_id, int dia_id) {
        return memDAO.dibs_dup_chk(mem_id, dia_id);
    }

    public MemDTO getById(String id) {
        return memDAO.getById(id);
    }

    public int memInsert(MemDTO mem) {
        return memDAO.memInsert(mem);
    }

    public int dibs_insert(String mem_id, int dia_id) {
        return memDAO.dibs_insert(mem_id, dia_id);
    }

    public int memUpdate_name(String name, String id) {
        return memDAO.memUpdate_name(name, id);
    }

    public int memUpdate_pn(String pn, String id) {
        return memDAO.memUpdate_pn(pn, id);
    }

    public int memUpdate_email(String email, String id) {
        return memDAO.memUpdate_email(email, id);
    }

    public int memUpdate_card(String card, String id) {
        return memDAO.memUpdate_card(card, id);
    }
    
    public int memUpdate_credit(int credit, String id) {
        return memDAO.memUpdate_credit(credit, id);
    }

    public boolean dia_id_dup_chk(int id) {
        return diaDAO.dia_id_dup_chk(id);
    }

    public boolean is_owner(int dia_id, String mem_id) {
        return diaDAO.is_owner(dia_id, mem_id);
    }

    public String makeSql(String price, String carat, String cut, String color,
    String clarity, String depth, String table) {
        return diaDAO.makeSql(price, carat, cut, color, clarity, depth, table);
    }

    public int totalDia() {
        return diaDAO.totalDia();
    }

    public int totalDia(String sql) {
        return diaDAO.totalDia(sql);
    }

    public int inquiry_total(String owner_id) {
        return diaDAO.inquiry_total(owner_id);
    }

    public int dibs_total(String cur_id) {
        return diaDAO.dibs_total(cur_id);
    }

    public List<DiaDTO> table_set(int table_sel, int total) {
        return diaDAO.table_set(table_sel, total);
    }

    public List<DiaDTO> table_set(int table_sel2, int total, String sql) {
        return diaDAO.table_set(table_sel2, total, sql);
    }

    public List<DiaDTO> inquiry(int table_sel3, int total, String owner_id) {
        return diaDAO.inquiry(table_sel3, total, owner_id);
    }

    public List<DiaDTO> dibs_table(int table_sel4, int total, String cur_id) {
        return diaDAO.dibs_table(table_sel4, total, cur_id);
    }

    public int del_dia(int dia_id) {
        return diaDAO.del_dia(dia_id);
    }

    public int del_from_dibs(int dia_id, String mem_id) {
        return diaDAO.del_from_dibs(dia_id, mem_id);
    }

    public int diaInsert(int dia_id, double carat, String cut, String color, String clarity,
    double depth, double table, double x, double y, double z, int price, String owner) {
        return diaDAO.diaInsert(dia_id, carat, cut, color, clarity, depth, table, x, y, z, price, owner);
    }

    
}

