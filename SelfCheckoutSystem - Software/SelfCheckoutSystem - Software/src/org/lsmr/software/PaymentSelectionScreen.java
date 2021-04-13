package org.lsmr.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.TouchScreen;

public class PaymentSelectionScreen extends MainGUI {
	
	static JPanel jp = new JPanel();
	public static TouchScreen tsl;
	private static JFrame frame;

	public PaymentSelectionScreen() {

		tsl = new TouchScreen();
		frame = tsl.getFrame();
		
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.insets = new Insets(5,5,5,5);
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		JButton debit_key = new JButton("Debit");
		debit_key.setFont(new Font("Arial", Font.PLAIN, 40));
		debit_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipady = 40;
		jp.add(debit_key, gc);
		debit_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(2);
			}
		});
		
		JButton credit_key = new JButton("Credit");
		credit_key.setFont(new Font("Arial", Font.PLAIN, 40));
		credit_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 1;
		gc.gridy = 0;
		gc.ipady = 40;
		jp.add(credit_key,gc);
		credit_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(1);
			}
		});
		
		JButton cash_key = new JButton("Cash");
		cash_key.setFont(new Font("Arial", Font.PLAIN, 40));
		cash_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 2;
		gc.gridy = 0;
		gc.ipady = 40;
		jp.add(cash_key,gc);
		cash_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(12);
			}
		});
		
		JButton admin_key = new JButton("Admin");
		admin_key.setFont(new Font("Arial", Font.PLAIN, 40));
		admin_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 4;
		gc.gridy = 0;
		gc.ipady = 40;
		jp.add(admin_key,gc);
		admin_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(10);
			}
		});
		
		JButton giftcard_key = new JButton("Gift Card");
		giftcard_key.setFont(new Font("Arial", Font.PLAIN, 40));
		giftcard_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 1;
		gc.gridy = 1;
		gc.ipady = 40;
		jp.add(giftcard_key,gc);
		giftcard_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(13);
			}
		});
		
		JButton back_key = new JButton("Back");
		back_key.setFont(new Font("Arial", Font.PLAIN, 40));
		back_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 0;
		gc.gridy = 1;
		gc.ipady = 40;
		jp.add(back_key,gc);
		back_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(6);
			}
		});
		
		JButton help_key = new JButton("Help");
		help_key.setFont(new Font("Arial", Font.PLAIN, 40));
		help_key.setForeground(new Color(204, 136, 153));
		gc.gridx = 4;
		gc.gridy = 1;
		jp.add(help_key,gc);
		help_key.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help_key.setBackground(new Color(255, 255, 0));
			}
		});
		
		jp.setBackground(new Color(204, 136, 153));
		frame.add(jp);
		frame.setVisible(false);
	}	
}
