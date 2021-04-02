//package org.lsmr.test;
//
//import static org.junit.Assert.assertEquals;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.Locale;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.Banknote;
//import org.lsmr.selfcheckout.Barcode;
//import org.lsmr.selfcheckout.Coin;
//import org.lsmr.selfcheckout.devices.DisabledException;
//import org.lsmr.selfcheckout.devices.OverloadException;
//import org.lsmr.selfcheckout.products.BarcodedProduct;
//import org.lsmr.software.ControlUnit;
//
//public class BanknotePaymentTest {
//	
//	public ControlUnit cu;
//	public BarcodedProduct coke;
//	public BarcodedProduct gum;
//	public BarcodedProduct charger;
//	public BarcodedProduct pen;
//	public BarcodedProduct microwave;
//	public ArrayList<BarcodedProduct> scannedProducts = new ArrayList<>();
//	public ArrayList<Banknote> banknotesToBeInserted = new ArrayList<>();
//	public HashMap<Barcode, BarcodedProduct> database;
//	public Currency currency;
//	public Banknote fiveCAD;
//	public Banknote tenCAD;
//	public Banknote twentyCAD;
//	public Banknote fiftyCAD;
//	public Banknote hundredCAD;
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
////		int option = 4;
//		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
//		cu.main(null);
//			
//		int five = 5;
//		int ten = 10;
//		int twenty = 20;
//		int fifty = 50; 
//		int hundred = 100;
//		fiveCAD = new Banknote(five, currency);
//		tenCAD = new Banknote(ten, currency);
//		twentyCAD = new Banknote(twenty, currency);
//		fiftyCAD = new Banknote(fifty, currency);
//		hundredCAD = new Banknote(hundred, currency);
//	
//
//		cokeBarcode = new Barcode("123123");
//		BigDecimal cokePrice = new BigDecimal("5.00");
//		coke = new BarcodedProduct(cokeBarcode, "coke", cokePrice);
//
//		gumBarcode = new Barcode("123124");
//		BigDecimal gumPrice = new BigDecimal("10.00");
//		gum = new BarcodedProduct(gumBarcode, "gum", gumPrice);
//	
//		chargerBarcode = new Barcode("123125");
//		BigDecimal chargerPrice = new BigDecimal("15.00");
//		charger = new BarcodedProduct(chargerBarcode, "charger", chargerPrice);
//
//		penBarcode = new Barcode("123126");
//		BigDecimal penPrice = new BigDecimal("20.00");
//		pen = new BarcodedProduct(penBarcode, "pen", penPrice);
//
//		microwaveBarcode = new Barcode("123127");
//		BigDecimal microwavePrice = new BigDecimal("40.00");
//		microwave = new BarcodedProduct(microwaveBarcode, "microwave", microwavePrice);
//
//		database = new HashMap<Barcode, BarcodedProduct>();
//		database.put(cokeBarcode, coke);
//		database.put(gumBarcode, gum);
//		database.put(chargerBarcode, charger);
//		database.put(penBarcode, pen);
//		database.put(microwaveBarcode, microwave);
//	}
//	
////	@Test 
////	public void testBanknotePayOneItem() {
////		scannedProducts.add(database.get(cokeBarcode));
////		banknotesToBeInserted.add(fiveCAD);
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted,scannedProducts);
////		int expected = 0;
////		int actual = cu.payBanknote.getBalance();
////		assertEquals(expected, actual);
////	}
////	
////	
////	@Test
////	public void testBanknotePayMultipleItems() {
////		scannedProducts.add(database.get(cokeBarcode));
////		scannedProducts.add(database.get(cokeBarcode));
////		scannedProducts.add(database.get(gumBarcode));
////		banknotesToBeInserted.add(twentyCAD);
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////		int expected = 0;
////		int actual = cu.payBanknote.getBalance();
////		assertEquals(expected, actual);
////	}
////	
////	
////	@Test
////	public void testBanknotePayMultipleBills() {
////		scannedProducts.add(database.get(microwaveBarcode));
////		for (int i = 0; i < 4; i++) {
////			banknotesToBeInserted.add(tenCAD);
////		}
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////		int actual = 0;
////		int expected = cu.payBanknote.getBalance();
////		assertEquals(expected, actual);
////	}
////	
////	@Test
////	public void testBanknotePaymentWith1000bills() {
////		scannedProducts.add(database.get(chargerBarcode));
////		for (int i = 0; i < 2000; i++) {
////			banknotesToBeInserted.add(tenCAD);
////		}
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////		boolean actual = cu.payBanknote.getFullStorageCheck();
////		boolean expected = true;
////		assertEquals(expected, actual);
////	}
////	
////	@Test (expected = NullPointerException.class)
////	public void testBanknotePayNullBills() {
////		scannedProducts.add(database.get(chargerBarcode));
////		banknotesToBeInserted = null;
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////	}
////	
////	@Test (expected = NullPointerException.class)
////	public void testBanknotePaysNullBarcodedProducts() {
////		banknotesToBeInserted.add(fiveCAD);
////		scannedProducts = null;
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////	}
////	
////	@Test
////	public void testBanknotePayInvalidBanknote() {
////		scannedProducts.add(database.get(chargerBarcode));
////		scannedProducts.add(database.get(chargerBarcode));
////		int invalid = 2;
////		Banknote invalidNote = new Banknote(invalid, currency);
////		banknotesToBeInserted.add(invalidNote);
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////		int expected = 1;
////		int actual = cu.payBanknote.getNumInvalidBanknotes();
////		assertEquals(expected, actual);
////	}
////	
////	@Test
////	public void testBanknotePayInsufficient() {
////		scannedProducts.add(database.get(chargerBarcode));
////		banknotesToBeInserted.add(fiveCAD);
////		cu.payBanknote.payWithBanknotes(banknotesToBeInserted, scannedProducts);
////		boolean expected = true;
////		int actual = cu.payBanknote.getBalance();
////		boolean actualBool = false;
////		if (actual > 0) {
////			actualBool = true;
////		}
////		assertEquals(expected, actualBool);
////	}
//	
//}
