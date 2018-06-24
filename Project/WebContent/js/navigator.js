/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
var deleteElementId=0;
function logout(urlDepth) {
	$.ajax({
		type : "POST",
		url : urlDepth + "logout",
		success : function(data) {
			window.location.replace(urlDepth + "login.jsp");
		}
	});
}
function deleteMessage(button,urlDepth){
	deleteElementId=button.name;
	$.ajax({
		type : "POST",
		url : urlDepth + "message.do",
		data: { idToDelete: document.getElementsByName(button.name)[0].name} ,
		dataType : "json",
		success : function(data) {
			window.location.replace(urlDepth + "message.jsp");
		}
	});
}

