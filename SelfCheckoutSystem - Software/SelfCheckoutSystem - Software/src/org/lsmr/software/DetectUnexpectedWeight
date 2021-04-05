package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class DetectUnexpectedWeight {
	//Covers the use case: Station detects that the weight in the bagging area does not conform to expectations 
	//NOTE: the method PlaceItemFail already has a method checkWeights() that does determine if there exists unexpected weights
	//What this class does then is enable the scale listener to detect this/react to it 
	
	private SelfCheckoutStation station;
	private boolean unexpectedWeight;
	
	public DetectUnexpectedWeight(SelfCheckoutStation station) {
		this.station = station;
	}
	
	public void checkValidity(double actualWeight, double expectedWeight) {
		if(actualWeight < 0 || expectedWeight < 0)
			throw new SimulationException("Negative values not accepted");
		
		double diff = Math.abs(actualWeight - expectedWeight);
		System.out.println(diff);
		if(diff <= station.baggingArea.getSensitivity() || ControlUnit.approveWeight.isApproved())
			weightFixed();
		else
			weightWrong();
	}
	
	public void weightWrong() {
		unexpectedWeight = true;
	}
	
	public void weightFixed() {
		unexpectedWeight = false;
	}
	
	public boolean getUnexpectedWeight() {
		return this.unexpectedWeight;
	}
}
