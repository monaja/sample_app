<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
    var action = '${organization.formAction}';
</script>
<script type="text/javascript"
	src="<c:url value="/js/modules/organization/organization.js"/>"></script>

                <div class="box box-solid">
                
                <div class="box-body">
                  <div class="box-group" id="accordion">
                    <!-- we are adding the .panel class so bootstrap.js collapse plugin detects it -->
                    <div class="panel panel-primary">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                          Organization Definition
                        </a>
                      </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in">
                      <div class="panel-body">
                          <h4>Organization Details</h4>
                          
                          
                          <c:choose>
						    <c:when test="${organization.formAction=='A'}">
						        <jsp:include page="orgForm.jsp"></jsp:include>
						    </c:when>
						    <c:otherwise>
						         <jsp:include page="orgFormDetails.jsp"></jsp:include>
						    </c:otherwise>
						</c:choose>
                      </div>
                    </div>
                  </div>
                  <div class="panel panel-primary">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                          Organization Regions
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                      <div class="panel-body">
                       <ul class="nav nav-tabs">
			              <li class="active"><a href="#tab_1" data-toggle="tab">Regions</a></li>
			              
			              <li class="pull-right"><i class="fa fa-gear"></i></li>
			            </ul>
                        
                         <div class="tab-content">
                           <div class="tab-pane active" id="tab_1">
                               <h4>Region Details</h4>
		                         <div class="spacer"></div>
		                          <c:choose>
						    <c:when test="${organization.formAction=='A'}">
		                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#regModal">New</button>
		                        </c:when>
		                        </c:choose>
		                        <input type="hidden" id="selOrgReg">
		                         <hr>
		                          <table id="orgRegion" class="table table-bordered table-striped dataTable"
									>
									<thead>
										<tr>
											
											<th>Region ID</th>
											<th>Region Name</th>
											<th>WEF</th>
											<th>WET</th>
											<th width="5%"> </th>
											<th width="5%"> </th>
										</tr>
									</thead>
								</table>
								<div class="spacer"></div>
								 <h4>Branch Details</h4>
		                         <div class="spacer"></div>
		                          <c:choose>
						    <c:when test="${organization.formAction=='A'}">
		                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#branchModal">New</button>
		                        </c:when>
		                        </c:choose>
		                        <hr>
		                          <table id="orgBranches" class="table table-hover table-bordered"
									>
									<thead>
										<tr>
											
											<th>Branch ID</th>
											<th>Branch Name</th>
											<th>Address</th>
											<th>Tel. Number</th>
											<th>Branch Manager</th>
											<th width="5%"> </th>
											<th width="5%"> </th>
										</tr>
									</thead>
								</table>
                           </div>
                          
                          </div>
                      
                        
                      </div>
                    </div>
                  </div>
                  
                 
                 
                </div>
                     <button type="button" class="btn btn-info" data-toggle="modal" data-target="#reportModal">Report</button>
		                       
                </div>
                </div>
                
                 <jsp:include page="modals.jsp"></jsp:include>