<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.Task"%>
<%@page import="ro.edu.ubb.service.TaskService"%>
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
<link rel="stylesheet" type="text/css" href="styles/eventtasks.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/user.js"></script>
</head>
<body>
	<h1>Others tasks</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<div class="panel panel-default inbox" id="trashPanel">
		<div class="table-responsive">
			<table id="myTable"
				class="table table-striped table-hover refresh-container pull-down">
				<%
							TaskService taskService = new TaskService();
							request.getSession().setAttribute("usersTasks",
									taskService.getAllOthersTasks((String)request.getSession().getAttribute("loggedUsername")));
							List<Task> tasks = (List<Task>) request.getSession().getAttribute("usersTasks");
							if (tasks != null && !tasks.isEmpty()){
				%>
					<thead class="hidden-xs">
					<tr>
						<td><strong>Event</strong></td>
						<td><strong>Task</strong></td>
						<td><strong>Organizer</strong></td>
						<td><strong>Solved</strong></td>
					</tr>
					</thead>
						<%
								for (Task task : tasks) {
						%>
				<tbody>
					<tr>
						<td>
							<%
								out.print(task.getPartOf());
							%>
						</td>
						<td>
							<%
								out.print(task.getTaskName());
							%>
						</td>
						<td>
							<%
								out.print(task.getAssignedTo());
							%>
						</td>
						<td>
							<%
								if(task.getIsSolved()){
									out.print("Solved");
								}
								else{
									out.print("Unsolved");
								}
							%>
						</td>
					</tr>
					<%
						}
							}else{
					%>
					<label class="errorlabel">The other organizers haven't have a task for this event.</label>
			<%
				}
			%>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>