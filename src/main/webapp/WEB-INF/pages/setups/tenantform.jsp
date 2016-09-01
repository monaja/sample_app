<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/tenants.js"/>"></script>
<script type="text/javascript">
		var tenantId = ${tenantId};
	</script>
<div class="box box-info">
<div class="box-body">

<form id="tenant-form" class="form-horizontal">
		<div class="box-body">
		    <input type="hidden" name="tenantId" id="tenant-pk">
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">First
						Name</label>
					<div class="col-md-7">
						<input type="text" name="fname" id="first-name" class="form-control"
							placeholder="First Name" required>
					</div>
				</div>
				<div class="col-md-6">
					<label for="houseName" class="control-label col-md-5">Other
						Names</label>
					<div class="col-md-7">
						<input type="text" name="otherNames" id="other-names" class="form-control"
							placeholder="Other Names" required>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">Address</label>
					<div class="col-md-7">
						<textarea rows="2" cols="20" name="address" id="address" class="form-control" placeholder="Address"></textarea>
					</div>
				</div>
				<div class="col-md-6 form-required">
					<label for="noOfUnits" class="control-label col-md-5">ID No/Passport</label>
					<div class="col-md-7">
						<input type="text" name="idPassport" id="id-no" class="form-control"
							placeholder="ID No/Passport" required/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-md-6 form-required">
					<label for="noOfUnits" class="control-label col-md-5">Tel No</label>
					<div class="col-md-7">
						<input type="text" name="telNo" id="tel-no" class="form-control"
							placeholder="Tel No" required/>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">SMS No</label>
					<div class="col-md-7">
						<input type="text" name="smsNo" id="sms-no" class="form-control"
							placeholder="SMS No"/>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">Email Address</label>
					<div class="col-md-7">
						<input type="email" name="emailAddress" id="email-address" class="form-control"
							placeholder="Email Address" required/>
					</div>
				</div>
				<div class="col-md-6">
					
				</div>
			</div>
			
			 <div class='spacer'></div>
				        <h4>Bank Account Information</h4>
				        <hr>
				         
				        
				        <div class="form-group">
				           
				             <div class="col-md-6">
		                    <label for="logo" class="control-label col-md-5">Bank Name</label>
		                    <div class="col-md-7">
						       <input type="text" name="bankName" id="bank-name" class="form-control"
							placeholder="Bank Name"/>
						    </div>
		                 </div>
				          
				          </div>
				          
				          <div class="form-group">
				            <div class="col-md-6">
		                    <label for="logo" class="control-label col-md-5">Account Number</label>
		                    <div class="col-md-7">
						       <input type="text" name="acctNumber" id="account-number" class="form-control"
							placeholder="Account Number"/>
						    </div>
		                 </div>
		                 </div>

		</div>
<div class="box-footer">
     
     <input type="button" class="btn btn-info pull-left" style="margin-right: 10px;" value="Save and New" id="btn-save-landlord">
	 <a href="<c:url value='/protected/rental/setups/tenantList'/> " class="btn btn-info pull-left">Cancel</a>
	 </div>
	</form>
</div>
</div>