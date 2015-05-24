<%--
  by Stanislav Zabielin
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Taxi Service System</title>
<link href="/resources/customer_assets/css/comments.css"
	rel="stylesheet">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/font-awesome.min.css" rel="stylesheet">
<link href="/resources/css/customer.css" rel="stylesheet">
<link href="/resources/customer_assets/css/anytime.5.1.0.css"
	rel="stylesheet">
<link href="/resources/customer_assets/css/bootstrap-select.css"
	rel="stylesheet">

<link href="/resources/img/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
    <link href="/resources/css/map.css" rel="stylesheet"/>
</head>

<body>

	<div id="wrapper">

		<div id="sidebar-wrapper">
			<%@ include file="partials/sidebar.jspf"%>
		</div>
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<%@ include file="partials/customer-header.jspf"%>

				<c:if test="${not empty errorMessage}">
					<div class="row row-fix">
						<div class="col-md-offset-1 col-md-10">
							<div class="alert alert-danger alert-dismissible text-center" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<i class="fa fa-exclamation"></i> ${errorMessage}
							</div>
						</div>
					</div>
				</c:if>

				<c:if test="${not empty successMessage}">
					<div class="row row-fix">
						<div class="col-md-offset-1 col-md-10">
							<div class="alert alert-success alert-dismissible text-center" role="alert">
								<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<i class="fa fa-exclamation"></i> ${successMessage}
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<jsp:include page="${pageContent}" />

		</div>
	</div>

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
            {type: "block", label: "Regular event" }
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



	<script>
		$('#pers_addr').on('change', function() {
			var txt = $("#pers_addr option:selected").text();
			$('#fromAddr').val(txt);
		})
		$('#pers_addr_to').on('change', function() {
			var txt = $("#pers_addr_to option:selected").text();
			$('#toAddr').val(txt);
		})
	</script>

    <script src="/resources/js/jqBootstrapValidation.js"></script>
    <%--<script>--%>
        <%--$(function () {--%>
            <%--$("input").not("[type=submit]").jqBootstrapValidation();--%>
            <%--//     $(".form-sign-error-msg").css("visibility", "visible");--%>
        <%--});--%>
    <%--</script>--%>
</body>
</html>
