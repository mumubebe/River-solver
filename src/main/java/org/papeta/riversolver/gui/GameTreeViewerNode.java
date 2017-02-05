package org.papeta.riversolver.gui;

import javax.swing.tree.DefaultMutableTreeNode;

import org.papeta.riversolver.algorithm.Range;

public class GameTreeViewerNode extends DefaultMutableTreeNode{
	private Range range;
	public Range relativeRange;
	private String name;
	
	public GameTreeViewerNode(Range range, String name){
		this.range=range;
		this.name=name;
	}
	
	public String toString(){
		return name;
	}
	
	public Range getRange(){
		return range;
	}
	
	/**
	 * Get the relative weight of the range
	 * @return Range
	 */
	public Range getRelativeRange(){
		/*if range already has been set*/
		if(!(relativeRange==null)){
			return relativeRange;
		}
		/*if the node is root or child to root*/
		if(!relativeRangePossible()){
			
			return getRange();
		}
		
		/*create range object*/
		relativeRange = new Range();
		/*set new relative weight*/
		for(int i=0; i<1326; i++){
			/*remove micro weights*/
			if(this.getParentNode().getParentNode().getRange().getValue(i)>0.02){
			relativeRange.setValue(i, getRange().getValue(i)  /  this.getParentNode().getParentNode().getRange().getValue(i));
			}
			
		}
		System.out.println("Return relative range");
		return relativeRange;
	}
	
	/**
	 * Get parent GameTreeViewerNode
	 * @return GameTreeViewerNode
	 */
	/*fixa denna fulfix*/
	public GameTreeViewerNode getParentNode(){
		DefaultMutableTreeNode node1 = (DefaultMutableTreeNode) getParent();
		GameTreeViewerNode gtvn = (GameTreeViewerNode)node1.getUserObject();
		return gtvn;
		
	}
	
	public boolean relativeRangePossible(){
		if(this.getParent()==null || this.getParent().toString().contains("oop") || this.getParent().getParent().toString().contains("oop")){
			
			return false;
		}
		return true;
	}

	
}