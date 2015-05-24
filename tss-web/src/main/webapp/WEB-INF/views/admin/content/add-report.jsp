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
    <div class="col-md-offset-1 col-md-10 col-sm-offset-0 col-sm-12 col-xs-offset-0 col-xs-12">
        <div class="panel panel-default">
            <div class="panel-body">

                <form action="/admin?menu=report${formAction}" method="post" class="form-horizontal" id="reportForm">

                    <input type="hidden" name="id" value="${reportInfo.id}">

                    <div class="form-group">
                        <br/>
                        <label for="name" class="col-md-3 col-sm-3 control-label">Name:</label>
                        <div class="col-md-9 col-sm-9">
                            <input type="text" class="form-control" id="name" placeholder="Report name" name="name" value="${reportInfo.name}" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-3 col-sm-3 control-label">Description:</label>
                        <div class="col-md-9 col-sm-9">
                            <textarea class="form-control" id="description" rows="5" placeholder="Report description" name="description">${reportInfo.description}</textarea>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <label for="selectQuery" class="col-md-3 col-sm-3 control-label">Select Query:</label>
                        <div class="col-md-9 col-sm-9">
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

                    <c:if test="${reportInfo.filterable}">
                        <c:set var="filterable" value="checked"/>
                    </c:if>
                    <c:if test="${!reportInfo.filterable}">
                        <c:set var="filterHide" value="hide-position"/>
                    </c:if>

                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9 col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <%--<label>--%>
                                    <%--<input type="checkbox" id="countable" ${countable} name="countable" /> Paginable--%>
                                <%--</label>--%>

                                    <input type="checkbox" id="countable" ${countable} name="countable" >
                                    <label for="countable"><b>Pagination</b></label>
                            </div>
                        </div>
                    </div>

                    <div class="switcher ${hide}" id="countable-switcher">
                        <div class="form-group">
                            <label for="countQuery" class="col-md-3 col-sm-3 control-label">Count Query:</label>
                            <div class="col-md-9 col-sm-9">
                                <textarea class="form-control" id="countQuery" rows="5" placeholder="SELECT count(column) FROM table" name="countQuery">${reportInfo.countQuery}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="pageSize" class="col-md-3 col-sm-3 control-label">Page size:</label>
                            <div class="col-md-3 col-sm-3">
                                <input type="text" class="form-control" id="pageSize" placeholder="15" name="pageSize" value="${reportInfo.pageSize}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="exportSize" class="col-md-3 col-sm-3 control-label">Max export size:</label>
                            <div class="col-md-3 col-sm-3">
                                <input type="text" class="form-control" id="exportSize" placeholder="1000" name="exportSize" value="${reportInfo.exportSize}"/>
                            </div>
                        </div>

                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-md-offset-3 col-md-9 col-sm-offset-3 col-sm-9">
                            <div class="checkbox">
                                <%--<label>--%>
                                <%--<input type="checkbox" id="countable" ${countable} name="countable" /> Paginable--%>
                                <%--</label>--%>
                                <%--<label for="filterable"><b>Filter</b></label>--%>
                                <input type="checkbox" id="filterable" name="filterable" ${filterable}>
                                <label for="filterable"><b>Filter</b></label>
                            </div>
                        </div>
                    </div>

                    <div class="switcher ${filterHide}" id="filter-switcher">

                        <input type="hidden" name="crAmount" id="criteriaAmount" value="${reportInfo.filter.size()}">

                        <div id="criteriaContainer">

                            <c:if test="${not empty reportInfo.filter and reportInfo.filter.size() gt 0}">
                                <c:forEach begin="0" end="${reportInfo.filter.size() - 1}" var="counter">
                                    <c:set var="filterCriterion" value="${reportInfo.filter.get(counter)}"/>

                                    <div class="form-group">

                                        <input type="hidden" name="crId${filterCriterion.sequentialNumber}" value="${filterCriterion.id}">

                                        <label for="criterion" class="col-md-3 col-sm-3 col-xs-12 control-label criterion-label">Criterion ${filterCriterion.sequentialNumber}:</label>
                                        <div class="col-md-4 col-sm-4 col-xs-12 text-right">
                                            <input type="text" class="form-control criterion-name" id="criterion" placeholder="Label" name="crName${filterCriterion.sequentialNumber}" value="${filterCriterion.name}"/>
                                            <div class="visible-xs vertical-margin"></div>
                                        </div>

                                        <div class="col-md-3 col-sm-3 col-xs-12 text-left">
                                            <select class="form-control criterion-type" name="crType${filterCriterion.sequentialNumber}">
                                                <c:forEach var="type" items="${dataTypes}">
                                                    <c:if test="${type.ordinal() == filterCriterion.type}">
                                                        <c:set value="selected" var="selected"/>
                                                    </c:if>
                                                    <c:if test="${type.ordinal() != filterCriterion.type}">
                                                        <c:set value="" var="selected"/>
                                                    </c:if>
                                                    <option value="${type.ordinal()}" ${selected}>${type.name()}</option>
                                                </c:forEach>

                                            </select>
                                            <div class="visible-xs vertical-margin"></div>
                                        </div>

                                        <input type="hidden" class="criterion-seq-num" name="crSeqNum${filterCriterion.sequentialNumber}" value="${filterCriterion.sequentialNumber}">

                                        <div class="col-md-2 col-sm-2 col-xs-12 text-center">
                                            <a class="btn btn-danger delete-criterion hidden-xs"><i class="fa fa-trash-o"></i></a>
                                            <a class="btn btn-danger delete-criterion col-xs-offset-3 col-xs-6 visible-xs"><i class="fa fa-trash-o"></i></a>
                                        </div>

                                    </div>

                                </c:forEach>
                            </c:if>


                        </div>

                        <div class="form-group">
                            <%--<label for="addCriteriaBtn" class="col-md-3 control-label">Filter:</label>--%>
                            <div class="col-md-offset-10 col-md-2 col-sm-offset-10 col-sm-2 col-xs-12 text-center">
                                <a class="btn btn-success addCriterionBtn hidden-xs"><i class="fa fa-plus"></i></a>
                                <a class="btn btn-success addCriterionBtn visible-xs col-xs-offset-3 col-xs-6"><i class="fa fa-plus"></i></a>
                            </div>
                        </div>

                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-md-12 col-sm-12 text-center">
                            <a href="${backButton}" class="btn btn-danger">Cancel</a>
                            <button type="submit" class="btn btn-primary">${buttonName}</button>
                        </div>
                    </div>

                </form>

            </div>
        </div>
    </div>
