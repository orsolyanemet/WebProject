<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.Message"%>
<%@page import="ro.edu.ubb.service.MessageService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	<h1>Inbox</h1>
	<div id="includedMenubar"></div>
	<br>
	<br>
	<input type="button" class="button" value="Delete"
		onclick="deleteMessage('')">
            <div class="panel panel-default inbox" id="inboxPanel">
                <div class="table-responsive">
                    <table class="table table-striped table-hover refresh-container pull-down" method="POST" action="inbox.do">
                        <thead class="hidden-xs">
                            <tr>
                            <td><input type="checkbox" title="Mark all"></td>
                            <td><strong>From</strong></td>
                            <td><strong>Subject</strong></td>
                            <td><strong>Message</strong></td>
                            <td><strong>Date</strong></td>
                        </tr></thead>
                          <tbody><tr>
                          <%
                          	MessageService messageService=new MessageService();
                          	request.getSession().setAttribute("userMessages", messageService.getAllMessages((String)request.getSession().getAttribute("loggedUsername")));
                       		List<Message> messages=(List<Message>)request.getSession().getAttribute("userMessages");
							if(messages!=null)
								for( Message message : messages){%>
							<td><input type="checkbox" title="Mark this item here"></td>
							<td>Administrator</td>
							<td><% out.print(message.getSubject()); %></td>
							<td><% out.print(message.getMess()); %></td>
							<td><% out.print(message.getDate()); %></td>
                          </tr>
                          <%} %>
                        </tbody>
                    </table>
                </div>
       
            </div>
</body>
</html>