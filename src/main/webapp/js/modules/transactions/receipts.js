var arr = {};

var recexists = [];

$(function() {

	$(document)
			.ready(
					function() {
						$(".datepicker-input").each(function() {
							$(this).datetimepicker({
								format : 'DD/MM/YYYY'
							});

						});

						createReceipts();
						currencyLov();
						paymentModesLov();
						addInvoiceTrans();
						
						

						$("#rct-detail-tbl").on(
								'click',
								'.hyperlink-btn',
								function() {
									var transNo = $(this).closest('tr').find(
											"td:first").html();
									var index = recexists.indexOf(transNo);
									recexists.splice(index, 1);
									// if (index in arr){
									delete arr[transNo];
									// }
									console.log("trans no " + transNo);
									// console.log("index "+index);
									console.log(recexists);
									console.log(arr);
									$(this).closest('tr').remove();
								});

						$("#rct-amount").number(true, 2);

						$('#btn-add-selected')
								.on(
										'click',
										function() {
											var data = "";
											var counter = 0;
											for (x in arr) {

												var index = recexists
														.indexOf(arr[x].transId);
												if (index > -1) {

												} else {
													counter++;
													data += "<tr><td><input type='text' size='7' readonly value='"
															+ arr[x].transId
															+ "' name='invoiceCode'></td><td>"
															+ arr[x].refno
															+ "</td>";
													data += "<td>"
															+ moment(arr[x].transDate).format('DD/MM/YYYY') 
															+ "</td> <td>"
															+ arr[x].tenant.fname
															+ ' '
															+ arr[x].tenant.otherNames
															+ "</td><td><textarea rows='3' cols=15 name='narration'/></td><td>"
															+ arr[x].transAmount
															+ "</td><td>"
															+ arr[x].transBalance
															+ "</td><td><input type='text' size='11' id='rctamt-"
															+ counter
															+ "'  name='rctAmount'/></td><td><input type='button' class='hyperlink-btn' value='Delete'></td></tr>";

													recexists
															.push(arr[x].transId);
												}

											}

											$('#rct-detail-tbl').append(data);
											$('[id^=rctamt-]').number(true, 2);
										});
						$('#btn-add-selected-exit')
						.on(
								'click',
								function() {
									var data = "";
									var counter = 0;
									for (x in arr) {

										var index = recexists
												.indexOf(arr[x].transId);
										if (index > -1) {

										} else {
											counter++;
											data += "<tr><td><input type='text' size='7' readonly value='"
													+ arr[x].transId
													+ "' name='invoiceCode'></td><td>"
													+ arr[x].refno
													+ "</td>";
											data += "<td>"
													+ moment(arr[x].transDate).format('DD/MM/YYYY') 
													+ "</td> <td>"
													+ arr[x].tenant.fname
													+ ' '
													+ arr[x].tenant.otherNames
													+ "</td><td><textarea rows='3' cols=15 name='narration'/></td><td>"
													+ arr[x].transAmount
													+ "</td><td>"
													+ arr[x].transBalance
													+ "</td><td><input type='text' size='11' id='rctamt-"
													+ counter
													+ "'  name='rctAmount'/></td><td><input type='button' class='hyperlink-btn' value='Delete'></td></tr>";

											recexists
													.push(arr[x].transId);
										}

									}

									$('#rct-detail-tbl').append(data);
									$('[id^=rctamt-]').number(true, 2);
									$('#tenantTransModal').modal('hide');
								});

						$("#btn-add-receipt").on('click', function() {
							createandPrintReceipt();
							
						});
						
						$("#btn-print-receipt").on('click', function() {
							createReceipt();
						});
						
						$("#btn-search-invoice").on('click', function(){
							createTransactionTbl();
							
						});

					});

});

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}



function printPdf(url){
	var iframe = this._printIframe;
	  if (!this._printIframe) {
	    iframe = this._printIframe = document.createElement('iframe');
	    document.body.appendChild(iframe);

	    iframe.style.display = 'none';
	    iframe.onload = function() {
	      setTimeout(function() {
	        iframe.focus();
	        iframe.contentWindow.print();
	      }, 1);
	    };
	  }

	  iframe.src = url;
}

