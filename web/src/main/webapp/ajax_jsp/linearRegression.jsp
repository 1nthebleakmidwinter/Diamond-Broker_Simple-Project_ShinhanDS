<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String carat = request.getParameter("carat");
String cut = request.getParameter("cut");
String color = request.getParameter("color");
String clarity = request.getParameter("clarity");
String depth = request.getParameter("depth");
String table = request.getParameter("table");
String x = request.getParameter("x");
String y = request.getParameter("y");
String z = request.getParameter("z");

ProcessBuilder processBuilder = new ProcessBuilder("python", "C:/close/shds/firstproject/LinearRegression.py", carat, cut, color, clarity, depth, table, x, y, z);
Process process = processBuilder.start();
InputStream inputStream;
String evaluated=null;
BufferedReader reader; 
while(evaluated != null) {
	inputStream = process.getInputStream();
	reader = new BufferedReader(new InputStreamReader(inputStream));
	evaluated = reader.readLine();
}
System.out.println(carat+" "+cut+" "+color+" "+clarity+" "+depth+" "+table+" "+x+" "+y+" "+z+" ");
System.out.println(evaluated);
%>