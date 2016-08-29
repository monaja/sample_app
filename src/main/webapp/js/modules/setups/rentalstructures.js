
function houseImage(id){
	$("#avatar").fileinput({
	    overwriteInitial: true,
	    maxFileSize: 1500,
	    showClose: false,
	    showCaption: false,
	    browseLabel: '',
	    removeLabel: '',
	    browseIcon: '<i class="fa fa-folder-open"></i>',
	    removeIcon: '<i class="fa fa-times"></i>',
	    removeTitle: 'Cancel or reset changes',
	    elErrorContainer: '#kv-avatar-errors',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="'+getContextPath()+'/protected/rental/setups/houseImage/'+id+'"  style="width:180px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
}


function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}


function showUnitModal(){
	$("#btn-add-unit").on('click', function(){
		if ($("#rental-struct-pk").val() != ''){
			 $('#units-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
			 populateUnitLov();
			 $("#unit-rental-id").val($("#rental-struct-pk").val());
			 $('#unitsModal').modal('show');
		}
		else{
			 bootbox.alert("Create a rental structure before adding a unit");
		}
	
	});
}


function createRentalUnit(){
	var $unitForm = $('#units-form');
	  var validator = $unitForm.validate();
	  $('#unitsModal').on('hidden.bs.modal', function () {
		    validator.resetForm();
			$('#units-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
			
      });
	 $('#saveRentalUnit').click(function(){
			if (!$unitForm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$unitForm.serializeArray().map(function(x){data[x.name] = x.value;});
			console.log(data);
			var url = "createRentalUnit";
         var request = $.post(url, data );
			request.success(function(){
				bootbox.alert("Record created/updated Successfully");
				$('#rentalUnits').DataTable().ajax.reload();				
				validator.resetForm();
				$('#units-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				$('#unitsModal').modal('hide');
			});

			request.error(function(jqXHR, textStatus, errorThrown){
				bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
         });
		});
}

function createStructure(){
	
	$('form#struct-form')
	  .submit( function( e ) {
		  e.preventDefault();
		  var data = new FormData( this );
		  data.append( 'file', $( '#avatar' )[0].files[0] );
		  $.ajax( {
		      url: 'createRentalStruct',
		      type: 'POST',
		      data: data,
		      processData: false,
		      contentType: false,
		      success: function (s ) {
		    	  bootbox.alert("Record created/updated Successfully");
		    	  $("#rental-struct-pk").val(s);		    
		      },
		      error: function(xhr, error){
		    	  bootbox.alert(xhr.responseText);
		      }
		      });
	  });
}

function populateRentalDetails(data){
	$("#rental-struct-pk").val(data.rentalId);
	$("#house-id").val(data.houseId);
	$("#house-name").val(data.houseName);
	$("#no-of-floors").val(data.noOfFloors);
	$("#no-of-units").val(data.noOfUnits);
	bCode = data.branch.obId;
	bName = data.branch.obName;
	$("#b-name").val(data.branch.obName);
	$("#obId").val(data.branch.obId);
	populateBranchDiv();
	houseImage(data.rentalId);
}

function getRentalDetails(){
	if(typeof rentalId!== 'undefined'){
		if(rentalId!==-2000){
			$.ajax( {
			      url: 'rentalStructure/'+rentalId,
			      type: 'GET',
			      processData: false,
			      contentType: false,
			      success: function (s ) {
			    	  populateRentalDetails(s);
			      },
			      error: function(xhr, error){
			    	  bootbox.alert(xhr.responseText);
			      }
			      });
		}
		else{
			houseImage(-2000);
		}
		
	}
}

function populateUnitLov(){
	if($("#unit-id").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "unit-id",
	            sort : 'unitName',
	            change: function(e,a,v){
	            	$("#unitId").val(e.added.unitId);
	            },
	            formatResult : function(a)
	            {
	            	return a.unitName
	            },
	            formatSelection : function(a)
	            {
	            	return a.unitName
	            },
	            initSelection: function (element, callback) {
	            	var unitCode = $("#unitId").val();
                    var unitName = $("#unit-type-name").val();
	            	model.rental.unit.unitId = unitCode;
	            	var data = {unitName:unitName,unitId:unitCode};
	            	
                    callback(data);
                },
	            id: "unitId",
	            width:"200px"
	        });
	  }

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

function populateBranchDiv(){
	if($("#branch").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "branch",
	            sort : 'obName',
	            change: function(e,a,v){
	            	 $("#obId").val(e.added.obId);
	            },
	            formatResult : function(a)
	            {
	            	return a.obName
	            },
	            formatSelection : function(a)
	            {
	            	return a.obName
	            },
	            initSelection: function (element, callback) {
	            	
	            	var branchCode = $("#obId").val();
                    var branchName = $("#b-name").val();
	            	model.rental.branch.brnCode = branchCode;
	            	var data = {obName:branchName,obId:branchCode};
	            	
                    callback(data);
              },
	            id: "obId",
	            width:"200px"
	        });
	  }
}

function addUnitCharges(){
	$("#btn-add-charges").click(function(){
		$('#charge-form').find("input[type=text],input[type=number],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea,select").val("");
		populateRateDiv();
		var $chargeForm = $('#charge-form');
		var validator = $chargeForm.validate();
		validator.resetForm();  
		$("#unit-id-pk").val($("#chargeid").val());
		$('#unitsChargesModal').modal({
			  backdrop: 'static',
			  keyboard: true
			})
	});
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

function checkUnitCharges(){
	$('#chk-taxable').click(function() {
		refreshTaxes();
	});
}


$(function(){

	$(document).ready(function() {
		//houseImage();
		var bCode;
		var bName;
		createStructureTable();
		createRentalUnits();
		createStructure();
		showUnitModal();
		getRentalDetails();
		populateBranchDiv();
		createRentalUnit();
		addUnitCharges();
		createRentalUnitCharges();
		checkUnitCharges();
		
		
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		
		rivets.bind($("#rental_model"), model);
		
		 if($("#rent-branch").filter("div").html() != undefined)
		  {
			  Select2Builder.initAjaxSelect2({
		            containerId : "rent-branch",
		            sort : 'obName',
		            change: branchChanged,
		            formatResult : function(a)
		            {
		            	return a.obName
		            },
		            formatSelection : function(a)
		            {
		            	return a.obName
		            },
		            initSelection: function (element, callback) {
	                 /*   */
	                },
		            id: "obId",
		            width:"200px"
		        });
		  }
		 
		 		
		function branchChanged(e, a, v) {
            model.rental.branch = e.added || {};
            $("#obId").val(e.added.obId);
            createStructureTable();
        }
		
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


function createStructureTable(){
	var url = "rentalstructures/0";
	  if ($("#obId").val() != ''){
		  url = "rentalstructures/"+$("#obId").val();
		}
	  var currTable = $('#rentstruct').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "houseId" },
				{ "data": "houseName" },
				{ "data": "noOfUnits" },
				{ "data": "noOfFloors" },
				{ 
					"data": "rentalId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editRentalForm" method="post"><input type="hidden" name="rentalId" value='+full.rentalId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
					}

				},
				{ 
					"data": "rentalId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-structs='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmStuctDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}

function showChargesModal(button){
	var unit = JSON.parse(decodeURI($(button).data("units")));
	var $chargeForm = $('#charge-form');
	createUnitCharges(unit["renId"]);
    $("#charge-unit-name").text(unit["unitName"]);
    $("#chargeid").val(unit["renId"]);
	$("#unitsRatesModalLabel").text("Charges for Unit: "+unit["unitName"]+" ("+unit["unitType"].unitName+")");
	
	$('#unitRatesModal').modal({
		  backdrop: 'static',
		  keyboard: true
		})
}

function createUnitCharges(renId){
	var url = "rentalCharges/0";
	if(typeof renId!== 'undefined'){
		url = "rentalCharges/"+renId;
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
				{ "data": "unit",
				  "render": function ( data, type, full, meta ) {
						   if(full.unit)
						  return full.unit.unitName;
						  else{
							  return "";
						  }
				   }	
				},
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
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-charges='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editUnitCharge(this);"/>';
					}

				},
				{ 
					"data": "chargeId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-charges='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmUnitChargeDel(this);"/>';
					 }

				},
				
			]
		} );
	  return currTable;
}


function editUnitCharge(button){
	var charge = JSON.parse(decodeURI($(button).data("charges")));
	var $chargeForm = $('#charge-form');
	var validator = $chargeForm.validate();
	validator.resetForm();  
	$("#charge-id").val(charge["chargeId"]);
	$("#rate-type-name").val(charge["rateType"].rateType);
	$("#rateId").val(charge["rateType"].rateId);
	populateRateDiv();
	$("#unit-id-pk").val($("#chargeid").val());
	$("#unit-amount").val(charge["amount"]);
	$("#sel1").val(charge["frequency"]);
	if(charge["taxable"])
		$("#chk-taxable").prop("checked", charge["taxable"]);
	else
		$("#chk-taxable").prop("checked", false);
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


function confirmUnitChargeDel(button){
	var charge = JSON.parse(decodeURI($(button).data("charges")));
	bootbox.confirm("Are you sure want to delete Unit Charge for "+charge["unit"].unitName+" "+charge["rateType"].rateType+"?", function(result) {
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


function createRentalUnits(){
	var url = "rentalUnits/0";
	if(typeof rentalId!== 'undefined'){
		url = "rentalUnits/"+rentalId;
	}
	  var currTable = $('#rentalUnits').DataTable( {
			"processing": true,
			"serverSide": true,
			 autoWidth: true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			"columns": [
				{ "data": "unitName" },
				{ "data": "unitType",
				  "render": function ( data, type, full, meta ) {
					  
						   if(full.unitType)
						  return full.unitType.unitName;
						  else{
							  return "";
						  }
				   }	
				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						var nam = full.unitName;
						return '<a href="javascript:void(0)" data-units='+encodeURI(JSON.stringify(full)) + ' onclick="showChargesModal(this);">View Rates</a>';
					}

				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-units='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editUnitType(this);"/>';
					}

				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-units='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmUnitTypeDel(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}

function editUnitType(button){
	var unit = JSON.parse(decodeURI($(button).data("units")));
	$("#ren-id").val(unit["renId"]);
	$("#unit-name").val(unit["unitName"]);
	$("#unit-rental-id").val($("#rental-struct-pk").val());
	$("#unit-type-name").val(unit["unitType"].unitName);
	$("#unitId").val(unit["unitType"].unitId);
	populateUnitLov();
	$('#unitsModal').modal('show');
}

function confirmUnitTypeDel(button){
	var unit = JSON.parse(decodeURI($(button).data("units")));
	bootbox.confirm("Are you sure want to delete "+unit["unitName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteRentalUnit/' + unit["renId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#rentalUnits').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                       bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
	
}


function confirmStuctDel(button){
	var struct = JSON.parse(decodeURI($(button).data("structs")));
	bootbox.confirm("Are you sure want to delete "+struct["houseName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteRentalStruct/' + struct["rentalId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#rentstruct').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                      bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

