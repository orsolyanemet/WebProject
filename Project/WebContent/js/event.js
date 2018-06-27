/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function validateEventName() {
	var en = document.forms["eventForm"]["eventName"].value;
	if (en == "") {
		document.getElementById("errorEventName").innerHTML = "Event name must be filled out!";
	} else if (en[0] != en[0].toUpperCase()) {
		document.getElementById("errorEventName").innerHTML = "The event name must start with capital letter!";
	} else {
		document.getElementById("errorEventName").innerHTML = "";
	}
}
function validateLocation() {
	var lo = document.forms["eventForm"]["eventLocation"].value;
	if (lo == "") {
		document.getElementById("errorLocation").innerHTML = "Location must be filled out!";
	} else if (lo[0] != lo[0].toUpperCase()) {
		document.getElementById("errorLocation").innerHTML = "The location must start with capital letter!";
	} else {
		document.getElementById("errorLocation").innerHTML = "";
	}
}
function validateDate() {
	var date = document.forms["eventForm"]["eventDate"].value;
	var d = new Date(date);
	if (date == "") {
		document.getElementById("errorDate").innerHTML = "Event date must be selected!";
	}
	if (!dateInFuture(d)) {
		document.getElementById("errorDate").innerHTML = "Event date must be in the future!";
	} else {
		document.getElementById("errorDate").innerHTML = "";
	}
}
function dateInFuture(date) {
	var now = new Date();
	now.setHours(0, 0, 0, 0);
	if (date.getFullYear() < now.getFullYear())
		return false;
	else if (date.getMonth() + 1 < now.getMonth() + 1
			&& date.getFullYear() <= now.getFullYear())
		return false;
	else if (date.getDate() < now.getDate()
			&& date.getMonth() + 1 <= now.getMonth() + 1
			&& date.getFullYear() <= now.getFullYear())
		return false;
	return true;
}
function submitButtonClicked() {
	var error = false;
	if (document.getElementById("errorEventName").innerText != ""
			|| document.getElementById("errorLocation").innerText != ""
			|| document.getElementById("errorDate").innerText != "") {
		error = true;
	}
	if (!error) {
		var urlDepth = '';
		$
				.ajax({
					type : "POST",
					url : urlDepth + "event.do",
					data : {
						eventName : document.forms["eventForm"]["eventName"].value,
						eventType : document.getElementById("programType").value,
						eventLocation : document.forms["eventForm"]["eventLocation"].value,
						eventDate : document.getElementById("eventDate").value,
						eventDescription : document.forms["eventForm"]["eventDescription"].value
					},
					dataType : "json",
					success : function(data) {
						var respons = data.respons;
						if (respons.localeCompare("OK") == 0) {
							document.getElementById("msg").innerHTML = "Event has been successfully added.";
						} else {
							document.getElementById("msg").innerHTML = "Error! Event has not been added.";
						}
						document.forms["eventForm"]["eventName"].value = "";
						document.getElementById("programType").value = "";
						document.forms["eventForm"]["eventLocation"].value = "";
						document.getElementById("eventDate").value = "";
						document.forms["eventForm"]["eventDescription"].value = "";
					}
				});
	}
}
