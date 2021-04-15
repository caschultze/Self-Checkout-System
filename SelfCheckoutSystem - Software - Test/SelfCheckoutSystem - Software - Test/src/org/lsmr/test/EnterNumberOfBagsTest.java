package org.lsmr.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.software.ControlUnit;

public class EnterNumberOfBagsTest {

	public ControlUnit control;
	
	@Before
	public void setup() {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
//		int option = 3;
		control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
	}
	
	@After
	public void tearDown() {
		control = null;
	}
	
	@Test
	public void testAddBagsPriceToTotalOneBag() {
		control.enterNumBags.addBagsPriceToTotal(1);
		
		BigDecimal expected = new BigDecimal("0.10");
		
		assertEquals(expected, control.sessionData.getTotal());
	}
	
	@Test
	public void testAddBagsPriceToTotalTenBags() {
		control.enterNumBags.addBagsPriceToTotal(10);
		
		BigDecimal expected = new BigDecimal("1.00");
		
		assertEquals(expected, control.sessionData.getTotal());
	}
	
	@Test
	public void testAddBagsPriceToTotalTenBagsCurrentAmountOwing() {
		control.enterNumBags.addBagsPriceToTotal(10);
		
		BigDecimal expected = new BigDecimal("1.00");
		
		assertEquals(expected, control.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void testAddBagsPriceToTotalNegativeBags() {
		control.enterNumBags.addBagsPriceToTotal(-1);
		
		BigDecimal expected = new BigDecimal("0.00");
		control.sessionData.getTotal();
		BigDecimal actual = control.sessionData.getTotalPrice();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddBagsPriceToTotalNegativeBagsCurrentAmountOwing() {
		control.enterNumBags.addBagsPriceToTotal(-1);
		
		BigDecimal expected = new BigDecimal("0.00");
		BigDecimal actual = control.sessionData.getCurrentAmountOwing();
		
		assertEquals(expected, actual);
	}
}
