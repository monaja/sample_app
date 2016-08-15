package com.brokersystems.setups.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate_types")
public class RateTypes {
	
	public RateTypes() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rt_id")
	private Long rate_id;
	
	@Column(name="rate_type",nullable=false)
	private String rateType;
	
	@Column(name="rate_desc")
	private String rateDesc;

	public Long getRate_id() {
		return rate_id;
	}

	public void setRate_id(Long rate_id) {
		this.rate_id = rate_id;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRateDesc() {
		return rateDesc;
	}

	public void setRateDesc(String rateDesc) {
		this.rateDesc = rateDesc;
	}
	
	

}
