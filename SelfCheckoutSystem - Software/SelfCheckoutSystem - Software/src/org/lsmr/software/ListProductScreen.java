package org.lsmr.software;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


import org.lsmr.selfcheckout.Barcode;
import org.lsmr.selfcheckout.BarcodedItem;
import org.lsmr.selfcheckout.PLUCodedItem;
import org.lsmr.selfcheckout.PriceLookupCode;
import org.lsmr.selfcheckout.devices.AbstractDevice;
import org.lsmr.selfcheckout.devices.OverloadException;
import org.lsmr.selfcheckout.devices.SelfCheckoutStation;
import org.lsmr.selfcheckout.devices.TouchScreen;
import org.lsmr.selfcheckout.devices.listeners.TouchScreenListener;
import org.lsmr.selfcheckout.external.ProductDatabases;
import org.lsmr.selfcheckout.products.BarcodedProduct;
import org.lsmr.selfcheckout.products.PLUCodedProduct;

//private static HashMap<Barcode, BarcodedProduct> scannedProducts = new HashMap<Barcode,BarcodedProduct>(); 
//private static ArrayList <BarcodedItem> scannedItems = new ArrayList<BarcodedItem>();


public class ListProductScreen extends MainGUI {
	
		private static JFrame frame;
		private static JPanel jp;
		public static TouchScreen tsl;
		private static Color blue = new Color(237, 246, 249);
	    private static Color white = new Color(255, 255, 255);
		
		public ListProductScreen () {
		
		PriceLookupCode code1 = new PriceLookupCode("4123");
		PLUCodedProduct product1 = new PLUCodedProduct(code1,"Apple",BigDecimal.valueOf(1.19));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code1, product1);
			
