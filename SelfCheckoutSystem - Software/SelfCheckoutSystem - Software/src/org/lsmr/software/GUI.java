package org.lsmr.software;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Currency;

import org.lsmr.software.CurrentSessionData;
import org.lsmr.software.BanknotePayment;
import org.lsmr.selfcheckout.Banknote;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class GUI extends AbstractDevice <TouchScreenListener>{
	
	private static JFrame frame;
	private static JPanel mainPanel;
	private static TouchScreen tsl;
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
	
	public static void main(String[] args) {
		
		mainPanel = new JPanel();
		//centerPanel = new JPanel();
		tsl = new TouchScreen();
        frame = tsl.getFrame();
        
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
        total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
        
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
        
        //MOVE BUTTONS 
        mainPanel.setLayout(null);
        hundred.setBounds(550, 250, 100, 100);
        fifty.setBounds(700, 250, 100, 100);
        twenty.setBounds(850, 250, 100, 100);
        ten.setBounds(1000, 250, 100, 100);
        five.setBounds(1150, 250, 100, 100);
        help.setBounds(1500, 75, 100, 100);
        admin.setBounds(1625, 75, 100, 100);
        cash.setBounds(50,50,100,100);
        back.setBounds(50,850,100,100);
        total.setBounds(1500, 850, 100, 100);
        
        
        
        //BUTTON ICONS
       // hundred.setIcon(new ImageIcon(Class.class.getResource("/src/100.jpg")));


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
        mainPanel.add(hundred);
        mainPanel.add(fifty);
        mainPanel.add(twenty);
        mainPanel.add(ten);
        mainPanel.add(five);
        mainPanel.add(help);
        mainPanel.add(admin);
        mainPanel.add(cash);
        mainPanel.add(back);
        mainPanel.add(total);
        
        //System.out.println(ControlUnit.payBanknote.getBalance());
       
        
        frame.setVisible(true);
       
    
	}

		//HANDLE EVENTS FOR BUTTON HUNDRED
		static class hundredButtonHandler implements ActionListener{

		//what to do about currency?
		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 100;
			Banknote hundred = new Banknote(value, Currency.getInstance("CAD"));
			
			
			
			ControlUnit.payBanknote.payWithBanknotes(hundred);
			CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
			
		}
		
	}
		
		//HANDLE EVENTS FOR BUTTON FIFTY
		static class fiftyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 50;
			Banknote fifty = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(fifty);
			CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
					
		}
				
	}	
		
		//HANDLE EVENTS FOR BUTTON TWENTY
		static class twentyButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 20;
			Banknote twenty = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(twenty);
			CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
							
		}
						
	}

		//HANDLE EVENTS FOR BUTTON TEN
		static class tenButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 10;
			Banknote ten = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(ten);
			CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
		}
						
	}
		
		
		//HANDLE EVENTS FOR BUTTON FIVE
		static class fiveButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int value = 5;
			Banknote five = new Banknote(value, Currency.getInstance("CAD"));
			ControlUnit.payBanknote.payWithBanknotes(five);
			CurrentSessionData.payBanknote(value);
			total = new JLabel("Total: " + CurrentSessionData.getTotalPrice());
							
		}
						
	}		
		
		//HANDLE EVENTS FOR BUTTON HELP
		static class helpButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("help");
			//call for help
							
		}
						
	}		
		

		//HANDLE EVENTS FOR BUTTON ADMIN
		static class adminButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("admin");
			//admin login 
							
		}
						
	}		
		

		//HANDLE EVENTS FOR BUTTON BACK
		static class backButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("back");
			//go back to previous screen 
							
		}
						
	}
		
	
}

