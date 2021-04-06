package org.lsmr.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.software.BlockStationSetup;
import org.lsmr.software.ControlUnit;

public class BlockStationSetupTest {
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
	
	@Test
	public void testUnblockedToBlocked() {
		control.blocker.blockStation();
		assertTrue(control.blocker.isBlocked());
	}
	
	@Test
	public void testBlockedToUnblocked() {
		control.blocker.blockStation();
		control.blocker.unblockStation();
		assertFalse(control.blocker.isBlocked());
	}
	
	@Test
	public void testBlockedToBlocked() {
		control.blocker.blockStation();
		control.blocker.blockStation();
		assertTrue(control.blocker.isBlocked());
	}
	
	@Test
	public void testUnblockedToUnblocked() {
		control.blocker.unblockStation();
		control.blocker.unblockStation();
		assertFalse(control.blocker.isBlocked());
	}
}