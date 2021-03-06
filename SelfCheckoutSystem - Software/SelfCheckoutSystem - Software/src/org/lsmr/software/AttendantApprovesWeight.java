package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SimulationException;

public class AttendantApprovesWeight {
	
	CurrentSessionData data = ControlUnit.sessionData;
	private boolean approved;
	private double expectedWeight;
	private double actualWeight;
	private double maxDiscrepancy;
	
	/*Use case works under the idea that the employee will accept weight discrepancy's within a range
	/E.x.: A watermelon 5 pounds under than expected is permitted, a watermelon 50 pounds over is ridiculous
	 * double expectedWeight = the expected/proper weight of the item
	 * double actualWeight = the weight of the item on the scale, should be unequal to expectedWeight
	 * double maxDiscrepancy =
	*/

	public void checkDiscrepancy(double expectedWeight, double actualWeight, double maxDiscrepancy) {
		//If there is no attendant present, then throw an exception as this use case should not even exist without one
		if(data.getCurrentAttendant() == null)
			throw new SimulationException("There is no attendant available to help");
		if(expectedWeight < 0 || actualWeight < 0)
			throw new SimulationException("Negative Weights not Accepted");
		
		this.expectedWeight = expectedWeight;
		this.actualWeight = actualWeight;
		this.maxDiscrepancy = maxDiscrepancy;
		
		//Use the absolute value here to determine the difference between the expected/actual weight
		if(Math.abs(expectedWeight - actualWeight) <= maxDiscrepancy) 
			this.approved = true;
		else
			this.approved = false;
	}
	
	
	public boolean isApproved() {
		return this.approved;
	}

}
