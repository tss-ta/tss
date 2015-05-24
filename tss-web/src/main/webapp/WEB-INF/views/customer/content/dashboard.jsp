<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="row row-fix">
    <div class="col-lg-12">
        <div class="text-center page-title">

            <h1>Dashboard</h1>
        </div>

        <h4 class="text-center">
            Your Taxi Orders
        </h4>
        <section id="unseen">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>Price</th>
                    <th class="hidden-sm hidden-md">Booking Time</th>
                    <th>Order Time</th>
                    <th>Status</th>
                    <th class="hidden-sm hidden-md">Driver</th>
                    <%--<th class="hidden-sm hidden-md">Route</th>--%>
                    <th>From Address</th>
                    <th>To Address</th>
                    <th class="hidden-sm">Service</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${history}" var="list">
                    <c:if test="${list.status != 5}">
                        <tr>
                            <td class="numeric">${list.price}</td>
                            <td class="hidden-sm hidden-md">${list.bookingTime}</td>
                            <td>${list.orderTime}</td>
                            <td>${list.strStatus}</td>
                            <td class="hidden-sm hidden-md">
                                <c:if test="${list.driverCarId != null }">
                                    Driver-Car ID = ${list.driverCarId.drvCarId}
                                </c:if>
                            </td>
                            <%--<td class="hidden-sm hidden-md">${list.routeId.pathContent}</td>--%>
                            <td>${list.fromAddr}</td>
                            <td>${list.toAddr}</td>
                            <td class="hidden-sm">
                                    ${list.serviceName}
                            </td>
                            <c:choose>
                                <c:when test="${(list.serviceBool==false) and (list.status==0)}">
                                    <td>
                                        <c:if test="${list.status!=5}">
                                            <a class="btn btn-default"
                                               href="/customer/edit?action=editTaxiOrder&taxiOrderId=${list.getId()}">Edit</a>
                                        </c:if>
                                    </td>
                                    <td class="text-center">
                                        <c:if test="${list.status!=5}">
                                            <a href="/customer/edit?action=deleteTaxiOrder&taxiOrderId=${list.getId()}">
                                                <i class="fa fa-times"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td></td>
                                    <td></td>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </section>
        <div class="text-center">
            <form action="/customer/dashboard"
                  class="form-horizontal style-form" method="get">
                <div class="btn-group">
                    <button type="submit" class="btn btn-default" id="previous "
                            name="previous">Previous
                    </button>
                    <button type="submit" class="btn btn-default" id="next"
                            name="next">Next
                    </button>
                </div>
            </form>
        </div>

    </div>
</div>


