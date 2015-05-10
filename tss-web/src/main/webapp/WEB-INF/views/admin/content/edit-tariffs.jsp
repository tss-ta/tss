<%--
    author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-2 col-md-8">

        <div class="text-center">
            <h1>Tariffs Edit Panel</h1>
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin?menu=tariffs&action=view" class="btn btn-default"> Back </a>
                </div>
                <div class="col-md-offset-7 col-md-4">
                    <form action="/admin" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name="name" placeholder="Search " value="${param.name}" maxlength="40">
                            <input type="hidden" name="action" value="search-for-edit">
                            <input type="hidden" name="menu" value="tariffs">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <table class="table table-hover panel-body">
                <thead class="tablehead">
                <td class="col-md-3">Name</td>
                <td class="col-md-offset-1 col-md-2">Price summand <i class="fa fa-plus"></i></td>
                <td class="col-md-offset-1 col-md-2">Price multiplier <i class="fa fa-times"></i></td>
                <td class="col-md-offset-1 col-md-2"></td>
                </thead>

                <tbody>
                    <c:forEach var = "tariff" items = "${requestScope.tariffs}">
                        <tr>
                    <form action="/admin" method="post">
                        <div class="form-group">
                            <td class="col-md-3">${tariff.tariffName}</td>
                            <td class="col-md-offset-1 col-md-2">
                                <input class="form-control" type="number" name="add" value="${tariff.plusCoef}">
                            </td>
                            <td class="col-md-offset-1 col-md-2">
                                <input class="form-control" type="number" name="mult" value="${tariff.multipleCoef}">
                            </td>
                            <td class="col-md-offset-1 col-md-2">
                                <input type="submit" class="btn btn-default" value="save">
                            </td>
                            <input type="hidden" name="id" value="${tariff.id}">
                            <input type="hidden" name="action" value="save">
                            <input type="hidden" name="menu" value="tariffs">
                        </div>
                    </form>
                    </tr>
                </c:forEach> 
                </tbody>
            </table>
        </div>
        <%@ include file="../partials/pagination.jspf" %>
    </div>

    <div class="col-md-1"></div>
</div>