$(function(){

	$(document).ready(function() {
		
		createChargeGroupTable();
		showGroupModal();
		createUnitCharges(-2000);
		createRentalUnitCharges();
		addUnitCharges();
		checkUnitCharges();
		populateRateDiv();
		
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
	});
});

var model = {
		rental: {
			branch:{
				brnCode:"",
			},
			unit:{
				unitId:"",
			},
			ratetype:{
				rateId:"",
			}
	    }
	};

function editGroup(button){
	var group = JSON.parse(decodeURI($(button).data("group")));
	$("#charge-id").val(group["chargeId"]);
	$("#group-sht-desc").val(group["shortDesc"]);
	$("#group-name").val(group["groupName"]);
	$('#groupModal').modal('show');		
}

function confirmGroupDel(button){
	var group = JSON.parse(decodeURI($(button).data("group")));
	bootbox.confirm("Are you sure want to delete "+group["shortDesc"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteChargeGroup/' + group["chargeId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#grouptbl').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                       bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}


function showGroupModal(){
	
 $("#newgroup").on('click', function(){
	 $('#group-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
	$('#groupModal').modal('show');		
 });
 
	 var $currForm = $('#group-form');
		var currValidator = $currForm.validate();
		 $('#countryModal').on('hidden.bs.modal', function () {
			 currValidator.resetForm();
			 $('#group-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
	     });
		
		$('#save-group-btn').click(function(){
			if (!$currForm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "createChargeGroup";
	        var request = $.post(url, data );
	        request.success(function(){
	        	bootbox.alert("Record created/updated Successfully");
				$('#grouptbl').DataTable().ajax.reload();
				currValidator.resetForm();
				$('#group-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				$('#groupModal').modal('hide');
	        });
	        request.error(function(jqXHR, textStatus, errorThrown){
	        	bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
	        });
 });
 
	
}



function createChargeGroupTable(){
	var url = "chargegroupings";
	  var currTable = $('#grouptbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "shortDesc" },
				{ "data": "groupName" },
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-group='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editGroup(this);"/>';
					}

				},
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-group='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmGroupDel(this);"/>';
					}

				},
			]
		} );
	  
	  $('#grouptbl tbody').on( 'click', 'tr', function () {
			 
			 $(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
			
			 var d = currTable.row( this ).data();
			 if(d){
				 createUnitCharges(d.chargeId);
				 $("#groupId").val(d.chargeId);
				// $("#inv-number").val(d.invoiceNumber);
			 }
			   
		    
	  } );
	  return currTable;
}

function addUnitCharges(){
	$("#btn-add-charges").click(function(){
		$('#charge-form').find("input[type=text],input[type=number],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea,select").val("");
		$("#chk-taxable").prop("checked", false);
		$("#chk-refundable").prop("checked", false);
		 $("#sel2").val("");
		   $("#sel3").val("");
		   $("#tax-amount").val("");
		   $("#sel2").prop("disabled", true);
		   $("#sel3").prop("disabled", true);
		   $("#tax-amount").prop("disabled", true);
		   $("#sel2").removeAttr('required');
	       $("#sel3").removeAttr('required');
	       $("#tax-amount").removeAttr('required');
		populateRateDiv();
		var $chargeForm = $('#charge-form');
		var validator = $chargeForm.validate();
		validator.resetForm();  
		$("#group-id-pk").val($("#groupId").val());
		$('#unitsChargesModal').modal({
			  backdrop: 'static',
			  keyboard: true
			})
	});
}


function createUnitCharges(groupCode){
	console.log("Group Code "+groupCode);
	var url = "rentalCharges/0";
	if(typeof groupCode!== 'undefined'){
		url = "rentalCharges/"+groupCode;
	}
	  var currTable = $('#unitCharges').DataTable( {
			"processing": true,
			"serverSide": true,
			 autoWidth: true,
			"ajax": url,
			"destroy":true,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			"columns": [
				{ "data": "rateType",
					  "render": function ( data, type, full, meta ) {
							   if(full.rateType)
							  return full.rateType.rateType;
							  else{
								  return "";
							  }
					   }	
				},
				{ "data": "amount" },
				{ "data": "frequency" },
				{ "data": "taxable" },
				{ "data": "taxType" },
				{ "data": "taxValue" },
				{ "data": "wefDate" },
				{ "data": "wetDate" },
				{ "data": "refundable" },
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editUnitCharge(this);"/>';
					}

				},
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmUnitChargeDel(this);"/>';
					 }

				},
				
			]
		} );
	  return currTable;
}


