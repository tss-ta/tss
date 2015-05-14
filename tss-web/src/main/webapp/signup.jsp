<%-- 
    Document   : registration
    Created on : 21.04.2015, 22:02:01
    Author     : Виктор
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <link href="/resources/css/font-awesome.min.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <script src="/resources/js/jquery-1.11.2.min.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>

        <%@ include file="WEB-INF/views/partials/non-dasboard-header.jspf" %>

        <div class="container">

            <form class="form-sign" action="RegistrationServlet" method="post" autocomplete="off">
                <h2 class="form-sign-heading">Registrate please</h2>
                <%--<h5 class="form-sign-error-msg">${requestScope.errorMessage}</h5>--%>
                <h5 class="form-sign-heading">${requestScope.errorMessage}</h5>
                <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="User name" maxlength="40" required autofocus><br/>
                <input type="email" id="inputEmail" name="email" class="form-control" placeholder="Email" maxlength="40" required><br/>
                <input type="password" id="password" name="password" class="form-control" placeholder="Password" maxlength="60" required><br/>
                <input type="password" id="confirPassword" name="confirPassword" onkeyup="checkPass(); return false;" class="form-control" placeholder="Confirm Password" maxlength="60" required><br/>
                <%--<div class="row row-fix">--%>
                    <%--<div class="col-md-2">--%>
                        <%--<button id="driver_token_id" type="button"><i class="fa fa-plus-square-o fa-2x"></i></button>--%>
                    <%--</div>--%>
                    <%--<div id="token_name" class="col-md-8">--%>
                        <%--<h4 class="form-sign-heading">For Driver Register</h4>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div id="div_for_token"></div>--%>

                <c:if test="${param.token != null}">
                    diverrrrr
                </c:if>


                <span id="confirmMessage" class="confirmMessage"></span>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Registrate</button>
            </form>
        </div> <!-- /container -->
        <%@ include file="WEB-INF/views/partials/footer.jspf" %>

    <%--<script>--%>
        <%--$('#driver_token_id').click(function() {--%>
            <%--$('#driver_token_id').remove();--%>
            <%--$('#token_name').remove();--%>
            <%--$('#inputEmail').remove();--%>
            <%--var div_for_token = $('#div_for_token');--%>
            <%--div_for_token.append('<input class="form-control" type="number" name="token" placeholder="Input your token here">');--%>
            <%--div_for_token.append('<br/>');--%>
            <%--var select = '<label for="inputCategory"><h4 class="form-sign-heading">Choose category  </h4></label>' +--%>
                            <%--'<select id="inputCategory" name="category">' +--%>
                                <%--'<option value="B">B</option>' +--%>
                                <%--'<option value="C">C</option>' +--%>
                                <%--'<option value="D">D</option>' +--%>
                            <%--'</select>';--%>
            <%--div_for_token.append(select);--%>
            <%--var checkbox = '<div class="checkbox">' +--%>
                                <%--'<label class="checkbox"><input id="availableChkBox" type="checkbox" name="available" >available</label>' +--%>
                                <%--'<label class="checkbox"><input id="isMaleChkBox" type="checkbox" name="ismale">is male</label>' +--%>
                                <%--'<label class="checkbox"><input id="smokesChkBox" type="checkbox" name="smokes">smokes</label>' +--%>
                           <%--'</div>';--%>
            <%--div_for_token.append(checkbox);--%>
        <%--});--%>
    <%--</script>--%>
    </body>
</html>
