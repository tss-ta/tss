<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-2 col-md-8">
        <div class="text-center">
            <h1 class="page-title">Dashboard</h1>
        </div>
    </div>
</div>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">

        <div class="col-md-offset-1 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=users&action=view"><i class="fa fa-child fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    CUSTOMERS: ${customers}
                </div>
            </div>
        </div>
        <div class="col-md-offset-2 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=report&action=all"><i class="fa fa-line-chart fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    ORDERS: ${orders}
                </div>
            </div>
        </div>
        <div class="col-md-offset-1 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=car&action=all"><i class="fa fa-taxi fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    CARS: ${cars}
                </div>
            </div>
        </div>
        <div class="col-md-offset-2 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=driver&action=all"><i class="fa fa-user fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    DRIVERS: ${drivers}
                </div>
            </div>
        </div>
        <div class="col-md-offset-1 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=groups&action=view"><i class="fa fa-users fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    GROUPS: ${groups}
                </div>
            </div>
        </div>
        <div class="col-md-offset-2 col-md-4 text-center">
            <div class="panel panel-default custom-panel">
                <div class="panel-body">
                    <a href="/admin?menu=tariffs&action=view"><i class="fa fa-usd fa-5x"></i></a>
                </div>
                <div class="panel-footer">
                    TARIFFS: ${tariffs}
                </div>
            </div>
        </div>

    </div>
</div>
