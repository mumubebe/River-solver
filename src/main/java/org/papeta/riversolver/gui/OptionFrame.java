package org.papeta.riversolver.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class OptionFrame extends JFrame {
	private static JTextField stackSize;
	private static JTextField moneyInPot;
	private static JTextField iterationField;
	public OptionFrame() {
		setSize(245, 231);
		getContentPane().setLayout(null);
		
		JLabel lblStackSize = new JLabel("Stack size");
		lblStackSize.setBounds(99, 6, 96, 16);
		getContentPane().add(lblStackSize);
		
		stackSize = new JTextField();
		stackSize.setText("100");
		stackSize.setBounds(61, 25, 134, 28);
		getContentPane().add(stackSize);
		stackSize.setColumns(10);
		
		JLabel lblMoneyInPot = new JLabel("Money in pot");
		lblMoneyInPot.setBounds(87, 65, 96, 16);
		getContentPane().add(lblMoneyInPot);
		
		moneyInPot = new JTextField();
		moneyInPot.setText("4");
		moneyInPot.setBounds(61, 80, 134, 28);
		getContentPane().add(moneyInPot);
		moneyInPot.setColumns(10);
		
		JLabel lblIterations = new JLabel("Iterations");
		lblIterations.setBounds(99, 120, 61, 16);
		getContentPane().add(lblIterations);
		
		iterationField = new JTextField();
		iterationField.setText("1000000");
		iterationField.setBounds(61, 148, 134, 28);
		getContentPane().add(iterationField);
		iterationField.setColumns(10);
	}
	
	public static void setStackSize(double[] ss){
		stackSize.setText(""+ss[0]);
	}
	
	public static void setPotSize(double ps){
		moneyInPot.setText(""+ps);
	}
	
	public static double[] getStackSize(){
		double ss[] = { Double.parseDouble(stackSize.getText()), Double.parseDouble(stackSize.getText())}; 
		return ss;
	}
	
	public static double getMoneyInPot(){
		return Double.parseDouble(moneyInPot.getText());
	}
	public static int getIterations(){
		return Integer.parseInt(iterationField.getText());
	}

	
	
	
}
