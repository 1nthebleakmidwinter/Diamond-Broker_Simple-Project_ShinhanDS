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
    <link rel="stylesheet" href="../css/register.css?after">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
        function init() {
        	var carat_chk = false;
        	var depth_chk = false;
        	var table_chk = false;
        	var x_chk = false;
        	var y_chk = false;
        	var z_chk = false;
        	
            var cut = $("#inp2 .select");
            var color = $("#inp3 .select");
            var clarity = $("#inp4 .select");
            
            cut.on("change", function() {
                $("#cut").val($("#inp2 option:selected").val());
            });
            color.on("change", function() {
                $("#color").val($("#inp3 option:selected").val());
            });
            clarity.on("change", function() {
                $("#clarity").val($("#inp4 option:selected").val());
            });
            
            $("#btn").on("click", function(e) {
            	e.preventDefault();
            	if(carat_chk && depth_chk && table_chk && x_chk && y_chk && z_chk && !$("#inp2 option:selected").val().includes("Select") && !$("#inp3 option:selected").val().includes("Select") && !$("#inp4 option:selected").val().includes("Select")) {
	            	$.ajax({
	                    url:"register.do",
	                    type:"post",
	                    data:{carat:$("input[name='carat']").val(), cut:$("input[name='cut']").val(),
	                    	color:$("input[name='color']").val(), clarity:$("input[name='clarity']").val(),
	                    	depth:$("input[name='depth']").val(), table:$("input[name='table']").val(),
	                    	x:$("input[name='x']").val(), y:$("input[name='y']").val(), z:$("input[name='z']").val()
	                    	},
	                    success:function(resp) {
	                        $("#eval").text(resp);
	                    }
	                })
	                popOpen();            		
            	}
            });
            
            $("#frame").on("click", function() {
            	popClose();
            });
            
            $("#reg").on("click", function() {
            	$.ajax({
                    url:"register2.do",
                    type:"post",
                    data:{carat:$("input[name='carat']").val(), cut:$("input[name='cut']").val(),
                    	color:$("input[name='color']").val(), clarity:$("input[name='clarity']").val(),
                    	depth:$("input[name='depth']").val(), table:$("input[name='table']").val(),
                    	x:$("input[name='x']").val(), y:$("input[name='y']").val(), z:$("input[name='z']").val(), price:$("#eval").text().substr(2)
                    	},
                    success:function(resp) {
                        $("#inner").html("<br>"+resp);
                        
                        $("#frame").on("click", function() {
                        	location.reload();
                        });
                    }
                })
            });
            
            $("#inp1 input").on("change", function() {
            	const regex = /^\d{1}\.\d{1,2}$/;
                carat_chk = regex.test( $("#inp1 input").val()) || /^\d{1}\$/.test($("#inp1 input").val());
                if(!carat_chk) {
                    if(!$("#inp1").has("img").length) {
                        $("#inp1").append("<img id='exc1'>");
                        $("#exc1").attr("src", "../image/exclamation.png");
                        $("#inp1").append("<div id='exc_text1'>Invalid Carat format. (0.2~5.01)</div>");
                    }
                } else {
                    $("#exc1").remove();
                }
            });
            
            $("#inp5 input").on("change", function() {
            	const regex = /^\d{2}\.\d{1,2}$/;
                depth_chk = regex.test( $("#inp5 input").val()) || /^\d{2}\$/.test($("#inp5 input").val());
                if(!depth_chk) {
                    if(!$("#inp5").has("img").length) {
                        $("#inp5").append("<img id='exc5'>");
                        $("#exc5").attr("src", "../image/exclamation.png");
                        $("#inp5").append("<div id='exc_text5'>Invalid Depth format. (43~79)</div>");
                    }
                } else {
                    $("#exc5").remove();
                }
            });
            
            $("#inp6 input").on("change", function() {
            	const regex = /^\d{2}\.\d{1,2}$/;
                table_chk = regex.test( $("#inp6 input").val()) || /^\d{2}\$/.test($("#inp6 input").val());
                if(!table_chk) {
                    if(!$("#inp6").has("img").length) {
                        $("#inp6").append("<img id='exc6'>");
                        $("#exc6").attr("src", "../image/exclamation.png");
                        $("#inp6").append("<div id='exc_text6'>Invalid Table format. (43~95)</div>");
                    }
                } else {
                    $("#exc6").remove();
                }
            });
            
            $("#inp7 input").on("change", function() {
            	const regex = /^\d{1,2}\.\d{1,2}$/;
                x_chk = regex.test( $("#inp7 input").val()) || /^\d{1,2}\$/.test($("#inp7 input").val());
                if(!x_chk) {
                    if(!$("#inp7").has("img").length) {
                        $("#inp7").append("<img id='exc7'>");
                        $("#exc7").attr("src", "../image/exclamation.png");
                        $("#inp7").append("<div id='exc_text7'>Invalid x format. (0~10.74)</div>");
                    }
                } else {
                    $("#exc7").remove();
                }
            });
            
            $("#inp8 input").on("change", function() {
            	const regex = /^\d{1,2}\.\d{1,2}$/;
                y_chk = regex.test( $("#inp8 input").val()) || /^\d{1,2}\$/.test($("#inp8 input").val());
                if(!y_chk) {
                    if(!$("#inp8").has("img").length) {
                        $("#inp8").append("<img id='exc8'>");
                        $("#exc8").attr("src", "../image/exclamation.png");
                        $("#inp8").append("<div id='exc_text8'>Invalid y format. (0~58.9)</div>");
                    }
                } else {
                    $("#exc8").remove();
                }
            });
            
            $("#inp9 input").on("change", function() {
            	const regex = /^\d{1,2}\.\d{1,2}$/;
                z_chk = regex.test( $("#inp9 input").val()) || /^\d{1,2}\$/.test($("#inp9 input").val());
                if(!z_chk) {
                    if(!$("#inp9").has("img").length) {
                        $("#inp9").append("<img id='exc9'>");
                        $("#exc9").attr("src", "../image/exclamation.png");
                        $("#inp9").append("<div id='exc_text9'>Invalid z format. (0~31.8)</div>");
                    }
                } else {
                    $("#exc9").remove();
                }
            });
            
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

	<p id="top3">Register</p>
    <div class="field">
        <form action="register.do" method="post">

            <div id="inp2" class="selectBox">
            	<input name="cut" id="cut" required>
                <select class="select" >
                	<option disabled selected>Select Cut</option>
                	<option>Fair</option>
                	<option>Good</option>
                	<option>Very Good</option>
                	<option>Premium</option>
                	<option>Ideal</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
            </div>
            
            <div id="inp3" class="selectBox">
            	<input name="color" id="color" required>
                <select class="select" >
                	<option disabled selected>Select Color</option>
                	<option>J</option>
                	<option>I</option>
                	<option>H</option>
                	<option>G</option>
                	<option>F</option>
                	<option>E</option>
                	<option>D</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
            </div>
            
            <div id="inp4" class="selectBox">
            	<input name="clarity" id="clarity" required>
                <select class="select" >
                	<option disabled selected>Select Clarity</option>
                	<option>I1</option>
                	<option>SI2</option>
                	<option>SI1</option>
                	<option>VS2</option>
                	<option>VS1</option>
                	<option>VVS2</option>
                	<option>VVS1</option>
                	<option>IF</option>
                </select>
                <span class="icoArrow"><img src="https://freepikpsd.com/media/2019/10/down-arrow-icon-png-7-Transparent-Images.png" alt=""></span>
            </div>
            
            <div id="inp1" class="inp">
                <input name="carat" id="carat" required>
                <label for="carat"><span>Carat</span></label>
            </div>
            
            <div id="inp5" class="inp">
                <input name="depth" id="depth" required>
                <label for="depth"><span>Depth</span></label>
            </div>
            
            <div id="inp6" class="inp">
                <input name="table" id="table" required>
                <label for="table"><span>Table</span></label>
            </div>
            
            <div id="inp7" class="inp">
                <input name="x" id="x" required>
                <label for="x"><span>X</span></label>
            </div>
            
            <div id="inp8" class="inp">
                <input name="y" id="y" required>
                <label for="y"><span>Y</span></label>
            </div>
            
            <div id="inp9" class="inp">
                <input name="z" id="z" required>
                <label for="z"><span>Z</span></label>
            </div>
            
            <input id="btn" type="submit" value="Evaluate">
        </form>
	    <div id="modal-wrap">
	    	Diamond Broker - Evaluated
	    	<div id="frame">
	    		<div id="close">        
	    			<div>CLOSE</div>    
	    		</div>
	    	</div>
	    	<hr>
	    	<div id="inner">
	    		<br>
	    		<div id="eval" ></div><br>
	    		Do you want to register?<br>
	    		<button id="reg" >Register</button>
	    	</div>
	    </div>
    </div>
    <hr>
</body>
</html>