<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <label class="checkbox"><input type="checkbox" name="isMale">is male</label>
                <label class="checkbox"><input type="checkbox" name="smokes">smokes</label>
            </div>

            <%--TODO: Assign some car to a driver--%>

            <input type="hidden" name="action" value="newdriver">
        </div>
    </div> <%--Row end--%>
    <button class="btn btn-lg btn-primary" type="submit">Register Driver</button>
</form>
