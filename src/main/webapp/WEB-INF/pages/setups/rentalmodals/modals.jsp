<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="modal fade" id="unitsModal" tabindex="-1" role="dialog"
		aria-labelledby="unitsModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="unitsModalLabel">
						Edit/Add Unit
					</h4>
				</div>
				<div class="modal-body" id="branch_model">
					<form id="units-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="ren-id" name="renId">
						<input type="hidden" name="rentalStruct.rentalId" id="unit-rental-id"/>
						<div class="form-group">
							<label for="brn-id" class="col-md-3 control-label">Unit Name</label>

							<div class="col-md-8">
								<input type="text" class="form-control" id="unit-name"
									name="unitName"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Unit Type</label>

							<div class="col-md-8">
							     <input type="hidden" id="unit-type-name">
								 <input type="hidden" id="unitId" name="unitType.unitId" rv-value="rental.unit.unitId"/>
		                        <div id="unit-id" class="form-control" 
				                                 select2-url="<c:url value="/protected/rental/setups/selunitTypes"/>" >			                          
				               </div> 
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveRentalUnit"
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
	
	<div class="modal fade" id="unitRatesModal" tabindex="-1" role="dialog"
		aria-labelledby="unitsRatesModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="unitsRatesModalLabel">
						Unit Charges
					</h4>
				</div>
				<div class="modal-body">
				<button type="button" class="btn btn-info" id="btn-add-charges">New</button>
	              <div class="spacer"></div>
					<table id="unitCharges" class="table table-hover table-bordered">
					<thead>
						<tr>
			
							<th>Unit</th>
							<th>Rate Type</th>
							<th>Amount</th>
							<th>Frequency</th>
							<th>Taxable</th>
							<th>Tax Type</th>
							<th>Tax Value</th>
							<th>WEF</th>
							<th>WET</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
				 </table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">
						Close
					</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="unitsChargesModal" tabindex="-1" role="dialog"
		aria-labelledby="unitsChargesLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="unitsChargesLabel">
						Edit/Add Unit Charges
					</h4>
				</div>
				<div class="modal-body">
				    <input type="hidden" id="chargeid">
					<form id="charge-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="charge-id" name="chargeId">
						<input type="hidden" class="form-control" id="unit-id-pk" name="unit.renId">
						<div class="form-group">
							<label for="brn-id" class="col-md-3 control-label">Unit Name</label>
							<div class="col-md-8">
								<p class="form-control-static" id="charge-unit-name"></p>
							</div>
						</div>
						<div class="form-group  form-required">
							<label for="rate-type" class="col-md-3 control-label">Rate Type</label>
							<div class="col-md-8">
							     <input type="hidden" id="rate-type-name">
						        <input type="hidden" id="rateId" name="rateType.rateId" rv-value="rental.ratetype.rateId"/>
		                        <div id="ratetype" class="form-control" 
				                                 select2-url="<c:url value="/protected/rental/setups/unitratetypes"/>" >			                          
				               </div> 
							</div>
						</div>
						<div class="form-group  form-required">
							<label for="rate-amount" class="col-md-3 control-label">Amount</label>
							<div class="col-md-8">
							     <input type="number" class="form-control" id="unit-amount"
									name="amount"  required>
							</div>
						</div>
						<div class="form-group  form-required">
							<label for="sel1" class="col-md-3 control-label">Frequency</label>
							<div class="col-md-8">
							      <select class="form-control" id="sel1" name="frequency" required>
							       <option value="">Select Frequency</option>
							        <option value="One-Off">One-Off</option>
								    <option value="Monthly">Monthly</option>
								    <option value="Quarterly">Quarterly</option>
								    <option value="Semi-Annually">Semi-Annually</option>
								    <option value="Annually">Annually</option>
								  </select>
							</div>
						</div>
						<div class="form-group">
							<label for="rate-taxable" class="col-md-3 control-label">Taxable</label>
							<div class="col-md-8">
							     <div class="checkbox">
								  <label><input type="checkbox" name="taxable" id="chk-taxable"></label>
								 </div>
							</div>
						</div>
						<div class="form-group">
							<label for="sel2" class="col-md-3 control-label">Tax Type</label>
							<div class="col-md-8">
							      <select class="form-control" id="sel2" name="taxType" disabled>
							        <option value="">Select Tax Type</option>
							        <option value="VAT">VAT</option>
								    <option value="WHTX">WHTX</option>
								  </select>
							</div>
						</div>
						<div class="form-group">
							<label for="sel3" class="col-md-3 control-label">Tax Rate Type</label>
							<div class="col-md-8">
							      <select class="form-control" id="sel3" name="taxRateType" disabled>
							        <option value="">Select Rate Type</option>
							        <option value="P">Percentage</option>
								    <option value="A">Amount</option>
								  </select>
							</div>
						</div>
						<div class="form-group">
							<label for="tax-amount" class="col-md-3 control-label">Tax Amount</label>
							<div class="col-md-8">
							     <input type="number" class="form-control" id="tax-amount"
									name="taxValue" disabled  required>
							</div>
						</div>
						<div class="form-group  form-required">
							<label for="reg-wef" class="col-md-3 control-label">WEF</label>

							<div class="col-md-8">
							   <div class='input-group date datepicker-input'>
				                    <input type='text' class="form-control" name="wefDate" id="wef-date"  required/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							</div>
						</div>
						
						<div class="form-group">
							<label for="reg-wet" class="col-md-3 control-label">WET</label>

							<div class="col-md-8">
							   <div class='input-group date datepicker-input'>
				                    <input type='text' class="form-control" name="wetDate" id="wet-date" />
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveRentalFees"
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