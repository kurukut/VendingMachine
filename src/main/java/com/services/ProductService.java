package com.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CoinEntity;
import com.entity.ProductEntity;
import com.entity.ProductEntityID;
import com.entity.VendingMachineEntity;
import com.exceptions.InsufficientProductQuantityException;
import com.exceptions.NoChangeException;
import com.exceptions.ProductNotFoundException;
import com.repositories.CoinRepository;
import com.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository prodRepo;
	@Autowired
	CoinService coinServ;
	
	public List<ProductEntity> getVMProductDetails(){
		return prodRepo.findAll();
	}
	@Transactional
	public int addVendingProduct(Integer vendingMachineID,Integer productId,Integer productCount,Float productCost,String productName) {
		return prodRepo.insertProductValue(productId, productCount, vendingMachineID,productCost,productName);
	}
	
	public Optional<ProductEntity> getProdDetailsFromVendingMachine(Integer vendingMachineID,Integer productId) 
			 {
		Optional<ProductEntity> result=null;
		if(vendingMachineID!=null && productId!=null) {
		
				result=prodRepo.findById(new ProductEntityID(productId,new VendingMachineEntity(vendingMachineID,null,null)));
				
		}
		return result;
		
	}
	
	public Float getTotalprice(Integer vendingMachineID,Integer productId,Integer userProductCount) 
			throws ProductNotFoundException,InsufficientProductQuantityException {
		VendingMachineEntity vmEntity=new VendingMachineEntity(vendingMachineID,null,null);
		Optional<ProductEntity> prod=prodRepo.findById(new ProductEntityID(productId,vmEntity));
		Integer prodCount=prod.get().getProductCount();
		if(prodCount==null) {
			throw new ProductNotFoundException(productId+" is noot found!");
		}
		float prodCost=prod.get().getProductCost();
		if(userProductCount<=prodCount) {
			return (prodCost*userProductCount);
		}
		else {
			throw new InsufficientProductQuantityException("requested less than actual quantity for productId:"+productId+" requested="+userProductCount
					+" actual="+prodCount);
		}
		
	}
	
	@Transactional(rollbackOn = { RuntimeException.class,NoChangeException.class })
	public List<CoinEntity> confirmPayment(Integer vendingMachineID,Integer productId,Integer userProductCount,Float actualAmount,
			HashMap<String,String>userDenominations,Float userAmount) throws NoChangeException {
			List<CoinEntity> result=coinServ.updateCoinDenominations(userDenominations,userAmount,actualAmount,vendingMachineID);
			updateProductDetails( vendingMachineID, productId, userProductCount);
			return result;
	}
	
	public void updateProductDetails(Integer vendingMachineID,Integer productId,Integer userProductCount) {
		prodRepo.decrementProductValue(userProductCount, productId, vendingMachineID);
	}

	
}
