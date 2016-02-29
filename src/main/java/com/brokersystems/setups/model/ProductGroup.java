package com.brokersystems.setups.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the product_groups database table.
 * 
 */
@Entity
@Table(name="product_groups")
public class ProductGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRG_CODE")
	private Long prgCode;

	@Column(name="PRG_DESCN")
	private String prgDescn;

	@Column(name="PRG_TYPE")
	private String prgType;
	
	@XmlTransient
	 @JsonIgnore
	@OneToMany(mappedBy="productGroup")
	private List<Product> products;
	


	public ProductGroup() {
	}

	public Long getPrgCode() {
		return this.prgCode;
	}

	public void setPrgCode(Long prgCode) {
		this.prgCode = prgCode;
	}

	public String getPrgDescn() {
		return this.prgDescn;
	}

	public void setPrgDescn(String prgDescn) {
		this.prgDescn = prgDescn;
	}

	public String getPrgType() {
		return this.prgType;
	}

	public void setPrgType(String prgType) {
		this.prgType = prgType;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductGroup(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductGroup(null);

		return product;
	}

	
	

}