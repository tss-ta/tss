<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Car Panel</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin/cars?menu=cars&action=add-car" class="btn btn-default">Add <i class="fa fa-taxi"></i></a>
                </div>
                <div class="col-md-offset-7 col-md-4">
                    <form>
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="AAA-000-AAA">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <table class="table table-striped table-bordered">
            <thead class="tablehead text-center">
            <td class="col-md-3">№</td>
            <td class="col-md-2">Type</td>
            <td class="col-md-1">WI-FI</td>
            <td class="col-md-2">Conditioner</td>
            <td class="col-md-1">Animal</td>
            <td class="col-md-1">Active</td>
            <td class="col-md-2">Settings</td>
            </thead>

            <tbody>
            <c:forEach items="${carList}" var="car">
                <tr class="text-center">
                    <td>${car.getLicPlate()}</td>
                    <td>
                        <c:choose>
                            <c:when test="${car.category == 1}">
                                Business
                            </c:when>
                            <c:when test="${car.category == 2}">
                                Economy
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
                    <td>
                        <c:if test="${car.wifi}">
                            <i class="fa fa-wifi"></i>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${car.conditioner}">
                            <i class="fa fa-check"></i>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${car.animalable}">
                            <i class="fa fa-check"></i>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${car.available}">
                            <i class="fa fa-check"></i>
                        </c:if>
                    </td>
                    <c:if test="${not assign}">
                        <td><a href="/admin?action=edit">Edit</a></td>
                    </c:if>
                    <c:if test="${assign}">
                        <td><a href="/admin/driver?action=assigncar&carid=${car.getId()}&driverid=${driverid}">Assign Car</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <%@ include file="../partials/pagination.jspf"%>

    </div>
</div>