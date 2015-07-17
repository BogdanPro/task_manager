<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Prog.kiev.ua</title>
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
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
<a href="/user/index">To user panel</a>
<a href="/user/NewTaskForm">Create new task</a>
<sec:authentication var="user" property="principal" />
<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
    ${user.username}
</sec:authorize>
<table>
    <tr>
        <td>
        <form class="form-inline" id="search-form" action="/search" method="get" role="form" style="display: block;">
            <select name="groupName" id="groupName" class="form-control" style="width: 150px">
                <option>test</option>
                <option>none</option>
                <option>all</option>
            </select>
            <div class="form-group">
                <input type="text" name="email"  id="email" tabindex="1" class="form-control" placeholder="user email" value="">
            </div>
            <div class="form-group">
                <input type="text" name="namePattern"  id="namePattern" tabindex="1" class="form-control" placeholder="task name" value="" >
            </div>
            <div class="form-group">
                <input type="text" name="descriptionPattern" id="descriptionPattern" tabindex="1" class="form-control" placeholder="description" value="">
            </div>
            <%--<div class="form-group">--%>
                <%--<input type="date" id="deadline" tabindex="1" class="form-control" value="" >--%>
            <%--</div>--%>
            <input type="submit" name="submit" value="Search">
        </form>
        </td>
    </tr>


    <c:forEach items="${tasks}" var="task">
        <tr>

            <td><a href="/task/${task.id}" target="_blank">${task.name}</a></td>
            <td>${task.description}</td>
            <td>${task.group.name}</td>
            <td><a href="/user/editTaskForm/${task.id}" target="/user/editTaskForm/${task.id}">edit</a></td>
            <td><a href="/delete/${task.id}&${tasks}" target="_blank">delete</a></td>

        </tr>
    </c:forEach>
</table>
</body>
</html>