package com.brokersystems.invtransactions.model;

import java.util.Date;

public class RevisionForm {
	
	private Long invoiceId;
	
	private Date effectiveDate;
	
	private String revisionType;

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getRevisionType() {
		return revisionType;
	}

	public void setRevisionType(String revisionType) {
		this.revisionType = revisionType;
	}
	
	

}
