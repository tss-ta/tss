<%--
  author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/resources/customer_assets/css/anytime.5.1.0.css"
      rel="stylesheet">


<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1 class="text-center">Most Popular Car Options For ${requestScope.user}</h1>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin/report" class="btn btn-default"> Back </a>
                </div>
                <div class="col-md-1">
                    <a href="/admin/report" class="btn btn-default"> Export to excel file <i class="fa fa-download"></i></a>
                </div>
            </div>
        </div>






        <div class="panel panel-default">
            <form class="panel-body" action="/admin/report" >
                <div class="form-group">
                    <label class="col-md-2 control-label">Begin Time: </label>
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
                <input type="submit" value="Ok" class="btn btn-default">
            </form>
        </div>

        <table class="table table-bordered table-striped table-condensed">
            <thead>
                <tr>
                    <th>Price</th>
                    <th>Booking Time</th>
                    <th>Order Time</th>
                    <th>Status</th>
                    <th>Driver</th>
                    <th>Route</th>
                    <th>Car Category</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.orders}" var="list">
                    <tr>
                        <td class="numeric">${list.price}</td>
                        <td>${list.bookingTime}</td>
                        <td>${list.orderTime}</td>
                        <td>${list.enumStatus}</td>
                        <td></td>
                        <td>${list.routeId.pathContent}</td>
                        <td>${list.enumCarCategory}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br>
        <h3 class="text-right">All new taxi orders booked by this period: ${requestScope.allTO}</h3>

        <%--       <%@ include file="../partials/pagination.jspf" %>  --%>

    </div>
    <div class="col-md-1"></div>


    <script src="/resources/customer_assets/js/jquery.js"></script>
    <script src="/resources/customer_assets/js/jquery-1.8.3.min.js"></script>
    <script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>


    <script>
        AnyTime.picker("begin", {
        format: "%H:%i, %d %m %Y",
        firstDOW: 1
        });
    </script>
    <script>
        AnyTime.picker("end", {
        format: "%H:%i, %d %m %Y",
        firstDOW: 1
        });
    </script>


</div>