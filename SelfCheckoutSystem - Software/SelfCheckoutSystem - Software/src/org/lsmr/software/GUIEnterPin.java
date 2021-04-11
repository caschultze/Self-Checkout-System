package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.AbstractDeviceListener;

public class GUIEnterPin extends AbstractDevice<AbstractDeviceListener> {
	private static TouchScreen touchScreen;
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
	
	public static void main(String[] args) {
		touchScreen = new TouchScreen();
		frame = touchScreen.getFrame();
		
		windowPanel = new JPanel(new BorderLayout());
		
		topPanel = new JPanel(new BorderLayout());
		windowPanel.add(topPanel, BorderLayout.PAGE_START);
		
		textPanel = new JPanel(new BorderLayout());
		textPanel.setPreferredSize(new Dimension(500, 200));
		textPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		topPanel.add(textPanel, BorderLayout.PAGE_END);
		
		padPanel = new JPanel(new GridLayout(4, 3, 10, 10));
		padPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		windowPanel.add(padPanel, BorderLayout.CENTER);
		
		extraPanel = new JPanel(new GridLayout(2,1,50,50));
		extraPanel.setBorder(new EmptyBorder(50, 50, 50, 50));;
		windowPanel.add(extraPanel ,BorderLayout.LINE_END);
		
		windowTitle("Enter Pin.");
		addTextfield();
		makePad();
		addExtraButtons();
		frame.add(windowPanel);
		
		frame.setVisible(true);
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
		
		key_ADMIN.setFont(new Font(fontname, style, fontSize));
		key_backScreen.setFont(new Font(fontname, style, fontSize));
	}
}

