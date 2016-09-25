package com.brokersystems.invtransactions.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TenantInvoiceDetailsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long detailId;
	private final Long chargeId;
	private final Long rateTypeId;
	private final String rateType;
	private final BigDecimal amount;
	private final BigDecimal netAmount;
	
	
	
	
	public TenantInvoiceDetailsBean(Long detailId, Long chargeId, Long rateTypeId, String rateType, BigDecimal amount,
			BigDecimal netAmount) {
		this.detailId = detailId;
		this.chargeId = chargeId;
		this.rateTypeId = rateTypeId;
		this.rateType = rateType;
		this.amount = amount;
		this.netAmount = netAmount;
	}
	public Long getDetailId() {
		return detailId;
	}
	public Long getChargeId() {
		return chargeId;
	}
	public Long getRateTypeId() {
		return rateTypeId;
	}
	public String getRateType() {
		return rateType;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public BigDecimal getNetAmount() {
		return netAmount;
	}
	
	

}
