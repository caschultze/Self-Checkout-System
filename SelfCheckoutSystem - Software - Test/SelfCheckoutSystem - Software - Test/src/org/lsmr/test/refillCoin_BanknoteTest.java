package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.ControlUnit;

public class refillCoin_BanknoteTest {

	public ControlUnit cu;
	public Currency currency;
	BigDecimal coinChange = new BigDecimal("0");
	int noteChange = 0;
	
	@Before
	public void setup() {
		currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
	}
		
	
	
	
	@Test
	public void loadTooMuchCoin() {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Coin[] notes = new Coin[201];
		for(int i =0;i<201;i++) {
			notes[i] = new Coin(new BigDecimal("0.05"),currency);
		}
		try {
		cu.refillMoney.loadCoins(notes);
		assertTrue("Error should've been thrown",false);
		}catch(Exception e) {
			if (e instanceof SimulationException) {
				assertTrue("Not the correct Errror",false);}
			}
	}
	
	@Test
	public void loadTooMuchNote() {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Banknote[] notes = new Banknote[201];
		for(int i =0;i<201;i++) {
			notes[i] = new Banknote(5,currency);
		}
		try {
		cu.refillMoney.loadBanknotes(notes);
		assertTrue("Error should've been thrown",false);
		}catch(Exception e) {
			if (e instanceof SimulationException) {
				assertTrue("Not the correct Errror",false);}
			}
	}
	
	@Test
	public void badNotes() {
		Banknote[] notes = new Banknote[3];
		notes[0] = new Banknote(5,currency);
		notes[1] = new Banknote(5,currency);
		notes[2] = new Banknote(10,currency);
		try {
			cu.refillMoney.loadBanknotes(notes);
			assertTrue("Should've thrown an error",false);
		}catch(Exception e){}		
	}
	
	@Test
	public void badCoins() {
		Coin[] notes = new Coin[3];
		notes[0] = new Coin(new BigDecimal("0.05"),currency);
		notes[1] = new Coin(new BigDecimal("0.05"),currency);
		notes[2] = new Coin(new BigDecimal("0.10"),currency);
		try {
			cu.refillMoney.loadCoins(notes);
			assertTrue("Should've thrown an error",false);
		}catch(Exception e){}		
	}
	
	@Test
	public void propercoins() {
		Coin[] notes = new Coin[3];
		notes[0] = new Coin(new BigDecimal("0.05"),currency);
		notes[1] = new Coin(new BigDecimal("0.05"),currency);
		notes[2] = new Coin(new BigDecimal("0.05"),currency);
		try {
			cu.refillMoney.loadCoins(notes);
			assertEquals("The dispenser should be empty",3,cu.checkoutStation.coinDispensers.get(new BigDecimal("0.05")).size());
		}catch(Exception e){}		
	}
	
	@Test
	public void propernotes() {
		Banknote[] notes = new Banknote[3];
		notes[0] = new Banknote(5,currency);
		notes[1] = new Banknote(5,currency);
		notes[2] = new Banknote(5,currency);
		try {
			cu.refillMoney.loadBanknotes(notes);
			assertEquals("The dispenser should be empty",3,cu.checkoutStation.banknoteDispensers.get(5).size());
		}catch(Exception e){}		
	}
}
