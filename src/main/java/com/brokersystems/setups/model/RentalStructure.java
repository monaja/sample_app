package com.brokersystems.setups.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="org_rental_structs")
public class RentalStructure implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rental_id")
	private Long rental_id;
	
	@Column(name="house_id",nullable=false)
	private String houseId;
	
	@Column(name="house_name",nullable=false)
	private String houseName;
	
	@Column(name="no_of_floors",nullable=false)
	private Integer noOfFloors;
	
	@Column(name="no_of_units",nullable=false)
	private Integer noOfUnits;
	
	@Lob
	@Column(name="house_image")
	private byte[] house_image;
	
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="rentalStruct")
	private List<RentalUnits> rentalUnits;
	
	
	
	
	
	
	

}
