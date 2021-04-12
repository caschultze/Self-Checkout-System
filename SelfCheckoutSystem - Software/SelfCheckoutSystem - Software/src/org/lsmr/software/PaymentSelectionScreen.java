package org.lsmr.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class PaymentSelectionScreen extends MainGUI {
	
	static JPanel jp = new JPanel();
	public static TouchScreen tsl;
	private static JFrame frame;
	
	/*
	public PaymentSelectionScreen(MainGui main)
	{
		
	}
	*/
	
	public PaymentSelectionScreen() {
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		enterPanel(frame);

	}

	public static void enterPanel(JFrame frame) {

		jp.setLayout(null);
		
		JButton debit_key = new JButton("Debit");
		debit_key.setFont(new Font("Arial", Font.PLAIN, 40));
		debit_key.setBackground(new Color(152, 251, 152));
		debit_key.setForeground(new Color(204, 136, 153));
		debit_key.setBounds(150, 150, 200, 200);
		jp.add(debit_key);
		debit_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to debit payment screen
			}
		});
		
		JButton credit_key = new JButton("Credit");
		credit_key.setFont(new Font("Arial", Font.PLAIN, 40));
		credit_key.setBackground(new Color(152, 251, 152));
		credit_key.setForeground(new Color(204, 136, 153));
		credit_key.setBounds(400, 150, 200, 200);
		jp.add(credit_key);
		credit_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to credit payment screen
			}
		});
		
		JButton cash_key = new JButton("Cash");
		cash_key.setFont(new Font("Arial", Font.PLAIN, 40));
		cash_key.setBackground(new Color(152, 251, 152));
		cash_key.setForeground(new Color(204, 136, 153));
		cash_key.setBounds(650, 150, 200, 200);
		jp.add(cash_key);
		cash_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to cash payment screen
			}
		});
		
		JButton admin_key = new JButton("Admin");
		admin_key.setFont(new Font("Arial", Font.PLAIN, 40));
		admin_key.setBackground(new Color(152, 251, 152));
		admin_key.setForeground(new Color(204, 136, 153));
		admin_key.setBounds(1000, 150, 200, 200);
		jp.add(admin_key);
		admin_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to admin screen
			}
		});
		
		JButton giftcard_key = new JButton("Gift Card");
		giftcard_key.setFont(new Font("Arial", Font.PLAIN, 40));
		giftcard_key.setBackground(new Color(152, 251, 152));
		giftcard_key.setForeground(new Color(204, 136, 153));
		giftcard_key.setBounds(200, 400, 600, 200);
		jp.add(giftcard_key);
		giftcard_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to gift card payment screen
			}
		});
		
		JButton help_key = new JButton("Help");
		help_key.setFont(new Font("Arial", Font.PLAIN, 40));
		help_key.setBackground(new Color(152, 251, 152));
		help_key.setForeground(new Color(204, 136, 153));
		help_key.setBounds(1000, 400, 200, 200);
		jp.add(help_key);
		help_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// switch to help screen
				// we just set visible to false here to easily exit for now
				frame.setVisible(false);
			}
		});
		
		jp.setBackground(new Color(204, 136, 153));
		frame.add(jp);
		frame.setVisible(false);
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void setVisible(boolean visible) {
		frame.setVisible(visible);
	}
	
}