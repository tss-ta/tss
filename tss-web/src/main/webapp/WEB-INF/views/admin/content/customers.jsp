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
                    <div class="col-md-offset-7 col-md-4">
                        <form action="/admin/customer" method="get">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search by email" name="email" value="${param.email}">
                                <input type="hidden" name="action" value="search-users">
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
                    <c:forEach var = "customer" items = "${requestScope.customers}">
                        <tr>
                            <td>${customer.username}</td>
                            <td>${customer.email}</td>
                            <td>
                                <c:forEach var = "role" items = "${customer.roles}">
                                    ${role}; 
                                </c:forEach> 
                            </td>
                            <td>
                                <c:forEach var = "group" items = "${customer.groups}">
                                    ${group}; 
                                </c:forEach> 
                            </td>
                            <td class="col-md-1">
                                <a href="/admin/customer?action=add-role&email=${customer.email}&id=${customer.id}" class="btn btn-default">Add role</a>
                            </td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <%@ include file="../partials/pagination.jspf" %>
        </div>
        <div class="col-md-1"></div>
    </div>