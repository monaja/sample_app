$(function(){

	$(document).ready(function() {
		
		createRateTypeTable();
		saveUpdateRateType();
		
		$("#new-btn").on("click", function(){
			$('#rate-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");		
		});	
		
		createUnitTypeTable();
		saveUpdateUnitType();
		
	});
	
});



function createRateTypeTable(){
	var url = "allratetypes";
	  var currTable = $('#ratetypeList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "rateType" },
				{ "data": "rateDesc" },
				{ 
					"data": "rateId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editRateType(this);"/>';
					}

				},
				{ 
					"data": "rateId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmRateTypeDel(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}

function editUnitType(button){
	var unit = JSON.parse(decodeURI($(button).data("units")));
	$("#unit-code").val(unit["unitId"]);
	$("#unit-name").val(unit["unitName"]);
	$("#unit-desc").val(unit["unitDescription"]);
}

function confirmUnitTypeDel(button){
	var country = JSON.parse(decodeURI($(button).data("units")));
	bootbox.confirm("Are you sure want to delete "+country["unitName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteUnitType/' + country["unitId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#unittypeList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

function createUnitTypeTable(){
	var url = "allunittypes";
	  var currTable = $('#unittypeList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "unitName" },
				{ "data": "unitDescription" },
				{ 
					"data": "unitId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-units='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editUnitType(this);"/>';
					}

				},
				{ 
					"data": "unitId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-units='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmUnitTypeDel(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}





function saveUpdateRateType(){
	var $currForm = $('#rate-form');
	var currValidator = $currForm.validate();
	$('#save-rate-type').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createRateType";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#ratetypeList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#rate-form').find("input[type=text],input[type=mobileNumber],input[type=number],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");

		});
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
	});
}


function saveUpdateUnitType(){
	var $currForm = $('#rate-form');
	var currValidator = $currForm.validate();
	$('#save-unit-type').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createUnitType";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#unittypeList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#rate-form').find("input[type=text],input[type=mobileNumber],input[type=number],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");

		});
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
	});
}

function editRateType(button){
	var rate = JSON.parse(decodeURI($(button).data("rates")));
	$("#rate-code").val(rate["rateId"]);
	$("#rate-type").val(rate["rateType"]);
	$("#rate-desc").val(rate["rateDesc"]);
}

function confirmRateTypeDel(button){
	var country = JSON.parse(decodeURI($(button).data("rates")));
	bootbox.confirm("Are you sure want to delete "+country["rateType"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteRateType/' + country["rateId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#ratetypeList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
	
}
	