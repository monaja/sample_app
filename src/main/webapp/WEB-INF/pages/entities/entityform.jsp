<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <div class="box box-solid">
 
 <div class="box-body">
   <div class="box-group" id="accordion">
    <form:form class="form-horizontal" role="form"  modelAttribute="brokerEntity"
                       method="post" action="createEntity" id="entityForm">
   <div class="panel panel-primary">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                          Entity General Details
                           
                      </h4>
                      
                    </div>
                    <div id="collapseOne" >
                      <div class="panel-body">
                          <h4>Entity General Details</h4>
                         <div class="spacer"></div>
				        <input type="submit"  class="btn btn-info" value="Save" >
				        <hr>
				        <div class="form-group form-required">
				            <div class="col-md-6">
		                    <label for="orgName" class="control-label col-md-5">Entity ID</label>
		                     <div class="col-md-7">
		                     <form:input path="shtDesc" class="form-control" placeholder="Entity ID" required="required" disableed="tru"/>   
		                     </div>               
		                    </div>
		                    <div class="col-md-6">
		                    <label for="orgShtDesc" class="control-label col-md-5">Entity Name</label>
		                    <div class="col-md-7">
		                    <form:input path="names" class="form-control" placeholder="Name"/>
		                    </div>    
		                    </div>   
				        </div>
                          
                      </div>
                    </div>
                  </div>
               <div class="panel panel-primary">
                    <div class="panel-heading">
                      <h4 class="panel-title">
                          Entity Address Information
                      </h4>
                    </div>
                    <div id="collapseOne" >
                      <div class="panel-body">
                          <h4>Address Information</h4>
                          
                          
                      </div>
                    </div>
                  </div>
                  </form:form>
   </div>
 <a href="<c:url value="/protected/entities/"/>" class="btn btn-primary">Back to Entities</a>
                            
 </div>
 
 </div>