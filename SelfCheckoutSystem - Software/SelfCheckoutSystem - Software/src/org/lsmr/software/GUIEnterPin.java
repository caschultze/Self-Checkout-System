package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.Card.CardData;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;

//import com.sun.java.swing.plaf.windows.TMSchema.Control;

public class GUIEnterPin extends MainGUI {
	public static TouchScreen touchscreen;
	private static JFrame frame;
	private static JPanel windowPanel;
	private static JPanel topPanel;
	private static JPanel padPanel;
	private static JPanel extraPanel;
	private static JPanel textPanel;
	private static String fontname;
	private static JTextField numberDisplay;
	private static String membershipID = "";
	private static int style;
	private static int fontSize;
	private static Color blue = new Color(237, 246, 249);
    private static Color white = new Color(255, 255, 255);
	
	
	public GUIEnterPin () {
//		this.cu = cu;
		makeFrame();
	}
	
	public void makeFrame() {
		touchscreen = new TouchScreen();
		frame = touchscreen.getFrame();
		
		windowPanel = new JPanel(new BorderLayout());
		
		topPanel = new JPanel(new BorderLayout());
		windowPanel.add(topPanel, BorderLayout.PAGE_START);
		windowPanel.setBackground(blue);
		
		textPanel = new JPanel(new BorderLayout());
		textPanel.setPreferredSize(new Dimension(500, 200));
		textPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		topPanel.add(textPanel, BorderLayout.PAGE_END);
		topPanel.setBackground(blue);
		textPanel.setBackground(blue);
		
		padPanel = new JPanel(new GridLayout(4, 3, 10, 10));
		padPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		windowPanel.add(padPanel, BorderLayout.CENTER);
		padPanel.setBackground(blue);
		
		extraPanel = new JPanel(new GridLayout(2,1,50,50));
		extraPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		windowPanel.add(extraPanel ,BorderLayout.LINE_END);
		extraPanel.setBackground(blue);
		
		windowTitle("Enter Pin.");
		addTextfield();
		makePad();
		addExtraButtons();
		frame.add(windowPanel);
		
		frame.setVisible(false);
	}
	
	public static void windowTitle(String string) {
		JLabel membershipText = new JLabel(string);
		membershipText.setFont(new Font(membershipText.getFont().getName(), membershipText.getFont().getStyle(), 50));
		membershipText.setVerticalAlignment(JLabel.TOP);
		membershipText.setHorizontalTextPosition(JLabel.LEFT);
		topPanel.add(membershipText, BorderLayout.LINE_START);
	}
	
	public static void addTextfield() {
		numberDisplay = new JTextField(16);
		//numberDisplay.setFont(new Font(fontname, style, fontSize));
		numberDisplay.setMinimumSize(new Dimension(1000, 250));
		numberDisplay.setPreferredSize(new Dimension(1000, 500));
		textPanel.add(numberDisplay);
	}
	
	public static void makePad() {
		JButton key_1 = new JButton("1");
		key_1.setPreferredSize(new Dimension(10, 10));
		padPanel.add(key_1);
		
		key_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_2 = new JButton("2");
		key_2.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_2);
		
