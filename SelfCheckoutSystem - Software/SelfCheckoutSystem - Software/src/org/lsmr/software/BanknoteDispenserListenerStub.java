package org.lsmr.software;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteDispenser;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteDispenserListener;

public class BanknoteDispenserListenerStub implements BanknoteDispenserListener{

	int value;
	
	public BanknoteDispenserListenerStub(int v) {
		value = v;
	}
	
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
		System.out.println("The banknote dispenser for " + value +" is full");
		
	}

	@Override
	public void banknotesEmpty(BanknoteDispenser dispenser) {
		System.out.println("The banknote dispenser for " + value +" is empty");
		
	}

	@Override
	public void banknoteAdded(BanknoteDispenser dispenser, Banknote banknote) {
		if (banknote.getValue() == value) {
			System.out.println("The banknote of value " + banknote.getValue()+ " was added to the dispenser of value " + value);
		}else {
			System.out.println("HEY you can't add a banknote of "+ banknote.getValue()+" to the dispenser of value "+ value);
		}
		
	}

	@Override
	public void banknoteRemoved(BanknoteDispenser dispenser, Banknote banknote) {
		if (banknote.getValue() == value) {
			System.out.println("The banknote of value " + banknote.getValue()+ " was removed to the dispenser of value " + value);
		}else {
			System.out.println("HEY you can't remove a banknote of "+banknote.getValue()+" to the dispenser of value "+ value);
		}
	}

	@Override
	public void banknotesLoaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		for (Banknote notes:banknotes) {
			if (notes.getValue()!= value) {
				System.out.println("HEY you can't add a banknote of "+notes.getValue()+" to the dispenser of value "+ value);
			}
		}
		System.out.println(banknotes.length + " banknotes where added to the dispenser of value "+ value);
		
	}

	@Override
	public void banknotesUnloaded(BanknoteDispenser dispenser, Banknote... banknotes) {
		for (Banknote notes:banknotes) {
			if (notes.getValue()!= value) {
				System.out.println("HEY you can't remove a banknotes of "+notes.getValue()+" to the dispenser of value "+ value);
			}
		}
		System.out.println(banknotes.length + " banknotes where unloaded to the dispenser of value "+ value);
		
	}
	public int getValue() {
		return value;
	}


}
