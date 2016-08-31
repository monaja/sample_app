package com.brokersystems.setups.model;

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
@Table(name ="g7_user_roles")
public class UserRole extends AuditBaseEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="user_role_id")
	private Long userroleid;
	
	
	@Column(name="role")
	private String role;
	
	@XmlTransient
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(Long userroleid) {
		this.userroleid = userroleid;
	}	
	

}
