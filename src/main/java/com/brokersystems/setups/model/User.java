package com.brokersystems.setups.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="g7_users")
public class User  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
    @Column(name="user_id")
    private Long id;
    
    @Column(name="user_username")
    private String username;
    
    @Column(name="user_name")
    private String name;
    
    @Column(name="user_status")
    private String enabled;
    
    @Column(name="user_password")
    private String password;
    
    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
    
    public User(User user) {
        this.id = user.id;
        this.username = user.username;
        this.name = user.name;       
        this.password = user.password;
        this.enabled=user.enabled;        
}

    public User() {
    	
	}





	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return username;
	}
	
	
}