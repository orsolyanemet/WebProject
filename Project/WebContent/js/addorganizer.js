/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function validateUserName(){
			var un = document.forms["organizerForm"]["userName"].value;
			if (un == "") {
				document.getElementById("errorUserName").innerHTML ="Username must be filled out!";
			}
			 else if(un.length > 10) {
				document.getElementById("errorUserName").innerHTML ="Username must contain maximum 10 characters!!";
			}
			else if ((/[^a-z]/.test(un))){
				document.getElementById("errorUserName").innerHTML ="Username must contain only lowercase characters!";
			}
			else{
				document.getElementById("errorUserName").innerHTML ="";
			}
}
function validateLastName(){
			var ln = document.forms["organizerForm"]["lastName"].value;
			if (ln == "") {
				document.getElementById("errorLastName").innerHTML ="Lastname must be filled out!";
			}
			else if (!/^[A-Z]{1}[a-z]{1,}$/.test(ln)){
				document.getElementById("errorLastName").innerHTML ="The lastname must start with and containt only one capital letter!";
			}
			else if (/[^a-zA-Z]/.test(ln)){
				document.getElementById("errorLastName").innerHTML ="The lastname must contains only letters!";
			}
			else{
				document.getElementById("errorLastName").innerHTML ="";
			}
}
function validateFirstName(){
			var fn = document.forms["organizerForm"]["firstName"].value;
			if (fn == "") {
				document.getElementById("errorFirstName").innerHTML = "Firstname must be filled out!";
			}
			else if (!/^[A-Z]{1}[a-z]{1,}$/.test(fn)){
				document.getElementById("errorFirstName").innerHTML ="The firstname must start with and containt only one capital letter!";
			}
			else if (/[^a-zA-Z]/.test(fn)){;
				document.getElementById("errorFirstName").innerHTML ="The firstname must contains only letters!";
			}
			else{
				document.getElementById("errorFirstName").innerHTML ="";
			}
}
function validatePassword(){
			var pwd = document.forms["organizerForm"]["password"].value;
			var strongRegex = new RegExp("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.{6,10})");
			  if(pwd.length < 6) {
				document.getElementById("errorPassword").innerHTML ="Password must contains at least 6 characters!";
			  }
			  else if(pwd.length > 10) {
				document.getElementById("errorPassword").innerHTML ="Password must contain maximum 10 characters!";
			  }
			  else if(!strongRegex.test(pwd)){
				document.getElementById("errorPassword").innerHTML ="Password must contain numbers and letters!";
			  }
			  else{
				document.getElementById("errorPassword").innerHTML ="";
			  }
} 

function validatePasswordConfirm() {
	var password = document.forms["organizerForm"]["password"].value;
	var passwordConfirm = document.forms["organizerForm"]["passwordConfirm"].value;
	if (password != passwordConfirm) {
		document.getElementById("errorPasswordConfirm").innerHTML = "Password do not match.";
	} else {
		document.getElementById("errorPasswordConfirm").innerHTML = "";
	}
}

function validateEmail(){
	var email = document.forms["organizerForm"]["email"].value;
	if((!/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
			.test(email))
			|| (email == "")){
		document.getElementById("errorEmail").innerHTML ="Email should be like email@example.com";
	}
	else{
		document.getElementById("errorEmail").innerHTML ="";
	}
}

function validatePhoneNumber(){
	var pn=document.forms["organizerForm"]["phoneNumber"].value;
	if (pn == "") {
		document.getElementById("errorPhoneNumber").innerHTML ="Phone number must be filled out!";
	}
	 else if (/[^0-9]/.test(pn)){
		document.getElementById("errorPhoneNumber").innerHTML ="The phone number must contains only numbers!";
	}
	 else{
		 document.getElementById("errorPhoneNumber").innerHTML ="";
	 }
}

function submitButtonClicked() {
	var error=false;
	if(document.getElementById("errorUserName").innerText!="" || document.getElementById("errorLastName").innerText!="" || document.getElementById("errorFirstName").innerText!="" || document.getElementById("errorPassword").innerText!=""|| document.getElementById("errorPasswordConfirm").innerText!="" || document.getElementById("errorPhoneNumber").innerText!="" || document.getElementById("errorEmail").innerText!=""){
		error=true;
	}
	if(!error){
		var urlDepth='';
		$.ajax({
			type : "POST",
			url : urlDepth +"neworganizer.do",
			data : {
				firstName : document.forms["organizerForm"]["firstName"].value,
				lastName : document.forms["organizerForm"]["lastName"].value,
				userName : document.forms["organizerForm"]["userName"].value,
				password : document.forms["organizerForm"]["password"].value,
				email : document.forms["organizerForm"]["email"].value,
				phoneNumber: document.forms["organizerForm"]["phoneNumber"].value
			},
			dataType : "json",
			success : function(data) {
			}
		});
	}
}