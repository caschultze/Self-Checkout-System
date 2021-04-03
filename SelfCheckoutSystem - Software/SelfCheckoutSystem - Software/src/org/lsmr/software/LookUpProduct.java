package org.lsmr.software;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
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

	private SelfCheckoutStation station;
	
	public LookUpProduct(SelfCheckoutStation station) {
		this.station = station;
		
	}
	
	/**
	 * Checks to see if a barcoded product is in the product database
	 * 
	 * @param product
	 * @return true if the product is in the database and false otherwise. 
	 * 
	 */
	
	public boolean checkBarcodedProductInDatabase(BarcodedProduct product) {
		for (Barcode barcode : ProductDatabases.BARCODED_PRODUCT_DATABASE.keySet()) {
			if (product.getBarcode().equals(barcode)) {
				return true; 
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if a PLU coded product is in the product database
	 * 
	 * @param product
	 * @return true if the product is in the database and false otherwise. 
	 * 
	 */
	
	public boolean checkPLUProductInDatabase(PLUCodedProduct product) {
		for (PriceLookupCode code : ProductDatabases.PLU_PRODUCT_DATABASE.keySet()) {
			if (product.getPLUCode().equals(code)) {
				return true; 
			}
		}
		return false;
	}
	
}
