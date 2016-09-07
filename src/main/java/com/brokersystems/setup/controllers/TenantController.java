package com.brokersystems.setup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.service.TenantService;

@Controller
@RequestMapping({ "/protected/tenants/setups" })
public class TenantController {
	
	@Autowired
	private TenantService tenService;
	
	
	@RequestMapping(value = "tenantlist", method = RequestMethod.GET)
	public String tenantList(Model model) {
		return "tenants";
	}
	
	
	@RequestMapping(value = "tenantsform", method = RequestMethod.GET)
	public String tenantForm(Model model) {
		return "tenantsform";
	}
	
	@RequestMapping(value = { "tenants" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantDef> getCurrencies(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return tenService.findAllTenants(pageable);
	}

}
