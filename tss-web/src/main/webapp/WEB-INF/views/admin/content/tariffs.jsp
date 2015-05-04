<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <div class="text-center">
            <h1>Tariffs Panel</h1>
        </div>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin/tariff?action=edit" class="btn btn-default">Edit tariffs <i class="fa fa-money"></i></a>
                </div>
                <div class="col-md-offset-7 col-md-4">
                    <form action="/admin/tariff" method="get">
                        <div class="input-group">
                            <input type="text" class="form-control" name="name" placeholder="Search " value="${param.name}">
                            <input type="hidden" name="action" value="search">
                            <span class="input-group-btn">
                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <table class="table table-striped table-bordered">
            <thead class="tablehead">
            <td>Name</td>
            <td>Price summand <i class="fa fa-plus"></i></td>
            <td>Price multiplier <i class="fa fa-times"></i></td>
<!--            <td></td>-->
            </thead>

            <tbody>
                <c:forEach var = "tariff" items = "${requestScope.tariffs}">
                    <tr>
                        <td>${tariff.tariffName}</td>
                        <td>${tariff.plusCoef}</td>
                        <td>${tariff.multipleCoef}</td>
<!--                        <td class="col-md-1"><a href="/admin/tariff" class="btn btn-default" >edit</a></td>-->
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>