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
        <c:set var="backButton" value="/admin?menu=report&action=view&id=${reportInfo.id}" />
    </c:when>
    <c:when test="${formType eq 'create'}">
        <c:set var="pageTitle" value="Create new Report" />
        <c:set var="buttonName" value="Create" />
        <c:set var="formAction" value="&action=add" />
        <c:set var="backButton" value="/admin?menu=report&action=all" />
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

                <form action="/admin?menu=report${formAction}" method="post" class="form-horizontal" id="reportForm">
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
                            <a href="${backButton}" class="btn btn-default">Cancel</a>
                            <button type="submit" class="btn btn-default">${buttonName}</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        $('#reportForm').formValidation(getOptions(false));
    });

    function getOptions(enabled) {
        return {
            framework: 'bootstrap',
            icon: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    validators: {
                        notEmpty: {
                            message: 'The name is required'
                        },
                        stringLength: {
                            min: 6,
                            max: 100,
                            message: 'The name must be more than 6 and less than 100 characters long'
                        }
                    }
                },
                selectQuery: {
                    validators: {
                        notEmpty: {
                            message: 'The select query is required'
                        }
                    }
                },
                countQuery: {
                    validators: {
                        notEmpty: {
                            enabled: enabled,
                            message: 'The count query is required'
                        }
                    }
                },
                pageSize: {
                    validators: {
                        notEmpty: {
                            enabled: enabled,
                            message: 'The page size is required'
                        },
                        numeric: {
                            enabled: enabled,
                            message: 'The page size must be a number'
                        },
                        between: {
                            enabled: enabled,
                            min: 1,
                            max: 100,
                            message: 'The page size must be between 1 and 100'
                        }
                    }
                },
                exportSize: {
                    validators: {
                        notEmpty: {
                            enabled: enabled,
                            message: 'The export size is required'
                        },
                        numeric: {
                            enabled: enabled,
                            message: 'The export size must be a number'
                        },
                        between: {
                            enabled: enabled,
                            min: 1,
                            max: 10000,
                            message: 'The export size must be between 1 and 10000'
                        }
                    }
                }
            }
        };
    }
</script>
<script>
    function switchPagerFieldValidation(element, enable) {
        element.formValidation('enableFieldValidators', 'countQuery', enable);
        element.formValidation('enableFieldValidators', 'pageSize', enable);
        element.formValidation('enableFieldValidators', 'exportSize', enable);
//        element.formValidation(getOptions(enable)).formValidation('validate');
    };

    $('#countable').change(function() {
        var switcher = $('.switcher');
        var form = $('#reportForm');
        if(this.checked == true) {
            switchPagerFieldValidation(form, true);
            switcher.removeClass('hide-position');
        } else if (this.checked == false) {
            switchPagerFieldValidation(form, false);
            switcher.addClass('hide-position');
        }
    });
</script>