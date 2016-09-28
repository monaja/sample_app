<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/invoice.js"/>"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="box box-info" id="inv_model">
<div class="box-body">
  <div class="spacer"></div>
	<hr>
	<div class="spacer"></div>
	 <a href="<c:url value='/protected/transactions/invoices/invform'/> " class="btn btn-info pull-right">New</a>
	<h4>Invoice List</h4>
	<div class="spacer"></div>
	<table id="invtbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th width="10%">Inv. No.</th>
				<th width="10%">Inv. Date</th>
				<th width="10%">Tenant</th>
				<th width="10%">Currency</th>
				<th width="10%">WEF</th>
				<th width="10%">WET</th>
				<th width="10%">Invoice Amount</th>
				<th width="10%">Status</th>
				<th width="2%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>