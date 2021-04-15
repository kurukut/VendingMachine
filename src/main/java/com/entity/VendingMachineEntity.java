package com.entity;

import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.interfaces.VendingMachine;

@Entity
public class VendingMachineEntity implements VendingMachine {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Integer vmId;
	@OneToMany(targetEntity = ProductEntity.class,mappedBy="vmProdEntity",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ProductEntity> vmProducts; 
	@OneToMany(targetEntity = CoinEntity.class,mappedBy="vmCoinEntity",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<CoinEntity> vmCoinDenominations;
	
	public VendingMachineEntity() {
		super(); 
		// TODO Auto-generated constructor stub 
	}
    
	public VendingMachineEntity(Integer vmId, List<ProductEntity> vmProducts, List<CoinEntity> vmCoinDenominations) {
		super(); 
		this.vmId = vmId;
		this.vmProducts = vmProducts;
		this.vmCoinDenominations = vmCoinDenominations;
	}
	
	public Integer getVmId() {
		return vmId;
	}
	public void setVmId(Integer vmId) {
		this.vmId = vmId;
	}
	public List<ProductEntity> getVmProducts() {
		return vmProducts;
	}
	public void setVmProducts(List<ProductEntity> vmProducts) {
		this.vmProducts = vmProducts;
	}
	public List<CoinEntity> getVmCoinDenominations() {
		return vmCoinDenominations;
	}
	public void setVmCoinDenominations(List<CoinEntity> vmCoinDenominations) {
		this.vmCoinDenominations = vmCoinDenominations; 
	}
	
	
	
	

}
