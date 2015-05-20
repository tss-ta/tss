<%--
  author: maks
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

                <c:if test="${not forAssignee}">
                    <div class="col-md-offset-1 col-md-2 col-sm-offset-1 col-sm-2 col-xs-offset-4 col-xs-4">
                        <form action = "/admin" class="text-center">

                            <select class="bootstrap-select" name ="role" onchange="this.form.submit()" style="margin-top: 5px">
                                <c:forEach var = "role" items = "${requestScope.rolesEnum}">
                                    <option ${role == param.role ? 'selected="selected"' : ''}> ${role} </option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="page" value="1">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="menu" value="users">
                        </form>
                    </div>
                </c:if>


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

                                <c:if test="${forAssignee}">
                                    <form action="/admin" class="form-horizontal">
                                        <input type="hidden" name="id" value="${user.id}">
                                        <input type="hidden" name="menu" value="drivers">
                                        <input type="hidden" name="action" value="assignFields">
                                        <button type="submit" class="btn btn-default">Make Driver</button>
                                    </form>
                                    <%--<a href="/admin?menu=users&action=add-roles&id=${user.id}&role=DRIVER" class="btn btn-default">--%>
                                        <%--Make a Driver--%>
                                    <%--</a>--%>
                                </c:if>
                                <c:if test="${not forAssignee}">
                                    <a href="/admin?menu=users&action=add-role&email=${user.email}&id=${user.id}&role=${param.role}" class="btn btn-default">
                                        Change roles
                                    </a>
                                </c:if>
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
