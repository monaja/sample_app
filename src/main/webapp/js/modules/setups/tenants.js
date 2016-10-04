$(function(){

	$(document).ready(function() {
		
		createTenantTable();
		saveRentalDetails();
		getTenantDetails();
	});
	
});


function saveRentalDetails(){
	var $tenantForm = $('#tenant-form');
	var validator = $tenantForm.validate();
	$("#btn-save-landlord").click(function(){
		if (!$tenantForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$tenantForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createTenant";
        var request = $.post(url, data );
			request.success(function(){
				bootbox.alert("Record created/updated Successfully");
				validator.resetForm();
				$('#tenant-form').find("input[type=text],input[type=mobileNumber],input[type=email],input[type=password],input[type=hidden], textarea").val("");
			});

			request.error(function(jqXHR, textStatus, errorThrown){
				bootbox.alert(jqXHR.responseText);
			});
			request.always(function(){
				$btn.button('reset');
        });
	})
}


function populateTenantDetails(data){
	$("#tenant-pk").val(data.tenantId);
	$("#first-name").val(data.fname);
	$("#other-names").val(data.otherNames);
	$("#address").val(data.address);
	$("#id-no").val(data.idPassport);
	$("#tel-no").val(data.telNo);
	$("#sms-no").val(data.smsNo);
	$("#email-address").val(data.emailAddress);
	$("#bank-name").val(data.bankName);
	$("#account-number").val(data.acctNumber);
}


function getTenantDetails(){
	if(typeof tenantId!== 'undefined'){
		if(tenantId!==-2000){
			$.ajax( {
			      url: 'tenantDetails/'+tenantId,
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
	}
}


function confirmTenantDel(button){
	var tenant = JSON.parse(decodeURI($(button).data("tenant")));
	bootbox.confirm("Are you sure want to delete "+tenant["fname"]+" "+tenant["otherNames"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteTenant/' + tenant["tenantId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#tenantDet').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                      bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}


function createTenantTable(){
	var url = "tenants";
	  var table = $('#tenantDet').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 15], [10, 15] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "fname" },
				{ "data": "otherNames" },
				{ "data": "idPassport" },
				{ "data": "telNo" },
				{ "data": "emailAddress" },
				{ 
					"data": "tenantId",
					"render": function ( data, type, full, meta ) {
						return '<form action="editTenantForm" method="post"><input type="hidden" name="tenantId" value='+full.tenantId+'><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
					}

				},
				{ 
					"data": "tenantId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-tenant='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmTenantDel(this);"/>';
					}

				},
			]
		} );
	  return table;
}