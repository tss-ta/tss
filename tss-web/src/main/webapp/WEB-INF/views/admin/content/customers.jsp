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
                <h1>Customers Panel</h1>
            </div>
            
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-1">
                        <a href="/admin" class="btn btn-default">Dashboard</a>
                    </div>
                    <div class="col-md-offset-1">
                        <form action = "/admin" >
                                <select class="bootstrap-select col-md-offset-1" name ="role" onchange="this.form.submit()" >
                                    <option value="CUSTOMER" ${'CUSTOMER' == param.role ? 'selected="selected"' : ''}> CUSTOMER </option>
                                    <option value="ADMIN" ${'ADMIN' eq param.role ? 'selected="selected"' : ''}> ADMIN </option>
                                    <option value="BANNED" ${'BANNED' == param.role ? 'selected="selected"' : ''}> BANNED </option>
                                </select>
                            <input type="hidden" name="page" value="1">
                            <input type="hidden" name="action" value="view">
                            <input type="hidden" name="menu" value="users">
                        </form>
                    </div>
                    <div class="col-md-offset-2 col-md-4">
                        <form action="/admin" method="get">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search by email" name="email" value="${param.email}">
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

            <table class="table table-striped table-bordered">
                <thead class="tablehead">
                <td>Username</td>
                <td>Email</td>
                <td>Roles</td>
                <td>Groups</td>
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
                                <a href="/admin?menu=users&action=add-role&email=${user.email}&id=${user.id}" class="btn btn-default">Add role</a>
                            </td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <%@ include file="../partials/pagination.jspf" %>
        </div>
        <div class="col-md-1"></div>
    </div>