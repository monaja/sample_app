<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/modules/transactions/invoice.js"/>"></script>

<div class="box box-info" id="acct_model">

	<form id="invoice-form" class="form-horizontal">
		<div class="box-footer">

			<input type="button" class="btn btn-info pull-left"
				style="margin-right: 10px;" value="Save"> <a
				href="<c:url value='/protected/setups/accts'/> "
				class="btn btn-info pull-left">Back</a> <input type="button"
				class="btn btn-info pull-right" style="margin-right: 10px;"
				value="Authorize">
		</div>
		<div class="box-body">
			<input type="hidden" name="invoiceId" id="invoice-pk">
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Invoice
						Number</label>

					<div class="col-md-6">
						<input type="text" name=invoiceNumber id="inv-number"
							class="form-control" placeholder="" readonly>
					</div>
				</div>
				<div class="col-md-6"></div>


			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Select
						Tenant</label>

					<div class="col-md-6">
						<input type="hidden" id="acc-id" name="tenant"
							rv-value="accounts.accType.accId" /> <input type="hidden"
							id="acc-name">
						<div id="accounttypes" class="form-control"
							select2-url="<c:url value="/protected/setups/selAcctTypes"/>">

						</div>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Transaction
						Branch</label>
					<div class="col-md-7">
						<input type="hidden" id="acc-id" name="branch"
							rv-value="accounts.accType.accId" /> <input type="hidden"
							id="acc-name">
						<div id="accounttypes" class="form-control"
							select2-url="<c:url value="/protected/setups/selAcctTypes"/>">

						</div>
					</div>

				</div>


			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Transaction
						Date</label>

					<div class="col-md-6">
						<div class='input-group date datepicker-input'>
							<input type='text' class="form-control pull-right" name="invoiceDate"
								id="dob" />
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<label for="brn-id" class="col-md-5 control-label">Payment
						Frequency</label>

					<div class="col-md-7">
						<select class="form-control" id="sel2" name="frequency">
							<option value="">Select Payment Frequency</option>
							<option value="M">Monthly</option>
							<option value="Q">Quartely</option>
							<option value="S">Semi-Annually</option>
							<option value="A">Annually</option>
						</select>
					</div>

				</div>


			</div>

			<div class="form-group">
				<div class="col-md-6  form-required">
					<label for="brn-id" class="col-md-6 control-label">Date
						From</label>

					<div class="col-md-6">
						<div class='input-group date datepicker-input' id="wef-date">
							<input type='text' class="form-control pull-right" name="wefDate"
								 id="from-date"/>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Date
						To</label>
					<div class="col-md-7">
						<div class='input-group date datepicker-input'>
							<input type='text' class="form-control pull-right" name="wetDate"
								id="wet-date" readonly />
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</div>
						</div>
					</div>

				</div>


			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Select
						Currency</label>

					<div class="col-md-6">
						<input type="hidden" id="acc-id" name="transCurrency"
							rv-value="accounts.accType.accId" /> <input type="hidden"
							id="acc-name">
						<div id="accounttypes" class="form-control"
							select2-url="<c:url value="/protected/setups/selAcctTypes"/>">

						</div>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Payment
						Mode</label>
					<div class="col-md-7">
						<input type="hidden" id="acc-id" name="paymentMode"
							rv-value="accounts.accType.accId" /> <input type="hidden"
							id="acc-name">
						<div id="accounttypes" class="form-control"
							select2-url="<c:url value="/protected/setups/selAcctTypes"/>">

						</div>
					</div>

				</div>


			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Invoice
						Status</label>

					<div class="col-md-6">
						<p class="form-control-static">Draft</p>
					</div>
				</div>
				<div class="col-md-6"></div>


			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Invoice
						Amount</label>

					<div class="col-md-6">
						<p class="form-control-static">0</p>
					</div>
				</div>
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Total
						Taxes</label>

					<div class="col-md-6">
						<p class="form-control-static">0</p>
					</div>

				</div>


			</div>

			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Net
						Amount</label>

					<div class="col-md-6">
						<p class="form-control-static">0</p>
					</div>
				</div>
				<div class="col-md-6"></div>


			</div>

		</div>

	</form>
<div class="box-body">
	<h4>Invoice Rates</h4>
	<div class="spacer"></div>
	

		<button type="button" class="btn btn-info" data-toggle="modal"
			data-target="#invmodal">New</button>

	<hr>
	<table id="invdet-tbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Rate Type</th>
				<th>Amount</th>
				<th>Taxes</th>
				<th>Net Amount</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
	</div>
</div>