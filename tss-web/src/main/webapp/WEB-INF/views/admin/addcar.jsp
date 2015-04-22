<%-- 
    Document   : addcar
    Created on : 22.04.2015, 21:02:57
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>TSS add car</title>

        <link href="/resources/css/style.css" rel="stylesheet" type="text/css"
              media="all" />
                <link href='http://fonts.googleapis.com/css?family=Raleway'
                      rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/responsiveslides.css">


        <!-- Bootstrap core CSS -->
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="/resources/css/sign-in.css" rel="stylesheet">

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
                            <li class="active"><a href="/signin.jsp">Sign In</a></li>
                            <li><a href="/signup.jsp">Sign Up</a></li>
                            <li><a href="#">Guest</a></li>
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

            <form action="/admin" method="post">


                <h2 class="form-signin-heading">Add car</h2>
                <p class="form-signin-heading">license plate</p>
                <input type="text" name="plate">
                <p>category</p>
                <select name="category">
                    <option value="1">business</option>
                    <option value="2">econom</option>
                    <option value="3">van</option>
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


        </div> <!-- /container -->

        <%@ include file="/WEB-INF/views/partials/footer.jspf" %>

    </body>
</html>