<%--
  User: Kyrylo Berehovyi
  Date: 19/04/2015
  Time: 21:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Taxi Service System</title>

        <!-- Bootstrap -->
        <link href="resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/css/custom.css" rel="stylesheet">
        <link href="resources/img/favicon.ico" rel="shortcut icon" type="image/x-icon" />

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <%@ include file="../partials/header.jspf" %>

        <div class="container-fluid">

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <h1>${title}</h1>

                    <nav class="navbar navbar-default">
                        <ul class="nav navbar-nav">
                            <li><a href="admin?menu=cars">Cars</a></li>
                            <li><a href="admin?menu=drivers">Drivers</a></li>
                            <li class="active"><a href="admin?menu=customers">Customers</a></li>
                            <li><a href="admin?menu=groups">Groups</a></li>
                            <li><a href="admin?menu=tariffs">Tariffs</a></li>
                            <li><a href="admin?menu=reports">Reports</a></li>
                        </ul>
                    </nav>
                    <p>
                        <a href="admin?action=adduser">Add user</a> 

                    </p>
                    <table class="table table-striped table-bordered">
                        <thead class="tablehead">
                        <td>username</td>
                        <td>group</td>
                        <td>email</td>
                        </thead>

                        <tbody>
                            <tr>
                                <td>usr0</td>
                                <td>1</td>
                                <td>m@i.ua</td>
                                <td><a href="admin">edit</a></td>
                            </tr>
                            <%--                        <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}">
                                                        <tr>
                                                            <td>${array.frequency}</td>
                                                            <td>${array.voltage}</td>
                                                        </tr>
                            </c:forEach> --%>
                        </tbody>
                    </table>
                    <%@ include file="../partials/pagination.jspf" %>
                    <!--                    <table>
                                            <td class="col-md-3">
                                                <a href="admin?page=1&menu=${param.menu}" > First </a>
                                            </td>
                                            <td class="col-md-3">
                                                <a href="admin?page=${param.page - 1}&menu=${param.menu}" > Previous </a>
                                            </td>
                                            <td> ${param.page} </td>
                                            <td>
                                                <a href="admin?page=${param.page + 1}&menu=${param.menu}"> Next</a>
                                            </td>
                                            <td>
                                                <a href="admin?page=last&menu=${param.menu}"> Last </a>
                                            </td>
                                        </table>-->
                </div>
                <div class="col-md-1"></div>
            </div><!-- /.row -->

        </div><!-- /.container -->

        <%@ include file="../partials/footer.jspf" %>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="resources/js/jquery-1.11.2.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="resources/js/bootstrap.min.js"></script>
    </body>
</html>
