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

                            <div id="btnParams"></div>

                            <input type="hidden" name="id" value="${report.info.id}">

                            <input type="hidden" name="page" id="pageNumber">

                            <input type="hidden" value="${report.info.filter.size()}" name="crAmount">

                            <c:forEach items="${report.info.filter}" var="criterion">
                                <div class="col-md-6">
                                    <div class="form-group">

                                        <input type="hidden" name="crType${criterion.sequentialNumber}" value="${criterion.type}">

                                        <c:if test="${not empty filter}">
                                            <c:set var="rowColumn" value="${filter.get(criterion.sequentialNumber - 1)}"/>
                                            <c:choose>
                                                <c:when test="${rowColumn.type eq 'INTEGER'}">
                                                    <c:set var="criterionValue" value="${rowColumn.intValue}"/>
                                                </c:when>
                                                <c:when test="${rowColumn.type eq 'STRING'}">
                                                    <c:set var="criterionValue" value="${rowColumn.stringValue}"/>
                                                </c:when>
                                                <c:when test="${rowColumn.type eq 'DOUBLE'}">
                                                    <c:set var="criterionValue" value="${rowColumn.doubleValue}"/>
                                                </c:when>
                                                <c:when test="${rowColumn.type eq 'BOOLEAN'}">
                                                    <c:if test="${rowColumn.booleanValue}">
                                                        <c:set var="criterionValue" value="checked"/>
                                                    </c:if>
                                                </c:when>
                                                <c:when test="${rowColumn.type eq 'TIMESTAMP'}">
                                                    <c:set var="criterionValue" value="${rowColumn.timestampValue}"/>
                                                </c:when>
                                                <c:when test="${rowColumn.type eq 'LONG'}">
                                                    <c:set var="criterionValue" value="${rowColumn.longValue}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    unknown
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>

                                        <c:choose>

                                            <%-- STRING --%>
                                            <c:when test="${criterion.type eq 0}">
                                                <label for="cr${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class=" col-md-6">
                                                    <input type="text" class="form-control" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" value="${criterionValue}">
                                                </div>
                                            </c:when>

                                            <%-- INTEGER --%>
                                            <c:when test="${criterion.type eq 1}">
                                                <label for="cr${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class=" col-md-6">
                                                    <input type="text" class="form-control" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" value="${criterionValue}">
                                                </div>
                                            </c:when>

                                            <%-- BOOLEAN --%>
                                            <c:when test="${criterion.type eq 2}">
                                                <div class="checkbox col-md-offset-5 col-md-7 text-left">
                                                    <input type="checkbox" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" ${criterionValue} style="styled">
                                                    <label for="cr${criterion.id}"><b>${criterion.name}</b></label>
                                                </div>
                                            </c:when>

                                            <%-- DOUBLE --%>
                                            <c:when test="${criterion.type eq 3}">
                                                <label for="cr${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class=" col-md-6">
                                                    <input type="text" class="form-control" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" value="${criterionValue}">
                                                </div>
                                            </c:when>

                                            <%-- TIMESTAMP --%>
                                            <c:when test="${criterion.type eq 4}">
                                                <label for="for${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class="col-md-6">
                                                    <input type="text" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" class="form-control datePicker" value="${criterionValue}">
                                                </div>
                                            </c:when>

                                            <%-- LONG --%>
                                            <c:when test="${criterion.type eq 5}">
                                                <label for="cr${criterion.id}" class="control-label col-md-6">${criterion.name}:</label>
                                                <div class=" col-md-6">
                                                    <input type="text" class="form-control" id="cr${criterion.id}" name="crName${criterion.sequentialNumber}" value="${criterionValue}">
                                                </div>
                                            </c:when>

                                        </c:choose>
                                    </div>
                                </div>
                            </c:forEach>

                            <div class="col-md-12">
                                <hr/>
                            </div>

                            <div class="col-md-12 text-center">
                                <button type="button" class="btn btn-success" id="applyBtn" data-action="/admin"><i class="fa fa-check"></i> Apply</button>
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

            <c:if test="${!filterPager}">
                <%@ include file="../partials/pagination.jspf"%>
            </c:if>
            <c:if test="${filterPager}">
                <nav>
                    <ul class="pager custom-pager">

                        <c:if test="${not empty pager.firstPage}">
                            <li><a href="#" class="pagerBtn" data-page="${pager.firstPage}" title="First (${pager.firstPage})"><i class="fa fa-angle-double-left fa-lg"></i></a></li>
                        </c:if>

                        <c:if test="${not empty pager.previousPage}">
                            <li><a href="#" class="pagerBtn" data-page="${pager.previousPage}" title="Previous (${pager.previousPage})"><i class="fa fa-angle-left fa-lg"></i></a></li>
                        </c:if>

                        <c:if test="${not empty pager.nextPage}">
                            <li><a href="#" class="pagerBtn" data-page="${pager.nextPage}" title="Next (${pager.nextPage})"><i class="fa fa-angle-right fa-lg"></i></a></li>
                        </c:if>

                        <c:if test="${not empty pager.lastPage}">
                            <li><a href="#" class="pagerBtn" data-page="${next.previousPage}"  title="Last (${pager.lastPage})"><i class="fa fa-angle-double-right fa-lg"></i></a></li>
                        </c:if>

                    </ul>
                </nav>
            </c:if>

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
    <input type="hidden" name="action" value="filter">
</div>

<script src="/resources/customer_assets/js/anytime.5.1.0.js"></script>
<script>
    $(".datePicker").AnyTime_picker( { format: "%Y-%m-%d %h:%m:%s", firstDOW: 1 } );

    $("#applyBtn").click(function() {
        addParams($("#applyActionParams-template"))
        sendForm("#applyBtn")
    });

    $(".pagerBtn").click(function() {
        addParams($("#applyActionParams-template"))
        addPageParam($(this));
        sendForm("#applyBtn")
    });

    $("#excelBtn").click(function() {
        clearParams();
        sendForm("#excelBtn")
    });

    function addPageParam(elem) {
        var page = elem.data("page");
        $("#pageNumber").attr("value", page);
    }

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

    $('#filter').on('hide.bs.collapse', function () {
        $("#filterBtn").html("<i class=\"fa fa-chevron-down\"></i> Filter");
    });

    $('#filter').on('show.bs.collapse', function () {
        $("#filterBtn").html("<i class=\"fa fa-chevron-up\"></i> Filter");
    });
</script>
