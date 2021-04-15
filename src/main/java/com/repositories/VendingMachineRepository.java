package com.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.VendingMachineEntity;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachineEntity,Integer> {
	List<VendingMachineEntity> findAll();
	Optional<VendingMachineEntity> findById(Integer vendingMachineID);
	
	@Modifying
	@Query(value="INSERT INTO VendingMachineEntity  (vmId) values(?1)",
    		nativeQuery = true)
	int insertVM(Integer vendingMachineID);
	

}