</div>

<div class="not-to-show" id="criterion-template" data-number="0">
    <div class="form-group">
        <label for="criterion" class="col-md-3 col-sm-3 col-xs-12 control-label criterion-label"></label>
        <div class="col-md-4 col-sm-4 col-xs-12 text-right">
            <input type="text" class="form-control criterion-name" id="criterion" placeholder="Label"/>
            <div class="visible-xs vertical-margin"></div>
        </div>

        <div class="col-md-3 col-sm-3 col-xs-12 text-left">
            <select class="form-control criterion-type">
                <c:forEach var="type" items="${dataTypes}">
                    <option value="${type.ordinal()}">${type.name()}</option>
                </c:forEach>
            </select>
            <div class="visible-xs vertical-margin"></div>
        </div>

        <input type="hidden" class="criterion-seq-num">

        <div class="col-md-2 col-sm-2 col-xs-12 text-center">
            <a class="btn btn-danger delete-criterion hidden-xs"><i class="fa fa-trash-o"></i></a>
            <a class="btn btn-danger delete-criterion col-xs-offset-3 col-xs-6 visible-xs"><i class="fa fa-trash-o"></i></a>
        </div>

    </div>
</div>

<script>
    var criteriaNameValidators = {
                validators: {
                    notEmpty: {
                        message: 'The criteria name is required'
                    }
                }
            };

    $('.addCriterionBtn').click(function() {
        $('#criteriaContainer').append($('#criterion-template').html());
        updateCriteria();
        var name = 'crName' + $('#criteriaAmount').val();
        $('#reportForm').formValidation('addField', name, criteriaNameValidators);
    });

    $(document).on("click", ".delete-criterion", function() {
        $(this).closest('.form-group').remove();
        $("#reportForm").formValidation('enableFieldValidators', 'filterable', true);
        updateCriteria();
    });

    function updateCriteria() {
        $('#criteriaAmount').val(updateCriteriaSerialNumbers());
        $("#reportForm").formValidation('revalidateField', "filterable");
    }

    function updateCriteriaSerialNumbers() {
        var counter = 0;
        $('#criteriaContainer .form-group').each(function() {
            counter++;

            $(this).find('.criterion-label').html('Criterion ' + counter + ":");
            $(this).find('.criterion-name').attr('name','crName' + counter);
            $(this).find('.criterion-type').attr('name','crType' + counter);
            $(this).find('.criterion-seq-num').attr('name','crSeqNum' + counter);
            $(this).find('.criterion-seq-num').attr('value', counter);
        });
        return counter;
    }

    function addCriterionValidator() {
        var amount = $("#criteriaContainer .criterion-name").length;
//        alert(amount);
        var form = $('#reportForm');
        for ( var i = 1; i <= amount; i++) {
            form.formValidation('addField', "crName" + i, criteriaNameValidators);
        }

    }
