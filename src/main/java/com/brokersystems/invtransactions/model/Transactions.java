package com.brokersystems.invtransactions.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.TenantDef;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="sys_transactions")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="trans_id")
	private Long transId;
	
	@Column(name="trans_ref_no",nullable=false)
	private String refno;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="trans_ten_id",nullable=false)
	private TenantDef tenant;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="trans_currency")
	private Currencies transCurrency;
	
	@Column(name="trans_dc",nullable=false,length=1)
	private String transDC;
	
	@Column(name="trans_type",nullable=false)
	private String transtype;
	
	@Column(name="trans_amount",nullable=false)
	private BigDecimal transAmount;
	
	@Column(name="trans_balance",nullable=false)
	private BigDecimal transBalance;
	
	@Column(name="trans_taxes",nullable=false)
	private BigDecimal transTaxes;
	
	@Column(name="trans_commission")
	private BigDecimal transCommission;
	
	@Column(name="trans_net_amt",nullable=false)
	private BigDecimal transNetAmt;
	
	@Column(name="trans_settled_amt",nullable=false)
	private BigDecimal transSettledAmt;
	
	
	@Column(name="trans_authorized")
	private String authorized;
	
	@Column(name="trans_authorized_by")
	private String authoriedBy;
	
	@Column(name="trans_dt")
	private Date transDate;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="trans_invoice")
	private TenantInvoice invoice;
	

	public Long getTransId() {
		return transId;
	}

	public void setTransId(Long transId) {
		this.transId = transId;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
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

	public String getTransDC() {
		return transDC;
	}

	public void setTransDC(String transDC) {
		this.transDC = transDC;
	}

	public String getTranstype() {
		return transtype;
	}

	public void setTranstype(String transtype) {
		this.transtype = transtype;
	}

	public BigDecimal getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public BigDecimal getTransBalance() {
		return transBalance;
	}

	public void setTransBalance(BigDecimal transBalance) {
		this.transBalance = transBalance;
	}

	public BigDecimal getTransTaxes() {
		return transTaxes;
	}

	public void setTransTaxes(BigDecimal transTaxes) {
		this.transTaxes = transTaxes;
	}

	public BigDecimal getTransCommission() {
		return transCommission;
	}

	public void setTransCommission(BigDecimal transCommission) {
		this.transCommission = transCommission;
	}

	public BigDecimal getTransNetAmt() {
		return transNetAmt;
	}

	public void setTransNetAmt(BigDecimal transNetAmt) {
		this.transNetAmt = transNetAmt;
	}

	public String getAuthorized() {
		return authorized;
	}

	public void setAuthorized(String authorized) {
		this.authorized = authorized;
	}

	public String getAuthoriedBy() {
		return authoriedBy;
	}

	public void setAuthoriedBy(String authoriedBy) {
		this.authoriedBy = authoriedBy;
	}

	public BigDecimal getTransSettledAmt() {
		return transSettledAmt;
	}

	public void setTransSettledAmt(BigDecimal transSettledAmt) {
		this.transSettledAmt = transSettledAmt;
	}

	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public TenantInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(TenantInvoice invoice) {
		this.invoice = invoice;
	}
	
	
	
}
