package org.lsmr.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.Attendant;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData;

public class UnexpectedWeightTest {

	public ControlUnit cu;
	CurrentSessionData data;
	SelfCheckoutStation station;
	
	String name = "Luigi";
	String username = "Luigi";
	String password = "ahhhhscarymansion";
	String jobTitle = "Brother";
	String department = "Customer service";
	
	Barcode one = new Barcode("12345");
	Barcode two = new Barcode("000");
	
	double weightOne = 10.00;
	double weightTwo = 5.57;
	
	double wrongWeightOne = 15.23;
	double wrongWeightTwo = 5.59;
	double invalidWeight = -3.00;
	
	double maxDiscrepancy1 = 2.0;
	double maxDiscrepancy2 = 20.0;
	
	@Before
	public void setup() {
		Attendant a = new Attendant(name, username, password, jobTitle, department);

		BarcodedItem itemOne = new BarcodedItem(one, weightOne);
		BarcodedItem itemTwo = new BarcodedItem(two, weightTwo);
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 300;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
		data = cu.sessionData;
		
		data.addScannedItem(itemOne);
		data.addScannedItem(itemTwo);
		
		data.setCurrentAttendant(a);
	} 
	
	@Test
	public void weightWrong() {
		double expectedWeight = data.getScannedItems().get(0).getWeight();
		cu.approveWeight.checkDiscrepancy(expectedWeight, wrongWeightOne, maxDiscrepancy1);
		cu.checkWrongWeight.checkValidity(wrongWeightOne, expectedWeight);
		boolean actual = cu.checkWrongWeight.getUnexpectedWeight();
		assertEquals(true, actual);
	}
	
	@Test
	public void attendantApprove() {
		double expectedWeight = data.getScannedItems().get(1).getWeight();
		cu.approveWeight.checkDiscrepancy(expectedWeight, wrongWeightTwo, maxDiscrepancy1);
		cu.checkWrongWeight.checkValidity(wrongWeightTwo, expectedWeight);
		boolean actual = cu.checkWrongWeight.getUnexpectedWeight();
		assertEquals(false, actual);
	}
	
	@Test
	public void sensitivityRange() {
		double expectedWeight = data.getScannedItems().get(0).getWeight();
		cu.approveWeight.checkDiscrepancy(expectedWeight, wrongWeightOne, maxDiscrepancy2);
		cu.checkWrongWeight.checkValidity(wrongWeightOne, expectedWeight);
		boolean actual = cu.checkWrongWeight.getUnexpectedWeight();
		assertEquals(false, actual);
	}
	
	@Test
	public void weightEqual() {
		double expectedWeight = data.getScannedItems().get(0).getWeight();
		cu.approveWeight.checkDiscrepancy(expectedWeight, expectedWeight, maxDiscrepancy1);
		cu.checkWrongWeight.checkValidity(expectedWeight, expectedWeight);
		boolean actual = cu.checkWrongWeight.getUnexpectedWeight();
		assertEquals(false, actual);
	}
	
	@Test(expected = SimulationException.class)
	public void invalidWeight1() {
		double expectedWeight = data.getScannedItems().get(0).getWeight();
		cu.approveWeight.checkDiscrepancy(expectedWeight, invalidWeight, maxDiscrepancy1);
		cu.checkWrongWeight.checkValidity(invalidWeight, expectedWeight);
	}
	
	@Test(expected = SimulationException.class)
	public void invalidWeight2() {
		cu.approveWeight.checkDiscrepancy(invalidWeight, weightOne, maxDiscrepancy1);
		cu.checkWrongWeight.checkValidity(invalidWeight, weightOne);
	}
}
