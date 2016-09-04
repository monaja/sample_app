package com.brokersystems.setups.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="accounts")
public class AccountDef extends AuditBaseEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="acct_id")
	private Long acctId;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="acct_acc_code")
	private AccountTypes accountType;
	
	@Column(name="acct_fname")
	private String fname;
	
	@Column(name="acct_other_names")
	private String otherNames;
	
	@Column(name="acct_address")
	private String address;
	
	@Column(name="acct_pin")
	private String pinNo;
	
	@Column(name="acct_idpassport")
	private String idPassportNo;
	
	@Column(name="acct_photo")
	private byte[] photo;
	
	@Column(name="acct_email")
	private String email;
	
	@Column(name="acct_phone")
	private String phoneNo;
	
	@Column(name="acct_wef")
	private Date wef;
	
	@Column(name="acct_wet")
	private Date wet;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="acct_brn_code")
	private OrgBranch branch;
	
	@Column(name="acct_dob")
	private Date dob;
	
	@Column(name="acct_status")
	private String status;

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acctId) {
		this.acctId = acctId;
	}

	public AccountTypes getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountTypes accountType) {
		this.accountType = accountType;
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

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getIdPassportNo() {
		return idPassportNo;
	}

	public void setIdPassportNo(String idPassportNo) {
		this.idPassportNo = idPassportNo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
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

	public OrgBranch getBranch() {
		return branch;
	}

	public void setBranch(OrgBranch branch) {
		this.branch = branch;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
