package com.entity;

import java.io.Serializable;
import java.util.Objects;

public class CoinEntityID implements Serializable {
	private Float coinValue;
	private VendingMachineEntity vmCoinEntity;
	public CoinEntityID() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(coinValue, vmCoinEntity);
	}
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoinEntityID coinEntityID = (CoinEntityID) o;
        return this.coinValue.equals(coinEntityID.coinValue) && 
        		this.vmCoinEntity.getVmId().equals(coinEntityID.vmCoinEntity.getVmId());
	}
	

}
