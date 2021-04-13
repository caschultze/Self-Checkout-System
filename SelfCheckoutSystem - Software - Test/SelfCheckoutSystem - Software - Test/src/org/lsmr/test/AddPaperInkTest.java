package org.lsmr.test;

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
		control.addInk.addInk(1);
		control.addPaper.addPaper(1);
		
		assertTrue(control.addPaper.getPaperAdded() && control.addInk.getInkAdded());
	}
	
	@Test
	public void testAddInkThenRunOut() {
		control.addPaper.addPaper(5);
		control.addPaper.addInk(1);
		control.addPaper.print('a');
		
		assertTrue(control.addInk.getInkAdded());
	}
	
	@Test
	public void testAddPaperThenRunOut() {
		
		control.addPaper.addPaper(1);
		control.addInk.addInk(5);
		control.addPaper.print('\n');
		
		assertTrue(control.addPaper.getPaperAdded());
	}
	
	@Test
	public void testOutOfPaper() {
		control.addPaper.addPaper(2); //start with 2 paper 
		
		for (int i = 0; i < 2; i++) { // use up two paper
			control.addPaper.print('\n');
		}
		
		assertTrue(control.paperLow.getNoPaper());
		
	}
	
	@Test
	public void testOutOfInk() {
		control.addInk.addPaper(2);
		control.addInk.addInk(5);
		
		for (int i = 0; i < 5; i++) { 
			control.addInk.print('*');
		}
		
		assertTrue(control.inkLow.getNoInk());
		
	}
	
}
