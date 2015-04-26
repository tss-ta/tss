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
    <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
    <title>Taxi Service System Admin Dashboard</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/admin.css" rel="stylesheet">
    <link href="/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<%@ include file="partials/admin-header.jspf"%>
<%--<div id="sidebar-wrapper">--%>
    <%--<ul class="sidebar-nav">--%>
        <%--<li><a href="#">Dashboard</a></li>--%>
        <%--<li><a href="#">Cars</a></li>--%>
        <%--<li><a href="#">Drivers</a></li>--%>
        <%--<li><a href="#">Customers</a></li>--%>
        <%--<li><a href="#">Groups</a></li>--%>
        <%--<li><a href="#">Tariffs</a></li>--%>
        <%--<li><a href="#">Reports</a></li>--%>
    <%--</ul>--%>
<%--</div>--%>

<%--<div class="container">--%>

    <%--<div class="row">--%>
        <%--<div class="col-md-2">--%>
            <%--<ul class="nav nav-pills nav-stacked">--%>
                <%--<li role="presentation" class="active"><a href="/admin"><i class="fa fa-tachometer"></i> Dashboard</a></li>--%>
                <%--<li role="presentation"><a href="/admin/car"><i class="fa fa-taxi"></i> Cars</a></li>--%>
                <%--<li role="presentation"><a href="/admin/driver"><i class="fa fa-child"></i> Drivers</a></li>--%>
                <%--<li role="presentation"><a href="/admin/customer"><i class="fa fa-user"></i> Customers</a></li>--%>
                <%--<li role="presentation"><a href="/admin/group"><i class="fa fa-users"></i> Groups</a></li>--%>
                <%--<li role="presentation"><a href="/admin/tariff"><i class="fa fa-usd"></i> Tariffs</a></li>--%>
                <%--<li role="presentation"><a href="/admin/report"><i class="fa fa-bar-chart"></i> Reports</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>

        <%--<div class="col-md-10">--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="col-md-4">--%>
                <%--<div class="panel panel-default">--%>
                    <%--<div class="panel-heading">--%>
                        <%--<h3 class="panel-title">Panel title</h3>--%>
                    <%--</div>--%>
                    <%--<div class="panel-body">--%>
                        <%--Panel content--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

<%--</div>--%>
<!-- /.container -->
<div class="row-offcanvas row-offcanvas-left">
    <div id="sidebar" class="sidebar-offcanvas">
        <div class="col-md-12">
            <ul class="left-nav">
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
                <li><a href="#">Section</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="col-md-12">
            <p class="visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas"><i class="glyphicon glyphicon-chevron-left"></i></button>
            </p>
            <h2>Fixed + Fluid Bootstrap Template with Off-canvas Sidebar</h2>
            <div class="row">
                <div class="col-md-12"><div class="well"><p>Shrink the browser width to make the sidebar collapse off canvase.</p></div></div>
            </div>
            <div class="row">
                <div class="col-md-4"><div class="well"><p>4 cols</p></div></div>
                <div class="col-md-4"><div class="well"><p>4 cols</p></div></div>
                <div class="col-md-4"><div class="well"><p>4 cols</p></div></div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-sm-6"><div class="well"><p>6 cols, 6 small cols</p></div></div>
                <div class="col-lg-6 col-sm-6"><div class="well"><p>6 cols, 6 small cols</p></div></div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-sm-6"><div class="well">4 cols, 6 small cols</div></div>
                <div class="col-lg-4 col-sm-6"><div class="well">4 cols, 6 small cols</div></div>
                <div class="col-lg-4 col-sm-12"><div class="well">4 cols, 12 small cols</div></div>
            </div>
        </div>
    </div>
</div><!--/row-offcanvas -->


<%@ include file="/WEB-INF/views/partials/footer.jspf"%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="resources/js/jquery-1.11.2.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="resources/js/bootstrap.min.js"></script>
</body>
</html>
