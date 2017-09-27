package GUI;
import javax.swing.JFrame;

import Solver.Range;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.text.DefaultEditorKit;


public class RangeSelectFrame extends JFrame {
	RangeSelector rangeSelector = new RangeSelector(this);
	SuitSelector suitSelector = new SuitSelector(rangeSelector);

	JLabel comboLabel;
	private JTextField textField;
	
	public RangeSelectFrame() {

		
		setSize(650, 593);
		getContentPane().setLayout(null);
		
		
		rangeSelector.setBounds(6, 6, 575, 488);
		getContentPane().add(rangeSelector);
		
		
		suitSelector.setBounds(360, 506, 209, 33);
		getContentPane().add(suitSelector);
		
	
	
		
		comboLabel = new JLabel("");
		comboLabel.setBounds(16, 494, 182, 16);
		getContentPane().add(comboLabel);
		
		InputMap im = (InputMap) UIManager.get("TextField.focusInputMap");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.META_DOWN_MASK), DefaultEditorKit.copyAction);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.META_DOWN_MASK), DefaultEditorKit.pasteAction);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.META_DOWN_MASK), DefaultEditorKit.cutAction);
		
	
	}
	
	public Range getRange(){
		
		return rangeSelector.getRange();
	}
	
	public void getSelectedRange(){
		rangeSelector.setRange(getRange());
		
	}
}
