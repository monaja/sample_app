$(function(){

	$(document).ready(function() {
		createAccTypesTable();
		newAccountTypes();
		saveAccountTypes();
		checkTaxes();
		$('#vat-rate,#whtx-rate').number( true, 2 );
	});
});

function newAccountTypes(){
	$("#btn-add-acctypes").on("click", function(){
		$('#acct-types-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
		$('#acct-types-form').find("input[type=checkbox]").attr("checked", false);
		refreshTaxes();
		$('#acctTypesModal').modal('show');
	});
}


function saveAccountTypes(){
	var $accTypesFrm= $('#acct-types-form');
	var validator = $accTypesFrm.validate();
	 $('#acctTypesModal').on('hidden.bs.modal', function () {
		 validator.resetForm();
		 $('#acct-types-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
		 $('#acct-types-form').find("input[type=checkbox]").attr("checked", false);
	 });
	 
	 $('#saveAccountTypes').click(function(){
		 if (!$accTypesFrm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$accTypesFrm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "createAcctTypes";
	        var request = $.post(url, data );
	        request.success(function(){
	        	bootbox.alert("Record created/updated Successfully");
				$('#accounttbl').DataTable().ajax.reload();
				validator.resetForm();
				$('#acctTypesModal').modal('hide');
	        });
	        request.error(function(jqXHR, textStatus, errorThrown){
	        	bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
	        });
	 });
	
}

function editAcctTypes(button){
	var acctype = JSON.parse(decodeURI($(button).data("acctype")));
	$("#acc-id").val(acctype["accId"]);
	$("#sht-desc").val(acctype["accShtDesc"]);
	$("#description").val(acctype["accName"]);
	if(acctype["vatAppli"])
		$("#vat-appli").prop("checked", acctype["vatAppli"]);
	else
		$("#vat-appli").prop("checked", false);
	if(acctype["whtxAppl"])
		$("#whtx-appli").prop("checked", acctype["vatAppli"]);
	else
		$("#whtx-appli").prop("checked", false);
	$("#vat-rate").val(acctype["vatRate"]);
	$("#whtx-rate").val(acctype["whtaxVal"]);
	refreshTaxes();
	$('#acctTypesModal').modal('show');
}

function checkTaxes(){
	$('#vat-appli').click(function() {
		if($('#vat-appli').prop('checked')) {
			$("#vat-rate").attr("required", true);
		  	  $("#vat-rate").removeAttr('disabled');
		  	  
			   } 
			   else{
				   $("#vat-rate").val("");
				   $("#vat-rate").prop("disabled", true);
			       $("#vat-rate").removeAttr('required');
				  
			  }
	});
	$('#whtx-appli').click(function() {
		if($('#whtx-appli').prop('checked')) {
		  	  $("#whtx-rate").removeAttr('disabled');
		  	  $("#whtx-rate").attr("required", true);
			   } 
			   else{
				   $("#whtx-rate").val("");
				   $("#whtx-rate").prop("disabled", true);
			       $("#whtx-rate").removeAttr('required');
				  
			  }
	});
}
function refreshTaxes()
{
	if($('#vat-appli').prop('checked')) {
  	  $("#vat-rate").removeAttr('disabled');
  	  $("#vat-rate").prop("required", true);
	   } 
	   else{
		   $("#vat-rate").val("");
		   $("#vat-rate").prop("disabled", true);
	       $("#vat-rate").removeAttr('required');
		  
	  }
	if($('#whtx-appli').prop('checked')) {
	  	  $("#whtx-rate").removeAttr('disabled');
	  	  $("#whtx-rate").prop("required", true);
		   } 
		   else{
			   $("#whtx-rate").val("");
			   $("#whtx-rate").prop("disabled", true);
		       $("#whtx-rate").removeAttr('required');
			  
		  }

}

function confirmAcctypeDel(button){
	var acctype = JSON.parse(decodeURI($(button).data("acctype")));
	bootbox.confirm("Are you sure want to delete "+acctype["accShtDesc"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteAcctype/' + acctype["accId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#accounttbl').DataTable().ajax.reload();
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


function createAccTypesTable(){
	var url = "acctypes";
	  var table = $('#accounttbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 20, 30], [10, 20, 30] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "accShtDesc" },
				{ "data": "accName" },
				{ "data": "vatAppli",
				  "render":function(data,type,full,meta){
					  if(full.vatAppli) return "Yes";
					  else
						  return "No";
				  } 
				  
				},
				{ "data": "vatRate",
					  "render":function(data,type,full,meta){
						  return currencyFormat(full.vatRate);
					  }
				},
				{ "data": "whtxAppl", 
				  "render":function(data,type,full,meta){
					  if(full.whtxAppl) return "Yes";
					  else
						  return "No";
				  } 
				},
				{ "data": "whtaxVal" ,
				  "render":function(data,type,full,meta){
					  return currencyFormat(full.whtaxVal);
				  }
				},
				{ 
					"data": "accId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-acctype='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editAcctTypes(this);"/>';
					}

				},
				{ 
					"data": "accId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-acctype='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmAcctypeDel(this);"/>';
					}

				},
			]
		} );
	  return table;
}
