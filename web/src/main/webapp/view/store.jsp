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
    <link rel="stylesheet" href="../css/store.css?after">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
    	var sel=1;
    	var state=0;
        function init() {
            dias();
	        $("#previous").on("click", prev);
	        $("#next").on("click", next);
	        $("#btn").on("click", function() {
	        	sel=1;
	        	search();
	        });
	        sel=1
	        
	        $("#frame").on("click", function() {
            	popClose();
            });
        }
        function dias() {
        	$.ajax({
                url:"store.do",
                data:{user_id:"${user_id}", "sel":sel},
                success:function(responseData) {
                    $(".field").html(responseData);
		            $(".back .card-btn:nth-of-type(1)").text("Dibs");
		            $(".back .card-btn:nth-of-type(2)").text("Buy");
		            $(".card-dia").addClass("card-ani");
		            
		            $(".back .card-btn:nth-of-type(1)").on("click", function() {
		            	$.ajax({
		            		url:"todibs.do",
		            		type:"get",
		            		data:{dia_id:$(this).attr("name")},
		            		success:function(resp) {
		            			if(state==0) dias();
		            			else search();
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
		            			if(state==0) dias();
		            			else search();
		            			$("#eval").text(resp);
		            			popOpen();
		            		}
		            	})
		            })
                },
                error:function(xhr, status, error){
                    alert(error); // ajax가 실패, 보안실패, 주소오류
                }
            })
        }
        function search() {
        	if(state==0) {
        		state=1;
        	}
        	$.ajax({
                url:"search.do",
                type:"post",
                data:{"user_id":"${user_id}", "sel":sel,
                	price:$("#price option:selected").val(), carat:$("#carat option:selected").val(),
                	cut:$("#cut option:selected").val(), color:$("#color option:selected").val(),
                	clarity:$("#clarity option:selected").val(), depth:$("#depth option:selected").val(),
                	table:$("#table option:selected").val()
                	},
                success:function(responseData) {
                    $(".field").html(responseData);
		            $(".back .card-btn:nth-of-type(1)").text("Dibs");
		            $(".back .card-btn:nth-of-type(2)").text("Buy");
		            $(".card-dia").addClass("card-ani");
		            
		            $(".back .card-btn:nth-of-type(1)").on("click", function() {
		            	$.ajax({
		            		url:"todibs.do",
		            		type:"get",
		            		data:{dia_id:$(this).attr("name")},
		            		success:function(resp) {
		            			if(state==0) dias();
		            			else search();
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
		            			if(state==0) dias();
		            			else search();
		            			$("#eval").text(resp);
		            			popOpen();
		            		}
		            	})
		            })
                },
                error:function(xhr, status, error){
                    alert(error); // ajax가 실패, 보안실패, 주소오류
                }
            })
        }
        function prev() {
        	if(state==0) {
	        	if(parseInt(sel/7)+1!=1) {
	        		sel-=7
	        		dias();
	        	}
        	} else {
	        	if(parseInt(sel/7)+1!=1) {
	        		sel-=7
	        		search();
	        	}
        	}
        }
        function next() {
        	if(state==0) {
	        	if(parseInt(sel/7)+1<Number($("#page").text())) {
	        		sel+=7
		        	dias();
	        	}        		
        	} else {
	        	if(parseInt(sel/7)+1<Number($("#page").text())) {
	        		sel+=7
		        	search();
	        	}        		
        	}
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
	<p id="top3">Store</p>
	
	<div id="price" class="selectBox">
                <select class="select" >
                	<option disabled selected>Price Option</option>
                	<option>$2000 ↓</option>
                	<option>$4000 ↓</option>
                	<option>$6000 ↓</option>
                	<option>$8000 ↓</option>
                	<option>$10000 ↓</option>
                	<option>$12000 ↓</option>
                	<option>$14000 ↓</option>
                	<option>$16000 ↓</option>
                	<option>$16000 ↑</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="carat" class="selectBox">
                <select class="select" >
                	<option disabled selected>Carat Option</option>
                	<option>0.5 ↓</option>
                	<option>1.0 ↓</option>
                	<option>1.5 ↓</option>
                	<option>2.0 ↓</option>
                	<option>2.5 ↓</option>
                	<option>3.0 ↓</option>
                	<option>3.5 ↓</option>
                	<option>4.0 ↓</option>
                	<option>4.0 ↑</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="cut" class="selectBox">
                <select class="select" >
                	<option disabled selected>Cut Option</option>
                	<option>Fair √</option>
                	<option>Good √</option>
                	<option>Very Good √</option>
                	<option>Premium √</option>
                	<option>Ideal √</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="color" class="selectBox">
                <select class="select" >
                	<option disabled selected>Color Option</option>
                	<option>J √</option>
                	<option>I √</option>
                	<option>H √</option>
                	<option>G √</option>
                	<option>F √</option>
                	<option>E √</option>
                	<option>D √</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="clarity" class="selectBox">
                <select class="select" >
                	<option disabled selected>Clarity Option</option>
                	<option>I1 √</option>
                	<option>SI2 √</option>
                	<option>SI1 √</option>
                	<option>VS2 √</option>
                	<option>VS1 √</option>
                	<option>VVS2 √</option>
                	<option>VVS1 √</option>
                	<option>IF √</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="depth" class="selectBox">
                <select class="select" >
                	<option disabled selected>Depth Option</option>
                	<option>45% ↓</option>
                	<option>50% ↓</option>
                	<option>55% ↓</option>
                	<option>60% ↓</option>
                	<option>65% ↓</option>
                	<option>70% ↓</option>
                	<option>75% ↓</option>
                	<option>79% ↓</option>
                	<option>79% ↑</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <div id="table" class="selectBox">
                <select class="select" >
                	<option disabled selected>Table Option</option>
                	<option>45% ↓</option>
                	<option>50% ↓</option>
                	<option>55% ↓</option>
                	<option>60% ↓</option>
                	<option>65% ↓</option>
                	<option>70% ↓</option>
                	<option>75% ↓</option>
                	<option>80% ↓</option>
                	<option>80% ↑</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
    </div>
    
    <button id="btn">Search →</button>
	
    <div class="field">

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
    
    <img id="previous" src="../image/previous.png">
    <img id="next" src="../image/next.png">
    <hr>
</body>
</html>