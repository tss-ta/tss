<%-- 
    Document   : track
    Author     : Viktor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <div class="input-group">
                <input type="text" class="form-control" name ="trackNumber" value="${trackId}" placeholder="Enter tracking number" /> 
                <span class="input-group-btn">
                    <button class="btn btn-primary" name="submit_track_number" type="submit">Submit</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-4 -->
    </div>
    <c:if test="${error}">
        <div class="alert alert-warning">
            Order not found.
        </div>
    </c:if>

    <br>&nbsp;<br>
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
                <tr>
                    <td class="numeric">${list.price}</td>
                    <td>${list.bookingTime}</td>
                    <td>${list.orderTime}</td>
                    <td>${list.strStatus}</td>
                    <td>${list.driverCarId}</td>
                    <td>${list.routeId.pathContent}</td>
                    <td>${list.fromAddr}</td>
                    <td>${list.toAddr}</td>
                </tr>
            </tbody>
        </table>
    </section>
    <br>&nbsp;<br>
    <br>&nbsp;<br>
    <br>&nbsp;<br>
</form>