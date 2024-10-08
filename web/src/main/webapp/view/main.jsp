<%@page import="home.shinhan.diabroker.Model" %>
<%@page import="home.shinhan.diabroker.DiaDTO" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Diamond Broker</title>
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css' integrity='sha512-c42qTSw/wPZ3/5LBzD+Bw5f7bSF2oxou6wEb+I/lqeaKV5FDIfMvvRp772y4jcJLKuGUOpbJMdg/BTl50fJYAw==' crossorigin='anonymous'/>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Dancing+Script:wght@400..700&family=Nanum+Brush+Script&family=Nanum+Myeongjo&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css?after">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
        function init() {
            $(".back .card-btn:nth-of-type(1)").text("Dibs");
            $(".back .card-btn:nth-of-type(2)").text("Buy");
            
            $("#frame").on("click", function() {
            	popClose();
            });
            
            $(".back .card-btn:nth-of-type(1)").on("click", function() {
            	$.ajax({
            		url:"todibs.do",
            		type:"get",
            		data:{dia_id:$(this).attr("name")},
            		success:function(resp) {
            			$("#eval").text(resp);
            			popOpen();
            		}
            	})
            })
            
            $(".back .card-btn:nth-of-type(2)").on("click", function() {
            	$.ajax({
            		url:"buy.do",
            		type:"get",
            		data:{dia_id:$(this).attr("name"), owner:$(this).attr("owner"), price:$(this).attr("price")},
            		success:function(resp) {
            			$("#eval").text(resp);
            			popOpen();
            		}
            	})
            })
        }
        function popOpen() {
        	$("#modal-wrap").show();
        	$(".field form").css("pointer-events", "none");
        }
        function popClose() {
        	$("#modal-wrap").hide();
        	$(".field form").css("pointer-events", "all");
        }
        $(init);
    </script>
