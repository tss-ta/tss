
<form action="/guest/order" class="form-horizontal style-form"
	method="post">
	<div class="row mt bottom_line">
		<div class="form-group">
			<div class="col-md-6">
				<div class="col-lg-12">
					<div class="form-panel">
						<div class="form-group">
							<div class="col-sm-12">
								<div class="text-center">
									<h3 class="mb">
										<i class="fa"></i> Order Taxi Now!
									</h3>
								</div>
							</div>
						</div>
						<%@ include file="personal_info.jspf"%>
						<%@ include file="order_time_buttons.jspf"%>
					</div>
				</div>
				<br>&nbsp;<br>
				<%@ include file="from_addr.jspf"%>
				<%@ include file="to_addr.jspf"%>
				<%@ include file="price.jspf"%>
			</div>
			<div class="col-md-6">
				<br>&nbsp;<br>
				<%@ include file="map.jspf"%>
			</div>
		</div>
		<div class="col-lg-12">
			<%@ include file="options.jspf"%>
			<div class="col-lg-12 text-center">
				<button class="btn btn-success btn-lg btn-block" type="submit">Order
					Now</button>
			</div>
		</div>
	</div>
</form>

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
				fromAddr : transliterate($("#fromAddr").val()),
				toAddr : transliterate($("#toAddr").val()),
				ordertime : $("#ordertime").val(),
				addOptions : $("#addOptions").val(),
				carType : $("#carType").val()
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
