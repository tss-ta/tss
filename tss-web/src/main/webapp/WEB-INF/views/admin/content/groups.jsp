<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 00:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>
