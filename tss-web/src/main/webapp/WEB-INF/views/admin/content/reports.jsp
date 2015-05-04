<%--
  authors: Kyrylo Berehovyi, maks
  Date: 27/04/2015
  Time: 01:55
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <h1 class="text-center">Reports Panel</h1>
        
        <ul>
            <li><a href="/admin/report?action=profitable-service">Most profitable taxi service</a></li>
            <li><a href="/admin/report?action=">Most popular car and driver category</a></li>
            <li><a href="/admin/report?action=customer-popular-car-options">Most popular additional car options for each customer user</a></li>
            <li><a href="/admin/report?action=overall-popular-car-options">Most popular additional car options overall</a></li>
            <li><a href="/admin/report?action=new-orders-report">New orders per period</a></li>
            <li><a href="/admin/report?action=">Service profitability by month</a></li>
        </ul>
    </div>
    <div class="col-md-1"></div>
</div>