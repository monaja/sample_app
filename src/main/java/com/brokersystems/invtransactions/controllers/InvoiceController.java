package com.brokersystems.invtransactions.controllers;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.service.InvoiceService;
import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.RentalStructure;
import com.brokersystems.setups.model.TenantDef;

@Controller
@RequestMapping({ "/protected/transactions/invoices" })
public class InvoiceController {
	
	
	
	@Autowired
	private InvoiceService invService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
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
	
	@RequestMapping(value = { "paymentmodes" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<PaymentModes> paymentModes(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return invService.findPaymentModesForSelect(term, pageable);
	}
	
	
	@RequestMapping(value = { "activetenants" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<TenantDef> findActiveTenants(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return invService.findActiveTenants(term, pageable);
	}
	
	@RequestMapping(value = { "currencies" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<Currencies> allCurrencies(@RequestParam(value = "term", required = false) String term, Pageable pageable)
			throws IllegalAccessException {
		return invService.findCurrencyForSelect(term, pageable);
	}
	
	
	@RequestMapping(value = { "createInvoice" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ResponseEntity<TenantInvoice> createTenant(TenantInvoice invoice) throws BadRequestException {
		TenantInvoice created = invService.createInvoice(invoice);
		return new ResponseEntity<TenantInvoice>(created,HttpStatus.OK);
	}
	
	@RequestMapping(value = { "editInvoice/{invoiceId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ResponseEntity<TenantInvoice> editInvoice(@PathVariable Long invoiceId) throws BadRequestException {
		TenantInvoice created = invService.findByInvoiceId(invoiceId);
		return new ResponseEntity<TenantInvoice>(created,HttpStatus.OK);
	}

}
