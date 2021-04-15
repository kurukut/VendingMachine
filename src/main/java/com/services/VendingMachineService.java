package com.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.VendingMachineEntity;
import com.repositories.CoinRepository;
import com.repositories.ProductRepository;
import com.repositories.VendingMachineRepository;

@Service
public class VendingMachineService {
	@Autowired 
	VendingMachineRepository vmRepo;
	@Transactional
	public int addVendingMachineEntity(Integer vendingMachineID){
		return vmRepo.insertVM(vendingMachineID);
	}
	public List<VendingMachineEntity> getVMDetails(){
		return vmRepo.findAll();
	}
	
	public Optional<VendingMachineEntity> getVMDetailsById(Integer vendingMachineID){
		return vmRepo.findById(vendingMachineID);
	}
	
	

}
