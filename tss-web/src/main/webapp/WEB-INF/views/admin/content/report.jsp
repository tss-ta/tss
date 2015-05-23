<%--
  User: Kyrylo Berehovyi
  Date: 08/05/2015
  Time: 23:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="/resources/customer_assets/css/anytime.5.1.0.css" rel="stylesheet">

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>${report.info.name}</h1>
        </div>
    </div>
</div>

<c:if test="${not empty report.info.description}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="panel panel-default">
                <div class="panel-body text-center">
                    <p><i class="fa fa-info-circle fa-lg"></i> ${report.info.description}</p>
                </div>
            </div>
        </div>
    </div>
</c:if>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="panel panel-default">
            <div class="panel-body text-center">
                <a href="/admin?menu=report&action=all" class="btn btn-primary"><i class="fa fa-reply"></i> Back</a>

                <c:if test="${not report.info.isFilterable()}">
                    <a href="/admin/report/download?id=${report.info.id}" id="download" class="btn btn-success"><i class="fa fa-download"></i> Excel</a>
                </c:if>


                <a href="/admin?menu=report&action=edit&id=${report.info.id}" class="btn btn-warning"><i class="fa fa-pencil"></i> Edit</a>

                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#delModal">
                    <i class="fa fa-trash-o"></i> Delete
                </button>

                <c:if test="${report.info.isFilterable()}">
                    <button class="btn btn-info" type="button" id="filterBtn" data-arrow="0" data-toggle="collapse" data-target="#filter" aria-expanded="false" aria-controls="filter">
                        <i class="fa fa-chevron-down"></i> Filter
                    </button>
                </c:if>

            </div>
        </div>
    </div>
</div>



<%-- Filter --%>
<c:if test="${report.info.isFilterable()}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="collapse" id="filter">
                <div class="well">
                    <div class="row">
                        <form class="form-horizontal" id="filter-form" method="get">

                            <c:forEach items="${report.info.filter}" var="criterion">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <c:choose>
                                            <c:when test="${(criterion.type eq 1) or (criterion.type eq 2) or (criterion.type eq 3) or (criterion.type eq 4)}">
                                                <label for="cr${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class=" col-md-6">
                                                    <input type="text" class="form-control" id="cr${criterion.id}" name="crName${criterion.id}">
                                                </div>
                                            </c:when>
                                            <c:when test="${criterion.type eq 5}">
                                                <div class="checkbox col-md-offset-5 col-md-7 text-left">
                                                    <input type="checkbox" id="cr${criterion.id}" name="crName${criterion.id}" style="styled">
                                                    <label for="cr${criterion.id}"><b>${criterion.name}</b></label>
                                                </div>
                                            </c:when>
                                            <c:when test="${criterion.type eq 6}">
                                                <label for="for${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="cr${criterion.id}" name="crName${criterion.id}" class="form-control datePicker">
                                                </div>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="col-md-12">
                                <hr/>
                            </div>

                            <div id="btnParams"></div>

                            <div class="col-md-12 text-center">
                                <button type="button" class="btn btn-success" id="applyBtn" data-action="/admin?menu=report&action=test"><i class="fa fa-check"></i> Apply</button>
                                <button type="button" class="btn btn-success" id="excelBtn" data-action="/excel"><i class="fa fa-download"></i> Excel</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>



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
                <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                <a href="/admin?menu=report&action=delete&id=${report.info.id}" class="btn btn-danger"><i class="fa fa-trash-o"></i> Delete</a>
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
<c:if test="${empty report.data.rows or report.data.columnAmount() eq 0}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No data found for this report</h3>
            </div>
        </div>
    </div>
</c:if>

<div class="not-to-show" id="applyActionParams-template" data-number="0">
    <input type="hidden" name="menu" value="report">
    <input type="hidden" name="action" value="test">
</div>

<script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
<script>
    $(".datePicker").AnyTime_picker( { format: "%Y-%m-%d %h:%m:%s", firstDOW: 1 } );

    $("#applyBtn").click(function() {
        addParams($("#applyActionParams-template"))
        sendForm("#applyBtn")
    });
    $("#excelBtn").click(function() {
        clearParams();
        sendForm("#excelBtn")
    });

    function addParams(templateElem) {
        $("#btnParams").append(templateElem.html());
    }

    function clearParams() {
        $("#btnParams").html("");
    }

    function sendForm(elem) {
        var url = $(elem).data("action");
        var form = $("#filter-form");
        form.attr("action", url);
        form.submit();
    };

    $("#filterBtn").click(function() {
        var arrowState = $(this).data("arrow");
        if(arrowState == 0) {
            changeFilterBtnArrowState($(this), 1, "<i class=\"fa fa-chevron-up\"></i> Filter");
        }
        else {
            changeFilterBtnArrowState($(this), 0, "<i class=\"fa fa-chevron-down\"></i> Filter");
        }
    });

    function changeFilterBtnArrowState(elem, arrowState, content) {
        elem.data("arrow", arrowState);
        elem.html(content);
    }
</script>
