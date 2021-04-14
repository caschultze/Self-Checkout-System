package org.lsmr.software;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.lsmr.selfcheckout.devices.TouchScreen;

public class SceenBootUp extends MainGUI {
	
	public static TouchScreen touchscreen;
	static ControlUnit control;
    static JTextField jtext = new JTextField(16);
    static JPasswordField jPass = new JPasswordField(16);
	
    static JButton aButton = new JButton("A");
    static JButton bButton = new JButton("B");
    static JButton cButton = new JButton("C");
    static JButton dButton = new JButton("D");
    static JButton eButton = new JButton("E");
    static JButton fButton = new JButton("F");
    static JButton gButton = new JButton("G");
    static JButton hButton = new JButton("H");
    static JButton iButton = new JButton("I");
    static JButton jButton = new JButton("J");
    static JButton kButton = new JButton("K");
    static JButton lButton = new JButton("L");
    static JButton mButton = new JButton("M");
    static JButton nButton = new JButton("N");
    static JButton oButton = new JButton("O");
    static JButton pButton = new JButton("P");
    static JButton qButton = new JButton("Q");
    static JButton rButton = new JButton("R");
    static JButton sButton = new JButton("S");
    static JButton tButton = new JButton("T");
    static JButton uButton = new JButton("U");
    static JButton vButton = new JButton("V");
    static JButton wButton = new JButton("W");
    static JButton xButton = new JButton("X");
    static JButton yButton = new JButton("Y");       
    static JButton zButton = new JButton("Z");
    
    static JButton zeroButton = new JButton("0");
    static JButton oneButton = new JButton("1");
    static JButton twoButton = new JButton("2");
    static JButton threeButton = new JButton("3");
    static JButton fourButton = new JButton("4");
    static JButton fiveButton = new JButton("5");
    static JButton sixButton = new JButton("6");
    static JButton sevenButton = new JButton("7");
    static JButton eightButton = new JButton("8");
    static JButton nineButton = new JButton("9");
    
    static boolean shifted = false;
    static JTextField selected = jtext;
    Color blue = new Color(237, 246, 249);
    Color white = new Color(255, 255, 255);
    
