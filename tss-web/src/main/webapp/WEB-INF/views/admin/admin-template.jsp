<%--
  User: happy
  Date: 25/04/2015
  Time: 17:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Taxi Service System Admin Dashboard</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/admin.css" rel="stylesheet">
    <link href="/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />
</head>
<body>

<div id="wrapper">

    <div id="sidebar-wrapper">
        <%@ include file="partials/sidebar.jspf" %>
    </div>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <%@ include file="partials/admin-header.jspf" %>
        </div>

        <jsp:include page="${pageContent}" />

    </div>

</div>

<script src="resources/js/jquery-1.11.2.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
