package org.lsmr.software;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.CoinValidator;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinDispenserListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;
import org.lsmr.selfcheckout.devices.listeners.CoinValidatorListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.Product;

public class CoinPayment {

	public SelfCheckoutStation scs;
	public CoinValidatorListener cvl;
	public CoinStorageUnitListener csul;
	public CoinDispenserListener cdl;
	private BigDecimal currentBalance = new BigDecimal("0.00");
	private BigDecimal currentCoinValue = new BigDecimal("0.00");
	private boolean validCheck = false; 
	private boolean storedCheck = false;
	private boolean fullStorageCheck = false;
	private int numInvalidCoins = 0;
	public boolean slotDisabled = false;
	private CurrentSessionData session;
	
	public CoinPayment(SelfCheckoutStation checkoutStation) {

		scs = checkoutStation;
		session = new CurrentSessionData();
		registerListeners();
	}
	
	public void registerListeners() {
		cvl = new CoinValidatorListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void validCoinDetected(CoinValidator validator, BigDecimal value) {
				validCheck = true;
				currentCoinValue = value;
			}

			@Override
			public void invalidCoinDetected(CoinValidator validator) {
				numInvalidCoins++;
			}
		};
		
		csul = new CoinStorageUnitListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsFull(CoinStorageUnit unit) {
				fullStorageCheck = true;
			}

			@Override
			public void coinAdded(CoinStorageUnit unit) {
				storedCheck = true;
			}

			@Override
			public void coinsLoaded(CoinStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsUnloaded(CoinStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		cdl = new CoinDispenserListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void coinsFull(CoinDispenser dispenser) {
				fullStorageCheck = true;
				
			}

			@Override
			public void coinsEmpty(CoinDispenser dispenser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinAdded(CoinDispenser dispenser, Coin coin) {
				storedCheck = true;
				
			}

			@Override
			public void coinRemoved(CoinDispenser dispenser, Coin coin) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		for(CoinDispenser dis:scs.coinDispensers.values()) {
			dis.register(cdl);
		}
		scs.coinValidator.register(cvl);
		scs.coinStorage.register(csul);
	}
	
	public BigDecimal getBalance() {
		return currentBalance;
	}
	
	public int getNumInvalidCoins() {
		return numInvalidCoins;
	}
	
	public void payWithCoins(Coin c) {
		
		currentBalance = BigDecimal.ZERO;
		
		if (c == null) {
			throw new NullPointerException("No argument may be null");
		}
		
			try {
				scs.coinSlot.accept(c);
			} catch (DisabledException e) {
				e.printStackTrace();
			}
			
			if (fullStorageCheck) {
				scs.coinSlot.disable();
				slotDisabled = true;
			}
			
			if (validCheck && storedCheck && c.getValue().compareTo(currentCoinValue) == 0) {
				session.payCoin(c.getValue());
			}
			
			currentCoinValue = BigDecimal.ZERO;
			validCheck = false;
			storedCheck = false;
		}
}
