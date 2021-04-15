package com.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.ProductEntity;
import com.entity.ProductEntityID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,ProductEntityID> {
	List<ProductEntity>findAll();
    Optional<ProductEntity> findById(ProductEntityID productEntityId);
    
    @Modifying
	@Query(value="UPDATE ProductEntity c SET c.productCount = c.productCount - ?1 WHERE c.productId = ?2 and c.vmId = ?3",
    		nativeQuery = true)
    int decrementProductValue(Integer productCount, Integer productId,Integer vendingMachineID);
    
    @Modifying
	@Query(value="INSERT INTO ProductEntity  (productId,productCount,vmId,productCost,productName) values(?1,?2,?3,?4,?5)",
    		nativeQuery = true)
	int insertProductValue(Integer productId, Integer productCount,Integer vendingMachineID,Float productCost,String productName);
    
}
