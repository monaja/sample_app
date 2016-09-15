package com.brokersystems.setup.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.AccountDef;
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
	
	
	@RequestMapping(value = "/tenantImage/{tenId}")
	public void getImage(HttpServletResponse response, @PathVariable Long tenId)
			throws IOException, ServletException {
		TenantDef tenant = tenService.getTenantDetails(tenId);
		if (tenant.getTenId()!=null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(tenant.getPhoto());
			response.getOutputStream().close();
		}
	}

}