function createReceipt() {
	var $currForm = $('#receipt-form');
	var currValidator = $currForm.validate();
	if (!$currForm.valid()) {
		return;
	}

	var data = {};
	$currForm.serializeArray().map(function(x) {
		data[x.name] = x.value;
	});
	var url = "createReceipt";
	var arr = createAllocation();
	data.details = arr;
	$.ajax({
		url : url,
		type : "POST",
		data : JSON.stringify(data),
		success : function(s) {
			$('#receipt-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
			 arr = {};
			 recexists = [];
			 $('#rct-detail-tbl tbody').remove();
			 currencyLov();
			 paymentModesLov();
			$.ajax({
		        type: 'GET',
		        url:  'allocateReceipt',
		        dataType: 'json',
		        data: {"receiptCode": s},
		        async: true,
		        success: function(result) {
		        	bootbox.alert("Receipt Created Successfully");
		        },
		        error: function(jqXHR, textStatus, errorThrown) {
		        	console.log(jqXHR);
		        	bootbox.alert(jqXHR.responseText);
		        }
		    });
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			bootbox.alert(jqXHR.responseText);
		},
		dataType : "json",
		contentType : "application/json"
	});
}

function createandPrintReceipt() {
	var $currForm = $('#receipt-form');
	var currValidator = $currForm.validate();
	if (!$currForm.valid()) {
		return;
	}

	var data = {};
	$currForm.serializeArray().map(function(x) {
		data[x.name] = x.value;
	});
	var url = "createReceipt";
	var arr = createAllocation();
	data.details = arr;
	$.ajax({
		url : url,
		type : "POST",
		data : JSON.stringify(data),
		success : function(s) {
			$('#receipt-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
			 arr = {};
			 recexists = [];
			 $('#rct-detail-tbl tbody').remove();
			 currencyLov();
			 paymentModesLov();
			printPdf(getContextPath()+"/protected/transactions/receipts/receipt_rpt/"+s);
		    bootbox.confirm({
		        message: "Receipt Printed Successfully?",
		        buttons: {
		            confirm: {
		                label: 'Yes',
		                className: 'btn-success'
		            },
		            cancel: {
		                label: 'No',
		                className: 'btn-danger'
		            }
		        },
		        callback: function (result) {
		        	 if(result){
							$.ajax({
						        type: 'GET',
						        url:  'allocateReceipt',
						        dataType: 'json',
						        data: {"receiptCode": s},
						        async: true,
						        success: function(result) {
						        	bootbox.alert("Receipt Printing operation complete");
						        	
						        },
						        error: function(jqXHR, textStatus, errorThrown) {
						        	console.log(jqXHR);
						        	bootbox.alert(jqXHR.responseText);
						        }
						    });
						 
					 }
		        }
		    });
		},
		error : function(jqXHR, textStatus, errorThrown) {
			bootbox.alert(jqXHR.responseText);
		},
		dataType : "json",
		contentType : "application/json"
	});
}

function createAllocation() {
	var arr = [];
	$('#rct-detail-tbl > tbody  > tr').each(
			function() {
				var data = {};
				$(this).find(":input[type='text'],textarea").serializeArray()
						.map(function(x) {
							data[x.name] = x.value;
						});
				arr.push(data);
			});
	return arr;
}

function addInvoiceTrans() {
	$("#add-det-btn").on('click', function() {
		createTransactionTbl();
		$('#tenantTransModal').modal({
			backdrop : 'static',
			keyboard : true
		})
	});
}

var model = {
	receipt : {
		tenant : {
			tenId : "",
		},
		currency : {
			curCode : "",
		},
		paymentmodes : {
			pmId : ""
		}
	}
};

function currencyLov() {
	if ($("#curr-div").filter("div").html() != undefined) {
		Select2Builder.initAjaxSelect2({
			containerId : "curr-div",
			sort : 'curName',
			change : function(e, a, v) {
				$("#cur-id").val(e.added.curCode);
			},
			formatResult : function(a) {
				return a.curName
			},
			formatSelection : function(a) {
				return a.curName
			},
			initSelection : function(element, callback) {
				// var currCode = $("#cur-id").val();
				// var currName = $("#cur-name").val();
				// model.invoice.currency.curCode = currCode;
				// var data = {curName:currName,curCode:currCode};
				// callback(data);
			},
			id : "curCode",
			width : "220px"
		});
	}
}

function paymentModesLov() {
	if ($("#div-paymodes").filter("div").html() != undefined) {
		Select2Builder.initAjaxSelect2({
			containerId : "div-paymodes",
			sort : 'pmDesc',
			change : function(e, a, v) {
				$("#pymt-id").val(e.added.pmId);
			},
			formatResult : function(a) {
				return a.pmDesc
			},
			formatSelection : function(a) {
				return a.pmDesc
			},
			initSelection : function(element, callback) {
				// var code = $("#pymt-id").val();
				// var pname = $("#pymt-desc").val();
				// model.invoice.paymentmodes.pmId = code;
				// var data = {pmDesc:pname,pmId:code};
				// callback(data);
			},
			id : "pmId",
			width : "220px"
		});
	}
}

function currencyFormat(num) {
	if (num)
		return num.toFixed(2).replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,")
	else
		return null;
}

function createReceipts() {
	var url = "receipts";
	var currTable = $('#rcts-tbl')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"ajax" : url,
						lengthMenu : [ [ 20, 30, 40, 50 ], [ 20, 30, 40, 50 ] ],
						pageLength : 20,
						destroy : true,
						"columns" : [
								{
									"data" : "receiptNo"
								},
								{
									"data" : "receiptDate",
									"render" : function(data, type, full, meta) {
										return moment(full.receiptDate).format(
												'DD/MM/YYYY');
									}

								},
								{
									"data" : "receiptUser",
									"render" : function(data, type, full, meta) {
										return full.receiptUser.username;
									}
								},
								{
									"data" : "paymentMode",
									"render" : function(data, type, full, meta) {
										return full.paymentMode.pmDesc;
									}
								},
								{
									"data" : "receiptAmount",
									"render" : function(data, type, full, meta) {
										return currencyFormat(full.receiptAmount);
									}
								},
								{
									"data" : "paidBy"
								},
								{
									"data" : "printed"
								},
//								{
//									"data" : "receiptId",
//									"render" : function(data, type, full, meta) {
//										if (full.status === "A") {
//											return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='
//													+ full.receiptId
//													+ '><input type="submit"  class="hyperlink-btn" value="View" ></form>';
//
//										} else
//											return '<form action="editInvoice" method="post"><input type="hidden" name="id" value='
//													+ full.receiptId
//													+ '><input type="submit"  class="hyperlink-btn" value="Edit" ></form>';
//
//									}
//
//								}, 
								]
					});

	return currTable;
}

