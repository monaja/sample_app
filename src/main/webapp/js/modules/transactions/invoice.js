$(function(){

	$(document).ready(function() {
		createInvoices();
		$(".datepicker-input").each(function() {
		    $(this).datetimepicker({
                format: 'DD/MM/YYYY'
            });
		    
		});
		changeWefDate();
		tenantLov();
		branchLov();
		currencyLov();
		paymentModesLov();
		createInvoice();
		queryInvoiceDetails();
		addNewCharge();
		saveNewCharges();
		authorizeInvoice();
		
		
	});
});

function authorizeInvoice(){
	$("#auth-btn").on('click', function(){
		bootbox.confirm("Are you sure want to authorize invoice", function(result) {
			 if(result){
					$.ajax({
				        type: 'GET',
				        url:  'confirmInvoice',
				        dataType: 'json',
				        data: {"invoiceCode": $("#invoice-pk").val()},
				        async: true,
				        success: function(result) {
				        	bootbox.alert("Invoice Authorized Successfully...");
				        	queryInvoiceDetails();
				        },
				        error: function(jqXHR, textStatus, errorThrown) {
				        	console.log(jqXHR);
				        	bootbox.alert(jqXHR.responseText);
				        }
				    });
				 
			 }
	});
	
});
}

function addNewCharge(){
	$("#btn-add-charge").on('click', function(){
		if($("#ten-id").val()!=''){
		getNewChargeUnit($("#ten-id").val());
		$('#chargesModal').modal('show');
		}
	})
	
}

function saveNewCharges(){
	$("#saveNewInvoice").on('click', function(){
		saveInvoiceDetails();
		$('#chargesModal').modal('hide');
	});
}


function queryInvoiceDetails(){
	if(typeof invoiceCode!== 'undefined'){
		if(invoiceCode!==-2000){
			$("#invoice-pk").val(invoiceCode);
			$("#tenant-label-name").show();
			$("#tenant-info").hide();
			$.ajax( {
			      url: 'invoice/'+invoiceCode,
			      type: 'GET',
			      processData: false,
			      contentType: false,
			      success: function (s ) {
			    	  $("#inv-number").val(s.invoiceNumber);
                	  $("#invoice-pk").val(s.invoiceId);
                	  $("#tot-inv-amt").text(s.invAmount);
                	  $("#tot-tax-amt").text(s.taxAmount);
                	  $("#tot-net-amt").text(s.netAmount);
                	  $("#tenant-label-name").show();
                	  $("#tenant-label-name").text(s.tenantName);
                	  $("#tenant-info").hide();
                	  $("#inv-date").val(s.transDate);
                	  $("#pay-freq").val(s.frequency);
                	  $("#from-date").val(s.wefDate);
                	  $("#wet-date").val(s.wetDate);
                	  $("#inv-status").text((s.status == "A" ? "Authorized" : "Draft"));
                	  $("#cur-name").val(s.currency.curName);
                	  $("#cur-id").val(s.currency.curCode);
                	  $('#ten-id').val(s.tenantId);
                	  $("#brn-id").val(s.branch.obId);
                	  $("#trans-brn-name").val(s.branch.obName);
                	  $('#pymt-id').val(s.paymentMode.pmId);
                	  $("#pymt-desc").val(s.paymentMode.pmDesc);
                	  $("#inv-ren-date").text(s.renewalDate);
                	 
                	  if(s.status === "A"){
                		    $("#inv-date").prop("disabled", true);
	  			        	$("#pay-freq").prop("disabled", true);
	  			        	$("#from-date").prop("disabled", true);
	  			        	$("#wet-date").prop("disabled", true);
	  			        	$("#rates-div").hide();
	  			        	 $("#branch-label-name").show();
	  	          			  $("#branch-info").hide();
	  	          			$("#currency-label-name").show();
	  	        			$("#curr-info-panel").hide();
	  	          			 $("#branch-label-name").text(s.branch.obName);
	  	          			$("#currency-label-name").text(s.currency.curName);
	  	          		$("#paymode-label-name").show();
  	        			$("#pay-mode-info").hide();
  	          			 $("#paymode-label-name").text(s.paymentMode.pmDesc);
  	          			 $("#btn-add-invoice").hide();
  	          			$("#auth-btn").hide();
  	          			$("#rates-details-div").show();
  	          		    getInvDetails();
                	  }
                	  else{
                		  $("#inv-date").removeAttr('disabled');
                		  $("#pay-freq").removeAttr('disabled');
                		  $("#from-date").removeAttr('disabled');
                		  $("#wet-date").removeAttr('disabled');
                		  $("#rates-div").show();
                		  $("#branch-label-name").hide();
              			  $("#branch-info").show();
              			$("#currency-label-name").hide();
            			$("#curr-info-panel").show();
            			$("#paymode-label-name").hide();
  	        			$("#pay-mode-info").show();
  	        			 $("#btn-add-invoice").show();
  	        			$("#auth-btn").show();
  	        			$("#rates-details-div").hide();
                	  }
                	 
                	  currencyLov();
                	  branchLov();
                	  paymentModesLov();
                	  if(s.tenantId)
                	  getUnitDetails(s.tenantId);
                	  getInvoiceCharges(s.invoiceDetails);
			    	  
			      },
			      error: function(xhr, error){
			    	  bootbox.alert(xhr.responseText);
			      }
			      });
		}
		else{
			$("#tenant-label-name").hide();
			$("#tenant-info").show();
			$("#branch-label-name").hide();
			$("#branch-info").show();
			$("#currency-label-name").hide();
			$("#curr-info-panel").show();
			$("#paymode-label-name").hide();
  			$("#pay-mode-info").show();
			$("#rates-div").show();
			$("#btn-add-invoice").show();
			$("#auth-btn").show();
			$("#rates-details-div").hide();
		}
	}
}


