/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function deleteUser(button,urlDepth){
	deleteElementId=button.name;
	$.ajax({
		type : "POST",
		url : urlDepth + "deleteuser.do",
		data: { idToDelete: document.getElementsByName(button.name)[0].name} ,
		dataType : "json",
		success : function(data) {
			window.location.replace(urlDepth + "deleteuser.jsp");
		}
	});
}