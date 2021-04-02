package org.lsmr.test;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.ControlUnit;

public class ScanMembershipTest {

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
	

	//Testing Functionality of CardReader Listener 
	//Checks that the card reader is enabled and accepting swipes
	//expected = true, actual = true
	@Test
	public void isEnabled() {
		cu.checkoutStation.cardReader.enable();
		boolean expected = true;
		boolean actual = cu.membershipScan.getEnabled();
		assertEquals(expected, actual);
	}
	
	//Checks that the card reader is disabled and does not accept swipes
	//expected = true, actual = true
	@Test
	public void isDisabled() {
		cu.checkoutStation.cardReader.disable();
		boolean expected = true;
		boolean actual = cu.membershipScan.getDisabled();
		assertEquals(expected, actual);
	}
	
	//Checks if data is read when swipe occurs
	//expected = true, actual = true 
	@Test
	public void testCardReader() throws IOException {
		String cardType = "Membership";
		String customerNumber = "123456789";
		String cardholder = "Mario Luigi";
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		boolean expected = true;
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
		boolean actual = cu.membershipScan.isDataRead();
		assertEquals(expected, actual);
	}
	
	//Checks swipe function of use case
	//expected = true, actual = true
	@Test
	public void testSwipe() throws IOException {
		String cardType = "Membership";
		String customerNumber = "123456789";
		String cardholder = "Mario Luigi";
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		boolean expected = true;
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
		boolean actual = cu.membershipScan.isMember();
		assertEquals(expected, actual);
	}
	
	//Check swipe function if a wrong card such as credit card is used
	//Should throw a simulation exception error 
	@Test (expected = SimulationException.class)
	public void wrongCardType() throws IOException {
		String cardType = "Credit";
		String customerNumber = "123456789";
		String cardholder = "Mario Luigi";
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
	}
	
	//checks swipe function if the wrong number has been entered
	//number does not belong in member database
	//expected = false, actual = false
	@Test
	public void wrongNumber() throws IOException { 
		String cardType = "Membership";
		String customerNumber = "113456789";
		String cardholder = "Mario Luigi";
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		boolean expected = false;
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
		boolean actual = cu.membershipScan.isMember();
		assertEquals(expected, actual);
	}
	
	//Check swipe function if a null name on card is used
	//there should be no null names on cards/database
	//Should throw a simulation exception error 
	@Test (expected = SimulationException.class)
	public void nullName() throws IOException {
		String cardType = "Membership";
		String customerNumber = "123456789";
		String cardholder = null;
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);

	}
	
	//Check swipe function if a null number on card is used
	//there should be no null numbers on cards/database
	//Should throw a simulation exception error 
	@Test (expected = SimulationException.class)
	public void nullNumber() throws IOException {
		String cardType = "Membership";
		String customerNumber = null;
		String cardholder = "Mario Luigi";
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = new Card(cardType, customerNumber, cardholder, null, null, false, false);
		boolean expected = false;
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
		boolean actual = cu.membershipScan.isMember();
		assertEquals(expected, actual);
	}
	
	//Check swipe function if a null card is used
	//there should be no null cards 
	//Should throw a simulation exception error 
	@Test (expected = NullPointerException.class)
	public void nullCard() throws IOException {
		BufferedImage signature = new BufferedImage(1, 1, 1);
		Card MembershipCard = null;
		boolean expected = false;
		cu.membershipScan.swipeMemberCard(MembershipCard, signature);
		boolean actual = cu.membershipScan.isMember();
		assertEquals(expected, actual);
	}
	
 
}
