$(document).ready(function(){
	
	var url =  $(location).attr('href'); 
	var error =url.indexOf("error") >= 0;	
	if(error){
		$('#login-error').show();
	}
	else{
		$('#login-error').hide();
	}
	
})
