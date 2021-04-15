package com.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.CoinEntity;
import com.entity.CoinEntityID;

@Repository
public interface CoinRepository extends JpaRepository<CoinEntity,CoinEntityID> {
	List<CoinEntity>findAll(); 
	@Modifying
    @Query(value="UPDATE CoinEntity c SET c.coinCount = c.coinCount + ?1 WHERE c.coinValue = ?2 and c.vmId = ?3",
    		nativeQuery = true)
    int incrementcoinValue(Integer coinCount, Float coinValue,Integer vendingMachineID);
	
	@Modifying
	@Query(value="UPDATE CoinEntity c SET c.coinCount = c.coinCount - ?1 WHERE c.coinValue = ?2 and c.vmId = ?3",
    		nativeQuery = true)
    int decrementcoinValue(Integer coinCount, Float coinValue,Integer vendingMachineID);
	
	@Modifying
	@Query(value="INSERT INTO CoinEntity  (coinValue,coinCount,vmId) values(?2,?1,?3)",
    		nativeQuery = true)
	int insertCoinValue(Integer coinCount, Float coinValue,Integer vendingMachineID);
	
}
