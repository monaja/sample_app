package com.brokersystems.setups.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="payment_modes")
public class PaymentModes extends AuditBaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pm_id")
	private Long pmId;
	
	@Column(name="pm_sh_desc",nullable=false,unique=true)
	private String pmShtDesc;
	
	@Column(name="pm_desc")
	private String pmDesc;
	
	@Column(name="pm_max_val")
	private BigDecimal pmMaxValue;
	
	@Column(name="pm_min_val")
	private BigDecimal pmMinValue;

	public Long getPmId() {
		return pmId;
	}

	public void setPmId(Long pmId) {
		this.pmId = pmId;
	}

	public String getPmShtDesc() {
		return pmShtDesc;
	}

	public void setPmShtDesc(String pmShtDesc) {
		this.pmShtDesc = pmShtDesc;
	}

	public String getPmDesc() {
		return pmDesc;
	}

	public void setPmDesc(String pmDesc) {
		this.pmDesc = pmDesc;
	}

	public BigDecimal getPmMaxValue() {
		return pmMaxValue;
	}

	public void setPmMaxValue(BigDecimal pmMaxValue) {
		this.pmMaxValue = pmMaxValue;
	}

	public BigDecimal getPmMinValue() {
		return pmMinValue;
	}

	public void setPmMinValue(BigDecimal pmMinValue) {
		this.pmMinValue = pmMinValue;
	}
	
	
	
	

}
