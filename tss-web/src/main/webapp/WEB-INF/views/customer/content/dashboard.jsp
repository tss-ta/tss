<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <div class="col-lg-9 main-chart">
        <div class="text-center">
            <h1>Dashboard</h1>
        </div>
        <div class="row mt bottom_line">
            <div class="col-lg-12">
                <h4>
                    <i class="fa"></i> Your Taxi Orders
                </h4>
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>Price</th>
                                <th>Booking Time</th>
                                <th>Order Time</th>
                                <th>Status</th>
                                <th>Driver</th>
                                <th>Route</th>
                                <th>From Address</th>
                                <th>To Address</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${history}" var="list">
                                <c:if test="${list.status != 5}">
                                    <tr>
                                        <td class="numeric">${list.price}</td>
                                        <td>${list.bookingTime}</td>
                                        <td>${list.orderTime}</td>
                                        <td>${list.strStatus}</td>
                                        <td> <c:if test="${list.driverCarId != null }">
                                                DriverCar ${list.driverCarId.drvCarId}
                                            </c:if>
                                        </td>
                                        <td>${list.routeId.pathContent}</td>
                                        <td>${list.fromAddr}</td>
                                        <td>${list.toAddr}</td>
                                        <c:if test="${list.status==0}">
                                            <td><c:if test="${list.status!=5}">
                                                    <a
                                                        href="/customer/edit?action=editTaxiOrder&taxiOrderId=${list.getId()}">edit</a>
                                                </c:if></td>
                                            <td><c:if test="${list.status!=5}">
                                                    <a
                                                        href="/customer/edit?action=deleteTaxiOrder&taxiOrderId=${list.getId()}">X</a>
                                                </c:if></td>
                                            </c:if>

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
                                    name="previous">Previous</button>
                            <button type="submit" class="btn btn-default" id="next"
                                    name="next">Next</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


