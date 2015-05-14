<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
	<div class="col-lg-9 main-chart">
		<div class="text-center">
			<h1>Orders History</h1>
		</div>
		<div class="row mt bottom_line">
			<div class="col-lg-12">
				<h4>
					<i class="fa"></i> Completed Taxi Orders
				</h4>
				<section id="unseen">
					<table class="table table-bordered table-striped table-condensed">
						<thead>
							<tr>
								<th>Booking Time</th>
								<th>Order Time</th>
								<th>Status</th>
								<th>From Address</th>
								<th>To Address</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${history}" var="list">
								<tr>
									<td>${list.bookingTime}</td>
									<td>${list.orderTime}</td>
									<td>${list.strStatus}</td>
									<td>${list.fromAddr}</td>
									<td>${list.toAddr}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</section>
				<div class="text-center">
					<form action="/driver/dashboard"
						class="form-horizontal style-form" method="get">
						<div class="btn-group">
							<button type="submit" class="btn btn-default" id="previous "
								name="previous">Previous</button>
							<button type="submit" class="btn btn-default" id="next"
								name="next">Next</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>


