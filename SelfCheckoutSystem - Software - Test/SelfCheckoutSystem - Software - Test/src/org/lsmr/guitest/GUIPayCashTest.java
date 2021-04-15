package org.lsmr.guitest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.GUIPayCash;
import org.lsmr.software.MainGUI;

public class GUIPayCashTest {
	@Before
	public void setup() {
		MainGUI.main(null);
	}
	
	@Test
	public void test100Button() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("100"));
		GUIPayCash.hundred.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		assertEquals(zero, ControlUnit.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void test50Button() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("50"));
		GUIPayCash.fifty.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		assertEquals(zero, ControlUnit.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void test20Button() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("20"));
		GUIPayCash.twenty.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		assertEquals(zero, ControlUnit.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void test10Button() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("10"));
		GUIPayCash.ten.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		assertEquals(zero, ControlUnit.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void test5Button() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("5"));
		GUIPayCash.five.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		assertEquals(zero, ControlUnit.sessionData.getCurrentAmountOwing());
	}
	
	@Test
	public void testtoonieButton() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("2"));
		GUIPayCash.toonie.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		boolean result = zero.compareTo(ControlUnit.sessionData.getCurrentAmountOwing()) == 0;
		assertTrue(result);
	}
	
	@Test
	public void testquarterButton() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("0.25"));
		GUIPayCash.quarter.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		boolean result = zero.compareTo(ControlUnit.sessionData.getCurrentAmountOwing()) == 0;
		assertTrue(result);
	}
	
	@Test
	public void testdimeButton() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("0.1"));
		GUIPayCash.dime.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		boolean result = zero.compareTo(ControlUnit.sessionData.getCurrentAmountOwing()) == 0;
		assertTrue(result);
	}
	
	@Test
	public void testnickelButton() {
		ControlUnit.sessionData.setAndGetTotalPrice(new BigDecimal("0.05"));
		GUIPayCash.nickel.doClick();
		BigDecimal zero = BigDecimal.ZERO;
		boolean result = zero.compareTo(ControlUnit.sessionData.getCurrentAmountOwing()) == 0;
		assertTrue(result);
	}
}
