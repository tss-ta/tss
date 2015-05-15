<%--
  User: Kyrylo Berehovyi
  Date: 15/05/2015
  Time: 15:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">

            <c:if test="${not empty car}">
                <h1>Edit Report</h1>
            </c:if>
            <c:if test="${empty car}">
                <h1>Add Report</h1>
            </c:if>

        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-2 col-md-8">
        <div class="panel panel-default">
            <div class="panel-body">

                <form action="/admin?menu=report&action=add" method="post" class="form-horizontal" data-toggle="validator">

                    <div class="form-group">
                        <br/>
                        <label for="name" class="col-md-3 control-label">Name:</label>
                        <div class="col-md-9">
                            <input type="text" class="form-control" id="name" placeholder="Report name..." name="license" autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="col-md-3 control-label">Description:</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="description" rows="5" placeholder="Report description..."></textarea>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <label for="selectQuery" class="col-md-3 control-label">Select Query:</label>
                        <div class="col-md-9">
                            <textarea class="form-control" id="selectQuery" rows="5" placeholder="SELECT column FROM table..."></textarea>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <label for="exportSize" class="col-md-3 control-label">Max export size:</label>
                        <div class="col-md-2">
                            <input type="number" class="form-control" id="exportSize" placeholder="1000"/>
                        </div>
                    </div>

                    <hr/>

                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" ${available} name="countable" id="countable"> Countable
                                </label>
                            </div>
                        </div>
                    </div>

                    <div class="switcher hide-position">
                        <div class="form-group">
                            <label for="countQuery" class="col-md-3 control-label">Count Query:</label>
                            <div class="col-md-9">
                                <textarea class="form-control" id="countQuery" rows="5" placeholder="SELECT count(column) FROM table..."></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pageSize" class="col-md-3 control-label">Page size:</label>
                            <div class="col-md-2">
                                <input type="number" class="form-control" id="pageSize" placeholder="15"/>
                            </div>
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
