<%--
  by Stanislav Zabielin
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="Dashboard">

	<title>User Dashboard</title>

	<!-- Bootstrap core CSS -->
	<link href="/resources/customer_assets/css/bootstrap.css" rel="stylesheet">
	<!--external css-->
	<link href="/resources/customer_assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="/resources/customer_assets/css/zabuto_calendar.css">
	<link rel="stylesheet" type="text/css" href="/resources/customer_assets/js/gritter/css/jquery.gritter.css" />
	<link rel="stylesheet" type="text/css" href="/resources/customer_assets/lineicons/style.css">    

	<!-- Custom styles for this template -->
	<link href="/resources/customer_assets/css/style.css" rel="stylesheet">
	<link href="/resources/customer_assets/css/style-responsive.css" rel="stylesheet">

	<script src="/resources/customer_assets/js/chart-master/Chart.js"></script>

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
  </head>

  <body>

  	<section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <header class="header black-bg">
      	<div class="sidebar-toggle-box">
      		<div class="fa fa-bars tooltips" data-placement="right" data-original-title="Toggle Navigation"></div>
      	</div>
      	<!--logo start-->
      	<a href="index.html" class="logo"><b>Taxi Service System</b></a>
      	<!--logo end-->
      	<div class="nav notify-row" id="top_menu">
      	</div>
      	<div class="top-menu">
      		<ul class="nav pull-right top-menu">
      			<li><a class="logout" href="/signout"> Signout</a></li>
      		</ul>
      	</div>
      </header>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
      	<div id="sidebar"  class="nav-collapse ">
      		<!-- sidebar menu start-->
      		<ul class="sidebar-menu" id="nav-accordion">

      			<p class="centered"><a href="/customer"><img src="/resources/customer_assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
      			<h5 class="centered">User Dashboard</h5>

      			<li class="mt">
      				<a class="active" href="/customer">
      					<i class="fa fa-dashboard"></i>
      					<span>Dashboard</span>
      				</a>
      			</li>

      			<li class="sub-menu">
      				<a href="/ordertaxi">
      					<i class="fa fa-dashboard"></i>
      					<span>Order Taxi</span>
      				</a>
      			</li>

      			<li class="sub-menu">
      				<a href="index.html">
      					<i class="fa fa-dashboard"></i>
      					<span>Orders History</span>
      				</a>
      			</li>

      		</ul>
      		<!-- sidebar menu end-->
      	</div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
      	<section class="wrapper">

      		<div class="row">
      			<div class="col-lg-9 main-chart">

      				<div class="row mt">
      					<div class="col-lg-12">
      						<div class="content-panel">
      						<h4><i class="fa fa-angle-right"></i> Your Orders History</h4>
      							<section id="unseen">
      								<table class="table table-bordered table-striped table-condensed">
      									<thead>
      										<tr>
      											<th>Code</th>
      											<th>Company</th>
      											<th class="numeric">Price</th>
      											<th class="numeric">Change</th>
      											<th class="numeric">Change %</th>
      											<th class="numeric">Open</th>
      											<th class="numeric">High</th>
      											<th class="numeric">Low</th>
      											<th class="numeric">Volume</th>
      										</tr>
      									</thead>
      									<tbody>
      										<tr>
      											<td>AAC</td>
      											<td>AUSTRALIAN AGRICULTURAL COMPANY LIMITED.</td>
      											<td class="numeric">$1.38</td>
      											<td class="numeric">-0.01</td>
      											<td class="numeric">-0.36%</td>
      											<td class="numeric">$1.39</td>
      											<td class="numeric">$1.39</td>
      											<td class="numeric">$1.38</td>
      											<td class="numeric">9,395</td>
      										</tr>
      										<tr>
      											<td>AAD</td>
      											<td>ARDENT LEISURE GROUP</td>
      											<td class="numeric">$1.15</td>
      											<td class="numeric">  +0.02</td>
      											<td class="numeric">1.32%</td>
      											<td class="numeric">$1.14</td>
      											<td class="numeric">$1.15</td>
      											<td class="numeric">$1.13</td>
      											<td class="numeric">56,431</td>
      										</tr>
      										<tr>
      											<td>AAX</td>
      											<td>AUSENCO LIMITED</td>
      											<td class="numeric">$4.00</td>
      											<td class="numeric">-0.04</td>
      											<td class="numeric">-0.99%</td>
      											<td class="numeric">$4.01</td>
      											<td class="numeric">$4.05</td>
      											<td class="numeric">$4.00</td>
      											<td class="numeric">90,641</td>
      										</tr>
      										<tr>
      											<td>ABC</td>
      											<td>ADELAIDE BRIGHTON LIMITED</td>
      											<td class="numeric">$3.00</td>
      											<td class="numeric">  +0.06</td>
      											<td class="numeric">2.04%</td>
      											<td class="numeric">$2.98</td>
      											<td class="numeric">$3.00</td>
      											<td class="numeric">$2.96</td>
      											<td class="numeric">862,518</td>
      										</tr>
      										<tr>
      											<td>ABP</td>
      											<td>ABACUS PROPERTY GROUP</td>
      											<td class="numeric">$1.91</td>
      											<td class="numeric">0.00</td>
      											<td class="numeric">0.00%</td>
      											<td class="numeric">$1.92</td>
      											<td class="numeric">$1.93</td>
      											<td class="numeric">$1.90</td>
      											<td class="numeric">595,701</td>
      										</tr>
      										<tr>
      											<td>ABY</td>
      											<td>ADITYA BIRLA MINERALS LIMITED</td>
      											<td class="numeric">$0.77</td>
      											<td class="numeric">  +0.02</td>
      											<td class="numeric">2.00%</td>
      											<td class="numeric">$0.76</td>
      											<td class="numeric">$0.77</td>
      											<td class="numeric">$0.76</td>
      											<td class="numeric">54,567</td>
      										</tr>
      										<tr>
      											<td>ACR</td>
      											<td>ACRUX LIMITED</td>
      											<td class="numeric">$3.71</td>
      											<td class="numeric">  +0.01</td>
      											<td class="numeric">0.14%</td>
      											<td class="numeric">$3.70</td>
      											<td class="numeric">$3.72</td>
      											<td class="numeric">$3.68</td>
      											<td class="numeric">191,373</td>
      										</tr>
      										<tr>
      											<td>ADU</td>
      											<td>ADAMUS RESOURCES LIMITED</td>
      											<td class="numeric">$0.72</td>
      											<td class="numeric">0.00</td>
      											<td class="numeric">0.00%</td>
      											<td class="numeric">$0.73</td>
      											<td class="numeric">$0.74</td>
      											<td class="numeric">$0.72</td>
      											<td class="numeric">8,602,291</td>
      										</tr>
      										<tr>
      											<td>AGG</td>
      											<td>ANGLOGOLD ASHANTI LIMITED</td>
      											<td class="numeric">$7.81</td>
      											<td class="numeric">-0.22</td>
      											<td class="numeric">-2.74%</td>
      											<td class="numeric">$7.82</td>
      											<td class="numeric">$7.82</td>
      											<td class="numeric">$7.81</td>
      											<td class="numeric">148</td>
      										</tr>
      										<tr>
      											<td>AGK</td>
      											<td>AGL ENERGY LIMITED</td>
      											<td class="numeric">$13.82</td>
      											<td class="numeric">  +0.02</td>
      											<td class="numeric">0.14%</td>
      											<td class="numeric">$13.83</td>
      											<td class="numeric">$13.83</td>
      											<td class="numeric">$13.67</td>
      											<td class="numeric">846,403</td>
      										</tr>
      										<tr>
      											<td>AGO</td>
      											<td>ATLAS IRON LIMITED</td>
      											<td class="numeric">$3.17</td>
      											<td class="numeric">-0.02</td>
      											<td class="numeric">-0.47%</td>
      											<td class="numeric">$3.11</td>
      											<td class="numeric">$3.22</td>
      											<td class="numeric">$3.10</td>
      											<td class="numeric">5,416,303</td>
      										</tr>
      									</tbody>
      								</table>
      							</section>
      						</div><!-- /content-panel -->
      					</div>
      				</div><!-- /row -->




      			</div><!-- /col-lg-9 END SECTION MIDDLE -->


      <!-- **********************************************************************************************************************************************************
      RIGHT SIDEBAR CONTENT
      *********************************************************************************************************************************************************** -->                  

      <div class="col-lg-3 ds">
      	<!--COMPLETED ACTIONS DONUTS CHART-->
      	<h3>NOTIFICATIONS</h3>

      	<!-- First Action -->
      	<div class="desc">
      		<div class="thumb">
      			<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
      		</div>
      		<div class="details">
      			<p><muted>2 Minutes Ago</muted><br/>
      				<a href="#">James Brown</a> subscribed to your newsletter.<br/>
      			</p>
      		</div>
      	</div>
      	<!-- Second Action -->
      	<div class="desc">
      		<div class="thumb">
      			<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
      		</div>
      		<div class="details">
      			<p><muted>3 Hours Ago</muted><br/>
      				<a href="#">Diana Kennedy</a> purchased a year subscription.<br/>
      			</p>
      		</div>
      	</div>
      	<!-- Third Action -->
      	<div class="desc">
      		<div class="thumb">
      			<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
      		</div>
      		<div class="details">
      			<p><muted>7 Hours Ago</muted><br/>
      				<a href="#">Brandon Page</a> purchased a year subscription.<br/>
      			</p>
      		</div>
      	</div>
      	<!-- Fourth Action -->
      	<div class="desc">
      		<div class="thumb">
      			<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
      		</div>
      		<div class="details">
      			<p><muted>11 Hours Ago</muted><br/>
      				<a href="#">Mark Twain</a> commented your post.<br/>
      			</p>
      		</div>
      	</div>
      	<!-- Fifth Action -->
      	<div class="desc">
      		<div class="thumb">
      			<span class="badge bg-theme"><i class="fa fa-clock-o"></i></span>
      		</div>
      		<div class="details">
      			<p><muted>18 Hours Ago</muted><br/>
      				<a href="#">Daniel Pratt</a> purchased a wallet in your store.<br/>
      			</p>
      		</div>
      	</div>


      </div><!-- /col-lg-3 -->
  </div><! --/row -->
