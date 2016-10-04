<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/receipts.js"/>"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="box box-info" id="rct_model">
<div class="box-body">
  <div class="spacer"></div>
	<hr>
	<div class="spacer"></div>
	 <a href="<c:url value='/protected/transactions/receipts/receiptentry'/> " class="btn btn-info pull-right">New</a>
	<h4>System Receipts</h4>
	<div class="spacer"></div>
	<table id="rcts-tbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th width="10%">Receipt No.</th>
				<th width="10%">Receipt Date</th>
				<th width="10%">Receipt By</th>
				<th width="10%">Paymt Mode</th>
				<th width="10%">Amount</th>
				<th width="10%">Paid By</th>
				<th width="10%">Printed?</th>
				<th width="2%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>