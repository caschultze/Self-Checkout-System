package org.lsmr.software;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.BanknoteValidator;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteDispenserListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteValidatorListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class BanknotePayment {

	public SelfCheckoutStation scs;
	public BanknoteValidatorListener bvl;
	public BanknoteStorageUnitListener bsul;
	public BanknoteDispenserListener bdl;
	private BigDecimal currentBalanceBD = new BigDecimal("0.0");
	private int currentBalance;
	private int currentBanknoteValue;
	private boolean validCheck = false; 
	private boolean storedCheck = false;
	private boolean fullStorageCheck = false;
	private int numInvalidBanknotes = 0;
	private Currency currentCurrency;
	private CurrentSessionData session;
	
	public BanknotePayment(SelfCheckoutStation checkoutStation) {
		scs = checkoutStation;
		session = new CurrentSessionData();
		registerListeners();
	} 
	
	public void registerListeners() {
		bvl = new BanknoteValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void validBanknoteDetected(BanknoteValidator validator, Currency currency, int value) {
				validCheck = true;
				currentBanknoteValue = value;
				currentCurrency = currency;
			}

			@Override
			public void invalidBanknoteDetected(BanknoteValidator validator) {
				numInvalidBanknotes++;
			}
		};
		
		bsul = new BanknoteStorageUnitListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesFull(BanknoteStorageUnit unit) {
				fullStorageCheck = true;
			}

			@Override
			public void banknoteAdded(BanknoteStorageUnit unit) {
				storedCheck = true;
			}

			@Override
			public void banknotesLoaded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesUnloaded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			
		};
		
		bdl = new BanknoteDispenserListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesFull(BanknoteDispenser dispenser) {
				fullStorageCheck = true;
				
			}

			@Override
			public void banknotesEmpty(BanknoteDispenser dispenser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteAdded(BanknoteDispenser dispenser, Banknote banknote) {
				storedCheck = true;
				
			}

			@Override
			public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		
		for(BanknoteDispenser dis:scs.banknoteDispensers.values()) {
			dis.register(bdl);
		}
		scs.banknoteValidator.register(bvl);
		scs.banknoteStorage.register(bsul);
	}
	
	public int getBalance() {
		return currentBalance;
	}
	
	public int getNumInvalidBanknotes() {
		return numInvalidBanknotes;
	}
	
	public boolean getFullStorageCheck() {
		return fullStorageCheck;
	}
	
	public void payWithBanknotes(Banknote note) {
		
		if (note == null) {
			throw new NullPointerException("No argument may be null");
		}
		

			try {
				scs.banknoteInput.accept(note);
				if (fullStorageCheck) {
					scs.banknoteInput.disable();
				}
			} 
			catch (DisabledException e) {
				e.printStackTrace();
			} catch (OverloadException e) {
				fullStorageCheck = true;
				e.printStackTrace();
			}
			
			
			if (validCheck && storedCheck && note.getValue() == currentBanknoteValue && note.getCurrency().equals(currentCurrency)) {
				session.payBanknote(note.getValue());
			}
			
			currentBanknoteValue = 0;
			validCheck = false;
			storedCheck = false;
		}
}
