package org.lsmr.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.devices.TouchScreen;

public class ThanksForShoppingScreen extends MainGUI{
	
	static JPanel jp = new JPanel();
	public static TouchScreen tsl;
	private static JFrame frame;
	
	public ThanksForShoppingScreen() {
		
		Color blue = new Color(237, 246, 249);
        Color white = new Color(255, 255, 255);
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		JLabel thanks_shopping = new JLabel("label");
		thanks_shopping.setText("Thanks For Shopping With Us");
		thanks_shopping.setFont(new Font(thanks_shopping.getFont().getName(), thanks_shopping.getFont().getStyle(), 50));
		jp.add(thanks_shopping, gc);
		
		JButton finish = new JButton("Finish");
		finish.setFont(new Font("Arial", Font.PLAIN, 40));
		finish.setForeground(white);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.ipady = 40;
		jp.add(finish, gc);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (BarcodedItem item : ControlUnit.sessionData.getScannedItems())
				{
					ControlUnit.removesItems.removesItems(item);
				}
				ControlUnit.sessionData = new CurrentSessionData();
				switchScreen(5);
			}
		});
		
		jp.setBackground(blue);
		frame.add(jp);
		frame.setVisible(false);
	}
}
