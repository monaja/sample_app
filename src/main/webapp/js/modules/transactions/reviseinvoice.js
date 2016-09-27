

$(function(){

	$(document).ready(function() {
		
		createInvoices();
		loadInvoiceModal();
		confirmSelectedInvoice();
		
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
	});
});

function revisionTrans(){
	var $revForm= $('#revise-form');
	var validator = $revForm.validate();
	
	 
	 $('#btn-revise-invoice').click(function(){
		 if (!$revForm.valid()) {
				return;
			}
			//var $btn = $(this).button('Saving');
			var data = {};
			$revForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "reviseInvoice";
	        var request = $.post(url, data );
	        request.success(function(){
//	        	bootbox.alert("Record created/updated Successfully");
//				$('#accounttbl').DataTable().ajax.reload();
//				validator.resetForm();
//				$('#acctTypesModal').modal('hide');
	        });
	        request.error(function(jqXHR, textStatus, errorThrown){
	        	bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				//$btn.button('reset');
	        });
	 });
	
}


function confirmSelectedInvoice(){
   $("#selectInvoice").on('click', function(){
	   if ($("#invoice-pk").val() != ''){
		   $('#invoiceModal').modal('hide');
	   }
	   else{
		   bootbox.alert('Select an invoice to continue');
	   }
   });
}

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
	  
	  $('#invtbl tbody').on( 'click', 'tr', function () {
			 
			 $(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
			
			 var d = currTable.row( this ).data();
			 if(d){
				 $("#invoice-pk").val(d.invoiceId);
				 $("#inv-number").val(d.invoiceNumber);
			 }
			   
		    
	  } );
	 
	  return currTable;
}

function currencyFormat (num) {
	if(num)
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    else
    	return null;
}
