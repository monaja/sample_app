 <div class="modal fade" id="productGroupModal" tabindex="-1" role="dialog"
		aria-labelledby="prgModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="prgModalLabel">
						Edit/Add Product Group
					</h4>
				</div>
				<div class="modal-body">
					<form id="prg-form" class="form-horizontal">
						<input type="hidden" class="form-control" id="prg-code" name="prgCode">
						<div class="form-group">
							<label for="bank-id" class="col-md-3 control-label">Product Group</label>
							<div class="col-md-8">
								<input type="text" class="form-control" id="prg-desc"
									name="prgDescn"  required>
							</div>
						</div>
						<div class="form-group">
							<label for="brn-name" class="col-md-3 control-label">Type</label>

							<div class="col-md-8">
								  <select  class="form-control" name="prgType" id="prg-type" required>
								    
								     <option value="MOTOR">MOTOR</option>
								     <option value="FIRE">FIRE</option>
								     <option value="TRAVEL">TRAVEL</option>
								     <option value="MEDICAL">MEDICAL</option>
								     <option value="OTHERS">OTHERS</option>
								  </select>
							</div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="savePrgBtn"
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