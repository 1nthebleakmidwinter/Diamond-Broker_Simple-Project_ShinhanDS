<%@page import="home.shinhan.diabroker.Model" %>
<%@page import="home.shinhan.diabroker.MemDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int result = 0;
Model model = new Model();
model.mem = new MemDTO();
model.mem.setMem_name(request.getParameter("name"));
model.mem.setPhone_num(request.getParameter("pn"));
model.mem.setEmail(request.getParameter("email"));
model.mem.setId(request.getParameter("id"));
model.mem.setPw(request.getParameter("pw"));
if(model.memInsert(model.mem)==1) {
	result = 1;
}
out.print(result);
%>