import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.lsmr.software.Attendant;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.CurrentSessionData;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SimulationException;

public class EmptyBanknoteStorageTest {
	
	public ControlUnit cu;
	public Attendant a;
	String name = "Billy";
	String username = "Billy101";
	String password = "Billyisawesome!";
	String jobTitle = "Cashier";
	String department = "Customer Service";
	Banknote banknote;
	
	@Before
	public void setup() throws SimulationException, OverloadException {
		a = new Attendant(name, username, password, jobTitle, department);
		
		Currency currency = Currency.getInstance(Locale.CANADA);
		int[] banknoteDenominations = new int[] {5, 10, 20, 50, 100};
		BigDecimal[] coinDenominations = new BigDecimal[] {new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.25"), new BigDecimal("1.00"), new BigDecimal("2.00")};
		int scaleMaximumWeight = 100;
		int scaleSensitivity = 1;
		
		banknote = new Banknote(10, currency);
		
		cu = new ControlUnit(currency, banknoteDenominations, coinDenominations, scaleMaximumWeight, scaleSensitivity);
		cu.main(null);	
		cu.sessionData.setCurrentAttendant(a);
	}
	
	//checks that the banknotes unloaded match those loaded
	@Test
	public void testUnloadedBanknotes() throws SimulationException, OverloadException {
		cu.checkoutStation.banknoteStorage.load(banknote);
		cu.emptyBanknote.addToUnloaded(cu.emptyBanknote.emptyBanknoteStorage());
		for(Banknote b : cu.emptyBanknote.getUnloadedBanknotes()) {
			if(b!=null) {
				assertEquals(banknote,b);
			}
		}
	}
	
	
	//checks that the storage is empty
	@Test
	public void testStorageIsEmpty() throws SimulationException, OverloadException {
		cu.checkoutStation.banknoteStorage.load(banknote);
		cu.emptyBanknote.emptyBanknoteStorage();
		assertEquals(0,cu.checkoutStation.banknoteStorage.getBanknoteCount());
	}
	
	//null attendant
	//Simulation exception expected
	@Test
	public void nullAttendant() {
		a = null;
		cu.sessionData.setCurrentAttendant(a);
		try{
			cu.emptyBanknote.emptyBanknoteStorage();
		}
		catch(SimulationException e) {
			return;
		}
		fail("SimulationException expected");
	}
	


}
