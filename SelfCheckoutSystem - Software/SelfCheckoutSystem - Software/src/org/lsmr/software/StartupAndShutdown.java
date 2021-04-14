package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class StartupAndShutdown {
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
	public static RemovesItems placesItems;
	public static refillCoin coinRefill;
	public static refillBanknote banknoteRefill;
	public static CustomerNoBagScannedItemAttendantRemovesProduct attendantRemovesProduct;
	public static StartupAndShutdown startupShutdown;
	public static int InkCounter;
	public static int PaperCounter;
	public static EnterPLUCode enterPLU;
	public static boolean PowerOn;
	
	public StartupAndShutdown(SelfCheckoutStation station) {
		StartupAndShutdown.checkoutStation = station;
	}
	
	public void startup () {
		itemScan = new ScanItem(checkoutStation);
		itemBag = new BagItem(checkoutStation);
		payCoin = new CoinPayment(checkoutStation);
		payBanknote = new BanknotePayment(checkoutStation);
		ownBag = new AddOwnBag(checkoutStation);
		addFinish = new FinishAdding(checkoutStation);
		creditPayment = new PayCredit(checkoutStation);
		debitPayment = new PayDebit(checkoutStation);
		giftcardPayment = new PayGiftCard(checkoutStation);
		failPlaceItem = new PlaceItemFail(checkoutStation);
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
		placesItems = new RemovesItems(checkoutStation);
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
		PowerOn = true;
	}
	
	public void shutdown () {
		itemScan = null;
		itemBag = null;
		payCoin = null;
		payBanknote = null;
		ownBag = null;
		addFinish = null;
		creditPayment = null;
		debitPayment = null;
		giftcardPayment = null;
		failPlaceItem = null;
		customerReturnsToAddingItems = null;
		changeReceive = null;
		membershipScan = null;
		emptyBanknote = null;
		emptyCoin = null;
		membershipEnter = null;
		sessionData = null;
		paymentProcessing = null;
		blocker = null;
		paperLow = null;
		inkLow = null;
		login = null;
		enterNumBags = null;
		removesItems = null;
		placesItems = null;
		addPaper = null;
		login = null;
		approveWeight = null;
		checkWrongWeight = null;
		coinRefill = null;
		banknoteRefill = null;
		addInk = null;
		startupShutdown = null;
		attendantRemovesProduct = null;
		enterPLU = null;
		PowerOn = false;
	}
}
