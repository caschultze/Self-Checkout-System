package org.lsmr.software;

import org.lsmr.selfcheckout.Item;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class RemovesItems {
	
	public SelfCheckoutStation station;
	private int countWeightChanged;
	
	/**
	 * Constructor for RemovesItems
	 * Simulates the use case "Customer removes purchased items from bagging area"
	 * 
	 * @param station
	 * 		The SelfCheckoutStation that has the baggingArea. 
	 */
	public RemovesItems(SelfCheckoutStation station) {
		
		this.station = station;
		
		registerESListener();
		
	}
	
	/**
	 * Removes an item from the station's baggingArea 
	 * 
	 * @param item
	 * 		The Item to be removed from the station's baggingArea
	 */
	public void removesItems(Item item) {
		
		station.baggingArea.remove(item);
		try {
			if (station.baggingArea.getCurrentWeight() == 0.0) {
				ControlUnit.InkCounter--;
				ControlUnit.PaperCounter--;
			}
		} catch (OverloadException e) {
		}
	}
	
	/**
	 * Adds an item to the station's baggingArea 
	 * 
	 * @param item
	 * 		The Item to be added to the station's baggingArea
	 */
	public void placesItems(Item item) {
		
		station.baggingArea.add(item);
		
	}
	
	/**
	 * Registers an ElectronicScaleListener to the station's baggingArea
	 */
	private void registerESListener() {
		
		ElectronicScaleListener es_listener = new ElectronicScaleListener() {
			
			@Override 
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
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

	// Getter
	public int getCountWeightChanged() {
		return countWeightChanged;
	}

}
