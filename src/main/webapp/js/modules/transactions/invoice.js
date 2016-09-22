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
		
	});
});

function createInvoice(){
	var $currForm = $('#invoice-form');
	var currValidator = $currForm.validate();
	$('#btn-add-invoice').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createInvoice";
        var request = $.post(url, data );
        request.success(function(s){
        	$("#inv-number").val(s.invoiceNumber);
        	$("#invoice-pk").val(s.invoiceId);
        	$('[id^=ten-id-pk]').val(s.invoiceId);
        	bootbox.alert("Record created/updated Successfully");
			currValidator.resetForm();
			saveInvoiceDetails();
        });
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
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


function saveInvoiceDetails(){
	$("[id^=invoice-rates-form]").each(function(f){
		var $form = $(this);
		var data = {};
		$form.serializeArray().map(function(x){data[x.name] = x.value;});
		console.log(data);
		var url = "createInvoiceDetails";
        var request = $.post(url, data );
        request.success(function(s){
        	$form.find("[id^=detail-id-pk]").val(s.detailId);
        });
	});
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
        	    console.log(obj);
        	
        	    
        	   var data=' <form id="invoice-rates-form'+i+'" class="form-horizontal"><div class="form-group"> '+
    			'<div class="col-md-6"> '+
    			'<input type="hidden" name="charge" value = "'+obj.chargeId+'"> '+
    			'<input type="hidden" name="invoice" id="ten-id-pk'+i+'"> '+
    			'<input type="hidden" name="detailId" id="detail-id-pk'+i+'"> '+
    			'<input type="hidden" name="rateType" value = "'+obj.rateType.rateId+'"> '+
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
	            	
                },
	            id: "obId",
	            width:"220px"
	        });
	  }
}


function changeWefDate(){
	$('#wef-date').on('dp.change', function (ev) {
		var curDate = ev.date;
		if($("#sel2").val()!=''){
			if($("#sel2").val()==='M'){ 
			  $("#wet-date").val(moment(moment(curDate).add(1, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='Q'){ 
			  $("#wet-date").val(moment(moment(curDate).add(3, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='S'){ 
				  $("#wet-date").val(moment(moment(curDate).add(6, 'month')).format('DD/MM/YYYY'));
			}
			else if($("#sel2").val()==='A'){ 
				  $("#wet-date").val(moment(moment(curDate).add(12, 'month')).format('DD/MM/YYYY'));
			}
		}
	});
	
	$("#sel2").change(function(){
		if($("#from-date").val()!=''){
			var curDate = $("#from-date").val();
			if($("#sel2").val()==='M'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(1, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='Q'){ 
				  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(3, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='S'){ 
					  $("#wet-date").val(moment(moment(curDate,'DD/MM/YYYY').add(6, 'month')).format('DD/MM/YYYY'));
				}
				else if($("#sel2").val()==='A'){ 
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
				{ "data": "invoiceDate",
					"render": function ( data, type, full, meta ) {
					      return moment(full.invoiceDate).format('DD/MM/YYYY');
					  }
				},
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
				{ "data": "wefDate",
					"render": function ( data, type, full, meta ) {
					      return moment(full.wefDate).format('DD/MM/YYYY');
					  }
				},
				{ "data": "wetDate",
					"render": function ( data, type, full, meta ) {
					      return moment(full.wetDate).format('DD/MM/YYYY');
					  }
				},
				{ "data": "invAmount" },
				{ "data": "taxAmount" },
				{ "data": "netAmount" },
				{ 
					"data": "invoiceId",
					"render": function ( data, type, full, meta ) {
						return '<a href="editInvoice/1">Edit</a>';
					 }

				},
			]
		} );
	  return currTable;
}
		
		
