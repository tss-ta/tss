<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row">
    <div class="col-lg-9 main-chart">
        <div class="text-center">
            <h1>Dashboard</h1>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                <form action = "/driver/dashboard" class="text-left">
                    <label class="col-sm-2 col-sm-2 control-label">
                        Order Status:</label><select class="selectpicker" name ="status" onchange="this.form.submit()" >
                        <c:forEach var = "status" items = "${status_enum}">
                            <option ${status.getName() == requestScope.param ? 'selected="selected"' : ''}> ${status.getName()} </option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="action" value="filterByStatus">
                </form>
            </div>
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
                                <th>Order Time</th>
                                <th>Status</th>
                                <th>Route</th>
                                <th>From Address</th>
                                <th>To Address</th>
                                <th>Car Category</th>
                                <th>Wifi</th>
                                <th>Conditioner</th>
                                <th>Animal Transport</th>
                                <th>Smoke</th>
                                <th>Male</th>
                                <th>Music Style</th>
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
                                    <td>${list.routeId.pathContent}</td>
                                    <td>${list.fromAddr}</td>
                                    <td>${list.toAddr}</td>
                                    <td class="hidden-sm hidden-xs">
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
                                        <c:if test="${list.wifi}">
                                            <i class="fa fa-wifi fa-lg"></i>
                                        </c:if>
                                        <c:if test="${!list.wifi}">
                                            <i class="fa fa-minus"></i>
                                        </c:if>
                                    </td>
                                    <td class="hidden-sm hidden-xs">
                                        <c:if test="${list.conditioner}">
                                            <i class="fa fa-check"></i>
                                        </c:if>
                                        <c:if test="${!list.conditioner}">
                                            <i class="fa fa-minus"></i>
                                        </c:if>
                                    </td>
                                    <td class="hidden-sm hidden-xs">
                                        <c:if test="${list.animalTransport}">
                                            <i class="fa fa-check"></i>
                                        </c:if>
                                        <c:if test="${!list.animalTransport}">
                                            <i class="fa fa-minus"></i>
                                        </c:if>
                                    </td>
                                    <td class="hidden-sm hidden-xs">
                                        <c:if test="${list.smoke}">
                                            <i class="fa fa-check"></i>
                                        </c:if>
                                        <c:if test="${!list.smoke}">
                                            <i class="fa fa-minus"></i>
                                        </c:if>
                                    </td>
                                    <td class="hidden-sm hidden-xs">
                                        <c:if test="${list.male}">
                                            <i class="fa fa-check"></i>
                                        </c:if>
                                        <c:if test="${!list.male}">
                                            <i class="fa fa-minus"></i>
                                        </c:if>
                                    </td>
                                    <td class="hidden-sm hidden-xs">
                                        <c:choose>
                                            <c:when test="${list.musicStyle == 1}">
                                                Rock
                                            </c:when>
                                            <c:when test="${list.musicStyle == 2}">
                                                Classic
                                            </c:when>
                                            <c:when test="${list.musicStyle == 3}">
                                                Jazz
                                            </c:when>
                                        </c:choose>
                                    </td>
                                    <c:if test="${list.status != 5}">
                                        <c:if test="${list.status != 3}">
                                            <td class="hidden-sm hidden-xs">
                                                <a href="/driver/dashboard?action=changeStatus&order_id=${list.id}" class="btn btn-default">
                                                    <c:choose>
                                                        <c:when test="${list.status+1 == 2}">
                                                           TO ASSIGNED
                                                        </c:when>
                                                        <c:when test="${list.status+2 == 2}">
                                                           TO ASSIGNED
                                                        </c:when>
                                                        <c:when test="${list.status+2 == 4}">
                                                           TO IN_PROGRESS 
                                                        </c:when>
                                                        <c:when test="${list.status+1 == 5}">
                                                           TO COMPLETED
                                                        </c:when>
                                                        <c:otherwise>
                                                            Other
                                                        </c:otherwise>
                                                    </c:choose>
                                                </a>
                                            </td>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                        </tbody>
                    </table>
                </section>
                <div class="text-center">
                    <form action="/driver/dashboard"
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


