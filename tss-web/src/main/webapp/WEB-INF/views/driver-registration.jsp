<%--
  Created by IntelliJ IDEA.
  User: maks
  Date: 14.05.15
  Time: 12:13
  To change this template use File | Settings | File Templates.
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

<%@ include file="/WEB-INF/views/partials/non-dasboard-header.jspf" %>

<div class="container">

    <form class="form-sign" action="RegistrationServlet" method="post" autocomplete="off">
        <h2 class="form-sign-heading">Register please</h2>
        <%--<h5 class="form-sign-error-msg">${requestScope.errorMessage}</h5>--%>
        <h5 class="form-sign-heading">${requestScope.errorMessage}</h5>
        <input type="email" readonly id="inputEmail" name="email" class="form-control" value="${requestScope.email}" maxlength="40" required><br/>
        <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="User name" maxlength="40" required autofocus><br/>
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" maxlength="60" required><br/>
        <input type="password" id="confirPassword" name="confirPassword" onkeyup="checkPass(); return false;" class="form-control" placeholder="Confirm Password" maxlength="60" required><br/>



        <label for="inputCategory">
            <h4 class="form-sign-heading">Choose category   </h4>
        </label>
        <select id="inputCategory" name="category">
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
        </select>

        <div class="checkbox">
            <label class="checkbox"><input id="availableChkBox" type="checkbox" name="available" >available</label>
            <label class="checkbox"><input id="isMaleChkBox" type="checkbox" name="ismale">is male</label>
            <label class="checkbox"><input id="smokesChkBox" type="checkbox" name="smokes">smokes</label>
        </div>

        <input type="hidden" name="token" value="${param.token}" >
        <span id="confirmMessage" class="confirmMessage"></span>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Registrate</button>
    </form>
</div> <!-- /container -->
<%@ include file="/WEB-INF/views/partials/footer.jspf" %>

</body>
</html>
