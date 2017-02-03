package org.papeta.riversolver.gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;

import org.papeta.riversolver.algorithm.Serialize;

public class LoadBuildList extends JFrame{
	JList list;
	RiverFrame rf;
	
	
	public LoadBuildList(RiverFrame rf){
		this.rf = rf;
		setBackground(Color.WHITE);

		
		list = new JList(SavedRangeList.getFilesInDirectory("src/main/resources/tree"));
		list.setSelectedIndex(1);
		list.setBackground(Color.white);
		
		add(list);
		addMouseListener();
	}

	/**
	 * Add mouse listener
	 * WHen click save build
	 */
	public void addMouseListener(){
		MouseListener mouseListener = new MouseListener() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	

		           String selectedItem = (String) list.getSelectedValue();
		           try {
		        	  
		        	   
					SaveBuild data = (SaveBuild) Serialize.deserialize("tree", selectedItem);
					
					
					//DefaultTreeModel model = (DefaultTreeModel) data.tree.getModel();
					
					//rf.treeSelector.tree.setModel(data.tree.getModel());
					
					//model.reload();
					rf.r1.rangeSelector.setRange(data.r1);
					rf.r2.rangeSelector.setRange(data.r2);
					OptionFrame.setPotSize(data.potSize);
					OptionFrame.setStackSize(data.stackSize);
					rf.treeSelector = data.jt;
					System.out.println(data.jt.tree.getRowCount());
					rf.treeSelector.repaint();
				
					
				
					
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        

		         }
		   
		        	
		        	
		        	
		        
		    }

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {

				
			}
		};
		list.addMouseListener(mouseListener);
	}


}
	

