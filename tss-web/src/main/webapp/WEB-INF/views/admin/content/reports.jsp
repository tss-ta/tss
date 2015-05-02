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
        <h1>Reports Panel</h1>

        <table class="table table-striped table-bordered">
            <thead class="tablehead">
            <td>name</td>
<!--            <td>time</td>-->
            </thead>

            <tbody>
            <tr>
                <td><a href="/admin/reports">Most profitable taxi service</a></td>
                <td><a href="/admin/reports">Most popular car and driver category</a></td>
                <td><a href="/admin/reports">Most popular additional car options for each customer user</a></td>
                <td><a href="/admin/reports">Most popular additional car options overall</a></td>
                <td><a href="/admin/reports">New orders per period</a></td>
                <td><a href="/admin/reports">Service profitability by month</a></td>
<!--                <td>20-04-2015 13:14</td>-->

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