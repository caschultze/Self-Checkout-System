//package org.lsmr.test;
//import org.lsmr.software.*;
//
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.Barcode;
//import org.lsmr.selfcheckout.BarcodedItem;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.Locale;
//import java.util.HashMap;
//
//import org.lsmr.software.ControlUnit;
//
//public class ScanItemTest {
//	
//	public ControlUnit cu;
//	HashMap<Barcode, BarcodedItem> BarcodeDatabase;
//	String code1 = "123456789101"; 
//	double weight1 = 1.0;
//	String code2 = "123456789102"; 
//	double weight2 = 2.0;
//	String code3 = "123456789103"; 
//	double weight3 = 3.0;
//	String code4 = "123456789104"; 
//	double weight4 = 4.0;
//	String code5 = "123456789105"; 
//	double weight5 = 5.0;
//	public Barcode e;
//	public Barcode f;
//	public Barcode g;
//	public Barcode h;
//	public Barcode i;
//	
//	@Before
//	public void setup() {
//		
//		Currency currency = Currency.getInstance(Locale.CANADA);
//		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
//		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
//		int scaleMaximumWeight = 100;
//		int scaleSensitivity = 1;
//		
//		BarcodeDatabase = new HashMap <Barcode, BarcodedItem>();
//
//		
//		e = new Barcode(code1);
//		f = new Barcode(code2);
//		g = new Barcode(code3);
//		h = new Barcode(code4);
//		i = new Barcode(code5);
//		BarcodedItem Coke = new BarcodedItem(e, weight1);
//		BarcodedItem Gum = new BarcodedItem(f, weight2);
//		BarcodedItem Hat = new BarcodedItem(g, weight3);
//		BarcodedItem Cake = new BarcodedItem(h, weight4);
//		BarcodedItem Chips = new BarcodedItem(i, weight5);
//		
//		BarcodeDatabase.put(e, Coke);
//		BarcodeDatabase.put(f, Gum);
//		BarcodeDatabase.put(g, Hat);
//		BarcodeDatabase.put(h, Cake);
//		BarcodeDatabase.put(i, Chips);
//		
//		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
//		cu.main(null);
//	}
//	
//	//Utilizing barcode item database to create a shopping list
//	//Testing that scans were successful
//	//Checked by comparing number of product inputs to scanned in class
//	//Should be the same number, if failed then a randomization failure has occurred
////	@Test
////	public void scanSuccessTest() { 
////		ArrayList<BarcodedItem> oneProduct = new ArrayList<BarcodedItem>();
////		oneProduct.add(BarcodeDatabase.get(e));
////		cu.itemScan.scanItems(oneProduct);
////		boolean expected = true;
////		boolean actual = false;
////		int i = oneProduct.size();
////		int arraySize = cu.itemScan.ScannedList.size();
////		if (i == arraySize) {
////			for (int j = 0; j< i; j++) {
////				if (oneProduct.get(j).equals(cu.itemScan.ScannedList.get(j))) {
////					actual = true;
////				}
////			}
////		}
////		assertEquals(expected, actual);
////	} 
//	   
//	//Checking the enabled state of Scanner
//	@Test 
//	public void enableScanner() {
//		cu.checkoutStation.mainScanner.enable();
//		boolean expected = true;
//		boolean actual = cu.itemScan.getEnableCheck();
//		assertEquals(expected, actual);
//	}
//	
//	//Checking the disabled state of Scanner
//	@Test
//	public void disableScanner() {
//		cu.checkoutStation.mainScanner.disable();
//		ArrayList<BarcodedItem> oneProduct = new ArrayList<BarcodedItem>();
//		oneProduct.add(BarcodeDatabase.get(e));
//		cu.itemScan.scanItems(oneProduct);
//		boolean expected = true;
//		boolean actual = cu.itemScan.getDisableCheck();
//		assertEquals(expected, actual);
//	} 
//	
//	//Utilizing barcode item database to create a shopping list
//	// Tests the number of successful scanned items 
//	//Input: 2 barcoded items, expected: 2
//	//If expected and actual differ, then a randomization failure has occurred 
//	@Test 
//	public void numSuccessTest() {
//		ArrayList<BarcodedItem> twoProduct = new ArrayList<BarcodedItem>();
//		twoProduct.add(BarcodeDatabase.get(e));
//		twoProduct.add(BarcodeDatabase.get(f));
//		cu.itemScan.scanItems(twoProduct);
//		int expected = 2;
//		assertEquals(expected, cu.itemScan.getNumSuccess());
//	}
//	
//	//Utilizing barcode item database to create a shopping list
//	//Testing with five products that should expect failure due to randomization
//	@Test
//	public void probablityScan() {
//		ArrayList<BarcodedItem> fiveProduct = new ArrayList<BarcodedItem>();
//		fiveProduct.add(BarcodeDatabase.get(e));
//		fiveProduct.add(BarcodeDatabase.get(f));
//		fiveProduct.add(BarcodeDatabase.get(g));
//		fiveProduct.add(BarcodeDatabase.get(h));
//		fiveProduct.add(BarcodeDatabase.get(i));
//		cu.itemScan.scanItems(fiveProduct);
//		int expected = 5;
//		int actual = cu.itemScan.getNumSuccess();
//		assertEquals(expected, actual);
//	}
//	
//	@Test (expected = NullPointerException.class)
//	public void nullListScan() {
//		ArrayList<BarcodedItem> nullList = null;
//		cu.itemScan.scanItems(nullList);;
//	}
//	 
//	
//}   