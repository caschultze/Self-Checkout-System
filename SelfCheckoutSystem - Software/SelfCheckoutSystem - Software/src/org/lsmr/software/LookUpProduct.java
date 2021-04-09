package org.lsmr.software;

import java.util.ArrayList;

import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/**
 * Class for the use cases:
 * 		Customer looks up product
 * 		Attendant looks up product
 * 
 */

public class LookUpProduct {

	private ArrayList<String> prices = new ArrayList<String>();
	private ArrayList<String> descriptions = new ArrayList<String>();
	private ArrayList<String> codes = new ArrayList<String>();
	
	public LookUpProduct() {
		
	}
	
	/**
	 * Puts all the info of each product in the database into their respective arrays
	 * Arrays will be used to display, where the information matches depending on the index.
	 * 
	 */
	
	public void getAllProducts() {
		for (BarcodedProduct product : ProductDatabases.BARCODED_PRODUCT_DATABASE.values()) {
			prices.add(product.getPrice().toString());
			descriptions.add(product.getDescription());
			codes.add(product.getBarcode().toString());
		}
		for (PLUCodedProduct product : ProductDatabases.PLU_PRODUCT_DATABASE.values()) {
			prices.add(product.getPrice().toString());
			descriptions.add(product.getDescription());
			codes.add(product.getPLUCode().toString());
		}
	}
	
	public ArrayList<String> getProductPrices() {
		return prices;
	}
	
	public ArrayList<String> getProductDescriptions() {
		return descriptions;
	}
	
	public ArrayList<String> getProductCodes() {
		return codes;
	}
	
	
}
