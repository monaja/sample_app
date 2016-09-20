$(function(){

	$(document).ready(function() {
		createInvoices();
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		changeWefDate();
		
		
		
	});
});

function changeWefDate(){
	$('#wef-date').on('dp.change', function (ev) {
		var curDate = ev.date;
		if($("#sel2").val()!=''){
			if($("#sel2").val()==='M'){ 
			  $("#wet-date").val(moment(moment(curDate).add(1, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='Q'){ 
			  $("#wet-date").val(moment(moment(curDate).add(3, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='S'){ 
				  $("#wet-date").val(moment(moment(curDate).add(6, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='A'){ 
				  $("#wet-date").val(moment(moment(curDate).add(12, 'month')).format('DD/MM/YYYY'));
			}
		}
	});
	
	$("#sel2").change(function(){
		if($("#from-date").val()!=''){
			var curDate = $("#from-date").val();
			if($("#sel2").val()==='M'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(1, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='Q'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(3, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='S'){ 
					  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(6, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='A'){ 
					  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(12, 'month')).format('DD/MM/YYYY'));
				}
		}
	})
}


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
		
		

