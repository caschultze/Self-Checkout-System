package org.lsmr.software;

import java.util.ArrayList;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

/*
 * A class covering the use case 'fails to bag item'
 * Covers: if the user doesn't bag items or bagged item weight doesn't match predicted/supposed weight for each item
 */

public class PlaceItemFail extends BagItem{

	CurrentSessionData data = new CurrentSessionData();
	SelfCheckoutStation station;
	private double pastWeight = 0.0;
	private boolean discrepancy;
	final double threshold = 0.0001;

	private ArrayList<BarcodedItem> scannedItems = new ArrayList<BarcodedItem>();
	private ArrayList<BarcodedItem> baggedItems = new ArrayList<BarcodedItem>();

	
	//access barcode hashmap/product, create hashmap to copy off items, then delete first index when that item has been placed from the copy 
	
	public PlaceItemFail(SelfCheckoutStation checkoutStation) {
		super(checkoutStation);
		this.station = checkoutStation;
	}
	
	/*
	 * Function used to determine if added weight to the scale is correct (checks one item at a time)
	 * Should be called upon if weight has been changed/been registered by the scale listener 
	 */
	public void checkWeights() throws OverloadException {
		scannedItems.addAll(data.getScannedItems());
		
		//items should be bagged in the order they were scanned -> first scanned, first bagged
		//as such, can simply access the first index of scannedItems (that has not already been bagged) to check what weight should be placed
		
		double currentWeight = ControlUnit.itemBag.scs.baggingArea.getCurrentWeight();
		
		if(!checkItemPlaced() || scannedItems.isEmpty()) {
			this.discrepancy = true;
			throw new SimulationException("Weight should have changed");
		}
		
		double predicted = 0.0;
		
		for(int i = 0; i < scannedItems.size(); i++)
			predicted += scannedItems.get(i).getWeight();
		
		predicted = predicted - pastWeight;
		
		//get weight of the individual item just added
		double addedWeight = currentWeight - pastWeight;
		
		if(predicted != addedWeight) //if the just-added weight is not what it should be
			throw new SimulationException("Weight placed on scale is incorrect");
		
		this.pastWeight = currentWeight;
		
		System.out.println();
		
		for(BarcodedItem item : scannedItems) {
			if(!baggedItems.contains(item))
				baggedItems.add(item);
		}
		
		this.discrepancy = false;
		data.setCurrentTotalWeight(station.baggingArea.getCurrentWeight());
		
		//reset scannedItems
		scannedItems.clear();
	}
	
	/*
	 * Function used to determine if an item was placed on the scale at all
	 * Returns a boolean - true meaning item has been placed on scale, exception if it has not when expected
	*/
	public boolean checkItemPlaced() throws OverloadException {
		double currentWeight = ControlUnit.itemBag.scs.baggingArea.getCurrentWeight();
		
	/*	System.out.println("CurrentWeight: " + currentWeight);
		System.out.println("PWeight: " + pastWeight);
		System.out.println();*/
		
		// <= to account if an item is suddenly removed
		if(!baggedItems.isEmpty() && (currentWeight - pastWeight) <= threshold)
			return false;
	
		return true;
	}
	
	public boolean getDiscrepancy() {
		return this.discrepancy;
	}
	
}
