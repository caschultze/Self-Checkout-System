package org.lsmr.software;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
import org.lsmr.software.BagItem;

//This class covers 2 use case: 
//	
//	-Customer does not want to bag a scanned item: the customer has already scanned an item
//		-if the customer doesn't want to place their item in the bagging area, this action 
//		 must be approved by an attendant, which will alter the expected total weight
//		 (the expected weight of the scanned item is subtracted from the total expected weight,
//		  which is done manually by the attendant)
//
//	-Attendant removes product from purchases: the customer has already scanned and bagged
//	 their item, but decides they no longer want it, they call an attendant and the attendant
//	 removes the item from the bagging area, and removes it from the list of scanned items
//
//

public class CustomerNoBagScannedItemAttendantRemovesProduct {
	
	
	public SelfCheckoutStation station;
	private boolean attendantApproval = false;
	private int countWeightChanged;
	public CurrentSessionData data = new CurrentSessionData();
	
	
	//constructor
	
	public CustomerNoBagScannedItemAttendantRemovesProduct(SelfCheckoutStation checkoutStation) {
		
		this.station = checkoutStation;
		
	}
	
	//if the attendant has approved, "bag" the item, but don't change the current weight on the scale
	//this is done by stubbing out the electronic scale listener and overridng the weightChanged
	//method to do nothing
	
	public void DontBagScannedItem(BarcodedItem item) {
		
		if (attendantApproval == true) {
			double deduction = 0 - item.getWeight();
			data.setCurrentTotalWeight(deduction);
			
		}
		
	}
		
	
	//if the attendeant has approved, remove the item from the bagging area
	//and remove the item from the scanned items
	
	public void removeProductFromPurchases(BarcodedItem item) {
		
		if (attendantApproval == true) {
			
			station.baggingArea.remove(item);
			data.removeScannedItem(item);
			
		}
	}
	
	
	//setter and getter for the attendant approval variable
	
	public void setAttendantApproval(boolean input) {
		
		 attendantApproval = input;
		
	}
	
	public boolean getAttendantApproval() {
		
		return attendantApproval;
		
	}

}
