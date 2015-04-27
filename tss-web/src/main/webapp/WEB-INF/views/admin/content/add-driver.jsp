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

    <c:if test="${empty driver}">
        <h2 class="form-signin-heading">Register Driver</h2>
    </c:if>
    <c:if test="${not empty driver}">
        <h2 class="form-signin-heading">Edit Driver details</h2>
    </c:if>

    <div class="row">

        <%--Column for registering account details--%>
        <div class="col-md-6">
            <label for="inputDriverName" class="sr-only">Driver name</label>
            <c:if test="${empty driver}">
                <input type="text" id="inputDriverName" name="drivername" class="form-control" placeholder="Driver name" required autofocus>
            </c:if>
            <c:if test="${not empty driver}">
                <input type="text" id="inputDriverName" name="drivername" class="form-control" value="${driver.getUsername()}" autofocus>
            </c:if>

            <label for="inputEmail" class="sr-only">Email address</label>
            <c:if test="${empty driver}">
                <input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email address" required>
            </c:if>
            <c:if test="${not empty driver}">
                <input type="text" id="inputEmail" name="email" class="form-control" value="${driver.getEmail()}" required>
            </c:if>

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
                <label class="checkbox"><input id="availableChkBox" type="checkbox" name="available" >available</label>
                <label class="checkbox"><input id="isMaleChkBox" type="checkbox" name="ismale">is male</label>
                <label class="checkbox"><input id="smokesChkBox" type="checkbox" name="smokes">smokes</label>
            </div>

            <%--TODO: Assign some car to a driver--%>


        </div>
    </div> <%--Row end--%>
    <c:if test="${empty driver}">
        <input type="hidden" name="action" value="newdriver">
        <button class="btn btn-lg btn-primary" type="submit">Register Driver</button>
    </c:if>
    <c:if test="${not empty driver}">
        <input type="hidden" name="action" value="editdriver">
        <input type="hidden" name="driverid" value="${driver.getId()}">
        <button class="btn btn-lg btn-primary" type="submit">Edit Driver</button>
    </c:if>



</form>
    <c:if test="${not empty driver}">
        <script src="/resources/js/jquery-1.11.2.min.js"></script>
        <script>

            $("#inputCategory").val("${driver.getCategory().getCatStr()}");
            $("#availableChkBox").prop('checked', ${driver.isAvailable()});
            $("#isMaleChkBox").prop('checked', ${driver.isMale()});
            $("#smokesChkBox").prop('checked', ${driver.isSmokes()});
        </script>
    </c:if>