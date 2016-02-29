<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                          Organization Branches
                        </a>
                      </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                      <div class="panel-body">
                        <h4>Branch Details</h4>
                         <div class="spacer"></div>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#branchModal">New</button>
                        <hr>
                          <table id="orgBranches" class="table table-hover table-bordered"
							>
							<thead>
								<tr>
									
									<th>Branch ID</th>
									<th>Branch Name</th>
									<th width="5%"> </th>
									<th width="5%"> </th>
								</tr>
							</thead>
						</table>
                      </div>
                    </div>
                  </div>
                  
                  <div class="panel panel-primary">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                          Organization Banks
                           
                         
                        </a>
                      </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                      <div class="panel-body">
                        <h4>Bank Details</h4>
                          <div class="spacer"></div>
                        <button type="button" class="btn btn-info" data-toggle="modal" data-target="#bankModal">New</button>
                        <hr>
                         <table id="orgBranks" class="table table-hover table-bordered"
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Bank ID</th>
									<th>Bank Name</th>
									<th width="5%"></th>
									<th width="5%"></th>
								</tr>
							</thead>
						</table>
                       </div>
                    </div>
                  </div>
                 
                </div>
                </div>
                </div>
                
                 <jsp:include page="modals.jsp"></jsp:include>