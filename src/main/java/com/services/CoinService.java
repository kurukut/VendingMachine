package com.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.CoinEntity;
import com.entity.VendingMachineEntity;
import com.exceptions.NoChangeException;
import com.repositories.CoinRepository;



@Service
public class CoinService {
	@Autowired
	CoinRepository coinRepo;
	
	public List<CoinEntity> getAllCoinDetails(){
		return coinRepo.findAll();
	}
	@Transactional
	public int addVendingCoin(Integer vendingMachineID,Float coinValue,Integer coinCount){
		return coinRepo.insertCoinValue(coinCount, coinValue, vendingMachineID);
	}
	public List<CoinEntity>  updateCoinDenominations(HashMap<String,String> userDenominations,Float userAmount,Float actualAmount,
			Integer vendingMachineID) throws NoChangeException {
		List<CoinEntity> result=null;
		Float change=(userAmount-actualAmount);
		System.out.println("userAmount="+userAmount);
		System.out.println("actualAmount="+actualAmount);
		System.out.println("change="+change);
		persistCoinEntityAdd(userDenominations,vendingMachineID);
		
		if(change>0) {
			result=checkCoinChange(change);
			if(result!=null)
			persistCoinEntitySubtract(result,vendingMachineID);
			else {
				throw new NoChangeException("change  cannot be delivered.");
			}
			
		}
		
		return result;
	}
	
	public void persistCoinEntityAdd(HashMap<String,String> userDenominations,
			Integer vendingMachineID) {
		Iterator itr=userDenominations.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<String,String> entry=(Entry<String,String>) itr.next();
			String coinValueString=entry.getKey();
			Float coinValue=Float.parseFloat(coinValueString);
			String coinCountString=entry.getValue();
			Integer coinCount=Integer.parseInt(coinCountString);
			int updatedRows=coinRepo.incrementcoinValue(coinCount, coinValue,vendingMachineID);
			if(updatedRows==0) {
				coinRepo.insertCoinValue(coinCount, coinValue, vendingMachineID);
			}
			
		}
		
	}
	
	public void persistCoinEntitySubtract(List<CoinEntity> changeDenominations,
			Integer vendingMachineID) {
		Iterator itr=changeDenominations.iterator();
		while(itr.hasNext()) {
			CoinEntity coin=(CoinEntity) itr.next();
			Float coinValue=coin.getCoinValue();
			Integer coinCount=coin.getCoinCount();
			int updatedRows=coinRepo.decrementcoinValue(coinCount, coinValue,vendingMachineID);
			
		}
		
	}
	
	public List<CoinEntity> checkCoinChange(Float userAmount) {
		System.out.println(userAmount);
		List<CoinEntity> coinEntities=coinRepo.findAll();
		Collections.sort(coinEntities, new Comparator<CoinEntity>(){
			public int compare(CoinEntity a, CoinEntity b) {
				return Float.compare(b.getCoinValue(), a.getCoinValue());
			}
			
		}
		);
		Integer userIntegerAmount=(int) (userAmount*100);
		System.out.println(userIntegerAmount);
		List<CoinEntity> result=Calculate(coinEntities,userIntegerAmount,0);
		return result;
		
	}
	
	public  List<CoinEntity> Calculate(List<CoinEntity> coins, Integer change, int start)
	   {
	      for (int i = start; i < coins.size(); i++)
	      {
	    	  CoinEntity coin = coins.get(i);
	         // no point calculating anything if no coins exist or the 
	         // current denomination is too high
	    	  Integer coinCount=coin.getCoinCount();
	    	  Integer coinDenomination=(int) (coin.getCoinValue()*100);
	    	  System.out.println("coinCount="+coinCount+" coinDenomination="+coinDenomination);
	         if (coinCount > 0 && coinDenomination <= change)
	         {
	            Integer remainder = change % coinDenomination;
	            if (remainder < change)
	            {
	               Integer howMany = Math.min(coinCount, 
	                   (change - remainder) / coinDenomination);
	 
	               List<CoinEntity> matches = new ArrayList<CoinEntity>();
	               Float coinFloatDenomination=(float) (coinDenomination/100);
	               matches.add(new CoinEntity(coinFloatDenomination, howMany,null));
	 
	               Integer amount = howMany * coinDenomination;
	               Integer changeLeft = change - amount;
	               if (changeLeft == 0)
	               {
	                   return matches;
	               }
	 
	               List<CoinEntity> subCalc = Calculate(coins, changeLeft, i + 1);
	               if (subCalc != null)
	               {
	                  matches.addAll(subCalc);
	                  return matches;
	               }
	            }
	         }
	      }
	      return null;
	   }
	
	

}
