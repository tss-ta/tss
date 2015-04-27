<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/admin/group" method="post">
    <h2 class="form-signin-heading">Add group</h2>
    <p class="form-signin-heading">group name</p>
    <input type="text" name="name">
    <p>category</p>
    <div class="checkbox">
        <label class="checkbox"><input type="checkbox" name="admin" >admin</label>
        <label class="checkbox"><input type="checkbox" name="driver">driver</label>
        <label class="checkbox"><input type="checkbox" name="customer">customer</label>
        <label class="checkbox"><input type="checkbox" name="banned">banned</label>
    </div>
    <input type="hidden" name="action" value="newgroup">
    <button class="btn btn-lg btn-primary" type="submit">add group</button>
</form>
