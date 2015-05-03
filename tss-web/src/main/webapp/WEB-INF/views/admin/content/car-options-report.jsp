<%--
  author: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1 class="text-center">Most Popular Car Options For ${requestScope.user}</h1>

        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-1">
                    <a href="/admin/report" class="btn btn-default"> Back </a>
                </div>
                <div class="col-md-1">
                    <a href="/admin/report" class="btn btn-default"> Export to excel file <i class="fa fa-download"></i></a>
                </div>
            </div>
        </div>

        <table class="table table-striped table-bordered">
            <thead class="tablehead">
            <td class="col-md-1">#</td>
            <td>Option</td>
            <td class="text-center"><i class="fa fa-plus"></i></td>
            <td class="text-center"><i class="fa fa-minus"></i></td>
            </thead>

            <tbody>
                <c:forEach var = "row" items = "${requestScope.report}" varStatus = "counter">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${row.name}</td>
                        <td class="text-center">${row.value}</td>
                        <td class="text-center">${requestScope.allTO - row.value}</td>
                    </tr>
                </c:forEach> 
            </tbody>
        </table>
        <br>
        <h3 class="text-right">All analized taxi orders are ${requestScope.allTO}</h3>

        <%--       <%@ include file="../partials/pagination.jspf" %>  --%>

    </div>
    <div class="col-md-1"></div>
</div>