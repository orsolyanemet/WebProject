var deleteElementId=0;
function deleteMessage(button,urlDepth){
	deleteElementId=button.name;
	$.ajax({
		type : "POST",
		url : urlDepth + "message.do",
		data: { idToDelete: document.getElementsByName(button.name)[0].name} ,
		dataType : "json",
		success : function(data) {
			var respons = data.respons;
			if (respons.localeCompare("OK") == 0) {
				window.location.replace(urlDepth + "message.jsp");

			} else {
				window.location.replace(urlDepth + "error.jsp");
			}
		}
	});
}
function refreshTable() {
	window.location.replace("message.jsp");
}