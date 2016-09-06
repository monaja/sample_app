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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="tenant_alloc_details")
public class TenAllocations {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ten_alloc_id")
	private Long tenAllocId;
	
	@ManyToOne
	@JoinColumn(name="ten_alloc_brn",nullable=false)
	private OrgBranch allocbranch;
	
	@ManyToOne
	@JoinColumn(name="ten_alloc_struct",nullable=false)
	private RentalStructure structure;
	
	@ManyToOne
	@JoinColumn(name="ten_alloc_renunit",nullable=false)
	private RentalUnits renunits;
	
	@Column(name="ten_alloc_dreg",nullable=false)
	private Date dateregistered;
	
	@Column(name="ten_alloc_cancelled")
	private String cancelled;
	
	@Column(name="ten_alloc_dtcancel")
	private Date datecancelled;
	
	@XmlTransient
	 @JsonIgnore
	@ManyToOne
	@JoinColumn(name="ten_ten_id")
	private TenantDef tenant;

	public Long getTenAllocId() {
		return tenAllocId;
	}

	public void setTenAllocId(Long tenAllocId) {
		this.tenAllocId = tenAllocId;
	}

	public OrgBranch getAllocbranch() {
		return allocbranch;
	}

	public void setAllocbranch(OrgBranch allocbranch) {
		this.allocbranch = allocbranch;
	}

	public RentalStructure getStructure() {
		return structure;
	}

	public void setStructure(RentalStructure structure) {
		this.structure = structure;
	}


	public Date getDateregistered() {
		return dateregistered;
	}

	public void setDateregistered(Date dateregistered) {
		this.dateregistered = dateregistered;
	}

	public String getCancelled() {
		return cancelled;
	}

	public void setCancelled(String cancelled) {
		this.cancelled = cancelled;
	}

	public Date getDatecancelled() {
		return datecancelled;
	}

	public void setDatecancelled(Date datecancelled) {
		this.datecancelled = datecancelled;
	}

	public TenantDef getTenant() {
		return tenant;
	}

	public void setTenant(TenantDef tenant) {
		this.tenant = tenant;
	}

	public RentalUnits getRenunits() {
		return renunits;
	}

	public void setRenunits(RentalUnits renunits) {
		this.renunits = renunits;
	}
	
	
	
	

}
