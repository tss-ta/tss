<%-- 
    Document   : addcar
    Created on : 22.04.2015, 21:02:57
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="add car">
    <meta name="author" content="maks">
    <link rel="icon" href="/resources/img/favicon.ico">
    <title>TSS add car</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resources/css/admin.css" rel="stylesheet">
    <link href="/resources/img/favicon.ico" rel="shortcut icon">
</head>
<body>
    <div id="wrapper">

        <div id="sidebar-wrapper">
            <%@ include file="../partials/sidebar.jspf" %>
        </div>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <%@ include file="../partials/admin-header.jspf" %>
            </div>

            <form action="/admin/car" method="post">
                <h2 class="form-signin-heading">Add car</h2>
                <p class="form-signin-heading">license plate</p>
                <input type="text" name="plate">
                <p>category</p>
                <select name="category">
                    <option value="1">business</option>
                    <option value="2">econom</option>
                    <option value="3">van</option>
                    <option value="4">cargo</option>
                </select>
                <div class="checkbox">
                    <label class="checkbox"><input type="checkbox" name="avaliable" > availible</label>
                    <label class="checkbox"><input type="checkbox" name="animalable">animaliable</label>
                    <label class="checkbox"><input type="checkbox" name="wi-fi">wi-fi</label>
                    <label class="checkbox"><input type="checkbox" name="conditioner"> conditioner</label>
                </div>
                <input type="hidden" name="action" value="newcar">
                <button class="btn btn-lg btn-primary" type="submit">add car</button>
            </form>

        </div>

    </div>

</body>
</html>