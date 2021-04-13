package org.lsmr.test;

import static org.junit.Assert.*;

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
import org.lsmr.software.ControlUnit;

public class PayGiftCardTest {

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
	public void isEnabled() {
		control.checkoutStation.cardReader.enable();
		boolean expected = true;
		boolean actual = control.membershipScan.getEnabled();
		assertEquals(expected, actual);
	}
	
	@Test
	public void isDisabled() {
		control.checkoutStation.cardReader.disable();
		boolean expected = true;
		boolean actual = control.membershipScan.getDisabled();
		assertEquals(expected, actual);
	}
	

	@Test
	public void testGiftCardSwipe() throws IOException {
		Card card = new Card("Giftcard", "3003091283", "Carmen Sandiego", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal gcAmount = new BigDecimal("100.00");
		control.paymentProcessing.addData("3003091283", "Carmen Sandiego", expiry, "555", gcAmount);
		
		BigDecimal tempPrice = new BigDecimal("70.00");
		BigDecimal totalPrice = control.sessionData.setAndGetTotalPrice(tempPrice);
		BufferedImage image = new BufferedImage(1, 1, 1);
		
		boolean expected = true;
		boolean actual = false;
		control.giftcardPayment.swipeGiftCard(card, image);
		int holdNumber = control.paymentProcessing.authorize("3003091283", totalPrice);
		actual = control.paymentProcessing.post("3003091283", holdNumber, totalPrice);
		assertEquals(expected, actual && control.giftcardPayment.getSwipeCheck());
	}
	

	@Test (expected = NullPointerException.class)
	public void testGiftCardSwipeNullCard() throws IOException {
		Card card = null;
		BufferedImage image = new BufferedImage(1, 1, 1);
		control.giftcardPayment.swipeGiftCard(card, image);
	}
	
	
	@Test (expected = SimulationException.class)
	public void testGiftCardSwipeWrongType() throws IOException {
		Card card = new Card("Membership", "3003091283", "Carmen Sandiego", "555", "1234", true, true);
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal gcAmount = new BigDecimal("100.00");
		control.paymentProcessing.addData("3003091283", "Carmen Sandiego", expiry, "555", gcAmount);
		BufferedImage image = new BufferedImage(1, 1, 1);
		control.giftcardPayment.swipeGiftCard(card, image);
	}
	

}
