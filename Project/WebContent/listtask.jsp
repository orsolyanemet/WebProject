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
<link rel="stylesheet" type="text/css" href="styles/listtasks.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/user.js"></script>
<script src="js/listtask.js"></script>
</head>
<body>
	<h1>List tasks</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<div class="panel panel-default inbox" id="trashPanel">
		<div class="table-responsive">
			<input class="form-control" id="myInput" type="text" placeholder="Search..">
    </div>
			<table id="myTable"
				class="table table-striped table-hover refresh-container pull-down">
				<%
							TaskService taskService = new TaskService();
							request.getSession().setAttribute("tasks",
									taskService.getAllUserTasks(((String)request.getSession().getAttribute("loggedUsername"))));
							List<Task> tasks = (List<Task>) request.getSession().getAttribute("tasks");
							if (tasks != null && !tasks.isEmpty()){
				%>
					<thead class="hidden-xs">
					<tr>
						<td><strong>Event</strong></td>
						<td><strong>Task name</strong></td>
						<td><strong>Details</strong></td>
						<td><strong>Solved</strong></td>
						<td><strong>Deadline</strong></td>
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
								if(task.getDetails()!=null){
									out.print(task.getDetails());
								}
								else{
									out.print("-");
								}
							%>
						</td>
						<td>
							<%
							if(task.getIsSolved()){
								out.print("True");
							}
							else{
								out.print("False");
							}
							%>
						</td>
						<td>
							<%
								out.print(task.getDeadline());
							%>
						</td>
					</tr>
					<%
						}
							}else{
					%>
					<label class="errorlabel">You don't have any tasks.</label>
					<%
							}
					%>
				</tbody>
			</table>
		</div>

	</div>
</body>
</html>