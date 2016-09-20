package com.brokersystems.invtransactions.service.impl;

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
import com.brokersystems.setup.repository.CurrencyRepository;
import com.brokersystems.setup.repository.PaymentModeRepo;
import com.brokersystems.setup.repository.TenantAllocRepo;
import com.brokersystems.setup.repository.TenantRepository;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.QCurrencies;
import com.brokersystems.setups.model.QOrgBranch;
import com.brokersystems.setups.model.QPaymentModes;
import com.brokersystems.setups.model.QTenantDef;
import com.brokersystems.setups.model.TenantDef;
import com.mysema.query.types.Predicate;

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

	@Override
	public DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request) throws IllegalAccessException {
		Page<TenantInvoice> page = invoiceRepo.findAll(request.searchPredicate(QTenantInvoice.tenantInvoice), request);
	    return new DataTablesResult(request, page);
	}

	@Override
	public Page<TenantDef> findActiveTenants(String paramString, Pageable paramPageable) {
		Predicate pred = QTenantDef.tenantDef.status.eq("A");
		if (paramString == null || StringUtils.isBlank(paramString)) {
			pred = QTenantDef.tenantDef.isNotNull();
		} else {
			pred = QTenantDef.tenantDef.fname.containsIgnoreCase(paramString).or(QTenantDef.tenantDef.otherNames.containsIgnoreCase(paramString)).or(QTenantDef.tenantDef.tenantNumber.containsIgnoreCase(paramString));
		}
		return tenantRepo.findAll(pred, paramPageable);
	}

	@Override
	public Page<Currencies> findCurrencyForSelect(String paramString, Pageable paramPageable) {
		Predicate pred = null;
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

}
