

$(function(){

	$(document).ready(function() {
		
		createInvoices();
	});
});

function createInvoices(){
	var url = "revisionInvoices";
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
				{ "data": "invoiceDate"},
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
				{ "data": "wefDate"},
				{ "data": "wetDate"},
				
				{ "data": "netAmount" , 
					 "render":function(data,type,full,meta){
						  return currencyFormat(full.netAmount);
					  }
				},
				{ "data": "status" , 
					 "render":function(data,type,full,meta){
						  return (full.status) == "A" ? "Authorized" : "Draft";
					  }
				},
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
