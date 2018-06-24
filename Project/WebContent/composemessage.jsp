<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.User"%>
<%@page import="ro.edu.ubb.service.UserService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage="error.jsp" %>
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
<script src="js/compose.js"></script>
</head>
<body>
	<h1>Compose message</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<form name="composeFrom" method="POST">
		<div class="form-group">
        	<label>To:</label><br>
			<input id="user" list="users" name="user"  required="required">
	  			<datalist id="users">
	  			<%
	  				UserService userService = new UserService();
				request.getSession().setAttribute("users",userService.getAllUsers());
				List<User> users = (List<User>) request.getSession().getAttribute("users");
				if (users != null)
					for (User user : users) { %>
	    		<option value=<%out.print(user.getEmail());%>>
	    		<%
						}
				%>
	  			</datalist>
  		</div>
		<div class="form-group">
            <label>Subject:</label><br>
 			<input name="subject"  maxlength="30" type="text" required="required"  placeholder="Enter subject...">
        </div>
		<div class="form-group">
			<label>Message:</label><br>
			<textarea id="message" rows="20" cols="100" placeholder="Enter message..." required></textarea><br>
		</div>
		<input id="send" type="submit" name="send" value="Send" onclick="sendButtonClicked()" >
	</form>
	<br>
</body>
</html>