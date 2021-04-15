package com.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@DynamicUpdate
@IdClass(ProductEntityID.class)
public class ProductEntity  {
	@Id
	Integer productId;
	String productName;
	Float productCost;
	Integer productCount;
	@ManyToOne
	@JoinColumn(name = "vmId", nullable = false)
	@JsonBackReference
	@Id
	private VendingMachineEntity vmProdEntity;
	public ProductEntity(Integer productId, String productName, Float productCost, Integer productCount,
			VendingMachineEntity vmProdEntity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productCost = productCost;
		this.productCount = productCount;
		this.vmProdEntity = vmProdEntity;
	}
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Float getProductCost() {
		return productCost;
	}
	public void setProductCost(Float productCost) {
		this.productCost = productCost;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public VendingMachineEntity getVmProdEntity() {
		return vmProdEntity;
	}
	public void setVmProdEntity(VendingMachineEntity vmProdEntity) {
		this.vmProdEntity = vmProdEntity;
	}
	
	

}
