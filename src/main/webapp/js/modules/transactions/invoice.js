$(function(){

	$(document).ready(function() {
		createInvoices();
	});
});


function createInvoices(){
	var url = "invoices";
	  var currTable = $('#invtbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			autoWidth: true,
			lengthMenu: [ [20,30,40,50], [20,30,40,50] ],
			pageLength: 20,
			destroy: true,
			"columns": [
				{ "data": "invoiceNumber" },
				{ "data": "invoiceDate" },
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.tenant.fname + " "+full.tenant.otherNames;
						  }
				},
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.transCurrency.curName;
						  }
				},
				{ "data": "wefDate" },
				{ "data": "wetDate" },
				{ "data": "invAmount" },
				{ "data": "taxAmount" },
				{ "data": "netAmount" },
				{ 
					"data": "invoiceId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-account='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmAccountDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}
		
		

