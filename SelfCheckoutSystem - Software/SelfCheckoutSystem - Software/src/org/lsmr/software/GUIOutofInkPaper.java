package org.lsmr.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.devices.TouchScreen;

public class GUIOutofInkPaper extends MainGUI {
	static JPanel jp;
	public static TouchScreen tsl;
	private static JFrame frame;
	private static JLabel refillRequired;
	private static JButton adminButton;
	Color blue = new Color(237, 246, 249);
    Color white = new Color(255, 255, 255);
    private static Font font = new Font("Arial", Font.PLAIN, 40);
	
	public GUIOutofInkPaper() {
		jp = new JPanel();
		jp.setLayout(new BorderLayout());
        jp.setBackground(blue);
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		
		refillRequired = new JLabel("Station out of ink or paper");
		jp.add(refillRequired,BorderLayout.PAGE_START);
		refillRequired.setFont(font);
		
		adminButton = new JButton("ADMIN");
		jp.add(adminButton, BorderLayout.CENTER);
		adminButton.setBackground(white);
		jp.setBackground(blue);
		frame.add(jp);
		
		frame.setVisible(false);
		
		adminButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switchScreen(7);
			}
		});
	}
}
