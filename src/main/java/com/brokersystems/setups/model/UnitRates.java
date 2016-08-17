package com.brokersystems.setups.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="unit_rates")
public class UnitRates {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ur_id")
	private Long renId;
	

	@XmlTransient
	// @JsonIgnore
	@OneToOne
	@JoinColumn(name="ur_rate_type",nullable=false)
	private RateTypes rateType;
	
	@Column(name="ur_rate_amount",nullable=false)
	private BigDecimal rateAmount;
	
	@Column(name="ur_frequency",nullable=false)
	private String frequency;
	
	@Column(name="ur_tax_appl")
	private String taxApplicable;
	
	@Column(name="ur_tax_type")
	private String taxType;
	
	@Column(name="ur_tax_value",nullable=false)
	private BigDecimal taxValue;
	
	@Column(name="ur_wef",nullable=false)
	private Date wef;
	
	@Column(name="ur_wet")
	private Date wet;

	public Long getRenId() {
		return renId;
	}

	public void setRenId(Long renId) {
		this.renId = renId;
	}

	public RateTypes getRateType() {
		return rateType;
	}

	public void setRateType(RateTypes rateType) {
		this.rateType = rateType;
	}

	public BigDecimal getRateAmount() {
		return rateAmount;
	}

	public void setRateAmount(BigDecimal rateAmount) {
		this.rateAmount = rateAmount;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getTaxApplicable() {
		return taxApplicable;
	}

	public void setTaxApplicable(String taxApplicable) {
		this.taxApplicable = taxApplicable;
	}

	public String getTaxType() {
		return taxType;
	}

	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}

	public BigDecimal getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(BigDecimal taxValue) {
		this.taxValue = taxValue;
	}

	public Date getWef() {
		return wef;
	}

	public void setWef(Date wef) {
		this.wef = wef;
	}

	public Date getWet() {
		return wet;
	}

	public void setWet(Date wet) {
		this.wet = wet;
	}
	
	

}
