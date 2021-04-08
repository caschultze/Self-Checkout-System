package org.lsmr.software;

import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.ElectronicScale;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;
import org.lsmr.selfcheckout.devices.listeners.ElectronicScaleListener;
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
	private ElectronicScaleListener esl; 
	private boolean isEnabled = false;
	private boolean isDisabled= false;
	
	public EnterPLUCode(SelfCheckoutStation station, CurrentSessionData data) {
		this.station = station;
		this.data = data;
		registerListeners();
	}
	
	public void registerListeners() {
		esl = new ElectronicScaleListener() {

			@Override
			public void enabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isEnabled = true;
				
			}

			@Override
			public void disabled(AbstractDevice<? extends AbstractDeviceListener> device) {
				isDisabled = true;
				
			}

			@Override
			public void weightChanged(ElectronicScale scale, double weightInGrams) {

				double weightInKilograms = weightInGrams * 0.001;
				if (weightInKilograms != 0.0) {
					data.addPLUWeight(weightInKilograms);
				}
				
			}

			@Override
			public void overload(ElectronicScale scale) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void outOfOverload(ElectronicScale scale) {
				// TODO Auto-generated method stub
				
			} 
			
		};
		station.scale.register(esl);
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
	
	public boolean getEnabled() {
		return isEnabled;
	}
	
	public boolean getDisabled() {
		return isDisabled;
	}
	
}
