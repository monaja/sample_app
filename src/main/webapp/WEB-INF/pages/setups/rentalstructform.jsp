<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript"
	src="<c:url value="/js/modules/setups/rentalstructures.js"/>"></script>
<div class="box box-info">
	<form:form class="form-horizontal" role="form"
		modelAttribute="rentalStructure" method="post"
		action="createRentalStruct" id="rentalForm"
		enctype="multipart/form-data">
		<div class="box-body" id="rental_form_model">
		      <spring:hasBindErrors name="rentalStructure">
				      <div class="alert alert-error alert-dismissible">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						  <form:errors path="*" cssClass="help-inline"/>
						</div>
			  </spring:hasBindErrors>
		    <form:hidden path="rentalId" id="rental-struct-pk"/>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">House
						Id</label>
					<div class="col-md-7">
						<form:input path="houseId" class="form-control"
							placeholder="House Id" />
					</div>
				</div>
				<div class="col-md-6">
					<label for="houseName" class="control-label col-md-5">House
						Name</label>
					<div class="col-md-7">
						<form:input path="houseName" class="form-control"
							placeholder="House Name" />
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">No of
						Floors</label>
					<div class="col-md-7">
						<form:input path="noOfFloors" class="form-control"
							placeholder="No of Floors" />
					</div>
				</div>
				<div class="col-md-6">
					<label for="houseName" class="control-label col-md-5">No of
						Units</label>
					<div class="col-md-7">
						<form:input path="noOfUnits" class="form-control"
							placeholder="No of Units" />
					</div>
				</div>
			</div>

			<div class='spacer'></div>


			<div class="form-group">

				<div class="col-md-6 form-required">
					<label for="file" class="control-label col-md-5">House
						Image</label>
					<div class="col-md-7">
						<div class="kv-avatar center-block" style="width: 200px">
							<form:input path="file" type="file" id="avatar"
								cssClass="file-loading" />

						</div>
					</div>
				</div>

			</div>

		</div>
<div class="box-footer">
	 <input type="submit" value="Save" class="btn btn-info pull-left" style="margin-right: 10px;">
	 <a href="<c:url value='/protected/rental/setups/rentalstruct'/> " class="btn btn-info pull-left">Cancel</a>
	 </div>
	</form:form>
	<div class="box-body">
		<div class="spacer"></div>
		<h4>Units</h4>
		<div class="spacer"></div>
		<table id="rentalUnits" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Unit Name</th>
				<th>Unit Type</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
	<div class="spacer"></div>
	
	</div>
</div>