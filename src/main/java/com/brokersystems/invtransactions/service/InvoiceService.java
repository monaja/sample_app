package com.brokersystems.invtransactions.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.brokersystems.invtransactions.model.RevisionForm;
import com.brokersystems.invtransactions.model.TenantInvoice;
import com.brokersystems.invtransactions.model.TenantInvoiceBean;
import com.brokersystems.invtransactions.model.TenantInvoiceDetails;
import com.brokersystems.server.datatables.DataTablesRequest;
import com.brokersystems.server.datatables.DataTablesResult;
import com.brokersystems.server.exception.BadRequestException;
import com.brokersystems.server.exception.InvoiceRevisionException;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.brokersystems.setups.model.TenantDef;

public interface InvoiceService {

	DataTablesResult<TenantInvoice> findAllInvoices(DataTablesRequest request) throws IllegalAccessException;

	public Page<TenantDef> findActiveTenants(String paramString, Pageable paramPageable);

	public Page<Currencies> findCurrencyForSelect(String paramString, Pageable paramPageable);

	public Page<PaymentModes> findPaymentModesForSelect(String paramString, Pageable paramPageable);

	TenantInvoiceBean createInvoice(TenantInvoice invoice) throws BadRequestException;

	TenantInvoice findByInvoiceId(Long invoidId) throws BadRequestException;

	public List<RentalUnitCharges> getActiveCharges(long unitCode, Date invoiceDate) throws BadRequestException;
	
	public List<RentalUnitCharges> getNewCharges(long unitCode, Date invoiceDate,long tencode) throws BadRequestException;

	TenantInvoiceBean queryInvoice(Long invoiceId) throws BadRequestException;

	public Long getCalculateRate(String frequency) throws BadRequestException;
	
	void authorizeInvoice(Long invoiceId) throws BadRequestException;
	
	DataTablesResult<TenantInvoiceDetails> findInvoiceDetails(DataTablesRequest request,Long invoiceCode) throws IllegalAccessException;
	
	public DataTablesResult<TenantInvoice> findActiveInvoices(DataTablesRequest request, String invoiceNumber,
			String firstName,String otherNames) throws IllegalAccessException;

	Long reviseTransaction(RevisionForm revisionForm)  throws InvoiceRevisionException;
	
	DataTablesResult<TenantInvoice> findUnauthorisedInvoices(DataTablesRequest request,String invoiceNumber) throws IllegalAccessException;
	
	Long countUnauthTransaction(String invoiceNumber);
	
	void deleteInvoice(Long invoiceId);
	
	Long contraInvoice(RevisionForm revisionForm)  throws InvoiceRevisionException;

}
