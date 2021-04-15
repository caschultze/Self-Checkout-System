package org.lsmr.guitest;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData;
import org.lsmr.software.ListProductScreen;
import org.lsmr.software.MainGUI;

public class ListProductScreenTest {
	
	@Before
    public void setup() throws Exception{
        MainGUI.main(null);
		PriceLookupCode code1 = new PriceLookupCode("4123");
		PLUCodedProduct product1 = new PLUCodedProduct(code1,"Apple",BigDecimal.valueOf(1.19));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code1, product1);
			
		PriceLookupCode code2 = new PriceLookupCode("4051");
		PLUCodedProduct product2 = new PLUCodedProduct(code2,"Mango",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code2, product2);
			
		PriceLookupCode code3 = new PriceLookupCode("4022");
		PLUCodedProduct product3 = new PLUCodedProduct(code3,"Grapes",BigDecimal.valueOf(6.27));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code3, product3);
		
		PriceLookupCode code4 = new PriceLookupCode("4011");
		PLUCodedProduct product4 = new PLUCodedProduct(code4,"Banana",BigDecimal.valueOf(1.04));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code4, product4);
		
		PriceLookupCode code5 = new PriceLookupCode("8011");
		PLUCodedProduct product5 = new PLUCodedProduct(code5,"Bag",BigDecimal.valueOf(0.10));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code5, product5);
		
		Barcode barcode1 = new Barcode("12345678910");
		BarcodedProduct barproduct1 = new BarcodedProduct(barcode1,"Chocolate",BigDecimal.valueOf(1.70));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode1, barproduct1);
			
		Barcode barcode2 = new Barcode("22345678910");
		BarcodedProduct barproduct2 = new BarcodedProduct(barcode2,"Cupcake",BigDecimal.valueOf(2.50));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode2, barproduct2);
			
		Barcode barcode3 = new Barcode("32345678910");
		BarcodedProduct barproduct3 = new BarcodedProduct(barcode3,"Eggs",BigDecimal.valueOf(3.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode3, barproduct3);
		
		Barcode barcode4 = new Barcode("42345678910");
		BarcodedProduct barproduct4 = new BarcodedProduct(barcode4,"Milk",BigDecimal.valueOf(2.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode4, barproduct4);
		
		Barcode barcode5 = new Barcode("5345678910");
		BarcodedProduct barproduct5 = new BarcodedProduct(barcode5,"Water",BigDecimal.valueOf(1.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode5, barproduct5);
    }
 
   
    @Test
    public void testApple() {
    	PriceLookupCode code1 = new PriceLookupCode("4123");
    	PLUCodedProduct product1 = new PLUCodedProduct(code1,"Apple",BigDecimal.valueOf(1.19));
        MainGUI.ListProductScreen.apple.doClick();
        ArrayList<PLUCodedProduct> scanProd = new ArrayList<PLUCodedProduct>();
        scanProd = ControlUnit.sessionData.getPLUProducts();
        int itemAdded = 0;
        for (PLUCodedProduct product : ControlUnit.sessionData.getPLUProducts()) {
            if (product.getPLUCode().equals(product1.getPLUCode())) {
                itemAdded++;
            }
        }
        assertEquals(1,itemAdded);
    }

    @Test
    public void testMango() {
		PriceLookupCode code2 = new PriceLookupCode("4051");
		PLUCodedProduct product2 = new PLUCodedProduct(code2,"Mango",BigDecimal.valueOf(2.47));
		MainGUI.ListProductScreen.mango.doClick();
        ArrayList<PLUCodedProduct> scanProd = new ArrayList<PLUCodedProduct>();
        scanProd = ControlUnit.sessionData.getPLUProducts();
        int itemAdded = 0;
        for (PLUCodedProduct product : ControlUnit.sessionData.getPLUProducts()) {
            if (product.getPLUCode().equals(product2.getPLUCode())) {
                itemAdded++;
            }
        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testGrapes() {
    	PriceLookupCode code3 = new PriceLookupCode("4022");
		PLUCodedProduct product3 = new PLUCodedProduct(code3,"Grapes",BigDecimal.valueOf(6.27));
		MainGUI.ListProductScreen.grapes.doClick();
        ArrayList<PLUCodedProduct> scanProd = new ArrayList<PLUCodedProduct>();
        scanProd = ControlUnit.sessionData.getPLUProducts();
        int itemAdded = 0;
        for (PLUCodedProduct product : ControlUnit.sessionData.getPLUProducts()) {
            if (product.getPLUCode().equals(product3.getPLUCode())) {
                itemAdded++;
            }
        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testBanana() {
    	PriceLookupCode code4 = new PriceLookupCode("4011");
		PLUCodedProduct product4 = new PLUCodedProduct(code4,"Banana",BigDecimal.valueOf(1.04));
		MainGUI.ListProductScreen.bananas.doClick();
        ArrayList<PLUCodedProduct> scanProd = new ArrayList<PLUCodedProduct>();
        scanProd = ControlUnit.sessionData.getPLUProducts();
        int itemAdded = 0;
        for (PLUCodedProduct product : ControlUnit.sessionData.getPLUProducts()) {
            if (product.getPLUCode().equals(product4.getPLUCode())) {
                itemAdded++;
            }
        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testBag() {
    	PriceLookupCode code5 = new PriceLookupCode("8011");
		PLUCodedProduct product5 = new PLUCodedProduct(code5,"Bag",BigDecimal.valueOf(0.10));
		MainGUI.ListProductScreen.bag.doClick();
        ArrayList<PLUCodedProduct> scanProd = new ArrayList<PLUCodedProduct>();
        scanProd = ControlUnit.sessionData.getPLUProducts();
        int itemAdded = 0;
        for (PLUCodedProduct product : ControlUnit.sessionData.getPLUProducts()) {
            if (product.getPLUCode().equals(product5.getPLUCode())) {
                itemAdded++;
            }
        }
        assertEquals(1,itemAdded);
    }
     
   
    @Test
    public void testChocolate() {
		String chocoString = "12345678910";
		Barcode chocoBarcode = new Barcode(chocoString);
		BarcodedItem barproduct1 = new BarcodedItem(chocoBarcode, 200.0);
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(chocoBarcode);
		MainGUI.ListProductScreen.chocolate.doClick();
        HashMap<Barcode, BarcodedProduct> scanProd = new HashMap<Barcode, BarcodedProduct>();
        scanProd = ControlUnit.sessionData.getScannedProducts();
        int itemAdded = 0;
            if (scanProd.containsKey(chocoBarcode)) {
                itemAdded++;

        }
        assertEquals(1,itemAdded);
    } 
    
    @Test
    public void testCupcake() {
    	String cupString = "22345678910";
		Barcode cupBarcode = new Barcode(cupString);
		BarcodedItem barproduct3 = new BarcodedItem(cupBarcode, 500.0);
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(cupBarcode);
		MainGUI.ListProductScreen.cupcake.doClick();
        HashMap<Barcode, BarcodedProduct> scanProd = new HashMap<Barcode, BarcodedProduct>();
        scanProd = ControlUnit.sessionData.getScannedProducts();
        int itemAdded = 0;
            if (scanProd.containsKey(cupBarcode)) {
                itemAdded++;

        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testEggs() {
    	String eggString = "32345678910";
		Barcode eggBarcode = new Barcode(eggString);
		BarcodedItem barproduct4 = new BarcodedItem(eggBarcode, 500.0);
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(eggBarcode);
		MainGUI.ListProductScreen.eggs.doClick();
        HashMap<Barcode, BarcodedProduct> scanProd = new HashMap<Barcode, BarcodedProduct>();
        scanProd = ControlUnit.sessionData.getScannedProducts();
        int itemAdded = 0;
            if (scanProd.containsKey(eggBarcode)) {
                itemAdded++;

        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testMilk() {
    	String milkString = "42345678910";
		Barcode milkBarcode = new Barcode(milkString);
		ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
		BarcodedItem barproduct5 = new BarcodedItem(milkBarcode, 1000.0);
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(milkBarcode);
		MainGUI.ListProductScreen.milk.doClick();
        HashMap<Barcode, BarcodedProduct> scanProd = new HashMap<Barcode, BarcodedProduct>();
        scanProd = ControlUnit.sessionData.getScannedProducts();
        int itemAdded = 0;
            if (scanProd.containsKey(milkBarcode)) {
                itemAdded++;

        }
        assertEquals(1,itemAdded);
    }
    
    @Test
    public void testWater() {
    	String waterString = "5345678910";
		Barcode waterBarcode = new Barcode(waterString);
		ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
		BarcodedItem barproduct6 = new BarcodedItem(waterBarcode, 500.0);
		MainGUI.ListProductScreen.water.doClick();
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(waterBarcode);
        HashMap<Barcode, BarcodedProduct> scanProd = new HashMap<Barcode, BarcodedProduct>();
        scanProd = ControlUnit.sessionData.getScannedProducts();
        int itemAdded = 0;
            if (scanProd.containsKey(waterBarcode)) {
                itemAdded++;

        }
        assertEquals(1,itemAdded);
    }

   
}
