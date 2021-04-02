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

public class PayDebitTest {

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
	
	//teardown
	@After
	public void tearDown() {
		control = null;
	}
	
	@Test
	public void testDebitTap() {
		Card card = new Card("Debit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4500450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.debitPayment.debitTap(card);
			int holdNumber = control.paymentProcessing.authorize("4500450045004500", totalPrice);
			actual = control.paymentProcessing.post("4500450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.debitPayment.getTapCheck());
	}
	
	@Test
	public void testDebitTapCardDataRead() {
		Card card = new Card("Debit", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4501450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.debitPayment.debitTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.debitPayment.getType().equals("Debit"))) {
			actual = false;
		}
		if (!(control.debitPayment.getNumber().equals("4501450045004500"))) {
			actual = false;
		}
		if (!(control.debitPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
		if (!(control.debitPayment.getCVV().equals("555"))) {
			actual = false;
		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDebitTapInsufficientFunds() {
		Card card = new Card("Debit", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("60.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.debitPayment.debitTap(card);
			actual = control.paymentProcessing.authorize("4502450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testDebitTapNull() {
		Card card = null;
		try {
			control.debitPayment.debitTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testDebitTapWrongType() {
		Card card = new Card("Membership", "4503450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4503450045004500", "Jake Kim", expiry, "555", available);
		
		try {
			control.debitPayment.debitTap(card);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDebitSwipe() {
		Card card = new Card("Debit", "4504450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4504450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.debitPayment.debitSwipe(card, image);
			int holdNumber = control.paymentProcessing.authorize("4504450045004500", totalPrice);
			actual = control.paymentProcessing.post("4504450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.debitPayment.getSwipeCheck());
	}
	
	@Test
	public void testDebitSwipeCardDataRead() {
		Card card = new Card("Debit", "4505450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4505450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.debitPayment.debitSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.debitPayment.getType().equals("Debit"))) {
			actual = false;
		}
		if (!(control.debitPayment.getNumber().equals("4505450045004500"))) {
			actual = false;
		}
		if (!(control.debitPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
//		if (!(control.debitPayment.getCVV().equals(null))) {
//			actual = false;
//		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDebitSwipeInsufficientFunds() {
		Card card = new Card("Debit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("60.00");
		control.paymentProcessing.addData("4500450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.debitPayment.debitSwipe(card, image);
			actual = control.paymentProcessing.authorize("4500450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testDebitSwipeNullCard() {
		Card card = null;
		BufferedImage image = new BufferedImage(1, 1, 1);
		try {
			control.debitPayment.debitSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testDebitSwipeNullImage() {
		Card card = new Card("Debit", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		BufferedImage image = null;
		try {
			control.debitPayment.debitSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testDebitSwipeWrongType() {
		Card card = new Card("Membership", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", available);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		try {
			control.debitPayment.debitSwipe(card, image);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDebitInsert() {
		Card card = new Card("Debit", "4503450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4503450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.debitPayment.debitInsert(card, "1234");
			int holdNumber = control.paymentProcessing.authorize("4503450045004500", totalPrice);
			actual = control.paymentProcessing.post("4503450045004500", holdNumber, totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual && control.debitPayment.getInsertCheck());
	}
	
	@Test
	public void testDebitInsertCardDataRead() {
		Card card = new Card("Debit", "4505450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4505450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = true;
		
		try {
			control.debitPayment.debitInsert(card, "1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (!(control.debitPayment.getType().equals("Debit"))) {
			actual = false;
		}
		if (!(control.debitPayment.getNumber().equals("4505450045004500"))) {
			actual = false;
		}
		if (!(control.debitPayment.getCardholder().equals("Jake Kim"))) {
			actual = false;
		}
		if (!(control.debitPayment.getCVV().equals("555"))) {
			actual = false;
		}
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDebitInsertInsufficientFunds() {
		Card card = new Card("Debit", "4500450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("60.00");
		control.paymentProcessing.addData("4500450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		int expected = -1;
		int actual = 0;
		
		try {
			control.debitPayment.debitInsert(card, "1234");
			actual = control.paymentProcessing.authorize("4500450045004500", totalPrice);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, actual);
	}
	
	@Test (expected = NullPointerException.class)
	public void testDebitInsertNullCard() {
		Card card = null;
		String pin = "1234";
		try {
			control.debitPayment.debitInsert(card, pin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = NullPointerException.class)
	public void testDebitInsertNullPin() {
		Card card = new Card("Debit", "4501450045004500", "Jake Kim", "555", "1234", true, true);
		String pin = null;
		try {
			control.debitPayment.debitInsert(card, pin);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = SimulationException.class)
	public void testDebitInsertWrongType() {
		Card card = new Card("Membership", "4502450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4502450045004500", "Jake Kim", expiry, "555", available);
		
		try {
			control.debitPayment.debitInsert(card, "1234");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDebitRemove() {
		Card card = new Card("Debit", "4503450045004500", "Jake Kim", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal available = new BigDecimal("100.00");
		control.paymentProcessing.addData("4503450045004500", "Jake Kim", expiry, "555", available);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		
		boolean expected = true;
		boolean actual = false;
		
		try {
			control.debitPayment.debitInsert(card, "1234");
			int holdNumber = control.paymentProcessing.authorize("4503450045004500", totalPrice);
			actual = control.paymentProcessing.post("4503450045004500", holdNumber, totalPrice);
			control.debitPayment.debitRemove();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(expected, control.debitPayment.getRemoveCheck());
	}
	
	@Test
	public void testDebitRemoveNoCardInserted() {
		control.debitPayment.debitRemove();
		
		boolean expected = false;
		
		assertEquals(expected, control.debitPayment.getRemoveCheck());
	}
	
}
