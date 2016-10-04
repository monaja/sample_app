

$(function(){

	$(document).ready(function() {
		
		createInvoices();
		loadInvoiceModal();
		confirmSelectedInvoice();
		
		$("#existing-invoices").hide();
		
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		   $("#rev-type").on('change', function(){
			   
			   if($("#rev-type").val()==='CN' || $("#rev-type").val()==='CO'){
					 $("#eff-date").prop("disabled", true);
				 }
			   else{
				   $("#eff-date").removeAttr('disabled');
			   }
		   })
		    
		    
		});
		
	});
});



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
				{ "data": "invoiceDate",
					 "render": function ( data, type, full, meta ) {
					      return moment(full.invoiceDate).format('DD/MM/YYYY');
					  }
				},
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.tenant.fname + " "+full.tenant.otherNames;
						  }
				},
				{ "data": "wefDate",
					 "render": function ( data, type, full, meta ) {
					      return moment(full.wefDate).format('DD/MM/YYYY');
					  }
				},
				{ "data": "wetDate",
					 "render": function ( data, type, full, meta ) {
					      return moment(full.wetDate).format('DD/MM/YYYY');
					  }
				},
				
			]
		} );
	  
	  $('#invtbl tbody').on( 'click', 'tr', function () {
			 
			 $(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
			
			 var d = currTable.row( this ).data();
			 if(d){
				 $("#invoice-pk").val(d.invoiceId);
				 $("#inv-number").val(d.invoiceNumber);
				 
				 $.ajax({
				        type: 'GET',
				        url:  'countUnauthInvoices',
				        dataType: 'json',
				        data: {"invoiceNumber": $("#inv-number").val()},
				        async: true,
				        success: function(result) {
				        	if(result > 0){
				        		createUnauthInvoices();
				        		$("#existing-invoices").show();
				        	}
				        	else{
				        		 $("#existing-invoices").hide(); 
				        	}
				        },
				        error: function(jqXHR, textStatus, errorThrown) {
				        	
				        }
				    });

				 
			 }
			   
		    
	  } );
	 
	  return currTable;
}


function createUnauthInvoices(){
		var url = "unauthinvoices";
		  var currTable = $('#invtrans').DataTable( {
				"processing": true,
				"serverSide": true,
				"ajax": {
					'url': url,
					'data':{
						'invoiceNumber': $("#inv-number").val(),
					},
				},
				autoWidth: true,
				lengthMenu: [ [10], [10] ],
				pageLength: 10,
				destroy: true,
				searching: false,
				"columns": [
					{ "data": "invoiceNumber" },
					{ "data": "invoiceDate",
						 "render": function ( data, type, full, meta ) {
						      return moment(full.invoiceDate).format('DD/MM/YYYY');
						  }
					},
					{ "data": "wefDate",
						 "render": function ( data, type, full, meta ) {
						      return moment(full.wefDate).format('DD/MM/YYYY');
						  }
					},
					{ "data": "wetDate",
						 "render": function ( data, type, full, meta ) {
						      return moment(full.wetDate).format('DD/MM/YYYY');
						  }
					},
					{ "data": "modifiedBy" },
					{ "data": "currentStatus",
						 "render": function ( data, type, full, meta ) {
						      if(full.currentStatus ==='D') return "Draft";
						      else  if(full.currentStatus ==='NT') return "New Transaction";
						      else  if(full.currentStatus ==='RV') return "Revision Invoice"; 
						  }
					},
					{ 
						"data": "invoiceId",
						"render": function ( data, type, full, meta ) {
							if(full.status==="A"){
								return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.invoiceId+'><input type="submit"  class="hyperlink-btn" value="View" ></form>';
								
							}else
							return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.invoiceId+'><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
							
						 }

					},
					{ 
						"data": "invoiceId",
						"render": function ( data, type, full, meta ) {
							return '<input type="button" class="hyperlink-btn" data-invoice='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmInvoiceDelete(this);"/>';
						}

					},
					
					
				]
			} );
		  return currTable;
}

function confirmInvoiceDelete(button){
	var invoice = JSON.parse(decodeURI($(button).data("invoice")));
	bootbox.confirm("Are you sure want to delete "+invoice["invoiceNumber"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteInvoice',
			        data: {"invoiceCode": invoice["invoiceId"]},
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#invtrans').DataTable().ajax.reload();
			        	 $("#existing-invoices").hide(); 
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

function currencyFormat (num) {
	if(num)
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    else
    	return null;
}
