package org.lsmr.software;

public class EnterNumberOfBags {

	public CurrentSessionData sessionData = new CurrentSessionData();
	
	public void addBagsPriceToTotal(int numBags) {
		
		if(numBags < 0) {
			System.out.println("The number of compost bags used cannot be negative. Please enter a valid number of compost bags used. ");
		}
		
		sessionData.addNumberOfBagsToTotalPrice(numBags);
	}
}