function createTransactionTbl() {
	var url = "tenanttrans";
	var currTable = $('#modal-rct-detail-tbl')
			.DataTable(
					{
						"processing" : true,
						"serverSide" : true,
						"searching": false,
						"ajax": {
							'url': url,
							'data':{
								'firstName': $("#inv-search-name").val(),
								'otherNames':  $("#inv-search-other-names").val(),
								'invoiceNumber': $("#inv-search-number").val(),
							},
						},
						lengthMenu : [ [ 10,20 ], [ 10,20 ] ],
						pageLength : 10,
						destroy : true,
						"columns" : [
								{
									"data" : "transId",
									"render" : function(data, type, full, meta) {
										return '<input type="checkbox" class="chk-trans" id="'
												+ full.transId + '">';
									}

								},
								{
									"data" : "transId"
								},
								{
									"data" : "refno"
								},
								{
									"data" : "transDate",
									"render" : function(data, type, full, meta) {
										return moment(full.transDate).format('DD/MM/YYYY');
									}

								},
								{
									"data" : "tenant",
									"render" : function(data, type, full, meta) {
										return full.tenant.fname + " "
												+ full.tenant.otherNames;
									}
								},
								{
									"data" : "transAmount",
									"render" : function(data, type, full, meta) {
										return currencyFormat(full.transAmount);
									}
								},
								{
									"data" : "transBalance",
									"render" : function(data, type, full, meta) {
										return currencyFormat(full.transBalance);
									}
								},

						]
					});

	$('#modal-rct-detail-tbl tbody').on('click', 'tr', function() {
		var val = $(this).find('.chk-trans').attr('id');
		if ($(this).find('.chk-trans').is(':checked')) {
			var d = currTable.row(this).data();
			arr[val] = d;
		} else {
			if (val in arr) {
				delete arr[val];
			}
		}

	});

	return currTable;
}