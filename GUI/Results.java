package GUI;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JScrollPane;

public class Results extends JFrame {
	public Results() {
		getContentPane().setLayout(null);
		
		RangeSelector rangeSelector = new RangeSelector();
		rangeSelector.setBounds(227, 6, 416, 403);
		getContentPane().add(rangeSelector);
		
		SuitSelector suiteSelector = new SuitSelector((RangeSelector) null);
		suiteSelector.setBounds(434, 421, 209, 33);
		getContentPane().add(suiteSelector);
	}
}
