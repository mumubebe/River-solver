package org.papeta.riversolver.gui;

import java.awt.event.KeyEvent;

import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;

import org.papeta.riversolver.algorithm.Range;


public class RangeSelectFrame extends JFrame {
	RangeSelector rangeSelector = new RangeSelector(this);
	SuitSelector suitSelector = new SuitSelector(rangeSelector);

	JLabel comboLabel;
	private JTextField textField;
	
	public RangeSelectFrame() {

		
		setSize(751, 593);
		getContentPane().setLayout(null);
		
		
		rangeSelector.setBounds(6, 6, 575, 488);
		getContentPane().add(rangeSelector);
		
		
		suitSelector.setBounds(360, 506, 209, 33);
		getContentPane().add(suitSelector);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(593, 12, 135, 482);
		getContentPane().add(scrollPane);
	
		
		comboLabel = new JLabel("");
		comboLabel.setBounds(16, 494, 182, 16);
		getContentPane().add(comboLabel);
		
		InputMap im = (InputMap) UIManager.get("TextField.focusInputMap");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), DefaultEditorKit.copyAction);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.META_DOWN_MASK), DefaultEditorKit.pasteAction);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.META_DOWN_MASK), DefaultEditorKit.cutAction);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				    addRange();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    addRange();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    addRange();
				  }

				  public void addRange() {
					 if(textField.getText().length() == 0){
						 getRange().setAllValuesZero();
						 rangeSelector.setButtonAll();
					 }
					 if(textField.getText().length()%5==4 || textField.getText().length() == 4 ){
						 getRange().setAllValuesZero();
					     getRange().setTextRange(textField.getText());
					     rangeSelector.setButtonAll();
					 }
					
				  }
				});
		textField.setBounds(6, 506, 342, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	public Range getRange(){
		
		return rangeSelector.getRange();
	}
	
	public void getSelectedRange(){
		rangeSelector.setRange(getRange());
		
	}
}
