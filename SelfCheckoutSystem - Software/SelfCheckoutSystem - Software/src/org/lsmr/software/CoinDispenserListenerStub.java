package org.lsmr.software;

import java.math.BigDecimal;

import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.CoinDispenserListener;

public class CoinDispenserListenerStub implements CoinDispenserListener{
	
	BigDecimal value;
	//private static boolean dispenserFull = false;
	
	public CoinDispenserListenerStub(BigDecimal v){
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
	public void coinsFull(CoinDispenser dispenser){
		System.out.println("The coin dispenser for " + value +" is full");
		 //dispenserFull = true;
		
	}

	@Override
	public void coinsEmpty(CoinDispenser dispenser) {
		System.out.println("The coin dispenser for " + value +" is empty");
		
	}

	@Override
	public void coinAdded(CoinDispenser dispenser, Coin coin) {
		if (coin.getValue().compareTo(value) == 0) {
			System.out.println("The coin of value " + coin.getValue()+ " was added to the dispenser of value " + value);
		}else {
			System.out.println("HEY you can't add a coin of "+coin.getValue()+" to the dispenser of value "+ value);
		}
	
	}

	@Override
	public void coinRemoved(CoinDispenser dispenser, Coin coin) {
		if (coin.getValue().compareTo(value) == 0) {
			System.out.println("The coin of value " + coin.getValue()+ " was removed to the dispenser of value " + value);
		}else {
			System.out.println("HEY you can't remove a coin of "+coin.getValue()+" to the dispenser of value "+ value);
		}
	
	}

	@Override
	public void coinsLoaded(CoinDispenser dispenser, Coin... coins) {
		for (Coin coin:coins) {
			if (coin.getValue().compareTo(value) != 0) {
				System.out.println("HEY you can't add a coin of "+coin.getValue()+" to the dispenser of value "+ value);
			}
		}
		System.out.println(coins.length + " coins where added to the dispenser of value "+ value);
	}

	@Override
	public void coinsUnloaded(CoinDispenser dispenser, Coin... coins) {
		for (Coin coin:coins) {
			if (coin.getValue().compareTo(value) != 0) {
				System.out.println("HEY you can't remove a coin of "+coin.getValue()+" to the dispenser of value "+ value);
			}
		}
		System.out.println(coins.length + " coins where unloaded to the dispenser of value "+ value);
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
//	public boolean getDispenserFull() {
//		return dispenserFull;
//	}

}
