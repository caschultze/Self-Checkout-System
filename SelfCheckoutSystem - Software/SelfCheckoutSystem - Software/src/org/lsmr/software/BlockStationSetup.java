package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class BlockStationSetup {
	private SelfCheckoutStation station;
	private boolean blockedFlag = false;
	
	public BlockStationSetup(SelfCheckoutStation station) {
		this.station = station;
	}
	
	public boolean isBlocked() {
		return blockedFlag;
	}
	
	public void blockStation() {
		station.scale.disable();
		station.baggingArea.disable();
		station.printer.disable();
		station.cardReader.disable();
		station.mainScanner.disable();
		station.handheldScanner.disable();
		station.banknoteInput.disable();
		station.banknoteOutput.disable();		
		station.coinSlot.disable();
		station.banknoteInput.disable();
		 
		blockedFlag = true;	
	}
	
	public void unblockStation() {
		station.scale.enable();
		station.baggingArea.enable();
		station.printer.enable();
		station.cardReader.enable();
		station.mainScanner.enable();
		station.handheldScanner.enable();
		station.banknoteInput.enable();
		station.banknoteOutput.enable();		
		station.banknoteValidator.enable();
		station.coinValidator.enable();
		station.coinSlot.enable();
		station.banknoteInput.enable(); 
		
		blockedFlag = false;	
	}
}