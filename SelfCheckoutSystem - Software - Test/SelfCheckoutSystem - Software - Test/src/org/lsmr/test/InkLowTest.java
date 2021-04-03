package org.lsmr.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.ControlUnit;

public class InkLowTest {
	
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
	
	
	@Test (expected = SimulationException.class)
	public void testPrintWhenNoInk() {
		control.inkLow.addPaper(1);
		control.inkLow.print('a');
		
	}
	
	@Test
	public void testNoInkAfterPrintingOneChar() {
		control.inkLow.addInk(1);
		control.inkLow.addPaper(1);
		control.inkLow.print('a');
		control.inkLow.print('\n');
		
		boolean expected = true;
		assertEquals(expected, control.inkLow.getNoInk());
	}
	
	@Test (expected = SimulationException.class)
	public void testPrintWhenNoInkAfterRefill() {
		control.inkLow.addInk(1);
		control.inkLow.addPaper(1);
		control.inkLow.print('a');
		control.inkLow.print('\n');
		control.inkLow.print('b');
	}
}
