<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row row-fix">
    <div class=" col-lg-offset-1 col-md-10 main-chart">
        <div class="text-center">
            <h1>History Of Completed Orders</h1>
        </div>
        <div class="row mt bottom_line">
            <div class="col-lg-12">
                <h4>
                    Completed Taxi Orders
                </h4>
                <section id="unseen">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>Price</th>
                                <th>Order Time</th>
                                <th>Status</th>
                                <%--<th>Route</th>--%>
                                <th>From Address</th>
                                <th>To Address</th>
                                <th class="hidden-md hidden-sm hidden-xs">Car Category</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${history}" var="list">
                                <tr>
                                    <td class="numeric">
                                        <fmt:formatNumber value= "${list.price}" maxFractionDigits="1"/>
                                    </td>
                                    <td>${list.orderTime}</td>
                                    <td>${list.strStatus}</td>
                                    <%--<td>${list.routeId.pathContent}</td>--%>
                                    <td>${list.fromAddr}</td>
                                    <td>${list.toAddr}</td>
                                    <td class="hidden-md hidden-sm hidden-xs">
                                        <c:choose>
                                            <c:when test="${list.carCategory == 1}">
                                                Economy
                                            </c:when>
                                            <c:when test="${list.carCategory == 2}">
                                                Business 
                                            </c:when>
                                            <c:when test="${list.carCategory == 3}">
                                                Van
                                            </c:when>
                                            <c:when test="${list.carCategory == 4}">
                                                Cargo
                                            </c:when>
                                            <c:otherwise>
                                                Other
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/driver/dashboard/allinfo?action=viewAll&order_id=${list.id}" class="btn btn-default">Details</a>
                                    </td>
                                </c:forEach>
                        </tbody>
                    </table>
                    <c:if test="${empty history}">
                        <div class="row row-fix">
                            <div class="col-md-offset-1 col-md-10">
                                <div class="text-center">
                                    <h3>Taxi Orders Not Found</h3>
                                    <br/>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </section>
                <div class="text-center">
                    <form action="/driver/history"
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