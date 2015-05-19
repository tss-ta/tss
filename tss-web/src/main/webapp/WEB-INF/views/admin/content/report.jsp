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
        <%--<div>--%>
            <%--<p>${report.info.description}</p>--%>
        <%--</div>--%>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body text-center">
                <p><i class="fa fa-info-circle fa-lg"></i> ${report.info.description}</p>
            </div>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body text-center">
                <%--<div class="col-md-offset-0 col-md-2 col-sm-offset-0 col-sm-2 col-xs-offset-0 col-xs-4">--%>
                    <a href="/admin?menu=report&action=all" class="btn btn-default"><i class="fa fa-reply"></i> Back</a>
                <%--</div>--%>
                <%--<div class="col-md-offset-0 col-md-2 col-sm-offset-0 col-sm-2 col-xs-offset-0 col-xs-4">--%>
                    <a href="/admin/report/download?id=${report.info.id}" id="download" class="btn btn-default"><i class="fa fa-download"></i> Excel</a>
                <%--</div>--%>
                <%--<div class="col-md-offset-0 col-md-2 col-sm-offset-0 col-sm-2 col-xs-offset-0 col-xs-4">--%>
                    <a href="/admin?menu=report&action=edit&id=${report.info.id}" class="btn btn-default"><i class="fa fa-pencil"></i> Edit</a>
                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#delModal">
                        <i class="fa fa-trash-o"></i> Delete
                    </button>
                    <%--<a href="/admin?menu=report&action=delete&id=${report.info.id}" class="btn btn-default"><i class="fa fa-trash-o"></i> Delete</a>--%>
                <%--</div>--%>
            </div>
        </div>
    </div>
</div>

<!-- Delete Modal -->
<div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="delModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="delModalLabel">Delete Report</h4>
            </div>
            <div class="modal-body text-center">
                <p>Are you sure you want to delete "<b>${report.info.name}</b>" report?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a href="/admin?menu=report&action=delete&id=${report.info.id}" class="btn btn-default"><i class="fa fa-trash-o"></i> Delete</a>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty report.data}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <table class="table table-hover table-bordered">
                <thead class="tablehead text-center">
                    <c:forEach begin="1" end="${report.data.columnAmount()}" var="index">
                        <td>${report.data.columnName(index)}</td>
                    </c:forEach>
                </thead>

                <tbody>
                    <c:forEach items="${report.data.rows}" var="row">
                        <tr class="text-center">
                            <c:forEach begin="1" end="${row.columnAmount()}" var="columnIndex">
                                <c:set var="rowColumn" value="${row.columnData(columnIndex)}" />
                                <td>
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
                                            <c:if test="${rowColumn.booleanValue}">
                                                <i class="fa fa-check"></i>
                                            </c:if>
                                            <c:if test="${not rowColumn.booleanValue}">
                                                <i class="fa fa-minus"></i>
                                            </c:if>
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
<c:if test="${empty report.data.rows}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No data found for this report</h3>
            </div>
        </div>
    </div>
</c:if>

<%--<script src="/resources/js/report/jszip.min.js"></script>--%>
<%--<script src="http://cdn.jsdelivr.net/g/filesaver.js"></script>--%>
<%--<script src="/resources/js/report/xlsx.js"></script>--%>
<%--<script src="/resources/js/report/excel-dowloader.js"></script>--%>