<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.User"%>
<%@page import="ro.edu.ubb.entity.Task"%>
<%@page import="ro.edu.ubb.entity.Program"%>
<%@page import="ro.edu.ubb.service.UserService"%>
<%@page import="ro.edu.ubb.service.TaskService"%>
<%@page import="ro.edu.ubb.service.ProgramService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Event Organizer</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="styles/bootstrap.css">
<link rel="stylesheet" type="text/css" href="styles/menu.css">
<link rel="stylesheet" type="text/css" href="styles/form.css">
<link rel="stylesheet" type="text/css" href="styles/changestatus.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/user.js"></script>
<script src="js/changestatus.js"></script>
</head>
<body>
	<h1>Change status</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<samp id="msg"></samp>
	<form name="statusForm" method="POST">
			<%
				TaskService taskService = new TaskService();
				request.getSession().setAttribute("tasks",
						taskService.getAllUnresolvedTasks((String) request.getSession().getAttribute("loggedUsername")));
				List<Task> tasks = (List<Task>) request.getSession().getAttribute("tasks");
				if (!tasks.isEmpty()) {
			%>
			<div class="form-group">
			<label>Task:</label><br> <input id="task" list="tasks"
				name="task" required="required">
			<datalist id="tasks">
				<%
					for (Task task : tasks) {
				%>
				<option>
					<%
						out.println(task.getTaskName());
					%>
				</option>
				<%
					}
				%>
				</datalist>
				</div>
				<div class="form-group">
					<label>Solved:</label> <input type="checkbox" id="solved"
						name="solved" value="solved" />
				</div>
				<div class="form-group">
					<label>Add report:</label><br>
					<textarea id="report" rows="5" cols="50"
						placeholder="Enter report..."></textarea>
					<br>
				</div>
				<input id="submit" type="submit" name="submit" value="Submit"
					onclick="submitButtonClicked()">
				<%
					} else {
				%><label class="errorlabel">All tasks are solved.</label>
				<%
					}
				%>
	</form>
	<br>
</body>
</html>