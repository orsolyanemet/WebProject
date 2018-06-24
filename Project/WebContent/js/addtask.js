/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function validateName() {
	var tn = document.forms["addForm"]["taskName"].value;
	if (tn == "") {
		document.getElementById("errorName").innerHTML = "Task name must be filled out!";
	} else if (tn[0] != tn[0].toUpperCase()) {
		document.getElementById("errorName").innerHTML = "The task name must start with capital letter!";
	} else {
		document.getElementById("errorName").innerHTML = "";
	}
}
function validateDate() {
	var date = document.forms["addForm"]["taskDate"].value;
	var d = new Date(date);
	if (date == "") {
		document.getElementById("errorDate").innerHTML = "Task deadline must be selected!";
	} if (!dateInFuture(d)){
		document.getElementById("errorDate").innerHTML = "Task deadline must be in the future!";
	}
	else {
		document.getElementById("errorDate").innerHTML = "";
	}
}
function dateInFuture(date){ 
	var now = new Date();
	now.setHours(0,0,0,0);
	if(date.getFullYear()<now.getFullYear())
		return false;
	else
		if(date.getMonth()+1<now.getMonth()+1 && date.getFullYear()<=now.getFullYear())
			return false;
		else
			if(date.getDate()<now.getDate() && date.getMonth()+1<=now.getMonth()+1 && date.getFullYear()<=now.getFullYear())
				return false;
	return true;
}
function addButtonClicked(){
	var error=false;
	if(document.getElementById("errorName").innerText!=""  || document.getElementById("errorDate").innerText!=""){
		error=true;
	}
	if(!error){
		var urlDepth='';
		$.ajax({
			type : "POST",
			url : urlDepth +"addtask.do",
			data : {
				taskName : document.forms["addForm"]["taskName"].value,
				partOf : document.getElementById("event").value,
				assignedTo : document.getElementById("user").value,
				deadline : document.getElementById("taskDate").value,
				details : document.forms["addForm"]["detail"].value
			},
			dataType : "json",
			success : function(data) {
			}
		});
	}
}
