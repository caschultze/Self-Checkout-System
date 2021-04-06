package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.BarcodeScanner;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;
import org.lsmr.selfcheckout.devices.listeners.BarcodeScannerListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;

public class ControlUnit {
	
	public static SelfCheckoutStation checkoutStation;
	public static ScanItem itemScan;
	public static BagItem itemBag;
	public static CoinPayment payCoin;
	public static BanknotePayment payBanknote;
	public static AddOwnBag ownBag;
	public static FinishAdding addFinish;
	public static PayCredit creditPayment;
	public static PayDebit debitPayment;
	public static PlaceItemFail failPlaceItem;
	public static customerReturnsToAddingItems;
	public static ReceiveChange changeReceive;
	public static ScanMembership membershipScan;
	public static CurrentSessionData sessionData;
	public static CardPaymentProcessing paymentProcessing;
	private static Currency currency;
	private static int[] banknoteDenominations;
	private static BigDecimal[] coinDenominations;
	private static int scaleMaximumWeight;
	private static int scaleSensitivity;
//	private static State currentState;
//	private static int option;
//	
//	enum State {
//		SCAN,
//		BAG,
//		PAY_COIN,
//		PAY_BANKNOTE,
//		ALL_FUNC
//	}
	
	public static void main(String args[]) {
	
		checkoutStation = new SelfCheckoutStation(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		itemScan = new ScanItem(checkoutStation);
		itemBag = new BagItem(checkoutStation);
		payCoin = new CoinPayment(checkoutStation);
		payBanknote = new BanknotePayment(checkoutStation);
		ownBag = new AddOwnBag(checkoutStation);
		addFinish = new FinishAdding(checkoutStation);
		creditPayment = new PayCredit(checkoutStation);
		debitPayment = new PayDebit(checkoutStation);
		failPlaceItem = new PlaceItemFail(checkoutStation);
		customerReturnsToAddingItems = new CustomerReturnsToAddingItems(checkoutStation);
		changeReceive = new ReceiveChange(checkoutStation);
		membershipScan = new ScanMembership(checkoutStation);
		sessionData = new CurrentSessionData();
		paymentProcessing = new CardPaymentProcessing();
		
//		setCurrentState(option);
//		
//		switch(currentState) {
//		case SCAN:
//			
//			// currentState = State.Bag;
//			break;
//			
//		case BAG:
//			
//			break;
//			
//		case PAY_COIN:
//			
//			break;
//			
//		case PAY_BANKNOTE:
//			
//			break;
//			
//		case ALL_FUNC:
//			
//			break;
//		}
	}
	
	public ControlUnit(Currency currency, int[] banknoteDenominations, BigDecimal[] coinDenominations, int scaleMaximumWeight, int scaleSensitivity) {
		
		ControlUnit.currency = currency;
		ControlUnit.banknoteDenominations = banknoteDenominations;
		ControlUnit.coinDenominations = coinDenominations;
		ControlUnit.scaleMaximumWeight = scaleMaximumWeight;
		ControlUnit.scaleSensitivity = scaleSensitivity;
//		ControlUnit.option = option;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	public int[] getBanknoteDenominations() {
		return banknoteDenominations;
	}
	
	public BigDecimal[] getCoinDenominations() {
		return coinDenominations;
	}
	
	public int getScaleMaximumWeight() {
		return scaleMaximumWeight;
	}
	
	public int getScaleSensitivity() {
		return scaleSensitivity;
	}
	
//	public static void setCurrentState(int option) {
//		if (option == 1) {
//			currentState = State.SCAN;
//		} else if (option == 2) {
//			currentState = State.BAG;
//		} else if (option == 3) {
//			currentState = State.PAY_COIN;
//		} else if (option == 4) {
//			currentState = State.PAY_BANKNOTE;
//		} else if (option == 5) {
//			currentState = State.ALL_FUNC;
//		}
//	}

}