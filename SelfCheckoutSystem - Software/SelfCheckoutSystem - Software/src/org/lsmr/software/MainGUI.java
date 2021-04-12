package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class MainGUI extends AbstractDevice <TouchScreenListener> {
	private static GUICreditScreen GUICreditScreen1;	//1
	private static GUIDebitScreen GUIDebitScreen1;	//2
	
	public static void main(String[] args) {
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		ControlUnit control = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		control.main(null);
		GUICreditScreen1 = new GUICreditScreen();
		GUIDebitScreen1 = new GUIDebitScreen();
		control.sessionData.setAndGetTotalPrice(new BigDecimal("10"));
		GUICreditScreen1.setVisible(true);
	}
	
	public void switchScreen(int screenNum) {
		switch (screenNum) {
		case 1:
			GUICreditScreen1.setVisible(true);	//1
			GUIDebitScreen1.setVisible(false);	
			break;
		case 2:
			GUICreditScreen1.setVisible(false);	//1
			GUIDebitScreen1.setVisible(true);	//2
			break;
		case 3:
			GUICreditScreen1.setVisible(false);	//1
			GUIDebitScreen1.setVisible(false);	//2
			break;
		}
	}
	
	
	
	
}
