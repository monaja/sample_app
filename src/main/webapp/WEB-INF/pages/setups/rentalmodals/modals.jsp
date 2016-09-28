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
								 <input type="hidden" id="unitId" name="unitType" rv-value="rental.unit.unitId"/>
		                        <div id="unit-id" class="form-control" 
				                                 select2-url="<c:url value="/protected/rental/setups/selunitTypes"/>" >			                          
				               </div> 
							</div>
						</div>
						
						<div class="form-group">
							<label for="unit-id" class="col-md-3 control-label">Group Name</label>

							<div class="col-md-8">
							     <input type="hidden" id="group-name">
								 <input type="hidden" id="chargeId" name="chargeGroup" rv-value="rental.groups.chargeId"/>
		                        <div id="charge-id" class="form-control" 
				                                 select2-url="<c:url value="/protected/setups/selRatesGroups"/>" >			                          
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
	
	
	