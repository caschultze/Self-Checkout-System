package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinStorageUnit;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinStorageUnitListener;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;

import java.util.List;


public class EmptyCoinStorage extends CoinPayment {
	
	public CoinStorageUnitListener csl;
	private boolean checkUnloaded = false;
	private List<Coin> unloadedCoins;
	
	public EmptyCoinStorage(SelfCheckoutStation checkoutStation) {
//		scs = checkoutStation;
//		registerListeners();
		super(checkoutStation);
	}
	
	@Override
	public void registerListeners() {
		csl = new CoinStorageUnitListener() {

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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinAdded(CoinStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsLoaded(CoinStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinsUnloaded(CoinStorageUnit unit) {
				checkUnloaded = true;
				setFullStorageCheck(false);
			}
			
		};
		
		super.scs.coinStorage.register(csl);
	}
	
	public List<Coin> emptyCoinStorage() {
		if(ControlUnit.sessionData.getCurrentAttendant() == null) {
			throw new SimulationException("There is no attendant available to empty storage");
		}
		return super.scs.coinStorage.unload();
	}
	
	public void addToUnloaded(List<Coin> coin) {
		unloadedCoins = coin;
}
	
	//getter for unloadedBanknotes
	public List<Coin> getUnloadedCoins(){
		return unloadedCoins;
	}

	public boolean getCheckUnloaded() {
		return checkUnloaded;
	}
	
	public void setCheckUnloaded(boolean set) {
		checkUnloaded = set;
	}
}

