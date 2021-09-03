function openForm(action, messageId) {
	document.getElementById("myForm").style.display = "block"	
	if(action == "delete"){
		document.getElementById("soap_op").value = "delete";
		document.getElementById("editmsg_text").style.display = "none";
	}
	if(action == "edit"){
		document.getElementById("soap_op").value = "edit";
		document.getElementById("editmsg_text").style.display = "block";
	}
		
	document.getElementById("msg_id").value = messageId;
}

function closeForm() {
  	document.getElementById("myForm").style.display = "none";
}