package com.brokersystems.invtransactions.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;

import com.brokersystems.setups.model.AccountTypes;
import com.brokersystems.setups.model.AuditBaseEntity;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.TenantDef;
import com.brokersystems.setups.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tenant_invoices")
public class TenantInvoice extends AuditBaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invoice_id")
	private Long invoiceId;
	
	@Column(name="invoice_number",nullable=false,unique=true)
	private String invoiceNumber;
	
	@Column(name="invoice_dt",nullable=false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date invoiceDate;
	
	@Column(name="invoice_wef_date", nullable=false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date wefDate;
	
	@Column(name="invoice_wet_date",nullable=false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date wetDate;
	
	@Column(name="invoice_ren_date",nullable=false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date renewalDate;
	
	@Column(name="invoice_frequency",nullable=false)
	private String frequency;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_ten_id")
	private TenantDef tenant;
	
	@Transient
	private Long tenantId;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_branch_id")
	private OrgBranch branch;
	
	@Transient
	private Long branchId;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_pmode_id")
	private PaymentModes paymentMode;
	
	@Transient
	private Long payId;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_curr_id")
	private Currencies transCurrency;
	
	@Transient
	private Long currCode;
	
	@Column(name="invoice_amount")
	private BigDecimal invAmount;
	
	@Column(name="invoice_tax_amount")
	private BigDecimal taxAmount;
	
	@Column(name="invoice_net_amount")
	private BigDecimal netAmount;
	
	@Column(name="invoice_status")
	private String status;
	
	@Column(name="invoice_trans_type")
	private String transType;
	
	@Column(name="invoice_prev_invoice")
	private TenantInvoice previousTrans;
	
	@Column(name="invoice_current_status")
	private String currentStatus;
	
	@JsonIgnore
	@OneToMany(mappedBy="invoice")
	private List<TenantInvoiceDetails> invDetails;
	
	@Transient
	private List<TenantInvoiceDetails> details;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_auth_user")
	private User authBy;

	public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Date getWetDate() {
		return wetDate;
	}

	public void setWetDate(Date wetDate) {
		this.wetDate = wetDate;
	}

	public Date getWefDate() {
		return wefDate;
	}

	public void setWefDate(Date wefDate) {
		this.wefDate = wefDate;
	}

	public TenantDef getTenant() {
		return tenant;
	}

	public void setTenant(TenantDef tenant) {
		this.tenant = tenant;
	}

	public OrgBranch getBranch() {
		return branch;
	}

	public void setBranch(OrgBranch branch) {
		this.branch = branch;
	}

	public PaymentModes getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentModes paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Currencies getTransCurrency() {
		return transCurrency;
	}

	public void setTransCurrency(Currencies transCurrency) {
		this.transCurrency = transCurrency;
	}

	public BigDecimal getInvAmount() {
		return invAmount;
	}

	public void setInvAmount(BigDecimal invAmount) {
		this.invAmount = invAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TenantInvoiceDetails> getInvDetails() {
		return invDetails;
	}

	public void setInvDetails(List<TenantInvoiceDetails> invDetails) {
		this.invDetails = invDetails;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public List<TenantInvoiceDetails> getDetails() {
		return details;
	}

	public void setDetails(List<TenantInvoiceDetails> details) {
		this.details = details;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	public Long getCurrCode() {
		return currCode;
	}

	public void setCurrCode(Long currCode) {
		this.currCode = currCode;
	}

	public User getAuthBy() {
		return authBy;
	}

	public void setAuthBy(User authBy) {
		this.authBy = authBy;
	}

	public Date getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(Date renewalDate) {
		this.renewalDate = renewalDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public TenantInvoice getPreviousTrans() {
		return previousTrans;
	}

	public void setPreviousTrans(TenantInvoice previousTrans) {
		this.previousTrans = previousTrans;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	
	
	
}
