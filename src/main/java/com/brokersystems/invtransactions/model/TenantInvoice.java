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
import javax.xml.bind.annotation.XmlTransient;

import com.brokersystems.setups.model.AccountTypes;
import com.brokersystems.setups.model.AuditBaseEntity;
import com.brokersystems.setups.model.Currencies;
import com.brokersystems.setups.model.OrgBranch;
import com.brokersystems.setups.model.OrgRegions;
import com.brokersystems.setups.model.PaymentModes;
import com.brokersystems.setups.model.TenantDef;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tenant_invoices")
public class TenantInvoice extends AuditBaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="invoice_id")
	private Long invoiceId;
	
	@Column(name="invoice_number",nullable=false)
	private String invoiceNumber;
	
	@Column(name="invoice_dt",nullable=false)
	private Date invoiceDate;
	
	@Column(name="invoice_wef_date", nullable=false)
	private Date wefDate;
	
	@Column(name="invoice_wet_date",nullable=false)
	private Date wetDate;
	
	@Column(name="invoice_frequency",nullable=false)
	private String frequency;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_ten_id")
	private TenantDef tenant;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_branch_id")
	private OrgBranch branch;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_pmode_id")
	private PaymentModes paymentMode;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="invoice_curr_id")
	private Currencies transCurrency;
	
	@Column(name="invoice_amount")
	private BigDecimal invAmount;
	
	@Column(name="invoice_tax_amount")
	private BigDecimal taxAmount;
	
	@Column(name="invoice_net_amount")
	private BigDecimal netAmount;
	
	@Column(name="invoice_status")
	private String status;
	
	@JsonIgnore
	@OneToMany(mappedBy="invoice")
	private List<TenantInvoiceDetails> invDetails;

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
	
	
	
}