function saveInvoiceDetails(){
	var $currForm = $('#invoice-form');
	var currValidator = $currForm.validate();
	if (!$currForm.valid()) {
		return;
	}
	
	var data = {};
	$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
	var url = "createInvoice";
	var arr = getInvoiceDetails();
	data.details = arr;
    $.ajax(
            {
              url:url, 
              type: "POST", 
              data: JSON.stringify(data), 
              success: function(s){
            	  bootbox.alert("Record created/updated Successfully");
            	  $("#inv-number").val(s.invoiceNumber);
            	  $("#invoice-pk").val(s.invoiceId);
            	  $("#tot-inv-amt").text(s.invAmount);
            	  $("#tot-tax-amt").text(s.taxAmount);
            	  $("#tot-net-amt").text(s.netAmount);
            	  $("#tenant-label-name").show();
            	  $("#tenant-label-name").text(s.tenantName);
            	  $("#tenant-info").hide();
            	  getInvoiceCharges(s.invoiceDetails);
            	  $("#inv-ren-date").text(s.renewalDate);
            	  currValidator.resetForm();
              }, 
              error: function(jqXHR, textStatus, errorThrown){
            	  bootbox.alert(jqXHR.responseText);
              },
              dataType: "json",
              contentType: "application/json"
            } );  

}

function createInvoice(){
	
	$('#btn-add-invoice').click(function(){
		saveInvoiceDetails();
	});
}


var model = {
		invoice: {
			tenant:{
				tenId:"",
			},
			branch:{
				brnCode:"",
			},
			currency:{
				curCode:"",
			},
			paymentmodes:{
				pmId:""
			}
	    }
	};


function getInvoiceDetails(){
	var arr = [];
	var counter = 0;
	$("[id^=invoice-rates-form]").each(function(f){
		var $form = $(this);
		var data = {};
		$form.serializeArray().map(function(x){data[x.name] = x.value;});
		arr.push(data);
	});
	$("[id^=inv-new-rates-form]").each(function(f){
		var $form = $(this);
		var data = {};
		$form.serializeArray().map(function(x){data[x.name] = x.value;});
		arr.push(data);
	});
	return arr;
}

