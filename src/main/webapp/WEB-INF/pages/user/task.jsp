<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
  <title>Prog.kiev.ua</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<a href="/user/index">index</a>
<a href="/user/NewTaskForm/${task.id}">Create new subtask</a>
<a href="/user/editTaskForm/${task.id}">Edit task</a>
<sec:authorize access="hasRole('ROLE_ADMIN')">
  <a href="/admin/search">Moderate tasks</a>
</sec:authorize>
<table border="1">
  <tr>
  <td>name: ${task.name}</td>
  <td>description :${task.description}</td>
  <td>completeness: ${task.completeness} %</td>
  <td>group: ${task.group.name}</td>
  <td>creation time: ${task.creationTime}</td>
  <td>deadline: ${task.deadline}</td>
  <td>upper task: <a href="/task/${task.mainTask.id}">${task.mainTask.name}</a></td>
  <td><c:if test="${fn:length(task.subTasks) gt 0}">
    subtasks:
    <c:forEach items="${task.subTasks}" var="subTask">
      <a href="/task/${subTask.id}">${subTask.name}</a> <br/>
    </c:forEach>
      </c:if>
    <c:if test="${fn:length(task.subTasks) lt 1}">
    <a href="/complete/${task.id}">complete</a>
      </c:if>
  </td>
  </tr>
</table>
<table>
  <tr>
    <td>
    <form class="form-inline" id="register-form" action="/addComment" method="post" role="form" style="display: block;">
      <textarea name="text" id="text" maxlength="20000"></textarea>
      <input type="hidden" name="taskId" id="taskId" tabindex="1" class="form-control" value="${task.id}">
      <div class="form-group">
        <div class="row">
          <div class="col-sm-6 col-sm-offset-3">
            <input type="submit" name="task-submit" id="task-submit" tabindex="4" class="btn btn-default" value="Add comment">
          </div>
        </div>
      </div>
    </form>
    </td>
  </tr>
  <c:forEach items="${task.commentaries}" var="comment">
    <tr><td>${comment.user.name} (${comment.user.email})</td></tr>
    <tr>
      <td>
        ${comment.text}
      </td>
      <td>${comment.creationTime}</td>
    </tr>
  </c:forEach>
</table>
</body>
</html>