</section>
</section>

<!--main content end-->
<!--footer start-->
<footer class="site-footer">
	<div class="text-center">
		2015 A-Team
	</div>
</footer>
<!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/resources/customer_assets/js/jquery.js"></script>
<script src="/resources/customer_assets/js/jquery-1.8.3.min.js"></script>
<script src="/resources/customer_assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="/resources/customer_assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="/resources/customer_assets/js/jquery.scrollTo.min.js"></script>
<script src="/resources/customer_assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/resources/customer_assets/js/jquery.sparkline.js"></script>


<!--common script for all pages-->
<script src="/resources/customer_assets/js/common-scripts.js"></script>

<script type="text/javascript" src="/resources/customer_assets/js/gritter/js/jquery.gritter.js"></script>
<script type="text/javascript" src="/resources/customer_assets/js/gritter-conf.js"></script>

<!--script for this page-->
<script src="/resources/customer_assets/js/sparkline-chart.js"></script>    
<script src="/resources/customer_assets/js/zabuto_calendar.js"></script>	

<script type="application/javascript">
	$(document).ready(function () {
		$("#date-popover").popover({html: true, trigger: "manual"});
		$("#date-popover").hide();
		$("#date-popover").click(function (e) {
			$(this).hide();
		});

		$("#my-calendar").zabuto_calendar({
			action: function () {
				return myDateFunction(this.id, false);
			},
			action_nav: function () {
				return myNavFunction(this.id);
			},
			ajax: {
				url: "show_data.php?action=1",
				modal: true
			},
			legend: [
			{type: "text", label: "Special event", badge: "00"},
			{type: "block", label: "Regular event", }
			]
		});
	});


	function myNavFunction(id) {
		$("#date-popover").hide();
		var nav = $("#" + id).data("navigation");
		var to = $("#" + id).data("to");
		console.log('nav ' + nav + ' to: ' + to.month + '/' + to.year);
	}
</script>


</body>
</html>
