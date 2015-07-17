<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Task manager</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%
  String msg = (String)request.getAttribute("errMsg");
  if(msg != null) {
%>
<div class="alert alert-danger" role="alert">
  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
  <span class="sr-only">Error:</span>
  <%= msg %>
</div>
<%
  }
%>
<c:url value="/j_spring_security_check" var="loginUrl"/>
<table>
  <tr>
<form class="form-inline" role="form" action="/registrationForm">
  <input type="submit" class="btn btn-default" value="Registration">
</form>
  </tr>
  <tr>
<form class="form-inline" id="login-form" action='${loginUrl}' method="post" role="form" style="display: block;">
  <tr>
  <div class="form-group">
    <input type="text" name="username" id="j_username" tabindex="1" class="btn btn-default" placeholder="Email">
  </div>
  </tr>
  <tr>
  <div class="form-group">
    <input type="password" name="password" id="j_password" tabindex="2" class="btn btn-default" placeholder="Password">
  </div>
  </tr>
  <tr>
  <div class="form-group">
    <div class="row">
      <div class="col-sm-6 col-sm-offset-3">
        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="btn btn-default" value="Log In">
      </div>
    </div>
  </div>
  </tr>
</form>
</table>

</body>
</html>