package org.lsmr.software;

import java.math.BigDecimal;


import org.lsmr.selfcheckout.devices.SelfCheckoutStation;


public class FinishAdding {

	public SelfCheckoutStation station;
	public CurrentSessionData sessionData = new CurrentSessionData();
	public BigDecimal total; 
	
	
	public FinishAdding(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
	}
	
	
	public void finish(int numBags, BigDecimal temp) { 
		
			
		total = sessionData.setAndGetTotalPrice(temp);
		System.out.println("You have to pay " + total);
		
		
		station.mainScanner.disable();
		station.baggingArea.disable();
		
		payWithCard();
		payWithCash();
	}
	
	public void payWithCard() {
		
		System.out.println("Choose card payment method"); 
		System.out.println("Insert, swipe, or tap to pay"); 
		
	}
	
	
	public void payWithCash() {
		
		System.out.println("Insert bills or coins into corresponding slots"); 
		
	}

	
}
 