		PriceLookupCode code2 = new PriceLookupCode("4051");
		PLUCodedProduct product2 = new PLUCodedProduct(code2,"Mango",BigDecimal.valueOf(2.47));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code2, product2);
			
		PriceLookupCode code3 = new PriceLookupCode("4022");
		PLUCodedProduct product3 = new PLUCodedProduct(code3,"Grapes",BigDecimal.valueOf(6.27));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code3, product3);
		
		PriceLookupCode code4 = new PriceLookupCode("4011");
		PLUCodedProduct product4 = new PLUCodedProduct(code4,"Banana",BigDecimal.valueOf(1.04));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code4, product4);
		
		PriceLookupCode code5 = new PriceLookupCode("8011");
		PLUCodedProduct product5 = new PLUCodedProduct(code5,"Bag",BigDecimal.valueOf(0.10));
		ProductDatabases.PLU_PRODUCT_DATABASE.put(code5, product5);
		
		Barcode barcode1 = new Barcode("12345678910");
		BarcodedProduct barproduct1 = new BarcodedProduct(barcode1,"Chocolate",BigDecimal.valueOf(1.70));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode1, barproduct1);
			
		Barcode barcode2 = new Barcode("22345678910");
		BarcodedProduct barproduct2 = new BarcodedProduct(barcode2,"Cupcake",BigDecimal.valueOf(2.50));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode2, barproduct2);
			
		Barcode barcode3 = new Barcode("32345678910");
		BarcodedProduct barproduct3 = new BarcodedProduct(barcode3,"Eggs",BigDecimal.valueOf(3.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode3, barproduct3);
		
		Barcode barcode4 = new Barcode("42345678910");
		BarcodedProduct barproduct4 = new BarcodedProduct(barcode4,"Milk",BigDecimal.valueOf(2.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode4, barproduct4);
		
		Barcode barcode5 = new Barcode("5345678910");
		BarcodedProduct barproduct5 = new BarcodedProduct(barcode5,"Water",BigDecimal.valueOf(1.99));
		ProductDatabases.BARCODED_PRODUCT_DATABASE.put(barcode5, barproduct5);
		
		tsl = new TouchScreen();
		frame = tsl.getFrame();
		
		shopItems();
		backButton();
		PLUEntry();
		
		frame.setVisible(false);
		
		}
		
		public static void backButton() {
			JButton back = new JButton("BACK");
			back.setFont(new Font("Arial", Font.BOLD,20));
			back.setBackground(white);
			back.setForeground(Color.black);
			back.setVerticalTextPosition(SwingConstants.CENTER);
			back.setPreferredSize(new Dimension(500, 500));
			jp.add(back);
			back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					switchScreen(6);
				}
			});
			
		}
		
		public static void PLUEntry() {
			JPanel newP = new JPanel(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
	        gc.anchor = GridBagConstraints.CENTER;
	        gc.insets = new Insets(15,15,15,15);
	        
	        gc.gridx = 0;
	        gc.gridy = 1;
			JLabel Text = new JLabel("ENTER PLUCODE ");
			Text.setFont(new Font("Arial", Font.BOLD,15));;
			newP.add(Text, gc);
			
			gc.gridx = 1;
	        gc.gridy = 1;
			JLabel Text2 = new JLabel("QUANTITY: ");
			Text2.setFont(new Font("Arial", Font.BOLD,15));;
			newP.add(Text2, gc);
			
			gc.gridx = 0;
		    gc.gridy = 3;
		    JTextField enterP = new JTextField(4);
			newP.add(enterP, gc);
			
			gc.gridx = 1;
		    gc.gridy = 3;
		    JTextField enterQ = new JTextField(4);
			newP.add(enterQ, gc);
			
			gc.gridx = 0;
		    gc.gridy = 5;
			JButton enter = new JButton("Enter");
			newP.add(enter, gc);
			
			enter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				    String plustring = enterP.getText();
				    String quantity = enterQ.getText();
				    int inum = Integer.parseInt(quantity);
					PriceLookupCode tryPlu = new PriceLookupCode(plustring);
					if ((ProductDatabases.PLU_PRODUCT_DATABASE.containsKey(tryPlu)) && (inum > 0)){
						for (int i = 0; i < inum; i++) {
							ControlUnit.enterPLU.enterPLUProduct(tryPlu);
						}
						switchScreen(16);
						enterQ.setText("");
						enterP.setText("");
					}
				}
			});
			
			newP.setBackground(blue);
			jp.add(newP);
		}
		
		
		public static void shopItems() {
		GridBagConstraints gc = new GridBagConstraints();
		gc.insets = new Insets(15,15,15,15);
		gc.gridx = 0;
		gc.gridy = 0;
		//frame.add(jp, gc);
		jp = new JPanel(new GridLayout(4, 3, 40, 40));
		
		JButton apple = new JButton("<html>APPLE<br>PLU CODE: 4123<br> Click to add 1</html>", new ImageIcon(ListProductScreen.class.getResource("apple.jpg")));
		apple.setFont(new Font("Arial", Font.BOLD,20));
		apple.setBackground(Color.white);
		apple.setVerticalTextPosition(SwingConstants.CENTER);
		jp.add(apple);
		apple.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceLookupCode AppleCode = new PriceLookupCode("4123");
				ControlUnit.enterPLU.enterPLUProduct(AppleCode);
				switchScreen(16);
			}
		});
		
		JButton mango = new JButton("<html>MANGO<br>PLU CODE: 4051<br> Click to add 1</html>", new ImageIcon(ListProductScreen.class.getResource("mango.jpg")));
		mango.setFont(new Font("Arial", Font.BOLD,20));
		mango.setBackground(Color.white);
		mango.setVerticalTextPosition(SwingConstants.CENTER);
		jp.add(mango);
		mango.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceLookupCode MangoCode = new PriceLookupCode("4051");
				ControlUnit.enterPLU.enterPLUProduct(MangoCode);
				switchScreen(16);
			}
		});
		
		JButton grapes = new JButton("<html>GRAPES<br>PLU CODE: 4022<br> Click to add 1</html>", new ImageIcon(ListProductScreen.class.getResource("grapes.jpg")));
		grapes.setFont(new Font("Arial", Font.BOLD,20));
		grapes.setBackground(Color.white);
		grapes.setVerticalTextPosition(SwingConstants.CENTER);
		jp.add(grapes);
		grapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceLookupCode GrapeCode = new PriceLookupCode("4022");
				ControlUnit.enterPLU.enterPLUProduct(GrapeCode);
				switchScreen(16);
		
			}
		});
		
		
		JButton bananas = new JButton("<html>BANANA<br>PLU CODE: 4011<br> Click to add 1</html>", new ImageIcon(ListProductScreen.class.getResource("banana.jpeg")));
		bananas.setFont(new Font("Arial", Font.BOLD,20));
		bananas.setBackground(Color.white);
		bananas.setVerticalTextPosition(SwingConstants.CENTER);
		jp.add(bananas);
		bananas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceLookupCode bananaCode = new PriceLookupCode("4011");
				ControlUnit.enterPLU.enterPLUProduct(bananaCode);
				switchScreen(16);
			}
		});
	
		JButton chocolate = new JButton("<html>CHOCOLATE<br>(CLICK TO SCAN)</html>", new ImageIcon(ListProductScreen.class.getResource("choco.jpg")));
		chocolate.setFont(new Font("Arial", Font.BOLD,20));
		chocolate.setBackground(Color.white);
		jp.add(chocolate);
		chocolate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String chocoString = "12345678910";
				Barcode chocoBarcode = new Barcode(chocoString);
				ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
				BarcodedItem Chocolate = new BarcodedItem(chocoBarcode, 200.0);
				BarcodeList.add(Chocolate);
				ControlUnit.itemScan.scanItems(BarcodeList);
				try {
					ControlUnit.itemBag.bagItems(Chocolate);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchScreen(16);
	
			}
		});
		
	
		JButton cupcake = new JButton("<html>CUPCAKE<br>(CLICK TO SCAN)</html>", new ImageIcon(ListProductScreen.class.getResource("cupcake.jpeg")));
		cupcake.setFont(new Font("Arial", Font.BOLD,20));
		cupcake.setBackground(Color.white);
		jp.add(cupcake);
		cupcake.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cupString = "22345678910";
				Barcode cupBarcode = new Barcode(cupString);
				ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
				BarcodedItem Cupcake = new BarcodedItem(cupBarcode, 500.0);
				BarcodeList.add(Cupcake);
				ControlUnit.itemScan.scanItems(BarcodeList);
				try {
					ControlUnit.itemBag.bagItems(Cupcake);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchScreen(16);

			}
		});
		

		
		JButton eggs = new JButton("<html>EGGS<br>(CLICK TO SCAN)</html>", new ImageIcon(ListProductScreen.class.getResource("eggs.jpg")));
		eggs.setFont(new Font("Arial", Font.BOLD,20));
		eggs.setBackground(Color.white);
		jp.add(eggs);
		eggs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String eggString = "32345678910";
				Barcode eggBarcode = new Barcode(eggString);
				ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
				BarcodedItem Eggs = new BarcodedItem(eggBarcode, 500.0);
				BarcodeList.add(Eggs);
				ControlUnit.itemScan.scanItems(BarcodeList);
				try {
					ControlUnit.itemBag.bagItems(Eggs);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchScreen(16);
			}
		});
		
		JButton milk = new JButton("<html>MILK<br>(CLICK TO SCAN)</html>", new ImageIcon(ListProductScreen.class.getResource("milk.jpg")));
		milk.setFont(new Font("Arial", Font.BOLD,20));
		milk.setBackground(Color.white);
		jp.add(milk);
		milk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String milkString = "42345678910";
				Barcode milkBarcode = new Barcode(milkString);
				ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
				BarcodedItem Milk = new BarcodedItem(milkBarcode, 1000.0);
				BarcodeList.add(Milk);
				ControlUnit.itemScan.scanItems(BarcodeList);
				try {
					ControlUnit.itemBag.bagItems(Milk);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchScreen(16);
			}
		});
		
		JButton water = new JButton("<html>WATER<br>(CLICK TO SCAN)</html>", new ImageIcon(ListProductScreen.class.getResource("water.jpg")));
		water.setFont(new Font("Arial", Font.BOLD,20));
		water.setBackground(Color.white);
		jp.add(water);
		water.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String waterString = "5345678910";
				Barcode waterBarcode = new Barcode(waterString);
				ArrayList<BarcodedItem> BarcodeList = new ArrayList<BarcodedItem>();
				BarcodedItem Water = new BarcodedItem(waterBarcode, 500.0);
				BarcodeList.add(Water);
				ControlUnit.itemScan.scanItems(BarcodeList);
				try {
					ControlUnit.itemBag.bagItems(Water);
				} catch (OverloadException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				switchScreen(16);
 
			}
		});
		
		JButton bag = new JButton("<html>SHOPPING BAG<br>PLU CODE: 8011<br> Click to add 1</html>", new ImageIcon(ListProductScreen.class.getResource("bag.jpg")));
		bag.setFont(new Font("Arial", Font.BOLD,20));
		bag.setBackground(Color.white);
		jp.add(bag);
		bag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PriceLookupCode BagCode = new PriceLookupCode("8011");
				ControlUnit.enterPLU.enterPLUProduct(BagCode);
				switchScreen(6);
			}
		});
		frame.add(jp);
		jp.setBackground(blue);
	}
			
		
	}

