package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sys_locales database table.
 * 
 */
@Entity
@Table(name="sys_locales")
@NamedQuery(name="SysLocale.findAll", query="SELECT s FROM SysLocale s")
public class SysLocale extends AuditBaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="loc_id")
	private Long locId;

	@Column(name="loc_desc")
	private String locDesc;

	@Column(name="loc_name")
	private String locName;

	//bi-directional many-to-one association to Organization
	@OneToMany(mappedBy="sysLocale")
	private List<Organization> organizations;

	public SysLocale() {
	}

	public Long getLocId() {
		return this.locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public String getLocDesc() {
		return this.locDesc;
	}

	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}

	public String getLocName() {
		return this.locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public List<Organization> getOrganizations() {
		return this.organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public Organization addOrganization(Organization organization) {
		getOrganizations().add(organization);
		organization.setSysLocale(this);

		return organization;
	}

	public Organization removeOrganization(Organization organization) {
		getOrganizations().remove(organization);
		organization.setSysLocale(null);

		return organization;
	}

}