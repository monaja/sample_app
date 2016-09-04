<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/utilitysetups.js"/>"></script>
<div class="box box-info">
<div class="box-body">
  <div class="spacer"></div>
    <div class="spacer"></div>
     <button class="btn btn-info pull-right" id="btn-add-modes">New</button>
	<h4>Payment Modes List</h4>
	<div class="spacer"></div>
  <div class="spacer"></div>
	<table id="paymodetbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Sht Desc</th>
				<th>Payment Mode Desc</th>
				<th>Min Value</th>
				<th>Max Value</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
  </div>
  <div class="modal fade" id="paymentModesModal" tabindex="-1" role="dialog"
		aria-labelledby="paymodesModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="paymodesModalLabel">
						Edit/Add Payment Modes
					</h4>
				</div>
				<div class="modal-body" id="branch_model">
					<form id="pay-modes-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="pm-id" name="pmId">
						<div class="form-group">
							<label for="brn-id" class="col-md-3 control-label">Sht Desc</label>

							<div class="col-md-8">
								<input type="text" class="form-control" id="sht-desc"
									name="pmShtDesc"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Description</label>

							<div class="col-md-8">
							    <input type="text" class="form-control" id="description"
									name="pmDesc"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Min Value</label>

							<div class="col-md-8">
							    <input type="text" class="form-control number" id="min-val"
									name="pmMinValue" required>
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Max Value</label>

							<div class="col-md-8">
							    <input type="text" class="form-control" id="max-val"
									name="pmMaxValue"  required>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="savePaymentModes"
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