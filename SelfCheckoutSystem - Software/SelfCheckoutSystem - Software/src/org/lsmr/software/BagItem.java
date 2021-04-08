package org.lsmr.software;

import java.util.ArrayList;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class BagItem {


	public SelfCheckoutStation scs;
	public ElectronicScaleListener esl;
	public CurrentSessionData sessionData = new CurrentSessionData();
	private static boolean validWeightCheck = false;
	private static boolean checkOverloaded = false;
	private boolean enableCheck = false;
	private static ArrayList<BarcodedItem> baggedItems = new ArrayList<BarcodedItem>();;
	
	private boolean disabledCheck = false;
	
	
	public BagItem(SelfCheckoutStation checkoutStation) {
		scs = checkoutStation;
		registerListeners();
	}
	
	public void registerListeners() {
		esl = new ElectronicScaleListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				enableCheck = true;
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				disabledCheck = true;
			}
				
			@Override
			public void weightChanged(ElectronicScale scale, double weightInGrams) {
				validWeightCheck = true;
			}

			@Override
			public void overload(ElectronicScale scale) {
				checkOverloaded = true;
				
			}

			@Override
			public void outOfOverload(ElectronicScale scale) {
				// TODO Auto-generated method stub
				
			}
		};
		
		scs.baggingArea.register(esl);
	}
	
	//getter for validWeightCheck
	public static boolean getValidWeightCheck() {
		return validWeightCheck;
	}
	
	//getter for checkOverloaded
	public static boolean getCheckOverloaded() {
		return checkOverloaded;
	}
	
	//getter for baggedItems
	public static ArrayList<BarcodedItem> getBaggedItems() {
		return baggedItems;
	}
	//getter for enableCheck
	public boolean getEnableCheck() {
		return enableCheck;
	}
	
	public boolean getDisabledCheck() {
		return disabledCheck;
	}
	
//	public void bagItems(ArrayList<BarcodedItem> itemsToBeBagged) {	
//		
//		if (itemsToBeBagged == null) {
//			throw new NullPointerException("No argument may be null");
//		}
//		
//		validWeightCheck = false;
//		checkOverloaded = false;
//		for (BarcodedItem currentProduct : itemsToBeBagged) {
//			scs.baggingArea.add(currentProduct);
//			if(checkOverloaded) {
//				scs.baggingArea.disable();
//			}
//			if(validWeightCheck) {
//				baggedItems.add(currentProduct);
//			}
//		}
//	}
	
	public void bagItems(BarcodedItem item) {
		if (item == null) {
			throw new SimulationException(new NullPointerException("No argument may be null."));
		}
		
		validWeightCheck = false;
		checkOverloaded = false;
		
		scs.baggingArea.add(item);
		if(checkOverloaded) {
			scs.baggingArea.disable();
		}
//		if(validWeightCheck) {
//			sessionData.setCurrentTotalBaggedWeight(item.getWeight());
//		}
//		
//		if (sessionData.getCurrentExpectedWeight() != sessionData.getCurrentTotalBaggedWeight()) {
//			System.out.println("Expected weight does not match actual bagged weight");
//		}
	}

}