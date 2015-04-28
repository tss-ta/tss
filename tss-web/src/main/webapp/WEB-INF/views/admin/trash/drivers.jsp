<%--
  User: Kyrylo Berehovyi
  Date: 19/04/2015
  Time: 21:27
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">-->
        <title>Taxi Service System</title>

        <!-- Bootstrap -->
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

        <%@ include file="partials/admin-header.jspf" %>

        <div class="container-fluid">

            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10">
                    <h1>${title}</h1>

                    <%@ include file="partials/menu.jspf" %>

                    <p>
                        <a href="/admin/driver?action=adddriver">Add driver</a>

                    </p>
                    <table class="table table-striped table-bordered">
                        <thead class="tablehead">
                        <%--<td>surname</td>--%>
                        <%--<td>first name</td>--%>
                        <td>driver name</td>
                        <td>category</td>
                        <td>available</td>
                        <%--<td>hired date</td>--%>
                        <%--<td>fired date</td>--%>
                        <%--<td>salary</td>--%>
                        <td>is male</td>
                        <td>smokes</td>
                        <td>car</td>
                        <%--<td>car active from date</td>--%>
                        <%--<td>car active to date</td>--%>
                        </thead>
                        <tbody>

                        <c:forEach items="${drivers_page}" var="driver">
                            <c:set var="username" scope="session" value="${driver.getUsername()}"/>
                            <c:set var="email" scope="session" value="${driver.getEmail()}"/>
                            <c:set var="category" scope="session" value="${driver.getCategory().getCatStr()}"/>
                            <c:set var="available" scope="session" value="${driver.isAvailable()}"/>
                            <c:set var="isMale" scope="session" value="${driver.isMale()}"/>
                            <c:set var="smokes" scope="session" value="${driver.isSmokes()}"/>
                            <c:set var="carId" scope="session" value="${driver.getCar().getId()}"/>
                            <tr>
                                <td>${driver.getUsername()}</td>
                                <td>${driver.getCategory()}</td>
                                <td>${driver.isAvailable()}</td>
                                <td>${driver.isMale()}</td>
                                <td>${driver.isSmokes()}</td>
                                <td>${driver.getCar().getId()}</td>
                                <td><a href="admin/driver?action=editdriver&driverid=${driver.getId()}" >edit</a></td>
                            </tr>
                        </c:forEach>

                            <%--                        <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}">
                                                        <tr>
                                                            <td>${array.frequency}</td>
                                                            <td>${array.voltage}</td>
                                                        </tr>
                            </c:forEach> --%>
                        </tbody>
                    </table>
                    <%@ include file="partials/pagination.jspf" %>
                </div>
                <div class="col-md-1"></div>
            </div><!-- /.row -->

        </div><!-- /.container -->

        <%@ include file="../partials/footer.jspf" %>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="/resources/js/jquery-1.11.2.min.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="/resources/js/bootstrap.min.js"></script>
    </body>
</html>
