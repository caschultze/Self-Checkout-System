//package org.lsmr.test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Currency;
//import java.util.HashMap;
//import java.util.Locale;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.lsmr.selfcheckout.Barcode;
//import org.lsmr.selfcheckout.BarcodedItem;
//import org.lsmr.selfcheckout.devices.OverloadException;
//import org.lsmr.selfcheckout.devices.SimulationException;
//import org.lsmr.software.ControlUnit;
//
//public class BagItemTest {
//
//	public ControlUnit cu;
//	ArrayList<BarcodedItem> bagged = new ArrayList<BarcodedItem>();
//	HashMap<Barcode, BarcodedItem> BarcodeDatabase;
//	BarcodedItem lightItem; 
//	public Barcode light = null;
//	BarcodedItem bd; 		
//	public Barcode b;
//	BarcodedItem bd2;		
//	public Barcode b2;
//	BarcodedItem bd3;		
//	public Barcode b3;
//	BarcodedItem heavy1;	
//	public Barcode h1;
//	BarcodedItem heavy2;	
//	public Barcode h2;
//	BarcodedItem heavy3;	
//	public Barcode h3;
//	BarcodedItem heavy4;	
//	public Barcode h4;
//	
//	String codeLight = "123456789101"; 
//	double weight1 = 0.4;
//	String code1 = "123456789102"; 
//	double weight2 = 5.4;
//	String code2 = "123456789103"; 
//	double weight3 = 8.7;
//	String code3 = "123456789104"; 
//	double weight4 = 3.2;
//	String codeH1 = "123456789105"; 
//	double weight5 = 50.4;
//	String codeH2 = "123456789106"; 
//	double weight6 = 10.6;
//	String codeH3 = "123456789107"; 
//	double weight7 = 48.7;
//	String codeH4 = "123456789108"; 
//	double weight8 = 102.2;
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
//		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
//		cu.main(null);
//		
//		
//		light = new Barcode(codeLight);
//		b = new Barcode(code1);
//		b2 = new Barcode(code2);
//		b3 = new Barcode(code3);
//		h1 = new Barcode(codeH1);
//		h2 = new Barcode(codeH2);
//		h3 = new Barcode(codeH3);
//		h4 = new Barcode(codeH4);
//		lightItem = new BarcodedItem(light, weight1);
//		bd = new BarcodedItem(b, weight2);
//		bd2 = new BarcodedItem(b2, weight3);
//		bd3 = new BarcodedItem(b3, weight4);
//		heavy1 = new BarcodedItem(h1, weight5);
//		heavy2 = new BarcodedItem(h2, weight6);
//		heavy3 = new BarcodedItem(h3, weight7);
//		heavy4 = new BarcodedItem(h4, weight8);
//		
//		//adds the items to the Hash map
//		BarcodeDatabase.put(light, lightItem);
//		BarcodeDatabase.put(b, bd);
//		BarcodeDatabase.put(b2, bd2);
//		BarcodeDatabase.put(b3, bd3);
//		BarcodeDatabase.put(h1, heavy1);
//		BarcodeDatabase.put(h2, heavy2);
//		BarcodeDatabase.put(h3, heavy3);
//		BarcodeDatabase.put(h4, heavy4);
//	}
//	
//	//Testing the bagging area
//	
//	//Testing an item that is lighter than the sensitivity, weight should not change
//	@Test
//	public void testItemTooLight() {
//		bagged.add(BarcodeDatabase.get(light));
//		cu.itemBag.bagItems(bagged);
//		assertEquals(false, cu.itemBag.getValidWeightCheck());
//	}
//	
//	//Testing a valid item, weight should change accordingly
//	@Test
//	public void testValidItem() throws OverloadException {
//		bagged.add(BarcodeDatabase.get(b));
//		cu.itemBag.bagItems(bagged);
//		assertTrue("5.4".equals(String.valueOf(cu.itemBag.scs.baggingArea.getCurrentWeight())));
//	}
//	
//	//Testing a valid item, ArrayLists should match
//	@Test
//	public void testValidItemList() throws OverloadException {
//		bagged.add(BarcodeDatabase.get(b));
//		cu.itemBag.bagItems(bagged);
//		Barcode bb = null;
//		Barcode cc = null;
//		for(BarcodedItem b : bagged) {
//			bb = b.getBarcode();
//		}
//		for (BarcodedItem c: cu.itemBag.getBaggedItems()) {
//			cc = c.getBarcode();
//		}
//		assertEquals(bb,cc);
//	}	
//	
//	//Testing multiple valid items, weight should change accordingly
//	@Test
//	public void testMultipleValidItem() throws OverloadException {
//		bagged.add(BarcodeDatabase.get(b));
//		bagged.add(BarcodeDatabase.get(b2));
//		bagged.add(BarcodeDatabase.get(b3));
//		cu.itemBag.bagItems(bagged);
//		assertTrue("17.3".equals(String.valueOf(cu.itemBag.scs.baggingArea.getCurrentWeight())));
//	}
//	
//	//Testing multiple valid items, ArrayLists should match
//	@Test
//	public void testMultipleValidItemList() {
//		bagged.add(BarcodeDatabase.get(b));
//		bagged.add(BarcodeDatabase.get(b2));
//		bagged.add(BarcodeDatabase.get(b3));
//		cu.itemBag.bagItems(bagged);
//		Barcode bb = null;
//		Barcode cc = null;
//		for(BarcodedItem b : bagged) {
//			bb = b.getBarcode();
//		}
//		for (BarcodedItem c: cu.itemBag.getBaggedItems()) {
//			cc = c.getBarcode();
//		}
//		assertEquals(bb,cc);
//	}	
//
//	//Testing placing an item twice
//	@Test
//	public void testTwicePlaced() {
//		bagged.add(BarcodeDatabase.get(b));
//		cu.itemBag.bagItems(bagged);
//		try{
//			cu.itemBag.bagItems(bagged);
//		}
//		catch(SimulationException e) {
//			return;
//		}
//		fail("SimulationException expected");
//	}
//	
//	//Testing the weight limit
//	//Over the weight limit
//	@Test 
//	public void testWeightLimitOver() {
//		bagged.add(BarcodeDatabase.get(h1));
//		bagged.add(BarcodeDatabase.get(h2));
//		bagged.add(BarcodeDatabase.get(h3));
//		cu.itemBag.bagItems(bagged);
//		assertEquals(true, cu.itemBag.getCheckOverloaded());
//	}
//	
//	//Under the weight limit
//	@Test
//	public void testWeightLimitUnder() {
//		bagged.add(BarcodeDatabase.get(h1));
//		bagged.add(BarcodeDatabase.get(h3));
//		cu.itemBag.bagItems(bagged);
//		assertEquals(false, cu.itemBag.getCheckOverloaded());
//	}
//	
//	//Testing that weight is not changing without additional item being added
//	@Test
//	public void testNoWeightChange() {
//		bagged.add(BarcodeDatabase.get(h1));
//		cu.itemBag.bagItems(bagged);
//		bagged.remove(0);
//		bagged.add(BarcodeDatabase.get(light));
//		cu.itemBag.bagItems(bagged);
//		assertEquals(false, cu.itemBag.getValidWeightCheck());
//	}
//	
//	//Test enable
//	@Test
//	public void testEnable() {
//		cu.checkoutStation.baggingArea.enable();
//		bagged.add(BarcodeDatabase.get(h1));
//		cu.itemBag.bagItems(bagged);
//		assertEquals(true, cu.itemBag.getEnableCheck());
//	}
//	
//	@Test (expected = NullPointerException.class)
//	public void testNullBagged() {
//		bagged = null;
//		cu.itemBag.bagItems(bagged);
//	}
//}
