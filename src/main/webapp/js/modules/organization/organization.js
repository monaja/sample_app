//function to display avatar on the screen
function orgDetails(){
	$("#avatar").fileinput({
	    overwriteInitial: true,
	    maxFileSize: 1500,
	    showClose: false,
	    showCaption: false,
	    browseLabel: '',
	    removeLabel: '',
	    browseIcon: '<i class="fa fa-folder-open"></i>',
	    removeIcon: '<i class="fa fa-times"></i>',
	    removeTitle: 'Cancel or reset changes',
	    elErrorContainer: '#kv-avatar-errors',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img src="'+getContextPath()+'/protected/organization/logo"  style="width:160px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
}


function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
}

//function to display country details from the database
//This is based on Jquery Datatables framework 
/*
function showCountryModal(){
	var dataTableUrl = "countries";
    var usersTable = $('#country').DataTable( {
		"processing": true,
		"serverSide": true,
		destroy: true,
		"ajax": dataTableUrl,
		"columns": [
			{ "data": "couShtDesc" },
			{ "data": "couName" },
			{
				"data": "couCode",
				 "render": function ( data, type, full, meta ) {
						return '<a href="javascript:void(0)" data-country='+encodeURI(JSON.stringify(full)) + ' onclick="selectCountry(this);">Select</a>';
			    }

			},
		]
	} );
    $('#countryModal').modal('show');
}

//Function to populate selected 
function selectCountry(button){
	var country = JSON.parse(decodeURI($(button).data("country")));	
	$("#countryCode").val(country["couCode"]);
	$("#countryName").val(country["couName"]);
	$('#countryModal').modal('hide');
}

function showCountyModal(){
	 var countyUrl = "counties/0"
	if ($("#countryCode").val() != ''){
		 countyUrl = "counties/"+$("#countryCode").val();
	}
     var usersTable = $('#county').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": countyUrl,
			destroy: true,
			"columns": [
				{ "data": "countyCode" },
				{ "data": "countyName" },
				{
					"data": "countyId",
					 "render": function ( data, type, full, meta ) {
							return '<a href="javascript:void(0)" data-county='+encodeURI(JSON.stringify(full)) + ' onclick="selectCounty(this);">Select</a>';
				    }

				},
			]
		} );
	$('#countyModal').modal('show');
}


function selectCounty(button){
	var country = JSON.parse(decodeURI($(button).data("county")));	
	$("#txtcountyCode").val(country["countyId"]);
	$("#txtcounty").val(country["countyName"]);
	$('#countyModal').modal('hide');
}


function showTownModal(){
	 var countyUrl = "towns/0"
	if ($("#txtcountyCode").val() != ''){
		 countyUrl = "towns/"+model.organization.country.couCode;
	}
    var usersTable = $('#town').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": countyUrl,
			destroy: true,
			"columns": [
				{ "data": "ctShtDesc" },
				{ "data": "ctName" },
				{
					"data": "ctCode",
					 "render": function ( data, type, full, meta ) {
							return '<a href="javascript:void(0)" data-town='+encodeURI(JSON.stringify(full)) + ' onclick="selectTown(this);">Select</a>';
				    }

				},
			]
		} );
	$('#townModal').modal('show');
}


function selectTown(button){
	var country = JSON.parse(decodeURI($(button).data("town")));	
	$("#txtCityCode").val(country["ctCode"]);
	$("#txtCity").val(country["ctName"]);
	$('#townModal').modal('hide');
}


function selectCurrency(button){
	var country = JSON.parse(decodeURI($(button).data("currency")));	
	$("#txtCurrency").val(country["curName"]);
	$("#txtCurCode").val(country["curCode"]);
	$('#currencyModal').modal('hide');
}
*/
function openEditBranchModal(button){
	var branch = JSON.parse(decodeURI($(button).data("branch")));	
	$("#brn-code").val(branch["obId"]);
	$("#brn-id").val(branch["obShtDesc"]);
	$("#brn-name").val(branch["obName"]);
	$('#branchModal').modal('show');
}


function openEditBankModal(button){
	var branch = JSON.parse(decodeURI($(button).data("bank")));	
	$("#bank-code").val(branch["bankCode"]);
	$("#bank-id").val(branch["bankShtDesc"]);
	$("#bank-name").val(branch["bankName"]);
	$('#bankModal').modal('show');
}

function confirmBranchDelete(button){
	var branch = JSON.parse(decodeURI($(button).data("branch")));
	bootbox.confirm("Are you sure want to delete "+branch["obName"]+"?", function(result) {
	      if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteBranch/' + branch["obId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	$('#confirm-delete').modal('hide');
			        	$('#orgBranches').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                           bootbox.alert(jqXHR.responseText, function() {
			        		
			        	    });
			        }
			    });
	      }
	    });
	
}

