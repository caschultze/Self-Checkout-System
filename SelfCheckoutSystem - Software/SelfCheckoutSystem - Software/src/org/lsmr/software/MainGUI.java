package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class MainGUI extends AbstractDevice <TouchScreenListener> {
	public static GUICreditScreen GUICreditScreen;	//1
	public static GUIDebitScreen GUIDebitScreen;	//2
	public static GUIEnterPin GUIEnterPin;	//3	
	public static SceenBootUp SceenBootUp;	//4
	public static SceenStart SceenStart;	//5
	public static SceenScan SceenScan;	//6
	public static GUIAdminLogin GUIAdminLogin;	//7
	public static GUIMembershipLogIn GUIMembershipLogIn;	//8
	public static ListProductScreen ListProductScreen;	//9
	public static AdminGUI AdminGUI;	//10
	public static PaymentSelectionScreen PaymentSelectionScreen;	//11
	public static GUIPayCash GUIPayCash;	//12
	public static GUIPayGiftCardScreen GUIPayGiftCardScreen;	//13
	public static ThanksForShoppingScreen ThanksForShoppingScreen; //14
	public static ShutdownScreen ShutdownScreen; //15
	public static BagScreen bagScreen; //16
	public static GUIOutofInkPaper outofInkPaper; //17
	
	public static int PayingBy; //credit = 1, debit = 2
	public static Card card1 = new Card("Credit", "4500123412341234", "Dr. Walker", "123", "1234", true, true);
	public static Card card2 = new Card("Debit", "4500123412341235", "Dr. Walker", "123", "1234", true, true);
	public static Card card3 = new Card("Giftcard", "30031234", "Dr. Walker", "123", "1234", true, true);
	public static int CurrentScreen;
	
	public static void main(String[] args) {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100000;
		int scaleSensitivity = 1;
		ControlUnit control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
		GUICreditScreen = new GUICreditScreen();
		GUIDebitScreen = new GUIDebitScreen();
		GUIEnterPin = new GUIEnterPin();
		SceenBootUp = new SceenBootUp();
		SceenStart = new SceenStart();
		SceenScan = new SceenScan();
		GUIAdminLogin = new GUIAdminLogin();
		GUIMembershipLogIn = new GUIMembershipLogIn();
		ListProductScreen = new ListProductScreen();
		AdminGUI = new AdminGUI();
		PaymentSelectionScreen = new PaymentSelectionScreen();
		GUIPayCash = new GUIPayCash();
		GUIPayGiftCardScreen = new GUIPayGiftCardScreen();
		ThanksForShoppingScreen = new ThanksForShoppingScreen();
		ShutdownScreen = new ShutdownScreen();
		bagScreen = new BagScreen();
		outofInkPaper = new GUIOutofInkPaper();

		switchScreen(4);


//		GUIEnterPin.setVisible(true);
	}
	
	public static void switchScreen(int screenNum) {
		switch (screenNum) {
		case 1:
			PayingBy = 1;
			CurrentScreen = 1;
			
			GUICreditScreen.touchscreen.setVisible(true);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 2:
			PayingBy = 2;
			CurrentScreen = 2;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(true);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 3:
			
			CurrentScreen = 3;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(true);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			GUIEnterPin.setPin();

			break;
		case 4:
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(true);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 5:
			
			CurrentScreen = 5;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(true);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 6:
			
			CurrentScreen = 6;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(true);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			SceenScan.updateList();
			

			break;
		
		case 7:		
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(true);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			
			break;
		case 8:
			
			CurrentScreen = 8;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(true);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			GUIMembershipLogIn.setMembership();
			

			break;
		
		case 9:
			
			CurrentScreen = 9;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(true);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 10:
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(true);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 11:
			
			CurrentScreen = 11;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(true);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
		case 12:
			
			CurrentScreen = 12;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(true);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			GUIPayCash.updateTotalInitial();
			GUIPayCash.updateTotal();

			break;
		case 13:		
			
			CurrentScreen = 13;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(true);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
			
		case 14:	
			
			CurrentScreen = 14;
			
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(true);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);

			break;
			
		case 15:
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			ShutdownScreen.tsl.setVisible(true);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(false);
			
			break;
			
		case 16:
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(true);
			outofInkPaper.tsl.setVisible(false);
			
			break;
			
		case 17:
			CurrentScreen = 17;
			GUICreditScreen.touchscreen.setVisible(false);
			GUIDebitScreen.touchscreen.setVisible(false);	
			GUIEnterPin.touchscreen.setVisible(false);
			SceenBootUp.touchscreen.setVisible(false);
			SceenStart.touchscreen.setVisible(false);
			SceenScan.touchscreen.setVisible(false);
			GUIAdminLogin.touchscreen.setVisible(false);
			GUIMembershipLogIn.touchScreen.setVisible(false);
			ListProductScreen.tsl.setVisible(false);
			AdminGUI.tsl.setVisible(false);
			PaymentSelectionScreen.tsl.setVisible(false);
			GUIPayCash.touchscreen.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.tsl.setVisible(false);
			ShutdownScreen.tsl.setVisible(false);
			bagScreen.tsl.setVisible(false);
			outofInkPaper.tsl.setVisible(true);
			AdminGUI.logoutBtn.setEnabled(false);
			break;
		}
		
	}
	
	
	
	
}
