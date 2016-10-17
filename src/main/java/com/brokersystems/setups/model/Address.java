package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;



@Embeddable
public class Address extends AuditBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name="add_address")
	private String addAddress;

	@Column(name="add_zip_code")
	private String addZipCode;
	
	@Column(name="add_email_addr")
	private String emailAddress;

	//bi-directional many-to-one association to Town
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="town_code")
	private Town town;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="cou_code")
	private Country country;
	
//bi-directional many-to-one association to Country
	@ManyToOne
	@JsonManagedReference
	@JoinColumn(name="county_code")
	private County county;


	public Address() {
	}


	public String getAddAddress() {
		return this.addAddress;
	}

	public void setAddAddress(String addAddress) {
		this.addAddress = addAddress;
	}

	public String getAddZipCode() {
		return this.addZipCode;
	}

	public void setAddZipCode(String addZipCode) {
		this.addZipCode = addZipCode;
	}

	public Town getTown() {
		return this.town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


	public County getCounty() {
		return county;
	}


	public void setCounty(County county) {
		this.county = county;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}