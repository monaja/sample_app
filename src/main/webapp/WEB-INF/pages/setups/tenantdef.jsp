<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/tenants.js"/>"></script>

<div class="box box-info">
<div class="box-body">
  <div class="spacer"></div>
    <div class="spacer"></div>
	 <a href="<c:url value='/protected/rental/setups/tenantForm'/> " class="btn btn-info pull-right">New</a>
	<h4>Landlords List</h4>
	<div class="spacer"></div>
	<table id="tenantDet" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>First Name</th>
				<th>Other Names</th>
				<th>Id No/Passport</th>
				<th>Tel No.</th>
				<th>Email Address</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
  
  </div>
  </div>