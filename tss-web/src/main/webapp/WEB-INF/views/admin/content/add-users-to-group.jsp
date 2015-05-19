<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:48
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row row-fix">

    <div class="col-md-offset-1 col-md-10">
        <div class="col-md-10">
            <div class="text-center">
                <h1>Add/Remove users in group ${param.groupname}</h1>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-1">
                        <a href="/admin?menu=groups&action=view" class="btn btn-default"> Back <i class="fa fa-users"></i></a>
                    </div>
                    <div class="col-md-offset-7 col-md-4">
                        <form action="/admin" method="get">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search by email" name="email" value="${param.email}" maxlength="40">
                                <input type="hidden" name="action" value="search-users">
                                <input type="hidden" name="menu" value="groups">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                </span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <table class="table table-striped table-bordered">
                <thead class="tablehead">
                <td>Username</td>
                <td>Email</td>
                <td>Roles</td>
                <td>Groups</td>
                <td></td>
                <td></td>
                </thead>

                <tbody>
                    <c:forEach var = "user" items = "${requestScope.users}">
                        <tr>
                            <td>${user.username}</td>
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
                                <form action="/admin" method="post">
                                    <input type="hidden" name="menu" value="groups">
                                    <input type="hidden" name="action" value="add-to-group">
                                    <input type="hidden" name="groupid" value="${param.groupid}">
                                    <input type="hidden" name="userid" value="${user.id}">
                                    <input type="hidden" name="groupname" value="${param.groupname}">
                                    <input type="hidden" name="page" value="${param.page}">
                                    <button type="submit" class="btn btn-default">Add to group <i class="fa fa-users"></i></button>
                                </form>
                            </td>
                            <td class="col-md-1">
                                <form action="/admin" method="post">
                                    <input type="hidden" name="menu" value="groups">
                                    <input type="hidden" name="action" value="remove-from-group">
                                    <input type="hidden" name="groupid" value="${param.groupid}">
                                    <input type="hidden" name="userid" value="${user.id}">
                                    <input type="hidden" name="groupname" value="${param.groupname}">
                                    <input type="hidden" name="page" value="${param.page}">
                                    <button type="submit" class="btn btn-default btn-danger">Remove from group <i class="fa fa-users"></i></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <%@ include file="../partials/pagination.jspf" %>
        </div>
        <div class="col-md-1"></div>
    </div>
</div>