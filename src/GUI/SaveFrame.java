package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SaveFrame extends JFrame {
	private JTextField textField;
	RiverFrame rf;
	SaveFrame sf = this;
	SaveBuild sb;
	public SaveFrame(RiverFrame rf) {
		
		this.rf = rf;
		getContentPane().setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 6, 61, 16);
		getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(6, 34, 294, 37);
		getContentPane().add(textField);
		textField.setColumns(10);
		
	
	}
}
