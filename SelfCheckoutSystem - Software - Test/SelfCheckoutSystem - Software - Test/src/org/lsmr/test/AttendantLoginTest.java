package org.lsmr.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.Attendant;
import org.lsmr.software.ControlUnit;

public class AttendantLoginTest {

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
	public void testVerifyLoginJake() {
		boolean actual = control.login.verifyLogin("geesjake", "freshwaterGORILLA@9to5");
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerifyLoginSami() {
		boolean actual = control.login.verifyLogin("samiz50", "monkeLegion!");
		
		boolean expected = true;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerifyLoginRaymondLoggedIn() {
		control.login.verifyLogin("rayray22", "meOrangutanOohOohAhAh111");
		
		boolean expected = true;
		assertEquals(expected, control.sessionData.getAttendantLoggedIn());
	}
	
	@Test
	public void testVerifyLoginAustinLoggedIn() {
		control.login.verifyLogin("Bruh", "#$4CalgarybornChimpanzeeAustron");
		
		boolean expected = true;
		assertEquals(expected, control.sessionData.getAttendantLoggedIn());
	}
	
	@Test
	public void testVerifyLoginRobertLoggedIn() {
		control.login.verifyLogin("rjwalker", "realWorld123");
		
		boolean expected = true;
		assertEquals(expected, control.sessionData.getAttendantLoggedIn());
	}
	
	@Test
	public void testVerifyLoginNonexistentUsername() {
		boolean actual = control.login.verifyLogin("kanyewest", "freshwaterGORILLA@9to5");
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testVerifyLoginWrongPassword() {
		boolean actual = control.login.verifyLogin("Mana24129", "freshwaterGORILLA@9to5");
		
		boolean expected = false;
		assertEquals(expected, actual);
	}
	
	@Test (expected = SimulationException.class)
	public void testVerifyNullUsername() {
		control.login.verifyLogin(null, "monkeLegion!");
	}
	
	@Test (expected = SimulationException.class)
	public void testVerifyNullPassword() {
		control.login.verifyLogin("samiz50", null);
	}
	
	@Test (expected = SimulationException.class)
	public void testVerifyNullUsernameAndPassword() {
		control.login.verifyLogin(null, null);
	}
	
	@Test
	public void testAttendantLogOutBooleanCheck() {
		
		control.login.verifyLogin("rayray22", "meOrangutanOohOohAhAh111");
		control.login.attendantLogOut();
		
		boolean expected = false;
		boolean actual = control.sessionData.getAttendantLoggedIn();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAttendantLogOutObjectCheck() {
		
		control.login.verifyLogin("rayray22", "meOrangutanOohOohAhAh111");
		control.login.attendantLogOut();
		
		boolean expected = true;
		boolean actual = control.sessionData.getCurrentAttendant() == null;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAttendantLogOutCurrentlyNotLoggedIn() {
		control.login.attendantLogOut();
		
		boolean expected = false;
		boolean actual = control.sessionData.getAttendantLoggedIn();
		
		assertEquals(expected, actual);
	}
}
