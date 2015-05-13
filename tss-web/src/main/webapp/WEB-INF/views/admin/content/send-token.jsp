<%--
  User: Illya
  Date: 12.05.2015
  Time: 8:46
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row row-fix">
  <div class="col-md-offset-1 col-md-10">
    <div class="text-center">
      <h1>Send Token to a Driver</h1>
    </div>
  </div>
</div>
<div class="row row-fix">
  <div class="col-md-offset-3 col-md-6">
    <div class="panel panel-default">
      <div class="panel-body">
        <form action="/admin" method="post" class="form-horizontal">
          <div class="form-group">
            <label for="input_email" class="col-md-4 control-label">Driver email</label>
            <div class="col-sm-5">
              <input type="email" class="form-control" id="input_email" placeholder="Email" name="email">
            </div>
          </div>

          <div class="form-group">
            <div class="col-sm-offset-5 col-sm-3">
              <input type="hidden" name="menu" value="drivers">
              <input type="hidden" name="action" value="add">
              <button type="submit" class="btn btn-default">Send</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>
