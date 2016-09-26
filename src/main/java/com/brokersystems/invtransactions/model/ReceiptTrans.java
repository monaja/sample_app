package com.brokersystems.invtransactions.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.model.User;

@Entity
@Table(name="sys_receipts")
public class ReceiptTrans {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="receipt_id")
	private Long receiptId;
	
	@Column(name="receipt_date")
	private Date receiptDate;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_user")
	private User receiptUser;
	
	@Column(name="receipt_amount")
	private BigDecimal receiptAmount;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_pmode_id")
	private PaymentModes paymentMode;
	
	@Column(name="receipt_paid_by")
	private String paidBy;
	
	@Column(name="receipt_doc_date")
	private Date documentDate;
	
	@Column(name="receipt_desc")
	private String receiptDesc;
	
	@Column(name="receipt_printed")
	private String printed;
	
	@Column(name="receipt_cancelled")
	private String cancelled;
	
	@Column(name="receipt_no")
	private String receiptNo;
	
	@Column(name="receipt_counter")
	private BigInteger counter;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_cancelled_by")
	private User cancelledBy;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_brn_code")
	private OrgBranch branch;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_ten_id")
	private TenantDef tenant;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_curr_id")
	private Currencies transCurrency;
	
	@Column(name="receipt_cleared")
	private String cleared;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="receipt_cleared_by")
	private User receiptClearedBy;
	
	@Column(name="receipt_cleared_date")
	private Date receiptClearedDate;

	public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public User getReceiptUser() {
		return receiptUser;
	}

	public void setReceiptUser(User receiptUser) {
		this.receiptUser = receiptUser;
	}

	public BigDecimal getReceiptAmount() {
		return receiptAmount;
	}

	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	public PaymentModes getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentModes paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public Date getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(Date documentDate) {
		this.documentDate = documentDate;
	}

	public String getReceiptDesc() {
		return receiptDesc;
	}

	public void setReceiptDesc(String receiptDesc) {
		this.receiptDesc = receiptDesc;
	}

	public String getPrinted() {
		return printed;
	}

	public void setPrinted(String printed) {
		this.printed = printed;
	}

	public String getCancelled() {
		return cancelled;
	}

	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public BigInteger getCounter() {
		return counter;
	}

	public void setCounter(BigInteger counter) {
		this.counter = counter;
	}

	public User getCancelledBy() {
		return cancelledBy;
	}

	public void setCancelledBy(User cancelledBy) {
		this.cancelledBy = cancelledBy;
	}

	public OrgBranch getBranch() {
		return branch;
	}

	public void setBranch(OrgBranch branch) {
		this.branch = branch;
	}

	public TenantDef getTenant() {
		return tenant;
	}

	public void setTenant(TenantDef tenant) {
		this.tenant = tenant;
	}

	public Currencies getTransCurrency() {
		return transCurrency;
	}

	public void setTransCurrency(Currencies transCurrency) {
		this.transCurrency = transCurrency;
	}

	public String getCleared() {
		return cleared;
	}

	public void setCleared(String cleared) {
		this.cleared = cleared;
	}

	public User getReceiptClearedBy() {
		return receiptClearedBy;
	}

	public void setReceiptClearedBy(User receiptClearedBy) {
		this.receiptClearedBy = receiptClearedBy;
	}

	public Date getReceiptClearedDate() {
		return receiptClearedDate;
	}

	public void setReceiptClearedDate(Date receiptClearedDate) {
		this.receiptClearedDate = receiptClearedDate;
	}
	
	
	

}
