package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
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
	
	@Column(name="org_bank_name")
	private String bankName;
	
	@Column(name="org_acc_number")
	private String accountNumber;
	
	@Column(name="org_date_incorp")
	private Date dateIncorp;
	
	@Column(name="org_cert_number")
	private String certNumber;
	

	

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


	//bi-directional many-to-one association to OrgBranch
	@OneToMany(mappedBy="organization")
	private List<OrgRegions> orgRegions;

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

	public List<OrgRegions> getOrgRegions() {
		return orgRegions;
	}

	public void setOrgRegions(List<OrgRegions> orgRegions) {
		this.orgRegions = orgRegions;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getDateIncorp() {
		return dateIncorp;
	}

	public void setDateIncorp(Date dateIncorp) {
		this.dateIncorp = dateIncorp;
	}

	public String getCertNumber() {
		return certNumber;
	}

	public void setCertNumber(String certNumber) {
		this.certNumber = certNumber;
	}


}