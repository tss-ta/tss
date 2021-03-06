<%--
  author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row row-fix">

    <div class="col-md-offset-1 col-md-10">
        <div class="col-md-10">
            <div class="text-center">
                <h1>Choose Customer To View Report</h1>
            </div>

            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-1">
                        <a href="/admin?menu=report&action=all" class="btn btn-default"><i class="fa fa-reply"></i>Back </a>
                    </div>
                    <div class="col-md-offset-7 col-md-4">
                        <form action="/admin/report" method="get">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="Search by email" name="email" value="${param.email}" maxlength="40">
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
                                <a href="/admin/report?action=user-car-options-report&userid=${customer.id}&email=${customer.email}" class="btn btn-default">
                                    View Report
                                </a>
                            </td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
            <%--<%@ include file="../partials/pagination.jspf" %>--%>

            <c:if test="${not empty pager}">
                <nav>
                    <ul class="pager custom-pager">

                        <c:if test="${not empty pager.firstPage}">
                            <li>
                                <a  href="/admin/report?action=${param.action}&email=${param.email}&page=${pager.firstPage}"
                                    title="First (${pager.firstPage})"><i class="fa fa-angle-double-left fa-lg"></i>
                                </a>
                            </li>
                        </c:if>

                        <c:if test="${not empty pager.previousPage}">
                            <li><a href="/admin/report?action=${param.action}&email=${param.email}&page=${pager.previousPage}"
                                   title="Previous (${pager.previousPage})"><i class="fa fa-angle-left fa-lg"></i></a></li>
                        </c:if>

                        <c:if test="${not empty pager.nextPage}">
                            <li><a href="/admin/report?action=${param.action}&email=${param.email}&page=${pager.nextPage}"
                                   title="Next (${pager.nextPage})"><i class="fa fa-angle-right fa-lg"></i></a></li>
                        </c:if>

                        <c:if test="${not empty pager.lastPage}">
                            <li><a href="/admin/report?action=${param.action}&email=${param.email}&page=${pager.lastPage}"
                                   title="Last (${pager.lastPage})"><i class="fa fa-angle-double-right fa-lg"></i></a></li>
                        </c:if>

                    </ul>
                </nav>
            </c:if>

        </div>
        <div class="col-md-1"></div>
    </div>
    </div>