package Solver;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeBuilderNode extends DefaultMutableTreeNode {
	Action action;
	double[] stackSizes = new double[2];
	double potSize;
	double betSize;
	
	public TreeBuilderNode(String name){
		super(name);
		
	}
	
	
	public Action getAction(){
		return action;
	}
	
	public void setAction(Action action){
		this.action = action;
	}
	
	public TreeBuilderNode getParentNode(){
		return (TreeBuilderNode)this.getParent();
	}
	public boolean hasAction(){
		if(!(action==null)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
}
