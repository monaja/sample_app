
function accountImage(id){
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
	    defaultPreviewContent: '<img src="'+getContextPath()+'/protected/setups/accountImage/'+id+'"  style="width:180px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
}

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}

function createAccount(){
	$('form#account-form')
	  .submit( function( e ) {
		  e.preventDefault();
		  var data = new FormData( this );
		  data.append( 'file', $( '#avatar' )[0].files[0] );
		  $.ajax( {
		      url: 'createAccount',
		      type: 'POST',
		      data: data,
		      processData: false,
		      contentType: false,
		      success: function (s ) {
		    	  bootbox.alert("Record created/updated Successfully");
		    	  $('#account-form').find("input[type=text],input[type=mobileNumber],input[file],input[type=email],input[type=password],input[type=hidden],input[type=number], textarea,select").val("");
		    	  $('#avatar').fileinput('reset');
		    	  createBranchSelect();
		  		  createAccountTypeSelect();
			  	  $("#sel2").val("A");
				  $("#sel2").prop("disabled", true);
		      },
		      error: function(xhr, error){
		    	  bootbox.alert(xhr.responseText);
		      }
		      });
	  });
}

function createBranchSelect(){
	if($("#acct-branch").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "acct-branch",
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
	                var name = $("#ob-name").val();
	                model.accounts.branch.brnCode = code;
	                var data = {obName:name,obId:code};
	                callback(data);
                },
	            id: "obId",
	            width:"200px"
	        });
	  }
}

function createAccountTypeSelect(){
	if($("#accounttypes").filter("div").html() != undefined)
	  {
		Select2Builder.initAjaxSelect2({
          containerId : "accounttypes",
          sort : 'accShtDesc',
          change:  function(e, a, v){
          	$("#acc-id").val(e.added.accId);
          },
          formatResult : function(a)
          {
          	return a.accShtDesc
          },
          formatSelection : function(a)
          {
          	return a.accShtDesc
          },
          initSelection: function (element, callback) {
        	  var code = $("#acc-id").val();
              var name = $("#acc-name").val();
              model.accounts.accType.accId = code;
          	  var data = {accShtDesc:name,accId:code};
              callback(data);
          },
          id: "accId",
          width:"200px"
      });
	  }
}


function confirmAccountDel(button){
	var account = JSON.parse(decodeURI($(button).data("account")));
	bootbox.confirm("Are you sure want to delete "+account["fname"]+" "+account["otherNames"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteAccount/' + account["acctId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#acctbl').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

function populateAccountDetails(data){
	$("#acctId-pk").val(data.acctId);
	$("#fname").val(data.fname);
	$("#other-names").val(data.otherNames);
	$("#idno").val(data.idPassportNo);
	$("#pinNo").val(data.pinNo);
	$("#email").val(data.email);
	$("#phone-no").val(data.phoneNo);
	$("#address").val(data.address);
	$("#sel2").val(data.status);
	$("#dob").val(moment(data.dob).format('DD/MM/YYYY'));
	$("#acc-id").val(data.accountType.accId);
	$("#acc-name").val(data.accountType.accShtDesc);
	createAccountTypeSelect();
	$("#obId").val(data.branch.obId);
	$("#ob-name").val(data.branch.obName);
	createBranchSelect();
	accountImage(data.acctId);
	$("#sel2").prop("disabled", false);
	
}

function getAccountDetails(){
	if(typeof accIdpk!== 'undefined'){
		if(accIdpk!==-2000){
			$.ajax( {
			      url: 'accounts/'+accIdpk,
			      type: 'GET',
			      processData: false,
			      contentType: false,
			      success: function (s ) {
			    	  populateAccountDetails(s);
			      },
			      error: function(xhr, error){
			    	  bootbox.alert(xhr.responseText);
			      }
			      });
		}
		else{
			accountImage(-2000);
		}
		
	}
}

$(function(){

	$(document).ready(function() {
		
		
		
		
		createAccount();
		
		if($("#acctId-pk").val()!=''){
			 $("#sel2").removeAttr('disabled');
		}
		else{
			$("#sel2").val("A");
			$("#sel2").prop("disabled", true);
		}
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		rivets.bind($("#acct_model"), model);
		
		createBranchSelect();
		createAccountTypeSelect();
		getAccountDetails();
		
		
		 if($("#acc-types").filter("div").html() != undefined)
		  {
			  Select2Builder.initAjaxSelect2({
		            containerId : "acc-types",
		            sort : 'accShtDesc',
		            change: acctypeChanged,
		            formatResult : function(a)
		            {
		            	return a.accShtDesc
		            },
		            formatSelection : function(a)
		            {
		            	return a.accShtDesc
		            },
		            initSelection: function (element, callback) {
	                 
	                },
		            id: "accId",
		            width:"200px"
		        });
		  }
		 
		 		
		function acctypeChanged(e, a, v) {
			 
           model.accounts.accType = e.added || {};
           $("#accId").val(e.added.accId);
           createAccounts();
       }
		
	})
});


var model = {
		accounts: {
			accType:{
				accId:"",
			},
			branch:{
				brnCode:"",
			},
	    }
	};


function createAccounts(){
	var url = "allaccounts/0";
	  if ($("#accId").val() != ''){
		  url = "allaccounts/"+$("#accId").val();
		}
	  var currTable = $('#acctbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "fname",
				  "render": function ( data, type, full, meta ) {
					  return full.fname+" "+full.otherNames;
				  }
				},
				{ "data": "idPassportNo" },
				{ "data": "email" },
				{ "data": "phoneNo" },
				{ "data": "dob" },
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
					"data": "acctId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editAcctForm" method="post"><input type="hidden" name="id" value='+full.acctId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
					}

				},
				{ 
					"data": "acctId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-account='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmAccountDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}