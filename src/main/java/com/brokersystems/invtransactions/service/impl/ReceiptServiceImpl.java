package com.brokersystems.invtransactions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.brokersystems.invtransactions.model.QReceiptTrans;
import com.brokersystems.invtransactions.model.QTransactions;
import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.invtransactions.model.Transactions;
import com.brokersystems.invtransactions.repository.ReceiptRepository;
import com.brokersystems.invtransactions.repository.TransactionRepository;
import com.brokersystems.invtransactions.service.ReceiptService;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.setups.model.QTenantDef;
import com.mysema.query.types.Predicate;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	
	@Autowired
	private ReceiptRepository receiptRepo;
	
	@Autowired
	private TransactionRepository transRepo;

	@Override
	public DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException {
		Page<ReceiptTrans> page = receiptRepo.findAll(request.searchPredicate(QReceiptTrans.receiptTrans), request);
		return new DataTablesResult(request, page);
	}

	@Override
	public DataTablesResult<Transactions> findReceiptTransactions(DataTablesRequest request, String tenantFname,
			String otherName, String invoiceNumber) throws IllegalAccessException {
		QTenantDef tenant = QTransactions.transactions.tenant;
		Predicate pred =QTransactions.transactions.transDC.eq("D");//.and(tenant.fname.startsWithIgnoreCase(tenantFname).or(tenant.otherNames.startsWithIgnoreCase(otherName)).or(QTransactions.transactions.refno.eq(invoiceNumber)));
		Page<Transactions> page = transRepo.findAll(pred, request);
		return new DataTablesResult(request, page);
	}

}
