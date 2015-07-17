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
<c:if test="${id != 0}">
<a href="/task/${id}">Back to main task</a>
  </c:if>
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


<form class="form-inline" id="register-form" action="/addTask" method="post" role="form" style="display: block;">
  <select name="groupName" id="groupName" class="form-control" style="width: 150px">
    <option>test</option>
    <option>none</option>
  </select>
  <div class="form-group">
    <input type="text" name="name" id="name" tabindex="1" class="form-control" placeholder="name" value="" required>
  </div>
  <div class="form-group">
    <input type="text" name="description" id="description" tabindex="1" class="form-control" placeholder="description" value="" required>
  </div>
  <div class="form-group">
    <input type="date" name="deadline" id="deadline" tabindex="1" class="form-control" value="" required>
  </div>
  <input type="hidden" name="id" id="id" tabindex="1" class="form-control" value="${id}">
  <div class="form-group">
    <div class="row">
      <div class="col-sm-6 col-sm-offset-3">
        <input type="submit" name="task-submit" id="task-submit" tabindex="4" class="btn btn-default" value="Add task">
      </div>
    </div>
  </div>
</form>
</body>
</html>
