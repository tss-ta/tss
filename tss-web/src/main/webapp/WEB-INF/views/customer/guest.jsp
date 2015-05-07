<%--
  by Stanislav Zabielin
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Taxi Service System</title>

        <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
        <link href="/resources/css/font-awesome.min.css" rel="stylesheet">
        <link href="/resources/css/customer.css" rel="stylesheet">
        <link href="/resources/customer_assets/css/anytime.5.1.0.css"
              rel="stylesheet">
        <link href="/resources/customer_assets/css/bootstrap-select.css"
              rel="stylesheet">
        <link href="/resources/img/favicon.ico" rel="shortcut icon"
              type="image/x-icon" />
    </head>

    <body>

        <div id="sidebar-wrapper"
             style="display: block !important; height: auto !important; padding-bottom: 0; overflow: visible !important;">
            <ul class="sidebar-nav">
                <li class="sidebar-brand"><a href="/">Taxi Service</a></li>
            </ul>
        </div>

        <div id="page-content-wrapper">
            <div class="container-fluid">
                <%@ include file="partials/guest-header.jspf"%>
            </div>
            <div class="text-center">
                <h1>Guest Taxi Order</h1>
            </div>
            <form action="/guest/order" class="form-horizontal style-form"
                  method="post">
                <div class="row mt bottom_line">
                    <div class="col-lg-12">
                        <div class="form-panel">
                            <h4 class="mb">
                                <i class="fa"></i> Personal Info
                            </h4>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Name</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="name" name="name" maxlength="40" required autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">E-Mail</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" id="email" name="email" maxlength="40" required>
                                </div>
                            </div>
                            <c:if test="${not empty error }">
                                <c:if test="${error == 'email'}">
                                    <div class="alert alert-danger">
                                        <b>Error!</b> E-mail is already taken. Please choose different
                                        one.
                                    </div>
                                </c:if>
                            </c:if>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Order Time</label>
                                <div class="col-sm-6">
                                    <input type="text" id="ordertime" name="ordertime"
                                           class="form-control" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- col-lg-12-->
                </div>
                <!-- /row -->

                <div class="row mt bottom_line">
                    <div class="col-lg-12">
                        <div class="form-panel">
                            <h4 class="mb">
                                <i class="fa"></i> Book Your Taxi Now!
                                <div
                                    style="display: block; margin-left: auto; margin-right: auto; text-align: center">

                                </div>
                            </h4>
                            <div class="text-center">
                                <input type="button" class="btn btn-default" onclick="geoloc()"
                                       value="Find Me" /> <input type="button" class="btn btn-default"
                                       onclick="showonmap()" value="Show on Map" />
                            </div>
                            <br>&nbsp;<br>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">From :</label>
                                <div class="col-sm-10">
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="fromAddr"
                                               name="fromAddr" value="Ukraine, Kiev, Pobedy 55" maxlength="140"/>
                                    </div>	
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">To :</label>
                                <div class="col-sm-10">
                                    <div class="col-sm-6">
                                        <input type="text" class="form-control" id="toAddr"
                                               name="toAddr" value="Ukraine, Kiev" maxlength="140"/>
                                    </div>							
                                    <br>&nbsp;<br>
                                    <div class="col-sm-6">
                                        <input id="price_field" class="form-control" name="price"
                                               type="text" placeholder="Price will be shown here" readonly>
                                        <div class="text-center">
                                            <br>
                                            <button id="update_price" name="update_price" type="button"
                                                    class="btn btn-default">Calculate Price</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <input type="hidden" id="fromc" name="" value="" /> <input
                                type="hidden" id="toc" name="" value="" />

                            <div class="form-group" style="text-align: center;">
                                <div id="map_canvas"
                                     style="width: 45em; height: 25em; display: block; margin-left: auto; margin-right: auto;">
                                </div>
                            </div>
                        </div>
                        <!-- col-lg-12-->
                    </div>
                </div>

                <!-- INPUT MESSAGES -->
                <div class="row mt bottom_line">
                    <div class="col-lg-12">
                        <div class="form-panel">
                            <h4 class="mb">
                                <i class="fa"></i> Additional Options
                            </h4>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Choose car
                                    type :</label> <select class="selectpicker" title="Choose car type"
                                                       name="carType">
                                    <!--								<option></option>-->
                                    <option value="1">Economy class</option>
                                    <option value="2">Business class</option>
                                    <option value="3">Van</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Way of
                                    payment :</label> <select class="selectpicker"
                                                          title="Choose way of payment" name="paymentType">
                                    <!--								<option></option>-->
                                    <option value="1">Cash</option>
                                    <option value="2">Mastercard</option>
                                    <option value="3">Visa</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Driver's
                                    gender :</label> <select class="selectpicker"
                                                         title="Choose driver's gender" name="driverGender">
                                    <option></option>
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Music type
                                    :</label> <select class="selectpicker" title="Choose music type"
                                                  name="musicType">
                                    <option></option>
                                    <option value="1">Rock</option>
                                    <option value="2">Classic</option>
                                    <option value="3">Jazz</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">Other
                                    options :</label> <select class="selectpicker" multiple
                                                          title="Choose other options" name="addOptions">
                                    <option value="wifi">WI-FI</option>
                                    <option value="animal">Animal transportation</option>
                                    <option value="nosmoke">Non-smoking driver</option>
                                    <option value="conditioner">Air-conditioner</option>
                                </select>
                            </div>
                        </div>
                        <!-- /form-panel -->
                    </div>
                    <!-- /col-lg-12 -->
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <button class="btn btn-success btn-lg btn-block" type="submit">Order
                                Now</button>
                        </div>
                    </div>
                </div>
            </form>

            <!--/wrapper -->
            <!--main content end-->
            <!--footer start-->
        </div>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="/resources/customer_assets/js/jquery-1.8.3.min.js"></script>
        <script src="/resources/customer_assets/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript"
        src="/resources/customer_assets/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="/resources/customer_assets/js/jquery.scrollTo.min.js"></script>
        <script src="/resources/customer_assets/js/jquery.nicescroll.js"
        type="text/javascript"></script>
        <script src="/resources/customer_assets/js/jquery.sparkline.js"></script>


        <!--common script for all pages-->
        <script src="/resources/customer_assets/js/common-scripts.js"></script>

        <script type="text/javascript"
        src="/resources/customer_assets/js/gritter/js/jquery.gritter.js"></script>
        <script type="text/javascript"
        src="/resources/customer_assets/js/gritter-conf.js"></script>

        <!--script for this page-->
        <script src="/resources/customer_assets/js/sparkline-chart.js"></script>
        <script src="/resources/customer_assets/js/zabuto_calendar.js"></script>


        <script type="text/javascript"
        src="/resources/customer_assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
        <script src="/resources/customer_assets/js/bootstrap-select.min.js"></script>


        <script src="/resources/customer_assets/js/form-component.js"></script>

        <script type="application/javascript">
















            $(document).ready(function () {
            $("#date-popover").popover({html: true, trigger: "manual"});
            $("#date-popover").hide();
            $("#date-popover").click(function (e) {
            $(this).hide();
            });

            $("#my-calendar").zabuto_calendar({
            action: function () {
            return myDateFunction(this.id, false);
            },
            action_nav: function () {
            return myNavFunction(this.id);
            },
            ajax: {
            url: "show_data.php?action=1",
            modal: true
            },
            legend: [
            {type: "text", label: "Special event", badge: "00"},
            {type: "block", label: "Regular event", }
            ]
            });
            });


            function myNavFunction(id) {
            $("#date-popover").hide();
            var nav = $("#" + id).data("navigation");
            var to = $("#" + id).data("to");
            console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
            }















        </script>

        <script src="/resources/customer_assets/js/form-component.js"></script>



        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;language=en-GB">

        </script>

        <script type="text/javascript"
                src="/resources/customer_assets/js/map.js">

        </script>

        <script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
        <script>
            $(function() {
            window.onload = function() {
            initialize();
            showonmap();
            }
            });
            //custom select box

            $(function() {
            $('select.styled').customSelect();
            });
        </script>

        <script>
            AnyTime.picker("ordertime", {
            format : "%H:%i, %d %m %Y",
            firstDOW : 1
            });
        </script>

        <script>
            $('#update_price').click(function() {
            $.ajax({
            type : "GET",
            url : "http://localhost:8080/price",
            data : {
            fromAddr : $("#fromAddr").val(),
            toAddr : $("#toAddr").val(),
            ordertime: $("#ordertime").val()
            },
            dataType : "text",
            }).done(function(res) {
            $('#price_field').val(res);
            }).fail(function(jqXHR, textStatus, errorThrown) {
            alert("AJAX call failed: " + textStatus + ", " + errorThrown);
            });
            });
        </script>



    </body>
</html>
