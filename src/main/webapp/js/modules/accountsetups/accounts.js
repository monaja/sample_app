
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
		    	  console.log(s);
		    	  //$("#acctId-pk").val(s);		    
		      },
		      error: function(xhr, error){
		    	  bootbox.alert(xhr.responseText);
		      }
		      });
	  });
}

$(function(){

	$(document).ready(function() {
		
		accountImage(-2000);
		createAccount();
		
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		
		rivets.bind($("#acct_model"), model);
		
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
	                 /*   */
	                },
		            id: "obId",
		            width:"200px"
		        });
		  }
		
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
                 
                },
	            id: "accId",
	            width:"200px"
	        });
		  }
		
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
				{ "data": "status" },
				{ 
					"data": "acctId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editRentalForm" method="post"><input type="hidden" name="rentalId" value='+full.rentalId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
					}

				},
				{ 
					"data": "acctId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-structs='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmStuctDel(this);"/>';
					 }

				},
			]
		} );
	  return currTable;
}