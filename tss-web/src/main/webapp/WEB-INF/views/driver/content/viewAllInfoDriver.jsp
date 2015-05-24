<%-- 
    Document   : viewAllInfoDriver
    Created on : 17.05.2015, 21:09:16
    Author     : Виктор, maks
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-10">
        <div class="text-center">
            <h1>Taxi Order Options</h1>
        </div>

        <br/>

        <form id="submit_id" action="/driver/dashboard/allinfo"
              class="form-horizontal style-form" method="post">
            <div class="row mt bottom_line">
                <%--<div class="form-group">--%>
                <div class="col-md-5">
                    <%--<div class="col-lg-12">--%>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Order Time:</label>

                        <div class="col-md-9">
                            <input type="text" id="ordertime" name="ordertime" value="${orderTime}"
                                   class="form-control" readonly/>
                        </div>
                    </div>
                    <%--</div>--%>
                    <div class="form-group">

                        <label class="col-md-3 control-label">From:</label>

                        <div class="col-md-9">
                            <input type="text" class="form-control" id="fromAddr" name="fromAddr"
                                   value="${list.fromAddr}" readonly/>
                        </div>
                    </div>
                    <div class="form-group">

                        <label class="col-md-3 control-label">To:</label>

                        <div class="col-md-9">
                            <input type="text" class="form-control" id="toAddr" name="toAddr"
                                   value="${list.toAddr}" readonly/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3 control-label">Price:</label>

                        <div class="col-md-9">
                            <input class="form-control" name="price" type="text"
                                   value="${list.price}" readonly>
                        </div>
                    </div>


                    <h4 class="col-md-12 text-center">
                        Additional Options
                    </h4>
                    <table class="table" width="100%">
                        <tbody>
                        <tr>
                            <td class="text-right">Car type:</td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.carCategory == 1}">
                                        <p class="col-md-5">Economy</p>
                                    </c:when>
                                    <c:when test="${list.carCategory == 2}">
                                        <p class="col-md-5">Business</p>
                                    </c:when>
                                    <c:when test="${list.carCategory == 3}">
                                        <p class="col-md-5">Van</p>
                                    </c:when>
                                    <c:when test="${list.carCategory == 4}">
                                        <p class="col-md-5">Cargo</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="col-md-5">Other</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                        <tr>
                            <td class="text-right">
                                Way of payment:
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.payment == 1}">
                                        <p class="col-md-5">Cash</p>
                                    </c:when>
                                    <c:when test="${list.payment == 2}">
                                        <p class="col-md-5">Mastercard</p>
                                    </c:when>
                                    <c:when test="${list.payment == 3}">
                                        <p class="col-md-5">Visa</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="col-md-5">Other</p>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Driver's gender:
                            </td>
                            <td>
                                <c:if test="${list.male == null}">
                                    <i class="col-md-5 fa fa-minus"></i>
                                </c:if>
                                <c:if test="${list.male}">
                                    <i class="col-md-5 fa fa-male"></i>
                                </c:if>
                                <c:if test="${list.male==false}">
                                    <i class="col-md-5 fa fa-female"></i>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Music type:
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.musicStyle == 1}">
                                        <p class="col-md-5">Rock</p>
                                    </c:when>
                                    <c:when test="${list.musicStyle == 2}">
                                        <p class="col-md-5">Classic</p>
                                    </c:when>
                                    <c:when test="${list.musicStyle == 3}">
                                        <p class="col-md-5">Jazz</p>
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Wi-Fi:
                            </td>
                            <td>
                                <c:if test="${list.wifi}">
                                    <i class="col-md-5 fa fa-wifi fa-lg"></i>
                                </c:if>
                                <c:if test="${!list.wifi}">
                                    <i class="col-md-5 fa fa-minus"></i>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Animal transportation:
                            </td>
                            <td>
                                <c:if
                                        test="${list.animalTransport}">
                                    <i class="col-md-5 fa fa-check "></i>
                                </c:if>
                                <c:if test="${!list.animalTransport}">
                                    <i class="col-md-5 fa fa-minus"></i>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Non-smoking driver:
                            </td>
                            <td>
                                <c:if test="${list.smoke}">
                                    <i class="col-md-5 fa fa-check "></i>
                                </c:if>
                                <c:if test="${!list.smoke}">
                                    <i class="col-md-5 fa fa-minus"></i>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right">
                                Air-conditioner:
                            </td>
                            <td>
                                <c:if test="${list.conditioner}">
                                    <i class="col-md-5 fa fa-check"></i>
                                </c:if>
                                <c:if test="${!list.conditioner}">
                                    <i class="col-md-5 fa fa-minus"></i>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                </div>

                <div class="clearfix visible-xs-block"></div>
                <div class="col-md-6">
                    <%@ include file="../../partials/map.jspf" %>
                </div>
                <%--<div class="col-lg-12">--%>

                <%--</div>--%>
                <!-- /col-lg-12 -->
                <div class="row">
                    <div class="col-sm-12 text-center">
                        <button class="btn btn-info" OnClick="history.go(-1);">Back</button>
                        <c:if test="${list.status != 5}">
                            <c:if test="${list.status != 3}">
                                <a href="/driver/dashboard?action=changeStatus&order_id=${list.id}"
                                   class="btn btn-success">
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
        </form>
    </div>
</div>
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


<%--<script>--%>
<%--$('#update_price').click(function () {--%>
<%--$.ajax({--%>
<%--type: "GET",--%>
<%--url: "/priceSoberService",--%>
<%--data: {--%>
<%--fromAddr: transliterate($("#fromAddr").val()),--%>
<%--toAddr: transliterate($("#toAddr").val()),--%>
<%--ordertime: $("#ordertime").val(),--%>
<%--addOptions: $("#addOptions").val(),--%>
<%--carType: $("#carType").val()--%>
<%--},--%>
<%--dataType: "text",--%>
<%--}).done(function (res) {--%>
<%--$('#price_field').val(res);--%>
<%--}).fail(function (jqXHR, textStatus, errorThrown) {--%>
<%--alert("Address error");--%>
<%--});--%>
<%--});--%>
<%--</script>--%>




