package org.lsmr.test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.software.ControlUnit;

public class RemovesItemsTest {
	
	public ControlUnit control;
	private HashMap<Barcode, BarcodedItem> itemsDatabase;
	
	@Before
	public void setup() {
		
		// Create a database of items
		HashMap<Barcode, BarcodedItem> itemsDatabase = new HashMap<Barcode, BarcodedItem>();
		itemsDatabase.put(new Barcode("1111"), new BarcodedItem(new Barcode("1111"), 25));
		itemsDatabase.put(new Barcode("2222"), new BarcodedItem(new Barcode("2222"), 20));
		itemsDatabase.put(new Barcode("3333"), new BarcodedItem(new Barcode("3333"), 500));
		itemsDatabase.put(new Barcode("4444"), new BarcodedItem(new Barcode("4444"), 5));
		this.itemsDatabase = itemsDatabase;
		
		// Create SelfCheckoutSystem
		Currency currency = Currency.getInstance("CAD");
		int[] banknoteDenominations = new int[]{5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("0.50"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 10000;
		int scaleSensitivity = 10;
		control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
		
	}
	
	// Testing the bagging area for use case "Customer removes purchased items from bagging area"
	
	@Test
	public void testRemoveAnItem() {
		
		Barcode itemCode = new Barcode("2222");
		try {
			control.itemBag.bagItems(itemsDatabase.get(itemCode));
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		control.removesItems.removesItems(itemsDatabase.get(itemCode));
		assertEquals(control.removesItems.getCountWeightChanged(), 2);
		
	}
	
	@Test
	public void testCurrentWeightInGramsSingleItem() throws OverloadException {
		
		Barcode itemCode = new Barcode("2222");
		
		control.itemBag.bagItems(itemsDatabase.get(itemCode));
		assertTrue("20.0".equals(String.valueOf(control.itemBag.scs.baggingArea.getCurrentWeight())));
		
		control.removesItems.removesItems(itemsDatabase.get(itemCode));
		assertTrue("0.0".equals(String.valueOf(control.removesItems.station.baggingArea.getCurrentWeight())));
		
	}
	
	@Test
	public void testRemovesMultipleItems() {
		
		Barcode itemCode0 = new Barcode("1111");
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("3333");
		try {
		control.itemBag.bagItems(itemsDatabase.get(itemCode0));
		control.itemBag.bagItems(itemsDatabase.get(itemCode1));
		control.itemBag.bagItems(itemsDatabase.get(itemCode2));
		}catch(Exception e){}
		control.removesItems.removesItems(itemsDatabase.get(itemCode0));
		control.removesItems.removesItems(itemsDatabase.get(itemCode1));
		control.removesItems.removesItems(itemsDatabase.get(itemCode2));
		
		assertEquals(control.removesItems.getCountWeightChanged(), 6);
		
	}
	
	@Test
	public void testCurrentWeightInGramsMultipleItems() throws OverloadException {
		
		Barcode itemCode0 = new Barcode("1111");
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("3333");
		
		control.itemBag.bagItems(itemsDatabase.get(itemCode0));
		control.itemBag.bagItems(itemsDatabase.get(itemCode1));
		control.itemBag.bagItems(itemsDatabase.get(itemCode2));
		
		assertTrue("545.0".equals(String.valueOf(control.itemBag.scs.baggingArea.getCurrentWeight())));
		
		control.removesItems.removesItems(itemsDatabase.get(itemCode0));
		control.removesItems.removesItems(itemsDatabase.get(itemCode1));
		control.removesItems.removesItems(itemsDatabase.get(itemCode2));
		
		assertTrue("0.0".equals(String.valueOf(control.removesItems.station.baggingArea.getCurrentWeight())));
		
	}
	
	@Test
	public void testRemoveWeightBelowSensitivity() {
		
		Barcode itemCode = new Barcode("4444");
		try {
			control.itemBag.bagItems(itemsDatabase.get(itemCode));
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		control.removesItems.removesItems(itemsDatabase.get(itemCode));
		assertEquals(control.removesItems.getCountWeightChanged(), 0);
		
	}

}
