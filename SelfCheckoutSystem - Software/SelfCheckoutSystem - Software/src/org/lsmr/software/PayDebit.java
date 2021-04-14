package org.lsmr.software;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.Card.CardInsertData;
import org.lsmr.selfcheckout.Card.CardSwipeData;
import org.lsmr.selfcheckout.Card.CardTapData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CardReader;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CardReaderListener;

public class PayDebit {

	public SelfCheckoutStation station;
	public CardReaderListener cardListener;
	private boolean insertCheck = false;
	private boolean tapCheck = false;
	private boolean swipeCheck = false;
	private boolean removeCheck = false;
	private String type;
	private String number;
	private String cardholder;
	private String cvv = null;
	private String typeCard = "Debit";
	
	public PayDebit(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
		registerListeners();
	}
	
	public void registerListeners() {
		
		cardListener = new CardReaderListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void cardInserted(CardReader reader) {
				insertCheck = true;
			}

			@Override
			public void cardRemoved(CardReader reader) {
				removeCheck = true;
			}

			@Override
			public void cardTapped(CardReader reader) {
				tapCheck = true;
			}

			@Override
			public void cardSwiped(CardReader reader) {
				swipeCheck = true;
			}

			@Override
			public void cardDataRead(CardReader reader, CardData data) {
				if (data instanceof CardInsertData || data instanceof CardTapData || data instanceof CardSwipeData) {
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
	
	public CardData debitTap(Card card) throws IOException {
		
		if (card == null) {
			throw new NullPointerException("No argument may be null");
		}
		
		CardData data = station.cardReader.tap(card);
		
		if (!(type.equalsIgnoreCase(typeCard))) {
			throw new SimulationException("Type of card is not debit");
		}
		return data;

	}
	
	public CardData debitSwipe(Card card, BufferedImage signature) throws IOException {
		
		if (card == null || signature == null) {
			throw new NullPointerException("No argument may be null");
		}
		
		CardData data = station.cardReader.swipe(card, signature);
		
		if (!(type.equalsIgnoreCase(typeCard))) {
			throw new SimulationException("Type of card is not debit");
		}
		return data;

	}

	public CardData debitInsert(Card card, String pin) throws IOException {
	
		if (card == null || pin == null) {
			throw new NullPointerException("No argument may be null");
		}
		
		CardData data = station.cardReader.insert(card, pin);
		
		if (!(type.equalsIgnoreCase(typeCard))) {
			throw new SimulationException("Type of card is not debit");
		}
		return data;

	}
	
	public void debitRemove() {
		if (insertCheck) {
			station.cardReader.remove();
		}
	}
	
	public boolean getInsertCheck() {
		return insertCheck;
	}

	public boolean getTapCheck() {
		return tapCheck;
	}
	
	public boolean getSwipeCheck() {
		return swipeCheck;
	}
	
	public boolean getRemoveCheck() {
		return removeCheck;
	}
	
	public String getType() {
		return type;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getCardholder() {
		return cardholder;
	}
	
	public String getCVV() {
		return cvv;
	}
}
