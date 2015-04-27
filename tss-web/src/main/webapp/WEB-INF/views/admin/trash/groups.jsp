<%--
  User: Kyrylo Berehovyi
  Date: 19/04/2015
  Time: 21:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Taxi Service System</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/custom.css" rel="stylesheet">
    <link href="/resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

</head>
<body>

        <%--<%@ include file="partials/admin-header.jspf" %>--%>

        <%--<div class="container-fluid">--%>

            <%--<div class="row">--%>
                <%--<div class="col-md-1"></div>--%>
                <%--<div class="col-md-10">--%>
                    <%--<h1>${title}</h1>--%>

                    <%--<%@ include file="partials/menu.jspf" %>--%>

                    <%--<p>--%>
                        <%--<a href="/admin/group?action=addgroup">Add group</a> --%>

                    <%--</p>--%>
                    <%--<table class="table table-striped table-bordered">--%>
                        <%--<thead class="tablehead">--%>
                        <%--<td>group</td>--%>
                        <%--<td>roles</td>--%>
                        <%--</thead>--%>

                        <%--<tbody>--%>
                        <%--<c:forEach var = "group" items = "${requestScope.groups}">--%>
                            <%--<tr>--%>
                                <%--<td>${group.name}</td>--%>
                                <%--<td><c:forEach var = "role" items = "${group.roles}">${role};</c:forEach></td>--%>
                                <%--<td><a href="admin">edit</a></td>--%>
                            <%--</tr>--%>
                        <%--</c:forEach> --%>
                        <%--</tbody>--%>
                    <%--</table>--%>
                    <%--<%@ include file="partials/pagination.jspf" %>--%>
                <%--</div>--%>
                <%--<div class="col-md-1"></div>--%>
            <%--</div><!-- /.row -->--%>

        <%--</div><!-- /.container -->--%>

<div id="wrapper">

    <div id="sidebar-wrapper">
        <%@ include file="partials/sidebar.jspf" %>
    </div>

    <div id="page-content-wrapper">
        <div class="container-fluid">
            <%@ include file="partials/admin-header.jspf" %>

            <div class="row row-fix">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <h1>${title}</h1>

                    <p>
                        <a href="/admin/group?action=addgroup">Add group</a>
                    </p>

                    <table class="table table-striped table-bordered">
                        <thead class="tablehead">
                            <td>group</td>
                            <td>roles</td>
                        </thead>

                        <tbody>
                            <c:forEach var = "group" items = "${requestScope.groups}">
                                <tr>
                                    <td>${group.name}</td>
                                    <td><c:forEach var = "role" items = "${group.roles}">${role};</c:forEach></td>
                                    <td><a href="admin">edit</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <%@ include file="partials/pagination.jspf" %>
                </div>
                <div class="col-md-1"></div>
            </div><!-- /.row -->
        </div>
    </div>

</div>


<script src="/resources/js/jquery-1.11.2.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>

</body>
</html>
