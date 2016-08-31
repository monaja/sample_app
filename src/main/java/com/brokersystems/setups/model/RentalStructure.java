package com.brokersystems.setups.model;

import java.io.Serializable;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="org_rental_structs")
public class RentalStructure extends AuditBaseEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rental_id")
	private Long rentalId;
	
	@Column(name="house_id",nullable=false)
	private String houseId;
	
	@Column(name="house_name",nullable=false)
	private String houseName;
	
	@Column(name="no_of_floors",nullable=false)
	private Integer noOfFloors;
	
	@Column(name="no_of_units",nullable=false)
	private Integer noOfUnits;
	
	@Lob
	@JsonIgnore
	@Column(name="house_image")
	private byte[] house_image;
	
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="ob_brn_code")
	private OrgBranch branch;
	
	@Transient
	private Long branchCode;
	
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="rentalStruct")
	private List<RentalUnits> rentalUnits;
	
	@Transient
	MultipartFile file;

	public Long getRentalId() {
		return rentalId;
	}


	public void setRentalId(Long rentalId) {
		this.rentalId = rentalId;
	}


	public String getHouseId() {
		return houseId;
	}


	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}


	public String getHouseName() {
		return houseName;
	}


	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}


	public Integer getNoOfFloors() {
		return noOfFloors;
	}


	public void setNoOfFloors(Integer noOfFloors) {
		this.noOfFloors = noOfFloors;
	}


	public Integer getNoOfUnits() {
		return noOfUnits;
	}


	public void setNoOfUnits(Integer noOfUnits) {
		this.noOfUnits = noOfUnits;
	}


	public byte[] getHouse_image() {
		return house_image;
	}


	public void setHouse_image(byte[] house_image) {
		this.house_image = house_image;
	}


	public List<RentalUnits> getRentalUnits() {
		return rentalUnits;
	}


	public void setRentalUnits(List<RentalUnits> rentalUnits) {
		this.rentalUnits = rentalUnits;
	}


	public OrgBranch getBranch() {
		return branch;
	}


	public void setBranch(OrgBranch branch) {
		this.branch = branch;
	}


	public MultipartFile getFile() {
		return file;
	}


	public void setFile(MultipartFile file) {
		this.file = file;
	}


	public Long getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(Long branchCode) {
		this.branchCode = branchCode;
	}
	
	
	
	

}
