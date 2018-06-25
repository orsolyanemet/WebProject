/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function validate(){
	if((document.forms["statusForm"]["report"].value!="" || document.forms["statusForm"]["report"].value!=null) && document.getElementById("solved").checked!=true){
		return false;
	}
	return true;
}
function submitButtonClicked() {
	var urlDepth = '';
	if (validate()) {
		$.ajax({
			type : "POST",
			url : urlDepth + "changestatus.do",
			data : {
				partOf : document.getElementById("task").value,
				solved : document.getElementById("solved").checked,
				report : document.forms["statusForm"]["report"].value
			},
			dataType : "json",
			success : function(data) {
			}
		});
	}
}