		key_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_3 = new JButton("3");
		key_3.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_3);
		
		key_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_4 = new JButton("4");
		key_4.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_4);
		
		key_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_5 = new JButton("5");
		key_5.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_5);
		
		key_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_6 = new JButton("6");
		key_6.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_6);
		
		key_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_7 = new JButton("7");
		key_7.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_7);
		
		key_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_8 = new JButton("8");
		key_8.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_8);
		
		key_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_9 = new JButton("9");
		key_9.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_9);
		
		key_9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_0 = new JButton("0");
		key_0.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_0);
		
		key_0.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID += num;
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_back = new JButton("Backspace");
		key_back.setPreferredSize(new Dimension(50, 50));
		padPanel.add(key_back);
		
		key_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				membershipID = membershipID.substring(0, membershipID.length()-1);
				numberDisplay.setText(membershipID);
			}
		});
		
		JButton key_Enter = new JButton("Enter");
		key_Enter.setPreferredSize(new Dimension(100, 50));
		padPanel.add(key_Enter);
		
		key_Enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (PayingBy == 1) { 
					try {
						CardData data = ControlUnit.creditPayment.creditInsert(card1, membershipID);
						BigDecimal totalPrice = ControlUnit.sessionData.getTotalPrice();
						int holdNumber = ControlUnit.paymentProcessing.authorize(data.getNumber(), totalPrice);
						boolean actual = ControlUnit.paymentProcessing.post(data.getNumber(), holdNumber, totalPrice);
						ControlUnit.creditPayment.creditRemove();

						if (actual) {
							System.out.print("Credit card succesfully inserted and pin is correct, payment succesfully processed.\n");
							switchScreen(14);
						} else {
							GUICreditScreen.invalidPIN.setVisible(true);
							switchScreen(1);
						}
					} catch (IOException e1) {
						ControlUnit.creditPayment.creditRemove();
						switchScreen(1);
					}
				}
				if (PayingBy == 2) { 
					try {
						CardData data = ControlUnit.debitPayment.debitInsert(card2, membershipID);
						BigDecimal totalPrice = ControlUnit.sessionData.getTotalPrice();
						int holdNumber = ControlUnit.paymentProcessing.authorize(data.getNumber(), totalPrice);
						boolean actual = ControlUnit.paymentProcessing.post(data.getNumber(), holdNumber, totalPrice);
						ControlUnit.debitPayment.debitRemove();

						if (actual) {
							System.out.print("Debit card succesfully inserted and pin is correct, payment succesfully processed.\n");
							switchScreen(14);
						} else {
							switchScreen(2);
						}
					} catch (IOException e1) {
						ControlUnit.debitPayment.debitRemove();
						switchScreen(2);
					}
				}
			}
		});
		
		
		
		fontname = key_1.getFont().getName();
		style = key_1.getFont().getStyle();
		fontSize = 30;
		
		key_1.setFont(new Font(fontname, style, fontSize));
		key_2.setFont(new Font(fontname, style, fontSize));
		key_3.setFont(new Font(fontname, style, fontSize));
		key_4.setFont(new Font(fontname, style, fontSize));
		key_5.setFont(new Font(fontname, style, fontSize));
		key_6.setFont(new Font(fontname, style, fontSize));
		key_7.setFont(new Font(fontname, style, fontSize));
		key_8.setFont(new Font(fontname, style, fontSize));
		key_9.setFont(new Font(fontname, style, fontSize));
		key_0.setFont(new Font(fontname, style, fontSize));
		key_Enter.setFont(new Font(fontname, style, fontSize));
		key_back.setFont(new Font(fontname, style, fontSize));
		
		key_0.setBackground(white);
		key_1.setBackground(white);
		key_2.setBackground(white);
		key_3.setBackground(white);
		key_4.setBackground(white);
		key_5.setBackground(white);
		key_6.setBackground(white);
		key_7.setBackground(white);
		key_8.setBackground(white);
		key_9.setBackground(white);
		key_Enter.setBackground(white);
		key_back.setBackground(white);
		
}
	
	public static String returnMembership () {
		return membershipID;
	}
	
	public static void addExtraButtons () {
		JButton key_ADMIN = new JButton("ADMIN");
		key_ADMIN.setPreferredSize(new Dimension(500, 50));
		extraPanel.add(key_ADMIN);
		
		JButton key_backScreen = new JButton("BACK");
		key_backScreen.setPreferredSize(new Dimension(500, 50));
		extraPanel.add(key_backScreen);
		
		key_backScreen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (PayingBy == 1) { 
					switchScreen(1);
				}
				if (PayingBy == 2) { 
					switchScreen(2);
				}
			}
		});
		
		key_ADMIN.setFont(new Font(fontname, style, fontSize));
		key_backScreen.setFont(new Font(fontname, style, fontSize));
		touchscreen.setVisible(false);
		
		key_ADMIN.setBackground(white);
		key_backScreen.setBackground(white);
	}
	
	public void setPin () {
		membershipID = "";
		numberDisplay.setText(membershipID);
	}
}

