package org.lsmr.software;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class AddOwnBag {

	public SelfCheckoutStation station;
	private static ElectronicScaleListener esl;
	private static boolean checkOverLoaded = false;
	private static boolean checkDisabled = false;
	private double bagWeightLimit = 2.5;
	
	public AddOwnBag(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
		registerListeners();
	}
	
	public void registerListeners() {
		esl = new ElectronicScaleListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				checkDisabled = true;
				
			}

			@Override
			public void weightChanged(ElectronicScale scale, double weightInGrams) {
				// TODO Auto-generated method stub
			}

			@Override
			public void overload(ElectronicScale scale) {
				checkOverLoaded = true;
				
			}

			@Override
			public void outOfOverload(ElectronicScale scale) {
				// TODO Auto-generated method stub
				
			}
		};
		
		station.baggingArea.register(esl);
		
	}
	
	//getter for checkOverloaded
	public static boolean getCheckOverloaded() {
		return checkOverLoaded;
	}
	//getter for checkDisabled
	public static boolean getCheckDisabled() {
		return checkDisabled;
	}
	
	//Method for adding your own bag
	public void addOwnBag(BarcodedItem bag) {
		//check if the bag is under the expected weight limit for a bag
		//if the bag is an appropriate weight add it to the scale
		if(bag.getWeight()<=bagWeightLimit) {
			station.baggingArea.add(bag);
		}
		//if the bag is too heavy the scale is disabled
		//an attendant is needed
		else if(bag.getWeight()>bagWeightLimit) {
			station.baggingArea.disable();
		}
		//if the addition of the bag goes over the max weight of the scale
		//disable the scale
		if(checkOverLoaded) {
			station.baggingArea.disable();
		}
	}
}
