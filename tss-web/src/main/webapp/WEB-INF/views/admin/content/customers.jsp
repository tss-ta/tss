<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>User Panel</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body">

                <div class="col-md-offset-0 col-md-1 col-sm-offset-0 col-sm-2 col-xs-4">
                    <a href="/admin?menu=dashboard&action=view" class="btn btn-default">Dashboard</a>
                </div>

                <div class="col-md-offset-1 col-md-2 col-sm-offset-1 col-sm-2 col-xs-offset-4 col-xs-4">
                    <form action = "/admin" class="text-center">

                        <select class="bootstrap-select col-md-offset-1" name ="role" onchange="this.form.submit()" style="margin-left:auto; margin-right:auto;margin-top: 5px;margin-bottom: 0">
                            <c:forEach var = "role" items = "${requestScope.rolesEnum}">
                                <option ${role == param.role ? 'selected="selected"' : ''}> ${role} </option>
                            </c:forEach>
                            <%--<option value="CUSTOMER" ${'CUSTOMER' == param.role ? 'selected="selected"' : ''}> CUSTOMER </option>--%>
                            <%--<option value="ADMIN" ${'ADMIN' eq param.role ? 'selected="selected"' : ''}> ADMIN </option>--%>
                            <%--<option value="BANNED" ${'BANNED' == param.role ? 'selected="selected"' : ''}> BANNED </option>--%>
                        </select>
                        <input type="hidden" name="page" value="1">
                        <input type="hidden" name="action" value="view">
                        <input type="hidden" name="menu" value="users">
                    </form>
                </div>

                <div class="col-md-offset-4 col-md-4 col-sm-offset-1 col-sm-6 col-xs-12">
                    <form action="/admin" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search by email" name="email" value="${param.email}" maxlength="40">
                            <input type="hidden" name="action" value="search">
                            <input type="hidden" name="menu" value="users">
                            <input type="hidden" name="role" value="${param.role}"/>
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>

<c:if test="${not empty users}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <table class="table table-hover table-bordered">
                <%--<thead class="tablehead text-center">--%>
                <%--<td class="col-md-3 col-sm-5 col-xs-6">License Plate</td>--%>
                <%--<td class="col-md-2 hidden-sm hidden-xs">Type</td>--%>
                <%--<td class="col-md-1 hidden-sm hidden-xs">WI-FI</td>--%>
                <%--<td class="col-md-2 hidden-sm hidden-xs">Conditioner</td>--%>
                <%--<td class="col-md-1 hidden-sm hidden-xs">Animal</td>--%>
                <%--<td class="col-md-1 col-sm-2 hidden-xs">Active</td>--%>
                <%--<td class="col-md-2 col-sm-5 col-xs-6">Settings</td>--%>
                <%--</thead>--%>

                <thead class="tablehead text-center">
                    <td class="col-md-1 hidden-sm">Username</td>
                    <td class="col-md-2 col-sm-3">Email</td>
                    <td class="col-md-3 col-sm-3">Roles</td>
                    <td class="col-md-3 col-sm-3">Groups</td>
                    <td class="col-md-1 col-sm-3"></td>
                </thead>

                <tbody>
                    <c:forEach var = "user" items = "${requestScope.users}">
                        <tr class="text-center">
                            <td class="hidden-sm">${user.username}</td>
                            <td>${user.email}</td>
                            <td>
                                <c:forEach var = "role" items = "${user.roles}">
                                    ${role};
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var = "group" items = "${user.groups}">
                                    ${group};
                                </c:forEach>
                            </td>
                            <td class="col-md-1">
                                <a href="/admin?menu=users&action=add-role&email=${user.email}&id=${user.id}" class="btn btn-default">Add role</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>

            <%@ include file="../partials/pagination.jspf"%>

        </div>
    </div>
</c:if>
<c:if test="${empty users}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No results found</h3>
            </div>
        </div>
    </div>
</c:if>