<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
    <div class="col-md-12 main-chart">
        <div class="text-center">
            <h1>Taxi Orders History</h1>
        </div>
        <div class="row mt bottom_line">
            <div class="col-lg-12">
                <h4>
                    <i class="fa"></i> Your Taxi Orders History
                </h4>
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>Price</th>
                                <th class="hidden-sm hidden-md">Booking Time</th>
                                <th>Order Time</th>
                                <th class="hidden-md hidden-sm hidden-xs">Driver</th>
                                <%--<th class="hidden-md hidden-sm hidden-xs">Route</th>--%>
                                <th>From Address</th>
                                <th>To Address</th>
                                <th class="hidden-sm">Service</th>
                                <th>Comment</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${history}" var="list">
                                <tr>
                                    <td class="numeric">${list.price}</td>
                                    <td class="hidden-sm hidden-md">${list.bookingTime}</td>
                                    <td>${list.orderTime}</td>
                                    <td class="hidden-md hidden-sm hidden-xs">
                                            Driver ID = ${list.driverCarId.driverId}
                                    </td>
                                    <%--<td class="hidden-md hidden-sm hidden-xs">${list.routeId.pathContent}</td>--%>
                                    <td>${list.fromAddr}</td>
                                    <td>${list.toAddr}</td>
                                    <td class="hidden-sm">
                                            ${list.serviceName}
                                    </td>
                                    <td>${list.comment}</td>
                                    <td><a class="btn btn-default"
                                            href="/customer/comment?taxiOrderId=${list.getId()}">Comment</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </section>
                <div class="text-center">
                    <form action="/customer/history" class="form-horizontal style-form"
                          method="get">
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


