package com.brokersystems.setups.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="landlord_details")
public class Landlord extends AuditBaseEntity  {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ten_id")
	private Long tenantId;
	
	@Column(name="ten_fname")
	private String fname;
	
	@Column(name="ten_names")
	private String otherNames;
	
	@Column(name="ten_address")
	private String address;
	
	@Column(name="ten_idpassport")
	private String idPassport;
	
	@Column(name="ten_telno")
	private String telNo;
	
	@Column(name="ten_smsno")
	private String smsNo;
	
	@Column(name="ten_email")
	private String emailAddress;
	
	@Column(name="ten_bank")
	private String bankName;
	
	@Column(name="ten_acctNumber")
	private String acctNumber;

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getOtherNames() {
		return otherNames;
	}

	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdPassport() {
		return idPassport;
	}

	public void setIdPassport(String idPassport) {
		this.idPassport = idPassport;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getSmsNo() {
		return smsNo;
	}

	public void setSmsNo(String smsNo) {
		this.smsNo = smsNo;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAcctNumber() {
		return acctNumber;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}
	
	
	

}
