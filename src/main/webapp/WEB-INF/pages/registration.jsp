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
<form class="form-inline" role="form" action="/">
  <input type="submit" class="btn btn-default" value="Login">
</form>
<form class="form-inline" id="register-form" action="/registration" method="post" role="form" style="display: block;">
  <div class="form-group">
    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Username" value="" required>
  </div>
  <div class="form-group">
    <input type="text" name="phone" id="phone" tabindex="1" class="form-control" placeholder="Phone" value="" required>
  </div>
  <div class="form-group">
    <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email Address" value="" required>
  </div>
  <div class="form-group">
    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password" required>
  </div>
  <div class="form-group">
    <input type="password" name="confirm_password" id="confirm_password" tabindex="2" class="form-control" placeholder="Confirm Password" required>
  </div>
  <div class="form-group">
    <div class="row">
      <div class="col-sm-6 col-sm-offset-3">
        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="btn btn-default" value="Register Now">
      </div>
    </div>
  </div>
</form>


</body>
</html>