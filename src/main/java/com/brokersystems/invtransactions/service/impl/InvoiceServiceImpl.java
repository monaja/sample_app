package com.brokersystems.invtransactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.brokersystems.invtransactions.model.QTenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.repository.InvoiceRepository;
import com.brokersystems.invtransactions.service.InvoiceService;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepo;

	@Override
	public DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request) throws IllegalAccessException {
		Page<TenantInvoice> page = invoiceRepo.findAll(request.searchPredicate(QTenantInvoice.tenantInvoice), request);
	    return new DataTablesResult(request, page);
	}

}
