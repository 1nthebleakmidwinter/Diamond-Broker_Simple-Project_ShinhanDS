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
    <link rel="stylesheet" href="../css/signup-cpt.css">
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js' integrity='sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==' crossorigin='anonymous'></script>
    <script>
        function init() {
            var btn = $("#btn");
            btn.addClass("btn_ani");

            btn.on("click", function(e) {
                e.preventDefault();
                $(".title").addClass("title_ani_out");
                btn.addClass("btn_ani_out");
                setTimeout(function() {
                    $("form").submit();
                }, 900)
            });
        }
        $(init);
    </script>
</head>
<body>
	<h1 class="title">Welcome, <%=request.getParameter("newId")%></h1>
    <form action="signin.html">
        <input id="btn" type="submit" value="Sign In →">
    </form>
</body>
</html>