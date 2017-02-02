package org.papeta.riversolver.algorithm;

import java.util.Enumeration;

import javax.swing.JTree;


public class GameTreeBuilder {
	static TreeBuilderNode root;

	public static  void createGameTree(JTree tree){
		visitAllNodes(tree);

	}

	public static void visitAllNodes(JTree tree) {
		root = (TreeBuilderNode)tree.getModel().getRoot();
		root.setAction(new Action("root",0));
		root.stackSizes[0] = Action.gameAbstraction.stackSizes[0];
		root.stackSizes[1] = Action.gameAbstraction.stackSizes[1];

		buildTree(root);
	}
	public static void buildTree(TreeBuilderNode node) {

		
	

		if (node.getChildCount() >= 0) {

			for (Enumeration e=node.children(); e.hasMoreElements(); ) {				
				TreeBuilderNode n =(TreeBuilderNode) e.nextElement();
				int player = n.getParentNode().getAction().player;
				System.out.println("player: "+(1-player));
					/***CALL***/
				if(n.toString().equals("call")){
					
					n.setAction(new Action("call",(n.getParentNode().potSize + n.getParentNode().stackSizes[player]-n.getParentNode().stackSizes[1-player])/2, true, 1-player));	
					
					
					System.out.println("Stacksize for call - player facing bet: "+n.getParentNode().stackSizes[1-player]+" - betting player: "+n.getParentNode().stackSizes[player]);

					/***CHECK***/
				}else if (n.toString().equals("check")){
					
					n.setAction(new Action("check", 1-player));
					n.stackSizes[0] = root.stackSizes[0];
					n.stackSizes[1] = root.stackSizes[1];
					/*check-check showdown*/
					if(n.getParent().toString().equals("check")){
						n.setAction(new Action("check", Action.gameAbstraction.potSize/2, true, 1-player));
					}

					/***FOLD***/
				}else if(n.toString().equals("fold")){
					n.setAction(new Action("fold", Action.gameAbstraction.stackSizes[player]-n.getParentNode().stackSizes[player]+Action.gameAbstraction.potSize/2 , false, 1-player));	

					/***BET***/
				}else if(n.toString().contains("bet")){
					n.stackSizes[0] = n.getParentNode().stackSizes[0];
					n.stackSizes[1] = n.getParentNode().stackSizes[1];
					n.setAction(new Action("bet"+"("+n.betSize+")", 1-player));
					n.stackSizes[player] = n.stackSizes[player] - n.betSize;
					n.potSize = Action.gameAbstraction.potSize + (Action.gameAbstraction.stackSizes[0]-n.stackSizes[0])+(Action.gameAbstraction.stackSizes[1]-n.stackSizes[1]);
					System.out.println("Potsize: "+n.potSize);
				}


				/*Add node in tree*/
				n.getParentNode().getAction().addAction(n.getAction());
				System.out.println("node: "+n.toString()+" parent; "+n.getParent());
				buildTree(n);
			}


		}

	}

	public static Action getRootAction(){
		return root.getAction();
	}






}
