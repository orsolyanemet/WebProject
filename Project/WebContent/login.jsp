<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<!DOCTYPE html>
<html>
<head>
<title>Event Organizer</title>
<link rel="stylesheet" type="text/css" href="styles/loginstyle.css">
</head>
<body>
	<h1>Login</h1>
	<form action="login.do" method="POST">
		<input type="text" name="username"
			placeholder="Enter your username..."><br> <input
			type="password" name="password" placeholder="Enter your password..."><br>
		<label class="incorrect_data"><span id="msg3"
			class="important">${msgIncorrectData}</span><br></label> <input id="log"
			type="submit" name="submit" value="Log in"><br>
	</form>
</body>
</html>