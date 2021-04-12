package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class MainGUI extends AbstractDevice <TouchScreenListener> {
	private static GUICreditScreen GUICreditScreen;	//1
	private static GUIDebitScreen GUIDebitScreen;	//2
	private static GUIEnterPin GUIEnterPin;	//3	
	private static SceenBootUp SceenBootUp;	//4
	private static SceenStart SceenStart;	//5
	private static SceenScan SceenScan;	//6
	private static GUIAdminLogin GUIAdminLogin;	//7
	private static GUIMembershipLogIn GUIMembershipLogIn;	//8
	private static ListProductScreen ListProductScreen;	//9
	private static AdminGUI AdminGUI;	//10
	private static PaymentSelectionScreen PaymentSelectionScreen;	//11
	private static GUIPayCash GUIPayCash;	//12
	private static GUIPayGiftCardScreen GUIPayGiftCardScreen;	//13
	private static ThanksForShoppingScreen ThanksForShoppingScreen; //14
	
	public static int PayingBy; //credit = 1, debit = 2
	public static Card card1 = new Card("Credit", "4500123412341234", "Dr. Walker", "123", "1234", true, true);
	public static Card card2 = new Card("Debit", "4500123412341235", "Dr. Walker", "123", "1234", true, true);
	public static Card card3 = new Card("Giftcard", "30031234", "Dr. Walker", "123", "1234", true, true);

	
	public static void main(String[] args) {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
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
		control.sessionData.setAndGetTotalPrice(new BigDecimal("10"));
		switchScreen(12);
		
//		GUIEnterPin.setVisible(true);
	}
	
	public static void switchScreen(int screenNum) {
		switch (screenNum) {
		case 1:
			PayingBy = 1;

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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);
			break;
		case 2:
			PayingBy = 2;
			
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 3:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 5:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 6:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 8:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		
		case 9:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 11:
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 12:
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
			GUIPayCash.tsl.setVisible(true);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(false);

			break;
		case 13:			
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(true);
			ThanksForShoppingScreen.setVisible(false);

			break;
			
		case 14:			
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
			GUIPayCash.tsl.setVisible(false);
			GUIPayGiftCardScreen.touchscreen.setVisible(false);
			ThanksForShoppingScreen.setVisible(true);

			break;
			
		}
		
	}
	
	
	
	
}
