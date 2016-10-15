package com.brokersystems.utilities.service;

import java.math.BigDecimal;
import java.util.Date;

import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.Transactions;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.TenantDef;

public interface UtilitiesService {
	
	public int getCurrencyFractionUnits();
	
	public void allocateReceipt(Long receiptId) throws BadRequestException;
	
	void createRenewal(Long tenId,Date wefDate,Date wetDate,String invoiceNumber) throws BadRequestException;
	
	void authorizeRenewal(Long tenId, BigDecimal rctAmount,Transactions receipt,String invoiceNumber) throws BadRequestException;
	
	void createSettlements(TenantInvoice invoice,Transactions debit,Transactions credit,BigDecimal amount) throws BadRequestException;
	
     Transactions createTransaction(String refNo, TenantDef tenant,BigDecimal rctAmt,BigDecimal rctdAmt,Currencies currency) throws BadRequestException;
}
