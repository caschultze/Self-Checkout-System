package org.lsmr.software;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class refillCoin extends CoinPayment {

	
	
	public SelfCheckoutStation station;
	public Map<BigDecimal, CoinDispenserListenerStub> coinDispensersLis;
	private CurrentSessionData session;
	
	public refillCoin(SelfCheckoutStation checkoutStation) {
		super(checkoutStation);
	}
	
	
	@Override
	public void registerListeners() {
		coinDispensersLis = new HashMap<>();
		
		for(int i = 0; i < station.coinDenominations.size(); i++)
			coinDispensersLis.put(station.coinDenominations.get(i), new CoinDispenserListenerStub(station.coinDenominations.get(i)));

		for(CoinDispenserListenerStub coinDispenerLisn: coinDispensersLis.values()) {
			station.coinDispensers.get(coinDispenerLisn.getValue()).register(coinDispenerLisn);
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
			this.setEmptyDispenserCheck(false);
		}catch(OverloadException e) {
			System.out.println("The despenser will over flow when loading theses coins."); throw e;
		}catch(SimulationException e) {
			System.out.println("There was an invalid coin in the inserted coins.");throw e;
		}
	}
	

}
