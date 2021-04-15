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
import java.math.BigDecimal;
import java.util.Currency;

import org.lsmr.software.CurrentSessionData;

import java.awt.Insets;

import org.lsmr.software.BanknotePayment;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.Coin;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class GUIPayCash extends MainGUI{
	
	private static JPanel mainPanel;
	
	public static TouchScreen touchscreen;
 	public static JButton hundred;
	public static JButton fifty;
	public static JButton twenty;
	public static JButton ten;
	public static JButton five;
	public static JButton help;
	public static JButton admin;
	public static JButton back;
	public static JButton finish;
	public static JLabel total;
	public static JLabel cash;
	public static JLabel change;
	public static JButton toonie;
	public static JButton loonie;
	public static JButton quarter;
	public static JButton dime;
	public static JButton nickel;
	
	private static Color blue = new Color(237, 246, 249);
    private static Color white = new Color(255, 255, 255);
	
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
        
      
    	
    	toonie = new JButton("$2");
    	loonie = new JButton("$1");
    	quarter = new JButton("¢25");
    	dime = new JButton("¢10");
    	nickel = new JButton("¢5");
    	
        help = new JButton("Help");
        admin = new JButton("Admin");
        back = new JButton("Back");
        cash = new JLabel("CASH");
        total = new JLabel();
        change = new JLabel();
        finish = new JButton("Finish");
        updateTotalInitial();
        updateTotal();
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
        total.setFont(newbuttonFont);
        change.setFont(newbuttonFont);
        finish.setFont(newbuttonFont);
        toonie.setFont(newbuttonFont);
        loonie.setFont(newbuttonFont);
        quarter.setFont(newbuttonFont);
        dime.setFont(newbuttonFont);
        nickel.setFont(newbuttonFont);
        
        hundred.setBackground(white);
        fifty.setBackground(white);
        twenty.setBackground(white);
        ten.setBackground(white);
        five.setBackground(white);
        help.setBackground(white);
        admin.setBackground(white);
        back.setBackground(white);
        finish.setBackground(white); 
        toonie.setBackground(white);
        loonie.setBackground(white);
        quarter.setBackground(white);
        dime.setBackground(white);
        nickel.setBackground(white);
        
        
        //MOVE BUTTONS 
        gc.gridx = 0;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(admin, gc);
        
        
        gc.gridx = 1;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(help, gc);
        
        gc.gridx = 2;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(finish, gc);
        
        gc.gridx = 3;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(back, gc);
        
        
        gc.gridx = 4;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(total, gc);
        
        gc.gridx = 5;
        gc.gridy = 2;
        gc.ipady = 40;
        mainPanel.add(change, gc);


        //REGISTER ACTION EVENTS FOR BUTTONS
        hundred.addActionListener(new hundredButtonHandler());
        fifty.addActionListener(new fiftyButtonHandler());
        twenty.addActionListener(new twentyButtonHandler());
        ten.addActionListener(new tenButtonHandler());
        five.addActionListener(new fiveButtonHandler());
        help.addActionListener(new helpButtonHandler());
        admin.addActionListener(new adminButtonHandler());
        back.addActionListener(new backButtonHandler());
        finish.addActionListener(new finishButtonHandler());
        toonie.addActionListener(new toonieButtonHandler());
        loonie.addActionListener(new loonieButtonHandler());
        quarter.addActionListener(new quarterButtonHandler());
        dime.addActionListener(new dimeButtonHandler());
        nickel.addActionListener(new nickelButtonHandler());
        
        //COLOR
        mainPanel.setBackground(blue);
        
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
       
       
        
        gc.gridx = 0;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(toonie, gc);
        
        gc.gridx = 1;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(loonie, gc);
        
        gc.gridx = 2;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(quarter, gc);
       
        
        gc.gridx = 3;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(dime, gc);
        
        gc.gridx = 4;
        gc.gridy = 1;
        gc.ipady = 40;
        mainPanel.add(nickel, gc);
        
        frame.setVisible(false);
       
    
	}

		//HANDLE EVENTS FOR BUTTON HUNDRED
		static class hundredButtonHandler implements ActionListener{

		//what to do about currency?
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 100;
			ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
			ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
			Banknote hundred = new Banknote(value, Currency.getInstance("CAD"));
			
			try {
				ControlUnit.payBanknote.payWithBanknotes(hundred);
			}catch (Exception e21) {
				updateError();
			}
			
			updateTotal();
		}
		
		
	}
		
		//HANDLE EVENTS FOR BUTTON FIFTY
		static class fiftyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 50;
			ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
			ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
			Banknote fifty = new Banknote(value, Currency.getInstance("CAD"));
			try {
				ControlUnit.payBanknote.payWithBanknotes(fifty);
			}catch (Exception e21) {
				updateError();
			}
			
			updateTotal();

					
		}
				
	}	
		
		//HANDLE EVENTS FOR BUTTON TWENTY
		static class twentyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 20;
			ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
			ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
			Banknote twenty = new Banknote(value, Currency.getInstance("CAD"));
			try {
				ControlUnit.payBanknote.payWithBanknotes(twenty);
			}catch (Exception e21) {
				updateError();
			}
			updateTotal();

							
		}
						
	}

		//HANDLE EVENTS FOR BUTTON TEN
		static class tenButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 10;
			ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
			ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
			Banknote ten = new Banknote(value, Currency.getInstance("CAD"));
			try {
				ControlUnit.payBanknote.payWithBanknotes(ten);
			}catch (Exception e21) {
				updateError();
			}
			updateTotal();

		}
						
	}
		
		
		//HANDLE EVENTS FOR BUTTON FIVE
		static class fiveButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 5;
			ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
			ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
			Banknote five = new Banknote(value, Currency.getInstance("CAD"));
			try {
				ControlUnit.payBanknote.payWithBanknotes(five);
			}catch (Exception e21) {
				updateError();
			}
			updateTotal();

							
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
		
		static class finishButtonHandler implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				switchScreen(14);
								
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
			if(ControlUnit.sessionData.getCurrentAmountOwing().compareTo(ControlUnit.sessionData.getTotal()) == 0) {
			switchScreen(11);
			}		
		}
						
	}
		
		static class toonieButtonHandler implements ActionListener{

			//what to do about currency?
			@Override
			public void actionPerformed(ActionEvent e) {
				BigDecimal value = new BigDecimal("2.00");
				Coin c = new Coin(value, Currency.getInstance("CAD"));
				
				try {
					ControlUnit.payCoin.payWithCoins(c);
//					if(ControlUnit.payCoin.getDispenserFull() == true) {
//						updateDispenser();
//					}
//					
				}catch (Exception e21){
					updateError();
					
				}
	
				updateTotal();
			}
			
		}
		
		
		static class loonieButtonHandler implements ActionListener{

			//what to do about currency?
			@Override
			public void actionPerformed(ActionEvent e) {
				BigDecimal value = new BigDecimal("1.00");
				Coin c = new Coin(value, Currency.getInstance("CAD"));
				
				
				try {
					ControlUnit.payCoin.payWithCoins(c);
//					if(ControlUnit.payCoin.getDispenserFull() == true) {
//						updateDispenser();
//					}
					
				}catch (Exception e21){
					updateError();
					
				}
	
				updateTotal();
			}
			
		}
		
		static class quarterButtonHandler implements ActionListener{

			//what to do about currency?
			@Override
			public void actionPerformed(ActionEvent e) {
				BigDecimal value = new BigDecimal("0.25");
				Coin c = new Coin(value, Currency.getInstance("CAD"));
			
				try {
					ControlUnit.payCoin.payWithCoins(c);
//					if(ControlUnit.payCoin.getDispenserFull() == true) {
//						updateDispenser();
//					}
//					
				}catch (Exception e21){
					updateError();
					
				}
				updateTotal();
			}
			
		}
		
		static class dimeButtonHandler implements ActionListener{

			//what to do about currency?
			@Override
			public void actionPerformed(ActionEvent e) {
				BigDecimal value = new BigDecimal("0.10");
				Coin c = new Coin(value, Currency.getInstance("CAD"));
				
				try {
					ControlUnit.payCoin.payWithCoins(c);
//					if(ControlUnit.payCoin.getDispenserFull() == true) {
//						updateDispenser();
//					}
					
				}catch (Exception e21){
					updateError();
					
				}
	
				updateTotal();
			}
			
		}
		
		static class nickelButtonHandler implements ActionListener{

			//what to do about currency?
			@Override
			public void actionPerformed(ActionEvent e) {
				BigDecimal value = new BigDecimal("0.05");
				Coin c = new Coin(value, Currency.getInstance("CAD"));
			
				try {
					ControlUnit.payCoin.payWithCoins(c);
//					if(ControlUnit.payCoin.cdl.getClass()) {
//						updateDispenser();
//					}
					
				}catch (Exception e21){
					updateError();
					
					
				}
	
				updateTotal();
			}
			
		}
		
	//THIS IS NOT WORKING, TOTAL PRICE HAS NOT BEEN UPDATED
	public static void updateTotalInitial() {
		total.setText("Total = $ " + ControlUnit.sessionData.getTotalPrice());
		change.setText("Change: $0");
		
	}
		
	public static void updateError() {
		total.setText("Error! Please call an attendant");
		total.setBackground(Color.red);
		change.setText("");
		
	}
	
	public static void updateDispenser() {
		total.setText("Error! Please call an attendant");
		total.setBackground(Color.red);
		change.setText("");
		
	}
		
	
	public static void updateTotal() {
		if(ControlUnit.sessionData.getCurrentAmountOwing().compareTo(BigDecimal.ZERO) > 0) {
			total.setText("Total = $ " + ControlUnit.sessionData.getCurrentAmountOwing());
		}
		else {
			BigDecimal temp = ControlUnit.sessionData.getCurrentAmountOwing();
			total.setText("Total = $0");
			change.setText("Change: $ " + temp.abs());
			
			
		}
		
		
	}
		
	
}

