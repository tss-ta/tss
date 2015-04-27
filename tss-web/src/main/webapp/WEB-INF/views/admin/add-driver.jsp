<%--
  User: Illia Rudenko
  Date: 25/04/2015
  Time: 14:07
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

  <title>Taxi Service System Add Driver</title>

  <!-- Bootstrap core CSS -->
  <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/css/font-awesome.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <%--<link href="/resources/css/sign-in.css" rel="stylesheet">--%>

  <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>

<%@ include file="partials/admin-header.jspf" %>

<div class="container">

    <%@ include file="partials/menu.jspf" %>

    <form action="/admin/driver" method="post">

    <%-- Delete Chrome autocomplete --%>
    <input style="display:none" type="text" name="fakeusernameremembered"/>
    <input style="display:none" type="password" name="fakepasswordremembered"/>

    <h2 class="form-signin-heading">Register Driver</h2>

      <div class="row">

        <%--Column for registering account details--%>
        <div class="col-md-6">
          <label for="inputDriverName" class="sr-only">Driver name</label>
          <input type="text" id="inputDriverName" name="drivername" class="form-control" placeholder="Driver name" required autofocus>
          <label for="inputEmail" class="sr-only">Email address</label>
          <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email address" required>
          <label for="inputPassword" class="sr-only">Password</label>
          <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
          <label for="confirmInputPassword" class="sr-only">Confirm Password</label>
          <input type="password" id="confirmInputPassword" name="confirmPassword" class="form-control" placeholder="Confirm Password" required><br/>
        </div>

        <%--Column for additional driver options choose--%>
        <div class="col-md-6">

          <%--Choose driver category--%>
          <label for="inputCategory">Choose category</label>
          <select id="inputCategory" name="category">
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
          </select>

          <%--Choose driver options--%>
          <div class="checkbox">
            <label class="checkbox"><input type="checkbox" name="available" >available</label>
            <label class="checkbox"><input type="checkbox" name="ismale">is male</label>
            <label class="checkbox"><input type="checkbox" name="smokes">smokes</label>
          </div>

          <%--TODO: Assign some car to a driver--%>

          <input type="hidden" name="action" value="newdriver">
        </div>
      </div> <%--Row end--%>


    <%--<div class="checkbox">--%>
    <%--<label>--%>
    <%--<input type="checkbox" value="remember-me"> Remember me--%>
    <%--</label>--%>
    <%--</div>--%>

    <button class="btn btn-lg btn-primary" type="submit">Register Driver</button>
  </form>


</div> <!-- /container -->

<%@ include file="/WEB-INF/views/partials/footer.jspf" %>

</body>
</html>