	public SceenBootUp() {
		
		touchscreen = new TouchScreen();
        JFrame frame = touchscreen.getFrame();
        frame.setName("name");
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        frame.add(jp);
        jp.setBackground(blue);
        
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(40,0,0,40);
        gc.gridx = 0;
        gc.gridy = 0;
        jp.add(jtext,gc);

        
        gc.gridx = 0;
        gc.gridy = 1;
        jp.add(jPass,gc);
        
        
        gc.gridx = 0;
        gc.gridy = 2;
        JButton enterButton = new JButton("ENTER");
        enterButton.setPreferredSize(new Dimension(frame.getWidth()/6,frame.getHeight()/20));
        jp.add(enterButton,gc);
        enterButton.setBackground(white);
        
        
        
        JPanel jpkey = new JPanel();
        jpkey.setLayout(new GridBagLayout());
   
        
        JButton shiftButton = new JButton("Shift");
        
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(0,0,0,0);
        
        gc.gridx = 0;
        gc.gridy = 0;
        jpkey.add(oneButton,gc);
        oneButton.setBackground(white);
        
        gc.gridx = 1;
        gc.gridy = 0;
        jpkey.add(twoButton,gc);
        twoButton.setBackground(white);
        
        gc.gridx = 2;
        gc.gridy = 0;
        jpkey.add(threeButton,gc);
        threeButton.setBackground(white);
        
        gc.gridx = 3;
        gc.gridy = 0;
        jpkey.add(fourButton,gc);
        fourButton.setBackground(white);
            
        
        gc.gridx = 4;
        gc.gridy = 0;
        jpkey.add(fiveButton,gc);
        fiveButton.setBackground(white);
        
        gc.gridx = 5;
        gc.gridy = 0;
        jpkey.add(sixButton,gc);
        sixButton.setBackground(white);
        
        gc.gridx = 6;
        gc.gridy = 0;
        jpkey.add(sevenButton,gc);
        sevenButton.setBackground(white);
        
        
        gc.gridx = 7;
        gc.gridy = 0;
        jpkey.add(eightButton,gc);
        eightButton.setBackground(white);
        
        gc.gridx = 8;
        gc.gridy = 0;
        jpkey.add(nineButton,gc);
        nineButton.setBackground(white);
        gc.gridx = 9;
        gc.gridy = 0;
        jpkey.add(zeroButton,gc);
        zeroButton.setBackground(white);
        gc.gridx = 0;
        gc.gridy = 1;
        jpkey.add(qButton,gc);
        qButton.setBackground(white);
        gc.gridx = 1;
        gc.gridy = 1;
        jpkey.add(wButton,gc);
        wButton.setBackground(white);
        gc.gridx = 2;
        gc.gridy = 1;
        jpkey.add(eButton,gc);
        eButton.setBackground(white);
        gc.gridx = 3;
        gc.gridy = 1;
        jpkey.add(rButton,gc);
        rButton.setBackground(white);
        gc.gridx = 4;
        gc.gridy = 1;
        jpkey.add(tButton,gc);
        tButton.setBackground(white);
        gc.gridx = 5;
        gc.gridy = 1;
        jpkey.add(yButton,gc);
        yButton.setBackground(white);
        gc.gridx = 6;
        gc.gridy = 1;
        jpkey.add(uButton,gc);
        uButton.setBackground(white);
        gc.gridx = 7;
        gc.gridy = 1;
        jpkey.add(iButton,gc);
        iButton.setBackground(white);
        gc.gridx = 8;
        gc.gridy = 1;
        jpkey.add(oButton,gc);
        oButton.setBackground(white);
        
        gc.gridx = 9;
        gc.gridy = 1;
        jpkey.add(pButton,gc);
        pButton.setBackground(white);
        
        gc.gridx = 0;
        gc.gridy = 2;
        jpkey.add(aButton,gc);
        aButton.setBackground(white);
        
        gc.gridx = 1;
        gc.gridy = 2;
        jpkey.add(sButton,gc);
        sButton.setBackground(white);
        
        gc.gridx = 2;
        gc.gridy = 2;
        jpkey.add(dButton,gc);
        dButton.setBackground(white);
        
        gc.gridx = 3;
        gc.gridy = 2;
        jpkey.add(fButton,gc);
        fButton.setBackground(white);
        
        gc.gridx = 4;
        gc.gridy = 2;
        jpkey.add(gButton,gc);
        gButton.setBackground(white);
        
        gc.gridx = 5;
        gc.gridy = 2;
        jpkey.add(hButton,gc);
        hButton.setBackground(white);
        
        gc.gridx = 6;
        gc.gridy = 2;
        jpkey.add(jButton,gc);
        jButton.setBackground(white);
        
        gc.gridx = 7;
        gc.gridy = 2;
        jpkey.add(kButton,gc);
        kButton.setBackground(white);
        
        gc.gridx = 8;
        gc.gridy = 2;
        jpkey.add(lButton,gc);
        lButton.setBackground(white);
        
        gc.gridx = 0;
        gc.gridy = 3;
        jpkey.add(shiftButton,gc);
        shiftButton.setBackground(white);
        
        gc.gridx = 1;
        gc.gridy = 3;
        jpkey.add(zButton,gc);
        zButton.setBackground(white);
        
        gc.gridx = 2;
        gc.gridy = 3;
        jpkey.add(xButton,gc);
        xButton.setBackground(white);
        
        gc.gridx = 3;
        gc.gridy = 3;
        jpkey.add(cButton,gc);
        cButton.setBackground(white);
        
        gc.gridx = 4;
        gc.gridy = 3;
        jpkey.add(vButton,gc);
        vButton.setBackground(white);
        
        gc.gridx = 5;
        gc.gridy = 3;
        jpkey.add(bButton,gc);
        
        bButton.setBackground(white);
        gc.gridx = 6;
        gc.gridy = 3;
        jpkey.add(nButton,gc);
        nButton.setBackground(white);
        
        gc.gridx = 7;
        gc.gridy = 3;
        jpkey.add(mButton,gc);
        mButton.setBackground(white);
        
        gc.gridx = 0;
        gc.gridy = 10;
        JButton deleteButton = new JButton("Delete");
        jpkey.add(deleteButton,gc);
        deleteButton.setBackground(white);
        
        gc.ipady = 40;
        gc.ipadx = 100;
        gc.fill = GridBagConstraints.BOTH;
        gc.insets = new Insets(40,0,0,40);
        gc.gridx = 0;
        gc.gridy = 3;
        jp.add(jpkey,gc);
        jpkey.setBackground(blue);
        
        class enterButtoneHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = jtext.getText();
			String password = jPass.getText();
			if (control.login.verifyLogin(username, password)) {
				switchScreen(5);
				
			}
				jtext.setText("");
				jPass.setText("");
			
		}
        
        }
		enterButton.addActionListener(new enterButtoneHandler());
		
		
		 class shiftButtoneHandler implements ActionListener{
				@Override
				public void actionPerformed(ActionEvent e) {
					if(shifted) {
						
					aButton.setText("A");
			        bButton.setText("B");
			        cButton.setText("C");
			        dButton.setText("D");
			        eButton.setText("E");
			        fButton.setText("F");
			        gButton.setText("G");
			        hButton.setText("H");
			        iButton.setText("I");
			        jButton.setText("J");
			        kButton.setText("K");
			        lButton.setText("L");
			        mButton.setText("M");
			        nButton.setText("N");
			        oButton.setText("O");
			        pButton.setText("P");
			        qButton.setText("Q");
			        rButton.setText("R");
			        sButton.setText("S");
			        tButton.setText("T");
			        uButton.setText("U");
			        vButton.setText("V");
			        wButton.setText("W");
			        xButton.setText("X");
			        yButton.setText("Y");
			        zButton.setText("Z");
			        
			        
			        zeroButton.setText("0");
			        oneButton.setText("1");
			        twoButton.setText("2");
			        threeButton.setText("3");
			        fourButton.setText("4");
			        fiveButton.setText("5");
			        sixButton.setText("6");
			        sevenButton.setText("7");
			        eightButton.setText("8");
			        nineButton.setText("9");
			        
					}else {
						
						aButton.setText("a");
				        bButton.setText("b");
				        cButton.setText("c");
				        dButton.setText("d");
				        eButton.setText("e");
				        fButton.setText("f");
				        gButton.setText("g");
				        hButton.setText("h");
				        iButton.setText("i");
				        jButton.setText("j");
				        kButton.setText("k");
				        lButton.setText("l");
				        mButton.setText("m");
				        nButton.setText("n");
				        oButton.setText("o");
				        pButton.setText("p");
				        qButton.setText("q");
				        rButton.setText("r");
				        sButton.setText("s");
				        tButton.setText("t");
				        uButton.setText("u");
				        vButton.setText("v");
				        wButton.setText("w");
				        xButton.setText("x");
				        yButton.setText("y");
				        zButton.setText("z");
				        
				        
				        zeroButton.setText(")");
				        oneButton.setText("!");
				        twoButton.setText("@");
				        threeButton.setText("#");
				        fourButton.setText("$");
				        fiveButton.setText("%");
				        sixButton.setText("^");
				        sevenButton.setText("&");
				        eightButton.setText("*");
				        nineButton.setText("(");
						
						
					}
					shifted = !shifted; 
				}
		        
		}
		shiftButton.addActionListener(new shiftButtoneHandler());
				
		buttonHandler(aButton);
		buttonHandler(bButton);
		buttonHandler(cButton);
		buttonHandler(dButton);
		buttonHandler(eButton);
		buttonHandler(fButton);
		buttonHandler(gButton);
		buttonHandler(hButton);
		buttonHandler(iButton);
		buttonHandler(jButton);
		buttonHandler(kButton);
		buttonHandler(lButton);
		buttonHandler(mButton);
		buttonHandler(nButton);
		buttonHandler(oButton);
		buttonHandler(pButton);
		buttonHandler(qButton);
		buttonHandler(rButton);
		buttonHandler(sButton);
		buttonHandler(tButton);
		buttonHandler(uButton);
		buttonHandler(vButton);
		buttonHandler(wButton);
		buttonHandler(xButton);
		buttonHandler(yButton);
		buttonHandler(zButton);
		
		
		buttonHandler(oneButton);
		buttonHandler(twoButton);
		buttonHandler(threeButton);
		buttonHandler(fourButton);
		buttonHandler(fiveButton);
		buttonHandler(sixButton);
		buttonHandler(sevenButton);
		buttonHandler(eightButton);
		buttonHandler(nineButton);
		buttonHandler(zeroButton);
		
		
		class deleteButtoneHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String text =	selected.getText();
				text = text.substring(0,text.length()-1);
				selected.setText(text);
		}	
		}
		deleteButton.addActionListener(new deleteButtoneHandler());
		
		
		jtext.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                selected = jtext;
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Your code here
            }
        });
		
		jPass.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                selected = jPass;
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Your code here
            }
        });

		
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // So we can click X to close
}
	
	
	
	
	public static void buttonHandler(JButton button) {
		
		class aButtoneHandler implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String text =	selected.getText();
				text = text.concat(button.getText());
				selected.setText(text);
		}	
		}
		button.addActionListener(new aButtoneHandler());
		
	}

}
