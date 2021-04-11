package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class SceenScan extends AbstractDevice<TouchScreenListener>{
	public static void main(String[] args) {
		TouchScreen tsl = new TouchScreen();
        JFrame frame = tsl.getFrame();
        frame.setVisible(true);
        frame.setName("name");
        frame.setLayout(new BorderLayout());
        
        JPanel jpleft = new JPanel();
        jpleft.setLayout(new GridBagLayout());
        frame.add(jpleft,BorderLayout.WEST);
        jpleft.setBackground(Color.green);
        jpleft.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()));
        jpleft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Shopping Cart"));
   
        JPanel jpright = new JPanel();
        jpright.setLayout(new GridBagLayout());
        frame.add(jpright,BorderLayout.EAST);
        jpright.setBackground(Color.green);
        jpright.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()));
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 0;
        gc.ipady = frame.getWidth()/12;
        gc.ipadx = frame.getWidth()/12;
        gc.insets = new Insets(10,0,0,10);
        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(frame.getWidth()/20,frame.getHeight()/20));
        jpright.add(adminButton,gc);
        
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,10);
        JButton lookButton = new JButton("Lookup Product");
      //  adminButton.setPreferredSize(new Dimension(frame.getWidth()/20,frame.getHeight()/20));
        jpright.add(lookButton,gc);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(10,0,0,10);
        JButton pluButton = new JButton("PLU");
      //  adminButton.setPreferredSize(new Dimension(frame.getWidth()/20,frame.getHeight()/20));
        jpright.add(pluButton,gc);
        
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10,0,0,10);
        JButton helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(frame.getWidth()/6,frame.getHeight()/20));
        jpright.add(helpButton,gc);
        
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.fill = GridBagConstraints.BOTH;
        gc.gridwidth = 2;
        gc.ipady = 40;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets(10,10,10,10);
        JButton finishButton = new JButton("Finish and Pay");
        finishButton.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()/5));
        jpright.add(finishButton,gc);
        
        
        gc.gridx = 0;
        gc.gridy = 1;
        JLabel totalPrice = new JLabel("Total = $$$");
        totalPrice.setOpaque(true);
        jpleft.add(totalPrice,gc);
        
        gc.weighty = 100;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1000;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        JLabel shopCart = new JLabel("Items\n");
        shopCart.setBackground(Color.white);
        shopCart.setOpaque(true);
        shopCart.setVerticalAlignment(SwingConstants.TOP);
        shopCart.setHorizontalAlignment(SwingConstants.LEFT);
        jpleft.add(shopCart,gc);
        
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 0;
        JLabel shopCartPrice = new JLabel("Prices\n");
        shopCartPrice.setBackground(Color.white);
        shopCartPrice.setOpaque(true);
        shopCartPrice.setVerticalAlignment(SwingConstants.TOP);
        shopCartPrice.setHorizontalAlignment(SwingConstants.LEFT);
        jpleft.add(shopCartPrice,gc);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So we can click X to close
        frame.setVisible(true);
	}
}
