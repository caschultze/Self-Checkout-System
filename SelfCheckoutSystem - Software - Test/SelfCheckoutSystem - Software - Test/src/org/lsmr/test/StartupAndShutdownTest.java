package org.lsmr.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.StartupAndShutdown;

public class StartupAndShutdownTest {
	
	public ControlUnit control;
	public StartupAndShutdown Power;

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
		Power = new StartupAndShutdown(control.checkoutStation);
	}
	
	@Test
	public void testStartUp () {
		Power.startup();
		assertTrue(Power.PowerOn);
	}
	
	@Test
	public void testShutdown () {
		Power.shutdown();
		assertFalse(Power.PowerOn);
	}
	
}
