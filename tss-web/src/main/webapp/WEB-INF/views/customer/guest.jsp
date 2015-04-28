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
						</div>
					</div>
					<!-- col-lg-12-->
				</div>
				<!-- /row -->

				<!-- BASIC FORM ELELEMNTS -->
				<div class="row mt">
					<div class="col-lg-12">
						<div class="form-panel">
							<h4 class="mb">
								<i class="fa fa-angle-right"></i> Where do you want to be picked
								up from?
							</h4>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">Street
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="fromstreet">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">House
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="fromhouse">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">ZIP Code</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="fromzip">
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
								<i class="fa fa-angle-right"></i> Where are you going to?
							</h4>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">Street
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="tostreet">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">House
									Name</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="tohouse">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">ZIP Code</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id=tozip>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 col-sm-2 control-label">Order
									Now?</label>
								<div class="col-sm-6">
									<input type="checkbox" checked="" data-toggle="switch"
										id="ordernow" />
								</div>
							</div>
						</div>
						<!-- col-lg-12-->
					</div>
					<!-- /row -->

					<!-- INPUT MESSAGES -->
					<div class="row mt">
						<div class="col-lg-12">
							<div class="form-panel">
								<h4 class="mb">
									<i class="fa fa-angle-right"></i> Additional Options
								</h4>
								<div class="checkbox">
									<label> <input type="checkbox" value=""> Option
										one is this and that&mdash;be sure to include why it's great
									</label>
								</div>

								<div class="radio">
									<label> <input type="radio" name="optionsRadios"
										id="optionsRadios1" value="option1" checked> Option
										one is this and that&mdash;be sure to include why it's great
									</label>
								</div>
								<div class="radio">
									<label> <input type="radio" name="optionsRadios"
										id="optionsRadios2" value="option2"> Option two can be
										something else and selecting it will deselect option one
									</label>
								</div>

								<hr>
								<label class="checkbox-inline"> <input type="checkbox"
									id="inlineCheckbox1" value="option1"> 1
								</label> <label class="checkbox-inline"> <input type="checkbox"
									id="inlineCheckbox2" value="option2"> 2
								</label> <label class="checkbox-inline"> <input type="checkbox"
									id="inlineCheckbox3" value="option3"> 3
								</label>

								<hr>
								<select class="form-control">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select> <br> <select multiple class="form-control">
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
								</select>
							</div>
							<!-- /form-panel -->
						</div>
						<!-- /col-lg-12 -->
						<div class="text-center">

							<button class="btn btn-success btn-lg btn-block" type="submit">Order
								Now</button>

						</div>
			</form>
		</section>
		<! --/wrapper --> <!--main content end--> <!--footer start--> <footer
			class="site-footer">
			<div class="text-center">2015 A-Team</div>
		</footer> <!--footer end-->
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


	<script src="/resources/customer_assets/js/form-component.js"></script>


	<script>
		//custom select box

		$(function() {
			$('select.styled').customSelect();
		});
	</script>

</body>
</html>