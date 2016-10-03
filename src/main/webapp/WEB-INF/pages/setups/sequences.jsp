<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
	src="<c:url value="/js/modules/setups/sequences.js"/>"></script>
<div class="box box-info">
	<form id="seqform" class="form-horizontal">
		<div class="box-body">
		    <input type="hidden" name="seqId" id="seq-code">
			<div class="form-group">
				<label for="brn-id" class="col-md-3 control-label">Prefix</label>

				<div class="col-md-4">
					<input type="text" class="form-control" id="prefix-name"
						name="seqPrefix" required>
				</div>
			</div>
			<div class="form-group">
				<label for="frac-units" class="col-md-3 control-label">Last
					Value</label>

				<div class="col-md-4">
					<input type="number" class="editUserCntrls form-control"
						id="last-number" name="lastNumber" disabled>
				</div>
			</div>
			
			<div class="form-group">
				<label for="fraction" class="col-md-3 control-label">Next Value</label>

				<div class="col-md-4">
					<input type="number" class="editUserCntrls form-control"
						id="next-number" name="nextNumber" required>
				</div>
			</div>
			

			<div class="form-group">
				<label for="curr-symbol" class="col-md-3 control-label">Sequence Type</label>

				<div class="col-md-4">
					<select class="form-control" id="sel3" name="seqType" required>
							        <option value="">Select Sequence Type</option>
							        <option value="PBY">Per Branch Per Year</option>
								    <option value="PBA">Per Branch</option>
								    <option value="PAY">All Branches Per Year</option>
								     <option value="PAA">All Branches</option>
								  </select>
				</div>
			</div>
			<div class="form-group">
				<label for="curr-symbol" class="col-md-3 control-label">Transaction Type</label>

				<div class="col-md-4">
					<select class="form-control" id="sel2" name="transType" required>
							        <option value="">Select Transaction Type</option>
							        <option value="T">Tenants Definition</option>
								    <option value="I">Invoices</option>
								    <option value="R">Receipts</option>
								     <option value="A">Agents</option>
								  </select>
				</div>
			</div>

		</div>
		<div class="box-footer">
		<button type="button" class="btn btn-default" id="newSequency">New</button>
			<button data-loading-text="Saving..." id="saveSequencyBtn"
				type="button" class="btn btn-primary">Save</button>
			
		</div>

	</form>
	<div class="spacer"></div>
	<hr>
	<table id="seqList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Prefix</th>
				<th>Last Value</th>
				<th>Next Value</th>
				<th>Sequence Type</th>
				<th>Transaction Type</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>
