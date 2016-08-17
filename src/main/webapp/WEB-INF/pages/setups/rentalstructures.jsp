<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/setups/rentalstructures.js"/>"></script>
<div class="box box-info">
<div class="box-body">
  <div class="spacer"></div>
	<hr>
	<div class="spacer"></div>
	 <a href="<c:url value='/protected/rental/setups/rentalform'/> " class="btn btn-info pull-right">New</a>
	<h4>Rental Structures List</h4>
	
	<div class="spacer"></div>
	<table id="rentstruct" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>House Id</th>
				<th>House Name</th>
				<th>No. of Units</th>
				<th>No. of Floors</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>