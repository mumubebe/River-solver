package GUI;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.io.Serializable;
import javax.swing.JButton;
import Solver.Range;
import Solver.RiverSolver;
import Solver.JTreeSelector;



public class RiverFrame extends JFrame implements Serializable{
	RiverFrame rf = this;
	RangeSelectFrame r1 = new RangeSelectFrame();
	RangeSelectFrame r2 = new RangeSelectFrame();
	BoardSelectorFrame b = new BoardSelectorFrame();
	OptionFrame of = new OptionFrame();
	JScrollPane treeView;
	JTree tree;
	ResultViewer rv;
	JTreeSelector treeSelector;
	public SaveFrame sf;

	
	
	RiverSolver solve;
	public RiverFrame(){
		
		super("River-solver 0.1");
		this.setSize(861, 633);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		drawGUI();
		
				
	}
	
	public void drawGUI(){
		
		
		
		JButton btnOopRange = new JButton("OOP Range");
		btnOopRange.setBounds(118, 5, 113, 29);
		btnOopRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				r1.getSelectedRange();
				r1.setVisible(true);
			
			
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnOopRange);
		
		JButton btnIpRange = new JButton("IP RANGE");
		btnIpRange.setBounds(236, 5, 102, 29);
		btnIpRange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				r2.getSelectedRange();
				r2.setVisible(true);
			
				 
			}
		});
		getContentPane().add(btnIpRange);
		
		JButton btnBoard = new JButton("BOARD");
		btnBoard.setBounds(343, 5, 88, 29);
		btnBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				b.setVisible(true);
				 
			}
		});
		getContentPane().add(btnBoard);
		

	
		
		JButton btnOptions = new JButton("OPTIONS");
		btnOptions.setBounds(436, 5, 100, 29);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				of.setVisible(true);
			}
		});
		getContentPane().add(btnOptions);
		
		
		treeSelector = new JTreeSelector(this);
		treeSelector.setBounds(6, 46, 849, 559);
		getContentPane().add(treeSelector);
		
	

		this.setVisible(true);

		
		
	}
	
	
	public Range getRange1(){
		return r1.getRange();
	}
	
	public Range getRange2(){
		return r2.getRange();
	}
	





}
