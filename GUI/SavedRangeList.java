package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JList;

import Solver.Range;
import Solver.Serialize;

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
		addMouseListener();
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
	/**
	 * Add mouse listener
	 * When click on object in List file - load saved range in RangeSelector
	 */
	public void addMouseListener(){
		MouseListener mouseListener = new MouseListener() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	

		           String selectedItem = (String) list.getSelectedValue();
		           try {
					range = (Range) Serialize.deserialize("range",selectedItem);
				} catch (ClassNotFoundException | IOException e1) {
					e1.printStackTrace();
				}
		          rs.setRange(range);
		          rs.repaint();

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
