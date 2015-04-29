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
            <c:choose>
                <c:when test="${param.action eq 'edit-group'}">
                    <h1>Edit Group Form</h1>
                </c:when>
                <c:otherwise>
                    <h1>Add Group Form</h1>
                </c:otherwise>
            </c:choose>           
        </div>
    </div>
</div>
<div class="row row-fix">
    <div class="col-md-offset-3 col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <form action="/admin/group" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="name" class="col-md-4 control-label">Group name</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="name" placeholder="Name" name="name" value="${param.name}">
                        </div>
                    </div>
                    <%@ include file="../partials/rolescheckboxes.jspf" %>
                    <c:choose>
                        <c:when test="${param.action eq 'edit-group'}">
                            <input type="hidden" name="action" value="edit-group">
                            <input type="hidden" name="id" value="${param.id}">
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="action" value="newgroup">
                        </c:otherwise>
                    </c:choose> 
                    <div class="form-group">
                        <div class="col-sm-offset-5 col-sm-3">
                            <button type="submit" class="btn btn-default">Add <i class="fa fa-users"></i></button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
