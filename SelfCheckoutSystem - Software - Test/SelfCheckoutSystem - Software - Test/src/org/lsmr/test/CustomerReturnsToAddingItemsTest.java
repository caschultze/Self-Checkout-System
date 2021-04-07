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
import org.lsmr.software.CustomerReturnsToAddingItems;

//This test is completed but has not been ran yet

public class CustomerReturnsToAddingItemsTest {
	
	public ControlUnit cu;
	CurrentSessionData data;
	ArrayList<BarcodedItem> itemsToBeBagged;
	

	
	@Before
	public void setup() {
	
		// Create a database of items
		HashMap<Barcode, BarcodedItem> itemsDatabase = new HashMap<Barcode, BarcodedItem>();
		itemsDatabase.put(new Barcode("1111"), new BarcodedItem(new Barcode("1111"), 25));
		itemsDatabase.put(new Barcode("2222"), new BarcodedItem(new Barcode("2222"), 20));
		itemsDatabase.put(new Barcode("3333"), new BarcodedItem(new Barcode("3333"), 500));
		itemsDatabase.put(new Barcode("4444"), new BarcodedItem(new Barcode("4444"), 5));
		this.itemsDatabase = itemsDatabase;
		
		Barcode itemCode1 = new Barcode("1111");
		Barcode itemCode2 = new Barcode("2222");
		Barcode itemCode3 = new Barcode("3333");
		Barcode itemCode4 = new Barcode("4444");

		
		itemsToBeBagged.add(itemsDatabase.get(itemCode1));
		itemsToBeBagged.add(itemsDatabase.get(itemCode2));
		itemsToBeBagged.add(itemsDatabase.get(itemCode3));
		itemsToBeBagged.add(itemsDatabase.get(itemCode4));
		
		//create a selfcheckout system
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 300;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);	
		cu.main(null);
		data = cu.sessionData;
		cu.CustomerReturnsToAddingItems.setHelpNeeded(false);
		
	}
	
	//test for when the user does not need help, all items are expected to be bagged
	
	@Test
	public void checkItemPlacedWhenHelpNotNeeded() {
		
		cu.customerReturnsToAddingItems.setHelpNeeded(false);
		cu.customerReturnsToAddingItems.returnAndBagItems(itemsToBeBagged);
		assertTrue(cu.itemBag.getBaggedItems.equals(itemsToBeBagged));
	}
	
	//test for when the user does need help and tries to bag an item, all items are expected not be bagged

	@Test
	public void checkItemPlacedWhenHelpNeeded() {
		
		cu.customerReturnsToAddingItems.setHelpNeeded(true);
		cu.customerReturnsToAddingItems.returnAndBagItems(itemsToBeBagged);
		assertFalse(itemBag.getBaggedItems.equals(itemsToBeBagged));
		
		
	}
	
	
	
	
	
	
}
