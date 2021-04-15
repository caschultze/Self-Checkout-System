package org.lsmr.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;
import org.lsmr.software.ControlUnit;
import org.lsmr.software.MainGUI;

public class AdminGUITest {
	
	@Before
	public void setup() throws Exception{
		MainGUI.main(null);
	}
	
	@Test
	public void testBlockBtn() {
		
		MainGUI.AdminGUI.blockBtn.doClick(); // block
		
		assertTrue(ControlUnit.blocker.isBlocked());
		
		MainGUI.AdminGUI.blockBtn.doClick(); // unblock
		assertFalse(ControlUnit.blocker.isBlocked());
	}
	
	
	@Test
	public void testPaperBtn() {
		assertFalse(ControlUnit.addPaper.getPaperAdded());
		
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.paperBtn.doClick();
		
		assertTrue(ControlUnit.addPaper.getPaperAdded());
		assertFalse(ControlUnit.paperLow.getNoPaper());
	}
	
	@Test
	public void testInkBtn() {
		assertFalse(ControlUnit.addInk.getInkAdded());
		
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.inkBtn.doClick();
		
		assertTrue(ControlUnit.addInk.getInkAdded());
		assertFalse(ControlUnit.inkLow.getNoInk());
	}
	
	@Test
	public void testEmptyCoinBtn() {
		
		assertFalse(ControlUnit.emptyCoin.getCheckUnloaded());
		
		ControlUnit.login.verifyLogin("geesjake","freshwaterGORILLA@9to5");
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.emptyCoinBtn.doClick();
		
		assertTrue(ControlUnit.emptyCoin.getCheckUnloaded());
	}
	
	@Test
	public void testEmptyBanknoteBtn() {
		
		assertFalse(ControlUnit.emptyBanknote.getCheckUnloaded());
		
		ControlUnit.login.verifyLogin("geesjake","freshwaterGORILLA@9to5");
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.emptyNoteBtn.doClick();
		
		assertTrue(ControlUnit.emptyBanknote.getCheckUnloaded());
		
	}
	
	@Test
	public void testRefillCoinBtn() {
		
		ControlUnit.login.verifyLogin("geesjake","freshwaterGORILLA@9to5");	
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.refillCoinBtn.doClick();
		
		assertFalse(ControlUnit.payCoin.getEmptyDispenserCheck());
		
	}
	
	@Test
	public void testRefillNoteBtn() {
		
		ControlUnit.login.verifyLogin("geesjake","freshwaterGORILLA@9to5");
		MainGUI.AdminGUI.blockBtn.doClick();
		MainGUI.AdminGUI.refillNoteBtn.doClick();
		
		assertFalse(ControlUnit.payBanknote.getEmptyDispenserCheck());
		
	}
	
	@Test
	public void testShutdownBtn() {

		MainGUI.AdminGUI.shutdownBtn.doClick();
		assertFalse(ControlUnit.startupShutdown.PowerOn);
		
	}
	
	@Test
	public void testLogoutBtn() {
		
		ControlUnit.login.verifyLogin("geesjake","freshwaterGORILLA@9to5");
		MainGUI.AdminGUI.logoutBtn.doClick();
		assertFalse(ControlUnit.sessionData.getAttendantLoggedIn());
		
	}
	
	@Test
	public void testRemovePLUProduct() {
		
		PriceLookupCode code = new PriceLookupCode("4051");
		PLUCodedProduct product = new PLUCodedProduct(code,"Mango",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code, product);
		
		ControlUnit.enterPLU.enterPLUProduct(code);
		assertEquals(1,ControlUnit.sessionData.getPLUProducts().size()); // item entered
		
		MainGUI.AdminGUI.removeBtn.doClick();
		MainGUI.AdminGUI.codeTextField.setText("4051");
		MainGUI.AdminGUI.enterBtn.doClick();
		
		assertEquals(0,ControlUnit.sessionData.getPLUProducts().size()); // item removed
		
	}
	
	@Test
	public void testRemoveScannedItem() {
		
		Barcode barcode1 = new Barcode("12345678910");
		BarcodedProduct barproduct1 = new BarcodedProduct(barcode1,"Chocolate",BigDecimal.valueOf(1.70));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode1, barproduct1);
		BarcodedItem item = new BarcodedItem(barcode1,50);
		
		ArrayList<BarcodedItem> items = new ArrayList<BarcodedItem>();
		items.add(item);
		ControlUnit.itemScan.scanItems(items);
		try {
			ControlUnit.itemBag.bagItems(item);
		} catch (OverloadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1,ControlUnit.sessionData.getScannedItems().size()); // item entered
		
		MainGUI.AdminGUI.removeBtn.doClick();
		MainGUI.AdminGUI.codeTextField.setText("12345678910");
		MainGUI.AdminGUI.enterBtn.doClick();
		
		assertEquals(0,ControlUnit.sessionData.getScannedItems().size()); // item removed
		
	}
	
	@Test
	public void testRemoveInvalidItem() {
		
		PriceLookupCode code = new PriceLookupCode("4051");
		PLUCodedProduct product = new PLUCodedProduct(code,"Mango",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code, product);
		
		ControlUnit.enterPLU.enterPLUProduct(code);
		assertEquals(1,ControlUnit.sessionData.getPLUProducts().size()); // item entered
		
		MainGUI.AdminGUI.removeBtn.doClick();
		MainGUI.AdminGUI.codeTextField.setText("3452");		// item is not in purchases
		MainGUI.AdminGUI.enterBtn.doClick();
		
		assertEquals(1,ControlUnit.sessionData.getPLUProducts().size()); // no items have been removed
	}
	
}
