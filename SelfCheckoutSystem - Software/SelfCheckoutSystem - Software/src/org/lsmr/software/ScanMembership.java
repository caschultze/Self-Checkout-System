package org.lsmr.software;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class ScanMembership {

	public SelfCheckoutStation station;
	CardReaderListener csl;
	private boolean swipeCard = false;
	private boolean dataRead = false;
	private boolean member = false;
	private boolean cardSwiped = false;
	private boolean isEnabled = false;
	private boolean isDisabled = false;
	private String typeCard = "Membership";
	private String type;
	HashMap<String, String> MemberDatabase; 

	//Use case for when a loyalty customer wishes to scan their membership card	
	public ScanMembership(SelfCheckoutStation checkoutStation) {
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
	
	public void registerListeners() {
		csl = new CardReaderListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = true;
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isDisabled = true;
				
			}

			@Override
			public void cardInserted(CardReader reader) {
				// TODO Auto-generated method stub
			}

			@Override
			public void cardRemoved(CardReader reader) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cardTapped(CardReader reader) {
				// TODO Auto-generated method stub
			}

			@Override
			public void cardSwiped(CardReader reader) {
				swipeCard = true;
				
			}

			@Override
			public void cardDataRead(CardReader reader, CardData data) {
				if (swipeCard)
					dataRead = true;
				if (data instanceof CardSwipeData) {
					type = data.getType();
				}
			}
			
		};
		station.cardReader.register(csl);
	}
	
	//method that handles the processing of membership information once card data is read
	public void swipeMemberCard(Card coopMember, BufferedImage signature) throws IOException {
		
		if (coopMember == null) {
			throw new NullPointerException("No argument may be null");
		}
		
		CardData data = station.cardReader.swipe(coopMember, signature);
		String CustomerName = data.getCardholder();
		String CustomerNumber = data.getNumber();
		
		if (!type.equalsIgnoreCase(typeCard)) {
			throw new SimulationException("Type of card is not Membership");
		}
		
		if (dataRead && type.contentEquals(typeCard))
			cardSwiped = true;

		if (cardSwiped && (MemberDatabase.containsKey(CustomerName)) && MemberDatabase.get(CustomerName).contentEquals(CustomerNumber))
			member = true;
	}
	
	public boolean isDataRead() {
		return dataRead;
	}

	public boolean isMember() {
		return member;
	}
	
	public boolean getEnabled() {
		return isEnabled;
	}
	
	public boolean getDisabled() {
		return isDisabled;
	}
				
}
