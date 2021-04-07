package org.lsmr.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData; 


public class CustomerNoBagScannedItem-AttendantRemovesProductTest {
	
	public ControlUnit cu;
	CurrentSessionData data;
	
	@Before	
	public void setup() {
		
		// Create a database of items
		HashMap<Barcode, BarcodedItem> itemsDatabase = new HashMap<Barcode, BarcodedItem>();
		itemsDatabase.put(new Barcode("1111"), new BarcodedItem(new Barcode("1111"), 25));
		itemsDatabase.put(new Barcode("2222"), new BarcodedItem(new Barcode("2222"), 20));
		itemsDatabase.put(new Barcode("3333"), new BarcodedItem(new Barcode("3333"), 500));
		itemsDatabase.put(new Barcode("4444"), new BarcodedItem(new Barcode("4444"), 5));
		this.itemsDatabase = itemsDatabase;
		
		//create a selfcheckoutsytem
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 300;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);	
		cu.main(null);
		data = cu.sessionData;
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(false);
	}
	
	//test adding a single item without bagging it with attendant approval
	
	@Test
	public void CustomerNoBagScannedOneItemWithAttendantApproval() {
		
		Barcode itemCode = new Barcode("2222");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode));
		assertEquals(data.getCurrentTotalWeight() == 20);
		
	}
	
	//test adding multiple items without bagging with attendant approval
	
	@Test
	public void CustomerNoBagScannedMultipleItemsWithAttendantApproval() {
		
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("1111");
		Barcode itemCode3 = new Barcode("4444");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode3));
		assertEquals(data.getCurrentTotalWeight() == 50);
		
	}
	
	//test adding a single item without bagging it without attendant approval

	@Test
	public void CustomerNoBagScannedOneItemWithoutAttendantApproval() {
		
		Barcode itemCode = new Barcode("2222");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(false);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode));
		assertEquals(data.getCurrentTotalWeight() == 0);
		
	}
	
	//test adding multiple items without bagging them without attendant approval
	
	@Test
	public void CustomerNoBagScannedMultipleItemsWithoutAttendantApproval() {
		
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("1111");
		Barcode itemCode3 = new Barcode("4444");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(false);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode3));
		assertEquals(data.getCurrentTotalWeight() == 0);
		
	}
	
	
	
	//test the attendant removing a single product from purchases with approval
	
	@Test
	public void AttendantRemoveSingleProductFromPurchasesWithApproval() {
		
		Barcode itemCode1 = new Barcode("2222");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode1));
		assertEquals(data.getCurrentTotalWeight() == 0);
		
	}
	
	//test the attendant removing a single product from purchases without approval
	//the case where a customer tries to remove a product from purchases without authorization
	
	@Test
	public void AttendantRemoveSingleProductFromPurchasesWithoutApproval() {
			
		Barcode itemCode1 = new Barcode("2222");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(false);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode1));
		assertEquals(data.getCurrentTotalWeight() == 20);
	}
		
	//test the attendant removing multiple products from purchases with approval
	
	@Test
	public void AttendantRemoveMultipleProductsFromPurchasesWithApproval() {
		
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("1111");
		Barcode itemCode3 = new Barcode("4444");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode3));
		
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode3));
		assertEquals(data.getCurrentTotalWeight() == 0);
		
	}	
	
	//test the attendant removing multiple products from purchases without approval
	
	@Test
	public void AttendantRemoveMultipleProductsFromPurchasesWithoutApproval() {
		
		Barcode itemCode1 = new Barcode("2222");
		Barcode itemCode2 = new Barcode("1111");
		Barcode itemCode3 = new Barcode("4444");
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(true);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.dontBagScannedItem(itemsDatabase.get(itemCode3));
		
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.setAttendantApproval(false);
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode1));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode2));
		cu.CustomerNoBagScannedItem-AttendantRemovesProduct.removedScannedItem(itemsDatabase.get(itemCode3));
		assertEquals(data.getCurrentTotalWeight() == 50);
		
	}	
}
