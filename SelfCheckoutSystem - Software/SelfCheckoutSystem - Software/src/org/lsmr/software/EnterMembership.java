package org.lsmr.software;

import java.util.HashMap;

import javax.swing.JFrame;

import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;


public class EnterMembership {

	public SelfCheckoutStation station;
	TouchScreen tsl;
	TouchScreenListener tl;
	String membershipID;
	JFrame frame;
	private boolean isMember = false;
	HashMap<String, String> MemberDatabase;

	
	public EnterMembership(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
		registerListeners();
		
		String number1 = "123456789";
		String number2 = "223456789";
		String number3 = "323456789";
		String number4 = "423456789";
		
		//Database to keep track of members
		MemberDatabase = new HashMap <String, String>();
		
		MemberDatabase.put("Mario Luigi", number1);
		MemberDatabase.put("Julius Pringles", number2);
		MemberDatabase.put("Dale Gribble", number3);
		MemberDatabase.put("Bob Belcher", number4);
	}

	private void registerListeners() {
		tsl = new TouchScreen();
		station.screen.register(tl);
		JFrame frame = tsl.getFrame();
	}

	public void enterNumber(String membershipID) {
//		if (!tsl.isDisabled()) {
//		MembershipPanel.enterPanel(frame);
//	}
//		membershipID = MembershipPanel.getMemberID();
//		System.out.println(membershipID);
		
		if (MemberDatabase.containsValue(membershipID))
			isMember = true;
}

	public boolean isMember() {
		return isMember;
	}
	
}
