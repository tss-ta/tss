<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>Edit Driver details</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">

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
                                <button id="unassign_car_btn" class="btn btn btn-danger" type="submit" >Unassign</button>
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
                                <button id="assign_car_btn" class="btn btn btn-info" type="submit">Assign</button>
                            </form>
                        </div>
                    </div>
                </c:if>

                <hr/>

                <form action="/admin" method="post" class="form-horizontal" data-toggle="validator">

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <%--Choose driver options--%>
                            <div class="checkbox">
                                <label class="checkbox"><input id="availableChkBox" type="checkbox" name="available" >available</label>
                                <label class="checkbox"><input id="isMaleChkBox" type="checkbox" name="ismale">is male</label>
                                <label class="checkbox"><input id="smokesChkBox" type="checkbox" name="smokes">smokes</label>
                            </div>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-md-offset-1 col-md-5">
                            <label for="inputCategory">Choose category</label>
                            <select id="inputCategory" name="category">
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-3">
                            <input type="hidden" name="menu" value="drivers">
                            <input type="hidden" name="action" value="editdriver">
                            <input type="hidden" name="id" value="${driver.getId()}">
                            <button type="submit" class="btn btn-default">
                                Edit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/resources/js/jquery-1.11.2.min.js"></script>

<script>
    $("#inputCategory").val("${driver.getCategory().getCatStr()}");
    $("#availableChkBox").prop('checked', ${driver.isAvailable()});
    $("#isMaleChkBox").prop('checked', ${driver.isMale()});
    $("#smokesChkBox").prop('checked', ${driver.isSmokes()});
</script>