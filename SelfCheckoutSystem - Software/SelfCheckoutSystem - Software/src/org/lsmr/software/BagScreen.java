package org.lsmr.software;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.SimulationException;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

/**
 * Bag Menu for GUI 
 * 
 */
public class BagScreen extends MainGUI {
	
		private static JFrame frame;
		public static TouchScreen tsl;
		private static Color blue = new Color(237, 246, 249);
	    private static Color white = new Color(255, 255, 255);
	    private static Font font = new Font("Arial", Font.PLAIN, 40);
		
		public BagScreen () {
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		bagMenu();
		
		
		}
		
		
		//Creates an additional frame to handle bagging of item
		public static void bagMenu() {
			JPanel bagPanel = new JPanel(new GridBagLayout());
			bagPanel.setBackground(blue);
			GridBagConstraints gc = new GridBagConstraints();
	        gc.anchor = GridBagConstraints.CENTER;
	        gc.insets = new Insets(15,15,15,15);
	        
	        gc.gridx = 0;
	        gc.gridy = 1;
			JLabel BagQ = new JLabel("Please bag this item");
			BagQ.setFont(new Font("Arial", Font.BOLD,30));;
			bagPanel.add(BagQ, gc);
	        
			gc.gridx = 0;
		    gc.gridy = 3;
		    JButton yes = new JButton ("I have a bag");
		    yes.setBackground(white);
		    bagPanel.add(yes, gc);
		    
		    gc.gridx = 1;
		    gc.gridy = 3;
		    JButton yesBag = new JButton ("Add a bag");
		    yesBag.setBackground(white);
		    bagPanel.add(yesBag, gc);
		   
			
			gc.gridx = 0;
		    gc.gridy = 5;
		    JButton adminHelp = new JButton ("Admin Help / I don't want to bag this item");
		    adminHelp.setBackground(white);
		    bagPanel.add(adminHelp, gc);
			
			
			frame.add(bagPanel);
		    
		    yesBag.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							PriceLookupCode BagCode = new PriceLookupCode("8011");
							ControlUnit.enterPLU.enterPLUProduct(BagCode);
							switchScreen(6);
						}
					});
			    

		    yes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							switchScreen(6);
					}
				});
		    
			adminHelp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchScreen(7);
				}
			});
			
			yesBag.setFont(font);
			yes.setFont(font);
			adminHelp.setFont(font);
			BagQ.setFont(font);
			frame.setVisible(false);
		}
}