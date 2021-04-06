package org.lsmr.test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.software.Attendant;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData;

public class AttendantApprovesWeightTest  {

	public ControlUnit cu;
	CurrentSessionData data;
	String name = "Mario";
	String username = "Mario";
	String password = "itsameeeeeeeee";
	String jobTitle = "Plumber";
	String department = "Customer service";
	
	double actualWeight1 = 19.4;
	double expectedWeight1 = 21.0;
	double actualWeight2 = 4.0;
	double expectedWeight2 = 15.0;
	double maxDiscrepancy = 10.0;
	double invalidWeight = -0.90;
	
	@Before
	public void setup() {
		Attendant a = new Attendant(name, username, password, jobTitle, department);

		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 300;
		int scaleSensitivity = 1;
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);
		data = cu.sessionData;
		data.setCurrentAttendant(a);
	} 
	
	@Test
	public void weightApproved() {
		cu.approveWeight.checkDiscrepancy(expectedWeight1, actualWeight1, maxDiscrepancy);
		boolean approved = cu.approveWeight.isApproved();
		assertEquals(true, approved);
	}
	
	@Test
	public void weightNotApproved() {
		cu.approveWeight.checkDiscrepancy(expectedWeight2, actualWeight2, maxDiscrepancy);
		boolean approved = cu.approveWeight.isApproved();
		assertEquals(false, approved);
	}
	
	@Test(expected = SimulationException.class)
	public void noAttendant() {
		data.setCurrentAttendant(null);
		cu.approveWeight.checkDiscrepancy(expectedWeight2, actualWeight2, maxDiscrepancy);
	}
	
	@Test(expected = SimulationException.class)
	public void invalidWeight1() {
		cu.approveWeight.checkDiscrepancy(expectedWeight2, invalidWeight, maxDiscrepancy);
	}
	
	@Test(expected = SimulationException.class)
	public void invalidWeight2() {
		cu.approveWeight.checkDiscrepancy(invalidWeight, actualWeight2, maxDiscrepancy);
	}
}
