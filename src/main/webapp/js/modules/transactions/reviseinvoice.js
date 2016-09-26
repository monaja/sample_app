

$(function(){

	$(document).ready(function() {
		
		createInvoices();
		loadInvoiceModal();
	});
});


function loadInvoiceModal(){
	$("#btn-show-invoice").on('click', function(){
		createInvoices();
		$('#invoiceModal').modal({
			  backdrop: 'static',
			  keyboard: true
			})
	});
	
	$("#btn-search-invoice").on('click', function(){
		createInvoices();
		
	});
	
	
}

function createInvoices(){
	var url = "revisionInvoices";
	  var currTable = $('#invtbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": {
				'url': url,
				'data':{
					'firstName': $("#inv-search-name").val(),
					'otherNames':  $("#inv-search-other-names").val(),
					'invoiceNumber': $("#inv-search-number").val(),
				},
			},
			autoWidth: true,
			lengthMenu: [ [10], [10] ],
			pageLength: 10,
			destroy: true,
			searching: false,
			"columns": [
				{ "data": "invoiceNumber" },
				{ "data": "invoiceDate"},
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.tenant.fname + " "+full.tenant.otherNames;
						  }
				},
				{ "data": "wefDate"},
				{ "data": "wetDate"},
				
			]
		} );
	  return currTable;
}

function currencyFormat (num) {
	if(num)
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    else
    	return null;
}
