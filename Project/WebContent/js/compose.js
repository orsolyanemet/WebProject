/**
 * @author Nemet Orsolya, noim1553, 532/1 csoport
 */
function sendButtonClicked() {
	var urlDepth = '';
	$
			.ajax({
				type : "POST",
				url : urlDepth + "composemessage.do",
				data : {
					sendTo : document.getElementById("user").value,
					subject : document.forms["composeFrom"]["subject"].value,
					message : document.forms["composeFrom"]["message"].value
				},
				dataType : "json",
				success : function(data) {
				}
			});
}
