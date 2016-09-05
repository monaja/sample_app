package com.brokersystems.setups.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account_types")
public class AccountTypes extends AuditBaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="acc_id")
	private Long accId;
	
	@Column(name="acc_sht_desc",nullable=false,unique=true)
	private String accShtDesc;
	
	@Column(name="acc_name")
	private String accName;
	
	@Column(name="acc_vat_appl")
	private boolean vatAppli;
	
	@Column(name="acc_vat_rate")
	private BigDecimal vatRate;
	
	@Column(name="acc_whtx_appl")
	private boolean whtxAppl;
	
	@Column(name="acc_whtx_rate")
	private BigDecimal whtaxVal;

	public Long getAccId() {
		return accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	public String getAccShtDesc() {
		return accShtDesc;
	}

	public void setAccShtDesc(String accShtDesc) {
		this.accShtDesc = accShtDesc;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public boolean isVatAppli() {
		return vatAppli;
	}

	public void setVatAppli(boolean vatAppli) {
		this.vatAppli = vatAppli;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public boolean isWhtxAppl() {
		return whtxAppl;
	}

	public void setWhtxAppl(boolean whtxAppl) {
		this.whtxAppl = whtxAppl;
	}

	public BigDecimal getWhtaxVal() {
		return whtaxVal;
	}

	public void setWhtaxVal(BigDecimal whtaxVal) {
		this.whtaxVal = whtaxVal;
	}
	
	
	
	

}
