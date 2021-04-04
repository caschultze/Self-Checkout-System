package org.lsmr.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.software.ControlUnit;

public class AddPaperInkTest {

	public ControlUnit control;
	
	@Before
	public void setup() {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
	}
	
	@After
	public void tearDown() {
		control = null;
	}
	
	@Test 
	public void testPaperAndInkAdded() {
		control.addPaperInk.addInk(1);
		control.addPaperInk.addPaper(1);
		
		assertTrue(control.addPaperInk.getPaperAdded() && control.addPaperInk.getInkAdded());
	}
	
	@Test
	public void testCanMachinePrintTrue() {
		control.addPaperInk.addInk(1);
		control.addPaperInk.addPaper(1);
		
		assertTrue(control.addPaperInk.CanMachinePrint());
	}
	
	@Test 
	public void testCanMachinePrintFalsePaper() {
		control.addPaperInk.addInk(1);
		
		assertFalse(control.addPaperInk.CanMachinePrint());
	}
	
	@Test 
	public void testCanMachinePrintFalseInk() {
		control.addPaperInk.addPaper(1);
		
		assertFalse(control.addPaperInk.CanMachinePrint());
	}
	
}
