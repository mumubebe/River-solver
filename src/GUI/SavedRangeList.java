package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JList;

import Solver.Range;

public class SavedRangeList extends JPanel {
	RangeSelector rs;
	JList list;
	Range range;
	

	
	
	public SavedRangeList(RangeSelector rs) {
		setBackground(Color.WHITE);
		this.rs = rs;
		
		list = new JList(getFilesInDirectory("range"));
		list.setSelectedIndex(1);
		list.setBackground(Color.white);
		
		add(list);
	
	}




	/**
	 * Get files from directory and add it in list
	 */
	public static String[] getFilesInDirectory(String type){
		  String path = type+"/"; 		  
		 
		  File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		  String files[] = new String[listOfFiles.length];
		  for (int i = 0; i < listOfFiles.length; i++) 
		  {
			  
		   if (listOfFiles[i].isFile()) 
		   {
		   files[i] = listOfFiles[i].getName();
		   /*remove .ser*/
		   files[i] = files[i].substring(0, files[i].length()-4);
		      }
		  }
		  return files;
	}
	

}
