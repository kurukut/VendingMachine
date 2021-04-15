package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CoinEntity;
import com.entity.ProductEntity;
import com.entity.VendingMachineEntity;
import com.exceptions.InsufficientAmountException;
import com.exceptions.InsufficientProductQuantityException;
import com.exceptions.NoChangeException;
import com.exceptions.ProductNotFoundException;
import com.repositories.CoinRepository;
import com.repositories.ProductRepository;
import com.repositories.VendingMachineRepository;
import com.services.CoinService;
import com.services.ProductService;
import com.services.VendingMachineService;

@RestController
public class VendingMachineController {
	
	@Autowired
	VendingMachineService vmService;
	@Autowired
	ProductService prodService;
	@Autowired
	CoinService coinService;
	
	
	@PostMapping("/vendingmachine/addVendingMachine/vendingMachineID/{vendingMachineID}")
	public ResponseEntity<String> addVendingMachineEntity(@PathVariable ("vendingMachineID")Integer vendingMachineID) {
		int result=vmService.addVendingMachineEntity(vendingMachineID);
		
		return new ResponseEntity<String>("Successful", HttpStatus.OK);
		
	}
	@PostMapping("/vendingmachine/addCoin/vendingMachineID/{vendingMachineID}/coinValue/{coinValue}/coinCount/{coinCount}")
	public ResponseEntity<String> addVendingCoin(@PathVariable ("vendingMachineID")Integer vendingMachineID,
			@PathVariable ("coinValue")Float coinValue,
			@PathVariable ("coinCount")Integer coinCount) {
		int result=coinService.addVendingCoin(vendingMachineID,coinValue,coinCount);
		
		return new ResponseEntity<String>("Successful", HttpStatus.OK);
		
	}
	@PostMapping("/vendingmachine/addProduct/vendingMachineID/{vendingMachineID}/productId/{productId}/productName/{productName}/productCount/{productCount}/productCost/{productCost}")
	public ResponseEntity<String> addVendingProduct(@PathVariable ("vendingMachineID")Integer vendingMachineID,
			@PathVariable ("productId")Integer productId,
			@PathVariable ("productName")String productName,
			@PathVariable ("productCount")Integer productCount,
			@PathVariable ("productCost")Float productCost) {
		int result=prodService.addVendingProduct(vendingMachineID,productId,productCount,productCost,productName);
		
		return new ResponseEntity<String>("Successful", HttpStatus.OK);
		
	}
	@GetMapping("/vendingmachine/alldetails")
	public ResponseEntity<List<VendingMachineEntity>> getVMDetails() {
		 
        List<VendingMachineEntity> vmDetails = vmService.getVMDetails();
        return new ResponseEntity<>(vmDetails, HttpStatus.OK);
    }
	
	@GetMapping("/vendingmachine/alldetails/vendingMachineID/{vendingMachineID}")
	public ResponseEntity<VendingMachineEntity> getVMDetailsByID(@PathVariable ("vendingMachineID") Integer vendingMachineID) {
		 
        Optional<VendingMachineEntity> vmDetails = vmService.getVMDetailsById(vendingMachineID);
        return new ResponseEntity(vmDetails, HttpStatus.OK);
    }
	
	@GetMapping("/vendingmachine/productdetails")
	public ResponseEntity<List<ProductEntity>> getAllProdDetails() {
		 
        List<ProductEntity> prodDetails = prodService.getVMProductDetails();
        return new ResponseEntity<>(prodDetails, HttpStatus.OK);
    }
	
	@GetMapping("/vendingmachine/productdetails/vendingMachineId/{vendingMachineID}/productId/{productId}")
	public ResponseEntity<Optional<ProductEntity>> getProdDetailsFromVendingMachine(
			@PathVariable ("vendingMachineID") Integer vendingMachineID,
			@PathVariable ("productId") Integer productId)  {
		 
        Optional<ProductEntity> prodDetails = prodService.getProdDetailsFromVendingMachine(vendingMachineID,productId);
        return new ResponseEntity<>(prodDetails, HttpStatus.OK);
    }
	
	@GetMapping("/vendingmachine/coindetails")
	List<CoinEntity> getAllCoinDetails(){
		return coinService.getAllCoinDetails();
	}
	
	
	
	@GetMapping("/vendingmachine/checkprice/vendingMachineID/{vendingMachineID}/product/{productId}/productCount/{productCount}")
	ResponseEntity<Float> checkPrice(@PathVariable ("vendingMachineID") Integer vendingMachineID,
			@PathVariable ("productId") Integer productId,
			@PathVariable ("productCount") Integer productCount ) throws ProductNotFoundException, InsufficientProductQuantityException {
		Float result=prodService.getTotalprice(vendingMachineID,productId, productCount);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/vendingmachine/confirmPayment/vendingMachineID/{vendingMachineID}/product/{productId}/productCount/{productCount}/userTotal/{userTotal}")
	ResponseEntity<List<CoinEntity>> confirmpayment(@PathVariable ("vendingMachineID") Integer vendingMachineID,
			@PathVariable ("productId") Integer productId,
			@PathVariable ("productCount") Integer productCount,
			@PathVariable ("userTotal") Float userTotal,
			@RequestParam HashMap<String, String> userDenominations)
					throws ProductNotFoundException, InsufficientProductQuantityException, NoChangeException, InsufficientAmountException {
		Float totalP=prodService.getTotalprice(vendingMachineID,productId, productCount);
		if(userTotal<totalP) {
			throw new InsufficientAmountException("Actual price="+totalP+" User price="+userTotal);
		}
		List<CoinEntity> result=prodService.confirmPayment( vendingMachineID,productId, productCount, totalP,
				userDenominations, userTotal);
		
		return new ResponseEntity<List<CoinEntity>>(result, HttpStatus.OK);
	}
	
	
	
	
	
	

}
