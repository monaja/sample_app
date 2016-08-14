<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
	src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<div class="box box-info">
	<form id="currform" class="form-horizontal">
		<div class="box-body">
		    <input type="hidden" name="curCode" id="cur-code">
			<div class="form-group">
				<label for="brn-id" class="col-md-3 control-label">Currency
					Name</label>

				<div class="col-md-4">
					<input type="text" class="form-control" id="curr-name"
						name="curName" required>
				</div>
			</div>
			<div class="form-group">
				<label for="enabled" class="col-md-3 control-label">
					Enabled</label>

				<div class="col-md-4">
					<input type="checkbox" id="enabled" name="enabled">
				</div>
			</div>
			<div class="form-group">
				<label for="fraction" class="col-md-3 control-label">Fraction</label>

				<div class="col-md-4">
					<input type="text" class="editUserCntrls form-control"
						id="fraction" name="fraction" required>
				</div>
			</div>
			<div class="form-group">
				<label for="frac-units" class="col-md-3 control-label">Fraction
					Units</label>

				<div class="col-md-4">
					<input type="number" class="editUserCntrls form-control"
						id="frac-units" name="fractionUnits">
				</div>
			</div>

			<div class="form-group">
				<label for="curr-symbol" class="col-md-3 control-label">Symbol</label>

				<div class="col-md-4">
					<input type="text" class="editUserCntrls form-control"
						id="curr-symbol" name="curIsoCode" required>
				</div>
			</div>

		</div>
		<div class="box-footer">
		<button type="button" class="btn btn-default" id="newCurrency">New</button>
			<button data-loading-text="Saving..." id="saveCurrencyBtn"
				type="button" class="btn btn-primary">Save</button>
			
		</div>

	</form>
	<div class="spacer"></div>
	<hr>
	<table id="currencyList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Symbol</th>
				<th>Currency Name</th>
				<th>Enabled</th>
				<th>Fraction</th>
				<th>Fraction Units</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>
