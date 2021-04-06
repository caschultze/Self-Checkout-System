package org.lsmr.software;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;

public class CurrentSessionData {

	/**
	 * This is the class that stores attributes that are shared between use cases, such as the items that have been scanned,
	 * the current amount owed, etc. 
	 * 
	 * Add whatever attribute in here that you think should be shared between use cases. 
	 * 
	 * Make sure that these attributes are declared private and static, and include getters and setters.
	 */
	
	private static HashMap<Barcode, BarcodedProduct> scannedProducts = new HashMap<Barcode,BarcodedProduct>(); 
	private static ArrayList <BarcodedItem> scannedItems = new ArrayList<BarcodedItem>();
	private static BigDecimal currentAmountOwing = new BigDecimal("0.00");
	private static BigDecimal totalPrice = new BigDecimal("0.00");
	private static boolean attendantLoggedIn = false;
	private static Attendant currentAttendant = null;
	private static boolean attendantLoggedInMiddleCheck = false;

	/*
	 * Function to add products to a saved HashMap of items scanned -> this HashMap explicitly associates each item scanned with 
	 * it's price and description -> used to help calculate final costs/the receipt
	 * 
	 * Barcode bc: the barcode of the item just scanned
	 */
//	public void addScannedProducts(Barcode bc) {
//		scannedProducts.put(bc, ProductDatabases.BARCODED_PRODUCT_DATABASE.get(bc));
//	}
	
	/*
	 * Function to return the HashMap of scanned products
	 */
	public HashMap<Barcode, BarcodedProduct> getScannedProducts() {
		return scannedProducts;
	}
	
	/*
	 * Function to add items to saved ArrayList of scanned items -> used to determine use cases about the bagging area
	 * 
	 * BarcodedItem item: item that has just been scanned successfully
	 * 
	 * Jake: I've modified this method so that when you add an item, it automatically updates scannedProducts accordingly
	 */
	public void addScannedItem(BarcodedItem item) {
		scannedItems.add(item);
		
		Barcode code = item.getBarcode();
		BarcodedProduct pro = ProductDatabases.BARCODED_PRODUCT_DATABASE.get(code);
		scannedProducts.put(code, pro);
	}
	
	public ArrayList<BarcodedItem> getScannedItems() {
		
		ArrayList<BarcodedItem> newArray = new ArrayList<BarcodedItem>();
		for (BarcodedItem newItem : scannedItems) {
			newArray.add(newItem);
		}
		
		return new ArrayList<BarcodedItem>(newArray);
	}
	
	/**
	 * You need to call the below method, "getTotalPrice()", JUST ONCE before you call this method. Otherwise, this method doesn't
	 * give you the correct value.
	 * @param deduction
	 * @return
	 */
	public BigDecimal getCurrentAmountOwing() {
		return currentAmountOwing;
	}
	
	public void deductCurrentAmountOwing(BigDecimal deduction) {
		
		if (deduction.doubleValue() > 0) {
			currentAmountOwing.subtract(deduction);
		}
	}
	
	/**
	 * This is not designed to be a simple getter method, since the customer can remove any scanned items 
	 * at any point in time. Use this method for the 3rd Iteration, or if it currently applies to you.
	 * @return
	 */
	public BigDecimal getTotalPrice() {
		
		Collection<BarcodedProduct> calcPrice = scannedProducts.values();
		
		for (BarcodedProduct currentProduct : calcPrice) {
			totalPrice = totalPrice.add(currentProduct.getPrice());
		}
		
		BigDecimal GST = new BigDecimal("1.05");
		totalPrice = totalPrice.multiply(GST);
		totalPrice = totalPrice.setScale(2, RoundingMode.HALF_EVEN);
		
		currentAmountOwing = totalPrice;
		return totalPrice;
	}
	
	public void addNumberOfBagsToTotalPrice(int numBags) {
		
		if (numBags >= 0) {
			BigDecimal bag = new BigDecimal("0.05");
			for (int i = 0; i < numBags; i++) {
				totalPrice = totalPrice.add(bag);
				currentAmountOwing = currentAmountOwing.add(bag);
			}
		}
	}
	
	/**
	 * Warning: Only use this method for simplification of test cases. You cannot use this for the 3rd Iteration.
	 * @param temp
	 * @return
	 */
	public BigDecimal setAndGetTotalPrice(BigDecimal temp) {
		totalPrice = temp;
		currentAmountOwing = totalPrice;
		return totalPrice;
	}
	
	
	public void payCoin(BigDecimal amo) {
		currentAmountOwing = currentAmountOwing.subtract(amo);
	}
	
	public void payBanknote(int amo) {
		currentAmountOwing = currentAmountOwing.subtract(new BigDecimal(amo));
	}
	
	public boolean getAttendantLoggedIn() {
		return attendantLoggedIn;
	}
	
	public void setAttendantLoggedIn(boolean loggedIn) {
		attendantLoggedIn = loggedIn;
	}
	
	public boolean getAttendantLoggedInMiddleCheck() {
		return attendantLoggedInMiddleCheck;
	}
	
	public void setAttendantLoggedInMiddleCheck(boolean loggedIn) {
		attendantLoggedInMiddleCheck = loggedIn;
	}
	
	public Attendant getCurrentAttendant() {
		return currentAttendant;
	}
	
	public void setCurrentAttendant(Attendant attendant) {
		currentAttendant = attendant;
	}
}
