package org.lsmr.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;

import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData; 

public class FinishAddingTest {
	
	public ControlUnit control;
	
	ArrayList<BarcodedItem> bagged = new ArrayList<BarcodedItem>();
	HashMap<Barcode, BarcodedItem> BarcodeDatabase;
	
	public BarcodedItem pencil; 
	public BarcodedItem tv; 
	public BarcodedItem laptop; 
	
	public Barcode code1;
	public Barcode code2;
	public Barcode code3;
	
	public BigDecimal total;
	
	public ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();

	
	@Before
	public void setup() {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
		
//		data = control.sessionData;
		code1 = new Barcode("1234");
		code2 = new Barcode("1235");
		code3 = new Barcode("1236");
		
		pencil = new BarcodedItem(code1, 0.2);
		tv = new BarcodedItem(code2, 10.5);
		laptop = new BarcodedItem(code3, 234.5);
		
		control.sessionData.addScannedItem(pencil);
		control.sessionData.addScannedItem(tv);
		control.sessionData.addScannedItem(laptop); 
		
		BarcodeList.add(pencil);
		BarcodeList.add(tv);
		BarcodeList.add(laptop);
		
		total = new BigDecimal("70.00"); 
	}
	
	@After 
	public void tearDown() { 
		control = null;
	}
	
	@Test
	public void testScannerDisabled() throws SimulationException { 
		
		control.addFinish.finish(0, total);
		
		control.itemScan.scanItems(BarcodeList);
		boolean expected = true;
		boolean actual = control.itemScan.getDisableCheck();
		assertEquals(expected, actual); 
	}
	
	@Test
	public void testBaggingAreaDisabled() { 
		
		control.addFinish.finish(0, total);
		
		control.itemBag.bagItems(bagged);
		
		boolean expected = true; 
		boolean actual = control.itemBag.getDisabledCheck();
		
		assertEquals(expected, actual);
	}

	
}
