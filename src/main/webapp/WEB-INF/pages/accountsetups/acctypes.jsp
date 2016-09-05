<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/accountsetups/accounttypes.js"/>"></script>
<div class="box box-info">
<div class="box-body">
  <div class="spacer"></div>
    <div class="spacer"></div>
     <button class="btn btn-info pull-right" id="btn-add-acctypes">New</button>
	<h4>Account Types List</h4>
	<div class="spacer"></div>
  <div class="spacer"></div>
	<table id="accounttbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Sht Desc</th>
				<th>Account Type</th>
				<th>VAT Applic.</th>
				<th>VAT Value</th>
				<th>WITHX Applic.</th>
				<th>WITHX Value</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
  </div>
  <div class="modal fade" id="acctTypesModal" tabindex="-1" role="dialog"
		aria-labelledby="acctTypesModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="acctTypesModalLabel">
						Edit/Add Account Types
					</h4>
				</div>
				<div class="modal-body" id="branch_model">
					<form id="acct-types-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="acc-id" name="accId">
						<div class="form-group">
							<label for="brn-id" class="col-md-3 control-label">Sht Desc</label>

							<div class="col-md-8">
								<input type="text" class="form-control" id="sht-desc"
									name="accShtDesc"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Account Type</label>

							<div class="col-md-8">
							    <input type="text" class="form-control" id="description"
									name="accName"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="enabled" class="col-md-3 control-label">
								Vat Applicable</label>
			
							<div class="col-md-4">
								<input type="checkbox" id="vat-appli" name="vatAppli">
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">VAT Rate</label>

							<div class="col-md-8">
							    <input type="text" class="form-control" id="vat-rate"
									name="vatRate" disabled>
							</div>
						</div>
						<div class="form-group">
							<label for="enabled" class="col-md-3 control-label">
								WHTX Applicable</label>
			
							<div class="col-md-4">
								<input type="checkbox" id="whtx-appli" name="whtxAppl">
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">WHTX Rate</label>

							<div class="col-md-8">
							    <input type="text" class="form-control" id="whtx-rate"
									name="whtaxVal" disabled>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveAccountTypes"
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
  </div>