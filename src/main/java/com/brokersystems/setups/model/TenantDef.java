package com.brokersystems.setups.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tenant_details")
public class TenantDef {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ten_id")
	private Long tenId;
	
	@Column(name="ten_fname",nullable=false)
    private String fname;
    
	@Column(name="ten_onames",nullable=false)
    private String otherNames;
    
	@Column(name="ten_address")
    private String address;
    
	@Column(name="ten_email")
    private String emailAddress;
    
	@Column(name="ten_phone",nullable=false)
    private String phoneNo;
    
	@Column(name="ten_dob",nullable=false)
    private Date dob;
    
	@Column(name="ten_pin")
    private String pinNo;
    
	@Column(name="ten_idno",nullable=false)
    private String IdPassport;
    
    @Lob
	@JsonIgnore
	@Column(name="ten_photo")
	private byte[] photo;
    
    @Column(name="ten_ten_type",nullable=false)
    private String tenantType;
    
    @Column(name="ten_datereg",nullable=false)
    private Date dateregistered;
    
    @Column(name="ten_datetermin")
    private Date dateterminated;
    
    @Column(name="ten_status",nullable=false)
    private String status;
    
    
    @ManyToOne
	@JoinColumn(name="ten_brn_code",nullable=false)
	private OrgBranch registeredbrn;
    
    
    
	@XmlTransient
	@JsonIgnore
	@OneToMany(mappedBy="tenant")
	private List<TenAllocations> allocations;

	public Long getTenId() {
		return tenId;
	}

	public void setTenId(Long tenId) {
		this.tenId = tenId;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPinNo() {
		return pinNo;
	}

	public void setPinNo(String pinNo) {
		this.pinNo = pinNo;
	}

	public String getIdPassport() {
		return IdPassport;
	}

	public void setIdPassport(String idPassport) {
		IdPassport = idPassport;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getTenantType() {
		return tenantType;
	}

	public void setTenantType(String tenantType) {
		this.tenantType = tenantType;
	}

	public Date getDateregistered() {
		return dateregistered;
	}

	public void setDateregistered(Date dateregistered) {
		this.dateregistered = dateregistered;
	}

	public Date getDateterminated() {
		return dateterminated;
	}

	public void setDateterminated(Date dateterminated) {
		this.dateterminated = dateterminated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<TenAllocations> getAllocations() {
		return allocations;
	}

	public void setAllocations(List<TenAllocations> allocations) {
		this.allocations = allocations;
	}

	public OrgBranch getRegisteredbrn() {
		return registeredbrn;
	}

	public void setRegisteredbrn(OrgBranch registeredbrn) {
		this.registeredbrn = registeredbrn;
	}

}
