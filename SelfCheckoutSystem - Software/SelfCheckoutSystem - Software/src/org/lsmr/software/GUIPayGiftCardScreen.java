package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import org.lsmr.selfcheckout.Card;
import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class GUIPayGiftCardScreen extends MainGUI {
	
	public static TouchScreen touchscreen;
	private static JFrame frame;
	private static JPanel windowPanel;
	private static JPanel topPanel;
	private static JPanel bottomPanel;
	private static JPanel centerPanel;
	private static JPanel centerPanel2;
	
	private static JButton helpButton;
	private static JButton adminLoginButton;
	private static JButton backButton;
	private static JButton swipeButton;
	private static JButton invalidCardButton;
	private static JButton tryAgainButton;
	BufferedImage image = new BufferedImage(1, 1, 1);
	private static ControlUnit control;
	private static Color blue = new Color(237, 246, 249);
    private static Color white = new Color(255, 255, 255);
    private static Font font = new Font("Arial", Font.PLAIN, 40);
	
	public static JLabel invalidCardMsg = new JLabel("Card could not be scanned. Please try again (this may be because the card is invalid, or because of a random chance of failure).");
	public static JLabel PayFailedMsg = new JLabel("Payment not processed. Please try again (this may be because of insufficient funds).");
	public static JLabel invalidPIN = new JLabel("Invalid PIN entered, try again.");
	
	public GUIPayGiftCardScreen () {
		Calendar expiry = Calendar.getInstance(TimeZone.getTimeZone("MDT"));
		expiry.add(Calendar.YEAR, 2);
		BigDecimal creditLimit = new BigDecimal("200.00");
		ControlUnit.paymentProcessing.addData("30031234", "Dr. Walker", expiry, "555", creditLimit);
		
		BigDecimal totalPrice = ControlUnit.sessionData.getTotalPrice();

		touchscreen = new TouchScreen();
        frame = touchscreen.getFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.LINE_AXIS));
		
		JLabel membershipText = new JLabel("PAYING BY GIFT CARD");
		membershipText.setFont(new Font(membershipText.getFont().getName(), membershipText.getFont().getStyle(), 50));
		membershipText.setVerticalAlignment(JLabel.TOP);
		membershipText.setHorizontalTextPosition(JLabel.LEFT);
		topPanel.add(membershipText);
		topPanel.add(Box.createHorizontalGlue());
		helpButton = new JButton("Get help");
		topPanel.add(helpButton);
		adminLoginButton = new JButton("Admin login");
		topPanel.add(adminLoginButton);
		
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS));
		
		backButton = new JButton("Back");
		bottomPanel.add(backButton);
		bottomPanel.add(Box.createHorizontalGlue());
		
		centerPanel = new JPanel(new FlowLayout());
		swipeButton = new JButton("Swipe");
		invalidCardButton = new JButton("Use card that doesn't scan");
		
		adminLoginButton.setBackground(white);
		helpButton.setBackground(white);
		backButton.setBackground(white);
		swipeButton.setBackground(white);
		invalidCardButton.setBackground(white);
		centerPanel.setBackground(blue);
		bottomPanel.setBackground(blue);
		topPanel.setBackground(blue);
		
		adminLoginButton.setFont(font);
		helpButton.setFont(font);
		backButton.setFont(font);
		swipeButton.setFont(font);
		invalidCardButton.setFont(font);
		
		backButton.setPreferredSize(new Dimension(500, 100));

		centerPanel.add(swipeButton);
		centerPanel.add(invalidCardButton);
		
		centerPanel.add(invalidCardMsg);
		invalidCardMsg.setVisible(false);
		
		centerPanel.add(PayFailedMsg);
		PayFailedMsg.setVisible(false);
		
		centerPanel.add(invalidPIN);
		invalidPIN.setVisible(false);
		
		windowPanel = new JPanel(new BorderLayout());
		windowPanel.add(topPanel, BorderLayout.PAGE_START);
		windowPanel.add(centerPanel, BorderLayout.CENTER);
		windowPanel.add(bottomPanel, BorderLayout.PAGE_END);		
		
		swipeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invalidCardMsg.setVisible(false);
				PayFailedMsg.setVisible(false);
				try {
					CardData data = ControlUnit.giftcardPayment.swipeGiftCard(card3, image);
					int holdNumber = ControlUnit.paymentProcessing.authorize(data.getNumber(), totalPrice);
					boolean actual = ControlUnit.paymentProcessing.post(data.getNumber(), holdNumber, totalPrice);

					if (actual) {
						System.out.print("Gift card succesfully swiped, payment successfully processed.\n");
						switchScreen(14);
					} else {
						PayFailedMsg.setVisible(true);
					}
					
				} catch (IOException e1) {
					invalidCardMsg.setVisible(true);
				}
			}
		});
		
		invalidCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				invalidCardMsg.setVisible(true);
				PayFailedMsg.setVisible(false);
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (helpButton.getBackground() == Color.YELLOW) {
					helpButton.setBackground(null);
				} else {
					helpButton.setBackground(Color.YELLOW);
				}
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(11);
			}
		});
		
		adminLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchScreen(7);
			}
		});
		
		
		frame.add(windowPanel);    
		touchscreen.setVisible(false);
	}
}

