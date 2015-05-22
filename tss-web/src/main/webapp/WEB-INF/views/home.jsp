<%-- 
    Author     : Stanislav Zabielin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Taxi Service System</title>
<link href="/resources/img/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link href="/resources/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href='http://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>
<%--<link rel="stylesheet" href="css/responsiveslides.css">--%>
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="/resources/css/customer.css" rel="stylesheet">
<link href="/resources/customer_assets/css/anytime.5.1.0.css"
	rel="stylesheet">
<link href="/resources/customer_assets/css/bootstrap-select.css"
	rel="stylesheet">
</head>
<body>
	<!-----start-header----->
	<div class="header">
		<!---start-wrap---->
		<div class="wrap">
			<!---start-top-header---->
			<div class="top-header">
				<div class="clear"></div>
			</div>
			<!---End-top-header---->
			<!----start-main-header----->
			<div class="main-header">
				<div class="logo">
					<a href="index.html"><img src="/resources/images/logo1.png"
						title="logo" /></a>
				</div>
				<div class="top-nav">
					<ul>
						<li class="active"><a href="#">Home</a></li>
						<sec:authorize access="hasRole('ADMIN')">
							<li><a href="/admin?menu=dashboard&action=view">Dashboard</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('CUSTOMER')">
							<li><a href="/customer">Dashboard</a></li>
						</sec:authorize>
						<sec:authorize access="hasRole('DRIVER')">
							<li><a href="/driver">Dashboard</a></li>
						</sec:authorize>
						<sec:authorize access="isAnonymous()">
							<li><a href="/signin.jsp">Sign In</a></li>
							<li><a href="/signup.jsp">Sign Up</a></li>
							<li><a href="/track">Track Order</a></li>
						</sec:authorize>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!----End-main-header----->
		</div>
	</div>
	<div class="clear"></div>
	<jsp:include page="partials/guest.jsp" />
	<div class="content">
		<div class="wrap">
			<!---start-footer-grids--->
			<div class="footer-grids">
				<div class="footer-grid2 last-footer-grid">
					<span> EMail: team.a.taxi@gmail.com <br> <%@ include
							file="partials/footer.jspf"%></span>
				</div>
				<div class="clear"></div>
			</div>
			<!---End-footer-grids--->
		</div>
	</div>
	<!---End-wrap---->
	<!-- js placed at the end of the document so the pages load faster -->
	<script src="/resources/customer_assets/js/jquery-1.8.3.min.js"></script>
	<script src="/resources/customer_assets/js/bootstrap.min.js"></script>
	<script class="include" type="text/javascript"
		src="/resources/customer_assets/js/jquery.dcjqaccordion.2.7.js"></script>
	<script src="/resources/customer_assets/js/jquery.scrollTo.min.js"></script>
	<%--<script src="/resources/customer_assets/js/jquery.nicescroll.js"--%>
		<%--type="text/javascript"></script>--%>
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
</body>
</html>

