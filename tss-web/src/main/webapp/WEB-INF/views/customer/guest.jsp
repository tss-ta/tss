<%--
  by Stanislav Zabielin
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="Dashboard">
        <meta name="keyword"
              content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

        <title>Guest Taxi Ordering</title>

        <!-- Bootstrap core CSS -->
        <link href="/resources/customer_assets/css/bootstrap.css"
              rel="stylesheet">
        <!--external css-->
        <link
            href="/resources/customer_assets/font-awesome/css/font-awesome.css"
            rel="stylesheet" />
        <link rel="stylesheet" type="text/css"
              href="/resources/customer_assets/js/bootstrap-datepicker/css/datepicker.css" />
        <link rel="stylesheet" type="text/css"
              href="/resources/customer_assets/js/bootstrap-daterangepicker/daterangepicker.css" />

        <!-- Custom styles for this template -->
        <link href="/resources/customer_assets/css/style.css" rel="stylesheet">
        <link href="/resources/customer_assets/css/style-responsive.css"
              rel="stylesheet">
        <link href="/resources/customer_assets/css/anytime.5.1.0.css"
              rel="stylesheet">
        <link href="/resources/customer_assets/css/bootstrap-select.css"
              rel="stylesheet">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
    </head>

    <body>

        <section id="container">
            <!-- **********************************************************************************************************************************************************
TOP BAR CONTENT & NOTIFICATIONS
*********************************************************************************************************************************************************** -->
            <!--header start-->
            <header class="header black-bg">
                <!--logo start-->
                <a href="index.html" class="logo"><b>Taxi Service System</b></a>
                <!--logo end-->
                <div class="top-menu">
                    <ul class="nav pull-right top-menu">
                        <li><a class="logout" href="/signout">Home</a></li>
                    </ul>
                </div>
            </header>
            <!--header end-->


            <!-- **********************************************************************************************************************************************************
MAIN CONTENT
*********************************************************************************************************************************************************** -->
            <!--main content start-->
            <section class="wrapper">
                <h3>
                    <i class="fa fa-angle-right"></i> Guest Taxi Order
                </h3>
                <form action="/guest/order" class="form-horizontal style-form"
                      method="post">
                    <c:if test="${not empty added }">
                        <c:if test="${added == 'success'}">
                            <div class="alert alert-success">
                                <b>Taxi Ordered!</b> Taxi was successfully ordered!
                            </div>
                        </c:if>
                    </c:if>
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h4 class="mb">
                                    <i class="fa fa-angle-right"></i> Personal Info
                                </h4>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Name</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="name" name="name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">E-Mail</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="email" name="email">
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
                                    <label class="col-sm-2 col-sm-2 control-label">Order
                                        Time</label>
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

                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h4 class="mb">
                                    <i class="fa fa-angle-right"></i> Book Your Taxi Now!
                                    <div
                                        style="display: block; margin-left: auto; margin-right: auto; text-align: center">
                                        <input type="button" class="btn btn-default"
                                               onclick="geoloc()" value="Find Me" /> <input type="button"
                                               class="btn btn-default" onclick="initialize()"
                                               value="Show Map" /> <input type="button"
                                               class="btn btn-default" onclick="showonmap()"
                                               value="Show on Map" />
                                    </div>
                                </h4>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">From :</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="fromAddr" name="fromAddr"
                                               value="Holosiivskyi Avenue, 12, Kyiv, Ukraine" />
                                        <p id="fromAddrMessage"></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">To :</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="toAddr" name="toAddr"
                                               value="Vasylya Zhukovs'koho Lane, 6, Kyiv, Ukraine" />
                                        <p id="toAddrMessage"></p>
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
                    <div class="row mt">
                        <div class="col-lg-12">
                            <div class="form-panel">
                                <h4 class="mb">
                                    <i class="fa fa-angle-right"></i> Additional Options
                                </h4>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Choose car type :</label>
                                    <select class="selectpicker" title ="Choose car type" name = "carType">
                                        <option></option>
                                        <option value="1">Economy class</option>
                                        <option value="2">Business class</option>
                                        <option value="3">Van</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Way of payment :</label>
                                    <select class="selectpicker" title ="Choose way of payment" name = "paymentType">
                                        <option></option>
                                        <option value="1">Cash</option>
                                        <option value="2">Mastercard</option>
                                        <option value="3">Visa</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Driver's gender :</label>
                                    <select class="selectpicker" title ="Choose driver's gender" name = "driverGender">
                                        <option></option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Music type :</label>
                                    <select class="selectpicker" title ="Choose music type" name = "musicType">
                                        <option></option>
                                        <option value="1">Rock</option>
                                        <option value="2">Classic</option>
                                        <option value="3">Jazz</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-2 col-sm-2 control-label">Other options :</label>
                                    <select class="selectpicker"multiple title ="Choose other options" name = "addOptions">
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
                        <div class="text-center">

                            <button class="btn btn-success btn-lg btn-block" type="submit">Order
                                Now</button>

                        </div>
                    </div>
                </form>
            </section>
            <!--/wrapper -->
            <!--main content end-->
            <!--footer start-->
            <footer class="site-footer">
                <div class="text-center">2015 A-Team</div>
            </footer>
            <!--footer end-->
        </section>

        <!-- js placed at the end of the document so the pages load faster -->
        <script src="/resources/customer_assets/js/jquery.js"></script>
        <script src="/resources/customer_assets/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript"
        src="/resources/customer_assets/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="/resources/customer_assets/js/jquery.scrollTo.min.js"></script>
        <script src="/resources/customer_assets/js/jquery.nicescroll.js"
        type="text/javascript"></script>


        <!--common script for all pages-->
        <script src="/resources/customer_assets/js/common-scripts.js"></script>

        <!--script for this page-->
        <script
        src="/resources/customer_assets/js/jquery-ui-1.9.2.custom.min.js"></script>

        <!--custom switch-->
        <script src="/resources/customer_assets/js/bootstrap-switch.js"></script>

        <!--custom tagsinput-->
        <script src="/resources/customer_assets/js/jquery.tagsinput.js"></script>

        <!--custom checkbox & radio-->

        <script type="text/javascript"
        src="/resources/customer_assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript"
        src="/resources/customer_assets/js/bootstrap-daterangepicker/date.js"></script>
        <script type="text/javascript"
        src="/resources/customer_assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>

        <script type="text/javascript"
        src="/resources/customer_assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>

        <script src="/resources/customer_assets/js/bootstrap-select.min.js"></script>

        <script src="/resources/customer_assets/js/form-component.js"></script>



        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript"
                src="http://maps.googleapis.com/maps/api/js?sensor=false">

        </script>

        <script type="text/javascript"
                src="/resources/customer_assets/js/map.js">

        </script>

        <script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
        <script>
                                                   //custom select box

                                                   $(function () {
                                                       $('select.styled').customSelect();
                                                   });
        </script>

        <script>
            AnyTime.picker("ordertime", {
                format: "%H:%i, %M %D",
                firstDOW: 1
            });
        </script>

    </body>
</html>
