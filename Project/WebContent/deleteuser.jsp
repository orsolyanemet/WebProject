<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.User"%>
<%@page import="ro.edu.ubb.service.UserService"%>
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
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/admin.js"></script>
<script src="js/delete.js"></script>
</head>
<body>
	<h1>Delete organizer</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
		<div class="panel panel-default inbox" id="editPanel">
			<div class="table-responsive">
				<table id="myTable"
					class="table table-striped table-hover refresh-container pull-down">
					<%
								UserService userService = new UserService();
								request.getSession().setAttribute("users",
										userService.getAllUsers());
								List<User> users = (List<User>) request.getSession().getAttribute("users");
								if (users != null && !users.isEmpty()){
					%>
					<thead class="hidden-xs">
						<tr>
							<td></td>
							<td><strong>User name</strong></td>
							<td><strong>First name</strong></td>
							<td><strong>Last name</strong></td>
							<td><strong>Email</strong></td>
							<td><strong>Phone number</strong></td>
						</tr>
					</thead>
							<%
									for (User user : users) {
							%>
					<tbody>
						<tr>
							<td><button id="button" onClick="deleteUser(this,'')" name=<%=user.getIdUser()%>><i class="fa fa-trash"></i></button></td>
							<td>
								<%
									out.print(user.getUsername());
								%>
							</td>
							<td>
								<%
									out.print(user.getFirstname());
								%>
							</td>
							<td>
								<%
									out.print(user.getLastname());
								%>
							</td>
							<td>
								<%
									out.print(user.getEmail());
								%>
							</td>
							<td>
								<%
									out.print(user.getPhoneNumber());
								%>
							</td>
						</tr>
						<%
						}
							}else{
					%>
					<label class="error">There are no users.</label>
			<%
				}
			%>
					</tbody>
				</table>
			</div>
		</div>
</body>
</html>