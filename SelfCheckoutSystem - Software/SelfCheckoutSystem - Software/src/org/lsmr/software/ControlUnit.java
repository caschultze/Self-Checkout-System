package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Currency;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

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
	public static PayGiftCard giftcardPayment;
	public static PlaceItemFail failPlaceItem;
	public static CustomerReturnsToAddingItems customerReturnsToAddingItems;
	public static ReceiveChange changeReceive;
	public static ScanMembership membershipScan;
	public static EmptyBanknoteStorage emptyBanknote;
	public static EmptyCoinStorage emptyCoin;
	public static EnterMembership membershipEnter;
	public static CurrentSessionData sessionData;
	public static CardPaymentProcessing paymentProcessing;
	public static BlockStationSetup blocker;
	public static AttendantLogin login;
	public static EnterNumberOfBags enterNumBags;
	public static AttendantApprovesWeight approveWeight;
	public static DetectUnexpectedWeight checkWrongWeight;
	public static PaperLow paperLow;
	public static InkLow inkLow;
	public static AddPaper addPaper;
	public static AddInk addInk;
	public static RemovesItems removesItems;
	public static refillCoin coinRefill;
	public static refillBanknote banknoteRefill;
	public static CustomerNoBagScannedItemAttendantRemovesProduct attendantRemovesProduct;
	public static StartupAndShutdown startupShutdown;
	public static int InkCounter = 3;
	public static int PaperCounter = 3;
	public static EnterPLUCode enterPLU;
	
	// instantiate station variables below
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
		failPlaceItem = new PlaceItemFail(checkoutStation);
		itemBag = new BagItem(checkoutStation);
		payCoin = new CoinPayment(checkoutStation);
		payBanknote = new BanknotePayment(checkoutStation);
		ownBag = new AddOwnBag(checkoutStation);
		addFinish = new FinishAdding(checkoutStation);
		creditPayment = new PayCredit(checkoutStation);
		debitPayment = new PayDebit(checkoutStation);
		giftcardPayment = new PayGiftCard(checkoutStation);
		customerReturnsToAddingItems = new CustomerReturnsToAddingItems(checkoutStation);
		changeReceive = new ReceiveChange(checkoutStation);
		membershipScan = new ScanMembership(checkoutStation);
		emptyBanknote = new EmptyBanknoteStorage(checkoutStation);
		emptyCoin = new EmptyCoinStorage(checkoutStation);
		membershipEnter = new EnterMembership(checkoutStation);
		sessionData = new CurrentSessionData();
		paymentProcessing = new CardPaymentProcessing();
		blocker = new BlockStationSetup(checkoutStation);
		paperLow = new PaperLow(checkoutStation);
		inkLow = new InkLow(checkoutStation);
		login = new AttendantLogin();
		enterNumBags = new EnterNumberOfBags();
		removesItems = new RemovesItems(checkoutStation);
//		addPaperInk = new AddPaper(checkoutStation);
//		placesItems = new RemovesItems(checkoutStation);
		addPaper = new AddPaper(checkoutStation);
		login = new AttendantLogin();
		approveWeight = new AttendantApprovesWeight();
		checkWrongWeight = new DetectUnexpectedWeight(checkoutStation);
		coinRefill = new refillCoin(checkoutStation);
		banknoteRefill = new refillBanknote(checkoutStation);
		addInk = new AddInk(checkoutStation);
		startupShutdown = new StartupAndShutdown(checkoutStation);
		attendantRemovesProduct = new CustomerNoBagScannedItemAttendantRemovesProduct(checkoutStation);
		enterPLU = new EnterPLUCode(checkoutStation);
		
		
		// Instantiate attendant-specific use cases in here
		if (sessionData.getAttendantLoggedIn()) {
			
		}

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
	} // endmain
	
	
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
