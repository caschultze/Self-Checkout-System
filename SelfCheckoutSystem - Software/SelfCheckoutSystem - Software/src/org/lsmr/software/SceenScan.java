package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

public class SceenScan extends MainGUI {
	
	public static TouchScreen touchscreen;
	CurrentSessionData session = new CurrentSessionData();
    static JLabel shopCartPrice = new JLabel("<html>Prices<br/></html>");
    static JLabel shopCart = new JLabel("<html>Items<br/></html>");
    static JLabel totalPrice = new JLabel("Total = $$$");
    Color blue = new Color(237, 246, 249);
	 Color white = new Color(255, 255, 255);
    
    
    public SceenScan(){
    	touchscreen = new TouchScreen();
        JFrame frame = touchscreen.getFrame();
        frame.setName("name");
        frame.setLayout(new BorderLayout());
        
        JPanel jpleft = new JPanel();
        jpleft.setLayout(new GridBagLayout());
        frame.add(jpleft,BorderLayout.WEST);
        jpleft.setBackground(blue);
        jpleft.setPreferredSize(new Dimension(frame.getWidth()/2,frame.getHeight()));
        jpleft.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Shopping Cart"));
   
        JPanel jpright = new JPanel();
        jpright.setLayout(new GridBagLayout());
        frame.add(jpright,BorderLayout.EAST);
        jpright.setBackground(blue);
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
        adminButton.setBackground(white);
        gc.ipady = 40;
        gc.gridwidth = 2;
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.insets = new Insets(10,10,10,10);
        JButton lookButton = new JButton("Lookup Product/PLU");
      //  adminButton.setPreferredSize(new Dimension(frame.getWidth()/20,frame.getHeight()/20));
        jpright.add(lookButton,gc);
        lookButton.setBackground(white);
        gc.fill = GridBagConstraints.BOTH;
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.insets = new Insets(10,10,10,10);
        gc.ipady = frame.getWidth()/12;
        gc.ipadx = frame.getWidth()/12;
        gc.gridwidth = 1;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10,0,0,10);
        JButton helpButton = new JButton("Help");
        helpButton.setPreferredSize(new Dimension(frame.getWidth()/6,frame.getHeight()/20));
        jpright.add(helpButton,gc);
        helpButton.setBackground(white);
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
        finishButton.setBackground(white);
        
        gc.gridx = 0;
        gc.gridy = 1;
        totalPrice.setOpaque(true);
        jpleft.add(totalPrice,gc);
        totalPrice.setBackground(white);
        totalPrice.setBorder(BorderFactory.createLineBorder(Color.black));
        gc.weighty = 100;
        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1000;
        gc.gridwidth = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 0;
        gc.gridy = 0;
        shopCart.setBackground(Color.white);
        shopCart.setOpaque(true);
        shopCart.setVerticalAlignment(SwingConstants.TOP);
        shopCart.setHorizontalAlignment(SwingConstants.LEFT);
        shopCart.setBorder(BorderFactory.createLineBorder(Color.black));
        jpleft.add(shopCart,gc);
        
        gc.weightx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 0;

        shopCartPrice.setBackground(Color.white);
        shopCartPrice.setOpaque(true);
        shopCartPrice.setBorder(BorderFactory.createLineBorder(Color.black));
        shopCartPrice.setVerticalAlignment(SwingConstants.TOP);
        shopCartPrice.setHorizontalAlignment(SwingConstants.LEFT);
        jpleft.add(shopCartPrice,gc);
        
        
        
        class adminButtoneHandler implements ActionListener{
        	


    		@Override
    		public void actionPerformed(ActionEvent e) {
    			switchScreen(7);
    			
    		}
        
        }
        adminButton.addActionListener(new adminButtoneHandler());
        
        
        
        class lookupButtoneHandler implements ActionListener{
        	


    		@Override
    		public void actionPerformed(ActionEvent e) {
    			switchScreen(9);
    			
    		}
        
        }
        lookButton.addActionListener(new lookupButtoneHandler());
        
        class finishButtoneHandler implements ActionListener{
        	


    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (session.getTotalPrice().compareTo(BigDecimal.ZERO) != 0) {
    			switchScreen(11);
    			}
    		}
        
        }
        finishButton.addActionListener(new finishButtoneHandler());
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So we can click X to close
	}
	
	
	//Call if transitions into this screen.
	public void updateList() {
		
		shopCartPrice.setText("<html>Prices<br/></html>");
		shopCart.setText("<html>Items<br/></html>");
		
		for(BarcodedProduct item : session.getScannedProducts().values()) {
			Integer i = session.getScannedProductsDup().get(item.getBarcode());
			while(i>0) {
			String textItem = shopCart.getText().substring(0,shopCart.getText().length()-8);
			String textPrice = shopCartPrice.getText().substring(0,shopCartPrice.getText().length()-8);
			
			
				textItem = textItem.concat(item.getDescription()+"<br/></html>");
				shopCart.setText(textItem);
				
				System.out.println(i);
				textPrice = textPrice.concat("$");
				textPrice = textPrice.concat(item.getPrice().toString()+ "<br/></html>");
				shopCartPrice.setText(textPrice);
				i -= 1;
			}
			totalPrice.setText("Total = " + session.getTotalPrice());
		}
		for(PLUCodedProduct item : session.getPLUProducts()) {
			String textItem = shopCart.getText().substring(0,shopCart.getText().length()-8);
			String textPrice = shopCartPrice.getText().substring(0,shopCartPrice.getText().length()-8);
			
			textItem = textItem.concat(item.getDescription()+"<br/></html>");
			shopCart.setText(textItem);
			
			
			textPrice = textPrice.concat("$");
			textPrice = textPrice.concat(item.getPrice().toString()+ "<br/></html>");
			shopCartPrice.setText(textPrice);
			
			totalPrice.setText("Total = " + session.getTotalPrice());
		}
		totalPrice.setText("Total = " + session.getTotalPrice());
		
	}
	
}
