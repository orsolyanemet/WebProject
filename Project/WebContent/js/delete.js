/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function deleteUser(button,urlDepth){
	$.ajax({
		type : "POST",
		url : urlDepth + "deleteuser.do",
		data: { idToDelete: document.getElementsByName(button.name)[0].name} ,
		dataType : "json",
		success : function(data) {
			var respons = data.respons;
			if (respons.localeCompare("OK") == 0) {
				window.location.replace(urlDepth + "deleteuser.jsp");

			} else {
				window.location.replace(urlDepth + "error.jsp");
			}
		}
	});
}