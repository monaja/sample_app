$(function(){

	$(document).ready(function() {
		
		$("#newSequency").on("click", function(){
			$('#seqform').find("input[type=text],input[type=number],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea,select").val("");
		});	
		
		createSequenceTable();
		saveUpdateSequence();
		
	});
});

function saveUpdateSequence(){
	var $currForm = $('#seqform');
	var currValidator = $currForm.validate();
	
	
	$('#saveSequencyBtn').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createSequence";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#seqList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#seqform').find("input[type=text],input[type=number],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea,select").val("");
        });
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
		
	});
}

function editSequence(button){
	var sequence = JSON.parse(decodeURI($(button).data("sequences")));
	$("#seq-code").val(sequence["seqId"]);
	$("#prefix-name").val(sequence["seqPrefix"]);
	$("#last-number").val(sequence["lastNumber"]);
	$("#next-number").val(sequence["nextNumber"]);
	$("#sel3").val(sequence["seqType"]);
	$("#sel2").val(sequence["transType"]);
}

function confirmSequenceDelete(button){
	var sequence = JSON.parse(decodeURI($(button).data("sequences")));
	bootbox.confirm("Are you sure want to delete "+sequence["seqPrefix"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteSequence/' + sequence["seqId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#seqList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}


function createSequenceTable(){
	var url = "sequences";
	  var currTable = $('#seqList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 20, 30], [10, 20, 30] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "seqPrefix" },
				{ "data": "lastNumber" },
				{ "data": "nextNumber" },
				{ "data": "seqType" },
				{ "data": "transType" },
				{ 
					"data": "seqId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-sequences='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editSequence(this);"/>';
					}

				},
				{ 
					"data": "seqId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-sequences='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmSequenceDelete(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}