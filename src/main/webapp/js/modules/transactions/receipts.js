$(function(){

	$(document).ready(function() {
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		createReceipts();
		currencyLov();
		paymentModesLov();
		addInvoiceTrans();
		
		$("#rct-amount").number( true, 2 );
	});
});

function addInvoiceTrans(){
	$("#add-det-btn").on('click', function(){
		createTransactionTbl();
		$('#tenantTransModal').modal({
			  backdrop: 'static',
			  keyboard: true
			})
	});
}

var model = {
		receipt: {
			tenant:{
				tenId:"",
			},
			currency:{
				curCode:"",
			},
			paymentmodes:{
				pmId:""
			}
	    }
	};

function currencyLov(){
	if($("#curr-div").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "curr-div",
	            sort : 'curName',
	            change:  function(e, a, v){
	            	$("#cur-id").val(e.added.curCode);
	            },
	            formatResult : function(a)
	            {
	            	return a.curName
	            },
	            formatSelection : function(a)
	            {
	            	return a.curName
	            },
	            initSelection: function (element, callback) {
//	            	  var currCode = $("#cur-id").val();
//	                  var currName = $("#cur-name").val();
//		              model.invoice.currency.curCode = currCode;
//		              var data = {curName:currName,curCode:currCode};
//	                  callback(data);
                },
	            id: "curCode",
	            width:"220px"
	        });
	  }
}

function paymentModesLov(){
	if($("#div-paymodes").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "div-paymodes",
	            sort : 'pmDesc',
	            change:  function(e, a, v){
	            	$("#pymt-id").val(e.added.pmId);
	            },
	            formatResult : function(a)
	            {
	            	return a.pmDesc
	            },
	            formatSelection : function(a)
	            {
	            	return a.pmDesc
	            },
	            initSelection: function (element, callback) {
//	            	var code = $("#pymt-id").val();
//	                  var pname = $("#pymt-desc").val();
//		              model.invoice.paymentmodes.pmId = code;
//		              var data = {pmDesc:pname,pmId:code};
//	                  callback(data);
                },
	            id: "pmId",
	            width:"220px"
	        });
	  }
}

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



function createTransactionTbl(){
	var url = "tenanttrans";
	  var currTable = $('#modal-rct-detail-tbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10], [10] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "transId",
					 "render": function ( data, type, full, meta ) {
						 return '<input type="checkbox">';
					  }
				
				},
				{ "data": "transId" },
				{ "data": "refno"},
//				{ "data": "receiptDate",
//					 "render": function ( data, type, full, meta ) {
//					      return moment(full.receiptDate).format('DD/MM/YYYY');
//					  }
//				
//				},
				{ "data": "tenant",
					   "render": function ( data, type, full, meta ) {
						   return full.tenant.fname + " "+full.tenant.otherNames;
						  }
				},
				{ "data": "transAmount",
					 "render": function ( data, type, full, meta ) {
						 return currencyFormat(full.transAmount);
					  }
				},
				{ "data": "transBalance",
					 "render": function ( data, type, full, meta ) {
						 return currencyFormat(full.transBalance);
					  }
				},
				
			]
		} );
	  return currTable;
}