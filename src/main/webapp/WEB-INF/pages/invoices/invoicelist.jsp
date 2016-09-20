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

				<th>Inv. No.</th>
				<th>Inv. Date</th>
				<th>Tenant</th>
				<th>Currency</th>
				<th>WEF</th>
				<th>WET</th>
				<th>Gross Amount</th>
				<th>Taxes</th>
				<th>Net Amount</th>
				<th width="2%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>