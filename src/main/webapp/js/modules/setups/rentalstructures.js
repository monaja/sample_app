
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


$(function(){

	$(document).ready(function() {
		houseImage();
		createStructureTable();
		createRentalUnits();
		
		rivets.bind($("#rental_model"), model);
		
		function branchChanged(e, a, v) {
            model.rental.branch = e.added || {};
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
	var url = "rentalstructures";
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