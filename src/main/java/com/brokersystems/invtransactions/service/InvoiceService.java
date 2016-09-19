package com.brokersystems.invtransactions.service;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

public interface InvoiceService {
	
	DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request)  throws IllegalAccessException;

}
