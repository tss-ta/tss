<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- Delete Chrome autocomplete --%>
<input style="display:none" type="text" name="fakeusernameremembered"/>
<input style="display:none" type="password" name="fakepasswordremembered"/>
<div class="row row-fix">
    <div class="col-md-4">
        <div class="col-md-1"></div>
        <div class="col-md-11">
            <c:if test="${empty driver}">
                <h2 class="form-signin-heading">Register Driver</h2>
            </c:if>
            <c:if test="${not empty driver}">
                <h2 class="form-signin-heading">Edit Driver details</h2>
            </c:if>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-7">
        <form action="/admin/driver" method="post">
            <div class="row row-fix">
                <div class="col-md-8">
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
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
                        <label for="confirmInputPassword" class="sr-only">Confirm Password</label>
                        <input type="password" id="confirmInputPassword" name="confirmPassword" class="form-control" placeholder="Confirm Password" required><br/>
                    </c:if>
                    <c:if test="${not empty driver}">
                        <input type="text" id="inputEmail" name="email" class="form-control" value="${driver.getEmail()}" required>
                    </c:if>
                </div>

                <div class="col-md-4">
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
                </div>
            </div>

            <div class="row row-fix">
                <div class="col-md-6">
                    <div class="col-md-1"></div>
                    <div class="col-md-11">
                        <c:if test="${empty driver}">
                            <input type="hidden" name="action" value="newdriver">
                            <button class="btn btn-lg btn-primary" type="submit">Register Driver</button>
                        </c:if>
                        <c:if test="${not empty driver}">
                            <input type="hidden" name="action" value="editdriver">
                            <input type="hidden" name="driverid" value="${driver.getId()}">
                            <button class="btn btn-lg btn-primary" type="submit">Edit Driver</button>
                        </c:if>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <div class="col-md-5">
        <%--Assigned Car--%>
            <div class="row row-fix">
                <div class="col-md-11">
                    <c:if test="${not empty driver}">
                        <c:set var="car" value="${driver.getCar()}"/>
                        <c:if test="${not empty car}">
                            <div class="row row-fix">
                                <div class="col-md-4">
                                    <h4>Assigned car</h4>
                                </div>

                                <div class="col-md-4">
                                    <form action="/admin/driver" method="post">
                                        <input type="hidden" name="action" value="unassign">
                                        <input type="hidden" name="driverid" value="${driver.getId()}">
                                        <input type="hidden" name="carid" value="${car.getId()}">
                                        <button class="btn btn btn-danger" type="submit" >Unassign</button>
                                    </form>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered">
                                <thead class="tablehead text-center">
                                <td class="col-md-1">Option</td>
                                <td class="col-md-1">Value</td>
                                </thead>

                                <tbody>
                                    <%--License Plate--%>
                                <tr class="text-center">
                                    <td>№</td>
                                    <td>${car.getLicPlate()}</td>
                                </tr>

                                        <%--Car Type--%>
                                    <tr>
                                        <td class="text-center">Type</td>
                                        <td class="text-center">
                                            <c:choose>
                                                <c:when test="${car.category == 1}">
                                                    Business
                                                </c:when>
                                                <c:when test="${car.category == 2}">
                                                    Economy
                                                </c:when>
                                                <c:when test="${car.category == 3}">
                                                    Van
                                                </c:when>
                                                <c:when test="${car.category == 4}">
                                                    Cargo
                                                </c:when>
                                                <c:otherwise>
                                                    Other
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="text-center">Wi-fi</td>
                                        <td class="text-center">
                                            <c:if test="${car.wifi}">
                                                <i class="fa fa-wifi"></i>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="text-center">Conditioner</td>
                                        <td class="text-center">
                                            <c:if test="${car.conditioner}">
                                                <i class="fa fa-check"></i>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="text-center">Animalable</td>
                                        <td class="text-center">
                                            <c:if test="${car.animalable}">
                                                <i class="fa fa-check"></i>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td class="text-center">Available</td>
                                        <td class="text-center">
                                            <c:if test="${car.available}">
                                                <i class="fa fa-check"></i>
                                            </c:if>
                                        </td>
                                    </tr>

                                        <%--<td><a href="/admin?action=edit">Edit</a></td>--%>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${empty car}">
                            <div class="row row-fix">
                                <div class="col-md-6">
                                    <h4>There is no assigned Car</h4>
                                </div>

                                <div class="col-md-4">
                                    <form action="/admin/driver" method="post">
                                        <input type="hidden" name="action" value="assign">
                                        <input type="hidden" name="driverid" value="${driver.getId()}">
                                        <button class="btn btn btn-info" type="submit">Assign</button>
                                    </form>
                                </div>
                            </div>
                        </c:if>
                    </c:if>

                    <div class="col-md-2"></div>
                </div>
            </div>
    </div>
</div> <%--Row end--%>



<c:if test="${not empty driver}">
    <script src="/resources/js/jquery-1.11.2.min.js"></script>
    <script>
        $("#inputCategory").val("${driver.getCategory().getCatStr()}");
        $("#availableChkBox").prop('checked', ${driver.isAvailable()});
        $("#isMaleChkBox").prop('checked', ${driver.isMale()});
        $("#smokesChkBox").prop('checked', ${driver.isSmokes()});
    </script>
</c:if>