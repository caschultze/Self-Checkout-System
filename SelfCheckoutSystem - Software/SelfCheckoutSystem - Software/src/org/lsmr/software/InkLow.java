package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ReceiptPrinter;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ReceiptPrinterListener;

public class InkLow {
	
	private SelfCheckoutStation station;
	private boolean noInk;
		
	public InkLow (SelfCheckoutStation station){
		this.station = station; 
		registerRPListener();
	
	}
	
	public void print (char c) {
		station.printer.print(c);

	}
	
	public void addPaper(int amount) {
		station.printer.addPaper(amount);
	}
	
	public void addInk(int amount) {
		station.printer.addInk(amount);
	}
	
	
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
				noInk = false;
				
			}
			
		
		};
		
		station.printer.register(rp_listener);
		
			
	}
	
	public boolean getNoInk() {
		return noInk;
	}
	
	
}

