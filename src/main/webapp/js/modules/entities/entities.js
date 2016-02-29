$(function(){

	$(document).ready(function() {
		
		 var dataTableUrl = "entities";
		  var productGroupTbl = $('#entities').DataTable( {
				"processing": true,
				"serverSide": true,
				"ajax": dataTableUrl,
				"columns": [
						{"data": "shtDesc"},
						{"data": "names"},
						{"data": "entityType"},
						{"data": "emailAddress"},
						{"data": "mobileNumber"},
						{"data": "pin"},
						{"data": "createdDate"},
						{"data": "createdBy"},   

				]
			} );
		
	});
	
});