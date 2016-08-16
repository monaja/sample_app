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

function openEditBranchModal(button){
	var branch = JSON.parse(decodeURI($(button).data("branch")));	
	$("#brn-code").val(branch["obId"]);
	$("#brn-id").val(branch["obShtDesc"]);
	$("#brn-name").val(branch["obName"]);
	$("#brn-addresss").val(branch["address"]);
	$("#brn-telNumber").val(branch["telNumber"]);
	if(branch["branchManager"]){
	    $("#userCod").val(branch["branchManager"].id);
	    $("#username").val(branch["branchManager"].username);
	}
	
	if($("#branch-user").filter("div").html() != undefined)
	  { 
	  Select2Builder.initAjaxSelect2({
          containerId : "branch-user",
          sort : 'username',
          change: function(){
        	  
          },
          formatResult : function(a)
          {
          	return a.username
          },
          formatSelection : function(a)
          {
          	return a.username
          },
          initSelection: function (element, callback) {
        	  var usercode;
        	  var username;
          if(branch["branchManager"]){
             usercode = branch["branchManager"].id;
             username = branch["branchManager"].username;
          }
            model.organization.user.id = usercode;
            var data = {username:username,id:usercode};
            callback(data);
         },
          id: "id",
          width:"200px"
      });
	  }
	
	$('#branchModal').modal('show');
	
}


