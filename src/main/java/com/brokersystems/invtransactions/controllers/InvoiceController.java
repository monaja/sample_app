package com.brokersystems.invtransactions.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.brokersystems.invtransactions.model.InvoiceReportModel;
import com.brokersystems.invtransactions.model.RevisionForm;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoiceBean;
import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.invtransactions.service.InvoiceService;
import com.brokersystems.server.datatables.DataTable;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.exception.InvoiceRevisionException;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.ModelHelperForm;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.TenAllocations;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.service.SetupsService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping({ "/protected/transactions/invoices" })
public class InvoiceController {

	@Autowired
	private InvoiceService invService;

	@Autowired
	private SetupsService service;

	@Autowired
	private DataSource datasource;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private ResourceLoader resourceLoader;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "invlist", method = RequestMethod.GET)
	public String invoiceList(Model model) {
		return "invoices";
	}

	@RequestMapping(value = "invform", method = RequestMethod.GET)
	public String invoicehome(Model model) {
		model.addAttribute("invoiceCode", -2000);
		return "invoiceform";
	}

	@RequestMapping(value = "reviseinvoice", method = RequestMethod.GET)
	public String reviseinvoice(Model model) {
		// model.addAttribute("invoiceCode", -2000);
		return "reviseinvoice";
	}

	@ExceptionHandler(InvoiceRevisionException.class)
	public ModelAndView getSuperheroesUnavailable(InvoiceRevisionException ex) {
		return new ModelAndView("reviseinvoice", "error", ex.getMessage());
	}

	@RequestMapping(value = "/editInvoice", method = RequestMethod.POST)
	public String editRentalForm(@Valid @ModelAttribute ModelHelperForm helperForm, Model model,
			HttpServletRequest request) {
		model.addAttribute("invoiceCode", helperForm.getId());
		request.getSession().setAttribute("invoiceTransNo", helperForm.getId());
		return "invoiceform";
	}

	@RequestMapping(value = { "invoices" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantInvoice> getAllInvoices(@DataTable DataTablesRequest pageable)
			throws IllegalAccessException {
		return invService.findAllInvoices(pageable);
	}

	@RequestMapping(value = { "paymentmodes" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<PaymentModes> paymentModes(@RequestParam(value = "term", required = false) String term,
			Pageable pageable) throws IllegalAccessException {
		return invService.findPaymentModesForSelect(term, pageable);
	}

	@RequestMapping(value = { "activetenants" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<TenantDef> findActiveTenants(@RequestParam(value = "term", required = false) String term,
			Pageable pageable) throws IllegalAccessException {
		return invService.findActiveTenants(term, pageable);
	}

	@RequestMapping(value = { "currencies" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Page<Currencies> allCurrencies(@RequestParam(value = "term", required = false) String term,
			Pageable pageable) throws IllegalAccessException {
		return invService.findCurrencyForSelect(term, pageable);
	}

	@RequestMapping(value = { "createInvoice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public ResponseEntity<TenantInvoiceBean> createTenant(@RequestBody TenantInvoice invoice,
			HttpServletRequest request) throws BadRequestException {
		TenantInvoiceBean created = invService.createInvoice(invoice);
		request.getSession().setAttribute("invoiceTransNo", created.getInvoiceId());
		return new ResponseEntity<TenantInvoiceBean>(created, HttpStatus.OK);
	}

	@RequestMapping(value = { "getAllocation/{tenId}" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ResponseEntity<TenAllocations> getActiveAllocation(@PathVariable Long tenId) throws BadRequestException {
		TenAllocations created = service.getActiveAllocation(tenId);
		return new ResponseEntity<TenAllocations>(created, HttpStatus.OK);
	}

	@RequestMapping(value = { "getActiveCharges" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ResponseEntity<List<RentalUnitCharges>> getCurrentUnitCharges(
			@RequestParam(value = "unitCode", required = false) Long unitCode,
			@RequestParam(value = "invoiceDate", required = false) Date invoiceDate) throws BadRequestException {
		List<RentalUnitCharges> charges = invService.getActiveCharges(unitCode, invoiceDate);
		return new ResponseEntity<List<RentalUnitCharges>>(charges, HttpStatus.OK);
	}

	@RequestMapping(value = { "invoice/{invoiceId}" }, method = { RequestMethod.GET })
	@ResponseBody
	public TenantInvoiceBean queryInvoiceDetails(@PathVariable Long invoiceId) throws BadRequestException {
		TenantInvoiceBean invoice = invService.queryInvoice(invoiceId);
		return invoice;
	}

	@RequestMapping(value = { "getNewCharges" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public ResponseEntity<List<RentalUnitCharges>> getNewCharges(
			@RequestParam(value = "unitCode", required = false) Long unitCode,
			@RequestParam(value = "invoiceDate", required = false) Date invoiceDate,
			@RequestParam(value = "tencode", required = false) Long tencode) throws BadRequestException {
		List<RentalUnitCharges> charges = invService.getNewCharges(unitCode, invoiceDate, tencode);
		return new ResponseEntity<List<RentalUnitCharges>>(charges, HttpStatus.OK);
	}

	@RequestMapping(value = { "confirmInvoice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void authInvoice(@RequestParam(value = "invoiceCode", required = false) Long invoiceCode)
			throws BadRequestException {
		invService.authorizeInvoice(invoiceCode);
	}

	@RequestMapping(value = { "getInvDetails/{invoiceCode}" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantInvoiceDetails> getInvoiceDetails(@DataTable DataTablesRequest pageable,
			@PathVariable Long invoiceCode) throws IllegalAccessException {
		return invService.findInvoiceDetails(pageable, invoiceCode);
	}

	@RequestMapping(value = { "revisionInvoices" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantInvoice> getRevisionInvoices(@DataTable DataTablesRequest pageable,
			@RequestParam(value = "invoiceNumber", required = false) String invoiceNumber,
			@RequestParam(value = "firstName", required = false) String firstName,
			@RequestParam(value = "otherNames", required = false) String otherNames) throws IllegalAccessException {
		return invService.findActiveInvoices(pageable, invoiceNumber, firstName, otherNames);
	}

	@RequestMapping(value = { "reviseInvoice" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.POST })
	public String reviseInvoice(@Valid @ModelAttribute RevisionForm revision, Model model)
			throws InvoiceRevisionException, InvocationTargetException, IllegalAccessException {
		Long id = null;
		if ("CO".equalsIgnoreCase(revision.getRevisionType())) {
			id = invService.contraInvoice(revision);
		} else if ("CN".equalsIgnoreCase(revision.getRevisionType())
				|| "RV".equalsIgnoreCase(revision.getRevisionType())
				|| "NT".equalsIgnoreCase(revision.getRevisionType())) {
			id = invService.reviseTransaction(revision);
		} else {
			throw new InvoiceRevisionException("Transaction Type not supported");
		}
		model.addAttribute("invoiceCode", id);
		return "invoiceform";
	}

	@RequestMapping(value = { "unauthinvoices" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesResult<TenantInvoice> getUnauthInvoices(@DataTable DataTablesRequest pageable,
			@RequestParam(value = "invoiceNumber", required = false) String invoiceNumber)
			throws IllegalAccessException {
		return invService.findUnauthorisedInvoices(pageable, invoiceNumber);
	}

	@RequestMapping(value = { "countUnauthInvoices" }, method = { RequestMethod.GET })
	@ResponseBody
	public Long countUnauthInvoices(@RequestParam(value = "invoiceNumber", required = false) String invoiceNumber)
			throws IllegalAccessException {
		return invService.countUnauthTransaction(invoiceNumber);
	}

	@RequestMapping(value = { "deleteInvoice" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteInvoice(@RequestParam(value = "invoiceCode", required = false) Long invoiceCode)
			throws BadRequestException {
		invService.deleteInvoice(invoiceCode);
	}

	@RequestMapping(value = "invoice_rpt", method = RequestMethod.GET)
	public ModelAndView invoiceRpt(ModelMap modelMap, HttpServletRequest request, ModelAndView modelAndView)
			throws BadRequestException {

		Long invoiceCode = (Long) request.getSession().getAttribute("invoiceTransNo");
		TenantInvoice invoice = invService.findByInvoiceId(invoiceCode);

		modelMap.put("datasource", datasource);
		modelMap.put("format", "pdf");
		modelMap.put("invoicetrans", invoiceCode);
		if (invoice.getStatus() == null || invoice.getStatus().equals("D")) {
			modelAndView = new ModelAndView("rpt_draftinvoice", modelMap);
		} else
			modelAndView = new ModelAndView("rpt_invoice", modelMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "tenant_stmt", method = RequestMethod.GET)
	public ModelAndView tenantStmt(ModelMap modelMap, HttpServletRequest request, ModelAndView modelAndView)
			throws BadRequestException {

		Long invoiceCode = (Long) request.getSession().getAttribute("invoiceTransNo");
		TenantInvoice invoice = invService.findByInvoiceId(invoiceCode);
		modelMap.put("datasource", datasource);
		modelMap.put("format", "pdf");
		modelMap.put("tenantId", invoice.getTenant().getTenId());
	   modelAndView = new ModelAndView("rpt_ten_stmt", modelMap);
		
		return modelAndView;
	}
	
	@RequestMapping(value = { "sendEmail" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void sendEmail(HttpServletRequest request)
			throws BadRequestException, MessagingException, SQLException, JRException, IOException {
		Connection conn = datasource.getConnection();
		HashMap<String,Object> parameters=new HashMap<String,Object>();
		Long invoiceCode = (Long) request.getSession().getAttribute("invoiceTransNo");
		TenantInvoice invoice = invService.findByInvoiceId(invoiceCode);
		parameters.put("invoicetrans", invoiceCode);
		Resource resource = resourceLoader.getResource("classpath:/reports/rpt_invoice.jasper");
		if (invoice.getStatus() == null || invoice.getStatus().equals("D")) {
			resource = resourceLoader.getResource("classpath:/reports/rpt_draftinvoice.jasper");
		}
		else
			resource = resourceLoader.getResource("classpath:/reports/rpt_invoice.jasper");
		InputStream inputStream =resource.getInputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,conn);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
		javax.activation.DataSource aAttachment =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("pmugenya@gmail.com");
		helper.setTo("pmugenya@gmail.com");
		helper.setSubject("Test mail");
		helper.addAttachment("invoice.pdf", aAttachment);
		helper.setText("Attached is your invoice...",true);
		mailSender.send(message);
	}


}
