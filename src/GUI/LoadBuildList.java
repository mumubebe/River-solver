package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.tree.DefaultTreeModel;

import Solver.JTreeSelector;

public class LoadBuildList extends JFrame{
	JList list;
	RiverFrame rf;
	
	
	public LoadBuildList(RiverFrame rf){
		this.rf = rf;
		setBackground(Color.WHITE);

		
		list = new JList(SavedRangeList.getFilesInDirectory("tree"));
		list.setSelectedIndex(1);
		list.setBackground(Color.white);
		
		add(list);
		}

	
	


	




	
	}



	

