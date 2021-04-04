package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class AddPaperInk {

	private SelfCheckoutStation station;
	private boolean paperAdded = false;
	private boolean inkAdded = false;
	
	/**
	 * Constructor for the use case "Attendant adds paper to receipt printer" and "Attendant adds ink to receipt printer".
	 * 
	 * @param station	The SelfCheckoutStation of the system
	 */
	public AddPaperInk(SelfCheckoutStation station){
		this.station = station;
		registerRPListener();
	}
	
	/**
	 * The machine can only print if there is paper and ink added.
	 * 
	 * @return true if there is paper and ink in the machine, false otherwise
	 */
	public boolean CanMachinePrint()
	{
		if (paperAdded && inkAdded) {
			return true;
		}
		return false;
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
				paperAdded = false;
			}

			@Override
			public void outOfInk(ReceiptPrinter printer) {
				inkAdded = false;
			}

			@Override
			public void paperAdded(ReceiptPrinter printer) {
				paperAdded = true;
			}

			@Override
			public void inkAdded(ReceiptPrinter printer) {
				inkAdded = true;
			}
		};
		station.printer.register(rp_listener);
	}
	
	/**
	 * Returns true if the attendant has recently added paper to the machine.
	 * Returns false when the attendant has not added paper to the machine or if the machine is out of paper.
	 */
	public boolean getPaperAdded() {
		return paperAdded;
	}
	
	/**
	 * Returns true if the attendant has recently added ink to the machine.
	 * Returns false when the attendant has not added ink to the machine or if the machine is out of ink.
	 */
	public boolean getInkAdded() {
		return inkAdded;
	}
}
