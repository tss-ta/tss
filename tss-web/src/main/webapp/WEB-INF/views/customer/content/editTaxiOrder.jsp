<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
	<div class="col-md-offset-1 col-md-10">
		<div class="text-center">
			<h1>Order Taxi</h1>
		</div>
	</div>
</div>



<form action="/customer/edit" class="form-horizontal style-form"
	method="post">
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
                                                       class="form-control" value="${toh.getOrderTime()}"/>
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
						<input type="button" class="btn btn-default" onclick="geoloc()"
							value="Find Me" /> <input type="button" class="btn btn-default"
							onclick="initialize()" value="Show Map" /> <input type="button"
							class="btn btn-default" onclick="showonmap()" value="Show on Map" />
					</div>
				</h4>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">From :</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="fromAddr"
							name="fromAddr" value="${toh.getFromAddr()}" />
						<p id="fromAddrMessage"></p>
						<div class="text-center">
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
								name="addFrom">Add Current</button>
							<button type="submit" class="btn btn-default" id="deleteFrom"
								name="deleteFrom">Delete Current</button>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">To :</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="toAddr" name="toAddr" size="50"
							value="${toh.getToAddr()}" />
						<p id="toAddrMessage"></p>
						<div class="text-center">
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
								name="addTo">Add Current</button>
							<button type="submit" class="btn btn-default" id="deleteTo"
								name="deleteTo">Delete Current</button>
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

		<!-- /form-panel -->
	</div>
	<!-- /col-lg-12 -->
	<div class="text-center">

		<button class="btn btn-success btn-lg btn-block" type="submit">Update</button>

	</div>
</form>



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
		format : "%H:%i, %M %D",
		firstDOW : 1
	});
</script>