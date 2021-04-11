package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class AddPaper extends PaperLow {

	private SelfCheckoutStation station;
	private boolean paperAdded = false;
	
	/**
	 * Constructor for the use case "Attendant adds paper to receipt printer" and "Attendant adds ink to receipt printer".
	 * 
	 * @param station	The SelfCheckoutStation of the system
	 */
	public AddPaper(SelfCheckoutStation station){
//		this.station = station;
//		registerRPListener();
		super(station);
	}
	
	/** 
	 * Calls the addPaper method of the station's printer
	 */
	public void addPaper(int amount) {
		super.station.printer.addPaper(amount);
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
				paperAdded = true;
				setNoPaper(false);
			}

			@Override
			public void inkAdded(ReceiptPrinter printer) {
				//inkAdded = true;
			}
		};
		super.station.printer.register(rp_listener);
	}
	
	/**
	 * Returns true if the attendant has recently added paper to the machine.
	 * Returns false when the attendant has not added paper to the machine or if the machine is out of paper.
	 */
	public boolean getPaperAdded() {
		return paperAdded;
	}
	
}
