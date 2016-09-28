package com.brokersystems.setups.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Rental_charge_groups")
public class ChargeRatesGroup extends AuditBaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="group_id")
	private Long chargeId;
	
	@Column(name="group_sht_desc")
	private String shortDesc;
	
	@Column(name="group_name")
	private String groupName;
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="group")
	private List<RentalUnitCharges> unitChages;

	public Long getChargeId() {
		return chargeId;
	}

	public void setChargeId(Long chargeId) {
		this.chargeId = chargeId;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<RentalUnitCharges> getUnitChages() {
		return unitChages;
	}

	public void setUnitChages(List<RentalUnitCharges> unitChages) {
		this.unitChages = unitChages;
	}
	
	

}
