package org.lsmr.software;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

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
	private static JLabel thanks_shopping;
	
	
	public ThanksForShoppingScreen() {
		
		Color blue = new Color(237, 246, 249);
        Color white = new Color(255, 255, 255);
        
        jp.setBackground(blue);
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		
		jp.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		
		thanks_shopping = new JLabel("label");
		thanks_shopping.setText("Thanks For Shopping With Us");
		thanks_shopping.setFont(new Font(thanks_shopping.getFont().getName(), thanks_shopping.getFont().getStyle(), 50));
		jp.add(thanks_shopping, gc);
		
		JButton finish = new JButton("Finish");
		finish.setFont(new Font("Arial", Font.PLAIN, 40));
		finish.setBackground(white);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.ipady = 40;
		jp.add(finish, gc);
		finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (BarcodedItem item : ControlUnit.sessionData.getScannedItems())
				{
					ControlUnit.removesItems.removesItems(item);
				}
				ControlUnit.InkCounter--;
                ControlUnit.PaperCounter--;
                ControlUnit.sessionData.restart();
                if (ControlUnit.InkCounter != 0 && ControlUnit.PaperCounter != 0) {
                	switchScreen(5);
                }
                if (ControlUnit.InkCounter == 0) {
                    switchScreen(7);
                    System.out.print("Need to refill ink\n");
                } 
                if (ControlUnit.PaperCounter == 0) {
                    switchScreen(7);
                    System.out.print("Need to refill paper\n");
                }
                
			}
		});
		
		
		JButton help = new JButton("Help");
		help.setFont(new Font("Arial", Font.PLAIN, 40));
		help.setBackground(white);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.ipady = 40;
		jp.add(help, gc);
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help.setBackground(Color.green);
				
			}
		});
		
		JLabel finalChange = new JLabel();
		finalChange.setFont(new Font("Arial", Font.PLAIN, 40));
		finalChange.setText(updateChange());
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipady = 40;
		jp.add(finalChange, gc);

		jp.setBackground(blue);

		frame.add(jp);
		frame.setVisible(false);
	}
	
	
	
	public String updateChange() {
		String error = "Error! Please call an attendant";
		String success = "Please take your change.";
		String blank = "";
		try {
			ControlUnit.changeReceive.giveChange();
		}catch (Exception e) {
			thanks_shopping.setText("");
			return error;
			
		}
		thanks_shopping.setText("Thanks For Shopping With Us! " + success);
		return blank;
		
	}
	
	
	
}
