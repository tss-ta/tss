<%-- 
    Document   : registration
    Created on : 21.04.2015, 22:02:01
    Author     : Виктор
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/resources/img/favicon.ico">

        <title>Taxi Service System Sign In</title>

        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"
              media="all" />
        <link href='http://fonts.googleapis.com/css?family=Raleway'
              rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/responsiveslides.css">
        <script src="/resources/js/responsiveslides.min.js"></script>

        <!-- Custom javascript for password confirmation -->
        <script type="text/javascript" src="resources/js/confirmPassword.js"></script>

        <!-- Bootstrap core CSS -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="/resources/css/sign.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

        <!-----start-header----->
        <div class="header">
            <!---start-wrap---->
            <div class="wrap">
                <!---start-top-header---->
                <div class="top-header">
                    <div class="clear"></div>
                </div>
                <!---End-top-header---->
                <!----start-main-header----->
                <div class="main-header">
                    <div class="logo">
                        <a href="index.html"><img src="/resources/images/logo1.png"
                                                  title="logo" /></a>
                    </div>
                    <div class="top-nav">
                        <ul>
                            <li><a href="/signout">Home</a></li>
                            <li><a href="/signin.jsp">Sign In</a></li>
                            <li class="active"><a href="/signup.jsp">Sign Up</a></li>
                            <li><a href="/guest">Guest</a></li>
                            <li><a href="/signout">Sign Out</a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                    <div class="clear"></div>
                </div>
                <!----End-main-header----->
            </div>
        </div>
        <div class="container">

            <form class="form-sign" action="RegistrationServlet" method="post" autocomplete="off">
                <h2 class="form-sign-heading">Registrate please</h2>
                <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="User name" maxlength="40" required autofocus><br/>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" maxlength="40" required><br/>
                <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" maxlength="60" required><br/>
                <input type="password" id="confirmInputPassword" name="confirmInputPassword" onkeyup="checkPass();
                        return false;" class="form-control" placeholder="Confirm Password" maxlength="60" required><br/>
                <span id="confirmMessage" class="confirmMessage"></span>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Registrate</button>
            </form>
        </div> <!-- /container -->
        <%@ include file="WEB-INF/views/partials/footer.jspf" %>
    </body>
</html>