function confirmBankDelete(button){
	var branch = JSON.parse(decodeURI($(button).data("bank")));
	bootbox.confirm("Are you sure want to delete "+branch["bankName"]+"?", function(result) {
	      if(result){
			 $.ajax({
			        type: 'GET',
			        url:  'deleteBank/' + branch["bankCode"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	$('#orgBranks').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
			        	bootbox.alert(jqXHR.responseText, function() {
			        		
			        	});
			        }
			    });
	      }
	});
	
}

function createBankTable(){
	var banksUrl = "banks/0";
	  if ($("#orgCodepk").val() != ''){
		  banksUrl = "banks/"+$("#orgCodepk").val();
		}
	  var banksTable = $('#orgBranks').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": banksUrl,
			lengthMenu: [ [5, 10, 15], [5, 10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "bankShtDesc" },
				{ "data": "bankName" },
				{ 
					"data": "bankCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-bank='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="openEditBankModal(this);"/>';
					}

				},
				{ 
					"data": "bankCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-bank='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmBankDelete(this);"/>';
					}

				},
			]
		} );
	  return banksTable;
}



function createBranchTable(){
	var branchesUrl = "branches/0";
	  if ($("#orgCodepk").val() != ''){
		  branchesUrl = "branches/"+$("#orgCodepk").val();
		}
	  var branchTable = $('#orgBranches').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": branchesUrl,
			lengthMenu: [ [5, 10, 15], [5, 10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "obShtDesc" },
				{ "data": "obName" },
				{ 
					"data": "obId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="openEditBranchModal(this);"/>';
					}

				},
				{ 
					"data": "obId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmBranchDelete(this);"/>';
					}

				},
			]
		} );
	  return branchTable;
}

