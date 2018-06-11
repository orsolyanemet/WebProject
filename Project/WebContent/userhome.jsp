<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<!DOCTYPE html>
<html>
<head>
<title>Event Organizer</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="styles/userhome.css">
</head>
<body>
	<h1>
		Welcome
		<%
		out.print(request.getSession().getAttribute("logedUsername"));
	%>!
	</h1>
	<ul>
		<li><a class="active" href="userhome.jsp">Home</a></li>
		<div class="dropdown">
			<li class="dropbtn"><a href="">Message</a>
				<div class="dropdown-content">
					<a href=""><i class="fa fa-envelope-o"></i> Inbox</a> <a href=""><i
						class="fa fa-trash-o"></i> Trash</a>
				</div></li>
		</div>
		<div class="dropdown">
			<li class="dropbtn"><a href="">Task</a>
				<div class="dropdown-content">
					<a href=""><i class="fa fa-list"></i> List</a> <a href=""><i
						class="fa fa-check-square-o"></i> Change status</a><a href=""><i
						class="fa fa-list-alt"></i> Others tasks</a>
				</div></li>
		</div>
		<li class="right" onclick="logout('')"><a href="">Logout</a></li>
	</ul>
</body>
</html>