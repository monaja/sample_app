package com.brokersystems.setups.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="regions")
public class OrgRegions implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="reg_code")
	private Long regCode;
	
	@Column(name="sht_desc")
	private String ShtDesc;
	
	@Column(name="reg_desc")
	private String regDesc;
	
	
	@Column(name="wef")
	private String regWef;
	
	
	@Column(name="wet")
	private String regWet;
	
	@XmlTransient
	 @JsonIgnore
	@ManyToOne
	@JoinColumn(name="reg_org_code")
	private Organization organization;
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="region")
	private List<OrgBranch> orgBranches;
	

	public Long getRegCode() {
		return regCode;
	}

	public void setRegCode(Long regCode) {
		this.regCode = regCode;
	}

	public String getShtDesc() {
		return ShtDesc;
	}

	public void setShtDesc(String shtDesc) {
		ShtDesc = shtDesc;
	}

	public String getRegDesc() {
		return regDesc;
	}

	public void setRegDesc(String regDesc) {
		this.regDesc = regDesc;
	}

	public String getRegWef() {
		return regWef;
	}

	public void setRegWef(String regWef) {
		this.regWef = regWef;
	}

	public String getRegWet() {
		return regWet;
	}

	public void setRegWet(String regWet) {
		this.regWet = regWet;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public List<OrgBranch> getOrgBranches() {
		return orgBranches;
	}

	public void setOrgBranches(List<OrgBranch> orgBranches) {
		this.orgBranches = orgBranches;
	}
	
	
	

}
