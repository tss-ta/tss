<%--
  User: Kyrylo Berehovyi
  Date: 27/04/2015
  Time: 02:19
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/admin/car" method="post">
    <h2 class="form-signin-heading">Add car</h2>
    <p class="form-signin-heading">license plate</p>
    <input type="text" name="plate">
    <p>category</p>
    <select name="category">
        <option value="1">business</option>
        <option value="2">econom</option>
        <option value="3">van</option>
        <option value="4">cargo</option>
    </select>
    <div class="checkbox">
        <label class="checkbox"><input type="checkbox" name="avaliable" > availible</label>
        <label class="checkbox"><input type="checkbox" name="animalable">animaliable</label>
        <label class="checkbox"><input type="checkbox" name="wi-fi">wi-fi</label>
        <label class="checkbox"><input type="checkbox" name="conditioner"> conditioner</label>
    </div>
    <input type="hidden" name="action" value="newcar">
    <button class="btn btn-lg btn-primary" type="submit">add car</button>
</form>
