<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var requestContextPath = '${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value="/js/modules/setups/setups.js"/>"></script>
<div class="box box-info">
<div class="box-body">
	<h4>Country Details</h4>
	<hr>
	
	<button type="button" class="btn btn-info" data-toggle="modal"
		data-target="#countryModal">New</button>
	<div class="spacer"></div>
	<table id="countryList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th width="5%">Country Code</th>
				<th width="5%">Country Name</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
	<h4>County Details</h4>
	<hr>
	
	<button type="button" class="btn btn-info" id="btn-add-county">New</button>
	<div class="spacer"></div>
	<table id="countyList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th width="30%">County Code</th>
				<th width="30%">County Name</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
	<h4>Town Details</h4>
	<hr>
	
	<button type="button" class="btn btn-info" id="btn-add-town">New</button>
	<div class="spacer"></div>
	<table id="townList" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th width="20%">Town Code</th>
				<th width="20%">Town Name</th>
				<th width="20%">Postal Code</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>
<jsp:include page="countriesmodals/modals.jsp"></jsp:include>