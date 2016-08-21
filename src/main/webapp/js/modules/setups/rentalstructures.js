
function houseImage(){
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
	    defaultPreviewContent: '<img src="'+getContextPath()+'/protected/rental/setups"  style="width:160px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
}


function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}

function createStructure(){
	var $structForm = $('#struct-form');
	 var branchvalidator = $structForm.validate();
	 $('#saveStructBtn').click(function(){
			if (!$structForm.valid()) {
				return;
			}
			var $btn = $(this).button('Saving');
			var data = {};
			$structForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "createRentalStruct";
            var request = $.post(url, data );
            request.success(function(){
            	bootbox.alert("Record created/updated Successfully");
            });
            request.error(function(jqXHR, textStatus, errorThrown){
            	console.log(jqXHR);
            	console.log(textStatus);
            	console.log(errorThrown);
            	bootbox.alert(jqXHR.responseText);
    		});
    		request.always(function(){
    			$btn.button('reset');
            });
	 });
}


$(function(){

	$(document).ready(function() {
		houseImage();
		createStructureTable();
		createRentalUnits();
		createStructure();
		
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
	    }
	};


function createStructureTable(){
	var url = "rentalstructures/0";
	  if ($("#obId").val() != ''){
		  url = "rentalstructures/"+$("#obId").val();
		}
	  console.log(url);
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
						return '<input type="button" class="btn btn-primary" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editRateType(this);"/>';
					}

				},
				{ 
					"data": "rentalId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmRateTypeDel(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}

function createRentalUnits(){
	var url = "rentalUnits/0";
	 if ($("#rental-struct-pk").val() != ''){
		 url = "rentalUnits/"+$("#rental-struct-pk").val();
		}
	  var currTable = $('#rentalUnits').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "unitName" },
				{ "data": "unitType",
				  "render": function ( data, type, full, meta ) {
						   if(full.unitType)
						  return full.unitType.unit_name;
						  else{
							  return "";
						  }
				   }	
				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editRateType(this);"/>';
					}

				},
				{ 
					"data": "renId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-rates='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmRateTypeDel(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}