function getInvoiceCharges(charges){
	$('[id^=invoice-rates-form]').remove();
	for(var i = 0; i < charges.length; i++) {
	    var obj = charges[i];
	   var data=' <form id="invoice-rates-form'+i+'" class="form-horizontal"><div class="form-group"> '+
		'<div class="col-md-6"> '+
		'<input type="hidden" name="chargeId" value = "'+obj.chargeId+'"> '+
		'<input type="hidden" name="detailId" id="'+obj.detailId+'"> '+
		'<input type="hidden" name="rateTyoeId" value = "'+obj.rateTypeId+'"> '+
        ' <label for="brn-id" class="col-md-6 control-label">'+obj.rateType+'</label>  '+
       '  <div class="input-group col-md-6">  '+
           ' <input type="text" name="amount" class="form-control" value="'+obj.amount+'" required>   '+
       '  </div>  '+
   '  </div>  '+
    ' <div class="col-md-6">  '+
    ' </div> '+
	' </div></form> ';
	   $("#rates-div").append(data);
	};
	$("[id^=invoice-rates-form]").find("input[type=text]").number( true, 2 );
}


function getActiveCharges(unitId,invoiceDate){
	$.ajax({
        type: 'GET',
        url:  'getActiveCharges',
        dataType: 'json',
        data: {"unitCode": unitId,"invoiceDate":invoiceDate},
        async: true,
        success: function(result) {
            
        	$('[id^=invoice-rates-form]').remove();
        	for(var i = 0; i < result.length; i++) {
        	    var obj = result[i];
        	   var data=' <form id="invoice-rates-form'+i+'" class="form-horizontal"><div class="form-group"> '+
    			'<div class="col-md-6"> '+
    			'<input type="hidden" name="chargeId" value = "'+obj.chargeId+'"> '+
    			'<input type="hidden" name="detailId" id="detail-id-pk'+i+'"> '+
    			'<input type="hidden" name="rateTyoeId" value = "'+obj.rateType.rateId+'"> '+
                ' <label for="brn-id" class="col-md-6 control-label">'+obj.rateType.rateType+'</label>  '+
               '  <div class="input-group col-md-6">  '+
                   ' <input type="text" name="amount" class="form-control" value="'+obj.amount+'" required>   '+
               '  </div>  '+
           '  </div>  '+
            ' <div class="col-md-6">  '+
            ' </div> '+
    		' </div></form> ';
        	   $("#rates-div").append(data);
        	};
        	
        	$("[id^=invoice-rates-form]").find("input[type=text]").number( true, 2 );
        	
        	
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	
        }
    });
	
}

function getNewChargeUnit(id){
	 $.ajax({
	        type: 'GET',
	        url:  'getAllocation/' + id,
	        dataType: 'json',
	        async: true,
	        success: function(result) {
	        	if($("#inv-date").val()!=''){
	        		getNewCharges(result.renunits.renId,$("#inv-date").val(),$("#invoice-pk").val());	
	        	}
	        	
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
            bootbox.alert(jqXHR.responseText);
	        }
	    });
	
}



function getNewCharges(unitId,invoiceDate){
	$.ajax({
        type: 'GET',
        url:  'getNewCharges',
        dataType: 'json',
        data: {"unitCode": unitId,"invoiceDate":invoiceDate},
        async: true,
        success: function(result) {
            
        	$('[id^=inv-new-rates-form]').remove();
        	for(var i = 0; i < result.length; i++) {
        	    var obj = result[i];
        	   var data=' <form id="inv-new-rates-form'+i+'" class="form-horizontal"><div class="form-group"> '+
    			'<div class="col-md-6"> '+
    			'<input type="hidden" name="chargeId" value = "'+obj.chargeId+'"> '+
    			'<input type="hidden" name="detailId" id="detail-new-id-pk'+i+'"> '+
    			'<input type="hidden" name="rateTyoeId" value = "'+obj.rateType.rateId+'"> '+
                ' <label for="brn-id" class="col-md-6 control-label">'+obj.rateType.rateType+'</label>  '+
               '  <div class="input-group col-md-6">  '+
                   ' <input type="text" name="amount" class="form-control" value="'+obj.amount+'" required>   '+
               '  </div>  '+
           '  </div>  '+
            ' <div class="col-md-6">  '+
            ' </div> '+
    		' </div></form> ';
        	   $("#rates-new-div").append(data);
        	};
        	
        	
        	$("[id^=inv-new-rates-form]").find("input[type=text]").number( true, 2 );
        	
        	
        	
        },
        error: function(jqXHR, textStatus, errorThrown) {
        	
        }
    });
	
}

