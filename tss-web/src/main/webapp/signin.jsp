<%--
  User: Kyrylo Berehovyi, maks
  Date: 19/04/2015
  Time: 21:01
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/resources/img/favicon.ico">

    <title>Taxi Service System Sign In</title>
    
     <link href="/resources/css/style.css" rel="stylesheet" type="text/css"
	     media="all" />
    	<link href='http://fonts.googleapis.com/css?family=Raleway'
	    rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/responsiveslides.css">
        <script src="/resources/js/responsiveslides.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/css/sign.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<%@ include file="WEB-INF/views/partials/non-dasboard-header.jspf" %>

<div class="container">

    <form class="form-sign" action="/auth" method="post" autocomplete="off">

        <%-- Delete Chrome autocomplete --%>
        <input style="display:none" type="text" name="fakeusernameremembered"/>
        <input style="display:none" type="password" name="fakepasswordremembered"/>

        <h2 class="form-sign-heading">Please Sign In</h2>
        <h5 class="form-sign-error-msg">Incorrect Email and/or Password!</h5>
            <div class="control-group">
                <input type="email" data-validation-email-message="Incorrect email address"
                   class="form-control" id="input_email" placeholder="Email" name="email"
                   minlength="5" data-validation-minlength-message="Incorrect email address"
                   maxlength="40" required
                   data-validation-maxlength-message="Incorrect email address. It's too long" autofocus/>
                <br/>
                <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required minlength="1" maxlength="60"><br/>
                <h5 class="help-block form-sign-heading"></h5>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            </div>
    </form>


</div> <!-- /container -->

 <%@ include file="WEB-INF/views/partials/footer.jspf" %>


<script src="/resources/js/jquery-1.11.2.min.js"></script>
<script src="/resources/js/jqBootstrapValidation.js"></script>
<script>
    $(function () {
        $("input").not("[type=submit]").jqBootstrapValidation();
   //     $(".form-sign-error-msg").css("visibility", "visible");
    });
</script>
    <script>
        $(document).ready(function() {
            if(getUrlParameter("error") == "true") {
                printErrorMessage();
            }
        });
        function printErrorMessage() {
            $(".form-sign-error-msg").css("visibility", "visible");
            $("input").addClass("error");
        };
        function getUrlParameter(sParam) {
            var sPageURL = window.location.search.substring(1);
            var sURLVariables = sPageURL.split('&');
            for (var i = 0; i < sURLVariables.length; i++) {
                var sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] == sParam) {
                    return sParameterName[1];
                }
            }
        };
    </script>
</body>
</html>
