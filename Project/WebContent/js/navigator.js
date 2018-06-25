/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 *
 */
function logout(urlDepth) {
	$.ajax({
		type : "POST",
		url : urlDepth + "logout",
		success : function(data) {
			window.location.replace(urlDepth + "login.jsp");
		}
	});
}