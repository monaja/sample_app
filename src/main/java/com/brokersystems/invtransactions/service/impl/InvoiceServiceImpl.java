package com.brokersystems.invtransactions.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.brokersystems.invtransactions.model.QTenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.repository.InvoiceRepository;
import com.brokersystems.invtransactions.service.InvoiceService;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.PaymentModeRepo;
import com.brokersystems.setup.repository.RentalUnitChargeRepo;
import com.brokersystems.setup.repository.RentalUnitsRepository;
import com.brokersystems.setup.repository.TenantAllocRepo;
import com.brokersystems.setup.repository.TenantRepository;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgBranch;
import com.brokersystems.setups.model.QPaymentModes;
import com.brokersystems.setups.model.QRentalUnitCharges;
import com.brokersystems.setups.model.QTenantDef;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.RentalUnits;
import com.brokersystems.setups.model.TenantDef;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepo;
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	@Autowired
	private PaymentModeRepo paymentRepo;
	
	@Autowired
	private TenantRepository tenantRepo;
	
	@Autowired
	private RentalUnitsRepository unitsRepo;
	
	@Autowired
	private RentalUnitChargeRepo chargesRepo;
	

	@Override
	public DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request) throws IllegalAccessException {
		Page<TenantInvoice> page = invoiceRepo.findAll(request.searchPredicate(QTenantInvoice.tenantInvoice), request);
	    return new DataTablesResult(request, page);
	}

	@Override
	public Page<TenantDef> findActiveTenants(String paramString, Pageable paramPageable) {
		Predicate pred =null;
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QTenantDef.tenantDef.isNotNull().and(QTenantDef.tenantDef.status.eq("A"));
		} else {
			pred = QTenantDef.tenantDef.fname.containsIgnoreCase(paramString).and(QTenantDef.tenantDef.status.eq("A")).or(QTenantDef.tenantDef.otherNames.containsIgnoreCase(paramString)).or(QTenantDef.tenantDef.tenantNumber.containsIgnoreCase(paramString));
		}
		return tenantRepo.findAll(pred, paramPageable);
	}

	@Override
	public Page<Currencies> findCurrencyForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = QCurrencies.currencies.enabled.eq(true);
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QCurrencies.currencies.isNotNull();
		} else {
			pred = QCurrencies.currencies.curName.containsIgnoreCase(paramString);
		}
		return currencyRepo.findAll(pred, paramPageable);
	}

	@Override
	public Page<PaymentModes> findPaymentModesForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = null;
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QPaymentModes.paymentModes.isNotNull();
		} else {
			pred = QPaymentModes.paymentModes.pmDesc.containsIgnoreCase(paramString);
		}
		return paymentRepo.findAll(pred, paramPageable);
	}

	@Override
	public TenantInvoice createInvoice(TenantInvoice invoice)  throws BadRequestException {
		if(invoice.getTenant()==null){
			throw new BadRequestException("Tenant is Mandatory");
		}
		if(invoice.getBranch()==null){
			throw new BadRequestException("Branch is Mandatory");
		}
		if(invoice.getPaymentMode()==null){
			throw new BadRequestException("Payment Mode is Mandatory");
		}
		if(invoice.getTransCurrency()==null){
			throw new BadRequestException("Currency is Mandatory");
		}
		
		if(invoice.getInvoiceDate().after(new Date())){
			throw new BadRequestException("Invoice Date cannot be in future "+invoice.getInvoiceDate()+" now "+new Date()+" "+invoice.getWefDate()+" "+invoice.getWetDate());
		}
		
		if(invoice.getWefDate()==null){
			throw new BadRequestException("Invoice Date From is Mandatory");
		}
		final String invNumber =  String.format("%05d", invoiceRepo.count()+1);
		 invoice.setInvoiceNumber("INV"+invNumber);
		 invoice.setStatus("D");
		 TenantInvoice created = invoiceRepo.save(invoice);
		 return created;
		
	}

	@Override
	public TenantInvoice findByInvoiceId(Long invoiceId) throws BadRequestException {
		 Optional<TenantInvoice> invoice  = invoiceRepo.findByInvoiceId(invoiceId);
		 if(!invoice.isPresent()) throw new BadRequestException("The Invoice Does not Exist....");
		return invoice.get();
	}

	@Override
	public List<RentalUnitCharges> getActiveCharges(long unitCode,Date invoiceDate) throws BadRequestException {
		List<RentalUnitCharges> list = chargesRepo.getActiveUnitCharges(unitCode, invoiceDate);
		return list;
	}

}
