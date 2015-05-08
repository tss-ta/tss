<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="row">
	<div class="col-lg-9 main-chart">
		<div class="container">
			<div class="text-center">
				<div class="well">
					<h4>Comments for Taxi Order ID = ${taxiOrder.id}</h4>
					<form id="submit_id" action="/customer/comment"
						class="form-horizontal style-form" method="get">
						<div class="input-group">
							<input type="text" id="userComment" name="userComment"
								class="form-control input-sm chat-input"
								placeholder="Write your message here..." /> <span
								class="input-group-btn">
								<button type="submit" class="btn btn-default" id="addComment"
									name="addComment">Add Comment</button>
							</span>
						</div>
						<hr data-brackets-id="12673">
						<ul data-brackets-id="12674" id="sortable"
							class="list-unstyled ui-sortable">
							<strong class="pull-left primary-font">${taxiOrder.contactsId.username}</strong>
							</br>
							<li class="ui-state-default">${taxiOrder.comment}</li>
							</br>
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
