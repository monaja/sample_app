<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript"
	src="<c:url value="/js/modules/entities/entities.js"/>"></script>

<div class="box"> 
                <div class="box-header">
                 <h3 class="box-title">Entities</h3>
                 
                </div>
                   <div class="box-body">  
                   
                          <div class="bodycontainer scrollable">
                            <a href="<c:url value="/protected/entities/entitydetails/"/>" class="btn btn-primary">New Entity</a>
                            <div class="spacer"></div>
                            <table id="entities" class="table table-hover table-bordered">
							<thead>
								<tr>
									
									<th>Entity ID</th>
									<th>Entity Name</th>
									<th>Entity Type</th>
									<th>Email Address</th>
									<th>Phone Number</th>
									<th>Pin Number</th>
									<th>Created on</th>
									<th>Created By</th>
									
								</tr>
							</thead>
						</table>
                          
                          </div>
                       </div>
                    </div>