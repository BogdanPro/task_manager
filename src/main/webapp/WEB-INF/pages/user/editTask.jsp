<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 01.07.2015
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
  <title>Prog.kiev.ua</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<title></title>
</head>
<body>
<a href="/user/index">index</a>
<tr><a href="/task/${task.id}">Go back</a></tr>
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


<form class="form-inline" id="register-form" action="/editTask" method="post" role="form" style="display: block;">
  <select name="groupName" id="groupName" class="form-control" style="width: 150px">
    <option>test</option>
    <option>none</option>
  </select>
  <div class="form-group">
    <input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="name: ${task.name}" value="" >
  </div>
  <div class="form-group">
    <input type="text" name="description" id="description" tabindex="1" class="form-control" placeholder="description: ${task.description}" value="" >
  </div>
  <sec:authorize access="hasRole('ROLE_ADMIN')">
  <div class="form-group">
    <input type="date" name="deadline" id="deadline" tabindex="1" class="form-control" value="${task.deadline}">
  </div>
  <div class="form-group">
    <input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="employee email: ${task.user.email}" value="" >
  </div>
  </sec:authorize>
  <input type="hidden" name="id" id="id" tabindex="1" class="form-control" value="${task.id}">
  <input type="submit" name="submit" value="submit">
</form>
</body>
</html>
