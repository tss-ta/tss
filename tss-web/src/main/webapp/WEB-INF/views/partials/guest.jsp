
<form action="/guest/order" class="form-horizontal style-form"
	method="post">
	<div class="row mt bottom_line">
		<div class="control-group">
			<div class="col-md-4 col-lg-6">
				<div class="col-lg-12">
					<div class="form-panel">
						<div class="form-group">
							<%--<div class="col-sm-12">--%>
								<div class="text-center">
									<h3 class="mb">
										Order Taxi Now!
									</h3>
								</div>
							<%--</div>--%>
						</div>
						<%@ include file="personal_info.jspf"%>
						<%@ include file="order_time_buttons.jspf"%>
                        <br/>
                        <%@ include file="from_addr.jspf"%>
                        <%@ include file="to_addr.jspf"%>
                        <br/>
                        <%@ include file="price.jspf"%>
					</div>
				</div>



                <p class="help-block error-msg  text-center"></p>
			</div>
			<div class="clearfix visible-xs-block"></div>
			<div class="col-md-8 col-lg-6 text-right">
				<br>&nbsp;<br>
                <link href="/resources/css/map-guest.css" rel="stylesheet"/>
				<%@ include file="map.jspf"%>
			</div>

            <br/>
		</div>

        <div class="col-md-12">
            <%@ include file="options.jspf"%>
            <div class="form-group col-md-4 col-sm-6 col-xs-12">
                <button class="btn btn-success btn-lg btn-block col-lg-8 col-sm-8 col-xs-12" type="submit">Order Now</button>
            </div>
        </div>
	</div>
</form>


<script src="/resources/customer_assets/js/form-component.js"></script>


<script src="/resources/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript"
	src="http://maps.googleapis.com/maps/api/js?sensor=false&language=en-GB">

</script>



<script type="text/javascript"
	src="/resources/customer_assets/js/map.js">

</script>
<script src="/resources/js/jqBootstrapValidation.js"></script>

<script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
<script>
    $("input").not("[type=submit]").jqBootstrapValidation();
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
    format: "%H:%i, %d %m %Y",
    earliest: new Date(),
    firstDOW: 1
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
			dataType : "text"
		}).done(function(res) {
			$('#price_field').val(res);
		}).fail(function(jqXHR, textStatus, errorThrown) {
			alert("Address Error. Please check your address.");
		});
	});
</script>

