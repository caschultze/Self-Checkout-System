package org.lsmr.software;

import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/**
 * Class for the use case:
 * 		Customer enters PLU code for a product
 * 
 */

public class EnterPLUCode {

	private SelfCheckoutStation station;
	private CurrentSessionData data;
	
	public EnterPLUCode(SelfCheckoutStation station, CurrentSessionData data) {
		this.station = station;
		this.data = data;
	}
	
	/**
	 * Get the PLU code's corresponding product
	 * @param plu 
	 * 			The PLU code entered in by the customer
	 * @return the corresponding PLUCodedProduct. If the product does not exist, then a NullPointerException is thrown. 
	 */
	
	public PLUCodedProduct getPLUProduct(PriceLookupCode plu) {
		
		PLUCodedProduct product = ProductDatabases.PLU_PRODUCT_DATABASE.get(plu);
		if(product != null) {
			return product;
		}
		else {
			 throw new NullPointerException("This product does not exist in the database");
		}
		
	}
	
	/**
	 * Enters the PLUCodedProduct into the CurrentSessionData class, which has a list of PLU products entered by the user. 
	 * @param plu
	 * 			The PLU code entered in by the customer
	 */
	
	public void enterPLUProduct(PriceLookupCode plu) {
		
		PLUCodedProduct product = getPLUProduct(plu);
		data.addPLUProduct(product);
		
		
	}
	
}
