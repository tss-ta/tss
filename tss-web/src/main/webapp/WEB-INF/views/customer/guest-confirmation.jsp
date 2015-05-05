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
			<div class="row mt bottom_line">
				<div class="text-center">
						<h1>Taxi was successfully ordered!</h1>
					<h3>
						Your TaxiOrder ID is = <b style="color: crimson">${taxiOrderId}</b>
					</h3>
				</div>
			</div>
		</div>
		<!--/wrapper -->
		<!--main content end-->
		<!--footer start-->
	</div>

	<!-- js placed at the end of the document so the pages load faster -->
	<script src="/resources/customer_assets/js/jquery.js"></script>
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
		src="http://maps.googleapis.com/maps/api/js?sensor=false">
		
	</script>

	<script type="text/javascript"
		src="/resources/customer_assets/js/map.js">
		
	</script>

	<script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
	<script>
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


</body>
</html>
