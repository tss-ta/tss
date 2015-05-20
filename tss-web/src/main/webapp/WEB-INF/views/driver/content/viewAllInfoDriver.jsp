<%-- 
    Document   : viewAllInfoDriver
    Created on : 17.05.2015, 21:09:16
    Author     : Виктор
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Taxi Order Options</h1>
        </div>
    </div>
</div>
<br>
&nbsp;
<br>
<form id="submit_id" action="/driver/dashboard/allinfo"
      class="form-horizontal style-form" method="post">
    <div class="row mt bottom_line">
        <div class="form-group">
            <div class="col-md-6">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <label class="col-sm-2 col-sm-2 control-label">Order Time</label>
                        <div class="col-sm-4">
                            <input type="text" id="ordertime" name="ordertime" value="${orderTime}"
                                   class="form-control" readonly />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2"></div>
                    <label class="col-sm-2 col-sm-2 control-label">From :</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="fromAddr" name="fromAddr"
                               value="${list.fromAddr}" maxlength="140" readonly />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2"></div>
                    <label class="col-sm-2 col-sm-2 control-label">To :</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="toAddr" name="toAddr"
                               value="${list.toAddr}" maxlength="140" readonly />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-1"></div>
                    <label class="col-sm-2 col-sm-2 control-label">Price :</label>
                    <div class="col-sm-6">
                        <input  class="form-control" name="price" type="text"
                                value="${list.price}" readonly>
                    </div>
                </div>

            </div>
            <div class="clearfix visible-xs-block"></div>
            <div class="col-md-6">
                <%@ include file="../../partials/map.jspf"%>
            </div>
            <div class="col-lg-12">
                <div class="form-panel">
                    <h4 class="mb">
                        <i class="fa"></i> Additional Options
                    </h4>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Car type
                            :</label> <c:choose>
                                <c:when test="${list.carCategory == 1}">
                                <i class="col-sm-2 col-sm-2">Economy</i>  
                            </c:when>
                            <c:when test="${list.carCategory == 2}">
                                <i class="col-sm-2 col-sm-2">Business</i>  
                            </c:when>
                            <c:when test="${list.carCategory == 3}">
                                <i class="col-sm-2 col-sm-2">Van</i>  
                            </c:when>
                            <c:when test="${list.carCategory == 4}">
                                <i class="col-sm-2 col-sm-2">Cargo</i>  
                            </c:when>
                            <c:otherwise>
                                <i class="col-sm-2 col-sm-2">Other</i>  
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Way of payment
                            :</label><c:choose>
                                <c:when test="${list.payment == 1}">
                                <i class="col-sm-2 col-sm-2">Cash</i>  
                            </c:when>
                            <c:when test="${list.payment == 2}">
                                <i class="col-sm-2 col-sm-2">Mastercard</i>  
                            </c:when>
                            <c:when test="${list.payment == 3}">
                                <i class="col-sm-2 col-sm-2">Visa</i>  
                            </c:when>
                            <c:otherwise>
                                <i class="col-sm-2 col-sm-2">Other</i>  
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Driver's gender
                            :</label><c:if test="${list.male == null}">
                            <i class="col-sm-2 col-sm-2 fa fa-minus"></i>
                        </c:if>
                        <c:if test="${list.male}">
                               <i class="col-sm-2 col-sm-2 fa fa-male"></i>
                        </c:if>
                        <c:if test="${list.male==false}">
                            <i class="col-sm-2 col-sm-2 fa fa-female"></i>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Music type :</label> <c:choose>
                            <c:when test="${list.musicStyle == 1}">
                                <i class="col-sm-2 col-sm-2">Rock</i>  
                            </c:when>
                            <c:when test="${list.musicStyle == 2}">
                                <i class="col-sm-2 col-sm-2">Classic</i>  
                            </c:when>
                            <c:when test="${list.musicStyle == 3}">
                                <i class="col-sm-2 col-sm-2">Jazz</i>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">WI-FI :</label>  <c:if test="${list.wifi}">
                            <i class="col-sm-2 col-sm-2 fa fa-wifi fa-lg"></i>
                        </c:if>
                        <c:if test="${!list.wifi}">
                            <i class="col-sm-2 col-sm-2 fa fa-minus"></i>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Animal transportation :</label> <c:if test="${list.animalTransport}">
                            <i class=" col-sm-2 col-sm-2 fa fa-check "></i>
                        </c:if>
                        <c:if test="${!list.animalTransport}">
                            <i class="col-sm-2 col-sm-2 fa fa-minus"></i>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Non-smoking driver :</label>  <c:if test="${list.smoke}">
                            <i class="col-sm-2 col-sm-2 fa fa-check "></i>
                        </c:if>
                        <c:if test="${!list.smoke}">
                            <i class="col-sm-2 col-sm-2 fa fa-minus"></i>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 col-sm-2 control-label">Air-conditioner :</label> <c:if test="${list.conditioner}">
                            <i class="col-sm-2 col-sm-2 fa fa-check"></i>
                        </c:if>
                        <c:if test="${!list.conditioner}">
                            <i class="col-sm-2 col-sm-2 fa fa-minus"></i>
                        </c:if>

                    </div>
                </div>
                <!-- /col-lg-12 -->
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <button class="btn btn-info"  OnClick="history.go(-1);">Back</button>
                        <c:if test="${list.status != 5}">
                            <c:if test="${list.status != 3}">
                                <a href="/driver/dashboard?action=changeStatus&order_id=${list.id}" class="btn btn-success">
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
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>

<script src="/resources/customer_assets/js/form-component.js"></script>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="text/javascript"
        src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;language=en-GB">
</script>

<script type="text/javascript"
        src="/resources/customer_assets/js/map_driver.js">
</script>


<script>
    $(function () {
        window.onload = function () {
            initialize();
            showonmap();
        }
    });

    $(function () {
        $('select.styled').customSelect();
    });
</script>


<script>
    $('#update_price').click(function () {
        $.ajax({
            type: "GET",
            url: "/priceSoberService",
            data: {
                fromAddr: transliterate($("#fromAddr").val()),
                toAddr: transliterate($("#toAddr").val()),
                ordertime: $("#ordertime").val(),
                addOptions: $("#addOptions").val(),
                carType: $("#carType").val()
            },
            dataType: "text",
        }).done(function (res) {
            $('#price_field').val(res);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert("Address error");
        });
    });
</script>




