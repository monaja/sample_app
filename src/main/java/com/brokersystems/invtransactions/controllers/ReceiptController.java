package com.brokersystems.invtransactions.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.service.ReceiptService;
import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

@Controller
@RequestMapping({ "/protected/transactions/receipts" })
public class ReceiptController {
	
	@Autowired
	private ReceiptService receiptService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "receiptList", method = RequestMethod.GET)
	public String receiptList(Model model) {
		return "receiptslist";
	}
	
	
	@RequestMapping(value = "receiptentry", method = RequestMethod.GET)
	public String receiptlist(Model model) {
		return "receiptsform";
	}
	
	@RequestMapping(value = { "receipts" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<ReceiptTrans> getAllReceipts(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return receiptService.findAllReceipts(pageable);
	}

}
