<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Event Organizer</title>
<link rel="stylesheet" type="text/css" href="styles/bootstrap.css">
</head>
<body>
	<h1>Login</h1>
	<form action="login.do" method="POST">
		<input type="text" name="username"
			placeholder="Enter your username..." required><br> <input
			type="password" name="password" placeholder="Enter your password..." required><br>
		<label class="incorrect_data"><span id="msg3"
			class="important">${msgIncorrectData}</span><br></label> <br>
		<input id="log" type="submit" name="submit" value="Log in"><br>
	</form>
</body>
</html>