<%--
  authors: maks
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1 class="text-center">Reports Panel</h1>
        
        <ul>
            <li><a href="/admin/report?action=most-profitable-service">Most profitable taxi service</a></li>
            <li><a href="/admin/report?action=popular-car-category">Most popular car category</a></li>
            <li><a href="/admin/report?action=popular-driver-category">Most popular driver category</a></li>
            <li><a href="/admin/report?action=customer-popular-car-options">Most popular additional car options for each customer user</a></li>
            <li><a href="/admin/report?action=overall-popular-car-options">Most popular additional car options overall</a></li>
            <li><a href="/admin/report?action=new-orders-report">New orders per period</a></li>
            <li><a href="/admin/report?action=mont-service-profitability">Service profitability by month</a></li>
        </ul>
    </div>
    <div class="col-md-1"></div>
</div>