package org.papeta.riversolver.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.tree.DefaultTreeModel;

import org.papeta.riversolver.algorithm.Serialize;

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
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				sb = new SaveBuild();
				sb.setRanges(sf.rf.getRange1(), sf.rf.getRange2());
				sb.setPotSize(OptionFrame.getMoneyInPot());
				sb.setStackSize(OptionFrame.getStackSize());
				DefaultTreeModel model = (DefaultTreeModel) sf.rf.treeSelector.models;
				sb.setTreeSelector(sf.rf.treeSelector);
				sb.setJTree(sf.rf.treeSelector.tree);
				System.out.println("japp "+sf.rf.treeSelector.getJTree().getRowCount());
				
				//sb.setJTree(sf.rf.treeSelector.tree);
			
				Serialize.serialize(sb, textField.getText(), "tree");
				
			}
		});
		btnSave.setBounds(6, 83, 117, 29);
		getContentPane().add(btnSave);
	}
}
