<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
	<div class="col-md-offset-1 col-md-10">
		<div class="text-center">
			<h1>Celebration Service</h1>
		</div>
	</div>
</div>
<br>
&nbsp;
<br>
<form id="submit_id" action="/customer/selebrService"
	class="form-horizontal style-form" method="post">
	<div class="row mt bottom_line">
		<div class="form-group">
			<div class="col-md-6">
				<div class="col-lg-12">
					<div class="form-panel">
						<%@ include file="../../partials/order_time_buttons.jspf"%>
					</div>
				</div>
				<br>&nbsp;<br>
				<%@ include file="../../partials/from_addr.jspf"%>
				<%@ include file="../../partials/from_pers_addr.jspf"%>
				<div class="form-group">
					<label class="col-sm-6 col-sm-6 control-label">How many
						drivers?</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="driversAmountId"
							name="driversAmount" placeholder="Insert amount here" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-6 col-sm-6 control-label">How long
						would you celebrate?</label>
					<div class="col-sm-6">
						<input type="text" class="form-control" id="duration"
							name="duration" placeholder="Insert duration here" />
					</div>
				</div>
				<%@ include file="../../partials/price.jspf"%>
			</div>
			<div class="col-md-6">
				<%@ include file="../../partials/map.jspf"%>
			</div>
		</div>
		<div class="col-lg-12">
			<!-- /col-lg-12 -->
			<div class="col-lg-12 text-center">
				<button class="btn btn-success btn-lg btn-block" type="submit">Order
					Now</button>
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
	src="/resources/customer_assets/js/map.js">
	
</script>


<script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
<script>
	//	$(function() {
	//		$('#submit_id').submit(function(event) {
	//			event.preventDefault();
	//			initialize();
	//			showonmap();
	//			var distance = $('#id_route_dist').val();
	//
	//			if(distance != null && distance != "") {
	//				$('#submit_id').submit();
	//				distance.val("");
	//			}
	//			return true;
	//		});
	//	});

	$(function() {
		window.onload = function() {
			initialize();
			showonmap();
		}
	});

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
		//      type : "GET",
		//      url : "http://localhost:8080/price",
		//      data : {
		//        fromAddr : $("#fromAddr").val(),
		//        toAddr : $("#toAddr").val(),
		//        ordertime : $("#ordertime").val()
		//      },
		//      dataType : "text"
		//    }).done(function(res) {
		//      $('#price_field').val(res);
		//    }).fail(function(jqXHR, textStatus, errorThrown) {
		//      alert("AJAX call failed: " + textStatus + ", " + errorThrown);
		});
	});
</script>
