package com.brokersystems.invtransactions.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TenantInvoiceBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private final String invoiceNumber;
	private final Long invoiceId;
	private final String invAmount;
	private final String taxAmount;
	private final String netAmount;
	private final String tenantName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private final Date transDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private final Date wefDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private final Date wetDate;
	private final String frequency;
	private final String status;
	private final Long tenantId;
	private  Currencies currency;
	private OrgBranch branch;
	private PaymentModes paymentMode;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private final Date renewalDate;
	private final List<TenantInvoiceDetailsBean> invoiceDetails;
	
	
	
	public TenantInvoiceBean(String invoiceNumber, Long invoiceId, String invAmount, String taxAmount,
			String netAmount,String tenantName,Date transDate,
			Date wefDate,Date wetDate,String frequency,String status,Long tenantId,Currencies currency,
			OrgBranch branch,PaymentModes paymentMode,Date renewalDate,List<TenantInvoiceDetailsBean> invoiceDetails) {
		this.invoiceNumber = invoiceNumber;
		this.invoiceId = invoiceId;
		this.invAmount = invAmount;
		this.taxAmount = taxAmount;
		this.netAmount = netAmount;
		this.tenantName = tenantName;
		this.invoiceDetails = invoiceDetails;
		this.transDate = transDate;
		this.wefDate = wefDate;
		this.wetDate = wetDate;
		this.frequency = frequency;
		this.status = status;
		this.tenantId = tenantId;
		this.currency = currency;
		this.branch = branch;
		this.paymentMode =paymentMode;
		this.renewalDate = renewalDate;
	}
	
	public TenantInvoiceBean(String invoiceNumber, Long invoiceId, String invAmount, String taxAmount,
			String netAmount,String tenantName,Date renewalDate,List<TenantInvoiceDetailsBean> invoiceDetails) {
		this.invoiceNumber = invoiceNumber;
		this.invoiceId = invoiceId;
		this.invAmount = invAmount;
		this.taxAmount = taxAmount;
		this.netAmount = netAmount;
		this.tenantName = tenantName;
		this.invoiceDetails = invoiceDetails;
		this.transDate = null;
		this.wefDate = null;
		this.wetDate = null;
		this.frequency = null;
		this.status = null;
		this.tenantId =null;
		this.renewalDate = renewalDate;
	}



	public String getInvoiceNumber() {
		return invoiceNumber;
	}



	public Long getInvoiceId() {
		return invoiceId;
	}



	public String getInvAmount() {
		return invAmount;
	}



	public String getTaxAmount() {
		return taxAmount;
	}



	public String getNetAmount() {
		return netAmount;
	}



	public String getTenantName() {
		return tenantName;
	}



	public List<TenantInvoiceDetailsBean> getInvoiceDetails() {
		return invoiceDetails;
	}



	public Date getTransDate() {
		return transDate;
	}



	public Date getWefDate() {
		return wefDate;
	}



	public Date getWetDate() {
		return wetDate;
	}



	public String getFrequency() {
		return frequency;
	}



	public String getStatus() {
		return status;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public Currencies getCurrency() {
		return currency;
	}

	public OrgBranch getBranch() {
		return branch;
	}

	public PaymentModes getPaymentMode() {
		return paymentMode;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}
	
	

}
