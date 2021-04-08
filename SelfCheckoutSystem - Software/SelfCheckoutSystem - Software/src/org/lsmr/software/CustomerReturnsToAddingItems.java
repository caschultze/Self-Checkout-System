package org.lsmr.software;

import java.util.ArrayList;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

/**
 * This class covers the scenario where the customer has required help from an attendant and
 * the attendant has helped the customer, meaning that the customer can return to bagging
 * items.
 * 
 * This class makes use of the bagItem class and assumes that there are already outstanding
 * items to be bagged, NOTE: I havent tested this yet 
 * 
 */

public class CustomerReturnsToAddingItems {
	
	
	public SelfCheckoutStation station;
	public BagItem b1;
	public boolean helpNeeded;
	private int countWeightChanged;
	
	public CustomerReturnsToAddingItems(SelfCheckoutStation checkoutStation) {
		
		//assume the customer does not need help at this moment (they've already been helped)
		
		station = checkoutStation;
		b1 = new BagItem(checkoutStation);
		helpNeeded = false;
		registerESListener();
	}
	
	//bag items if the customer no longer requires help
	
	public void returnAndBagItems(BarcodedItem item) {
		
		if (helpNeeded == false) {
			
			b1.bagItems(item);
			
		}		
		
	}

	/**
	 * Registers an ElectronicScaleListener to the station's baggingArea
	 */
	private void registerESListener() {
		
		ElectronicScaleListener es_listener = new ElectronicScaleListener() {
			
			@Override 
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
		
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
			
			}
			
			@Override 
			public void weightChanged(ElectronicScale scale, double weightInGrams) {
				countWeightChanged++;
				
			}
			
			@Override
			public void overload(ElectronicScale scale) {
					
			}
			
			@Override
			public void outOfOverload(ElectronicScale scale) {
				
			}
			
		};
		
		station.baggingArea.register(es_listener);
		
	}
	
	//getter and setter for the help needed flag
	
	public boolean getHelpNeeded() {
		
		return helpNeeded;
		
	}
	
	public void setHelpNeeded(boolean input) {
		
		helpNeeded = input;
		
	}
	
	//getter for countWeightChanged
	
	public int getCountWeightChanged() {
		
		return countWeightChanged;
		
	}
	

	
	
}

