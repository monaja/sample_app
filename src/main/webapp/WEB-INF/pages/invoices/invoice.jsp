<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/modules/transactions/invoice.js"/>"></script>

<script type="text/javascript">
		var invoiceCode = ${invoiceCode};
	</script>

<div class="box box-info" id="acct_model">
     <div class="box-header with-border">
              <h3 class="box-title">Enter Invoice Details</h3>
            </div>
            <div class="box-footer">
            <a
				href="<c:url value='/protected/transactions/invoices/invlist'/> " 
				class="btn btn-info pull-right" style="margin-right: 10px;">Back</a> 
            <input type="button" class="btn btn-info pull-right"
				style="margin-right: 10px;" value="Save" id="btn-add-invoice"> 
            <input type="button" id="auth-btn"
				class="btn btn-info pull-right" style="margin-right: 10px;"
				value="Authorize">
			<input type="button" data-toggle="modal" data-target="#reportsModal"
				class="btn btn-info pull-right" style="margin-right: 10px;"
				value="Reports">
				
				</div>
	<div class="box-body">			
	<form id="invoice-form" class="form-horizontal">
		
		
			<input type="hidden" name="invoiceId" id="invoice-pk">
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Transaction
						Date</label>

					<div class="col-md-6">
						<div class='input-group date datepicker-input' id="inv-date-grp">
							<input type='text' class="form-control pull-right" name="invoiceDate"
								id="inv-date" required/>
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
						<select class="form-control" id="pay-freq" name="frequency" required>
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
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Select
						Tenant</label>

					<div class="col-md-6">
					   <div id="tenant-info">
						<input type="hidden" id="ten-id" name="tenantId"
							rv-value="invoice.tenant.tenId" /> <input type="hidden"
							id="ten-name">
						<div id="tenant-div" class="form-control"
							select2-url="<c:url value="/protected/transactions/invoices/activetenants"/>">

						</div>
						</div>
						<p class="form-control-static" id="tenant-label-name"></p>
					</div>
				</div>
				<div class="col-md-6">
				  <label for="brn-id" class="col-md-5 control-label">Unit Details</label>

					<div class="col-md-7">
						<input type="text"  id="unit-number"
							class="form-control" readonly>
					</div>
				</div>


			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
				
				   <label for="brn-id" class="col-md-6 control-label">Invoice
						Number</label>

					<div class="col-md-6">
						<input type="text" name=invoiceNumber" id="inv-number"
							class="form-control" placeholder="" readonly>
					</div>
					
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Transaction
						Branch</label>
					<div class="col-md-7">
					    <div id="branch-info">
						<input type="hidden" id="brn-id" name="branchId"
							rv-value="invoice.branch.brnCode" />
					    <input type="hidden" id="trans-brn-name">
						<div id="brn-div" class="form-control"
							select2-url="<c:url value="/protected/rental/setups/branches"/>">

						</div>
						</div>
						<p class="form-control-static" id="branch-label-name"></p>
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
								 id="from-date" required/>
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
								id="wet-date" readonly required/>
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
					   <div id="curr-info-panel">
						<input type="hidden" id="cur-id" name="currCode"
							rv-value="invoice.currency.curCode" /> 
						<input type="hidden" id="cur-name">
						<div id="curr-div" class="form-control"
							select2-url="<c:url value="/protected/transactions/invoices/currencies"/>">

						</div>
						</div>
						<p class="form-control-static" id="currency-label-name"></p>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Payment
						Mode</label>
					<div class="col-md-7">
					  <div id="pay-mode-info">
						<input type="hidden" id="pymt-id" name="payId"
							rv-value="invoice.paymentmodes.pmId" />
						<input type="hidden" id="pymt-desc">
						<div id="div-paymodes" class="form-control"
							select2-url="<c:url value="/protected/transactions/invoices/paymentmodes"/>">

						</div>
						</div>
						<p class="form-control-static" id="paymode-label-name"></p>
					</div>

				</div>


			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Invoice
						Status</label>

					<div class="col-md-6">
						<p class="form-control-static" id="inv-status">Draft</p>
					</div>
				</div>
				<div class="col-md-6">
				   <label for="brn-id" class="col-md-5 control-label">Revision
						Number</label>

					<div class="col-md-7">
						<p class="form-control-static" id="inv-rev-number"></p>
					</div>
				</div>


			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Invoice
						Amount</label>

					<div class="col-md-6">
						<p class="form-control-static" id="tot-inv-amt"></p>
					</div>
				</div>
				<div class="col-md-6">
					<label for="brn-id" class="col-md-5 control-label">Total
						Taxes</label>

					<div class="col-md-7">
						<p class="form-control-static" id="tot-tax-amt"></p>
					</div>

				</div>


			</div>

			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Net
						Amount</label>

					<div class="col-md-6">
						<p class="form-control-static" id="tot-net-amt"></p>
					</div>
				</div>
				<div class="col-md-6">
				     <label for="brn-id" class="col-md-5 control-label">Renewal
						Date</label>

					<div class="col-md-7">
						<p class="form-control-static" id="inv-ren-date"></p>
					</div>
				</div>


			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Installment Amount</label>

					<div class="col-md-6">
						<p class="form-control-static" id="inv-install-amt"></p>
					</div>
				</div>
				<div class="col-md-6">
				     
				</div>


			</div>

	</form>
	<div id="rates-details-div">
     	<h3 class="box-title">Invoice Details</h3>
	    <hr>
	    <table id="inv-detail-tbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Charge Name</th>
				<th>Gross Amount</th>
				<th>Tax Amount</th>
				<th>Net Amount</th>
			</tr>
		</thead>
	</table>
	</div>
	
	<div id="rates-div">
     	<h3 class="box-title">Invoice Charges</h3>
	    <hr>
	    <div class="spacer"></div>
	     <div class="box-footer">
            <input type="button" id="btn-add-charge"
				class="btn btn-info pull-left" style="margin-right: 10px;"
				value="New Charge">
				</div>
     	 
    </div>
	</div>
	
	
	
</div>

<div class="modal fade" id="chargesModal" tabindex="-1" role="dialog"
		aria-labelledby="chargesModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="chargesModalLabel">
						New Charge
					</h4>
				</div>
				<div class="modal-body">
					<div id="rates-new-div">
					
					</div>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveNewInvoice"
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
	
	<div class="modal fade" id="reportsModal" tabindex="-1" role="dialog"
		aria-labelledby="reportsModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="reportsModalLabel">
						Reports
					</h4>
				</div>
				<div class="modal-body">
				   <div class="row">
				      <div class="col-md-6">
				     
					 <ul style="list-style-type: none;">
					<li>  <a href="#" >Tenant Debit Note</a></li>
					  <li>  <a href="#" >Agent Credit Note</a></li>
					  <li>  <a href="#" >Rental Working</a></li>
					</ul>
				      </div>
				      <div class="col-md-6">
				     
					   <ul style="list-style-type: none;">
					<li>  <a href="#" >Invoice Note</a></li>
					</ul>
					
				      </div>
				   </div>
					
					
				</div>
				<div class="modal-footer">
					
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Close
					</button>
				</div>
			</div>
		</div>
	</div>