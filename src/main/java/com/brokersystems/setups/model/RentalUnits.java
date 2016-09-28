package com.brokersystems.setups.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="rental_units")
public class RentalUnits extends AuditBaseEntity {
	
	public RentalUnits() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ren_id")
	private Long renId;
	
	@Column(name="ren_unit_name",nullable=false,unique=true)
	private String unitName;
	
	@XmlTransient
	// @JsonIgnore
	@OneToOne
	@JoinColumn(name="ren_unit_type",nullable=false)
	private UnitTypes unitType;
	
	
	@XmlTransient
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ren_rental_code",nullable=false)
	private RentalStructure rentalStruct;
	
	@XmlTransient
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="ren_group_code",nullable=false)
	private ChargeRatesGroup chargeGroup;
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="renunits")
	private List<TenAllocations> allocations;

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

	public RentalStructure getRentalStruct() {
		return rentalStruct;
	}

	public void setRentalStruct(RentalStructure rentalStruct) {
		this.rentalStruct = rentalStruct;
	}


	public List<TenAllocations> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<TenAllocations> allocations) {
		this.allocations = allocations;
	}

	public ChargeRatesGroup getChargeGroup() {
		return chargeGroup;
	}

	public void setChargeGroup(ChargeRatesGroup chargeGroup) {
		this.chargeGroup = chargeGroup;
	}
	
	
	

}
