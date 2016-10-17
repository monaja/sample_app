<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<form:form class="form-horizontal" role="form"  modelAttribute="organization"
                       method="post" action="createOrganization" id="orgForm"
                       enctype="multipart/form-data">
                   <div class="box-body">
				          <div class="spacer"></div>
				        <input type="submit"  class="btn btn-info" value="Save" >
				        <hr>
				      <spring:hasBindErrors name="organization">
				      <div class="alert alert-error alert-dismissible">
						  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
						  <form:errors path="*" cssClass="help-inline"/>
						</div>
						</spring:hasBindErrors>
				          
				        <div class="form-group form-required">
				            <div class="col-md-6">
		                    <label for="orgName" class="control-label col-md-5">Company Name</label>
		                     <div class="col-md-7">
		                     <form:input path="orgName" class="form-control" placeholder="Org. Sht Desc"/>   
		                     </div>               
		                    </div>
		                    <div class="col-md-6">
		                    <label for="orgShtDesc" class="control-label col-md-5">Alias</label>
		                    <div class="col-md-7">
		                    <form:input path="orgShtDesc" class="form-control" placeholder="Description"/>
		                    </div>    
		                    </div>   
				        </div>
				     
				        <div class="form-group">
				          <div class="col-md-6">
		                    <label for="orgMobile" class="control-label col-md-5">Mobile</label>
		                     <div class="col-md-7">
		                     <form:input path="orgMobile" class="form-control" placeholder="Mobile Number"/>                  
		                 </div>
		                 </div>
		                 <div class="col-md-6">
		                  <label for="orgPhone" class="control-label col-xs-5">Phone</label>
		                    <div class="col-md-7">
		                     <form:input path="orgPhone" class="form-control" placeholder="Tel. Number"/>                  
		                 </div>
		                 </div>
				        </div>
				       
				        
				          <div class="form-group">
				           <div class="col-md-6">
		                    <label for="orgFax" class="control-label col-md-5">Fax Number</label>
		                    <div class="col-md-7">
		                     <form:input path="orgFax" class="form-control" placeholder="Fax Number"/>                  
		                 </div>
		                 </div>
		                  <div class="col-md-6">
		                 <label for="orgWebsite" class="control-label col-md-5">WebSite</label>
		                    <div class="col-md-7">
		                     <form:input path="orgWebsite" class="form-control" placeholder="Website"/>                  
		                 </div>
		                 </div>
				        </div>
				        
				        <div class="form-group">
				           <div class="col-md-6">
		                    <label for="orgDesc" class="control-label col-md-5">Date of Incorporation</label>
		                    <div class="col-md-7">
		                     <div class='input-group date datepicker-input'>
		                     <form:input path="dateIncorp"  class="form-control"/>    
		                     <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>    
				                </div>          
		                 </div>
		                 </div>
		                 <div class="col-md-6">
		                   <label for="certNumber" class="control-label col-md-5">Certificate No.</label>
		                    <div class="col-md-7">
		                     <form:input path="certNumber" class="form-control" placeholder="Cert Number"/>                  
		                 </div>
		                 </div>
		                 </div>
		                 <div class="form-group form-required">
				          
				           <div class="col-md-6">
		                 <label for="currencyName" class="control-label col-md-5">Currency</label>
		                    <div class="col-md-7">
		                     <div class="input-group">
		                     <form:input type="text" path="currency.curName" name="currencyName" class="form-control" id="txtCurrency" cssStyle="display:none;" disabled="true"/>
		                     <form:input type="text" path="currency.curCode" name="curCode" class="form-control" id="txtCurrencyCode" cssStyle="display:none;" disabled="true"/>
		                     <form:hidden path="currency.curCode" id="curCode" rv-value="organization.currency.curCode"/>
		                        <div id="currency" class="form-control" 
				                                 select2-url="<c:url value="/protected/organization/currencies"/>" >
				                                 
				                                 </div>
		                     </div>                  
		                 </div>
		                 </div>
				           
		                
				        </div>
				        <div class='spacer'></div>
				        <h4>Bank Account Information</h4>
				        
				         
				        
				        <div class="form-group">
				           
				             <div class="col-md-6">
		                    <label for="logo" class="control-label col-md-5">Bank Name</label>
		                    <div class="col-md-7">
						       <form:input path="bankName" class="form-control" />
						    </div>
		                 </div>
				          
				          </div>
				          
				          <div class="form-group">
				            <div class="col-md-6">
		                    <label for="logo" class="control-label col-md-5">Account Number</label>
		                    <div class="col-md-7">
						       <form:input path="accountNumber" class="form-control" />
						    </div>
		                 </div>
		                 </div>
				         <div class='spacer'></div>
				        <h4>Address Information</h4>
				        
				        <div class="form-group">
				           <div class="col-md-6 form-required">
		                    <label for="zipCode" class="control-label col-md-5">Address</label>
		                    <div class="col-md-7">
		                     <form:input path="address.addAddress" class="form-control" placeholder="Address"/>                  
		                 </div>
		                 </div>
		                 
				        
				        <div class="col-md-6 form-required">
		                 <label for="countryName" class="control-label col-md-5">Country</label>
		                    <div class="col-md-7">
		                     <div class="form-group">
		                      <form:input  path="address.country.couName" name="countryName" class="form-control" id="countryName" cssStyle="display:none;" disabled="true"/> 
		                      <form:input  path="address.country.couCode" name="couCode" class="form-control" id="countryCode" cssStyle="display:none;" disabled="true"/> 
		                     <form:hidden path="address.country.couCode" rv-value="organization.country.couCode"/>
		                      <div class="col-md-7">
				                            <div id="country" class="form-control" 
				                                 select2-url="<c:url value="/protected/organization/countries"/>" >
				                                 
				                                 </div>
				               </div >
		                     </div>                  
		                 </div>
		                 </div>
				        </div>
				        
				        <div class="form-group">
				          
				           <div class="col-md-6 form-required">
		                 <label for="countyName" class="control-label col-md-5">County</label>
		                    <div class="col-md-7">
		                     <div class="form-group">
		                      <form:input path="address.county.countyName" name="countyName" class="form-control" id="txtcounty" cssStyle="display:none;" disabled="true"/> 
		                      <form:input path="address.county.countyId" name="countyId" class="form-control" id="txtcountyId" cssStyle="display:none;" disabled="true"/>
		                     <form:hidden path="address.county.countyId" rv-value="organization.country.county.countyId"/>
		                      <div class="col-md-7">
				                            <div id="county" class="form-control" 
				                                 select2-url="<c:url value="/protected/organization/counties/"/>"></div>
				               </div >
		                     </div>                  
		                 </div>
		                 </div>
				           
		                  <div class="col-md-6 form-required">
		                 <label for="cityName" class="control-label col-md-5">Town</label>
		                    <div class="col-md-7">
		                     <div class="input-group">
		                     <form:input path="address.town.ctName" name="cityName" class="form-control" id="txtCity" cssStyle="display:none;" disabled="true"/>
		                      <form:input path="address.town.ctCode" name="ctCode" class="form-control" id="txtCityCode" cssStyle="display:none;" disabled="true"/>
		                     <form:hidden path="address.town.ctCode" id="ctCode" rv-value="organization.country.county.town.ctCode"/>
		                      <div id="town" class="form-control" 
				                                 select2-url="<c:url value="/protected/organization/towns"/>"></div>
		                     </div>                  
		                 </div>
		                 </div>
				        </div>
				        
				        <div class="form-group">
				           
				             <div class="col-md-6 form-required">
		                    <label for="zipCode" class="control-label col-md-5">Zip Code</label>
		                    <div class="col-md-7">
		                     <form:input path="address.addZipCode" id="zipCode" class="form-control" placeholder="Zip Code"/>                  
		                 </div>
		                 </div>
		                 <div class="col-md-6 form-required">
		                    <label for="zipCode" class="control-label col-md-5">Email Address</label>
		                    <div class="col-md-7">
		                     <form:input path="address.emailAddress" id="emailAddress" class="form-control" placeholder="Email Address"/>                  
		                 </div>
		                 </div>
				          
				          </div>
				        
				         <div class='spacer'></div>
				        <h4>Company Logo</h4>
				        
				         
				        
				        <div class="form-group">
				           
				             <div class="col-md-6">
		                    <label for="logo" class="control-label col-md-5">Logo</label>
		                    <div class="col-md-7">
		                     <div class="kv-avatar center-block" style="width:200px">
						       <form:input path="file" type="file" id="avatar" cssClass="file-loading"/>
						       
						    </div>
						    </div>
		                 </div>
				          
				          </div>
				        
				      </div> 
				      <form:hidden path="formAction" />
				      <form:hidden path="orgCode" id="orgCodepk"/>  
                      </form:form>
                      