<%--
  User: Kyrylo Berehovyi
  Date: 08/05/2015
  Time: 11:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty reportInfo}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center page-title">
                <h1>Car Details</h1>
            </div>
        </div>
    </div>

    <div class="row row-fix">
        <div class="col-md-offset-4 col-md-4">
            <div class="panel panel-default">
                <div class="panel-body">

                    <div class="col-md-6 text-right">
                        <br/>
                        <p>ID</p>
                    </div>
                    <div class="col-md-6 text-left">
                        <br/>
                        <p>
                                ${reportInfo.id}
                        </p>
                    </div>

                    <div class="col-md-12">
                        <hr/>
                    </div>

                    <div class="col-md-6 text-right">
                        <p>
                            ${reportInfo.name}
                            <%--<c:if test="${reportInfo.name}">--%>
                                <%--<i class="fa fa-check"></i>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${!car.available}">--%>
                                <%--<i class="fa fa-minus"></i>--%>
                            <%--</c:if>--%>
                        </p>
                        <p>
                                ${reportInfo.description}
                            <%--<c:if test="${car.animalable}">--%>
                                <%--<i class="fa fa-check"></i>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${!car.animalable}">--%>
                                <%--<i class="fa fa-minus"></i>--%>
                            <%--</c:if>--%>
                        </p>
                        <p>
                                ${reportInfo.query}
                            <%--<c:if test="${car.wifi}">--%>
                                <%--<i class="fa fa-check"></i>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${!car.wifi}">--%>
                                <%--<i class="fa fa-minus"></i>--%>
                            <%--</c:if>--%>
                        </p>
                        <p>
                            <%--<c:if test="${car.conditioner}">--%>
                                <%--<i class="fa fa-check"></i>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${!car.conditioner}">--%>
                                <%--<i class="fa fa-minus"></i>--%>
                            <%--</c:if>--%>
                        </p>
                    </div>
                    <div class="col-md-6 text-left">
                        <p>Description</p>
                        <p>Name</p>
                        <p>Query</p>
                    </div>

                    <div class="col-md-12">
                        <hr/>
                    </div>


                    <div class="col-md-12">
                        <hr/>
                    </div>

                    <div class="col-md-offset-5 col-sm-2">
                        <a href="/admin?menu=car&action=edit&id=${car.id}" class="btn btn-default">Edit</a>
                    </div>

                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${empty reportInfo}">
    <div class="row row-fix">
        <div class="col-md-offset-1 col-md-10">
            <div class="text-center page-title">
                <h1>
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-search fa-stack-1x"></i>
                        <i class="fa fa-ban fa-stack-2x text-danger"></i>
                    </span>
                    Can't find any Report with specified ID number.
                </h1>
            </div>
        </div>
    </div>
</c:if>
