package org.lsmr.software;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;

public class SceenStart extends AbstractDevice<TouchScreenListener> {
	static BoxLayout centerButtons;
	static JButton startButton;
	static JFrame frame;
	
	public SceenStart (){
		TouchScreen tsl = new TouchScreen();
		frame = tsl.getFrame();
        frame.setVisible(true);
        frame.setName("name");
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        frame.add(jp);
        jp.setBackground(Color.green);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.weightx = 0.5;
        gc.weighty = 0.5;
        
        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.insets = new Insets(10,0,0,10);
        JButton adminButton = new JButton("Admin");
        adminButton.setPreferredSize(new Dimension(frame.getWidth()/20,frame.getHeight()/20));
        jp.add(adminButton,gc);
        
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.weighty = 100;
        gc.gridx = 0;
        gc.gridy = 1;
        startButton = new JButton("Start Checkout");
        startButton.setPreferredSize(new Dimension(frame.getWidth()/3,frame.getHeight()/5));
        jp.add(startButton, gc);
        
        gc.insets = new Insets(10,0,0,0);
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.weighty = 100;
        gc.gridx = 0;
        gc.gridy = 2;
        JButton memButton = new JButton("Member Start");
        memButton.setPreferredSize(new Dimension(frame.getWidth()/3,frame.getHeight()/5));
        jp.add(memButton, gc);
        
        
        class memButtoneHandler implements ActionListener{
        	


    		@Override
    		public void actionPerformed(ActionEvent e) {
    			//move to frame 11
    			
    		}
        
        }
        memButton.addActionListener(new memButtoneHandler());
        
        class startButtoneHandler implements ActionListener{
        	


    		@Override
    		public void actionPerformed(ActionEvent e) {
    			//move to frame 2
    			
    		}
        
        }
        startButton.addActionListener(new startButtoneHandler());
        
        
        
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So we can click X to close
        frame.setVisible(false);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
	
	
}
