<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Add Car Form</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/admin/car?action=add-car" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="plate" class="col-md-4 control-label">License plate</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="plate" placeholder="License">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="available"> Available
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="animalable"> Animal transport
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="wi-fi"> WI-FI
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" name="conditioner"> Conditioner
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="plate" class="col-md-4 control-label">Type</label>
                        <div class="col-sm-5">
                            <select name="category" class="form-control">
                                <option value="1">Business</option>
                                <option value="2">Economy</option>
                                <option value="3">Van</option>
                                <option value="4">Cargo</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-3">
                            <button type="submit" class="btn btn-default">Add <i class="fa fa-taxi"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

