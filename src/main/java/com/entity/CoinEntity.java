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
@IdClass(CoinEntityID.class)
public class CoinEntity {
	@Id
	Float coinValue;
	Integer coinCount;
	@ManyToOne
	@JoinColumn(name = "vmId", nullable = false)
	@JsonBackReference
	@Id
	private VendingMachineEntity vmCoinEntity;
	public CoinEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CoinEntity(Float coinValue, Integer coinCount, VendingMachineEntity vmCoinEntity) {
		super();
		this.coinValue = coinValue;
		this.coinCount = coinCount;
		this.vmCoinEntity = vmCoinEntity;
	}
	public Float getCoinValue() {
		return coinValue;
	}
	public void setCoinValue(Float coinValue) {
		this.coinValue = coinValue; 
	}
	public Integer getCoinCount() {
		return coinCount;
	}
	public void setCoinCount(Integer coinCount) {
		this.coinCount = coinCount;
	}
	public VendingMachineEntity getVmCoinEntity() {
		return vmCoinEntity;
	}
	public void setVmCoinEntity(VendingMachineEntity vmCoinEntity) {
		this.vmCoinEntity = vmCoinEntity;
	}
	
	
}
