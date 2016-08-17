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
	

}
