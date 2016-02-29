<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="<c:url value="/js/modules/products/product.js"/>"></script>
               <div class="col-md-3 col-sm-6">
               <div class="box"> 
                <div class="box-header">
                 <h3 class="box-title">Product Groups</h3>
                 
                </div>
                   <div class="box-body">  
                   
                          <div class="bodycontainer scrollable" style="max-height: 600px; width: 100%;">
                           <button type="button" class="btn btn-info" data-toggle="modal" data-target="#productGroupModal">New</button>
                            <input type="hidden" id="prg-key">
                            <table id="productGroup" class="table table-hover table-bordered"
							>
							<thead>
								<tr>
									
									<th>Product Group</th>
								</tr>
							</thead>
						</table>
                          
                          </div>
                       </div>
                    </div>
                  </div>
              <div class="col-md-9 col-sm-6">
                <div class="box"> 
                <div class="box-header">
                 <h3 class="box-title">Products</h3>
                </div>
                
                   <div class="box-body">  
                          <div class="bodycontainer scrollable" style="max-height: 600px">
                           <button type="button" class="btn btn-info" data-toggle="modal" data-target="#productGroupModal">New</button>
                              <table id="products" class="table table-hover table-bordered"
							  width="100%">
							<thead>
								<tr>
									<th>Product Id</th>
									<th width="20%">Product Desc</th>
									<th>Claim Prefix</th>
									<th>Policy Prefix</th>
									<th>Renewable?</th>
									<th>Same Day Ren</th>
									<th>Min Prem</th>
									<th>WEF</th>
									<th>WET</th>
									<th></th>
								</tr>
							</thead>
						</table>
                          </div>
                       </div>
                    </div>
              </div> 
              
              
              <jsp:include page="modals.jsp"></jsp:include>
              