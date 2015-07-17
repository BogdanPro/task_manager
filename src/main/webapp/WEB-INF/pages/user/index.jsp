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
<a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
<a href="/user/NewTaskForm/0">Create new task</a>
<sec:authentication var="user" property="principal" />
<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
    ${user.username}
</sec:authorize>
<table>
<c:forEach items="${Tasks}" var="Task">
    <tr>
        <td><a href="/task/${Task.id}">${Task.name}</a></td>
        <td>${Task.description}</td>
    </tr>
</c:forEach>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="/admin/search">Moderate tasks</a>
</sec:authorize>
</body>
</html>