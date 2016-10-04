package com.brokersystems.invtransactions.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name="sys_receipt_dtls")
public class ReceiptTransDtls {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rect_id")
	private Long receiptId;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="rect_receipt_no")
	private ReceiptTrans receipt;
	
	@Column(name="rect_account")
	private String account;
	
	@Column(name="rect_rev_no")
	private String invoiceRevision;
	
	@Column(name="rect_amount")
	private BigDecimal rctAmount;
	
	@Column(name="rect_narration")
	private String narration;
	
	@Column(name="rect_allocated")
	private String allocated;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="rect_invoice_no")
	private TenantInvoice invoice;
	
	@Column(name="rect_dc", nullable=false)
	private String rctDC;
	
	@Column(name="rect_type")
	private String rctType;

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public ReceiptTrans getReceipt() {
		return receipt;
	}

	public void setReceipt(ReceiptTrans receipt) {
		this.receipt = receipt;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getInvoiceRevision() {
		return invoiceRevision;
	}

	public void setInvoiceRevision(String invoiceRevision) {
		this.invoiceRevision = invoiceRevision;
	}

	public BigDecimal getRctAmount() {
		return rctAmount;
	}

	public void setRctAmount(BigDecimal rctAmount) {
		this.rctAmount = rctAmount;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public TenantInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(TenantInvoice invoice) {
		this.invoice = invoice;
	}

	public String getRctDC() {
		return rctDC;
	}

	public void setRctDC(String rctDC) {
		this.rctDC = rctDC;
	}

	public String getRctType() {
		return rctType;
	}

	public void setRctType(String rctType) {
		this.rctType = rctType;
	}

	public String getAllocated() {
		return allocated;
	}

	public void setAllocated(String allocated) {
		this.allocated = allocated;
	}
	
}
