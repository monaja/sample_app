$(function(){

	$(document).ready(function() {
		createPaymentModes();
		newPaymentModes();
		savePaymentModes();
		$('#min-val,#max-val').number( true, 2 );
	})
	
});


function savePaymentModes(){
	var $paymodesForm = $('#pay-modes-form');
	var validator = $paymodesForm.validate();
	 $('#paymentModesModal').on('hidden.bs.modal', function () {
		 validator.resetForm();
		 $('#pay-modes-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
	 });
	 
	 $('#savePaymentModes').click(function(){
		 if (!$paymodesForm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$paymodesForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "createPaymentModes";
	        var request = $.post(url, data );
	        request.success(function(){
	        	bootbox.alert("Record created/updated Successfully");
				$('#paymodetbl').DataTable().ajax.reload();
				validator.resetForm();
				$('#paymentModesModal').modal('hide');
	        });
	        request.error(function(jqXHR, textStatus, errorThrown){
	        	bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
	        });
	 });
}


function newPaymentModes(){
	$("#btn-add-modes").on("click", function(){
		$('#pay-modes-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
		$('#paymentModesModal').modal('show');
	});
}

function currencyFormat (num) {
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
}

function createPaymentModes(){
	var url = "allpaymentModes";
	  var table = $('#paymodetbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "pmShtDesc" },
				{ "data": "pmDesc" },
				{ "data": "pmMinValue",
				  "render":function(data,type,full,meta){
					  return currencyFormat(full.pmMinValue);
				  }
				},
				{ "data": "pmMaxValue",
				  "render":function(data,type,full,meta){
					  return currencyFormat(full.pmMaxValue);
				  }
				},
				{ 
					"data": "pmId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-modes='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editPaymentModes(this);"/>';
					}

				},
				{ 
					"data": "pmId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-modes='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmModesDel(this);"/>';
					}

				},
			]
		} );
	  return table;
}


function editPaymentModes(button){
	var modes = JSON.parse(decodeURI($(button).data("modes")));
	$("#sht-desc").val(modes["pmShtDesc"]);
	$("#pm-id").val(modes["pmId"]);
	$("#description").val(modes["pmDesc"]);
	$("#min-val").val(modes["pmMinValue"]);
	$("#max-val").val(modes["pmMaxValue"]);
	$('#paymentModesModal').modal('show');
}

function confirmModesDel(button){
	var modes = JSON.parse(decodeURI($(button).data("modes")));
	bootbox.confirm("Are you sure want to delete "+modes["pmShtDesc"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deletePayMode/' + modes["pmId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#paymodetbl').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                       bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}