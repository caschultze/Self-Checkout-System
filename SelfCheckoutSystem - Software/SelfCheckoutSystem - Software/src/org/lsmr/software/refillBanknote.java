package org.lsmr.software;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;

public class refillBanknote extends BanknotePayment {

	public SelfCheckoutStation station;
	public Map<Integer, BanknoteDispenserListenerStub> banknoteDispensersLis;
	private CurrentSessionData session;
	
	public refillBanknote(SelfCheckoutStation checkoutStation) {
//		station = checkoutStation;
//		session = new CurrentSessionData();
//		registerListeners();
		super(checkoutStation);
	}
	
	@Override
	public void registerListeners() {
		banknoteDispensersLis = new HashMap<>();
		
		for(int i = 0; i < station.banknoteDenominations.length; i++)
			banknoteDispensersLis.put(new Integer(station.banknoteDenominations[i]), new BanknoteDispenserListenerStub(station.banknoteDenominations[i]));

		for(BanknoteDispenserListenerStub noteDispenerLisn: banknoteDispensersLis.values()) {
			station.banknoteDispensers.get(noteDispenerLisn.getValue()).register(noteDispenerLisn);
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
			this.setEmptyDispenserCheck(false);
		}catch(OverloadException e) {
			System.out.println("The despenser will over flow when loading theses banknotes."); throw e;
		}catch(SimulationException e) {
			System.out.println("There was an invalid banknote in the inserted banknotes."); throw e;
		}
	}
	
	
	
}