function currencyFormat (num) {
	if(num)
    return  num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
    else
    	return null;
}


function getTenantUnitDetails(id){
	 $.ajax({
	        type: 'GET',
	        url:  'getAllocation/' + id,
	        dataType: 'json',
	        async: true,
	        success: function(result) {
	        	$("#unit-number").val(result.renunits.unitName+"   "+result.structure.houseName);
	        	if($("#inv-date").val()!=''){
	        		getActiveCharges(result.renunits.renId,$("#inv-date").val());	
	        	}
	        	
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
             bootbox.alert(jqXHR.responseText);
	        }
	    });
	
}


function getUnitDetails(id){
	 $.ajax({
	        type: 'GET',
	        url:  'getAllocation/' + id,
	        dataType: 'json',
	        async: true,
	        success: function(result) {
	        	$("#unit-number").val(result.renunits.unitName+"   "+result.structure.houseName);	
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
            bootbox.alert(jqXHR.responseText);
	        }
	    });
	
}



function tenantLov(){
	if($("#tenant-div").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "tenant-div",
	            sort : 'tenantNumber',
	            change:  function(e, a, v){
	            	$("#ten-id").val(e.added.tenId);
	            	var id = e.added.tenId;
	            	getTenantUnitDetails(id);
	            },
	            formatResult : function(a)
	            {
	            	return a.tenantNumber+" "+a.fname+" "+a.otherNames
	            },
	            formatSelection : function(a)
	            {
	            	return a.tenantNumber+" "+a.fname+" "+a.otherNames
	            },
	            initSelection: function (element, callback) {
	            	
                },
	            id: "tenId",
	            width:"220px"
	        });
	  }
}

function paymentModesLov(){
	if($("#div-paymodes").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "div-paymodes",
	            sort : 'pmDesc',
	            change:  function(e, a, v){
	            	$("#pymt-id").val(e.added.pmId);
	            },
	            formatResult : function(a)
	            {
	            	return a.pmDesc
	            },
	            formatSelection : function(a)
	            {
	            	return a.pmDesc
	            },
	            initSelection: function (element, callback) {
	            	var code = $("#pymt-id").val();
	                  var pname = $("#pymt-desc").val();
		              model.invoice.paymentmodes.pmId = code;
		              var data = {pmDesc:pname,pmId:code};
	                  callback(data);
                },
	            id: "pmId",
	            width:"220px"
	        });
	  }
}

function currencyLov(){
	if($("#curr-div").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "curr-div",
	            sort : 'curName',
	            change:  function(e, a, v){
	            	$("#cur-id").val(e.added.curCode);
	            },
	            formatResult : function(a)
	            {
	            	return a.curName
	            },
	            formatSelection : function(a)
	            {
	            	return a.curName
	            },
	            initSelection: function (element, callback) {
	            	  var currCode = $("#cur-id").val();
	                  var currName = $("#cur-name").val();
		              model.invoice.currency.curCode = currCode;
		              var data = {curName:currName,curCode:currCode};
	                  callback(data);
                },
	            id: "curCode",
	            width:"220px"
	        });
	  }
}

function branchLov(){
	if($("#brn-div").filter("div").html() != undefined)
	  {
		  Select2Builder.initAjaxSelect2({
	            containerId : "brn-div",
	            sort : 'obName',
	            change:  function(e, a, v){
	            	$("#brn-id").val(e.added.obId);
	            },
	            formatResult : function(a)
	            {
	            	return a.obName
	            },
	            formatSelection : function(a)
	            {
	            	return a.obName
	            },
	            initSelection: function (element, callback) {
	            	var code = $("#brn-id").val();
	                var name = $("#trans-brn-name").val();
	                model.invoice.branch.brnCode = code;
	                var data = {obName:name,obId:code};
	                callback(data);
                },
	            id: "obId",
	            width:"220px"
	        });
	  }
}



