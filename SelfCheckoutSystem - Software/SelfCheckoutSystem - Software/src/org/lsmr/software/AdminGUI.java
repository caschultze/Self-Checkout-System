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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.AbstractDevice;
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
	
	public static void main(String[] args) {
		
		TouchScreen tsl = new TouchScreen();
        frame = tsl.getFrame();
        adminPanel();

	}
	
	public static void adminPanel() {
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
        
        removeBtn = new JButton("Remove Products");
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        removeBtn.setBackground(white);
        
        JLabel adminLabel = new JLabel("    Attendant: "); // has name for attendant
        
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
		gc.insets = new Insets(2,2,2,2);
		gc.weighty = 1.0;
		generalPanel.add(logoutBtn,gc);
		
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
				
				
			}
			
		});
	}
	public static void blockBtnAction() {
        blockBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!paperBtn.isEnabled()) {
					paperBtn.setEnabled(true);
					inkBtn.setEnabled(true);
					emptyCoinBtn.setEnabled(true);
					emptyNoteBtn.setEnabled(true);
					refillCoinBtn.setEnabled(true);
					refillNoteBtn.setEnabled(true);
					blockBtn.setForeground(new Color(230, 57, 70));
					
				}
				else {
					paperBtn.setEnabled(false);
					inkBtn.setEnabled(false);
					emptyCoinBtn.setEnabled(false);
					emptyNoteBtn.setEnabled(false);
					refillCoinBtn.setEnabled(false);
					refillNoteBtn.setEnabled(false);
					blockBtn.setForeground(new Color(0, 0, 0));
				}
			}
        	
        });
	}
	
	public static void logoutBtnAction () {
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void removeBtnAction () {
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void paperBtnAction () {
		paperBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void inkBtnAction () {
		inkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void emptyCoinBtnAction () {
		emptyCoinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void emptyNoteBtnAction () {
		emptyNoteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void refillCoinBtnAction () {
		refillCoinBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
	public static void refillNoteBtnAction () {
		refillNoteBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
	}
	
}