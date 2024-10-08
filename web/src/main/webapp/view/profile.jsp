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
    <link rel="stylesheet" href="../css/profile.css?after">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
    	var type_chk = false;
    	var cur;
    
        function init() {
        	$("#frame").on("click", function() {
            	popClose();
            });
        	
        	$(".btn").eq(0).on("click", function() {
        		$("#inp6 label span").text("New Name");
        		$("#set").text("Change");
        		cur = "name";
        		popOpen();
        	});
        	
        	$(".btn").eq(1).on("click", function() {
        		$("#inp6 label span").text("New Phone Number");
        		$("#set").text("Change");
        		cur = "pn";
        		popOpen();
        	});
        	
        	$(".btn").eq(2).on("click", function() {
        		$("#inp6 label span").text("New Email");
        		$("#set").text("Change");
        		cur = "email";
        		popOpen();
        	});
        	
        	$(".btn").eq(3).on("click", function() {
        		$("#inp6 label span").text("New Card ID");
        		$("#set").text("Change");
        		cur = "card";
        		popOpen();
        	});
        	
        	$(".btn").eq(4).on("click", function() {
        		$("#inp6 label span").text("How many");
        		$("#set").text("Charge");
        		cur = "credit";
        		popOpen();
        	});
        	
        	$("#set").on("click", function() {
        		if(type_chk) {
        			$.ajax({
        				url:"change.do",
        				type:"get",
        				data:{
        					target:cur,
        					val:$("#inp6 input").val()
        				},
        				success:function(resp) {
        					if(resp==0) {
        						$("#inner").html("<br> Unexpected Error, please try again.");
                                
                                $("#frame").on("click", function() {
                                	$("form").eq(0).submit();
                                });
        					} else if(resp==1) {
        						$("form").eq(0).submit();
        					} else if(resp==2) {
								$("#inner").html("<br> It is duplicated value. Try again.");
                                
                                $("#frame").on("click", function() {
                                	$("form").eq(0).submit();
                                });
        					} else if(resp==3) {
								$("#inner").html("<br> You have to get valid card id.");
                                
                                $("#frame").on("click", function() {
                                	$("form").eq(0).submit();
                                });
        					}
        				}
        			})
        		}
        	});
        	
        	$("#inp6 input").on("change", function() {
        		var regex;
        		if(cur=="name") {
        			regex = /^[a-zA-Z]{5,30}$/;
        		} else if(cur=="pn") {
        			regex = /^\d{2,3}-\d{3,4}-\d{4}$/;
        		} else if(cur=="email") {
        			regex = /^[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-z]+$/;
        		} else if(cur=="card") {
        			regex = /^[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}$/;
        		} else {
        			regex = /^\d{1,10}$/;
        		}
        		
        		type_chk = regex.test($("#inp6 input").val());
        		
        		if(!type_chk) {
                    if(!$("#inp6").has("img").length) {
                        $("#inp6").append("<img id='exc'>");
                        $("#exc").attr("src", "../image/exclamation.png");
                        $("#inp6").append("<div id='exc_text'>Invalid format.</div>");
                    }
                } else {
                    $("#exc").remove();
                }
        	});
        	
        	
        }
        function popOpen() {
        	$("#modal-wrap").show();
        	$(".field").css("pointer-events", "none");
        }
        function popClose() {
        	$("#inp6 input").val("");
        	$("#exc").remove();
        	$("#modal-wrap").hide();
        	$(".field").css("pointer-events", "all");
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

	<p id="title">Profile - ${user_id}</p>
    <div class="field">
    
        <div id="inp1" class="inp">
            <input name="name" id="name" value="${name}" disabled>
            <label for="name"><span>Name</span></label>
        </div>
        
        <button class="btn">Change</button>

        <div id="inp2" class="inp">
            <input name="pn" id="pn" value="${pn}" disabled>
            <label for="pn"><span>Phone Number</span></label>
        </div>
        
        <button class="btn">Change</button>
        
        <div id="inp3" class="inp">
            <input name="email" id="email" value="${email }" disabled>
            <label for="email"><span>Email</span></label>
        </div>
        
        <button class="btn">Change</button>

        <div id="inp4" class="inp">
            <input name="card" id="card" value="${card }" disabled>
            <label for="card"><span>Card ID</span></label>
        </div>
        
        <button class="btn">Change</button>
        
        <div id="inp5" class="inp">
            <input name="credit" id="credit" value="$ ${credit }" disabled>
            <label for="credit"><span>Credit</span></label>
        </div>
        
        <button class="btn">Charge</button>

        <form action="goinquiry.do">
            <input class="menu" type="submit" value="Inquiry my diamonds">
        </form>
        
    </div>
    
    <div id="modal-wrap">
    	Diamond Broker - Set
    	<div id="frame">
    		<div id="close">        
    			<div>CLOSE</div>    
    		</div>
    	</div>
    	<hr>
    	<div id="inner">
    		<br>
    		<br>
    		
    		<div id="inp6">
            	<input name="new" id="new" required>
            	<label for="new"><span></span></label>
        	</div>
        	
    		<br>
    		<button id="set" ></button>
    	</div>
    </div>
    <hr>
</body>
</html>