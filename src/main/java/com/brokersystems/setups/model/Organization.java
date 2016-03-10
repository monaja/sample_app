package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the organization database table.
 * 
 */
@Entity
public class Organization implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="org_code")
	private Long orgCode;

	@Column(name="org_desc")
	private String orgDesc;

	@Column(name="org_fax")
	private String orgFax;

	@Lob
	@Column(name="org_logo")
	private byte[] orgLogo;

	@Column(name="org_mobile")
	private String orgMobile;
	
    
	@Column(name="org_name")
	private String orgName;

	@Column(name="org_phone")
	private String orgPhone;

	@Column(name="org_sht_desc")
	private String orgShtDesc;

	@Column(name="org_website")
	private String orgWebsite;
	
	@Transient
	MultipartFile file;
	
	@Transient
	private String formAction;
	

	

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="addAddress",column=@Column(name="org_address")),
		@AttributeOverride(name="addZipCode",column=@Column(name="org_zip_code"))
	})
	private Address address;

	//bi-directional many-to-one association to Currency
	 @XmlTransient
	 @JsonIgnore
	@ManyToOne
	@JoinColumn(name="org_cur_code")
	private Currencies currency;

	//bi-directional many-to-one association to SysLocale
	@ManyToOne
	@JoinColumn(name="org_locale_id")
	private SysLocale sysLocale;
	
//bi-directional many-to-one association to Bank
	@OneToMany(mappedBy="organization")
	private List<Bank> banks;

	//bi-directional many-to-one association to OrgBranch
	@OneToMany(mappedBy="organization")
	private List<OrgBranch> orgBranches;

	public Organization() {
	}

	public Long getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(Long orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgDesc() {
		return this.orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getOrgFax() {
		return this.orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	public byte[] getOrgLogo() {
		return this.orgLogo;
	}

	public void setOrgLogo(byte[] orgLogo) {
		this.orgLogo = orgLogo;
	}

	public String getOrgMobile() {
		return this.orgMobile;
	}

	public void setOrgMobile(String orgMobile) {
		this.orgMobile = orgMobile;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgPhone() {
		return this.orgPhone;
	}

	public void setOrgPhone(String orgPhone) {
		this.orgPhone = orgPhone;
	}

	public String getOrgShtDesc() {
		return this.orgShtDesc;
	}

	public void setOrgShtDesc(String orgShtDesc) {
		this.orgShtDesc = orgShtDesc;
	}

	public String getOrgWebsite() {
		return this.orgWebsite;
	}

	public void setOrgWebsite(String orgWebsite) {
		this.orgWebsite = orgWebsite;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Currencies getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currencies currency) {
		this.currency = currency;
	}

	public SysLocale getSysLocale() {
		return this.sysLocale;
	}

	public void setSysLocale(SysLocale sysLocale) {
		this.sysLocale = sysLocale;
	}

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}

	public List<OrgBranch> getOrgBranches() {
		return orgBranches;
	}

	public void setOrgBranches(List<OrgBranch> orgBranches) {
		this.orgBranches = orgBranches;
	}



	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public String getFormAction() {
		return formAction;
	}

	public void setFormAction(String formAction) {
		this.formAction = formAction;
	}


}