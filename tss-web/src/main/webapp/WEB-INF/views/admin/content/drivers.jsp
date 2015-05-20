<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 01:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1 class="text-center">Drivers Panel</h1>

        <div class="row row-fix">
            <%--<div class="col-md-offset-1 col-md-10">--%>
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-1">
                            <a href="/admin?menu=drivers&action=add" class="btn btn-default">Invite Driver <i class="fa fa-user"></i></a>
                        </div>
                        <div class="col-md-offset-7 col-md-4">
                            <form action="/admin?menu=driver&action=search&page=1" method="post" >
                                <div class="input-group">
                                    <input type="text" class="form-control" name="search" placeholder="Driver name"
                                           maxlength="40" value="${param.search}">
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                <%--</div>--%>
            </div>
        </div>

        <table class="table table-hover table-bordered">
            <thead class="tablehead text-center">
            <td class="col-md-2">driver name</td>
            <td class="col-md-1">category</td>
            <td class="col-md-1">available</td>
            <td class="col-md-1">gender</td>
            <td class="col-md-1">smokes</td>
            <td class="col-md-2">car</td>
            <td class="col-md-1">edit</td>
            <%--<td class="col-md-1">delete</td>--%>
            </thead>
            <tbody>

            <c:forEach items="${driverList}" var="driver">
                <tr class="text-center">
                    <td>${driver.getUsername()}</td>
                    <td>${driver.getCategory().getCatStr()}</td>
                    <td>
                        <c:if test="${driver.isAvailable()}">
                            <i class="fa fa-check"></i>
                        </c:if>
                        <c:if test="${!driver.isAvailable()}">
                            <i class="fa fa-minus"></i>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${driver.isMale()}">
                            <i class="fa fa-male"></i>
                        </c:if>
                        <c:if test="${!driver.isMale()}">
                            <i class="fa fa-female"></i>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${driver.isSmokes()}">
                            <i class="fa fa-fire"></i>
                        </c:if>
                        <c:if test="${!driver.isSmokes()}">
                            <i class="fa fa-minus"></i>
                        </c:if>
                    </td>
                    <td>${driver.getCar().getLicPlate()}</td>
                    <td><a href="/admin/driver?action=editdriver&driverid=${driver.getId()}" class="btn btn-default">edit</a></td>
                    <%--<td><a href="/admin/driver?action=deletedriver&driverid=${driver.getId()}">X</a></td>--%>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <%@ include file="../partials/pagination.jspf" %>
    </div>
    <div class="col-md-1"></div>
</div>
