package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.CurrentSessionData;
import org.lsmr.software.EnterPLUCode;;

public class EnterPLUCodeTest {

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
		
		data = new CurrentSessionData();
		epc = new EnterPLUCode(data);
		
		makeTestDatabase();
		
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
	
	
	@After
	public void tearDown() throws Exception {
		ProductDatabases.PLU_PRODUCT_DATABASE.clear();
	}
	
}