
$(function(){

	$(document).ready(function() {
		createCurrencyTable();	
		createCountryTable();
		$("#newCurrency").on("click", function(){
			$('#currform').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden],input[type=number], textarea").val("");
			$('#currform').find("input[type=checkbox]").attr("checked", false);
		});	
		saveUpdateCurrency();
		saveUpdateCountry();
		makeCountrySelection();
		saveUpdateCounty();
		callCountyModal();
		callTownModal();
		saveUpdateTown();
	});
});

function callCountyModal(){
     $("#btn-add-county").on('click', function(){
    	 $("#country-code").val($("#country-pk-code").val());
    	 $('#countyModal').modal('show');
     });
}

function callTownModal(){
    $("#btn-add-town").on('click', function(){
   	 $("#count-code").val($("#county-pk-code").val());
   	 $('#townModal').modal('show');
    });
}

function makeCountrySelection(){
	var table = $('#countryList').DataTable();
	$('#countryList tbody').on( 'click', 'tr', function () {
		$(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
		var aData = table.rows('.bg-light-blue-active').data();
		  $("#country-code").val(aData[0].couCode);
		  $("#country-pk-code").val(aData[0].couCode);
		  createCountyTable();
		  makeCountySelection();
    } );
}

function makeCountySelection(){
	var myTable = $('#countyList').DataTable();
	$('#countyList tbody').on( 'click', 'tr', function () {
		$(this).addClass('bg-light-blue-active').siblings().removeClass('bg-light-blue-active');
		var aData = myTable.rows('.bg-light-blue-active').data();
		  $("#count-code").val(aData[0].countyId);
		  $("#county-pk-code").val(aData[0].countyId);
		  createTownTable();
    } );
}


function saveUpdateCountry(){
	var $currForm = $('#country-form');
	var currValidator = $currForm.validate();
	 $('#countryModal').on('hidden.bs.modal', function () {
		 currValidator.resetForm();
		 $('#country-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
     });
	
	$('#saveCountryBtn').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createCountry";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#countryList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#country-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
			$('#countryModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
		
	});
}


function saveUpdateCounty(){
	var $currForm = $('#county-form');
	var currValidator = $currForm.validate();
	 $('#countyModal').on('hidden.bs.modal', function () {
		 currValidator.resetForm();
		 $('#county-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
	 });
	
	$('#saveCountyBtn').click(function(){
		if($("#country-code").val() === ''){
			bootbox.alert("Select Country to add County");
			$('#countyModal').modal('hide');
		}
		else{
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createCounty";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#countyList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#county-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
			$('#countyModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
		}
	});
}

function saveUpdateTown(){
	var $currForm = $('#town-form');
	var currValidator = $currForm.validate();
	 $('#townModal').on('hidden.bs.modal', function () {
		 currValidator.resetForm();
		 $('#town-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
	 });
	
	$('#saveTownBtn').click(function(){
		if($("#count-code").val() === ''){
			bootbox.alert("Select County to add Town");
			$('#townModal').modal('hide');
		}
		else{
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createTown";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#townList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#county-form').find("input[type=text],input[type=mobileNumber],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
			$('#townModal').modal('hide');
        });
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
		}
	});
}

function saveUpdateCurrency(){
	var $currForm = $('#currform');
	var currValidator = $currForm.validate();
	$('#saveCurrencyBtn').click(function(){
		if (!$currForm.valid()) {
			return;
		}
		var $btn = $(this).button('Saving');
		var data = {};
		$currForm.serializeArray().map(function(x){data[x.name] = x.value;});
		var url = "createCurrency";
        var request = $.post(url, data );
        request.success(function(){
        	bootbox.alert("Record created/updated Successfully");
			$('#currencyList').DataTable().ajax.reload();
			currValidator.resetForm();
			$('#currform').find("input[type=text],input[type=mobileNumber],input[type=number],input[type=emailFull],input[type=password],input[type=hidden], textarea").val("");
			$('#currform').find("input[type=checkbox]").attr("checked", false);
			
		});
        request.error(function(jqXHR, textStatus, errorThrown){
        	bootbox.alert(jqXHR.responseText);
		});
		request.always(function(){
			$btn.button('reset');
        });
	});
}

function createCountryTable(){
	var url = "allCountries";
	  var currTable = $('#countryList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "couShtDesc" },
				{ "data": "couName" },
				{ 
					"data": "couCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-country='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editCountry(this);"/>';
					}

				},
				{ 
					"data": "couCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-country='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmCountryDelete(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}


function createCurrencyTable(){
	var url = "currencies";
	  var currTable = $('#currencyList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			lengthMenu: [ [10, 20, 30], [10, 20, 30] ],
			pageLength: 10,
			destroy: true,
			"columns": [
				{ "data": "curIsoCode" },
				{ "data": "curName" },
				{ "data": "enabled" },
				{ "data": "fraction" },
				{ "data": "fractionUnits" },
				{ 
					"data": "curCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-currency='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editCurrency(this);"/>';
					}

				},
				{ 
					"data": "curCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-currency='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmCurrencyDelete(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}

function editCurrency(button){
	var currency = JSON.parse(decodeURI($(button).data("currency")));
	$("#curr-name").val(currency["curName"]);
	if(currency["enabled"])
		$("#enabled").prop("checked", currency["enabled"]);
	else
		$("#enabled").prop("checked", false);
	$("#fraction").val(currency["fraction"]);
	$("#frac-units").val(currency["fractionUnits"]);
	$("#curr-symbol").val(currency["curIsoCode"]);
	$("#cur-code").val(currency["curCode"]);
	
}

function confirmCountryDelete(button){
	var country = JSON.parse(decodeURI($(button).data("country")));
	bootbox.confirm("Are you sure want to delete "+country["couName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteCountry/' + country["couCode"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#countryList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

function editCountry(button){
	var country = JSON.parse(decodeURI($(button).data("country")));
	$("#cou-code").val(country["couCode"]);
	$("#country-id").val(country["couShtDesc"]);
	$("#cou-name").val(country["couName"]);
	$('#countryModal').modal('show');
}

function confirmCurrencyDelete(button){
	var currency = JSON.parse(decodeURI($(button).data("currency")));
	bootbox.confirm("Are you sure want to delete "+currency["curName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteCurrency/' + currency["curCode"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#currencyList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}

function editCounty(button){
	var country = JSON.parse(decodeURI($(button).data("county")));
	$("#county-code").val(country["countyId"]);
	$("#country-code").val($("#country-pk-code").val());
	$("#county-id").val(country["countyCode"]);
	$("#county-name").val(country["countyName"]);
	$('#countyModal').modal('show');
}

function editTown(button){
	var country = JSON.parse(decodeURI($(button).data("town")));
	$("#town-code").val(country["ctCode"]);
	$("#town-id").val(country["ctShtDesc"]);
	$("#town-name").val(country["ctName"]);
	$("#town-postal-code").val(country["ctPostalCode"]);
	$("#count-code").val($("#county-pk-code").val());
	$('#townModal').modal('show');
}

function confirmTownDelete(button){
	var country = JSON.parse(decodeURI($(button).data("town")));
	bootbox.confirm("Are you sure want to delete "+country["ctName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteTown/' + country["ctCode"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#townList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}


function confirmCountyDelete(button){
	var country = JSON.parse(decodeURI($(button).data("county")));
	bootbox.confirm("Are you sure want to delete "+country["countyName"]+"?", function(result) {
		 if(result){
	    	  $.ajax({
			        type: 'GET',
			        url:  'deleteCounty/' + country["countyId"],
			        dataType: 'json',
			        async: true,
			        success: function(result) {
			        	bootbox.alert("Record deleted Successfully");
			        	$('#countyList').DataTable().ajax.reload();
			        },
			        error: function(jqXHR, textStatus, errorThrown) {
                        bootbox.alert(jqXHR.responseText);
			        }
			    });
	      }
		
	});
}



function createCountyTable(){
	var url = "allCounties/0";
	if($("#country-code").val() != ''){
		url = "allCounties/"+$("#country-code").val();
	}
	  var currTable = $('#countyList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			destroy: true,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "countyCode" },
				{ "data": "countyName" },
				{ 
					"data": "countyId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-county='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editCounty(this);"/>';
					}

				},
				{ 
					"data": "countyId",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-county='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmCountyDelete(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}

function createTownTable(){
	var url = "allTowns/0";
	if($("#count-code").val() != ''){
		url = "allTowns/"+$("#count-code").val();
	}
	  var currTable = $('#townList').DataTable( {
			"processing": true,
			"serverSide": true,
			"ajax": url,
			destroy: true,
			lengthMenu: [ [5, 10], [5, 10] ],
			pageLength: 5,
			destroy: true,
			"columns": [
				{ "data": "ctShtDesc" },
				{ "data": "ctName" },
				{ "data": "ctPostalCode" },
				{ 
					"data": "ctCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-town='+encodeURI(JSON.stringify(full)) + ' value="Edit" onclick="editTown(this);"/>';
					}

				},
				{ 
					"data": "ctCode",
					"render": function ( data, type, full, meta ) {
						return '<input type="button" class="hyperlink-btn" data-town='+encodeURI(JSON.stringify(full)) + ' value="Delete" onclick="confirmTownDelete(this);"/>';
					}

				},
			]
		} );
	  return currTable;
}
	