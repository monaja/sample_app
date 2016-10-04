<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript"
	src="<c:url value="/js/modules/utils/select2builder.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/libs/rivets/rivets.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/modules/transactions/receipts.js"/>"></script>

<div class="box box-info" id="acct_model">
     <div class="box-header with-border">
              <h3 class="box-title">Enter Receipt Details</h3>
            </div>
            <div class="box-footer">
            <a
				href="<c:url value='/protected/transactions/receipts/receiptList'/> " 
				class="btn btn-info pull-right" style="margin-right: 10px;">Back</a> 
            <input type="button" class="btn btn-info pull-right"
				style="margin-right: 10px;" value="Print" id="btn-add-receipt"> 
			<input type="button" data-toggle="modal" data-target="#reportsModal"
				class="btn btn-info pull-right" style="margin-right: 10px;"
				value="Save Unprinted">
            <input type="button" id="auth-btn"
				class="btn btn-info pull-right" style="margin-right: 10px;"
				value="Authorize Receipt">
			
				
				</div>
	<div class="box-body">	
	  <form id="receipt-form" class="form-horizontal">
	  
	  
	  </form>
	</div>
	</div>