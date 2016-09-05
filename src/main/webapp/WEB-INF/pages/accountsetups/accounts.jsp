<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript" src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/accountsetups/accounts.js"/>"></script>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="box box-info" id="acct_model">
<div class="box-body">
  <div class="spacer"></div>
	<hr>
	<form class="form-horizontal">
	   <div class="form-group">
				<label for="brn-id" class="col-md-3 control-label">Select
					Account Type</label>

				<div class="col-md-4">
		                     <input type="hidden" id="accId" name="accId" rv-value="accounts.accType.accId"/>
		                     
		                        <div id="acc-types" class="form-control" 
				                                 select2-url="<c:url value="/protected/setups/selAcctTypes"/>" >
				                                 
				               </div> 
				</div>
			</div>
	
	</form>
	<div class="spacer"></div>
	 <a href="<c:url value='/protected/setups/acctsform'/> " class="btn btn-info pull-right">New</a>
	<h4>Accounts List</h4>
	
	
	
	<div class="spacer"></div>
	<table id="acctbl" class="table table-hover table-bordered">
		<thead>
			<tr>

				<th>Account Name</th>
				<th>Id/Passport No</th>
				<th>Email</th>
				<th>Phone</th>
				<th>Date of Birth</th>
				<th>Status</th>
				<th width="5%"></th>
				<th width="5%"></th>
			</tr>
		</thead>
	</table>
</div>
</div>