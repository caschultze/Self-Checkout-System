package org.lsmr.software;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;

public class ScanItem {

	public SelfCheckoutStation scs;
	public BarcodeScannerListener bsl;
	private static boolean scanSuccess = false;
	private int numSuccess = 0;
	private boolean disableCheck = false;
	private boolean enableCheck;
	public CurrentSessionData data;

	
	public ScanItem(SelfCheckoutStation checkoutStation) {
		scs = checkoutStation;
		registerListeners();
	}
	 
	public void registerListeners() {
		bsl = new BarcodeScannerListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				enableCheck = true;		
			} 

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				disableCheck = true;
			}

			@Override
			public void barcodeScanned(BarcodeScanner barcodeScanner, Barcode barcode) {
				numSuccess++;
				setScanSuccess(true);
			}
		};
		scs.mainScanner.register(bsl);
	}
	
	//getter method to check state of scanner
	public boolean getDisableCheck() {
		 return disableCheck;
	}
	
	//getter method to check state of scanner
	 public boolean getEnableCheck() {
		 return enableCheck;
	 }  
	 
	 //getter method for how many were scanned
	 public int getNumSuccess() {
		 return numSuccess;
	 }  
	 
	//Scan Function will accept an arraylist of Barcoded items
	//For each item, it will check the scan status
	 public void scanItems(ArrayList <BarcodedItem> BarcodeList) {

		 if (BarcodeList == null) {
			 throw new NullPointerException("No argument may be null");
		 }
		 
		 for (BarcodedItem currentBarcode : BarcodeList) {
			scanSuccess = false;
			scs.mainScanner.scan(currentBarcode);
			if (scanSuccess) {
				data.addScannedItem(currentBarcode);
//				data.addScannedProducts(currentBarcode.getBarcode());
			}
		 }
	}

	 //setter for scan success
	public static void setScanSuccess(boolean scanSuccess) {
		ScanItem.scanSuccess = scanSuccess;
	}

}
