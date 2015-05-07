<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
	<div class="col-md-offset-1 col-md-10">
		<div class="text-center">
			<h1>Order Taxi</h1>
		</div>
	</div>
</div>



<form id="submit_id" action="/customer/order"
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
								name="fromAddr" value="Ukraine, Kiev, Pobedy 55" maxlength="140" />
						</div>
						<br>&nbsp;<br>
						<p id="fromAddrMessage"></p>
						<c:if test="${not empty personal_addr}">
							<select class="selectpicker" title="Choose from personal list"
								name="pers_addr" id="pers_addr">
								<option></option>
								<c:forEach items="${personal_addr}" var="list" varStatus="loop">
									<option value="${list.addr}">${list.addr}</option>
								</c:forEach>
							</select>
						</c:if>
						<button type="submit" class="btn btn-default" id="addFrom"
							name="addFrom">Add Current To List</button>
						<button type="submit" class="btn btn-default" id="deleteFrom"
							name="deleteFrom">Delete Current</button>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">To :</label>
					<div class="col-sm-10">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="toAddr" name="toAddr"
								value="Ukraine, Kiev" maxlength="140" />
						</div>
						<br>&nbsp;<br>
						<p id="toAddrMessage"></p>
						<c:if test="${not empty personal_addr}">
							<select class="selectpicker" title="Choose from personal list"
								name="pers_addr_to" id="pers_addr_to">
								<option></option>
								<c:forEach items="${personal_addr}" var="list" varStatus="loop">
									<option value="${list.addr}">${list.addr}</option>
								</c:forEach>
							</select>
						</c:if>
						<button type="submit" class="btn btn-default" id="addTo"
							name="addTo">Add Current To List</button>
						<button type="submit" class="btn btn-default" id="deleteTo"
							name="deleteTo">Delete Current</button>
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
						<!--						<option></option>-->
						<option value="1">Economy class</option>
						<option value="2">Business class</option>
						<option value="3">Van</option>
						<option value="4">Cargo</option>
					</select>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Way of
						payment :</label> <select class="selectpicker"
						title="Choose way of payment" name="paymentType">
						<!--						<option></option>-->
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
					<label class="col-sm-2 col-sm-2 control-label">Music type :</label>
					<select class="selectpicker" title="Choose music type"
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
		</div>
		<!-- /form-panel -->
	</div>
	<!-- /col-lg-12 -->
	<div class="row">
		<div class="col-lg-12 text-center">
			<div class="text-center">
				<input type="hidden" id="id_rout_dist" name="route_distance"
					value="" />
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
var a = {"Ё":"YO","Й":"I","Ц":"TS","У":"U","К":"K","Е":"E","Н":"N","Г":"G","Ш":"SH","Щ":"SCH","З":"Z","Х":"H","Ъ":"'","ё":"yo","й":"i","ц":"ts","у":"u","к":"k","е":"e","н":"n","г":"g","ш":"sh","щ":"sch","з":"z","х":"h","ъ":"'","Ф":"F","Ы":"I","В":"V","А":"a","П":"P","Р":"R","О":"O","Л":"L","Д":"D","Ж":"ZH","Э":"E","ф":"f","ы":"i","в":"v","а":"a","п":"p","р":"r","о":"o","л":"l","д":"d","ж":"zh","э":"e","Я":"Ya","Ч":"CH","С":"S","М":"M","И":"I","Т":"T","Ь":"'","Б":"B","Ю":"YU","я":"ya","ч":"ch","с":"s","м":"m","и":"i","т":"t","ь":"'","б":"b","ю":"yu"};

function transliterate(word){
  return word.split('').map(function (char) { 
    return a[char] || char; 
  }).join("");
} </script>

<script>
	$('#update_price').click(function() {
		$.ajax({
			type : "GET",
			url : "http://localhost:8080/price",
			data : {
				fromAddr : transliterate($("#fromAddr").val()),
				toAddr : transliterate($("#toAddr").val()),
				ordertime : $("#ordertime").val()
			},
			dataType : "text",
		}).done(function(res) {
			$('#price_field').val(res);
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("AJAX call failed: " + textStatus + ", " + errorThrown);
		});
	});
</script>
