package org.lsmr.software;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;


public class ListProductScreen extends AbstractDevice <TouchScreenListener> {
	
	private JFrame frame;
	private JPanel userPanel;
	private SelfCheckoutStation station;
	private ControlUnit cu;
	private static JTextField jtext;
	static JPanel jp;
	private static TouchScreen tsl;
	
	public static void main(String[] args) throws IOException {
		tsl = new TouchScreen();
		JFrame frame = tsl.getFrame();
		ProductScreen(frame);
	}

	public static void ProductScreen(JFrame frame) throws IOException {
		
		jp = new JPanel(new GridLayout(0, 3, 20, 20));
		
		JButton apple = new JButton("Apple", new ImageIcon(ListProductScreen.class.getResource("apple.jpeg")));
		apple.setFont(new Font("Arial", Font.BOLD,40));	
		apple.setBackground(Color.LIGHT_GRAY);
		apple.setForeground(Color.blue);
		jp.add(apple);
		apple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		JButton mango = new JButton("Mango", new ImageIcon(ListProductScreen.class.getResource("mango.jpg")));
		mango.setFont(new Font("Arial", Font.BOLD,40));
		mango.setBackground(Color.LIGHT_GRAY);
		mango.setForeground(Color.blue);
		jp.add(mango);
		mango.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		
		JButton grapes = new JButton("Grapes", new ImageIcon(ListProductScreen.class.getResource("grapes.jpg")));
		grapes.setFont(new Font("Arial", Font.BOLD,40));
		grapes.setBackground(Color.LIGHT_GRAY);
		grapes.setForeground(Color.blue);
		jp.add(grapes);
		grapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
			}
		});
		
		JButton bananas = new JButton("Banana", new ImageIcon(ListProductScreen.class.getResource("bananass.jpeg")));
		bananas.setFont(new Font("Arial", Font.BOLD,40));
		bananas.setBackground(Color.LIGHT_GRAY);
		bananas.setForeground(Color.blue);
		jp.add(bananas);
		bananas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JButton chocolate = new JButton("Chocolate", new ImageIcon(ListProductScreen.class.getResource("chocolate.jpg")));
		chocolate.setFont(new Font("Arial", Font.BOLD,40));
		chocolate.setBackground(Color.LIGHT_GRAY);
		chocolate.setForeground(Color.blue);
		jp.add(chocolate);
		chocolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		
		JButton cupcake = new JButton("Cupcake", new ImageIcon(ListProductScreen.class.getResource("cupcake.jpeg")));
		cupcake.setFont(new Font("Arial", Font.BOLD,40));
		cupcake.setBackground(Color.LIGHT_GRAY);
		cupcake.setForeground(Color.blue);
		jp.add(cupcake);
		cupcake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JButton eggs = new JButton("Eggs", new ImageIcon(ListProductScreen.class.getResource("eggs.jpg")));
		eggs.setFont(new Font("Arial", Font.BOLD,40));
		eggs.setBackground(Color.LIGHT_GRAY);
		eggs.setForeground(Color.blue);
		jp.add(eggs);
		eggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JButton milk = new JButton("Milk", new ImageIcon(ListProductScreen.class.getResource("milk.jpg")));
		milk.setFont(new Font("Arial", Font.BOLD,40));
		milk.setForeground(Color.blue);
		jp.add(milk);
		milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		JButton water = new JButton("Water", new ImageIcon(ListProductScreen.class.getResource("water.jpg")));
		water.setFont(new Font("Arial", Font.BOLD,40));
		water.setBackground(new Color(152, 251, 152));
		water.setForeground(Color.blue);
		jp.add(water);
		water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	
		
		jp.setBackground(Color.pink);
		frame.add(jp);
		frame.setVisible(true);
	
		
		}
}