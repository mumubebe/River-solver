package org.papeta.riversolver.gui;

import java.io.Serializable;

import javax.swing.JTree;

import org.papeta.riversolver.algorithm.JTreeSelector;
import org.papeta.riversolver.algorithm.Range;

public class SaveBuild implements Serializable {
	
	
	Range r1;
	Range r2;
	double[] stackSize;
	double potSize;
	String board;
	JTree tree;
	JTreeSelector jt;
	
	

	public void setRanges(Range r1, Range r2){
		this.r1 = r1;
		this.r2 = r2;
	}
	
	public void setStackSize(double[] stackSize){
		this.stackSize = stackSize;
	}
	
	public void setPotSize(double potSize){
		this.potSize = potSize;
	}
	
	public void setBoard(String board){
		this.board = board;
	}
	
	public void setJTree(JTree tree){
		this.tree = tree;
		System.out.println(this.tree.getRowCount());
	}
	
	public void setTreeSelector(JTreeSelector jt){
		this.jt = jt;
	}
	
	
	
	
}
