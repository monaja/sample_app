package com.brokersystems.invtransactions.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.service.InvoiceService;
import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

@Controller
@RequestMapping({ "/protected/transactions/invoices" })
public class InvoiceController {
	
	
	
	@Autowired
	private InvoiceService invService;
	
	@RequestMapping(value = "invlist", method = RequestMethod.GET)
	public String tenantList(Model model) {
		return "invoices";
	}
	
	
	@RequestMapping(value = "invform", method = RequestMethod.GET)
	public String tenantForm(Model model) {
		return "invoiceform";
	}
	
	
	@RequestMapping(value = { "invoices" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantInvoice> getCurrencies(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return invService.findAllInvoices(pageable);
	}

}