function openEditRegionModal(button){
	var branch = JSON.parse(decodeURI($(button).data("region")));	
	$("#reg-id").val(branch["shtDesc"]);
	$("#reg-name").val(branch["regDesc"]);
	if(branch["regWef"])
	$("#reg-wef").val(moment(branch["regWef"]).format('MM/DD/YYYY'));
	if(branch["regWet"])
	$("#reg-wet").val(moment(branch["regWet"]).format('MM/DD/YYYY'));
	$("#reg-code").val(branch["regCode"]);
	$('#regModal').modal('show');
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

function confirmRegionDelete(button){
	var branch = JSON.parse(decodeURI($(button).data("region")));
	bootbox.confirm("Are you sure want to delete "+branch["regDesc"]+"?", function(result) {
	      if(result){
			 $.ajax({
			        type: 'GET',
			        url:  'deleteRegion/' + branch["regCode"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	$('#orgRegion').DataTable().ajax.reload();
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



function createRegionTable(){
	var regionsUrl = "regions/0";
	  if ($("#orgCodepk").val() != ''){
		  regionsUrl = "regions/"+$("#orgCodepk").val();
		}
	  var regionTable = $('#orgRegion').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": regionsUrl,
			lengthMenu: [ [5, 10, 15], [5, 10, 15] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "shtDesc" },
				{ "data": "regDesc" },
				{ "data": "regWef" },
				{ "data": "regWet" },
				{ 
					"data": "regCode",
					"render": function ( data, type, full, meta ) {
						if(action =="A"){
							return '<input type="button" class="btn btn-primary" data-region='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="openEditRegionModal(this);"/>';
						}
						else{
							return '<input type="button" class="btn btn-primary" data-region='+encodeURI(JSON.stringify(full)) + ' value="Edit"  onclick="openEditRegionModal(this);" disabled/>';
						}
						
					}

				},
				{ 
					"data": "regCode",
					"render": function ( data, type, full, meta ) {
						if(action =="A"){
						return '<input type="button" class="btn btn-primary" data-region='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmRegionDelete(this);"/>';
						}
						else{
							return '<input type="button" class="btn btn-primary" data-region='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmRegionDelete(this);" disabled/>';
								
						}
					}

				},
			]
		} );
	  
	  $('#orgRegion tbody').on( 'click', 'tr', function () {
			 
			 $(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
			
			  var aData = regionTable.rows('.bg-light-blue-active').data();
			  $("#selOrgReg").val(aData[0].regCode);
			  createBranchTable();
			  if($("#selOrgReg"))
					$("#branchRegion").val($("#selOrgReg").val());
		    
	  } );
	  
	  
	 
	  
	  return regionTable;
}


function createBranchTable(){
	var branchesUrl = "branches/0";
	//console.log($("#selOrgReg").val());
	  if ($("#selOrgReg").val() != ''){
		  branchesUrl = "branches/"+$("#selOrgReg").val();
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
				{ "data": "address" },
				{ "data": "telNumber" },
				{ "data": "branchManager",
				  "render": function ( data, type, full, meta ) {
					   if(full.branchManager)
					  return full.branchManager.name;
					  else{
						  return "";
					  }
					}	
				},
				{ 
					"data": "obId",
					"render": function ( data, type, full, meta ) {
						if(action =="A"){
						return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="openEditBranchModal(this);"/>';
						}
						else{
							return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="openEditBranchModal(this);" disabled/>';
								
						}
					}

				},
				{ 
					"data": "obId",
					"render": function ( data, type, full, meta ) {
						if(action =="A"){
						return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmBranchDelete(this);"/>';
						}
						else{
							return '<input type="button" class="btn btn-primary" data-branch='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmBranchDelete(this);" disabled/>';
								
						}
						
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
		  
		  
		  $(".datepicker-input").each(function() {
			    $(this).datetimepicker({
                    format: 'DD/MM/YYYY'
                });
			    
			});
		  
		  createRegionTable();
		  
		   createBankTable();
		  
		   createBranchTable();
		   
		   
		   
		   var $regForm = $('#reg-form');
		   var regvalidator = $regForm.validate();
		   $('#regModal').on('hidden.bs.modal', function () {
			   regvalidator.resetForm();
			   $(".errordiv").hide();
			   $('#reg-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				if($("#orgCodepk"))
					$("#regOrgCode").val($("#orgCodepk").val());
	        });
		   
		   $('#saveRegionBtn').click(function(){
				if (!$regForm.valid()) {
					return;
				}
				var $btn = $(this).button('Saving');
				if($("#reg-wet")){
					if(Date.parse(from) > Date.parse(to)){
						 $(".errordiv").show();
						 $(".errordiv").html("Wef Date cannot be greater than Wet Date");
					}
					else{
						 $(".errordiv").hide();
					}
				}
				else{
					$("#reg-wet").val(null);
				}
				var data = {};
				$regForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var from = $("#reg-wef").val();
				var to = $("#reg-wet").val();
              
				
				var url = "createRegion";
	            var request = $.post(url, data );
				request.success(function(){
					$('#orgRegion').DataTable().ajax.reload();
					branchvalidator.resetForm();
					$('#reg-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
					$('#regModal').modal('hide');
				});

				request.error(function(jqXHR, textStatus, errorThrown){
					$(".errordiv").html(jqXHR.responseText);
					$(".errordiv").show();
				});
				request.always(function(){
					$btn.button('reset');
	            });
			});
		   
		   
		  
		  
		  var $branchForm = $('#branch-form');
		  var branchvalidator = $branchForm.validate();
		  $('#branchModal').on('hidden.bs.modal', function () {
			    branchvalidator.resetForm();
			    $("#errorDiv").hide();
				$('#branch-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
				if($("#selOrgReg"))
					$("#branchRegion").val($("#selOrgReg").val());
	        });
		  
		  $('#saveBranchBtn').click(function(){
				if (!$branchForm.valid()) {
					return;
				}
				var $btn = $(this).button('Saving');
				var data = {};
				$branchForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var url = "createRegionBranch";
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
		  
		  var $reportForm= $('#report-form');
		  
		  $('#printBtn').click(function(){
			  console.log("here....");
			     var data = {};
				$reportForm.serializeArray().map(function(x){data[x.name] = x.value;});
				var url = "generateReport";
	            var request = $.post(url, data );
	            request.success(function(){
	            	console.log("Report printed successfully");
	            });
	            
	            request.error(function(jqXHR, textStatus, errorThrown){
					console.log(jqXHR.responseText);
				//	$("#errorbankDiv").show();
				});
				request.always(function(){
	            });
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
				$("#regOrgCode").val($("#orgCodepk").val());
		  
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
		            	 model.organization.country.county.countyId = -2000;
		            	var data = {couName:countryName,couCode:countryCode};
	                    callback(data);
	                }, 
		            id: "couCode",
		            width:"200px"
		        });
		  }
		  if($("#county").filter("div").html() != undefined)
		  {
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
			  
		  }
		  
		  if($("#town").filter("div").html() != undefined)
		  {
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
		  }
		  if($("#town").filter("div").html() != undefined)
		  {
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
			  
			  
	
		  if($("#branch-user").filter("div").html() != undefined)
		  { 
		  Select2Builder.initAjaxSelect2({
	            containerId : "branch-user",
	            sort : 'username',
	            change: userChanged,
	            formatResult : function(a)
	            {
	            	return a.username
	            },
	            formatSelection : function(a)
	            {
	            	return a.username
	            },
	            initSelection: function (element, callback) {
	            	
                var usercode = $("#userCod").val();
                var username = $("#username").val();
                console.log("User is "+$("#username").val());
	              model.organization.user.id = usercode;
	              var data = {username:username,id:usercode};
                  callback(data);
               },
	            id: "id",
	            width:"200px"
	        });
		  }
		  
		  
		  function currencyChanged(e, a, v) {
	            model.organization.currency = e.added || {};
	        }
		  function userChanged(e, a, v) {
	            model.organization.user = e.added || {};
	            $("#id").val(e.added.id);
	        }
		  function countryChanged(e, a, v) {
	            model.organization.country = e.added || {};
	            countyChanged(e, a, v);
	        }
		  function countyChanged(e, a, v) {
	            model.organization.country.county = e.added || {};
	        }
		  function townChanged(e, a, v) {
			  $("#zipCode").val(e.added.ctPostalCode);
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
			},
			user:{
				id:"",
			}
	    }
	    /////////////////////////////
	};
