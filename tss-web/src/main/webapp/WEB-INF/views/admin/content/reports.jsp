<%--
  authors: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- OLD VERSION :--%>
<%--<div class="row row-fix">--%>
    <%--<div class="col-md-1"></div>--%>
    <%--<div class="col-md-10">--%>
        <%--<h1 class="text-center">Reports Panel</h1>--%>

        <%--<ul>--%>
            <%--<li><a href="/admin/report?action=most-profitable-service">Most profitable taxi service</a></li>--%>
            <%--<li><a href="/admin/report?action=popular-car-category">Most popular car category</a></li>--%>
            <%--<li><a href="/admin/report?action=popular-driver-category">Most popular driver category</a></li>--%>
            <%--<li><a href="/admin/report?action=customer-popular-car-options">Most popular additional car options for each customer user</a></li>--%>
            <%--<li><a href="/admin/report?action=overall-popular-car-options">Most popular additional car options overall</a></li>--%>
            <%--<li><a href="/admin/report?action=new-orders-report">New orders per period</a></li>--%>
            <%--<li><a href="/admin/report?action=mont-service-profitability">Service profitability by month</a></li>--%>
        <%--</ul>--%>
    <%--</div>--%>
    <%--<div class="col-md-1"></div>--%>
<%--</div>--%>

<%-- NEW VERSION--%>

<div class="row row-fix">
    <div class="col-md-offset-1 col-md-10">
        <div class="text-center page-title">
            <h1>All Reports</h1>
        </div>
    </div>
</div>

<div class="row row-fix">
    <div class="col-md-offset-2 col-md-8">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="col-md-offset-0 col-md-1 col-sm-offset-0 col-sm-2 col-xs-offset-4 col-xs-4">
                    <a href="/admin?menu=report&action=add" class="btn btn-default">Add Report</a>
                </div>
            </div>
        </div>
    </div>
</div>

<c:if test="${not empty reportList}">
    <div class="row row-fix">
        <div class="col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10 col-xs-offset-0 col-xs-12">
            <div class="list-group custom-list-group">
                <a href="/admin/report?action=new-orders-report" class="list-group-item">
                    <h4 class="list-group-item-heading">New orders report <small>All new orders by specified period</small></h4>
                        <%--<p class="list-group-item-text">${report.description}</p>--%>
                </a>
                <a href="/admin/report?action=customer-popular-car-options" class="list-group-item">
                    <h4 class="list-group-item-heading">Popular user's car options report
                        <small>This report shows how often user order taxi with each car option</small></h4>
                </a>
                <c:forEach items="${reportList}" var="report" >
                    <a href="/admin?menu=report&action=view&id=${report.id}" class="list-group-item">
                        <h4 class="list-group-item-heading">${report.name} - <small>${report.description}</small></h4>

                        <%--<p class="list-group-item-text">${report.description}</p>--%>
                    </a>
                </c:forEach>
            </div>

            <%@ include file="../partials/pagination.jspf"%>

        </div>
    </div>
</c:if>

<c:if test="${empty reportList}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center">
                <h3>No Reports found</h3>
            </div>
        </div>
    </div>
</c:if>