<!--Nemet Orsolya, noim1553, 532/1 csoport, Project -->
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="error.jsp" %>
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
<script src="js/addorganizer.js"></script>
</head>
<body>
	<h1>Add organizer</h1>
	<div id="includedMenubar"></div>
	<br>
	<form name="organizerForm" method="POST">
		<div class="form-group">
            <label>First name:</label><br>
 			<input name="firstName"  maxlength="30" type="text" onkeyup="validateFirstName()" required="required"  placeholder="Enter first name...">
            <br><span id="errorFirstName" class="error"></span><br>
        </div>
        <div class="form-group">
            <label>Last name:</label><br>
 			<input name="lastName"  maxlength="30" type="text" onkeyup="validateLastName()" required="required"  placeholder="Enter last name...">
            <br><span id="errorLastName" class="error"></span><br>
        </div>
		<div class="form-group">
            <label>User name:</label><br>
 			<input name="userName"  maxlength="30" type="text" onkeyup="validateUserName()" required="required"  placeholder="Enter user name...">
            <br><span id="errorUserName" class="error"></span><br>
        </div>
		<div class="form-group">
            <label>Password:</label><br>
 			<input name="password" type="password" onkeyup="validatePassword()" required="required" placeholder="Enter password..." >
            <br><span id="errorPassword" class="error"></span><br>
        </div>
        <div class="form-group">
            <label>Confirm password:</label><br>
 			<input name="passwordConfirm" type="password" onkeyup="validatePasswordConfirm()" required="required"  placeholder="Confirm password..." >
            <br><span id="errorPasswordConfirm" class="error"></span><br>
        </div>
        <div class="form-group">
  			<label>Email:</label><br>
			<input type="text" onkeyup="validateEmail()" name="email" required="required"  placeholder="Enter email...">
			<br><span id="errorEmail" class="error"></span><br>
		</div>
        <div class="form-group">
            <label>Phone number:</label><br>
 			<input name="phoneNumber" type="text" onkeyup="validatePhoneNumber()" required="required" placeholder="Enter phone number.." >
            <br><span id="errorPhoneNumber" class="error"></span><br>
        </div>
		<input id="submit" type="submit" name="submit" value="Submit" onclick="submitButtonClicked()" >
	</form>
</body>
</html>