function changeWefDate(){
	console.log("Passed here..");
	$('#wef-date').on('dp.change', function (ev) {
		var curDate = ev.date;
		console.log("here..");
		if($("#pay-freq").val()!=''){
			if($("#pay-freq").val()==='M'){ 
			  $("#wet-date").val(moment(moment(curDate).add(1, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#pay-freq").val()==='Q'){ 
			  $("#wet-date").val(moment(moment(curDate).add(3, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#pay-freq").val()==='S'){ 
				  $("#wet-date").val(moment(moment(curDate).add(6, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#pay-freq").val()==='A'){ 
				  $("#wet-date").val(moment(moment(curDate).add(12, 'month')).format('DD/MM/YYYY'));
			}
		}
	});
	
	 $("#inv-date-grp").on("dp.change", function (ev) {
		var curDate = ev.date;
		if($("#ten-id").val()!='')
		getTenantUnitDetails($("#ten-id").val());
		
	});
	
	$("#pay-freq").change(function(){
		if($("#from-date").val()!=''){
			var curDate = $("#from-date").val();
			if($("#pay-freq").val()==='M'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(1, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#pay-freq").val()==='Q'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(3, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#pay-freq").val()==='S'){ 
					  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(6, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#pay-freq").val()==='A'){ 
					  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(12, 'month')).format('DD/MM/YYYY'));
				}
		}
	})
}


function createInvoices(){
	var url = "invoices";
	  var currTable = $('#invtbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			autoWidth: true,
			lengthMenu: [ [20,30,40,50], [20,30,40,50] ],
			pageLength: 20,
			destroy: true,
			"columns": [
				{ "data": "invoiceNumber" },
				{ "data": "invoiceDate"},
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.tenant.fname + " "+full.tenant.otherNames;
						  }
				},
				{ "data": "invoiceId",
					   "render": function ( data, type, full, meta ) {
						      return full.transCurrency.curName;
						  }
				},
				{ "data": "wefDate"},
				{ "data": "wetDate"},
				{ "data": "invAmount", 
					 "render":function(data,type,full,meta){
						  return currencyFormat(full.invAmount);
					  }
				},
				{ "data": "taxAmount" , 
					 "render":function(data,type,full,meta){
						  return currencyFormat(full.taxAmount);
					  }
				},
				{ "data": "netAmount" , 
					 "render":function(data,type,full,meta){
						  return currencyFormat(full.netAmount);
					  }
				},
				{ "data": "status" , 
					 "render":function(data,type,full,meta){
						  return (full.status) == "A" ? "Authorized" : "Draft";
					  }
				},
				{ 
					"data": "invoiceId",
					"render": function ( data, type, full, meta ) {
						if(full.status==="A"){
							return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.invoiceId+'><input type="submit"  class="btn btn-primary" value="View" ></form>';
							
						}else
						return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='+full.invoiceId+'><input type="submit"  class="btn btn-primary" value="Edit" ></form>';
						
					 }

				},
			]
		} );
	  return currTable;
}

function getInvDetails(){
	var url = "getInvDetails/0";
	if(typeof invoiceCode!== 'undefined'){
		url = "getInvDetails/"+invoiceCode;
	}
	  var table = $('#inv-detail-tbl').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "detailId",
				  "render":function(data,type,full,meta){
					  return full.rateType.rateType;
				  } 
				  
				},
				{ "data": "amount",
					  "render":function(data,type,full,meta){
						  return currencyFormat(full.amount);
					  }
				},
				{ "data": "amount",
					  "render":function(data,type,full,meta){
						  var amt = full.amount-full.netAmount;
						  return currencyFormat(amt);
					  }
				},
				{ "data": "netAmount" ,
				  "render":function(data,type,full,meta){
					  return currencyFormat(full.netAmount);
				  }
				},
			]
		} );
	  return table;
}
		
		
