package org.lsmr.software;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

//private static HashMap<Barcode, BarcodedProduct> scannedProducts = new HashMap<Barcode,BarcodedProduct>(); 
//private static ArrayList <BarcodedItem> scannedItems = new ArrayList<BarcodedItem>();


public class ListProductScreen extends AbstractDevice <TouchScreenListener> {
	
		private static JFrame frame;
		private static JPanel jp;
		private static TouchScreen tsl;
		private SelfCheckoutStation scs;
		private CurrentSessionData csd;
		
		public ListProductScreen () {
		
		JPanel jp = new JPanel();
		TouchScreen tsl = new TouchScreen();
		JFrame frame = tsl.getFrame();
		jp = new JPanel(new GridLayout(0, 3, 20, 20));

		JButton apple = new JButton("Apple", new ImageIcon(ListProductScreen.class.getResource("apple.jpeg")));
		apple.setFont(new Font("Arial", Font.BOLD,40));	
		apple.setBackground(Color.LIGHT_GRAY);
		apple.setForeground(Color.blue);
		jp.add(apple);
		apple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal ApplePrice = new BigDecimal(1.10);
				PriceLookupCode AppleCode = new PriceLookupCode("4123");
				PLUCodedProduct Apple = new PLUCodedProduct(AppleCode, "Apple", ApplePrice);
				csd.addPLUProduct(Apple);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
			}
		});
		
		JButton mango = new JButton("Mango", new ImageIcon(ListProductScreen.class.getResource("mango.jpg")));
		mango.setFont(new Font("Arial", Font.BOLD,40));
		mango.setBackground(Color.LIGHT_GRAY);
		mango.setForeground(Color.blue);
		jp.add(mango);
		mango.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal MangoPrice = new BigDecimal(1.44);
				PriceLookupCode MangoCode = new PriceLookupCode("4051");
				PLUCodedProduct Mango = new PLUCodedProduct(MangoCode, "Mango", MangoPrice);
				csd.addPLUProduct(Mango);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
			}
		});
		
		JButton grapes = new JButton("Grapes", new ImageIcon(ListProductScreen.class.getResource("grapes.jpg")));
		grapes.setFont(new Font("Arial", Font.BOLD,40));
		grapes.setBackground(Color.LIGHT_GRAY);
		grapes.setForeground(Color.blue);
		jp.add(grapes);
		grapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal GrapePrice = new BigDecimal(6.99);
				PriceLookupCode GrapeCode = new PriceLookupCode("4022");
				PLUCodedProduct Grapes = new PLUCodedProduct(GrapeCode, "Grapes", GrapePrice);
				csd.addPLUProduct(Grapes);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
		
			}
		});
		
		JButton bananas = new JButton("Banana", new ImageIcon(ListProductScreen.class.getResource("banana.jpeg")));
		bananas.setFont(new Font("Arial", Font.BOLD,40));
		bananas.setBackground(Color.LIGHT_GRAY);
		bananas.setForeground(Color.blue);
		jp.add(bananas);
		bananas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigDecimal bananaPrice = new BigDecimal(1.00);
				PriceLookupCode bananaCode = new PriceLookupCode("4123");
				PLUCodedProduct Banana = new PLUCodedProduct(bananaCode, "Banana", bananaPrice);
				csd.addPLUProduct(Banana);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
			}
		});
		
		JButton chocolate = new JButton("Chocolate", new ImageIcon(ListProductScreen.class.getResource("chocolate.jpg")));
		chocolate.setFont(new Font("Arial", Font.BOLD,40));
		chocolate.setBackground(Color.LIGHT_GRAY);
		chocolate.setForeground(Color.blue);
		jp.add(chocolate);
		chocolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chocoString = "12345678910";
				Barcode chocoBarcode = new Barcode(chocoString);
				ArrayList<BarcodedItem> BarcodeList = null;
				BarcodedItem Chocolate = new BarcodedItem(chocoBarcode, 200.0);
				BarcodeList.add(Chocolate);
				ControlUnit.itemScan.scanItems(BarcodeList);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
	
			}
		});
		
		JButton cupcake = new JButton("Cupcake", new ImageIcon(ListProductScreen.class.getResource("cupcake.jpeg")));
		cupcake.setFont(new Font("Arial", Font.BOLD,40));
		cupcake.setBackground(Color.LIGHT_GRAY);
		cupcake.setForeground(Color.blue);
		jp.add(cupcake);
		cupcake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cupString = "22345678910";
				Barcode cupBarcode = new Barcode(cupString);
				ArrayList<BarcodedItem> BarcodeList = null;
				BarcodedItem Cupcake = new BarcodedItem(cupBarcode, 500.0);
				BarcodeList.add(Cupcake);
				ControlUnit.itemScan.scanItems(BarcodeList);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN

			}
		});
		
		JButton eggs = new JButton("Eggs", new ImageIcon(ListProductScreen.class.getResource("eggs.jpg")));
		eggs.setFont(new Font("Arial", Font.BOLD,40));
		eggs.setBackground(Color.LIGHT_GRAY);
		eggs.setForeground(Color.blue);
		jp.add(eggs);
		eggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eggString = "32345678910";
				Barcode eggBarcode = new Barcode(eggString);
				ArrayList<BarcodedItem> BarcodeList = null;
				BarcodedItem Eggs = new BarcodedItem(eggBarcode, 500.0);
				BarcodeList.add(Eggs);
				ControlUnit.itemScan.scanItems(BarcodeList);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
			}
		});
		
		JButton milk = new JButton("Milk", new ImageIcon(ListProductScreen.class.getResource("milk.jpg")));
		milk.setFont(new Font("Arial", Font.BOLD,40));
		milk.setForeground(Color.blue);
		jp.add(milk);
		milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String milkString = "42345678910";
				Barcode milkBarcode = new Barcode(milkString);
				ArrayList<BarcodedItem> BarcodeList = null;
				BarcodedItem Milk = new BarcodedItem(milkBarcode, 1000.0);
				BarcodeList.add(Milk);
				ControlUnit.itemScan.scanItems(BarcodeList);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN
			}
		});
		
		JButton water = new JButton("Water", new ImageIcon(ListProductScreen.class.getResource("water.jpg")));
		water.setFont(new Font("Arial", Font.BOLD,40));
		water.setBackground(new Color(152, 251, 152));
		water.setForeground(Color.blue);
		jp.add(water);
		water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String waterString = "52345678910";
				Barcode waterBarcode = new Barcode(waterString);
				ArrayList<BarcodedItem> BarcodeList = null;
				BarcodedItem Water = new BarcodedItem(waterBarcode, 500.0);
				BarcodeList.add(Water);
				ControlUnit.itemScan.scanItems(BarcodeList);
				//TRANSITION: BACK TO PRODUCT AND TOTAL SCREEN

			}
		});
	
		
		jp.setBackground(Color.pink);
		frame.add(jp);
		frame.setVisible(true);
		}
	}

