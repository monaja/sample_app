$(function(){

	$(document).ready(function() {
		
		createTenantsList();
		
	});
});

function createTenantsList(){
	var url = "tenants";
	  var currTable = $('#tenTbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
                { "data": "tenantNumber" },
				{ "data": "fname",
				  "render": function ( data, type, full, meta ) {
					  return full.fname+" "+full.otherNames;
				  }
				},
				{ "data": "IdPassport" },
				{ "data": "emailAddress" },
				{ "data": "phoneNo" },
				{ "data": "dob" },
				{ "data": "tenantType" },
				{ "data": "dateregistered" },
				{ "data": "status",
				   "render": function ( data, type, full, meta ) {
					   if(!full.status || full.status==="I"){
						   return "Inactive";
					   }
					   else if(full.status==="A")
						  return "Active";
					  }
				},
				{ 
					"data": "tenId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editAcctForm" method="post"><input type="hidden" name="id" value='+full.acctId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
					}

				},
				{ 
					"data": "tenId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-account='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmAccountDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}