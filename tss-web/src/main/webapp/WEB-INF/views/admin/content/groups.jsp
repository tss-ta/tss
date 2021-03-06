<%--
  author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Groups Panel</h1>
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin?menu=groups&action=add-group" class="btn btn-default">Add <i class="fa fa-users"></i></a>
                </div>
                <div class="col-md-offset-7 col-md-4">
                    <form action="/admin" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name="groupname" placeholder="Search by group name" value="${param.groupname}" maxlength="100">
                            <input type="hidden" name="action" value="search">
                            <input type="hidden" name="menu" value="groups">
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

<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">

        <table class="table table-bordered">
            <thead>
            <td>Group</td>
            <td>Roles</td>
            <td></td>
            <td></td>
            <td></td>
            </thead>

            <tbody>
                <c:forEach var = "group" items = "${requestScope.groups}">
                    <tr>
                        <td>${group.name}</td>
                        <td>
                            <c:forEach var = "role" items = "${group.roles}">${role}; </c:forEach>
                            </td>
                            <td class="col-md-1">
                                <a href="/admin?menu=groups&action=edit-group&id=${group.id}&name=${group.name}" class="btn btn-default">
                                Edit <i class="fa fa-users"></i>
                            </a>
                        </td>
                        <td class="col-md-1">
                            <form action="/admin" method="post">
                                <input type="hidden" name="action" value="delete-group">
                                <input type="hidden" name="menu" value="groups">
                                <input type="hidden" name="id" value="${group.id}">
                                <button type="submit" class="btn btn-default"> Remove <i class="fa fa-users"></i></button>
                            </form>
                        </td>
                        <td class="col-md-1">
                            <a href="/admin?menu=groups&action=view-users&groupid=${group.id}&groupname=${group.name}" class="btn btn-default">
                                Add/Remove users in group <i class="fa fa-users"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>
