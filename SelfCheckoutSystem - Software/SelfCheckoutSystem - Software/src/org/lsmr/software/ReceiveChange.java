package org.lsmr.software;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.CoinDispenser;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.CoinDispenserListener;

public class ReceiveChange {

	public SelfCheckoutStation station;
	public Map<BigDecimal, CoinDispenserListenerStub> coinDispensersLis;
	public Map<Integer, BanknoteDispenserListenerStub> banknoteDispensersLis;
	private CurrentSessionData session;
	
	public ReceiveChange(SelfCheckoutStation checkoutStation) {
		station = checkoutStation;
		session = new CurrentSessionData();
		registerListeners();
	}
	
	public void registerListeners() {
		coinDispensersLis = new HashMap<>();
		
		for(int i = 0; i < station.coinDenominations.size(); i++)
			coinDispensersLis.put(station.coinDenominations.get(i), new CoinDispenserListenerStub(station.coinDenominations.get(i)));

		for(CoinDispenserListenerStub coinDispenerLisn: coinDispensersLis.values()) {
			station.coinDispensers.get(coinDispenerLisn.getValue()).register(coinDispenerLisn);
		}
		
		banknoteDispensersLis = new HashMap<>();
		
		for(int i = 0; i < station.banknoteDenominations.length; i++)
			banknoteDispensersLis.put(new Integer(station.banknoteDenominations[i]), new BanknoteDispenserListenerStub(station.banknoteDenominations[i]));

		for(BanknoteDispenserListenerStub noteDispenerLisn: banknoteDispensersLis.values()) {
			station.banknoteDispensers.get(noteDispenerLisn.getValue()).register(noteDispenerLisn);
		}
	}
	
	
	public void loadCoins(Coin... Coins) throws OverloadException {
		BigDecimal val = Coins[0].getValue();
		try {
			for(Coin note:Coins) {
				if (note.getValue().compareTo(val) != 0) {
					throw new SimulationException("Not all the coins are of the same value");
				}
			}
			station.coinDispensers.get(val).load(Coins);
		}catch(OverloadException e) {
			System.out.println("The despenser will over flow when loading theses coins."); throw e;
		}catch(SimulationException e) {
			System.out.println("There was an invalid coin in the inserted coins.");throw e;
		}
	}
	
	public void loadBanknotes(Banknote... Banknotes) throws OverloadException {
		Integer val = Banknotes[0].getValue();
		try {
			for(Banknote note:Banknotes) {
				if (note.getValue() != val) {
					throw new SimulationException("Not all the Banknotes are of the same value");
				}
			}
			station.banknoteDispensers.get(val).load(Banknotes);
		}catch(OverloadException e) {
			System.out.println("The despenser will over flow when loading theses banknotes."); throw e;
		}catch(SimulationException e) {
			System.out.println("There was an invalid banknote in the inserted banknotes."); throw e;
		}
	}
	
	public void unloadCoins(BigDecimal val) {

		station.coinDispensers.get(val).unload();
	
	}
	
	
	public void unloadBanknotes(int val) {

		station.banknoteDispensers.get(val).unload();
	
	}
	
	public void giveChange() throws DisabledException, EmptyException, OverloadException {
		BigDecimal payed = session.getCurrentAmountOwing(new BigDecimal(0));
		if (payed.compareTo(new BigDecimal(0)) < 0) {
			BigDecimal change = payed.negate();
			int[] banknotes = station.banknoteDenominations;
			Arrays.sort(banknotes);
			for(int i=banknotes.length-1 ;i>= 0;i--) {
				BigDecimal amo = new BigDecimal(banknotes[i]);
				while (change.compareTo(amo) >= 0 && station.banknoteDispensers.get(new Integer(amo.intValue())).size() > 0) {
					try {
					station.banknoteDispensers.get(new Integer(amo.intValue())).emit();
					//station.banknoteOutput.removeDanglingBanknote(); //Hard coded though system should wait for customer to remove the note to continue.
					change = change.subtract(amo);
					}catch(DisabledException e) {System.out.println("The system is disabled");throw new DisabledException();}
					catch(EmptyException e) {System.out.println("The is no more notes of this value");throw new EmptyException();}
					catch(OverloadException e) {System.out.println("The deliver sink is full");throw new OverloadException();}
				}
			}
			List<BigDecimal> coins = station.coinDenominations;
			Collections.sort(coins);
			for(int i=coins.size()-1 ;i>= 0;i--) {
				BigDecimal amo = coins.get(i);
				while (change.compareTo(amo) >= 0 && station.coinDispensers.get(amo).size() > 0) {
					try {
					station.coinDispensers.get(amo).emit();
					change = change.subtract(amo);
					}catch(DisabledException e) {System.out.println("The system is disabled");throw new DisabledException();}
					catch(EmptyException e) {System.out.println("The is no more notes of this value");throw new EmptyException();}
					catch(OverloadException e) {System.out.println("The deliver sink is full");throw new OverloadException();}
				}
			}
			if(change.compareTo(new BigDecimal("0.05")) > 0) {
				System.out.println(change +" Failed to give proper change");
				throw new SimulationException("Failed to give proper change");
			}
		}

		
	}
}
