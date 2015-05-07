<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
	<div class="col-md-offset-1 col-md-10">
		<div class="text-center">
			<h1>Celebration Service</h1>
		</div>
	</div>
</div>



<form id="submit_id" action="/customer/selebrService"
	class="form-horizontal style-form" method="post">
	<div class="row mt bottom_line">
		<div class="col-lg-12">
			<div class="form-panel">
				<h4 class="mb">
					<i class="fa"></i> Specify Time
				</h4>
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
								name="fromAddr" value="Ukraine, Kiev, Pobedy 55" />
						</div>
						<br>&nbsp;<br>
						<p id="fromAddrMessage"></p>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">How many
						drivers?</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="driversAmountId"
							name="driversAmount" placeholder="Insert amount here" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">How long
						would you selebrate?</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="duration"
							name="duration" placeholder="Insert duration here" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<div class="text-center">
							<br>&nbsp;<br>
							<div class="col-sm-6">
								<input id="price_field" class="form-control" name="price"
									type="text" placeholder="Price will be shown here" readonly>
								<br>
								<button id="update_price" name="update_price" type="button"
									class="btn btn-default">Calculate Price</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<input type="hidden" id="fromc" name="" value="" />

			<div class="form-group" style="text-align: center;">
				<div id="map_canvas"
					style="width: 45em; height: 25em; display: block; margin-left: auto; margin-right: auto;">
				</div>
			</div>
		</div>
		<!-- col-lg-12-->
	</div>
	</div>

	<!-- /col-lg-12 -->
	<div class="text-center">
		<input type="hidden" id="id_rout_dist" name="route_distance" value="" />
		<button class="btn btn-success btn-lg btn-block" type="submit">Order
			Now</button>

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