</head>
<body>
    <hr>
    <div id="side">
        <p id="wel">Welcome, ${user_id}.</p>
        <img id="profile" src="../image/profile.png">

        <form action="profile.do">
            <input class="menu" type="submit" value="My Profile">
        </form>
        
        <form action="gostore.do">
            <input class="menu" type="submit" value="Store">
        </form>
        
        <form action="goregister.do">
            <input class="menu" type="submit" value="Register">
        </form>
        
        <form action="godibs.do">
			<input class="menu" type="submit" value="Dibs">
        </form>

        <form action="signin.do">
            <input class="menu" type="submit" value="Log Out">
        </form>
    </div>

	<%
	Model model = new Model();
	int total = model.totalDia();
	List<DiaDTO> dialist = model.table_set(1, total);
	%>

	<p id="top3">Top 3 in the store</p>
    <div class="field">
    
        <div class="card-dia">
            <div class="card">
                <div class="front">
                    <img src="../image/card.png">
                    <p>$ <%=dialist.get(0).getPrice()%></p>
                </div>
                <div class="back">
                	<table>
                        <tbody>
                            <tr><td>Carat</td><td><%=dialist.get(0).getCarat() %></td></tr>
                            <tr><td>Cut</td><td><%=dialist.get(0).getCut() %></td></tr>
                            <tr><td>Color</td><td><%=dialist.get(0).getColor() %></td></tr>
                            <tr><td>Clarity</td><td><%=dialist.get(0).getClarity() %></td></tr>
                            <tr><td>Depth</td><td><%=dialist.get(0).getDepth() %></td></tr>
                            <tr><td>Table</td><td><%=dialist.get(0).getTable() %></td></tr>
                            <tr><td>X</td><td><%=dialist.get(0).getX() %></td></tr>
                            <tr><td>Y</td><td><%=dialist.get(0).getY() %></td></tr>
                            <tr><td>Z</td><td><%=dialist.get(0).getZ() %></td></tr>
                        </tbody>
                	</table>
                	<button class="card-btn" name="<%=dialist.get(0).getDia_id() %>" owner="<%=dialist.get(0).getOwner() %>" price="<%=dialist.get(0).getPrice() %>"></button>
        			<button class="card-btn" name="<%=dialist.get(0).getDia_id() %>" owner="<%=dialist.get(0).getOwner() %>" price="<%=dialist.get(0).getPrice() %>"></button>
                </div>
            </div>
        </div>

        <div class="card-dia">
            <div class="card">
                <div class="front">
                    <img src="../image/card.png">
                    <p>$ <%=dialist.get(1).getPrice()%></p>
                </div>
                <div class="back">
                	<table>
                        <tbody>
                            <tr><td>Carat</td><td><%=dialist.get(1).getCarat() %></td></tr>
                            <tr><td>Cut</td><td><%=dialist.get(1).getCut() %></td></tr>
                            <tr><td>Color</td><td><%=dialist.get(1).getColor() %></td></tr>
                            <tr><td>Clarity</td><td><%=dialist.get(1).getClarity() %></td></tr>
                            <tr><td>Depth</td><td><%=dialist.get(1).getDepth() %></td></tr>
                            <tr><td>Table</td><td><%=dialist.get(1).getTable() %></td></tr>
                            <tr><td>X</td><td><%=dialist.get(1).getX() %></td></tr>
                            <tr><td>Y</td><td><%=dialist.get(1).getY() %></td></tr>
                            <tr><td>Z</td><td><%=dialist.get(1).getZ() %></td></tr>
                        </tbody>
                	</table>
                	<button class="card-btn" name="<%=dialist.get(1).getDia_id() %>" owner="<%=dialist.get(1).getOwner() %>" price="<%=dialist.get(1).getPrice() %>"></button>
        			<button class="card-btn" name="<%=dialist.get(1).getDia_id() %>" owner="<%=dialist.get(1).getOwner() %>" price="<%=dialist.get(1).getPrice() %>"></button>
                </div>
            </div>
        </div>

        <div class="card-dia">
            <div class="card">
                <div class="front">
                    <img src="../image/card.png">
                    <p>$ <%=dialist.get(2).getPrice()%></p>
                </div>
                <div class="back">
                	<table>
                        <tbody>
                            <tr><td>Carat</td><td><%=dialist.get(2).getCarat() %></td></tr>
                            <tr><td>Cut</td><td><%=dialist.get(2).getCut() %></td></tr>
                            <tr><td>Color</td><td><%=dialist.get(2).getColor() %></td></tr>
                            <tr><td>Clarity</td><td><%=dialist.get(2).getClarity() %></td></tr>
                            <tr><td>Depth</td><td><%=dialist.get(2).getDepth() %></td></tr>
                            <tr><td>Table</td><td><%=dialist.get(2).getTable() %></td></tr>
                            <tr><td>X</td><td><%=dialist.get(2).getX() %></td></tr>
                            <tr><td>Y</td><td><%=dialist.get(2).getY() %></td></tr>
                            <tr><td>Z</td><td><%=dialist.get(2).getZ() %></td></tr>
                        </tbody>
                	</table>
                	<button class="card-btn" name="<%=dialist.get(2).getDia_id() %>" owner="<%=dialist.get(2).getOwner() %>" price="<%=dialist.get(2).getPrice() %>"></button>
        			<button class="card-btn" name="<%=dialist.get(2).getDia_id() %>" owner="<%=dialist.get(2).getOwner() %>" price="<%=dialist.get(2).getPrice() %>"></button>
                </div>
            </div>
        </div>
        
        <div id="modal-wrap">
    	Diamond Broker - Notice
    	<div id="frame">
    		<div id="close">        
    			<div>CLOSE</div>    
    		</div>
    	</div>
    	<hr>
    	<div id="inner">
    		<br>
    		<div id="eval" ></div>
    	</div>
    </div>
        
    </div>
    <hr>
</body>
</html>