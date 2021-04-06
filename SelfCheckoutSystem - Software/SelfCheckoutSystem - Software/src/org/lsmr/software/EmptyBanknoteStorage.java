package org.lsmr.software;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteStorageUnit;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteStorageUnitListener;
import org.lsmr.selfcheckout.Banknote;

import java.util.List;


public class EmptyBanknoteStorage {
	
	public SelfCheckoutStation scs;
	public BanknoteStorageUnitListener bnl;
	private boolean checkUnloaded = false;
	private List<Banknote> unloadedBanknotes;
	
	
	public EmptyBanknoteStorage(SelfCheckoutStation checkoutStation) {
		scs = checkoutStation;
		registerListeners();
	}
	
	public void registerListeners() {
		bnl = new BanknoteStorageUnitListener() {

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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteAdded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesLoaded(BanknoteStorageUnit unit) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknotesUnloaded(BanknoteStorageUnit unit) {
				checkUnloaded = true;
				
			}
			
		};
		
		scs.banknoteStorage.register(bnl);
	}
	
	public List<Banknote> emptyBanknoteStorage() {
		if(ControlUnit.sessionData.getCurrentAttendant() == null) {
			throw new SimulationException("There is no attendant available to empty storage");
		}
		return scs.banknoteStorage.unload();

	}
	
	public void addToUnloaded(List<Banknote> banknote) {
			unloadedBanknotes = banknote;
	}
	
	//getter for unloadedBanknotes
	public List<Banknote> getUnloadedBanknotes(){
		return unloadedBanknotes;
	}

}
