package com.brokersystems.invtransactions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping({ "/protected/transactions/invoices" })
public class InvoiceController {
	
	@RequestMapping(value = "invlist", method = RequestMethod.GET)
	public String tenantList(Model model) {
		return "invoices";
	}
	
	
	@RequestMapping(value = "invform", method = RequestMethod.GET)
	public String tenantForm(Model model) {
		//model.addAttribute("tenId", -2000);
		return "invoiceform";
	}
	

}
