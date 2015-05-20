<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">

            <c:if test="${not empty car}">
                <h1>Edit Car Form</h1>
            </c:if>
            <c:if test="${empty car}">
                <h1>Add Car Form</h1>
            </c:if>

        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">

                <form action="/admin${pagerLink.buildParametersLink()}" method="post" class="form-horizontal" data-toggle="validator">

                    <div class="control-group">
                    <div class="form-group">
                        <br/>
                        <input type="hidden" name="id" value="${car.id}">
                        <label for="license" class="col-md-4 control-label">License plate</label>
                        <div class="col-md-5">
                            <input type="text" class="form-control" pattern="[a-zA-Z]{3}-[0-9]{3}-[a-zA-Z]{3}" minlength="11" maxlength="11" id="license" placeholder="License" value="${car.licPlate}" name="license" maxlength="11" required autofocus>
                        </div>
                        <div class="col-md-offset-4 col-md-8 help-block">Format: XXX-YYY-XXX. Length: 11</div>
                    </div>

                    <hr/>

                    <c:if test="${car.available}">
                        <c:set var="available" value="checked"/>
                    </c:if>
                    <c:if test="${car.animalable}">
                        <c:set var="animalable" value="checked"/>
                    </c:if>
                    <c:if test="${car.wifi}">
                        <c:set var="wifi" value="checked"/>
                    </c:if>
                    <c:if test="${car.conditioner}">
                        <c:set var="conditioner" value="checked"/>
                    </c:if>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" ${available} name="available"> Available
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" ${animalable} name="animailable"> Animal transport
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" ${wifi} name="wifi"> WI-FI
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" ${conditioner} name="conditioner"> Conditioner
                                </label>
                            </div>
                        </div>
                    </div>

                    <hr/>

                    <c:if test="${car.category == 1}">
                        <c:set var="business" value="selected"/>
                    </c:if>
                    <c:if test="${car.category == 2}">
                        <c:set var="economy" value="selected"/>
                    </c:if>
                    <c:if test="${car.category == 3}">
                        <c:set var="van" value="selected"/>
                    </c:if>
                    <c:if test="${car.category == 4}">
                        <c:set var="cargo" value="selected"/>
                    </c:if>

                    <div class="form-group">
                        <label class="col-md-4 control-label">Type</label>
                        <div class="col-md-5">
                            <select name="category" class="form-control">
                                <option ${business} value="1">Business</option>
                                <option ${economy} value="2">Economy</option>
                                <option ${van} value="3">Van</option>
                                <option ${cargo} value="4">Cargo</option>
                            </select>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-3">
                            <button type="submit" class="btn btn-default">
                                <c:if test="${empty car}">
                                    Add
                                </c:if>
                                <c:if test="${not empty car}">
                                    Save
                                </c:if>
                            </button>
                        </div>
                    </div>
                </div>
                </form>

            </div>
        </div>
    </div>
</div>

