<%-- 
    Document   : customer-mmgService
    Created on : 06.05.2015, 21:38:54
    Author     : Виктор
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row row-fix">
	<div class="col-md-offset-1 col-md-10">
		<div class="text-center">
			<h1>Meet my guest service</h1>
		</div>
	</div>
</div>
<br>
&nbsp;
<br>
<form id="submit_id" action="/customer/meetMyGuest"
	class="form-horizontal style-form" method="post">
	<div class="row mt bottom_line">
		<div class="control-group">
			<div class="col-md-6">
				<div class="col-lg-12">
					<div class="form-panel">
						<%@ include file="../../partials/order_time_buttons.jspf"%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 col-sm-2 control-label">Guest Name</label>
					<div class="col-sm-9">
						<input type="text" class="form-control" id="name" name="guestName" maxlength="60" minLength="2" required autofocus>
					</div>
				</div>
				<%@ include file="../../partials/from_addr.jspf"%>
				<%@ include file="../../partials/from_pers_addr.jspf"%>
				<%@ include file="../../partials/to_addr.jspf"%>
				<%@ include file="../../partials/to_pers_addr.jspf"%>
				<%@ include file="../../partials/price.jspf"%>
			</div>
			<div class="clearfix visible-xs-block"></div>
			<div class="col-md-6">
				<%@ include file="../../partials/map.jspf"%>
			</div>
		</div>

        <div class="col-lg-12">
            <%@ include file="../../partials/options.jspf"%>
            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                <button class="btn btn-success btn-lg btn-block col-lg-8 col-sm-8 col-xs-12" type="submit">Order Now</button>
            </div>
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
	var a = {
		"Ё" : "YO",
		"Й" : "I",
		"Ц" : "TS",
		"У" : "U",
		"К" : "K",
		"Е" : "E",
		"Н" : "N",
		"Г" : "G",
		"Ш" : "SH",
		"Щ" : "SCH",
		"З" : "Z",
		"Х" : "H",
		"Ъ" : "'",
		"ё" : "yo",
		"й" : "i",
		"ц" : "ts",
		"у" : "u",
		"к" : "k",
		"е" : "e",
		"н" : "n",
		"г" : "g",
		"ш" : "sh",
		"щ" : "sch",
		"з" : "z",
		"х" : "h",
		"ъ" : "'",
		"Ф" : "F",
		"Ы" : "I",
		"В" : "V",
		"А" : "a",
		"П" : "P",
		"Р" : "R",
		"О" : "O",
		"Л" : "L",
		"Д" : "D",
		"Ж" : "ZH",
		"Э" : "E",
		"ф" : "f",
		"ы" : "i",
		"в" : "v",
		"а" : "a",
		"п" : "p",
		"р" : "r",
		"о" : "o",
		"л" : "l",
		"д" : "d",
		"ж" : "zh",
		"э" : "e",
		"Я" : "Ya",
		"Ч" : "CH",
		"С" : "S",
		"М" : "M",
		"И" : "I",
		"Т" : "T",
		"Ь" : "'",
		"Б" : "B",
		"Ю" : "YU",
		"я" : "ya",
		"ч" : "ch",
		"с" : "s",
		"м" : "m",
		"и" : "i",
		"т" : "t",
		"ь" : "'",
		"б" : "b",
		"ю" : "yu"
	};

	function transliterate(word) {
		return word.split('').map(function(char) {
			return a[char] || char;
		}).join("");
	}
</script>

<script>
	$('#update_price').click(function() {
		$.ajax({
			type : "GET",
			url : "/price",
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
