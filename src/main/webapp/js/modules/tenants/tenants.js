$(function(){

	$(document).ready(function() {		
		createTenantsList();
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		tenantImage(-2000);
		populateBranchLov1();
		populateBranchLov2();
	});
});



var model = {
		tenant: {
			branch:{
				brnCode:"",
			},
	    }
	};
function populateBranchLov2(){
	if($("#ten-branch").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "unit-branch",
	            sort : 'obName',
	            change: function(e, a, v){
	            	// $("#obId").val(e.added.obId);
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
//	            	var code = $("#obId").val();
//	                var name = $("#ob-name").val();
//	                model.accounts.branch.brnCode = code;
//	                var data = {obName:name,obId:code};
//	                callback(data);
                },
	            id: "obId",
	            width:"200px"
	        });
	  }
}

function populateBranchLov1(){
	if($("#ten-branch").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "ten-branch",
	            sort : 'obName',
	            change: function(e, a, v){
	            	// $("#obId").val(e.added.obId);
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
//	            	var code = $("#obId").val();
//	                var name = $("#ob-name").val();
//	                model.accounts.branch.brnCode = code;
//	                var data = {obName:name,obId:code};
//	                callback(data);
                },
	            id: "obId",
	            width:"200px"
	        });
	  }
}

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}


function tenantImage(id){
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
	    defaultPreviewContent: '<img src="'+getContextPath()+'/protected/tenants/setups/tenantImage/'+id+'"  style="width:180px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
}

function createTenantsList(){
	var url = "tenants";
	  var currTable = $('#tenTbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
                { "data": "tenantNumber" },
				{ "data": "fname",
				  "render": function ( data, type, full, meta ) {
					  return full.fname+" "+full.otherNames;
				  }
				},
				{ "data": "IdPassport" },
				{ "data": "emailAddress" },
				{ "data": "phoneNo" },
				{ "data": "dob" },
				{ "data": "tenantType" },
				{ "data": "dateregistered" },
				{ "data": "status",
				   "render": function ( data, type, full, meta ) {
					   if(!full.status || full.status==="I"){
						   return "Inactive";
					   }
					   else if(full.status==="A")
						  return "Active";
					  }
				},
				{ 
					"data": "tenId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editAcctForm" method="post"><input type="hidden" name="id" value='+full.acctId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
					}

				},
				{ 
					"data": "tenId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-account='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmAccountDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}