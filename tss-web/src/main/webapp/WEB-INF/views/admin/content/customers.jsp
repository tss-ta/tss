<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1>Customers Panel</h1>


        <p>
            <a href="admin?action=adduser&menu=${param.menu}">Add user</a>

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
    </div>
    <div class="col-md-1"></div>
</div>