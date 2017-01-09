package GUI;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import Solver.Serialize;

public class ResultViewer extends JFrame implements java.io.Serializable {
	public JTree tree;
	public JCheckBox relativeBox;
	public RangeSelector rangeSelector;
	public JLabel evLabel;
	public SuitSelector suiteSelector;
	public JScrollPane scrollPane;
	public GameTreeViewerNode currentNode;
	public JButton btnSaveTree;
	public ResultViewer rv = this;
	private JTextField saveRangeField;
	
	public ResultViewer() {
		getContentPane().setLayout(null);
		
		
		/*
		 * Range selector
		 */
		rangeSelector = new RangeSelector();
		rangeSelector.setBounds(309, 6, 571, 473);
		getContentPane().add(rangeSelector);
		
		/*
		 * JTree
		 */
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 291, 473);
		getContentPane().add(scrollPane);
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent e) {
				
							
				DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
				if (node1 == null) return;
				currentNode = (GameTreeViewerNode)node1.getUserObject();
				
				
				if(relativeBox.isSelected()){
					
					
					rangeSelector.setRange(currentNode.getRelativeRange());
				}else{
					rangeSelector.setRange(currentNode.getRange());
				}
				
				
				rangeSelector.repaint();

			}
		});
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

		
		/*
		 * EV-label
		 */
		
		evLabel = new JLabel("ev");
		evLabel.setBounds(6, 494, 112, 16);
		getContentPane().add(evLabel);
		
		/*
		 * SuitSelector
		 */
		
		suiteSelector = new SuitSelector(rangeSelector);
		suiteSelector.setBounds(671, 491, 209, 33);
		getContentPane().add(suiteSelector);
		
		
		/*
		 * Relative hand weight checkbox
		 */
		
		relativeBox = new JCheckBox("Relative hand weight");
		relativeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(relativeBox.isSelected()){
					
					rangeSelector.setRange(currentNode.getRange());
					
					
				}else{
					
			
					rangeSelector.setRange(currentNode.getRelativeRange());
					
				}
				rangeSelector.repaint();
			}
		});
		relativeBox.setBounds(691, 536, 189, 23);
		getContentPane().add(relativeBox);
		
		btnSaveTree = new JButton("Save range");
		btnSaveTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(saveRangeField.equals("")){ return;
			}
				Serialize.serialize(rangeSelector.getRange(), saveRangeField.getText(), "range");
			
				
			}
		});
		btnSaveTree.setBounds(0, 511, 118, 23);
		getContentPane().add(btnSaveTree);
		
		saveRangeField = new JTextField();
		saveRangeField.setBounds(0, 536, 118, 28);
		getContentPane().add(saveRangeField);
		saveRangeField.setColumns(10);
	}

	
}
