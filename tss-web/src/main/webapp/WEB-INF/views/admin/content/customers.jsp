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
                                <a href="/admin/customer?action=add-to-group&email=${customer.email}" class="btn btn-default">Add to group<i class="fa fa-users"></i></a>
                            </td>
                            <td class="col-md-1">
                                <a href="/admin/customer?action=add-role&email=${customer.email}" class="btn btn-default">Add role</a>
                            </td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <%@ include file="../partials/pagination.jspf" %>
        </div>
        <div class="col-md-1"></div>
    </div>