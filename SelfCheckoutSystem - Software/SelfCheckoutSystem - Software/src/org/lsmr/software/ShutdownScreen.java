package org.lsmr.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.TouchScreen;

public class ShutdownScreen extends MainGUI{
	
	public static TouchScreen tsl;
	private static JFrame frame;
	private static JButton bootupBtn;
	
	public ShutdownScreen() {
		tsl = new TouchScreen();
		frame = tsl.getFrame();
        shutdownPanel();
        tsl.setVisible(false);
	}
	
	public void shutdownPanel() {
		JPanel shutdownPanel = new JPanel();
		frame.add(shutdownPanel);
		shutdownPanel.setBackground(new Color(0, 0, 0));
		
		
        bootupBtn = new JButton("Bootup System");
        bootupBtn.setFont(new Font("Arial", Font.PLAIN, 30));
        shutdownPanel.add(bootupBtn);
        
        bootupBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ControlUnit.checkoutStation.banknoteInput.removeDanglingBanknote();
				ControlUnit.checkoutStation.banknoteOutput.removeDanglingBanknote();
				ControlUnit.sessionData.restart();
				switchScreen(4);
			}
        	
        });
        
        frame.setVisible(false);
	}
}
