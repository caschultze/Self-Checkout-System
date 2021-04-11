package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class InkLow {
	
	public SelfCheckoutStation station;
	private static boolean noInk;
		
	/**
	 * Constructor for the use case "Station detects that the ink in a receipt printer is low."
	 * 
	 * @param station	The SelfCheckoutStation of the system
	 */
	public InkLow (SelfCheckoutStation station){
		this.station = station; 
		registerRPListener();
	}
	
	/**
	 * Calls the print method of the station's printer
	 */
	public void print (char c) {
		station.printer.print(c);
	}
	
	/** 
	 * Calls the addPaper method of the station's printer
	 */
	public void addPaper(int amount) {
		station.printer.addPaper(amount);
	}
	
	/** 
	 * Calls the addInk method of the station's printer
	 */
	public void addInk(int amount) {
		station.printer.addInk(amount);
	}
	
	/**
	 * Registers a ReceiptPrinter listener
	 */
	public void registerRPListener() {
		
		ReceiptPrinterListener rp_listener = new ReceiptPrinterListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void outOfPaper(ReceiptPrinter printer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void outOfInk(ReceiptPrinter printer) {
				noInk = true;
				
			}

			@Override
			public void paperAdded(ReceiptPrinter printer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void inkAdded(ReceiptPrinter printer) {
				//noInk = false;
				
			}
			
		
		};
		
		station.printer.register(rp_listener);
		
			
	}
	
	/**
	 * Returns true if the printer is out of ink.
	 * Otherwise false.
	 */
	public boolean getNoInk() {
		return noInk;
	}
	
	public void setNoInk(boolean flag) {
		noInk = flag;
	}
	
	
}

