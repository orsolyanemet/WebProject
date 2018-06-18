<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<!DOCTYPE html>
<html>
<head>
<title>Event Organizer</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="styles/bootstrap.css">
<link rel="stylesheet" type="text/css" href="styles/menu.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="jquery.js"></script> 
<script src="js/user.js"></script>
</head>
<body>
	<h1>
		Welcome
		<%
		out.print(request.getSession().getAttribute("loggedUsername"));
	%>!
	</h1>
	<div id="includedMenubar"></div>
</body>
</html>