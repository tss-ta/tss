<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Tariffs Panel</h1>
        <table class="table table-striped table-bordered">
            <thead class="tablehead">
            <td>service</td>
            <td>tariff type</td>
            <td>car type</td>
            <td>day of week</td>
            <td>from time</td>
            <td>to time</td>
            <td>price</td>
            <td>active from date</td>
            <td>active to date</td>
            </thead>

            <tbody>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
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
    </div>
    <div class="col-md-1"></div>
</div>