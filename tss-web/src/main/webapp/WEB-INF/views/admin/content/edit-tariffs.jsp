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
        <h1>Tariffs Panel</h1>
        <table class="table table-striped table-bordered">
            <thead class="tablehead">
            <td>name</td>
            <td>additive price</td>
            <td>multiplicative price coefficient</td>
            <td></td>
            </thead>

            <tbody>
                <c:forEach var = "tariff" items = "${requestScope.tariffs}">
                    <tr>
                        <form action="admin/tariff" method="post">
                            <td>${tariff.tariffName}</td>
                            <td>
                                <input type="number" name="add" value="${tariff.plusCoef}">
                            </td>
                            <td>
                                <input type="number" name="mult" value="${tariff.multipleCoef}">
                            </td>
                            <td class="col-md-1"><input type="submit" class="btn btn-default" value="save"></td>
                        </form>
                    </tr>
            </c:forEach> 
            </tbody>
        </table>
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>