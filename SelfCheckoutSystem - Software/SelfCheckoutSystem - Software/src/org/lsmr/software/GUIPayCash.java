package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;

import org.lsmr.software.CurrentSessionData;

import java.awt.Insets;

import org.lsmr.software.BanknotePayment;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class GUIPayCash extends MainGUI{
	
	private static JPanel mainPanel;
	
	public static TouchScreen touchscreen;
	private static JButton hundred;
	private static JButton fifty;
	private static JButton twenty;
	private static JButton ten;
	private static JButton five;
	private static JButton help;
	private static JButton admin;
	private static JButton back;
	private static JLabel total;
	private static JLabel cash;
	
	//public GUI (/*MainGUI main*/) {
	public GUIPayCash() {
		
		touchscreen = new TouchScreen();
        JFrame frame = touchscreen.getFrame();
        frame.setVisible(false);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(5,5,5,5);
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		
		//centerPanel = new JPanel();
		//tsl = new TouchScreen();
        //frame = tsl.getFrame();
        
        hundred = new JButton("$100");
        //hundred.setPreferredSize(new Dimension(100,100));
        
        fifty = new JButton("$50");
        //fifty.setPreferredSize(new Dimension(100,100));
        
        twenty = new JButton("$20");
        //twenty.setPreferredSize(new Dimension(100,100));
        
        ten = new JButton("$10");
        //ten.setPreferredSize(new Dimension(100,100));
        
        five = new JButton("$5");
       // five.setPreferredSize(new Dimension(100,100));
        
        help = new JButton("Help");
        admin = new JButton("Admin");
        back = new JButton("Back");
        cash = new JLabel("CASH");
        total = new JLabel("Total: " + ControlUnit.sessionData.getTotalPrice());
        //total = new JLabel("Total: $$$");
        
        //SET BUTTON FONT
    	Font newbuttonFont = new Font(hundred.getFont().getName(), hundred.getFont().getStyle(), 16);
        hundred.setFont(newbuttonFont);
        fifty.setFont(newbuttonFont);
        twenty.setFont(newbuttonFont);
        ten.setFont(newbuttonFont);
        five.setFont(newbuttonFont);
        help.setFont(newbuttonFont);
        admin.setFont(newbuttonFont);
        cash.setFont(newbuttonFont);
        back.setFont(newbuttonFont);
        //total.setFont(newbuttonFont);
        
        //MOVE BUTTONS 
        gc.gridx = 0;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(admin, gc);
        
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(help, gc);
        
        gc.gridx = 4;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(back, gc);
        
        
        gc.gridx = 4;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(total, gc);


        //REGISTER ACTION EVENTS FOR BUTTONS
        hundred.addActionListener(new hundredButtonHandler());
        fifty.addActionListener(new fiftyButtonHandler());
        twenty.addActionListener(new twentyButtonHandler());
        ten.addActionListener(new tenButtonHandler());
        five.addActionListener(new fiveButtonHandler());
        help.addActionListener(new helpButtonHandler());
        admin.addActionListener(new adminButtonHandler());
        back.addActionListener(new backButtonHandler());
        
        //COLOR
        mainPanel.setBackground(Color.decode("#48cae4"));
        
        frame.add(mainPanel);
   
        
        gc.gridx = 0;
        gc.gridy = 0;
        gc.ipady = 40;
        mainPanel.add(hundred, gc);
        
        gc.gridx = 1;
        gc.gridy = 0;
        gc.ipady = 40;
        mainPanel.add(fifty, gc);
        
        gc.gridx = 2;
        gc.gridy = 0;
        gc.ipady = 40;
        mainPanel.add(twenty, gc);
        
        gc.gridx = 3;
        gc.gridy = 0;
        gc.ipady = 40;
        mainPanel.add(ten, gc);
        
        gc.gridx = 4;
        gc.gridy = 0;
        gc.ipady = 40;
        mainPanel.add(five, gc);
       
       
        
        frame.setVisible(false);
       
    
	}

		//HANDLE EVENTS FOR BUTTON HUNDRED
		static class hundredButtonHandler implements ActionListener{

		//what to do about currency?
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 100;
			Banknote hundred = new Banknote(value, Currency.getInstance("CAD"));
			
			
			
			ControlUnit.payBanknote.payWithBanknotes(hundred);
			//CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + ControlUnit.sessionData.getCurrentAmountOwing());
			
		}
		
	}
		
		//HANDLE EVENTS FOR BUTTON FIFTY
		static class fiftyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 50;
			Banknote fifty = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(fifty);
			//CurrentSessionData.payBanknote(value);
			//total = new JLabel("Total: " + CurrentSessionData.getCurrentAmountOwing());
			total = new JLabel("Total: " + ControlUnit.sessionData.getCurrentAmountOwing());

					
		}
				
	}	
		
		//HANDLE EVENTS FOR BUTTON TWENTY
		static class twentyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 20;
			Banknote twenty = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(twenty);
			//CurrentSessionData.payBanknote(value);
			//total = new JLabel("Total: " + CurrentSessionData.getCurrentAmountOwing());
			total = new JLabel("Total: " + ControlUnit.sessionData.getCurrentAmountOwing());

							
		}
						
	}

		//HANDLE EVENTS FOR BUTTON TEN
		static class tenButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 10;
			Banknote ten = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(ten);
			//CurrentSessionData.payBanknote(value);
			//total = new JLabel("Total: " + CurrentSessionData.getCurrentAmountOwing());
			total = new JLabel("Total: " + ControlUnit.sessionData.getCurrentAmountOwing());

		}
						
	}
		
		
		//HANDLE EVENTS FOR BUTTON FIVE
		static class fiveButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 5;
			Banknote five = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(five);
			//CurrentSessionData.payBanknote(value);
			//total = new JLabel("Total: " + CurrentSessionData.getCurrentAmountOwing());
			total = new JLabel("Total: " + ControlUnit.sessionData.getCurrentAmountOwing());

							
		}
						
	}		
		
		//HANDLE EVENTS FOR BUTTON HELP
		static class helpButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("help");
			help.setBackground(Color.green);
			//call for help
							
		}
						
	}		
		

		//HANDLE EVENTS FOR BUTTON ADMIN
		static class adminButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("admin");
			//go to admin login screen
			switchScreen(7);
							
		}
						
	}		
		

		//HANDLE EVENTS FOR BUTTON BACK
		static class backButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("back");
			//go back to previous screen 
			switchScreen(11);
							
		}
						
	}
		
	
}

