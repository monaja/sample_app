package com.brokersystems.setup.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.AccountDef;
import com.brokersystems.setups.model.ModelHelperForm;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.TenAllocations;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.service.SetupsService;
import com.brokersystems.setups.service.TenantService;

@Controller
@RequestMapping({ "/protected/tenants/setups" })
public class TenantController {
	
	@Autowired
	private TenantService tenService;
	
	@Autowired
	private SetupsService setupsService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ModelAttribute
	public ModelHelperForm createHelperForm() {
		return new ModelHelperForm();
	}
	
	
	@RequestMapping(value = "tenantlist", method = RequestMethod.GET)
	public String tenantList(Model model) {
		return "tenants";
	}
	
	
	@RequestMapping(value = "tenantsform", method = RequestMethod.GET)
	public String tenantForm(Model model) {
		model.addAttribute("tenId", -2000);
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
	
	@RequestMapping(value = { "rentalstructs" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<RentalStructure> rentalStructures(@RequestParam(value = "term", required = false) String term, Pageable pageable,@RequestParam("branchId") Long branchId)
			throws IllegalAccessException {
	
		return setupsService.findRentalStructForSelect(branchId, term, pageable);
	}
	
	@RequestMapping(value = { "rentalunits" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<RentalUnits> rentalUnits(@RequestParam(value = "term", required = false) String term, Pageable pageable,@RequestParam("rentalId") Long rentalId)
			throws IllegalAccessException {
		return setupsService.findRentalUnitsForSelect(rentalId, term, pageable);
	}
	
	@RequestMapping(value = { "createTenant" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public void createTenant(TenantDef tenDef) throws IllegalAccessException, IOException, BadRequestException {
		if ((tenDef.getFile() != null) && (!tenDef.getFile().isEmpty())) {
			if (tenDef.getFile().getSize() != 0) {
				tenDef.setPhoto(tenDef.getFile().getBytes());
			} else {

				if (tenDef.getTenId() != null) {
					tenDef.setPhoto(
							setupsService.getTenantDetails(tenDef.getTenId()).getPhoto());

				}
			}
		} else {

			if (tenDef.getTenId() != null) {
				tenDef.setPhoto(setupsService.getTenantDetails(tenDef.getTenId()).getPhoto());

			}
		}
		 setupsService.defineTenant(tenDef);
	}
	
	@RequestMapping(value = { "tenants/{tenId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public TenantDef getAccountDetails(@PathVariable Long tenId) {
		TenantDef tenant =  setupsService.getTenantDetails(tenId);
		TenAllocations activeAlloc = setupsService.getActiveAllocation(tenId);
		tenant.setAllocation(activeAlloc);
		return tenant;
	}
	
	
	@RequestMapping(value = "/editTenantForm", method = RequestMethod.POST)
	public String editRentalForm(@Valid @ModelAttribute ModelHelperForm helperForm, Model model) {
		model.addAttribute("tenId", helperForm.getId());
		return "tenantsform";
	}
	

}
