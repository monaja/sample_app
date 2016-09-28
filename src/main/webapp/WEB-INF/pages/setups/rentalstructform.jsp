<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/js/modules/setups/rentalstructures.js"/>"></script>
	<script type="text/javascript">
		var rentalId = ${rentalId};
	</script>
<div class="box box-info">
    <input type="hidden" id="brnCode" name="branchCode">
	<form id="struct-form" class="form-horizontal" enctype="multipart/form-data">
		<div class="box-body">
		    <input type="hidden" name="rentalId" id="rental-struct-pk">
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">House
						Id</label>
					<div class="col-md-7">
						<input type="text" name="houseId" id="house-id" class="form-control"
							placeholder="House Id" required>
					</div>
				</div>
				<div class="col-md-6">
					<label for="houseName" class="control-label col-md-5">House
						Name</label>
					<div class="col-md-7">
						<input type="text" name="houseName" id="house-name" class="form-control"
							placeholder="House Name" required>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="houseId" class="control-label col-md-5">No of
						Floors</label>
					<div class="col-md-7">
						<input type="number" name="noOfFloors" id="no-of-floors" class="form-control"
							placeholder="No of Floors" required/>
					</div>
				</div>
				<div class="col-md-6">
					<label for="noOfUnits" class="control-label col-md-5">No of
						Units</label>
					<div class="col-md-7">
						<input type="number" name="noOfUnits" id="no-of-units" class="form-control"
							placeholder="No of Units" required/>
					</div>
				</div>
			</div>
			<div class="form-group form-required">
				<div class="col-md-6">
					<label for="branchId" class="control-label col-md-5">Branch</label>
					<div class="col-md-7">
					      <input type="hidden" id="b-name">
						  <input type="hidden" id="obId" name="branch.obId" rv-value="rental.branch.brnCode"/>
		                        <div id="branch" class="form-control" 
				                                 select2-url="<c:url value="/protected/rental/setups/branches"/>" >			                          
				               </div> 
					</div>
				</div>
				<div class="col-md-6">
					
				</div>
			</div>

			<div class='spacer'></div>


			<div class="form-group">

				<div class="col-md-6 form-required">
					<label for="file" class="control-label col-md-5">House
						Image</label>
					<div class="col-md-7">
						<div class="kv-avatar center-block" style="width: 200px">
							<input name="file" type="file" id="avatar" class="file-loading">

						</div>
					</div>
				</div>

			</div>

		</div>
<div class="box-footer">
     
     <input type="submit" class="btn btn-info pull-left" style="margin-right: 10px;" value="Save">
	 <a href="<c:url value='/protected/rental/setups/rentalstruct'/> " class="btn btn-info pull-left">Cancel</a>
	 </div>
	</form>
	<div class="box-body with-border">
		<div class="spacer"></div>
		<h4>Units</h4>
		<button type="button" class="btn btn-info" id="btn-add-unit">New</button>
	<div class="spacer"></div>
		<table id="rentalUnits" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Unit Name</th>
				<th>Unit Type</th>
				<th>Group</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
	</table>
	<div class="spacer"></div>
	
	</div>
	
	<jsp:include page="rentalmodals/modals.jsp"></jsp:include>
	
	
</div>