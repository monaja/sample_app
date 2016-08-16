<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/ratetype.js"/>"></script>
<div class="box box-info">
  <form id="rate-form" class="form-horizontal">
  <div class="box-body">
       <div class="form-group">
             <input type="hidden" name="rateId" id="rate-code">
				<label for="brn-id" class="col-md-3 control-label">Rate Type
					</label>

				<div class="col-md-4">
					<input type="text" class="form-control" id="rate-type"
						name="rateType" required>
				</div>
			</div>
		 <div class="form-group">
				<label for="brn-id" class="col-md-3 control-label">Rate Desc
					</label>

				<div class="col-md-4">
					<textarea class="form-control" rows="3" cols="20" id="rate-desc" name="rateDesc"
					required>
					</textarea>
				</div>
			</div>
  </div>
  <div class="box-footer">
  <button type="button" class="btn btn-default" id="new-rate-type">New</button>
			<button data-loading-text="Saving..." id="save-rate-type"
				type="button" class="btn btn-primary">Save</button>
  </div>
  </form>
  <div class="spacer"></div>
	<hr>
	<table id="ratetypeList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Rate Type</th>
				<th>Rate Desc</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>