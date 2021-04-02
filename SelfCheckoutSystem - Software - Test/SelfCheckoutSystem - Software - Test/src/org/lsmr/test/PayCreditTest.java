package org.lsmr.test;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.external.CardIssuer;
import org.lsmr.software.ControlUnit;

public class PayCreditTest {

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
	public void testCreditTap() {
		Card card = new Card("Credit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4500450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.creditPayment.creditTap(card);
			int holdNumber = control.paymentProcessing.authorize("4500450045004500", totalPrice);
			actual = control.paymentProcessing.post("4500450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.creditPayment.getTapCheck());
	}
	
	@Test
	public void testCreditTapCardDataRead() {
		Card card = new Card("Credit", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4501450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.creditPayment.creditTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.creditPayment.getType().equals("Credit"))) {
			actual = false;
		}
		if (!(control.creditPayment.getNumber().equals("4501450045004500"))) {
			actual = false;
		}
		if (!(control.creditPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
		if (!(control.creditPayment.getCVV().equals("555"))) {
			actual = false;
		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreditTapInsufficientFunds() {
		Card card = new Card("Credit", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("60.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.creditPayment.creditTap(card);
			actual = control.paymentProcessing.authorize("4502450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCreditTapNull() {
		Card card = null;
		try {
			control.creditPayment.creditTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testCreditTapWrongType() {
		Card card = new Card("Membership", "4503450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4503450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		try {
			control.creditPayment.creditTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreditSwipe() {
		Card card = new Card("Credit", "4504450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4504450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.creditPayment.creditSwipe(card, image);
			int holdNumber = control.paymentProcessing.authorize("4504450045004500", totalPrice);
			actual = control.paymentProcessing.post("4504450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.creditPayment.getSwipeCheck());
	}
	
	@Test
	public void testCreditSwipeCardDataRead() {
		Card card = new Card("Credit", "4505450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4505450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.creditPayment.creditSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.creditPayment.getType().equals("Credit"))) {
			actual = false;
		}
		if (!(control.creditPayment.getNumber().equals("4505450045004500"))) {
			actual = false;
		}
		if (!(control.creditPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
//		if (!(control.creditPayment.getCVV().equals(null))) {
//			actual = false;
//		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreditSwipeInsufficientFunds() {
		Card card = new Card("Credit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("60.00");
		control.paymentProcessing.addData("4500450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.creditPayment.creditSwipe(card, image);
			actual = control.paymentProcessing.authorize("4500450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCreditSwipeNullCard() {
		Card card = null;
		BufferedImage image = new BufferedImage(1, 1, 1);
		try {
			control.creditPayment.creditSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testCreditSwipeNullImage() {
		Card card = new Card("Credit", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		BufferedImage image = null;
		try {
			control.creditPayment.creditSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testCreditSwipeWrongType() {
		Card card = new Card("Membership", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", creditLimit);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		try {
			control.creditPayment.creditSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreditInsert() {
		Card card = new Card("Credit", "4503450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4503450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.creditPayment.creditInsert(card, "1234");
			int holdNumber = control.paymentProcessing.authorize("4503450045004500", totalPrice);
			actual = control.paymentProcessing.post("4503450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.creditPayment.getInsertCheck());
	}
	
	@Test
	public void testCreditInsertCardDataRead() {
		Card card = new Card("Credit", "4504450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4504450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.creditPayment.creditInsert(card, "1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.creditPayment.getType().equals("Credit"))) {
			actual = false;
		}
		if (!(control.creditPayment.getNumber().equals("4504450045004500"))) {
			actual = false;
		}
		if (!(control.creditPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
		if (!(control.creditPayment.getCVV().equals("555"))) {
			actual = false;
		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreditInsertInsufficientFunds() {
		Card card = new Card("Credit", "4505450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("60.00");
		control.paymentProcessing.addData("4505450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.creditPayment.creditInsert(card, "1234");
			actual = control.paymentProcessing.authorize("4505450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testCreditInsertNullCard() {
		Card card = null;
		String pin = "1234";
		try {
			control.creditPayment.creditInsert(card, pin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testCreditInsertNullPin() {
		Card card = new Card("Credit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		String pin = null;
		try {
			control.creditPayment.creditInsert(card, pin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testCreditInsertWrongType() {
		Card card = new Card("Membership", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4501450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		try {
			control.creditPayment.creditInsert(card, "1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreditRemove() {
		Card card = new Card("Credit", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("100.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", creditLimit);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.creditPayment.creditInsert(card, "1234");
			int holdNumber = control.paymentProcessing.authorize("4502450045004500", totalPrice);
			actual = control.paymentProcessing.post("4502450045004500", holdNumber, totalPrice);
			control.creditPayment.creditRemove();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, control.creditPayment.getRemoveCheck());
	}
	
	@Test
	public void testCreditRemoveNoCardInserted() {
		control.creditPayment.creditRemove();
		
		boolean expected = false;
		
		assertEquals(expected, control.creditPayment.getRemoveCheck());
	}
	
}

