<%@page import="home.shinhan.diabroker.Model" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
int result = 0;
Model model = new Model();
model.mem = model.getById(request.getParameter("user_id"));
if(model.mem!=null&&model.mem.getPw().equals(request.getParameter("user_pw"))) {
    result+=1;
}
out.print(result);
%>