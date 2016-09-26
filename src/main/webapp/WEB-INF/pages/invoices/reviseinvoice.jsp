<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/reviseinvoice.js"/>"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="box box-info">
 <div class="box-header with-border">
              <h3 class="box-title">Enter Invoice Details</h3>
            </div>
<div class="box-body">
  
  <form id="revise-form" class="form-horizontal">
      <div class="form-group form-required">
				<div class="col-md-6">
					<label for="brn-id" class="col-md-6 control-label">Select an Invoice</label>

					<div class="col-md-6">
						<div class='input-group'>
							<input type='text' class="form-control pull-right" name="inv-number"
								id="inv-number" required readonly/>
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-chevron-down" id="btn-show-invoice"></span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					

				</div>


			</div>
  
  </form>
  
	<div class="spacer"></div>
	
</div>
</div>

<div class="modal fade" id="invoiceModal" tabindex="-1" role="dialog"
		aria-labelledby="invoiceModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="invoiceModalLabel">
						Select an Invoice
					</h4>
				</div>
				<div class="modal-body">
				<div class="box-body">
				<form id="search-form" class="form-horizontal">
				 <div class="form-group">
				 	
				<div class="col-md-12">
					<label for="brn-id" class="col-md-6 control-label">Invoice Number</label>

					<div class="col-md-6">
						<input type='text' class="form-control pull-right"
								id="inv-search-number" />
					</div>
				</div>
				</div>
				<div class="form-group">
				<div class="col-md-12">
					<label for="brn-id" class="col-md-6 control-label">Tenant First Name</label>

					<div class="col-md-6">
						<input type='text' class="form-control pull-right"
								id="inv-search-name" />
					</div>
				</div>
				</div>
				<div class="form-group">
				<div class="col-md-12">
					<label for="brn-id" class="col-md-6 control-label">Tenant Other Names</label>

					<div class="col-md-6">
						<input type='text' class="form-control pull-right"
								id="inv-search-other-names" />
					</div>
				</div>
				</div>
				<div class="form-group">
				<input type="button" class="btn btn-info pull-right"
				style="margin-right: 10px;" value="Search" id="btn-search-invoice"> 
				</div>

			
			</form>
			</div>
			
					<table id="invtbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Inv. No.</th>
				<th>Inv. Date</th>
				<th>Tenant</th>
				<th>WEF</th>
				<th>WET</th>
			</tr>
		</thead>
	</table>
				</div>
				<div class="modal-footer">
					<button data-loading-text="Saving..." id="saveNewInvoice"
						type="button" class="btn btn-primary">
						OK
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						Cancel
					</button>
				</div>
			</div>
		</div>
	</div>