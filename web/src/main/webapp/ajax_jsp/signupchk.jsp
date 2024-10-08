<%@page import="home.shinhan.diabroker.Model" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String result="";
Model model = new Model();
if(model.pn_dup_chk(request.getParameter("pn"))) result += 1;
else result += 0;
if(model.email_dup_chk(request.getParameter("email"))) result += 1;
else result += 0;
if(model.id_dup_chk(request.getParameter("id"))) result += 1;
else result += 0;
out.print(result);
%>