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
    <link rel="stylesheet" href="../css/inquiry.css?after">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
    	var sel=1;
        function init() {
            dias();
            $("#previous").on("click", prev);
	        $("#next").on("click", next);
	        sel=1
        }
        function dias() {
            $.ajax({
                url:"inquiry.do",
                type:"get",
                data:{"user_id":"${user_id}", "sel":sel},
                success:function(responseData) {
                    $(".field").html(responseData);
                    $(".back .card-btn:nth-of-type(1)").text("Cancel");
		            $(".back .card-btn:nth-of-type(2)").remove();
		            $(".card-dia").addClass("card-ani");
		            
		            $(".back .card-btn:nth-of-type(1)").on("click", function() {
		            	$.ajax({
		            		url:"cancel.do",
		            		type:"get",
		            		data:{dia_id:$(this).attr("name")},
		            		success:function(resp) {
		            			dias();
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
        	if(parseInt(sel/7)+1!=1) {
        		sel-=7
        		dias();
        	}
        }
        function next() {
        	if(parseInt(sel/7)+1<Number($("#page").text())) {
        		sel+=7
	        	dias();
        	}
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
	<p id="top3">Your Diamonds</p>
    <div class="field">

    </div>
    <img id="previous" src="../image/previous.png">
    <img id="next" src="../image/next.png">
    <hr>
</body>
</html>