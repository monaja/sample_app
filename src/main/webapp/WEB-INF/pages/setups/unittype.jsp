<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/rentalsetups.js"/>"></script>
<div class="box box-info">
 <form id="rate-form" class="form-horizontal">
 <div class="box-body">
   <div class="form-group">
             <input type="hidden" name="unitId" id="unit-code">
				<label for="brn-id" class="col-md-3 control-label">Unit Name
					</label>

				<div class="col-md-4">
					<input type="text" class="form-control" id="unit-name"
						name="unitName" required>
				</div>
			</div>
		 <div class="form-group">
				<label for="brn-id" class="col-md-3 control-label">Unit Desc
					</label>

				<div class="col-md-4">
					<textarea class="form-control" rows="3" cols="20" id="unit-desc" name="unitDescription"
					required>
					</textarea>
				</div>
			</div>
    
 </div>
 <div class="box-footer">
    <button type="button" class="btn btn-default" id="new-btn">New</button>
			<button data-loading-text="Saving..." id="save-unit-type"
				type="button" class="btn btn-primary">Save</button>
 </div>
 </form>
 <div class="spacer"></div>
	<hr>
	<table id="unittypeList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Unit Name</th>
				<th>Unit Desc</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>