package Solver;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JCheckBox;

import GUI.ResultViewer;

public class Serialize {
	
	public static void serialize(Object obj, String name, String type){
		
		 try{
	         FileOutputStream fileOut = new FileOutputStream(type+"/"+name+".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(obj);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in"+type+"/"+name+".ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static Object deserialize(String type,String fileName) throws IOException, ClassNotFoundException{
	
		        FileInputStream fis = new FileInputStream(type+"/"+fileName+".ser");
		        ObjectInputStream ois = new ObjectInputStream(fis);
		        Object obj = ois.readObject();
		        ois.close();
		        return obj;
		    
	}
	
}
