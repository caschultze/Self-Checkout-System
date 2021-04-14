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


//NOT DONE YET, I'M JUST PUSHING THIS B/C GITHUB IS WEIRD AND I NEED TO PUSH THIS BEFORE
//I CREATE A NEW FILE IN THE BRANCH B/C I DON'T KNOW HOW ELSE TO CREATE A NEW FILE 
//EXCEPT THROUGH GITHUB ITSELF

public class CustomerReturnsToAddingItemsTest {
	
	public ControlUnit cu;
	
	private Barcode one = new Barcode("12345");
	private Barcode two = new Barcode("000");
	private Barcode three = new Barcode("19823");
	private BarcodedItem itemOne;
	private BarcodedItem itemTwo;
	private BarcodedItem itemThree;
	
	
	public ArrayList<BarcodedItem> itemsToBeBagged = new ArrayList<BarcodedItem>();
	
	@Before
	public void setup() {
	
		double weightOne = 56.3;
		double weightTwo = 100.6;
		double weightThree = 462.3;
		
		BarcodedItem itemOne = new BarcodedItem(one, weightOne);
		BarcodedItem itemTwo = new BarcodedItem(two, weightTwo);
		BarcodedItem itemThree = new BarcodedItem(three, weightThree);
		
		BarcodedProduct firstProduct = new BarcodedProduct(one, "Chess set", new BigDecimal(10.40));
		BarcodedProduct secondProduct = new BarcodedProduct(two, "Monopoly", new BigDecimal(11.12));
		BarcodedProduct thirdProduct = new BarcodedProduct(three, "Watermelon", new BigDecimal(6.80));
		
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(one, firstProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(two, secondProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(three, thirdProduct);
		
		itemsToBeBagged.add(itemOne);
		itemsToBeBagged.add(itemTwo); 
		itemsToBeBagged.add(itemThree);
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 1000;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);	
		cu.main(null);
		cu.customerReturnsToAddingItems.setHelpNeeded(false);
		
	}
	
	//test for when the user does not need help, all items are expected to be bagged
	
	@Test
	public void checkItemPlacedWhenHelpNotNeeded() {
		
		for (BarcodedItem item : itemsToBeBagged) {
			cu.customerReturnsToAddingItems.returnAndBagItems(item);
		}
		
		assertEquals(3,cu.customerReturnsToAddingItems.getCountWeightChanged());
	}
	
	//test for when the user doe need help and tries to bag an item, all items are expected not be bagged

	
	@Test
	public void checkItemPlacedWhenHelpNeeded() {
		
		if (!cu.customerReturnsToAddingItems.getHelpNeeded()) {
			cu.customerReturnsToAddingItems.setHelpNeeded(true);
		}
		
		for (BarcodedItem item : itemsToBeBagged) {
			cu.customerReturnsToAddingItems.returnAndBagItems(item);
		}
		
		assertEquals(0,cu.customerReturnsToAddingItems.getCountWeightChanged());
		
	}
	
	
	
	
	
	
}
