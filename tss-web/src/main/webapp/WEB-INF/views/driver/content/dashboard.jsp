<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="row row-fix">
    <div class="col-lg-offset-1 col-md-10">
        <div class="text-center">
            <h1>Driver Dashboard</h1>
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
            <div class="col-md-12">
                <h4 class="text-center">
                    Taxi Orders
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
                                    <td class="hidden-sm hidden-xs hidden-md">
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
                                    <c:if test="${list.status != 5}">
                                        <c:if test="${list.status != 3}">
                                            <td>
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
                    <form action="/driver/dashboard"
                          class="form-horizontal style-form" method="get">
                        <div class="btn-group">
                            <button type="submit" class="btn btn-default" id="previous "
                                    name="previous">Previous</button>
                            <button type="submit" class="btn btn-default" id="next"
                                    name="next">Next</button>
                        </div>

                        <input type="hidden" name="status" value="${requestScope.param}">
                        <input type="hidden" name="action" value="filterByStatus">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


