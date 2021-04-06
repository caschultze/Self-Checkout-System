package org.lsmr.software;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class refillCoin_Banknote {

	
	
	public SelfCheckoutStation station;
	public Map<BigDecimal, CoinDispenserListenerStub> coinDispensersLis;
	public Map<Integer, BanknoteDispenserListenerStub> banknoteDispensersLis;
	private CurrentSessionData session;
	
	public refillCoin_Banknote(SelfCheckoutStation checkoutStation) {
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
	
}
