package org.lsmr.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.LookUpProduct;

public class LookUpProductTest {

	private LookUpProduct lup;
	
	/**
	 * Create a product database for testing.
	 * 
	 */	
	
	public void makeTestDatabase() {
		Barcode barcode1 = new Barcode("111");
		BarcodedProduct product1 = new BarcodedProduct(barcode1,"4L Milk",BigDecimal.valueOf(4.87));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode1,product1);
		
		Barcode barcode2 = new Barcode("1473892");
		BarcodedProduct product2 = new BarcodedProduct(barcode2,"570g Whole Wheat Bread",BigDecimal.valueOf(1.57));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode2,product2);
		
		Barcode barcode3 = new Barcode("95975982146");
		BarcodedProduct product3 = new BarcodedProduct(barcode3,"1.89L Lemonade",BigDecimal.valueOf(2.00));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode3,product3);
		
		PriceLookupCode code1 = new PriceLookupCode("12345");
		PLUCodedProduct product4 = new PLUCodedProduct(code1,"Apples",BigDecimal.valueOf(1.19));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code1, product4);
		
		PriceLookupCode code2 = new PriceLookupCode("6283");
		PLUCodedProduct product5 = new PLUCodedProduct(code1,"Celery",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code2, product5);
		
		PriceLookupCode code3 = new PriceLookupCode("93204");
		PLUCodedProduct product6 = new PLUCodedProduct(code1,"Mango",BigDecimal.valueOf(1.27));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code3, product6);
		
	}
	
	@Before
	public void setUp() throws Exception {
		lup = new LookUpProduct();
		makeTestDatabase();
		lup.getAllProducts();
	}

	@Test
	public void testProductCodes() {
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList("111","1473892","95975982146","12345","6283","93204"));
		ArrayList<String> actual = lup.getProductCodes();
		
		int correctCount = 0;
		
		for (int i = 0; i < expected.size(); i++) {
			for (int j = 0; j < actual.size(); j++) {
				if (expected.get(i).equals(actual.get(j))) {
					correctCount++;
				}
			}
		}
		
		assertEquals(correctCount,6);
		
	}
	
	@Test
	public void testProductDescriptions() {

		ArrayList<String> expected = new ArrayList<String>(Arrays.asList("4L Milk","570g Whole Wheat Bread","1.89L Lemonade","Apples","Celery","Mango"));
		ArrayList<String> actual = lup.getProductDescriptions();
		
		int correctCount = 0;
		
		for (int i = 0; i < expected.size(); i++) {
			for (int j = 0; j < actual.size(); j++) {
				if (expected.get(i).equals(actual.get(j))) {
					correctCount++;
				}
			}
		}
		
		assertEquals(correctCount,6);
	}
	
	@Test
	public void testProductPrices() {

		ArrayList<String> expected = new ArrayList<String>(Arrays.asList("4.87","1.57","2.0","1.19","2.47","1.27"));
		ArrayList<String> actual = lup.getProductPrices();
		
		int correctCount = 0;
		
		for (int i = 0; i < expected.size(); i++) {
			for (int j = 0; j < actual.size(); j++) {
				if (expected.get(i).equals(actual.get(j))) {
					correctCount++;
				}
			}
		}
		
		assertEquals(correctCount,6);
	}
	
	@After
	public void tearDown() throws Exception {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
		lup.getProductCodes().clear();
		lup.getProductDescriptions().clear();
		lup.getProductPrices().clear();
	}
}