function createRentalUnitCharges(){
	var $chargeForm = $('#charge-form');
	  var validator = $chargeForm.validate();
	 $('#saveRentalFees').click(function(){
			if (!$chargeForm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$chargeForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "createRentalCharge";
         var request = $.post(url, data );
			request.success(function(){
				bootbox.alert("Record created/updated Successfully");
				$('#unitCharges').DataTable().ajax.reload();				
				validator.resetForm();
				$('#charge-form').find("input[type=text],input[type=number],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea,select").val("");
				$('#unitsChargesModal').modal('hide');
			});

			request.error(function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
				bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
         });
		});
}

function refreshTaxes()
{
	if($('#chk-taxable').prop('checked')) {
  	  $("#sel2").removeAttr('disabled');
  	  $("#sel3").removeAttr('disabled');
  	  $("#tax-amount").removeAttr('disabled');
  	  $("#sel2").prop("required", true);
  	  $("#sel3").prop("required", true);
  	  $("#tax-amount").prop("required", true);
	   } 
	   else{
		   $("#sel2").val("");
		   $("#sel3").val("");
		   $("#tax-amount").val("");
		   $("#sel2").prop("disabled", true);
		   $("#sel3").prop("disabled", true);
		   $("#tax-amount").prop("disabled", true);
		   $("#sel2").removeAttr('required');
	       $("#sel3").removeAttr('required');
	       $("#tax-amount").removeAttr('required');
		  
	  }

}


function editUnitCharge(button){
	var charge = JSON.parse(decodeURI($(button).data("rates")));
	var $chargeForm = $('#charge-form');
	var validator = $chargeForm.validate();
	validator.resetForm();  
	$("#group-id-pk").val($("#groupId").val());
	$("#charge-pk-id").val(charge["chargeId"]);
	$("#rate-type-name").val(charge["rateType"].rateType);
	$("#rateId").val(charge["rateType"].rateId);
	populateRateDiv();
	$("#unit-amount").val(charge["amount"]);
	$("#sel1").val(charge["frequency"]);
	if(charge["taxable"])
		$("#chk-taxable").prop("checked", charge["taxable"]);
	else
		$("#chk-taxable").prop("checked", false);
	if(charge["refundable"])
		$("#chk-refundable").prop("checked", charge["taxable"]);
	else
		$("#chk-refundable").prop("checked", false);
	refreshTaxes();
	$("#sel2").val(charge["taxType"]);
	$("#sel3").val(charge["taxRateType"]);
	$("#tax-amount").val(charge["taxValue"]);
	$("#wef-date").val(moment(charge["wefDate"]).format('DD/MM/YYYY'));
	if(charge["wetDate"])
	$("#wet-date").val(moment(charge["wetDate"]).format('DD/MM/YYYY'));
	
	$('#unitsChargesModal').modal({
		  backdrop: 'static',
		  keyboard: true
		})
}


function checkUnitCharges(){
	$('#chk-taxable').click(function() {
		refreshTaxes();
	});
}

function populateRateDiv(){
	if($("#ratetype").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "ratetype",
	            sort : 'rateType',
	            change: function(e,a,v){
	            	 $("#rateId").val(e.added.rateId);
	            },
	            formatResult : function(a)
	            {
	            	return a.rateType
	            },
	            formatSelection : function(a)
	            {
	            	return a.rateType
	            },
	            initSelection: function (element, callback) {
	            	var rateId = $("#rateId").val();
                    var rateName = $("#rate-type-name").val();
	            	model.rental.ratetype.rateId = rateId;
	            	var data = {rateType:rateName,rateId:rateId};
                    callback(data);
              },
	            id: "rateId",
	            width:"200px"
	        });
	  }
}

function confirmUnitChargeDel(button){
	var charge = JSON.parse(decodeURI($(button).data("rates")));
	bootbox.confirm("Are you sure want to delete  Charge  "+charge["rateType"].rateType+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteRentalCharge/' + charge["chargeId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#unitCharges').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                      bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}


function showChargesModal(button){
	var unit = JSON.parse(decodeURI($(button).data("units")));
	var $chargeForm = $('#charge-form');
    $("#chargeid").val(unit["renId"]);
	
	$('#unitRatesModal').modal({
		  backdrop: 'static',
		  keyboard: true
		})
}



