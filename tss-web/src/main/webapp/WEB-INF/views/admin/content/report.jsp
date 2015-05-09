<%--
  User: Kyrylo Berehovyi
  Date: 08/05/2015
  Time: 23:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>${report.info.name}</h1>
        </div>
        <div>
            <p>${report.info.description}</p>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-offset-0 col-md-1 col-sm-offset-0 col-sm-2 col-xs-offset-4 col-xs-4">
                    <a href="/admin?menu=car&action=add" class="btn btn-default">Add <i class="fa fa-taxi"></i></a>
                </div>
                <%--<div class="col-md-offset-7 col-md-4 col-sm-offset-2 col-sm-8 col-xs-12">--%>
                    <%--<form action="/admin" method="get" >--%>
                        <%--<div class="input-group">--%>
                            <%--<input type="hidden" name="menu" value="car">--%>
                            <%--<input type="hidden" name="action" value="search">--%>
                            <%--<input type="text" class="form-control" name="search" placeholder="Search by License Plate" value="${param.search}" maxlength="11">--%>
                            <%--<span class="input-group-btn">--%>
                                <%--<button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>--%>
                            <%--</span>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty report.data}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <table class="table table-hover table-bordered">
                <thead class="tablehead text-center">
                    <c:forEach begin="1" end="${report.data.columnAmount}" var="index">
                        <td>${report.data.getColumnName(index)}</td>
                    </c:forEach>
                </thead>

                <tbody>
                    <c:forEach items="${report.data.rows}" var="row">
                        <tr class="text-center">
                            <c:forEach begin="1" end="${row.columnAmount}" var="columnIndex">
                                <c:set var="rowColumn" value="${row.getColumnData(columnIndex)}" />
                                <td class="hidden-sm hidden-xs">
                                    <c:choose>
                                        <c:when test="${rowColumn.type eq 'INTEGER'}">
                                            ${rowColumn.intValue}
                                        </c:when>
                                        <c:when test="${rowColumn.type eq 'STRING'}">
                                            ${rowColumn.stringValue}
                                        </c:when>
                                        <c:when test="${rowColumn.type eq 'DOUBLE'}">
                                            ${rowColumn.doubleValue}
                                        </c:when>
                                        <c:when test="${rowColumn.type eq 'BOOLEAN'}">
                                            ${rowColumn.booleanValue}
                                        </c:when>
                                        <c:when test="${rowColumn.type eq 'TIMESTAMP'}">
                                            ${rowColumn.timestampValue}
                                        </c:when>
                                        <c:when test="${rowColumn.type eq 'LONG'}">
                                            ${rowColumn.longValue}
                                        </c:when>
                                        <c:otherwise>
                                            unknown
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <%@ include file="../partials/pagination.jspf"%>

        </div>
    </div>
</c:if>
<c:if test="${empty report.data}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No data found for this report</h3>
            </div>
        </div>
    </div>
</c:if>

