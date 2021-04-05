package org.lsmr.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.ControlUnit;

public class EnterMembershipTest {

	public ControlUnit cu;
	
	@Before
	public void setup() {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;	

		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
	}
	
	//number does not belong in member database
		//expected = false, actual = false
		@Test
		public void enterNumber() throws IOException { 
			String customerNumber = "123456789";
			boolean expected = true;
			cu.membershipEnter.enterNumber(customerNumber);
			boolean actual = cu.membershipEnter.isMember();
			assertEquals(expected, actual);
		}
		
	
	//number does not belong in member database
	//expected = false, actual = false
	@Test
	public void wrongNumber() throws IOException { 
		String customerNumber = "113456789";
		boolean expected = false;
		cu.membershipEnter.enterNumber(customerNumber);
		boolean actual = cu.membershipEnter.isMember();
		assertEquals(expected, actual);
	}
	

}
