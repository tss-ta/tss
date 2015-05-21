<%--
  author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/resources/customer_assets/css/anytime.5.1.0.css"
      rel="stylesheet">


<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <h1 class="text-center">Most Popular Car Options For ${requestScope.user}</h1>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin?menu=report&action=all" class="btn btn-default"><i class="fa fa-long-arrow-left"></i> Back</a>
                </div>
                <div class="col-md-1">
                    <a href="/admin/report?action=new-orders-excel&begin=${param.begin}&end=${param.end}" class="btn btn-default">
                        <i class="fa fa-download"></i> Excel
                    </a>
                </div>
            </div>
        </div>


        <div class="panel panel-default">
            <form class="panel-body" action="/admin/report">
                <table>
                    <thead>
                    <th class=" col-md-offset-1 text-center">Begin Time</th>
                    <th class="col-md-offset-1  text-center">End Time</th>
                    </thead>
                    <tbody>
                    <td class="col-md-offset-1 ">
                        <input type="text" id="begin" name="begin"
                               class="form-control" value="${param.begin}">
                    </td>
                    <td class="col-md-offset-1 ">
                        <input type="text" id="end" name="end"
                               class="form-control" value="${param.end}">
                    </td>
                    <td class="col-md-offset-1 ">
                        <input type="hidden" name="action" value="new-orders-per-period">
                        <input type="submit" value="Ok" class="btn btn-default">
                    </td>
                    </tbody>
                </table>
                <!--                    <label class="col-md-2 control-label">Begin Time: </label>
                                    <div class="col-md-3">
                                        <input type="text" id="begin" name="begin"
                                               class="form-control" />
                                    </div>


                                    <label class="col-md-2 control-label">End Time: </label>
                                    <div class="col-md-3">
                                        <input type="text" id="end" name="end"
                                               class="form-control" />
                                    </div>
                                </div>
                                <input type="hidden" name="action" value="new-orders-per-period">
                                <input type="submit" value="Ok" class="btn btn-default">-->
            </form>
        </div>

        <table class="table table-bordered table-striped table-condensed">
            <thead>
            <tr>
                <th>Price</th>
                <th>Booking Time</th>
                <th>Order Time</th>
                <th>Status</th>
                <%--<th>Driver</th>--%>
                <th>Car Category</th>
                <th class="hidden-xs hidden-sm">Wi-Fi</th>
                <th class="hidden-xs hidden-sm hidden-md">Conditioner</th>
                <th class="hidden-xs hidden-sm hidden-md">Animalable</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.orders}" var="list">
                <tr>
                    <td class="numeric">${list.price}</td>
                    <td>${list.bookingTime}</td>
                    <td>${list.orderTime}</td>
                    <td>${list.enumStatus}</td>
                    <%--<td></td>--%>
                    <td>${list.enumCarCategory}</td>
                    <td class="hidden-xs hidden-sm">${list.wifi}</td>
                    <td class="hidden-xs hidden-sm hidden-md">${list.conditioner}</td>
                    <td class="hidden-xs hidden-sm hidden-md">${list.animalTransport}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>

        <h3 class="text-right">All new taxi orders booked by this period: ${requestScope.allTO}</h3>

        <%--<%@ include file="../partials/pagination.jspf" %>--%>
        <c:if test="${not empty pager}">
            <nav>
                <ul class="pager custom-pager">

                    <c:if test="${not empty pager.firstPage}">
                        <li>
                            <a  href="/admin/report?action=new-orders-per-period&begin=${param.begin}&end=${param.end}&page=${pager.firstPage}"
                                title="First (${pager.firstPage})"><i class="fa fa-angle-double-left fa-lg"></i>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${not empty pager.previousPage}">
                        <li><a href="/admin/report?action=new-orders-per-period&begin=${param.begin}&end=${param.end}&page=${pager.previousPage}"
                               title="Previous (${pager.previousPage})"><i class="fa fa-angle-left fa-lg"></i></a></li>
                    </c:if>

                    <c:if test="${not empty pager.nextPage}">
                        <li><a href="/admin/report?action=new-orders-per-period&begin=${param.begin}&end=${param.end}&page=${pager.nextPage}"
                               title="Next (${pager.nextPage})"><i class="fa fa-angle-right fa-lg"></i></a></li>
                    </c:if>

                    <c:if test="${not empty pager.lastPage}">
                        <li><a href="/admin/report?action=new-orders-per-period&begin=${param.begin}&end=${param.end}&page=${pager.lastPage}"
                               title="Last (${pager.lastPage})"><i class="fa fa-angle-double-right fa-lg"></i></a></li>
                    </c:if>

                </ul>
            </nav>
        </c:if>


    </div>
    <div class="col-md-1"></div>


    <script src="/resources/customer_assets/js/jquery.js"></script>
    <script src="/resources/customer_assets/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>


    <script>
        AnyTime.picker("begin", {
        format: "%H-%i_%d-%m-%Y",
        firstDOW: 1
        });
    </script>
    <script>
        AnyTime.picker("end", {
            format: "%H-%i_%d-%m-%Y",
        firstDOW: 1
        });
    </script>


</div>