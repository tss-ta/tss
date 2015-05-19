<%--
  User: Kyrylo Berehovyi
  Date: 19/05/2015
  Time: 23:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>Mailer state</h1>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${mailerState}">
        <c:set var="stateColor" value="${'green-color'}"/>
        <c:set var="stateName" value="${'ON'}"/>
    </c:when>
    <c:when test="${!mailerState}">
        <c:set var="stateColor" value="${'red-color'}"/>
        <c:set var="stateName" value="${'OFF'}"/>
    </c:when>
</c:choose>

<div class="row row-fix">
    <div class="col-md-offset-4 col-md-4 col-sm-offset-3 col-sm-6 col-xs-0 col-xs-12">
        <div class="panel panel-default">
            <div class="panel-body text-center">
                <h3 class="${stateColor}"><i class="fa fa-lightbulb-o"></i> ${stateName}</h3>
                <br/>
                <form action="/admin?menu=mailer&action=state" method="post">
                    <button type="submit" class="btn btn-default" data-toggle="modal" data-target="#delModal">
                        Change
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
