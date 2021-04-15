package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.software.ControlUnit;

public class AddOwnBagTest {
	
	public ControlUnit cu;
	ArrayList<BarcodedItem> bagged = new ArrayList<BarcodedItem>();
	HashMap<Barcode, BarcodedItem> BarcodeDatabase;
	String barcode = "0";
	String itemBar1 = "1234";
	String itemBar2 = "1235";
	Barcode b;
	Barcode itemB1;
	Barcode itemB2;
	BarcodedItem bag;
	BarcodedItem bag2;
	BarcodedItem bin;
	BarcodedItem item1;
	BarcodedItem item2;
	
	
	
	@Before
	public void setup() {
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		
		BarcodeDatabase = new HashMap <Barcode, BarcodedItem>();
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
		
		b = new Barcode(barcode);
		itemB1 = new Barcode(itemBar1);
		itemB2 = new Barcode(itemBar2);		
		bag = new BarcodedItem(b, 2.0);
		bag2 = new BarcodedItem(b, 1.7);
		bin = new BarcodedItem(b, 4.7);
		item1 = new BarcodedItem(itemB1, 22.5);
		item2 = new BarcodedItem(itemB2, 99.5);	
		
		BarcodeDatabase.put(itemB1, item1);
		BarcodeDatabase.put(itemB2, item2);
	}
	
	//Tests adding a bag that doesn't go over the bag weight limit
	@Test
	public void testOneBag() throws OverloadException {
		cu.ownBag.addOwnBag(bag);
		assertTrue("2.0".equals(String.valueOf(cu.ownBag.station.baggingArea.getCurrentWeight())));
	}
	
	//Tests adding multiple valid bags
	@Test
	public void testMultipleBags() throws OverloadException {
		cu.ownBag.addOwnBag(bag);
		cu.ownBag.addOwnBag(bag2);
		assertTrue("3.7".equals(String.valueOf(cu.ownBag.station.baggingArea.getCurrentWeight())));
	}
	
	//Tests adding a bin instead of a bag
	//The bin is heavier than the bag weight limit
	//Bagging area scale should be disabled
	@Test
	public void testBagsTooHeavy() {
		cu.ownBag.addOwnBag(bin);
		assertEquals(true, cu.ownBag.getCheckDisabled());
	}
	
	//Tests the addition of a valid bag that goes over the weight limit of the scale
	@Test
	public void testScaleLimit() throws OverloadException {
		bagged.add(BarcodeDatabase.get(itemB2));
		cu.itemBag.bagItems(BarcodeDatabase.get(itemB2));
		cu.ownBag.addOwnBag(bag);
		assertEquals(true, cu.ownBag.getCheckOverloaded());
	}
	
	//Test adding a bag and then bagging items
	//bag should not show up in items list
	@Test
	public void testItemList() {
		cu.ownBag.addOwnBag(bag);
		bagged.add(BarcodeDatabase.get(itemB1));
		try {
			cu.itemBag.bagItems(BarcodeDatabase.get(itemB1));
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Barcode bb = null;
		Barcode cc = null;
		Barcode expected = null;
		for (BarcodedItem c: cu.itemBag.getBaggedItems()) {
			cc = c.getBarcode();
		}
		assertEquals(expected,cc);
	}
	

}

