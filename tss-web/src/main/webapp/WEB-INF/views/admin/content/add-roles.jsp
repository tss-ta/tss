<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center">
            <h1>Edit user ${param.email} roles</h1>     
        </div>
    </div>
</div>
<div class="row row-fix">
    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/admin" method="post" class="form-horizontal">
                    <%@ include file="../partials/rolescheckboxes.jspf" %>
                    <input type="hidden" name="id" value="${param.id}">
                    <input type="hidden" name="menu" value="users">
                    <input type="hidden" name="action" value="add-roles">
                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-3">
                            <button type="submit" class="btn btn-default"> Ok </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
