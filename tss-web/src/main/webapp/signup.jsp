<%-- 
    Document   : registration
    Created on : 21.04.2015, 22:02:01
    Author     : Виктор, maks
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/img/favicon.ico">

    <title>Taxi Service System Sign Up</title>

    <link href="/resources/css/style.css" rel="stylesheet" type="text/css"
          media="all"/>
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
    <%--<link href="/resources/css/font-awesome.min.css" rel="stylesheet">--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">


</head>

<body>


<%@ include file="WEB-INF/views/partials/non-dasboard-header.jspf" %>

<div class="container">


    <form class="form-sign form-horizontal" action="RegistrationServlet" method="post" autocomplete="off">
        <h2 class="form-sign-heading">Please Sign Up</h2>
        <h5 class="form-sign-error-msg">${requestScope.errorMessage}</h5>

        <%-- Delete Chrome autocomplete --%>
        <%--<input style="display:none" type="text" name="fakeusernameremembered"/>--%>
        <%--<input style="display:none" type="password" name="fakepasswordremembered"/>--%>
        <div class="control-group">
            <div class="controls">


                <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="User name"
                       minlength="1" maxlength="40" required autofocus
                       data-validation-required-message="Name is required"/>
                <br/>
                <input type="email" data-validation-email-message="Incorrect email address"
                       class="form-control" id="input_email" placeholder="Email" name="email"
                       minlength="5" data-validation-minlength-message="Incorrect email address"
                       maxlength="40"
                       data-validation-maxlength-message="Incorrect email address. It's too long"
                       data-validation-required-message="Email is required"/>
                <br/>
                <div class="control-group">
                <input type="password" id="password" name="password" class="form-control" placeholder="Password"
                       maxlength="60" minlength="1" required data-validation-required-message="Password is required">
                <br/>
                    <%--<p class="help-block form-sign-error-msg"></p>--%>
                </div>
                <div class="control-group">
                <input type="password" id="confirPassword" name="confirPassword"
                       <%--onkeyup="checkPass(); return false;"--%>
                       class="form-control" placeholder="Confirm Password"
                       maxlength="60" minlength="1"
                       required data-validation-match-match="password"
                       data-validation-match-message="Password do not match"
                       data-validation-required-message="Password with confirmation is required">
                <%--<br/>--%>
                    <p class="help-block form-sign-error-msg"></p>
                    </div>
                <%--<span id="confirmMessage" class="confirmMessage"></span>--%>
                <p class="help-block form-sign-error-msg"></p>
            </div>
            <button class="btn btn-lg btn-primary btn-block register" type="submit" name="register" id="register">Sign Up</button>

        </div>
    </form>


</div>
<!-- /container -->
<%@ include file="WEB-INF/views/partials/footer.jspf" %>

<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/jquery-1.11.2.min.js"></script>
<script src="/resources/js/jqBootstrapValidation.js"></script>
<script>
    $(function () {
        $("input").not("[type=submit]").jqBootstrapValidation();
        $(".form-sign-error-msg").css("visibility", "visible");
    });
</script>
</body>
</html>
