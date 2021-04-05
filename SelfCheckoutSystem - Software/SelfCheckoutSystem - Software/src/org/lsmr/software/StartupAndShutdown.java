package org.lsmr.software;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;

public class StartupAndShutdown {
	public SelfCheckoutStation checkoutStation;
	public static ScanItem itemScan;
	public static BagItem itemBag;
	public static CoinPayment payCoin;
	public static BanknotePayment payBanknote;
	public static AddOwnBag ownBag;
	public static FinishAdding addFinish;
	public static PayCredit creditPayment;
	public static PayDebit debitPayment;
	public static PlaceItemFail failPlaceItem;
	public static ReceiveChange changeReceive;
	public static ScanMembership membershipScan;
	public static CurrentSessionData sessionData;
	public static CardPaymentProcessing paymentProcessing;
	public static boolean PowerOn;
	
	public StartupAndShutdown(SelfCheckoutStation station) {
		this.checkoutStation = station;
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
		failPlaceItem = new PlaceItemFail(checkoutStation);
		changeReceive = new ReceiveChange(checkoutStation);
		membershipScan = new ScanMembership(checkoutStation);
		sessionData = new CurrentSessionData();
		paymentProcessing = new CardPaymentProcessing();
		PowerOn = true;
	}
	
	public void shutdown () {
		PowerOn = false;
		//System.exit(1);
	}
}
