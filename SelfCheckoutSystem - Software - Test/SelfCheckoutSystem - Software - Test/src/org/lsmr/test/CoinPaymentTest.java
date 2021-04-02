//package org.lsmr.test;
//
//import static org.junit.Assert.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.Locale;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.Barcode;
//import org.lsmr.selfcheckout.Coin;
//import org.lsmr.selfcheckout.devices.SimulationException;
//import org.lsmr.selfcheckout.products.BarcodedProduct;
//import org.lsmr.software.ControlUnit;
//
//public class CoinPaymentTest {
//
//	public ControlUnit cu;
//	public BarcodedProduct coke;
//	public BarcodedProduct gum;
//	public BarcodedProduct charger;
//	public BarcodedProduct pen;
//	public BarcodedProduct microwave;
//	public ArrayList<BarcodedProduct> scannedProducts = new ArrayList<>();
//	public ArrayList<Coin> coinsToBeInserted = new ArrayList<>();
//	public HashMap<Barcode, BarcodedProduct> database;
//	public Currency currency;
//	public Coin nickel;
//	public Coin dime;
//	public Coin quarter;
//	public Coin loonie;
//	public Coin toonie;
//	public Barcode cokeBarcode;
//	public Barcode gumBarcode;
//	public Barcode chargerBarcode;
//	public Barcode penBarcode;
//	public Barcode microwaveBarcode;
//	
//	@Before
//	public void setup() {
//		
//		currency = Currency.getInstance(Locale.CANADA);
//		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
//		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
//		int scaleMaximumWeight = 100;
//		int scaleSensitivity = 1;
////		int option = 3;
//		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
//		cu.main(null);
//		
//		cokeBarcode = new Barcode("123123");
//		BigDecimal cokePrice = new BigDecimal("0.05");
//		coke = new BarcodedProduct(cokeBarcode, "coke", cokePrice);
//		nickel = new Coin(cokePrice, currency);
////		scannedProducts.add(coke);
//		
//		gumBarcode = new Barcode("123124");
//		BigDecimal gumPrice = new BigDecimal("0.10");
//		gum = new BarcodedProduct(gumBarcode, "gum", gumPrice);
//		dime = new Coin(gumPrice, currency);
////		scannedProducts.add(gum);
//		
//		chargerBarcode = new Barcode("123125");
//		BigDecimal chargerPrice = new BigDecimal("0.25");
//		charger = new BarcodedProduct(chargerBarcode, "charger", chargerPrice);
//		quarter = new Coin(chargerPrice, currency);
////		scannedProducts.add(charger);
//		
//		penBarcode = new Barcode("123126");
//		BigDecimal penPrice = new BigDecimal("1.00");
//		pen = new BarcodedProduct(penBarcode, "pen", penPrice);
//		loonie = new Coin(penPrice, currency);
////		scannedProducts.add(pen);
//		
//		microwaveBarcode = new Barcode("123127");
//		BigDecimal microwavePrice = new BigDecimal("2.00");
//		microwave = new BarcodedProduct(microwaveBarcode, "microwave", microwavePrice);
//		toonie = new Coin(microwavePrice, currency);
////		scannedProducts.add(microwave);
//		
//		database = new HashMap<Barcode, BarcodedProduct>();
//		database.put(cokeBarcode, coke);
//		database.put(gumBarcode, gum);
//		database.put(chargerBarcode, charger);
//		database.put(penBarcode, pen);
//		database.put(microwaveBarcode, microwave);
//	}
//	
//	@After
//	public void tearDown() {
//		cu = null;
//		coinsToBeInserted = null;
//		scannedProducts = null;
////		cu.cp = null;
////		cu.checkoutStation = null;
////		cu.cp.scs = null;
//	}
//	
////	@Test 
////	public void testPayWithCoinsOneItem() {
////		scannedProducts.add(database.get(cokeBarcode));
////		coinsToBeInserted.add(nickel);
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////		double expected = 0.00;
////		double actual = cu.payCoin.getBalance().doubleValue();
////		assertEquals(expected, actual, 0.0);
////	}
////	
////	@Test
////	public void testPayWithCoinsMultipleItems() {
////		scannedProducts.add(database.get(chargerBarcode));
////		scannedProducts.add(database.get(penBarcode));
////		scannedProducts.add(database.get(microwaveBarcode));
////		coinsToBeInserted.add(quarter);
////		coinsToBeInserted.add(loonie);
////		coinsToBeInserted.add(toonie);
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////		double expected = 0.00;
////		double actual = cu.payCoin.getBalance().doubleValue();
////		assertEquals(expected, actual, 0.0);
////	}
////	
////	@Test
////	public void testPayWithCoins1000Coins() {
////		scannedProducts.add(database.get(chargerBarcode));
////		for (int i = 0; i < 1500; i++) {
////			coinsToBeInserted.add(dime);
////		}
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////		boolean actual = cu.payCoin.slotDisabled;
////		boolean expected = true;
////		assertEquals(expected, actual);
////	}
////	
////	@Test (expected = NullPointerException.class)
////	public void testPayWithCoinsNullCoins() {
////		scannedProducts.add(database.get(chargerBarcode));
////		coinsToBeInserted = null;
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////	}
////	
////	@Test (expected = NullPointerException.class)
////	public void testPayWithCoinsNullBarcodedProducts() {
////		coinsToBeInserted.add(dime);
////		scannedProducts = null;
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////	}
////	
////	@Test
////	public void testPayWithCoinsInvalidCoin() {
////		scannedProducts.add(database.get(chargerBarcode));
////		scannedProducts.add(database.get(chargerBarcode));
////		BigDecimal invalid = new BigDecimal("0.50");
////		Coin invalidCoin = new Coin(invalid, currency);
////		coinsToBeInserted.add(invalidCoin);
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////		int expected = 1;
////		int actual = cu.payCoin.getNumInvalidCoins();
////		assertEquals(expected, actual);
////	}
////	
////	@Test
////	public void testPayWithCoinsInsufficientFunds() {
////		scannedProducts.add(database.get(chargerBarcode));
////		coinsToBeInserted.add(dime);
////		cu.payCoin.payWithCoins(coinsToBeInserted, scannedProducts);
////		boolean expected = true;
////		double actual = cu.payCoin.getBalance().doubleValue();
////		boolean actualBool = false;
////		if (actual > 0) {
////			actualBool = true;
////		}
////		assertEquals(expected, actualBool);
////	}
//}
