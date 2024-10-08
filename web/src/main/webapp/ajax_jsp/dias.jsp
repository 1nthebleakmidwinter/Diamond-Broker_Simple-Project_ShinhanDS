<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page"><div id="cur">${cur}</div> / <div id="page">${page}</div></div>
<c:forEach items="${dialist}" var="dia">
<div class="card-dia">
    <div class="card">
        <div class="front">
            <img src="../image/card.png">
            <p>$ ${dia.getPrice()}</p>
        </div>
        <div class="back">
        	<table>
                <tbody>
                    <tr><td>Carat</td><td>${dia.getCarat()}</td></tr>
                    <tr><td>Cut</td><td>${dia.getCut()}</td></tr>
                    <tr><td>Color</td><td>${dia.getColor()}</td></tr>
                    <tr><td>Clarity</td><td>${dia.getClarity()}</td></tr>
                    <tr><td>Depth</td><td>${dia.getDepth()}</td></tr>
                    <tr><td>Table</td><td>${dia.getTable()}</td></tr>
                    <tr><td>X</td><td>${dia.getX()}</td></tr>
                    <tr><td>Y</td><td>${dia.getY()}</td></tr>
                    <tr><td>Z</td><td>${dia.getZ()}</td></tr>
                </tbody>
        	</table>
        	<button class="card-btn" name="${dia.getDia_id()}" owner="${dia.getOwner()}" price="${dia.getPrice()}"></button>
        	<button class="card-btn" name="${dia.getDia_id()}" owner="${dia.getOwner()}" price="${dia.getPrice()}"></button>
        </div>
    </div>
</div>
</c:forEach>
<div class="card-dia" id="last">
	<div class="card">
        <div class="front">
            <img src="../image/card.png">
        </div>
        <div class="back">
        </div>
    </div>
</div>