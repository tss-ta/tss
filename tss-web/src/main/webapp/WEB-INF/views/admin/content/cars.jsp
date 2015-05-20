<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>Car Panel</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-offset-0 col-md-1 col-sm-offset-0 col-sm-2 col-xs-offset-4 col-xs-4">
                    <a href="/admin?menu=car&action=add" class="btn btn-default">Add <i class="fa fa-taxi"></i></a>
                </div>
                <div class="col-md-offset-7 col-md-4 col-sm-offset-2 col-sm-8 col-xs-12">
                    <form action="/admin" method="get" >
                        <div class="input-group">
                            <input type="hidden" name="menu" value="car">
                            <input type="hidden" name="action" value="search">
                            <input type="text" class="form-control" name="search" placeholder="Search by License Plate" value="${param.search}" maxlength="11">
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

<c:if test="${not empty carList}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <table class="table table-hover table-bordered">
                <thead class="tablehead text-center">
                <td class="col-md-3 col-sm-5 col-xs-6">License Plate</td>
                <td class="col-md-2 hidden-sm hidden-xs">Type</td>
                <td class="col-md-1 hidden-sm hidden-xs">WI-FI</td>
                <td class="col-md-2 hidden-sm hidden-xs">Conditioner</td>
                <td class="col-md-1 hidden-sm hidden-xs">Animal</td>
                <td class="col-md-1 col-sm-2 hidden-xs">Active</td>
                <td class="col-md-2 col-sm-5 col-xs-6">Settings</td>
                </thead>

                <tbody>
                <c:forEach items="${carList}" var="car">
                    <tr class="text-center">
                        <td><a class="custom-link" href="/admin?menu=car&action=view&id=${car.id}">${car.getLicPlate()}</a></td>
                        <td class="hidden-sm hidden-xs">
                           <c:choose>
                                <c:when test="${car.category == 1}">
                                    Economy
                                </c:when>
                                <c:when test="${car.category == 2}">
                                    Business
                                </c:when>
                                <c:when test="${car.category == 3}">
                                    Van
                                </c:when>
                                <c:when test="${car.category == 4}">
                                    Cargo
                                </c:when>
                                <c:otherwise>
                                    Other
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="hidden-sm hidden-xs">
                            <c:if test="${car.wifi}">
                                <i class="fa fa-wifi fa-lg"></i>
                            </c:if>
                            <c:if test="${!car.wifi}">
                                <i class="fa fa-minus"></i>
                            </c:if>
                        </td>
                        <td class="hidden-sm hidden-xs">
                            <c:if test="${car.conditioner}">
                                <i class="fa fa-check"></i>
                            </c:if>
                            <c:if test="${!car.conditioner}">
                                <i class="fa fa-minus"></i>
                            </c:if>
                        </td>
                        <td class="hidden-sm hidden-xs">
                            <c:if test="${car.animalable}">
                                <i class="fa fa-check"></i>
                            </c:if>
                            <c:if test="${!car.animalable}">
                                <i class="fa fa-minus"></i>
                            </c:if>
                        </td>

                        <td class="hidden-xs">
                            <c:if test="${car.available}">
                                <i class="fa fa-check"></i>
                            </c:if>
                            <c:if test="${!car.available}">
                                <i class="fa fa-minus"></i>
                            </c:if>
                        </td>

                        <td>
                            <c:if test="${not assign}">
                                <a class="custom-link" href="/admin?menu=car&action=edit&id=${car.id}" title="Edit"><i class="fa fa-pencil fa-lg"></i></a>
                                <%--<a class="custom-link trash" href="/admin?menu=car&action=remove&id=${car.id}" title="Delete"><i class="fa fa-trash-o fa-lg"></i></a>--%>
                            </c:if>

                            <c:if test="${assign}">
                                <a class="custom-link hover-green" href="/admin/driver?action=assigncar&carid=${car.id}&driverid=${driverid}">Assign <i class="fa fa-check-circle"></i></a>
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
<c:if test="${empty carList}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No results found</h3>
            </div>
        </div>
    </div>
</c:if>

