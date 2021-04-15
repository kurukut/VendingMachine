package com.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductEntityID implements Serializable{
	private Integer productId;
	private VendingMachineEntity vmProdEntity;
	public ProductEntityID() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductEntityID(Integer productId, VendingMachineEntity vmProdEntity) {
		super();
		this.productId = productId;
		this.vmProdEntity = vmProdEntity; 
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(productId, vmProdEntity);
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntityID productEntityID = (ProductEntityID) o;
        return this.productId.equals(productEntityID.productId) && 
        		this.vmProdEntity.getVmId().equals(productEntityID.vmProdEntity.getVmId());
        
	}
	
	
	 
	 
}
