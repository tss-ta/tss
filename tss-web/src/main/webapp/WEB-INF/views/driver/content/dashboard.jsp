<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
	<div class="col-lg-9 main-chart">
		<div class="text-center">
			<h1>Dashboard</h1>
		</div>
		<div class="row mt bottom_line">
			<div class="col-lg-12">
				<h4>
					<i class="fa"></i> Your Taxi Orders
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
								<th>Action</th>
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
									<td class="col-md-1"><c:if test="${(list.status==0) || (list.status==1)}">
											<form action="/driver/todetails" method="get">
												<input type="hidden" name="taxiOrderId" value="${list.getId()}">
												<button type="submit" class="btn btn-default">View Details</button>
											</form>
										</c:if> <c:if test="${(list.status==2)}">
											<form action="/driver/inprogress" method="post">
												<input type="hidden" name="taxiOrderId" value="${list.getId()}">
												<button type="submit" class="btn btn-default">Set In Progress</button>
											</form>
										</c:if> <c:if test="${(list.status==4)}">
											<form action="/driver/completed" method="post">
												<input type="hidden" name="taxiOrderId" value="${list.getId()}">
												<button type="submit" class="btn btn-default">Set Completed</button>
											</form>
										</c:if></td>
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


