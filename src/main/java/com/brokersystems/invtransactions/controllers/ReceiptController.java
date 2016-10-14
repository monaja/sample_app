package com.brokersystems.invtransactions.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoiceBean;
import com.brokersystems.invtransactions.model.Transactions;
import com.brokersystems.invtransactions.service.ReceiptService;
import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;

@Controller
@RequestMapping({ "/protected/transactions/receipts" })
public class ReceiptController {
	
	@Autowired
	private ReceiptService receiptService;
	
	@Autowired
	  private DataSource datasource;
	
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
	
	@RequestMapping(value = { "tenanttrans" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<Transactions> getTransactions(@DataTable DataTablesRequest pageable, @RequestParam(value = "invoiceNumber", required = false) String invoiceNumber,
			@RequestParam(value = "firstName", required = false) String firstName,@RequestParam(value = "otherNames", required = false) String otherNames)
			throws IllegalAccessException {
		return receiptService.findReceiptTransactions(pageable, firstName, otherNames, invoiceNumber);
	}
	
	@RequestMapping(value = { "createReceipt" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public ResponseEntity<Long> createReceipt(@RequestBody ReceiptTrans receipt) throws BadRequestException {
		Long created = receiptService.createReceipt(receipt);
		return new ResponseEntity<Long>(created,HttpStatus.OK);
	}
	
	@RequestMapping(value = "receipt_rpt/{receiptNo}", method = RequestMethod.GET)
	public ModelAndView receiptRpt(ModelMap modelMap,  HttpServletRequest request,ModelAndView modelAndView, @PathVariable Long receiptNo) throws BadRequestException {
	  
	 
	  modelMap.put("datasource", datasource);
	  modelMap.put("format", "pdf");
	  modelMap.put("receiptNo", receiptNo);
	  modelAndView = new ModelAndView("rpt_receipt", modelMap);
	  return modelAndView;
	}

}
