package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;


import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.BanknoteSlot;
import org.lsmr.selfcheckout.devices.CoinTray;
import org.lsmr.selfcheckout.devices.DisabledException;
import org.lsmr.selfcheckout.devices.EmptyException;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.BanknoteSlotListener;
import org.lsmr.selfcheckout.devices.listeners.CoinTrayListener;
import org.lsmr.software.ControlUnit;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class ReceiveChangeTest {

	
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
	
		
		CoinTrayListener tray = new CoinTrayListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void coinAdded(CoinTray tray) {
				List<Coin> c = tray.collectCoins();
				Coin coin = c.get(0);
				BigDecimal amo = coin.getValue();
				coinChange = coinChange.add(amo);

			}
			
		};
		
		cu.checkoutStation.coinTray.register(tray);
		BanknoteSlotListener slot = new BanknoteSlotListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteInserted(BanknoteSlot slot) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void banknoteEjected(BanknoteSlot slot) {
				noteChange += slot.removeDanglingBanknote().getValue();
			}

			@Override
			public void banknoteRemoved(BanknoteSlot slot) {
				// TODO Auto-generated method stub
				
			}
			
		};
		cu.checkoutStation.banknoteOutput.register(slot);

	}
	
	
	 
	@Test
	public void properNickelTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payCoin.payWithCoins(new Coin(new BigDecimal("0.05"),currency));
		try {
			cu.changeReceive.giveChange();
			boolean result = (coinChange.compareTo(new BigDecimal("0.05")) == 0);
			assertTrue("The change didn't match",result);
		}catch(Exception e){throw e;}
		
	}		
	
	@Test
	public void properMulitpleCoinTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payCoin.payWithCoins(new Coin(new BigDecimal("0.05"),currency));
		cu.payCoin.payWithCoins(new Coin(new BigDecimal("0.05"),currency));
		try {
			cu.changeReceive.giveChange();
			boolean result = (coinChange.compareTo(new BigDecimal("0.10")) == 0);
			assertTrue("The change didn't match",result);
		}catch(Exception e){throw e;}
		
	}
	
	@Test
	public void properFiveTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Banknote[] notes = new Banknote[1];
		notes[0] = new Banknote(5,currency);
		try {
			cu.changeReceive.loadBanknotes(notes);
			cu.changeReceive.giveChange();
			boolean result = (noteChange == 5);
			assertTrue("The change didn't match",result);
		}catch(Exception e){throw e;}
	}	
	
	@Test
	public void properMulitpleFiveTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Banknote[] notes = new Banknote[3];
		notes[0] = new Banknote(5,currency);
		notes[1] = new Banknote(5,currency);
		notes[2] = new Banknote(5,currency);
		try {
			cu.changeReceive.loadBanknotes(notes);
			cu.changeReceive.giveChange();
			boolean result = (noteChange == 10);
			assertEquals("The change didn't match",10,noteChange);
		}catch(Exception e){throw e;}
	}
	
	@Test
	public void badNotes() {
		Banknote[] notes = new Banknote[3];
		notes[0] = new Banknote(5,currency);
		notes[1] = new Banknote(5,currency);
		notes[2] = new Banknote(10,currency);
		try {
			cu.changeReceive.loadBanknotes(notes);
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
			cu.changeReceive.loadCoins(notes);
			assertTrue("Should've thrown an error",false);
		}catch(Exception e){}		
	}
	
	@Test
	public void unloadcoins() {
		Coin[] notes = new Coin[3];
		notes[0] = new Coin(new BigDecimal("0.05"),currency);
		notes[1] = new Coin(new BigDecimal("0.05"),currency);
		notes[2] = new Coin(new BigDecimal("0.05"),currency);
		try {
			cu.changeReceive.loadCoins(notes);
			cu.changeReceive.unloadCoins(new BigDecimal("0.05"));
			assertEquals("The dispenser should be empty",0,cu.checkoutStation.coinDispensers.get(new BigDecimal("0.05")).size());
		}catch(Exception e){}		
	}
	
	@Test
	public void unloadnotes() {
		Banknote[] notes = new Banknote[3];
		notes[0] = new Banknote(5,currency);
		notes[1] = new Banknote(5,currency);
		notes[2] = new Banknote(5,currency);
		try {
			cu.changeReceive.loadBanknotes(notes);
			cu.changeReceive.unloadBanknotes(5);
			assertEquals("The dispenser should be empty",0,cu.checkoutStation.banknoteDispensers.get(5).size());
		}catch(Exception e){}		
	}
	
	@Test
	public void noChange() {
		try {
			cu.sessionData.getTotalPrice(0);
			cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
			cu.changeReceive.giveChange();
			boolean result = (coinChange.compareTo(new BigDecimal("0")) == 0);
			assertTrue("The change didn't match",result);
			assertEquals("The change didn't match",0,noteChange);
		} catch (DisabledException | EmptyException | OverloadException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void emptyFiveTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		cu.changeReceive.unloadBanknotes(5);
		try {
			cu.changeReceive.giveChange();
			assertTrue("Error should've been thrown",false);
		}catch(Exception e){}
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
		cu.changeReceive.loadCoins(notes);
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
		cu.changeReceive.loadBanknotes(notes);
		assertTrue("Error should've been thrown",false);
		}catch(Exception e) {
			if (e instanceof SimulationException) {
				assertTrue("Not the correct Errror",false);}
			}
	}
	
	@Test
	public void disabledNickelTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payCoin.payWithCoins(new Coin(new BigDecimal("0.05"),currency));
		try {
			cu.checkoutStation.coinDispensers.get(new BigDecimal("0.05")).disable();
			cu.changeReceive.giveChange();
			
			assertTrue("The error should be thrown",false);
		}catch(Exception e){}
		
	}
	
	@Test
	public void disabledFiveTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Banknote[] notes = new Banknote[1];
		notes[0] = new Banknote(5,currency);
		try {
			cu.changeReceive.loadBanknotes(notes);
			cu.checkoutStation.banknoteDispensers.get(5).disable();
			cu.changeReceive.giveChange();
			assertTrue("The error should be thrown",false);
		}catch(Exception e){}
	}	
	
	@Test
	public void tooMuchChange() {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal(0));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Coin[] notes = new Coin[30];
		for(int i =0;i<30;i++) {
			notes[i] = new Coin(new BigDecimal("0.05"),currency);
		}
		try {
		cu.changeReceive.loadCoins(notes);
		cu.changeReceive.giveChange();
		assertTrue("Error should've been thrown",false);
		}catch(Exception e) {
			if (e instanceof OverloadException) {
			}else {		
				assertTrue("Not the correct Errror",false);}
		}
	}
	
	@Test
	public void properPennyTest() throws Exception {
		cu.sessionData.getTotalPrice(0);
		cu.sessionData.setAndGetTotalPrice(new BigDecimal("0.06"));
		cu.payBanknote.payWithBanknotes(new Banknote(5,currency));
		Banknote[] notes = new Banknote[1];
		notes[0] = new Banknote(5,currency);
		Coin[] toonie = new Coin[2];
		toonie[0] = new Coin(new BigDecimal("2.00"),currency);
		toonie[1] = new Coin(new BigDecimal("2.00"),currency);
		cu.changeReceive.loadCoins(toonie);
		Coin[] quarter = new Coin[3];
		quarter[0] = new Coin(new BigDecimal("0.25"),currency);
		quarter[1] = new Coin(new BigDecimal("0.25"),currency);
		quarter[2] = new Coin(new BigDecimal("0.25"),currency);
		cu.changeReceive.loadCoins(quarter);
		Coin[] dime = new Coin[1];
		dime[0] = new Coin(new BigDecimal("0.10"),currency);
		cu.changeReceive.loadCoins(dime);
		Coin[] nickel = new Coin[1];
		nickel[0] = new Coin(new BigDecimal("0.05"),currency);
		cu.changeReceive.loadCoins(nickel);
		try {
			cu.changeReceive.loadBanknotes(notes);
			cu.changeReceive.giveChange();
			boolean result = (coinChange.compareTo(new BigDecimal("4.90")) == 0);
			assertTrue("The change didn't match",result);
		}catch(Exception e){throw e;}
	}
}
