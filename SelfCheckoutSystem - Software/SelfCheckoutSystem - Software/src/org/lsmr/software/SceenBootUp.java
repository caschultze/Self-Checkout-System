package org.lsmr.software;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lsmr.selfcheckout.devices.TouchScreen;

public class SceenBootUp {
	public static void main(String[] args) {
		
		
		SceenStart start = new SceenStart();
		TouchScreen tsl = new TouchScreen();
        JFrame frame = tsl.getFrame();
        frame.setVisible(true);
        frame.setName("name");
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        frame.add(jp);
        jp.setBackground(Color.green);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(40,0,0,40);
        gc.gridx = 0;
        gc.gridy = 0;
        JTextField jtext = new JTextField(16);
        jp.add(jtext,gc);

        
        gc.gridx = 0;
        gc.gridy = 1;
        JTextField jtext2 = new JTextField(16);
        jp.add(jtext2,gc);
        
        
        gc.gridx = 0;
        gc.gridy = 2;
        JButton enterButton = new JButton("ENTER");
        enterButton.setPreferredSize(new Dimension(frame.getWidth()/6,frame.getHeight()/20));
        jp.add(enterButton,gc);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So we can click X to close
        frame.setVisible(true);
        
        class enterButtoneHandler implements ActionListener{
        	


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setVisible(false);
			start.setVisible(true);
			
		}
        
        }
		enterButton.addActionListener(new enterButtoneHandler());
}
	

	
	
	
	
}
