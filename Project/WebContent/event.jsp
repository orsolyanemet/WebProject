<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page import="ro.edu.ubb.entity.ProgramTypes"%>
<%@page import="ro.edu.ubb.service.ProgramTypeService"%>
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
<link rel="stylesheet" type="text/css" href="styles/form.css">
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/navigator.js"></script>
<script src="js/admin.js"></script>
<script src="js/event.js"></script>
</head>
<body>
	<h1>Add event</h1>
	<div id="includedMenubar"></div>
	<br>
	<samp id="msg"></samp>
	<br>
	<br>
	<form name="eventForm" method="POST">
		<div class="form-group">
            <label>Event name:</label><br>
 			<input name="eventName"  maxlength="30" type="text" onkeyup="validateEventName()" required="required"  placeholder="Enter event name...">
            <br><span id="errorEventName" class="error"></span><br>
        </div>
        <div class="form-group">
        	<label>Event type:</label><br>
			<input id="programType" list="programTypes" name="programType"  required="required">
	  			<datalist id="programTypes">
	    		<%
	  				ProgramTypeService programTypeService = new ProgramTypeService();
				request.getSession().setAttribute("programTypes",programTypeService.getAllProgramTypes());
				List<ProgramTypes> programTypes = (List<ProgramTypes>) request.getSession().getAttribute("programTypes");
				if (programTypes != null)
					for (ProgramTypes programType : programTypes) { %>
	    		<option value=<%out.print(programType.getProgramTypeName());%>>
	    		<%
						}
				%>
	  			</datalist>
  		</div>
  		<div class="form-group">
  			<label>Event location:</label><br>
			<input type="text" onkeyup="validateLocation()" name="eventLocation" required="required"  placeholder="Enter event location...">
			<br><span id="errorLocation" class="error"></span><br>
		</div>
		<div class="form-group">
			<label>Event date:</label><br>
			<input type="date" id="eventDate" onchange="validateDate()" name="eventDate" required="required">
			<br><span id="errorDate" class="error"></span><br>
		</div>
		<div class="form-group">
			<label>Event description:</label><br>
			<textarea id="eventDescription" rows="4" cols="50" placeholder="Enter description of event..."></textarea><br>
		</div>
		<input id="submit" type="submit" name="submit" value="Submit" onclick="submitButtonClicked()" >
	</form>
</body>
</html>