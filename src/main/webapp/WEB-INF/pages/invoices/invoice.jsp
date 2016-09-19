<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/invoice.js"/>"></script>

<div class="box box-info" id="acct_model">

	<form id="invoice-form" class="form-horizontal">
		<div class="box-body">
			<input type="hidden" name="acctId" id="invoice-pk">
			<div class="form-group">
				<label for="reg-wet" class="col-md-3 control-label">Invoice Date</label>

				<div class="col-md-3">
					<div class='input-group date datepicker-input'>
						<input type='text' class="form-control pull-right" name="dob"
							id="dob" />
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">ID/Passport
						No</label>
					<div class="col-md-7">
						<input type="text" name="idPassportNo" id="idno"
							class="form-control" placeholder="ID or Passport No" required>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Pin
						No</label>
					<div class="col-md-7">
						<input type="text" name="pinNo" id="pinNo" class="form-control"
							placeholder="Pin No" required>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">Email</label>
					<div class="col-md-7">
						<input type="email" name="email" id="email" class="form-control"
							placeholder="Email" required>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Phone
						No</label>
					<div class="col-md-7">
						<input type="text" name="phoneNo" id="phone-no"
							class="form-control" placeholder="Phone No" required>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Address</label>
					<div class="col-md-7">
						<textarea rows="3" cols=30 class="form-control" name="address"
							id="address"></textarea>
					</div>

				</div>
				<div class="col-md-6">
					<label for="brn-id" class="col-md-5 control-label">Select
						Account Type</label>

					<div class="col-md-7">
						<input type="hidden" id="acc-id" name="accountType"
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
					<label for="houseId" class="control-label col-md-5">Status</label>
					<div class="col-md-7">
						<select class="form-control" id="sel2" name="status">
							<option value="">Select Status</option>
							<option value="A">Active</option>
							<option value="I">Inactive</option>
						</select>
					</div>
				</div>
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">Branch</label>
					<div class="col-md-7">
						<input type="hidden" id="obId" name="branch"
							rv-value="accounts.branch.brnCode" /> <input type="hidden"
							id="ob-name">
						<div id="acct-branch" class="form-control"
							select2-url="<c:url value="/protected/rental/setups/branches"/>">

						</div>
					</div>
				</div>
			</div>
			<div class='spacer'></div>
			<h4>Other Information</h4>
			<hr>
			<div class="form-group">
				<label for="reg-wet" class="col-md-3 control-label">Date of
					Birth</label>

				<div class="col-md-3">
					<div class='input-group date datepicker-input'>
						<input type='text' class="form-control pull-right" name="dob"
							id="dob" />
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-calendar"></span>
						</div>
					</div>
				</div>
			</div>
			<div class='spacer'></div>

			<div class="form-group">

				<div class="col-md-6 form-required">
					<label for="file" class="control-label col-md-5"> Photo</label>
					<div class="col-md-7">
						<div class="kv-avatar center-block" style="width: 200px">
							<input name="file" type="file" id="avatar" class="file-loading">

						</div>
					</div>
				</div>

			</div>

		</div>
		<div class="box-footer">

			<input type="submit" class="btn btn-info pull-left"
				style="margin-right: 10px;" value="Save and New"> <a
				href="<c:url value='/protected/setups/accts'/> "
				class="btn btn-info pull-left">Cancel</a>
		</div>
	</form>
</div>