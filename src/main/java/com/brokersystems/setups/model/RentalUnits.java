package com.brokersystems.setups.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="rental_units")
public class RentalUnits {
	
	public RentalUnits() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ren_id")
	private Long renId;
	
	@Column(name="ren_unit_name",nullable=false)
	private String unitName;
	
	@XmlTransient
	// @JsonIgnore
	@OneToOne
	@JoinColumn(name="ren_unit_type",nullable=false)
	private UnitTypes unitType;
	
	@XmlTransient
	// @JsonIgnore
	@OneToOne
	@JoinColumn(name="ren_rate_type",nullable=false)
	private RateTypes rateType;
	
	@Column(name="ren_rate_amount",nullable=false)
	private BigDecimal rateAmount;
	
	@Column(name="ren_frequency",nullable=false)
	private String frequency;
	
	@Column(name="ren_tax_appl")
	private String taxApplicable;
	
	@Column(name="ren_tax_type")
	private String taxType;
	
	@Column(name="ren_tax_value",nullable=false)
	private BigDecimal taxValue;
	
	@Column(name="ren_wef",nullable=false)
	private Date wef;
	
	@Column(name="ren_wet")
	private Date wet;
	
	@XmlTransient
	 @JsonIgnore
	@ManyToOne
	@JoinColumn(name="ren_rental_code",nullable=false)
	private RentalStructure rentalStruct;

	public Long getRenId() {
		return renId;
	}

	public void setRenId(Long renId) {
		this.renId = renId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public UnitTypes getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitTypes unitType) {
		this.unitType = unitType;
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
