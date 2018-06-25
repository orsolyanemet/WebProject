<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.User"%>
<%@page import="ro.edu.ubb.entity.Program"%>
<%@page import="ro.edu.ubb.service.UserService"%>
<%@page import="ro.edu.ubb.service.ProgramService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"  errorPage="error.jsp" %>
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
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/admin.js"></script>
<script src="js/addtask.js"></script>
</head>
<body>
	<h1>Add task</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<form name="addForm" method="POST">
		<div class="form-group">
        	<label>Event:</label><br>
			<input id="event" list="events" name="event"  required="required" >
	  			<datalist id="events">
	  			<%
	  				ProgramService programService = new ProgramService();
				request.getSession().setAttribute("events",programService.getAllPrograms());
				List<Program> events = (List<Program>) request.getSession().getAttribute("events");
				if (events != null)
					for (Program event : events) {%>
	    		<option ><%out.println(event.getNameProgram());%></option>
	    		<%
						}
				%>
	  			</datalist>
  		</div>
		<div class="form-group">
            <label>Task name:</label><br>
 			<input id="taskName" name="taskName"  maxlength="30" type="text"  onkeyup="validateName()" required="required"  placeholder="Enter task name...">
 			<br><span id="errorName" class="error"></span><br>
        </div>
        <div class="form-group">
        	<label>Assigned to:</label><br>
			<input id="user" list="users" name="user"  required="required">
	  			<datalist id="users">
	  			<%
	  				UserService userService = new UserService();
				request.getSession().setAttribute("users",userService.getAllUsers());
				List<User> users = (List<User>) request.getSession().getAttribute("users");
				if (users != null)
					for (User user : users) { %>
	    		<option value=<%out.print(user.getUsername());%>>
	    		<%
						}
				%>
	  			</datalist>
  		</div>
  		<div class="form-group">
			<label>Task deadline:</label><br>
			<input type="date" id="taskDate" onchange="validateDate()" name="taskDate" required="required">
			<br><span id="errorDate" class="error"></span><br>
		</div>
		<div class="form-group">
			<label>Task details:</label><br>
			<textarea id="detail" rows="20" cols="100" placeholder="Enter task details..." ></textarea><br>
		</div>
		<input id="add" type="submit" name="add" value="Add" onclick="addButtonClicked()" >
	</form>
	<br>
</body>
</html>