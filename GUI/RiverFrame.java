package GUI;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import java.awt.FlowLayout;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.JButton;

import Solver.Range;
import Solver.RiverSolver;
import Solver.JTreeSelector;
import Solver.Serialize;


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
		
		super("d");
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
		
		/*
		 * MENU BAR**
		 */
		  JMenuBar menuBar = new JMenuBar();
		 // File Menu, F - Mnemonic
	    JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menuBar.add(fileMenu);
	    
		sf = new SaveFrame(rf);
	    JMenuItem saveMenuItem = new JMenuItem("Save build", KeyEvent.VK_N);
	    fileMenu.add(saveMenuItem);
	    sf = new SaveFrame(rf);
	    saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            
              sf.setSize(300, 200);
              sf.setVisible(true);
            }
        });
	   
	    JMenuItem loadMenuItem = new JMenuItem("Load build", KeyEvent.VK_N);
	    fileMenu.add(loadMenuItem);
	    loadMenuItem.addActionListener(new ActionListener() {
	    	LoadBuildList	 lbl = new LoadBuildList(rf);
            public void actionPerformed(ActionEvent ae) {
            lbl.setVisible(true);
            }
        });
	    
	    
	    setJMenuBar(menuBar);
	    
	    /*
	     * 
	     */

		this.setVisible(true);

		
		
	}
	
	
	public Range getRange1(){
		return r1.getRange();
	}
	
	public Range getRange2(){
		return r2.getRange();
	}
	
	/**
	 * Load all saves from SaveData object
	 * @param type
	 * @param name
	 */
	public void loadSaveBuild(String type, String name){
		try {
			SaveBuild data = (SaveBuild) Serialize.deserialize(type, name);
			treeSelector.tree.setModel(data.tree.getModel());
			
			treeSelector.repaint();
			r1.rangeSelector.setRange(data.r1);
			r2.rangeSelector.setRange(data.r1);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
