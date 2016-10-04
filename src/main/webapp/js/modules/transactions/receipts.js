$(function(){

	$(document).ready(function() {
		createReceipts();
	});
});



function currencyFormat (num) {
	if(num)
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    else
    	return null;
}

function createReceipts(){
	var url = "receipts";
	  var currTable = $('#rcts-tbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [20,30,40,50], [20,30,40,50] ],
			pageLength: 20,
			destroy: true,
			"columns": [
				{ "data": "receiptNo" },
				{ "data": "receiptDate",
					 "render": function ( data, type, full, meta ) {
					      return moment(full.receiptDate).format('DD/MM/YYYY');
					  }
				
				},
				{ "data": "receiptUser",
					   "render": function ( data, type, full, meta ) {
						      return full.receiptUser.username;
						  }
				},
				{ "data": "paymentMode",
					   "render": function ( data, type, full, meta ) {
						      return full.paymentMode.pmDesc;
						  }
				},
				{ "data": "receiptAmount",
					 "render": function ( data, type, full, meta ) {
						 return currencyFormat(full.receiptAmount);
					  }
				},
				{ "data": "paidBy"},
				{ "data": "printed"},
				{ 
					"data": "receiptId",
					"render": function ( data, type, full, meta ) {
						if(full.status==="A"){
							return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.receiptId+'><input type="submit"  class="hyperlink-btn" value="View" ></form>';
							
						}else
						return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.receiptId+'><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
						
					 }

				},
			]
		} );
	  return currTable;
}