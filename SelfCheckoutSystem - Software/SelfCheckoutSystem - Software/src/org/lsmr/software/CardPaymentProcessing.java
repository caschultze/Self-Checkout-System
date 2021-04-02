package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;

import org.lsmr.selfcheckout.external.CardIssuer;

public class CardPaymentProcessing {

	private CardIssuer BMO = new CardIssuer("Bank of Montreal");
	private CardIssuer Scotiabank = new CardIssuer("Bank of Nova Scotia");
	private CardIssuer CIBC = new CardIssuer("Canadian Imperial Bank of Commerce");
	private CardIssuer RBC = new CardIssuer("Royal Bank of Canada");
	private CardIssuer TD = new CardIssuer("Toronto-Dominion Bank");
	private CardIssuer Tangerine = new CardIssuer("Tangerine Bank");

	private String BMO_digits = "4500";
	private String Scotiabank_digits = "4501";
	private String CIBC_digits = "4502";
	private String RBC_digits = "4503";
	private String TD_digits = "4504";
	private String Tangerine_digits = "4505";
	
	private static final HashMap<String, CardIssuer> banks = new HashMap<>();
	public CurrentSessionData sessionData = new CurrentSessionData();
	private boolean paid = false;
	
	public CardPaymentProcessing() {
		banks.put(BMO_digits, BMO);
		banks.put(Scotiabank_digits, Scotiabank);
		banks.put(CIBC_digits, CIBC);
		banks.put(RBC_digits, RBC);
		banks.put(TD_digits, TD);
		banks.put(Tangerine_digits, Tangerine);
	}
	
	public CardIssuer getBank(String fourDigits) {
		return banks.get(fourDigits);
	}
	
	public void addData(String number, String cardholder, Calendar expiry, String cvv, BigDecimal amount) {
		
		getBank(number.substring(0, 4)).addCardData(number, cardholder, expiry, cvv, amount);
	}
	
	public int authorize(String cardNumber, BigDecimal holdAmount) {
		
		return getBank(cardNumber.substring(0, 4)).authorizeHold(cardNumber, holdAmount);
	}
	
	public boolean post(String cardNumber, int holdNumber, BigDecimal actualAmount) {
		
		paid = getBank(cardNumber.substring(0, 4)).postTransaction(cardNumber, holdNumber, actualAmount);
		
		if (paid) {
			sessionData.getCurrentAmountOwing(actualAmount);
		}
		
		return paid;
	}
	
	public boolean getPaid() {
		return paid;
	}
}
