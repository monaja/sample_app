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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import com.brokersystems.setups.model.AuditBaseEntity;
import com.brokersystems.setups.model.RateTypes;
import com.brokersystems.setups.model.RentalUnitCharges;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tenant_invoice_details")
public class TenantInvoiceDetails extends AuditBaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="inv_det_id")
	private Long detailId;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="inv_det_charge_id")
	private RentalUnitCharges charge;
	
	@Transient
	private Long chargeId;
	
	
	@XmlTransient
	// @JsonIgnore
	@ManyToOne
	@JoinColumn(name="inv_det_rate_type",nullable=false)
	private RateTypes rateType;
	
	@Transient
	private Long rateTyoeId;
	
	@Column(name="inv_det_amount", nullable=false)
	private BigDecimal amount;
	
	@Column(name="inv_det_net_amount", nullable=false)
	private BigDecimal netAmount;
	
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="inv_ten_code")
	private TenantInvoice invoice;

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public RentalUnitCharges getCharge() {
		return charge;
	}

	public void setCharge(RentalUnitCharges charge) {
		this.charge = charge;
	}

	public TenantInvoice getInvoice() {
		return invoice;
	}

	public void setInvoice(TenantInvoice invoice) {
		this.invoice = invoice;
	}

	public RateTypes getRateType() {
		return rateType;
	}

	public void setRateType(RateTypes rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public Long getRateTyoeId() {
		return rateTyoeId;
	}

	public void setRateTyoeId(Long rateTyoeId) {
		this.rateTyoeId = rateTyoeId;
	}
	
	
	

}
