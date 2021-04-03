package org.lsmr.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.LookUpProduct;

public class LookUpProductTest {

	private SelfCheckoutStation station;
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
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = {5,10,20,50,100};
		BigDecimal[] coinDenominations = {new BigDecimal(0.05),new BigDecimal(0.10),new BigDecimal(0.25),new BigDecimal(1.00),new BigDecimal(2.00)};
		int scaleMaximum = 136078;	// 300 pounds
		int scaleSensitivity = 10;	// 10 grams

		station = new SelfCheckoutStation(currency,banknoteDenominations,coinDenominations,scaleMaximum,scaleSensitivity);
		lup = new LookUpProduct(station);
		
		makeTestDatabase();
		
	}

	@Test
	public void testBarcodedProduct() {
		BarcodedProduct product = new BarcodedProduct(new Barcode("95975982146"),"1.89L Lemonade",BigDecimal.valueOf(2.00));
		assertTrue(lup.checkBarcodedProductInDatabase(product));
		
		BarcodedProduct product2 = new BarcodedProduct(new Barcode("029348"),"",BigDecimal.valueOf(2.20));
		assertFalse(lup.checkBarcodedProductInDatabase(product2));
	}
	
	@Test
	public void testPLUCodedProduct() {
		PLUCodedProduct product = new PLUCodedProduct(new PriceLookupCode("6283"),"Celery",BigDecimal.valueOf(2.47));
		assertTrue(lup.checkPLUProductInDatabase(product));
		
		PLUCodedProduct product2 = new PLUCodedProduct(new PriceLookupCode("1032"),"",BigDecimal.valueOf(3.86));
		assertFalse(lup.checkPLUProductInDatabase(product2));
		
	}
	
	@After
	public void tearDown() throws Exception {
		ProductDatabases.BARCODED_PRODUCT_DATABASE.clear();
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
	}
}
