<%-- 
    Author     : Stanislav Zabielin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Taxi Service System</title>
<link href="/resources/img/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<link href="/resources/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link href='http://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/responsiveslides.css">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="/resources/js/responsiveslides.min.js"></script>
<script>
	// You can also use "$(window).load(function() {"
	$(function() {

		// Slideshow 1
		$("#slider1").responsiveSlides({
			maxwidth : 1600,
			speed : 600
		});
	});
</script>
</head>
<body>
	<!-----start-header----->
	<div class="header">
		<!---start-wrap---->
		<div class="wrap">
			<!---start-top-header---->
			<div class="top-header">
				<div class="clear"></div>
			</div>
			<!---End-top-header---->
			<!----start-main-header----->
			<div class="main-header">
				<div class="logo">
					<a href="index.html"><img src="/resources/images/logo1.png"
						title="logo" /></a>
				</div>
				<div class="top-nav">
					<ul>
						<li class="active"><a href="#">Home</a></li>
						<li><a href="/signin.jsp">Sign In</a></li>
						<li><a href="/signup.jsp">Sign Up</a></li>
						<li><a href="#">Guest</a></li>
						<div class="clear"></div>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!----End-main-header----->
		</div>
	</div>
	<div class="clear"></div>
	<div class="wrap">
		<!---End-header--->
		<!--start-image-slider---->
		<div class="image-slider">
			<!-- Slideshow 1 -->
			<ul class="rslides" id="slider1">
				<li><img src="/resources/images/slider1.jpg" alt=""></li>
				<li><img src="/resources/images/slider2.jpg" alt=""></li>
				<li><img src="/resources/images/slider1.jpg" alt=""></li>
			</ul>
			<!-- Slideshow 2 -->
		</div>
		<!--End-image-slider---->
	</div>
	<!---start-content----->
	<div class="content">
		<div class="wrap">
			<div class="content-grids">
				<div class="grid">
					<h3 class="frist">FIND AN CAR</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua
						ut enim ad.</p>
					<a href="/signin.jsp">Sign In</a>
				</div>
				<div class="grid">
					<h3 class="second">CHALLENGE</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua
						ut enim ad.</p>
					<a href="/signup.jsp">Sign Up</a>
				</div>
				<div class="grid last-grid">
					<h3 class="second">CHALLENGE</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua
						ut enim ad.</p>
					<a href="/signin.jsp">Sign In</a>
				</div>
				<div class="grid">
					<h3 class="last">FIND AN CAR</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
						sed do eiusmod tempor incididunt ut labore et dolore magna aliqua
						ut enim ad.</p>
					<a href="#">Guest</a>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
			<!---start-footer-grids--->
			<div class="footer-grids">
				<div class="footer-grid2 last-footer-grid">
					<span> <%@ include file="partials/footer.jspf"%></span>
				</div>
				<div class="clear"></div>
			</div>
			<!---End-footer-grids--->
		</div>
	</div>
	<!---End-wrap---->
</body>
</html>

