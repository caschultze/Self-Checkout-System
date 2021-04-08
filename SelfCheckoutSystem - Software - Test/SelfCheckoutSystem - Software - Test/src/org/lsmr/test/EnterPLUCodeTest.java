package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.CurrentSessionData;
import org.lsmr.software.EnterPLUCode;;

public class EnterPLUCodeTest {

	private SelfCheckoutStation station;
	private EnterPLUCode epc;
	private CurrentSessionData data;
	private PLUCodedProduct product1;
	private PLUCodedProduct product2;
	private PLUCodedProduct product3;
	
	public void makeTestDatabase() {
		
		PriceLookupCode code1 = new PriceLookupCode("12345");
		product1 = new PLUCodedProduct(code1,"Apples",BigDecimal.valueOf(1.19));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code1, product1);
		
		PriceLookupCode code2 = new PriceLookupCode("6283");
		product2 = new PLUCodedProduct(code1,"Celery",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code2, product2);
		
		PriceLookupCode code3 = new PriceLookupCode("93204");
		product3 = new PLUCodedProduct(code1,"Mango",BigDecimal.valueOf(1.27));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code3, product3);
		
	}
	
	@Before
	public void setUp() throws Exception {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = {5,10,20,50,100};
		BigDecimal[] coinDenominations = {new BigDecimal(0.05),new BigDecimal(0.10),new BigDecimal(0.25),new BigDecimal(1.00),new BigDecimal(2.00)};
		int scaleMaximum = 136078;	// 300 pounds
		int scaleSensitivity = 10;	// 10 grams

		station = new SelfCheckoutStation(currency,banknoteDenominations,coinDenominations,scaleMaximum,scaleSensitivity);
		
		data = new CurrentSessionData();
		epc = new EnterPLUCode(station,data);
		
		makeTestDatabase();
		
	}
	
	@Test
	public void testGetPLUWeight() {
		PriceLookupCode code1 = new PriceLookupCode("12345");
		PLUCodedItem item1 = new PLUCodedItem(code1,3000);
		
		epc.enterPLUProduct(code1);
		station.scale.add(item1);
		station.scale.remove(item1);
		
		ArrayList<Double> PLUWeights = data.getPLUWeights();
		boolean weightEntered = false;
		
		for (double weights : PLUWeights) {
			if (item1.getWeight()*0.001 == weights) {
				weightEntered = true;
			}
		}
		
		assertTrue(weightEntered);
		
	}
	
	@Test
	public void testGetPLUProductSuccess() {
		PriceLookupCode code = new PriceLookupCode("12345");
		assertEquals(epc.getPLUProduct(code),product1);
		
		PriceLookupCode code2 = new PriceLookupCode("6283");
		assertEquals(epc.getPLUProduct(code2),product2);
		
		PriceLookupCode code3 = new PriceLookupCode("93204");
		assertEquals(epc.getPLUProduct(code3),product3);
		
	}
	
	@Test (expected = NullPointerException.class)
	public void testGetPLUProductFail() {
		PriceLookupCode code = new PriceLookupCode("54321");
		epc.getPLUProduct(code);
			
	}
	
	@Test
	public void testEnterPLUProduct() {
		PriceLookupCode code = new PriceLookupCode("12345");
		epc.enterPLUProduct(code);
		
		ArrayList<PLUCodedProduct> PLUProducts = data.getPLUProducts();	
		boolean productEntered = false;
		
		for (PLUCodedProduct product : PLUProducts) {
			if (product.getPLUCode().equals(code)) {
				productEntered = true;
			}
		}
		
		assertTrue(productEntered);
		
	}
	
	@Test
	public void checkEnable() {
		station.scale.enable();
		boolean expected = true;
		boolean actual = epc.getEnabled();
		assertEquals(expected,actual);
	}
	
	@Test
	public void checkDisable() {
		station.scale.disable();
		boolean expected = true;
		boolean actual = epc.getDisabled();
		assertEquals(expected,actual);
	}
	
	@After
	public void tearDown() throws Exception {
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
		data.getPLUProducts().clear();
		data.getPLUWeights().clear();
	}
	
}
