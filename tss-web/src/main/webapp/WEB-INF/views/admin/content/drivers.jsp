<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Drivers Panel</h1>

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

            <c:forEach items="${driverList}" var="driver">
                <tr>
                    <td>${driver.getUsername()}</td>
                    <td>${driver.getCategory().getCatStr()}</td>
                    <td>${driver.isAvailable()}</td>
                    <td>${driver.isMale()}</td>
                    <td>${driver.isSmokes()}</td>
                    <td>${driver.getCar()}</td>
                    <td><a href="/admin/driver?action=editdriver&driverid=${driver.getId()}">edit</a></td>
                    <td><a href="/admin/driver?action=deletedriver&driverid=${driver.getId()}">X</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>