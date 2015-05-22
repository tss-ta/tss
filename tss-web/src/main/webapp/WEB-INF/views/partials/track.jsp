<%-- 
    Document   : track
    Author     : Viktor, maks
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row row-fix">
<div class="col-md-offset-1 col-md-10 text-center">
<form action="/track/order" class="form-horizontal style-form"
      method="post">
    <div class="text-center">
        <div class="row mt bottom_line">
            <div class="text-center">
                <h1>Track Order</h1>

            </div>
        </div>

    </div>
    <br>&nbsp;<br>

    <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
            <div class="control-group">
                <input type="number" class="form-control" name ="trackNumber" value="${trackId}"
                       placeholder="Tracking number" min="1" max="4000000000" />
                <span class="input-group-btn">
                    <button class="btn btn-primary" name="submit_track_number" type="submit">Ok</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-4 -->
    </div>



</form>

<c:choose>
    <c:when test="${not empty list}">
        <br>&nbsp;<br>
        <section id="unseen">
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th class="text-center">Price</th>
                    <th class="text-center">Booking Time</th>
                    <th class="text-center">Order Time</th>
                    <th class="text-center">Status</th>
                        <%--<th>Driver</th>--%>
                    <%--<th class="text-center">Route</th>--%>
                    <th class="text-center">From Address</th>
                    <th class="text-center">To Address</th>
                    <th class="hidden-xs hidden-sm text-center">Wi-Fi</th>
                    <th class="hidden-xs hidden-sm hidden-md text-center">Conditioner</th>
                    <th class="hidden-xs hidden-sm hidden-md text-center">Animalable</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td class="numeric">${list.price}</td>
                    <td>${list.bookingTime}</td>
                    <td>${list.orderTime}</td>
                    <td>${list.strStatus}</td>
                        <%--<td><c:if test="${list.driverCarId != null }">--%>
                        <%--DriverCar ${list.driverCarId.drvCarId}--%>
                        <%--</c:if></td>--%>
                    <%--<td>${list.routeId.pathContent}</td>--%>
                    <td>${list.fromAddr}</td>
                    <td>${list.toAddr}</td>
                    <td class="hidden-xs hidden-sm"> ${list.wifi} </td>
                    <td class="hidden-xs hidden-sm hidden-md">${list.conditioner}</td>
                    <td class="hidden-xs hidden-sm hidden-md">${list.animalTransport}</td>
                </tr>
                </tbody>
            </table>
        </section>
    </c:when>
    <c:when test="${error}">
        <div class="alert alert-warning text-center">
            Sorry, this order not found.
        </div>
    </c:when>
    <c:otherwise>
        <h3 class="text-center">Please, enter your tracking number to view taxi order status</h3>
    </c:otherwise>
</c:choose>
<br>&nbsp;<br>
<br>&nbsp;<br>
<br>&nbsp;<br>
    </div>
</div>