package Solver;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import GUI.BoardSelectorFrame;
import GUI.OptionFrame;
import GUI.ResultViewer;
import GUI.RiverFrame;
import GUI.SaveBuild;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class JTreeSelector extends JPanel implements Runnable, java.io.Serializable {
	private JTextField tfBet;
	public RiverSolver rs;
	public JTree tree;
	public JScrollPane treeView;
	public RiverFrame rf;
	public JTreeSelector jt = this;
	public JLabel progress;
	public DefaultTreeModel models;

	private JTextField editTf;
	public JTreeSelector( final RiverFrame rf) {
		this.rf = rf;
	
		rs = new RiverSolver(this);
		setLayout(null);
		setSize(870, 555);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(339, 6, 504, 466);
		add(scrollPane);
		tree = new JTree();
		
		
		scrollPane.setViewportView(tree);
		tree.setModel(new DefaultTreeModel(
			new TreeBuilderNode("root") {
				{
					TreeBuilderNode node_1;
					node_1 = new TreeBuilderNode("check");
						node_1.add(new TreeBuilderNode("check"));
					add(node_1);
				}
			}
		));

		tree.addTreeSelectionListener(new TreeSelectionListener() {		
			@Override
			public void valueChanged(TreeSelectionEvent e) {

				TreeBuilderNode node = (TreeBuilderNode) tree.getLastSelectedPathComponent();
				if(node==null){
					return;
				}
				if(node.toString().contains("bet")){
					editTf.setText(""+node.betSize);
				}else{
					editTf.setText("");
				}

			}
		});

		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				TreeBuilderNode selectedNode = (TreeBuilderNode) tree.getLastSelectedPathComponent();
				//cap the betsize to the stacksize
				double size = Double.parseDouble(tfBet.getText()) > OptionFrame.getStackSize()[0] ? OptionFrame.getStackSize()[0]:Double.parseDouble(tfBet.getText());
				
				TreeBuilderNode newBet = new TreeBuilderNode("bet("+size+")");
				newBet.betSize = size;

				if(selectedNode!=null){
					if(!tfBet.getText().trim().equals("")){
						
						model.insertNodeInto(newBet, selectedNode, selectedNode.getChildCount());
						
						System.out.println(tree.getRowCount());
				
						model.insertNodeInto(new TreeBuilderNode("call"), newBet, newBet.getChildCount());
						model.insertNodeInto(new TreeBuilderNode("fold"), newBet, newBet.getChildCount());
						model.reload();
					
						
						
					}else{
						System.out.println("Amount missing");
					}
				}else{
					System.out.println("Select a node");
				}				
			}

		});
		btnNewButton.setBounds(252, 4, 75, 29);
		add(btnNewButton);

		tfBet = new JTextField();
		tfBet.setBounds(111, 5, 134, 28);
		add(tfBet);
		tfBet.setColumns(10);

		JLabel lblBet = new JLabel("Bet");
		lblBet.setBounds(38, 7, 61, 16);
		add(lblBet);

		JButton btnNewButton_1 = new JButton("Solve");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Thread thread = new Thread(jt);
				thread.start();

			}
		});
		btnNewButton_1.setBounds(726, 484, 117, 29);
		add(btnNewButton_1);




		JButton removeBtn = new JButton("X");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				TreeBuilderNode selectedNode = (TreeBuilderNode) tree.getLastSelectedPathComponent();
				if(selectedNode==null){
					return;
				}
				if(selectedNode.toString().contains("bet")){

					selectedNode.removeFromParent();
					model.reload();
				}
			}
		});
		removeBtn.setBounds(252, 91, 75, 29);
		add(removeBtn);

		editTf = new JTextField();
		editTf.setBounds(111, 45, 134, 28);
		add(editTf);
		editTf.setColumns(10);

		JLabel lblEdit = new JLabel("Edit");
		lblEdit.setBounds(38, 51, 61, 16);
		add(lblEdit);

		JButton editBtn = new JButton("√");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
				TreeBuilderNode selectedNode = (TreeBuilderNode) tree.getLastSelectedPathComponent();
				if(selectedNode.toString().contains("bet")){
					selectedNode.setUserObject("bet("+editTf.getText()+")");
					model.nodeChanged(selectedNode);
				}
			}
		});
		editBtn.setBounds(252, 45, 75, 29);
		add(editBtn);

		JLabel lblRemove = new JLabel("Remove");
		lblRemove.setBounds(38, 96, 61, 16);
		add(lblRemove);
		
			progress = new JLabel("0%");
		progress.setBounds(803, 525, 61, 16);
		add(progress);
	}


	@Override
	public void run() {
		double[] tempBetSize = {1};
		rs.initialize(rf.getRange1(), rf.getRange2(), BoardSelectorFrame.getBoard(), OptionFrame.getStackSize(), tempBetSize, OptionFrame.getMoneyInPot(), OptionFrame.getIterations());
		System.out.println("LETS ROOLLL");
		GameTreeBuilder.createGameTree(tree);
		rs.root = GameTreeBuilder.getRootAction();
		rs.train(OptionFrame.getIterations());
		rs.root.printNode();
		
		prepareResultView();


	}
	
	public void prepareResultView(){
		ResultViewer rv = new ResultViewer();
		DefaultTreeModel model = (DefaultTreeModel) rs.getJTree().getModel();
		rv.tree.setModel(model);
		model.reload();
		rv.evLabel.setText(rs.ev);
		rv.setVisible(true);
		rv.setSize(890, 590);
		
	}
	
	public JTree getJTree(){
		JTree returnTree = new JTree(tree.getModel());
		System.out.println(tree.getRowCount());
		return returnTree;
	}



}
