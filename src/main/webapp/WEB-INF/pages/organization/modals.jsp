<!--   

This was replaced with Select drop downs 


<div class="modal fade" id="countryModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Select Country</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger alert-dismissible" role="alert"
					id="errorDiv" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>


				<table id="country" class="table table-hover table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>Country</th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="countyModal" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Select County/State</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger alert-dismissible" role="alert"
					id="errorDiv" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>


				<table id="county" class="table table-hover table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>County/State</th>
							<th></th>
						</tr>
					</thead>
				</table>
			</div>
			
		</div>
	</div>
</div>

<div class="modal fade" id="townModal" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Select Town</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger alert-dismissible" role="alert"
					id="errorDiv" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>


				<table id="town" class="table table-hover table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							<th>ID</th>
							<th>Town</th>
							<th>Code</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="currencyModal" role="dialog">
	<div class="modal-dialog">

		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">Select Currency</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger alert-dismissible" role="alert"
					id="errorDiv" style="display: none;">
					<button type="button" class="close" data-dismiss="alert">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>


				<table id="currencies" class="table table-hover table-bordered"
					cellspacing="0" width="100%">
					<thead>
						<tr>
							
							<th>ISO Code</th>
							<th>Currency</th>
							<th>Code</th>
						</tr>
					</thead>
				</table>
			</div>
			
		</div>
	</div>
</div>
 -->

 <div class="modal fade" id="branchModal" tabindex="-1" role="dialog"
		aria-labelledby="branchModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="branchModalLabel">
						Edit/Add Branch
					</h4>
				</div>
				<div class="modal-body" id="branch_model">
					<form id="branch-form" class="form-horizontal">
					    <div class="alert alert-error alert-dismissible" id="errorDiv" style="display: none;">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						   <div id="errorId"></div>
						</div>
						<input type="hidden" class="form-control" id="brn-code" name="obId">
						<input type="hidden" name="organization" id="branchOrgCode"/>
						<div class="form-group">
							<label for="brn-id" class="col-md-3 control-label">Branch ID</label>

							<div class="col-md-8">
								<input type="text" class="form-control" id="brn-id"
									name="obShtDesc"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="brn-name" class="col-md-3 control-label">Branch Name</label>

							<div class="col-md-8">
								<input type="text" class="editUserCntrls form-control"
									id="brn-name" name="obName" 
									required>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveBranchBtn"
						type="button" class="btn btn-primary">
						Save
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel
					</button>
				</div>
			</div>
		</div>
	</div>
	
	
    
    
    <div class="modal fade" id="bankModal" tabindex="-1" role="dialog"
		aria-labelledby="bankModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="bankModalLabel">
						Edit/Add Bank
					</h4>
				</div>
				<div class="modal-body">
					<form id="bank-form" class="form-horizontal">
					     <div class="alert alert-error alert-dismissible" id="errorbankDiv" style="display: none;">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						   <div id="errorbankId"></div>
						</div>
						<input type="hidden" class="form-control" id="bank-code" name="bankCode">
						<input type="hidden" name="organization" id="bankOrgCode"/>
						<div class="form-group">
							<label for="bank-id" class="col-md-3 control-label">Bank ID</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="bank-id"
									name="bankShtDesc"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="brn-name" class="col-md-3 control-label">Bank Name</label>

							<div class="col-md-8">
								<input type="text" class="editUserCntrls form-control"
									id="bank-name" name="bankName" 
									required>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveBankBtn"
						type="button" class="btn btn-primary">
						Save
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel
					</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