$(function(){

	$(document).ready(function() {

		  orgDetails();
		  var dataTableUrl = "currencies";
		  var usersTable = $('#currencies').DataTable( {
				"processing": true,
				"serverSide": true,
				"ajax": dataTableUrl,
				"columns": [
					{ "data": "curIsoCode" },
					{ "data": "curName" },
					{
						"data": "curCode",
						 "render": function ( data, type, full, meta ) {
								return '<a href="javascript:void(0)" data-currency='+encodeURI(JSON.stringify(full)) + ' onclick="selectCurrency(this);">Select</a>';
					    }

					},
				]
			} );
		  
		   createBankTable();
		  
		   createBranchTable();
		  
		  
		  var $branchForm = $('#branch-form');
		  var branchvalidator = $branchForm.validate();
		  $('#branchModal').on('hidden.bs.modal', function () {
			    branchvalidator.resetForm();
			    $("#errorDiv").hide();
				$('#branch-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				if($("#orgCodepk"))
					$("#branchOrgCode").val($("#orgCodepk").val());
	        });
		  
		  $('#saveBranchBtn').click(function(){
				if (!$branchForm.valid()) {
					return;
				}
				var $btn = $(this).button('Saving');
				var data = {};
				$branchForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var url = "createOrgBranch";
	            var request = $.post(url, data );
				request.success(function(){
					$('#orgBranches').DataTable().ajax.reload();
					branchvalidator.resetForm();
					$('#branch-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
					$('#branchModal').modal('hide');
				});

				request.error(function(jqXHR, textStatus, errorThrown){
					$("#errorId").html(jqXHR.responseText);
					$("#errorDiv").show();
				});
				request.always(function(){
					$btn.button('reset');
	            });
			});
		  
		  
		  var $bankForm = $('#bank-form');
		  var bankvalidator = $bankForm.validate();
		  $('#bankModal').on('hidden.bs.modal', function () {
			  bankvalidator.resetForm();
			  $("#errorbankDiv").hide();
				$('#bank-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				if($("#orgCodepk"))
					$("#bankOrgCode").val($("#orgCodepk").val()); 
		  });
		  
		  
		  $('#saveBankBtn').click(function(){
				if (!$bankForm.valid()) {
					return;
				}
				var $btn = $(this).button('Saving');
				var data = {};
				$bankForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var url = "createOrgBank";
	            var request = $.post(url, data );
				request.success(function(){
					$('#orgBranks').DataTable().ajax.reload();
					bankvalidator.resetForm();
					$('#bank-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
					$('#bankModal').modal('hide');
				});

				request.error(function(jqXHR, textStatus, errorThrown){
					$("#errorbankId").html(jqXHR.responseText);
					$("#errorbankDiv").show();
				});
				request.always(function(){
	            });
			});
		  //01-March Update
		  rivets.bind($("#organization_model"), model);
		  //10-March Update
		  if($("#orgCodepk"))
			  $("#branchOrgCode").val($("#orgCodepk").val());
		  
		  if($("#orgCodepk"))
				$("#bankOrgCode").val($("#orgCodepk").val()); 
		  
		  if($("#country").filter("div").html() != undefined)
		  {
			  Select2Builder.initAjaxSelect2({
		            containerId : "country",
		            sort : 'couName',
		            change: countryChanged,
		            formatResult : function(a)
		            {
		            	return a.couName
		            },
		            formatSelection : function(a)
		            {
		            	return a.couName
		            },
		            initSelection: function (element, callback) {
	                      var countryCode = $("#countryCode").val();
	                      var countryName = $("#countryName").val();
		            	 model.organization.country.couCode = countryCode;
		            	var data = {couName:countryName,couCode:countryCode};
	                    callback(data);
	                }, 
		            id: "couCode",
		            width:"200px"
		        });
			  
			  Select2Builder.initAjaxSelect2({
		            containerId : "county",
		            sort : 'countyName',
		            change: countyChanged,
		            formatResult : function(a)
		            {
		            	return a.countyName
		            },
		            formatSelection : function(a)
		            {
		            	return a.countyName
		            },
		            initSelection: function (element, callback) {
	                    var countyCode = $("#txtcountyId").val();
	                    var countyName = $("#txtcounty").val();
		            	 model.organization.country.county.countyId = countyCode;
		            	var data = {countyName:countyName,countyCode:countyCode};
	                  callback(data);
	              },
		            id: "countyId",
		            width:"200px",
		            params: {couCode: function(){
		            	return model.organization.country.couCode;
		            }}
		        });
			  
			  
			  Select2Builder.initAjaxSelect2({
		            containerId : "town",
		            sort : 'ctName',
		            change: townChanged,
		            formatResult : function(a)
		            {
		            	return a.ctName
		            },
		            formatSelection : function(a)
		            {
		            	return a.ctName
		            },
		            initSelection: function (element, callback) {
	                  var cityCode = $("#txtCityCode").val();
	                  var cityName = $("#txtCity").val();
		              model.organization.country.county.town.ctCode = cityCode;
		              var data = {ctName:cityName,ctCode:cityCode};
	                  callback(data);
	            },
		            id: "ctCode",
		            width:"200px",
		            params: {countyId: function(){
		            	return model.organization.country.county.countyId;
		            }}
		        });
			  
			  
			  Select2Builder.initAjaxSelect2({
		            containerId : "currency",
		            sort : 'curName',
		            change: currencyChanged,
		            formatResult : function(a)
		            {
		            	return a.curName
		            },
		            formatSelection : function(a)
		            {
		            	return a.curName
		            },
		            initSelection: function (element, callback) {
	                  var currCode = $("#txtCurrencyCode").val();
	                  var currName = $("#txtCurrency").val();
		              model.organization.currency.curCode = currCode;
		              var data = {curName:currName,curCode:currCode};
	                  callback(data);
	            },
		            id: "curCode",
		            width:"200px"
		        });
			  
		  }
		  
		  
		  function currencyChanged(e, a, v) {
	            model.organization.currency = e.added || {};
	        }
		  function countryChanged(e, a, v) {
	            model.organization.country = e.added || {};
	        }
		  function countyChanged(e, a, v) {
	            model.organization.country.county = e.added || {};
	        }
		  function townChanged(e, a, v) {
	            model.organization.country.county.town = e.added || {};
	        }
	});
	
	
});

var model = {
		organization: {
			country:{
				couCode:"",
				county:{
					countyId:"",
					town:{
						ctCode:"",
					}
				}
			},
			currency:{
				curCode:"",
			}
	    }
	    /////////////////////////////
	};
