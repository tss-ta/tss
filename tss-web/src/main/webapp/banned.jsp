<%--
  User: Kyrylo Berehovyi
  Date: 05/05/2015
  Time: 12:01
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
    <link rel="stylesheet" href="/resources/css/responsiveslides.css">
    <script src="/resources/js/responsiveslides.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/resources/css/sign.css" rel="stylesheet">
    <style>
        .banned-title h1 {
            color: #989898;
        }
    </style>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<%@ include file="WEB-INF/views/partials/non-dasboard-header.jspf" %>

<div class="container">

    <div class="row">
        <div class="col-md-offset-1 col-md-10 text-center banned-title">
            <h1>
                <span class="fa-stack fa-lg">
                    <i class="fa fa-user fa-stack-1x"></i>
                    <i class="fa fa-ban fa-stack-2x text-danger"></i>
                </span>
                You are banned!
            </h1>
        </div>
    </div>

</div> <!-- /container -->

<%--<%@ include file="WEB-INF/views/partials/footer.jspf" %>--%>

<script src="/resources/js/jquery-1.11.2.min.js"></script>
</body>
</html>
