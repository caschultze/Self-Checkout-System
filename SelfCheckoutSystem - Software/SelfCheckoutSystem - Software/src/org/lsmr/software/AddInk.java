package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class AddInk extends InkLow {
	
	private boolean inkAdded = false;
	
	/**
	 * Constructor for the use case "Attendant adds paper to receipt printer" and "Attendant adds ink to receipt printer".
	 * 
	 * @param station	The SelfCheckoutStation of the system
	 */
	public AddInk(SelfCheckoutStation station){
//		this.station = station;
//		registerRPListener();
		super(station);
	}
	
	/** 
	 * Calls the addInk method of the station's printer
	 */
	public void addInk(int amount) {
		super.station.printer.addInk(amount);
	}
	
//	/**
//	 * Calls the print method of the station's printer
//	 */
//	public void print (char c) {
//		station.printer.print(c);
//	}
	
//	/**
//	 * The machine can only print if there is paper and ink added.
//	 * 
//	 * @return true if there is paper and ink in the machine, false otherwise
//	 */
//	public boolean CanMachinePrint()
//	{
//		if (paperAdded && inkAdded) {
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Registers a ReceiptPrinter listener
	*/
	@Override
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
				//paperAdded = false;
			}

			@Override
			public void outOfInk(ReceiptPrinter printer) {
				//inkAdded = false;
			}

			@Override
			public void paperAdded(ReceiptPrinter printer) {
				//paperAdded = true;
				
			}

			@Override
			public void inkAdded(ReceiptPrinter printer) {
				inkAdded = true;
				setNoInk(false);
			}
		};
		super.station.printer.register(rp_listener);
	}
	
	/**
	 * Returns true if the attendant has recently added ink to the machine.
	 * Returns false when the attendant has not added ink to the machine or if the machine is out of ink.
	 */
	public boolean getInkAdded() {
		return inkAdded;
	}
	
}
