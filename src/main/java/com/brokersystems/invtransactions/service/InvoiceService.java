package com.brokersystems.invtransactions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.TenantDef;

public interface InvoiceService {
	
	DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request)  throws IllegalAccessException;
	
	public Page<TenantDef> findActiveTenants(String paramString, Pageable paramPageable);
	
	public Page<Currencies> findCurrencyForSelect(String paramString, Pageable paramPageable);
	
	public Page<PaymentModes> findPaymentModesForSelect(String paramString, Pageable paramPageable);

}
