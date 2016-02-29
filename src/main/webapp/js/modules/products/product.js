
function createProduct(prgCode){
	
	var dataTableUrl = "products/"+prgCode;
	  var productTbl = $('#products').DataTable( {
			"processing": true,
			"serverSide": true,
			"dom": '<"top"f>rt<"bottom"p><"clear">',
			"ajax": dataTableUrl,
			'destroy': true,
			"columns": [
				{"data": "proShtDesc"},
				{"data": "proDesc"},
				{"data": "proClaimPrefix"},
				{"data": "proPolicyPrefix"},
				{"data": "proRenewable"},
				{"data": "proSameDayRenewal"},
				{"data": "proMinPrem"},
				{"data": "proWef"},
				{"data": "proWet"},
			]
		} );
	  
	return productTbl;
}


function selectProductGroup(button){
	var prggroup = JSON.parse(decodeURI($(button).data("progroup")));	
	var prgCode = prggroup["prgCode"];
	$("#prg-key").val(prgCode);
	createProduct(prgCode);
}


$(function(){

	$(document).ready(function() {
		
		 var dataTableUrl = "productGroups";
		  var productGroupTbl = $('#productGroup').DataTable( {
				"processing": true,
				"serverSide": true,
				"dom": '<"top"f>rt<"bottom"p><"clear">',
				"ajax": dataTableUrl,
				"columns": [
					{
						"data": "prgDescn",
						 "render": function ( data, type, full, meta ) {
								return '<a href="javascript:void(0)" onclick="selectProductGroup(this);" data-progroup='+encodeURI(JSON.stringify(full)) + '>'+full.prgDescn+' </a>';
					    }

					},
				]
			} );
		  
		  var $prgForm = $('#prg-form');
		  var prgvalidator = $prgForm.validate();
		  $('#productGroupModal').on('hidden.bs.modal', function () {
			    branchvalidator.resetForm();
				$('#prg-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],select, textarea").val("");
	        });
		  
		  
		  $('#savePrgBtn').click(function(){
				if (!$prgForm.valid()) {
					return;
				}
				var data = {};
				$prgForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var url = "createProductGroup";
	            var request = $.post(url, data );
				request.success(function(){
					productGroupTbl.ajax.reload();
					prgvalidator.resetForm();
					$('#prg-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],select, textarea").val("");
					$('#productGroupModal').modal('hide');
				});

				request.error(function(data){
					alert(data.responseText);
				});
				request.always(function(){
	               // $btn.button('reset');
	            });
			});
		
	});
	
});