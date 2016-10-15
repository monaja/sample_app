package com.brokersystems.invtransactions.service;

import java.math.BigDecimal;
import java.util.Date;

import com.brokersystems.invtransactions.model.ReceiptTrans;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.Transactions;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;

public interface ReceiptService {
	
	DataTablesResult<ReceiptTrans> findAllReceipts(DataTablesRequest request) throws IllegalAccessException;
	
	DataTablesResult<Transactions> findReceiptTransactions(DataTablesRequest request,String tenantFname,String otherName,String invoiceNumber) throws IllegalAccessException;

	Long createReceipt(ReceiptTrans receipt) throws BadRequestException;
	
	void markReceiptPrinted(Long receiptId) throws BadRequestException;
	
}
