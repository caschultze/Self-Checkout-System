package org.lsmr.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData;

public class PlaceItemFailTest {

	public ControlUnit cu;
	CurrentSessionData data;
	
	Barcode one = new Barcode("12345");
	Barcode two = new Barcode("000");
	Barcode three = new Barcode("19823");
	
	double weightOne = 10.00;
	double weightTwo = 5.57;
	double weightThree = 22.09;
	
	Barcode four = new Barcode("2231");
	double weightFour = 2.30;
	
	Barcode five = new Barcode("5555");
	double weightFive = 9.29;
	
	//Test to see how checkWeights works with an empty list
	@Test
	(expected = SimulationException.class)
	public void testNoScannedItems() throws OverloadException {		
		cu.failPlaceItem.checkWeights();
	}
	
	
	@Before
	public void setup() {
		
		BarcodedItem itemOne = new BarcodedItem(one, weightOne);
		BarcodedItem itemTwo = new BarcodedItem(two, weightTwo);
		BarcodedItem itemThree = new BarcodedItem(three, weightThree);
		
		BarcodedProduct firstProduct = new BarcodedProduct(one, "Chess set", new BigDecimal(10.40));
		BarcodedProduct secondProduct = new BarcodedProduct(two, "Monopoly", new BigDecimal(11.12));
		BarcodedProduct thirdProduct = new BarcodedProduct(three, "Watermelon", new BigDecimal(6.80));
		BarcodedProduct fourthProduct = new BarcodedProduct(four, "DVD set", new BigDecimal(18.99));
		BarcodedProduct fifthProduct = new BarcodedProduct(five, "12-pack socks", new BigDecimal(8.25));
		
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(one, firstProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(two, secondProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(three, thirdProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(four, fourthProduct);
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(five, fifthProduct);
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 300;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
		data = cu.sessionData;
		
		data.addScannedItem(itemOne);
		data.addScannedItem(itemTwo);
		data.addScannedItem(itemThree);
	} 
	
	//Test to deteremine if the function predicts the right weight/compares weights properly
	@Test
	public void testCheckWeightsRight() throws OverloadException {
		cu.itemBag.bagItems(data.getScannedItems());
		cu.failPlaceItem.checkWeights();

		BarcodedItem itemFour = new BarcodedItem(four, weightFour);
		ArrayList<BarcodedItem> addedItem = new ArrayList<BarcodedItem>();
		addedItem.add(itemFour);
		
		data.addScannedItem(itemFour);
		cu.itemBag.bagItems(addedItem);
		cu.failPlaceItem.checkWeights();
	}
	
	//Test to determine if the function handles no weight change properly
	@Test
	(expected = SimulationException.class)
	public void testCheckWeightsNoChange() throws OverloadException {
		cu.failPlaceItem.checkWeights();
	}
	
	
	//Test to determine if the program correctly registers when an item has not been placed
	@Test
	public void checkItemPlacedFalse() throws OverloadException {
		System.out.println("CHECKITEMPLACEDFALSE");
		cu.itemBag.bagItems(data.getScannedItems());
		cu.failPlaceItem.checkWeights();
		
		BarcodedItem itemFive = new BarcodedItem(five, weightFive);
		ArrayList<BarcodedItem> addedItem = new ArrayList<BarcodedItem>();
		addedItem.add(itemFive);
		
		data.addScannedItem(itemFive);
		cu.itemBag.bagItems(addedItem);

		cu.failPlaceItem.checkWeights();
		
		boolean actual = cu.failPlaceItem.checkItemPlaced();
		assertEquals(false, actual);
	}
	
	
	//Test to determine if the program correctly registers when an item has been placed
	@Test
	public void testItemPlacedTrue() throws OverloadException {		
		boolean actual = cu.failPlaceItem.checkItemPlaced();
		
		assertEquals(true, actual);
	}

	
}
