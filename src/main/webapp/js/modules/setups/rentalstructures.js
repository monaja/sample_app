
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
			 populateGroup();
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
			var url = "createRentalUnit";
         var request = $.post(url, data );
			request.success(function(){
				bootbox.alert("Record created/updated Successfully");
				$('#rentalUnits').DataTable().ajax.reload();				
				validator.resetForm();
				populateGroup();
				populateUnitLov();
				$("#unit-id").select2("val", "");
				$("#charge-id").select2("val", "");
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

function populateGroup(){
	if($("#charge-id").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "charge-id",
	            sort : 'shortDesc',
	            change: function(e,a,v){
	            	$("#chargeId").val(e.added.chargeId);
	            },
	            formatResult : function(a)
	            {
	            	return a.groupName
	            },
	            formatSelection : function(a)
	            {
	            	return a.groupName
	            },
	            initSelection: function (element, callback) {
	            	var unitCode = $("#chargeId").val();
                    var unitName = $("#group-name").val();
	            	model.rental.groups.chargeId = unitCode;
	            	var data = {groupName:unitName,chargeId:unitCode};
	            	
                    callback(data);
                },
	            id: "chargeId",
	            width:"260px"
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
		populateGroup();
		
		
		
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
			groups:{
				chargeId:"",
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
						return '<form action="editRentalForm" method="post"><input type="hidden" name="rentalId" value='+full.rentalId+'><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
					}

				},
				{ 
					"data": "rentalId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-structs='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmStuctDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
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
				{ "data": "chargeGroup",
					  "render": function ( data, type, full, meta ) {
						  
							   if(full.unitType)
							  return full.chargeGroup.groupName;
							  else{
								  return "";
							  }
					   }	
					},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-units='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editUnitType(this);"/>';
					}

				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-units='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmUnitTypeDel(this);"/>';
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
	$("#chargeId").val(unit["chargeGroup"].chargeId);
	$("#group-name").val(unit["chargeGroup"].groupName);
	populateGroup();
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

