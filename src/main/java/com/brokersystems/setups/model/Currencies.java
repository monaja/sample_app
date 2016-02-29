package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
@Table(name="currency")
public class Currencies implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cur_code")
	private Long curCode;

	@Column(name="cur_iso_code")
	private String curIsoCode;

	@Column(name="cur_name")
	private String curName;

	//bi-directional many-to-one association to Organization
	 @XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="currency")
	private List<Organization> organizations;

	public Currencies() {
	}

	public Long getCurCode() {
		return this.curCode;
	}

	public void setCurCode(Long curCode) {
		this.curCode = curCode;
	}

	public String getCurIsoCode() {
		return this.curIsoCode;
	}

	public void setCurIsoCode(String curIsoCode) {
		this.curIsoCode = curIsoCode;
	}

	public String getCurName() {
		return this.curName;
	}

	public void setCurName(String curName) {
		this.curName = curName;
	}

	public List<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public Organization addOrganization(Organization organization) {
		getOrganizations().add(organization);
		organization.setCurrency(this);

		return organization;
	}

	public Organization removeOrganization(Organization organization) {
		getOrganizations().remove(organization);
		organization.setCurrency(null);

		return organization;
	}

}