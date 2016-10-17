$(function(){

	$(document).ready(function() {		
		createTenantsList();
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		getTenantDetails();
		
		if($("#tenId-pk").val()!=''){
			 $("#sel3").removeAttr('disabled');
			
		}
		else{
			$("#sel3").val("A");
			$("#sel3").prop("disabled", true);
		}
		
		
		
		
		populateBranchLov1();
		populateBranchLov2();
		populateStructures();
		populateUnits();
		
		createTenant();
	});
});



var model = {
		tenant: {
			branch:{
				brnCode:"",
				struct:{
					rentalId: "",
					units:{
						renId: "",
					},
				},
			},
			
	    }
	};

function populateBranchLov2(){
	if($("#unit-branch").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "unit-branch",
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
	            	
                },
	            id: "obName",
	            width:"200px"
	        });
	  }
}


function createTenant(){
	$('form#tenant-form')
	  .submit( function( e ) {
		  e.preventDefault();
		  var data = new FormData( this );
		  data.append( 'file', $( '#avatar' )[0].files[0] );
		  $.ajax( {
		      url: 'createTenant',
		      type: 'POST',
		      data: data,
		      processData: false,
		      contentType: false,
		      success: function (s ) {
		    	  bootbox.alert("Record created/updated Successfully");
		  		 $('#ten-branch').select2('val', null);
		  		 $('#ten-branch').attr('value', '');
		  		 $('#unit-branch').select2('val', null);
		  		 $('#unit-branch').attr('value', '');
		  		$('#ten-property').select2('val', null);
		  		 $('#ten-property').attr('value', '');
		  		$('#ten-unit').select2('val', null);
		  		 $('#ten-unit').attr('value', '');
		    	  $('#tenant-form').find("input[type=text],input[type=mobileNumber],input[file],input[type=email],input[type=password],input[type=hidden],input[type=number], textarea,select").val("");
		    	  $('#avatar').fileinput('reset');
		      },
		      error: function(xhr, error){
		    	  bootbox.alert(xhr.responseText);
		      }
		      });
	  });
}

function branchChanged(e, a, v) {
    model.tenant.branch = e.added || {};
    populateStructures();
    populateUnits();
    $("#ob-alloc-id").val(e.added.obId);
}

function structChanged(e, a, v) {
	populateUnits();
    model.tenant.branch.struct = e.added || {};
    $("#rental-id").val(e.added.rentalId);
    
}

function populateStructures(){
	if($("#ten-property").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "ten-property",
	            sort : 'houseName',
	            change: structChanged,
	            formatResult : function(a)
	            {
	            	return a.houseName
	            },
	            formatSelection : function(a)
	            {
	            	return a.houseName
	            },
	            initSelection: function (element, callback) {
	            	
                },
	            id: "rentalId",
	            width:"200px",
	            params: {branchId: function(){
	            	return model.tenant.branch.obId;
	            }}
	        });
	  }
}

function populateUnits(){
	if($("#ten-unit").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "ten-unit",
	            sort : 'unitName',
	            change: function(e, a, v){
	            	 $("#unit-id").val(e.added.renId);
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
	            	
                },
	            id: "renId",
	            width:"200px",
	            params: {rentalId: function(){
	            	return model.tenant.branch.struct.rentalId;
	            }}
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
	            	var code = $("#obId").val();
	                var name = $("#reg-brn-name").val();
	                model.tenant.branch.brnCode = code;
	                var data = {obName:name,obId:code};
	                callback(data);
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
			autoWidth: true,
			"ajax": {
				'url': url,
			},
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 10,
			destroy: true,
			"columns": [
                { "data": "tenantNumber" },
				{ "data": "fname",
				  "render": function ( data, type, full, meta ) {
					  return full.fname+" "+full.otherNames;
				  }
				},
				{ "data": "idPassport" },
				{ "data": "emailAddress" },
				{ "data": "phoneNo" },
				{ "data": "tenantType",
					"render": function ( data, type, full, meta ) {
						   if(!full.tenantType || full.tenantType==="I"){
							   return "Individual";
						   }
						   else if(full.tenantType==="C")
							  return "Corporate";
						  }
				},
				{ "data": "status",
				   "render": function ( data, type, full, meta ) {
					   if(!full.status || full.status==="T"){
						   return "Terminated";
					   }
					   else if(full.status==="A")
						  return "Active";
					  }
				},
				{ 
					"data": "tenId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editTenantForm" method="post"><input type="hidden" name="id" value='+full.tenId+'><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
					}

				},
			]
		} );
	  return currTable;
}

function getTenantDetails(){
	if(typeof tenidpkey!== 'undefined'){
		if(tenidpkey!==-2000){
			$.ajax( {
			      url: 'tenants/'+tenidpkey,
			      type: 'GET',
			      processData: false,
			      contentType: false,
			      success: function (s ) {
			    	  populateTenantDetails(s);
			    	  
			      },
			      error: function(xhr, error){
			    	  bootbox.alert(xhr.responseText);
			      }
			      });
		}
		else{
			tenantImage(-2000);
		}
		
	}
}

function populateTenantDetails(data){
	$("#tenId-pk").val(data.tenId);
	$("#fname").val(data.fname);
	$("#other-names").val(data.otherNames);
	$("#id-no").val(data.idPassport);
	$("#pin-no").val(data.pinNo);
	$("#email-address").val(data.emailAddress);
	$("#phone-no").val(data.phoneNo);
	$("#address").val(data.address);
	$("#sel2").val(data.tenantType);
	$("#dob").val(moment(data.dob).format('DD/MM/YYYY'));
	if(data.status!="T"){
		$("#sel3").val(data.status);
		$("#sel3").prop("disabled", false);
		$("#btn-save-tenant").prop("disabled", false);
	}
	else{
		$("#sel3").val(data.status);
		$("#sel3").prop("disabled", true);
		$("#btn-save-tenant").prop("disabled", true);
	}
	
	$("#ten-id").val(data.tenantNumber);
	$("#date-reg").val(moment(data.dateregistered).format('DD/MM/YYYY'));
	if(data.allocation.structure){
		$("#rental-loc").text(data.allocation.structure.houseName);
	}
	if(data.allocation.allocbranch){
		$("#branch-alloc").text(data.allocation.allocbranch.obName);
	}
	
	if(data.allocation.renunits){
		$("#unit-alloc").text(data.allocation.renunits.unitName);
	}	
	else{
		$("#alloc-details").hide();
	}
	if(data.allocation.dateregistered){
		$("#dt-alloc").val(moment(data.allocation.dateregistered).format('DD/MM/YYYY'));
	}
	
	$("#reg-brn-name").val(data.registeredbrn.obName);
	$("#obId").val(data.registeredbrn.obId);
	populateBranchLov1();
	if(data.dateterminated)
	$("#dt-terminated").val(moment(data.dateterminated).format('DD/MM/YYYY'));
	tenantImage(data.tenId);
	
	
}