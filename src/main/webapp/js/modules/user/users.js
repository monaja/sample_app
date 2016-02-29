var CLIENT_MANAGE_USER='user';
function openEditDialog(button)
{
	$("#user-id").val($(button).data("userid"));
	var name  = $(button).data("fullname");
	var finalName = decodeURI(name);
	$("#user-name").val(finalName);
	$("#user-username").val($(button).data("username"));
	$('#userModal').modal('show');
}

$(function(){

	$(document).ready(function() {

		var $userForm = $('#user-form');
		var validator = $userForm.validate();
		var dataTableUrl = "datatable";
        var usersTable = $('#usersTable').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": dataTableUrl,
			"columns": [
				{ "data": "name"},
				{ "data": "username" },
				{ "data": "enabled"},
				{
					"data": "id"

				},
			]
		} );
        
        
        $('#userModal').on('hidden.bs.modal', function () {
        	validator.resetForm();
			$('#user-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password], textarea").val("");
        });

		$('#saveUpdateBtn').click(function(){
			
			
			if (!$userForm.valid()) {
				return;
			}
			var data = {};
			$userForm.serializeArray().map(function(x){data[x.name] = x.value;});
			var url = "save_or_update";
            var request = $.post(url, data );
			request.success(function(){
				usersTable.ajax.reload();
				validator.resetForm();
				$('#user-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password], textarea").val("");
				$('#userModal').modal('hide');
			});

			request.error(function(data){
				alert(data.responseText);
			});
			request.always(function(){
               // $btn.button('reset');
            });
		});

		
	})

});