</script>

<script>

    $(document).ready(function() {

        FormValidation.Validator.filter = {
            validate: function(validator, $field, options) {
                var container = $("#criteriaContainer");
                if (container.children().length == 0) {
                    return false;
                }
                return true;
            }
        };

        $('#reportForm').formValidation(getOptions(false, false));

//        $(document).ready(function() {
//            $('#reportForm').formValidation(getOptions(false, false));
//        });

        addCriterionValidator();
    });

    function getOptions(paggingState, filterState) {
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
                            enabled: paggingState,
                            message: 'The count query is required'
                        }
                    }
                },
                pageSize: {
                    validators: {
                        notEmpty: {
                            enabled: paggingState,
                            message: 'The page size is required'
                        },
                        numeric: {
                            enabled: paggingState,
                            message: 'The page size must be a number'
                        },
                        between: {
                            enabled: paggingState,
                            min: 1,
                            max: 100,
                            message: 'The page size must be between 1 and 100'
                        }
                    }
                },
                exportSize: {
                    validators: {
                        notEmpty: {
                            enabled: paggingState,
                            message: 'The export size is required'
                        },
                        numeric: {
                            enabled: paggingState,
                            message: 'The export size must be a number'
                        },
                        between: {
                            enabled: paggingState,
                            min: 1,
                            max: 10000,
                            message: 'The export size must be between 1 and 10000'
                        }
                    }
                },
                filterable: {
                    validators: {
                        filter: {
                            enabled: filterState,
                            message: 'If filter selected, filter must not be empty'
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
    };

    function switchFilterFieldValidation(element, enable) {
        element.formValidation('enableFieldValidators', 'filterable', enable);
    };

    $('#countable').change(function() {
        invertVisibility("#countable-switcher", '#reportForm', switchPagerFieldValidation, this);
    });

    $('#filterable').change(function() {
        invertVisibility("#filter-switcher", '#reportForm', switchFilterFieldValidation, this);
    });

    function invertVisibility(changableElem, form, validationFunction, checkbox) {
        var switcher = $(changableElem);
        var form = $(form);
        if(checkbox.checked == true) {
            validationFunction(form, true);
            switcher.removeClass('hide-position');
        } else {
            validationFunction(form, false);
            switcher.addClass('hide-position');
        }
    };

</script>