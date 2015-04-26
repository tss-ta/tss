<%-- 
    Document   : addgroup
    Created on : 22.04.2015, 21:02:57
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="add car">
        <meta name="author" content="maks">
        <link rel="icon" href="/resources/img/favicon.ico">

        <title>TSS add group</title>

        <!-- Bootstrap core CSS -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/css/font-awesome.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <%--<link href="/resources/css/sign-in.css" rel="stylesheet">--%>

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>

    <%@ include file="partials/admin-header.jspf" %>

        <div class="container">

            <%@ include file="partials/menu.jspf" %>

            <form action="/admin/group" method="post">


                <h2 class="form-signin-heading">Add group</h2>
                <p class="form-signin-heading">group name</p>
                <input type="text" name="name">
                <p>category</p>

                <div class="checkbox">
                    <label class="checkbox"><input type="checkbox" name="admin" >admin</label>
                    <label class="checkbox"><input type="checkbox" name="driver">driver</label>
                    <label class="checkbox"><input type="checkbox" name="customer">customer</label>
                    <label class="checkbox"><input type="checkbox" name="banned">banned</label>
                </div>
                <input type="hidden" name="action" value="newgroup">





                <button class="btn btn-lg btn-primary" type="submit">add car</button>
            </form>


        </div> <!-- /container -->

        <%@ include file="/WEB-INF/views/partials/footer.jspf" %>

    </body>
</html>