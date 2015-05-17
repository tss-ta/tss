<%--
  User: Kyrylo Berehovyi
  Date: 15/05/2015
  Time: 15:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${formType eq 'edit'}">
        <c:set var="pageTitle" value="Edit Report" />
        <c:set var="buttonName" value="Apply" />
        <c:set var="formAction" value="&action=edit" />
    </c:when>
    <c:when test="${formType eq 'create'}">
        <c:set var="pageTitle" value="Create new Report" />
        <c:set var="buttonName" value="Create" />
        <c:set var="formAction" value="&action=add" />
    </c:when>
</c:choose>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>${pageTitle}</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-2 col-md-8">
        <div class="panel panel-default">
            <div class="panel-body">

                <form action="/admin?menu=report${formAction}" method="post" class="form-horizontal" data-toggle="validator">
                    <input type="hidden" name="id" value="${reportInfo.id}">
                    <div class="form-group">
                        <br/>
                        <label for="name" class="col-md-3 control-label">Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="name" placeholder="Report name" name="name" value="${reportInfo.name}" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-3 control-label">Description:</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="description" rows="5" placeholder="Report description" name="description">${reportInfo.description}</textarea>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <label for="selectQuery" class="col-md-3 control-label">Select Query:</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="selectQuery" rows="5" placeholder="SELECT column FROM table LIMIT ? OFFSET ?" name="selectQuery">${reportInfo.selectQuery}</textarea>
                        </div>
                    </div>

                    <hr/>

                    <c:if test="${reportInfo.countable}">
                        <c:set var="countable" value="checked"/>
                    </c:if>
                    <c:if test="${!reportInfo.countable}">
                        <c:set var="hide" value="hide-position"/>
                    </c:if>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="countable" ${countable} name="countable" /> Paginable
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="switcher ${hide}">
                        <div class="form-group">
                            <label for="countQuery" class="col-md-3 control-label">Count Query:</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="countQuery" rows="5" placeholder="SELECT count(column) FROM table" name="countQuery">${reportInfo.countQuery}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pageSize" class="col-md-3 control-label">Page size:</label>
                            <div class="col-md-2">
                                <input type="number" class="form-control" id="pageSize" placeholder="15" name="pageSize" value="${reportInfo.pageSize}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="exportSize" class="col-md-3 control-label">Max export size:</label>
                            <div class="col-md-2">
                                <input type="number" class="form-control" id="exportSize" placeholder="1000" name="exportSize" value="${reportInfo.exportSize}"/>
                            </div>
                        </div>

                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn btn-default">${buttonName}</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<script>
    $('#countable').change(function() {
        var switcher = $('.switcher');
        if(this.checked == true) {
            switcher.removeClass('hide-position');
        } else if (this.checked == false) {
            switcher.addClass('hide-position');
        }
    });
</script>
