<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Taxi was successfully ordered!</h1>
        </div>
        <div class="row mt">
            <div class="col-lg-12">
                <div class="text-center">
                    <br/>
                    <h3>
                        Your Taxi Order Tracking Number is <b style="color: crimson">${taxiOrderId}</b>.
                    </h3>
                    <br/>
                    <h4 class="text-muted">
                        Please, remember your tracking number, because you can track status of your order with this number.
                    </h4>
                </div>
            </div>
        </div>
    </div>
</div>


