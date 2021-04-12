package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class AdminGUI extends AbstractDevice <TouchScreenListener>{
	private static JFrame frame;
	private static JButton shutdownBtn;
	private static JButton blockBtn;
	private static JButton logoutBtn;
	private static JButton removeBtn;
	private static JButton paperBtn;
	private static JButton inkBtn;
	private static JButton emptyCoinBtn;
	private static JButton emptyNoteBtn;
	private static JButton refillCoinBtn;
	private static JButton refillNoteBtn;
	private static JButton backBtn;
	
	public static void main(String[] args) {
		
		TouchScreen tsl = new TouchScreen();
        frame = tsl.getFrame();
        adminPanel();

	}
	
	public static void adminPanel() {
		
		// Used to test out GUI - delete later
		ControlUnit.main(null);
		ControlUnit.login.verifyLogin("geesjake", "freshwaterGORILLA@9to5");
		//-------------
		
        JPanel adminPanel = new JPanel();
        JPanel generalPanel = new JPanel();
        JPanel hiddenPanel = new JPanel();
        frame.add(adminPanel);
        adminPanel.setLayout(new BorderLayout());
        
        // Colours -----------------------------------------------------------
        Color blue = new Color(237, 246, 249);
        Color white = new Color(255, 255, 255);
        
		// General Panel ================================================================================================================================
        // (Buttons: shutdown, logout, block, remove products) 
        
        // Create Components -------------------------------------------------
       
        
        shutdownBtn = new JButton("Shutdown System");
        shutdownBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        shutdownBtn.setBackground(white);
        
        blockBtn = new JButton("  Block System  ");
        blockBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        blockBtn.setBackground(white);
        
        logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        logoutBtn.setBackground(white);
        
        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        backBtn.setBackground(white);
        
        removeBtn = new JButton("Remove Products");
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        removeBtn.setBackground(white);
          
        String adminName = ControlUnit.sessionData.getCurrentAttendant().getName();
        JLabel adminLabel = new JLabel("    Attendant: " + adminName); // has name for attendant
        
        // General Panel Setting --------------------------------------------

		Dimension size = generalPanel.getPreferredSize();
		size.width = 750;
		generalPanel.setPreferredSize(size); // set it back on panel
		
		generalPanel.setBackground(blue);
		        
		generalPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// Placing components ------------------------------------------------
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(6,20,6,20);
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		generalPanel.add(adminLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		gc.ipady = 40; // makes button taller
		gc.anchor = GridBagConstraints.CENTER;
		generalPanel.add(shutdownBtn,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		generalPanel.add(blockBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = 2;
		gc.ipadx = 0;
		generalPanel.add(removeBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.PAGE_END;
		//gc.insets = new Insets(2,2,2,2);
		gc.weighty = 1.0;
		generalPanel.add(logoutBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = 4;
		gc.weighty = 0;
		generalPanel.add(backBtn,gc);
		
        adminPanel.add(generalPanel,BorderLayout.WEST);
        
		
		// Hidden Panel ================================================================================================================================
        // (Buttons: Refill Paper, Refill Ink, Empty Coin Storage, Empty Banknote storage, Refill Coin Storage, Refill Banknote storage)
        
        // Create Components -------------------------------------------------
        
        paperBtn = new JButton("Add Paper");
        paperBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        paperBtn.setBackground(blue);
        paperBtn.setEnabled(false);
        
        inkBtn = new JButton("Add Ink");
        inkBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        inkBtn.setBackground(blue);
        inkBtn.setEnabled(false);
        
        emptyCoinBtn = new JButton("Empty Coin Storage");
        emptyCoinBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        emptyCoinBtn.setBackground(blue);
        emptyCoinBtn.setEnabled(false);
        
        emptyNoteBtn = new JButton("Empty Note Storage");
        emptyNoteBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        emptyNoteBtn.setBackground(blue);
        emptyNoteBtn.setEnabled(false);
        
        refillCoinBtn = new JButton("Refill Coin Dispenser");
        refillCoinBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        refillCoinBtn.setBackground(blue);
        refillCoinBtn.setEnabled(false);
        
        refillNoteBtn = new JButton("Refill Banknote Dispenser");
        refillNoteBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        refillNoteBtn.setBackground(blue);
        refillNoteBtn.setEnabled(false);
        
        // Hidden Panel Setting ------------------------------------------------
		size = hiddenPanel.getPreferredSize();
		size.width = 1175;
		hiddenPanel.setPreferredSize(size); // set it back on panel
		
		hiddenPanel.setBackground(white);
		        
		hiddenPanel.setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		
		// Placing Components ---------------------------------------------------
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(6,20,6,20);
		gc.weightx = 0.1;
		gc.weighty = 0;
		gc.ipady = 60;
		
		gc.gridx = 0;
		gc.gridy = 0;
		hiddenPanel.add(paperBtn,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		hiddenPanel.add(inkBtn,gc);
		
		gc.gridx = 1;
		gc.gridy = 0;
		hiddenPanel.add(emptyCoinBtn,gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		hiddenPanel.add(emptyNoteBtn,gc);
		
		gc.gridx = 2;
		gc.gridy = 0;
		hiddenPanel.add(refillCoinBtn,gc);
		
		gc.gridx = 2;
		gc.gridy = 1;
		hiddenPanel.add(refillNoteBtn,gc);
		
        adminPanel.add(hiddenPanel,BorderLayout.EAST);
		
		// Button actions ===================================================================================
        shutdownBtnAction();
		blockBtnAction();
		logoutBtnAction();
		backBtnAction();
		removeBtnAction();
		paperBtnAction();
		inkBtnAction();
		emptyCoinBtnAction();
		emptyNoteBtnAction();
		refillCoinBtnAction();
		refillNoteBtnAction();

        frame.setVisible(true);
	}
	
	public static void shutdownBtnAction () {
		shutdownBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.startupShutdown.shutdown();
				System.out.println("System has been shut down");
				// go to black screen?
			}
			
		});
	}
	
	public static void blockBtnAction() {
        blockBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (ControlUnit.blocker.isBlocked() == false) {
					paperBtn.setEnabled(true);
					inkBtn.setEnabled(true);
					emptyCoinBtn.setEnabled(true);
					emptyNoteBtn.setEnabled(true);
					refillCoinBtn.setEnabled(true);
					refillNoteBtn.setEnabled(true);
					blockBtn.setForeground(new Color(230, 57, 70));
					ControlUnit.blocker.blockStation();
					
				}
				else {
					paperBtn.setEnabled(false);
					inkBtn.setEnabled(false);
					emptyCoinBtn.setEnabled(false);
					emptyNoteBtn.setEnabled(false);
					refillCoinBtn.setEnabled(false);
					refillNoteBtn.setEnabled(false);
					blockBtn.setForeground(new Color(0, 0, 0));
					ControlUnit.blocker.unblockStation();
				}
			}
        	
        });
	}
	
	public static void logoutBtnAction () {
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.login.attendantLogOut();
				System.out.println("You have been logged out");
				// Transition: Go to login screen
			}
			
		});
	}
	
	public static void backBtnAction () {
		shutdownBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Transition: Switch to previous screen
			}
			
		});
	}
	
	public static void removeBtnAction () {
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.attendantRemovesProduct.setAttendantApproval(true); // items are now able to be removed
				
				// Transition: ???
				
			}
			
		});
	}
	
	public static void paperBtnAction () {
		paperBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {;
				if (!ControlUnit.addPaper.getPaperAdded() || ControlUnit.paperLow.getNoPaper()) {
					ControlUnit.addPaper.addPaper(1 << 10);
					System.out.println("Paper has been added");
				}
				else {
					System.out.println("Paper has already been added recently");
				}
			}
			
		});
	}
	
	public static void inkBtnAction () {
		inkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {	
				if (!ControlUnit.addInk.getInkAdded() || ControlUnit.inkLow.getNoInk()) {
					ControlUnit.addInk.addInk(50);
					System.out.println("Ink has been added");
				}
				else {
					System.out.println("Ink has already been added recently");
				}
			}
			
		});
	}
	
	public static void emptyCoinBtnAction () {
		emptyCoinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.emptyCoin.emptyCoinStorage();
				System.out.println("Coin Storage has been emptied");
				
			}
			
		});
	}
	
	public static void emptyNoteBtnAction () {
		emptyNoteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.emptyBanknote.emptyBanknoteStorage();
				System.out.println("Banknote Storage has been emptied");
				
			}
			
		});
	}
	
	public static void refillCoinBtnAction () {
		refillCoinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 0.05 -------------------------------------------------------------------------------
				Coin[] coins = new Coin[200];
				for(int i = 0; i < 200; i++) {
					coins[i] = new Coin(new BigDecimal("0.05"),Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.coinRefill.loadCoins(coins);

				} catch (OverloadException e1) {}
				
				// 0.10  ------------------------------------------------------------------------------
				Coin[] coins2 = new Coin[200];
				for(int i = 0; i < 200; i++) {
					coins2[i] = new Coin(new BigDecimal("0.10"),Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.coinRefill.loadCoins(coins2);
				} catch (OverloadException e1) {}
				
				// 0.25  ------------------------------------------------------------------------------
				Coin[] coins3 = new Coin[200];
				for(int i = 0; i < 200; i++) {
					coins3[i] = new Coin(new BigDecimal("0.25"),Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.coinRefill.loadCoins(coins3);
				} catch (OverloadException e1) {}
				
				// 1.00  ------------------------------------------------------------------------------
				Coin[] coins4 = new Coin[200];
				for(int i = 0; i < 200; i++) {
					coins4[i] = new Coin(new BigDecimal("1.00"),Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.coinRefill.loadCoins(coins4);
				} catch (OverloadException e1) {}
				
				// 2.00  ------------------------------------------------------------------------------
				Coin[] coins5 = new Coin[200];
				for(int i = 0; i < 200; i++) {
					coins5[i] = new Coin(new BigDecimal("2.00"),Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.coinRefill.loadCoins(coins5);
				} catch (OverloadException e1) {}
				
			}
			
		});
	}
	
	public static void refillNoteBtnAction () {
		refillNoteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// 5  ------------------------------------------------------------------------------
				Banknote[] notes = new Banknote[100];
				for(int i = 0; i < 100; i++) {
					notes[i] = new Banknote(5,Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.banknoteRefill.loadBanknotes(notes);

				} catch (OverloadException e1) {}
				
				// 10  ------------------------------------------------------------------------------
				Banknote[] notes2 = new Banknote[100];
				for(int i = 0; i < 100; i++) {
					notes2[i] = new Banknote(10,Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.banknoteRefill.loadBanknotes(notes2);

				} catch (OverloadException e1) {}
				
				// 20  ------------------------------------------------------------------------------
				Banknote[] notes3 = new Banknote[100];
				for(int i = 0; i < 100; i++) {
					notes3[i] = new Banknote(20,Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.banknoteRefill.loadBanknotes(notes3);

				} catch (OverloadException e1) {}
				
				// 50  ------------------------------------------------------------------------------
				Banknote[] notes4 = new Banknote[100];
				for(int i = 0; i < 100; i++) {
					notes4[i] = new Banknote(50,Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.banknoteRefill.loadBanknotes(notes4);

				} catch (OverloadException e1) {}
				
				// 100  ------------------------------------------------------------------------------
				Banknote[] notes5 = new Banknote[100];
				for(int i = 0; i < 100; i++) {
					notes5[i] = new Banknote(100,Currency.getInstance(Locale.CANADA));
				}
				try {
					ControlUnit.banknoteRefill.loadBanknotes(notes5);

				} catch (OverloadException e1) {}
			}
			
		});
	}
	
}