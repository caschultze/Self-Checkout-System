package org.lsmr.software;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardInsertData;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class PayGiftCard {
	
	public SelfCheckoutStation station;
	public CardReaderListener cardListener;
	private boolean swipeCard = false;
	private boolean dataRead = false;
	private boolean member = false;
	private boolean cardSwiped = false;
	private boolean cardInserted = false;
	private boolean cardRemoved = false;
	private boolean isEnabled = false;
	private boolean isDisabled = false;
	private String type;
	private String number;
	private String cardholder;
	private String cvv = null;
	private String typeCard = "Giftcard";
	
	public PayGiftCard(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
		registerListeners();
	}
	
public void registerListeners() {
		
		cardListener = new CardReaderListener() {

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
				cardInserted = true;
			}

			@Override
			public void cardRemoved(CardReader reader) {
				if (cardInserted)
					cardRemoved = true;
			}

			@Override
			public void cardTapped(CardReader reader) {
			
			}

			@Override
			public void cardSwiped(CardReader reader) {
				cardSwiped = true;
			}

			@Override
			public void cardDataRead(CardReader reader, CardData data) {
				if (data instanceof CardInsertData || data instanceof CardSwipeData) {
					type = data.getType();
					number = data.getNumber();
					cardholder = data.getCardholder();
					if (!(data instanceof CardSwipeData)) {
						cvv = data.getCVV();
					}
				}
			}
			
		};
		
		station.cardReader.register(cardListener);
	}

//method that handles the processing of membership information once card data is read
public void swipeGiftCard(Card coopMember, BufferedImage signature) throws IOException {
	
	if (coopMember == null) {
		throw new NullPointerException("No argument may be null");
	}
	
	station.cardReader.swipe(coopMember, signature);

	if (!type.equalsIgnoreCase(typeCard)) {
		throw new SimulationException("Type of card is not Giftcard");
	}
	
}


public void insertGiftCard(Card coopMember, String pin) throws IOException {

	if (coopMember == null || pin == null) {
		throw new NullPointerException("No argument may be null");
	}
	
	station.cardReader.insert(coopMember, pin);
	
	if (!(type.equalsIgnoreCase(typeCard))) {
		throw new SimulationException("Type of card is not Giftcard");
	}
}

public boolean giftRemove() {
	if (cardInserted) {
		station.cardReader.remove();
	}
	return cardRemoved;
}

public boolean getInsertCheck() {
	return cardInserted;
}

public boolean getSwipeCheck() {
	return cardSwiped;
}

public String getType() {
	return type;
}

public String getNumber() {
	return number;
}

public String getCVV() {
	return cvv;
}


}
