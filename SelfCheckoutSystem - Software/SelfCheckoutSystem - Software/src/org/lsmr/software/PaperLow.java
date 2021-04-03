package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class PaperLow {
	
	private SelfCheckoutStation station;
	private boolean noPaper;
	
	/**
	 * Constructor for the use case "Station detects that the paper in a receipt printer is low."
	 * 
	 * @param station	The SelfCheckoutStation of the system
	 */
	public PaperLow (SelfCheckoutStation station){
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
	private void registerRPListener() {
		
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
				noPaper = true;
				
			}

			@Override
			public void outOfInk(ReceiptPrinter printer) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void paperAdded(ReceiptPrinter printer) {
				noPaper = false;
				
			}

			@Override
			public void inkAdded(ReceiptPrinter printer) {
				// TODO Auto-generated method stub
				
			}
		};
		
		station.printer.register(rp_listener);
	}
	
	/**
	 * Returns true if the printer is out of paper.
	 * Otherwise false.
	 */
	public boolean getNoPaper() {
		return noPaper;
	}
	
	
}
