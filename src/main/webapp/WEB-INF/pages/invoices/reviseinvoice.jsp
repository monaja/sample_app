<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/reviseinvoice.js"/>"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="box box-info" id="inv_model">
<div class="box-body">
  <div class="spacer"></div>
	<hr>
	<div class="spacer"></div>
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
				<th>Net</th>
				<th>Status</th>
			</tr>
		</thead>
	</table>
</div>
</div>