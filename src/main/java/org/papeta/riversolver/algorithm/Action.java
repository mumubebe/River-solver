package org.papeta.riversolver.algorithm;


import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

import org.papeta.riversolver.gui.GameTreeViewerNode;

public class Action {
	enum Type {NORMAL, FOLD, SHOWDOWN, TERMINAL};
	public static int informationSets=0;
	public static int totalActionNodes=0;
	public static GameAbstraction gameAbstraction;
	int player; //0=Player1 action, 1=Player2 action, 2=Chance
	double[][] regretSum;
	double[][] strategy;
	double[][] strategySum;
	Action[] actions;
	double value;  	/*only terminal nodes*/
	int nActions;
	int handRangeSize;
	static int infoSetCounter=0;
	Type type;
	String name;


	/**
	 * Normal  
	 * @param name
	 * @param actions
	 */
	public Action(String name, Action[] actions, int player){
		this.actions=Arrays.copyOf(actions, actions.length);
		this.name=name;
		this.player=player;
		type = Type.NORMAL;
		informationSets++;
		totalActionNodes++;
		handRangeSize = gameAbstraction.ranges[player].lutSize;
		nActions = this.actions.length;
		regretSum = new double[handRangeSize][nActions];
		strategy = new double[handRangeSize][nActions];
		strategySum = new double[handRangeSize][nActions];
	}

	public Action(String name, int player){
		this.name=name;
		this.player=player;
		type = Type.NORMAL;
		informationSets++;
		totalActionNodes++;
		handRangeSize = gameAbstraction.ranges[player].lutSize;
	}

	public Action(String name, double value, boolean showdown, int player){
		if(showdown){
			type = Type.SHOWDOWN;
		} else {
			type = Type.FOLD;
		}
		this.value=value;
		this.name=name;
		this.player=player;
		totalActionNodes++;
	}

	double[] getStrategy(double realizationWeight, int simplifiedHandID) {
		double normalizingSum = 0;
		for (int a = 0; a < nActions; a++) {
			strategy[simplifiedHandID][a] = regretSum[simplifiedHandID][a] > 0 ? regretSum[simplifiedHandID][a] : 0;
			normalizingSum += strategy[simplifiedHandID][a];
		}
		for (int a = 0; a < nActions; a++) {
			if (normalizingSum > 0)
				strategy[simplifiedHandID][a] /= normalizingSum;
			else
				strategy[simplifiedHandID][a] = 1.0 / nActions;
			strategySum[simplifiedHandID][a] += realizationWeight * strategy[simplifiedHandID][a];
		}
		return strategy[simplifiedHandID];
	}


	public double[] getAverageStrategy(int simplifiedHandID) {
		double[] avgStrategy = new double[nActions];
		double normalizingSum = 0;
		for (int a = 0; a < nActions; a++)
			normalizingSum += strategySum[simplifiedHandID][a];
		for (int a = 0; a < nActions; a++) 
			if (normalizingSum > 0)
				avgStrategy[a] = strategySum[simplifiedHandID][a] / normalizingSum;
			else
				avgStrategy[a] = 1.0 / nActions;
		return avgStrategy;
	}

	public void resetStrategySum(){
		for(int i=0; i<nActions; i++){
			for(int j=0; j<handRangeSize; j++){
				strategySum[j][i]=0;
			}
		}
	}

	public Action[] getSuccessors(){
		return actions;
	}

	public void printNode(){
		if(type==Type.NORMAL){
			System.out.println(name);
			for(int i=0; i<handRangeSize; i++){
				System.out.println(Range.cards[Range.firstHoleCard[gameAbstraction.ranges[player].idLUT[i]]]+Range.cards[Range.secondHoleCard[gameAbstraction.ranges[player].idLUT[i]]]+": "+Arrays.toString(getAverageStrategy(i)));
			}
			for(Action a : actions){
				infoSetCounter+=handRangeSize;
				a.printNode();
			}
		} else {
			System.out.println(name);
		}
		System.out.println("infosets: "+infoSetCounter);

	}

	public String toString() {
		return actions.toString(); //String.format("%4s: %s", infoSet, Arrays.toString(getAverageStrategy()));
	}

	public static void setGameAbstraction(GameAbstraction gameAbstraction){
		Action.gameAbstraction=gameAbstraction;
	}

	public boolean terminal(){
		return type==Type.TERMINAL;
	}

	public Action getTree(){
		return this;
	}

	public DefaultMutableTreeNode buildJTree(DefaultMutableTreeNode treeNode, Range[] handRanges){
		if(type==Type.NORMAL){
			for(int i=0; i<actions.length; i++){
				Range nodeRange = calculateNodeRange(handRanges[player], i);
				String[] tmp = actions[i].name.split("-");
				String actionName= tmp[tmp.length-1];
				double frequency = Range.getFrequency(nodeRange)/Range.getFrequency(handRanges[player]);
				GameTreeViewerNode gtvn = new GameTreeViewerNode(nodeRange, "("+Math.round(frequency*100)+"%)"+actionName);
				gtvn.setParent(treeNode);
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(gtvn);				
				//treeNode.add(childNode);
				treeNode.insert(childNode, treeNode.getChildCount());
				Range[] newRanges = new Range[2];
				if(player==0){
					newRanges[0]=new Range(nodeRange);
					newRanges[1]=new Range(handRanges[1]);
				} else {
					newRanges[0]=new Range(handRanges[0]);
					newRanges[1]=new Range(nodeRange);					
				}
				actions[i].buildJTree(childNode, newRanges);
			}
		}
		return treeNode;
	}

	public void addAction(Action action){
		if(actions==null){
			actions = new Action[0];
		}
		Action[] newAction = new Action[actions.length+1];
		for(int i=0; i<actions.length; i++){
			newAction[i]=actions[i];
		}
		newAction[actions.length]=action;
		actions=newAction;
	
		nActions = this.actions.length;
	
		regretSum = new double[handRangeSize][nActions];
		strategy = new double[handRangeSize][nActions];
		strategySum = new double[handRangeSize][nActions];
	}

	private Range calculateNodeRange(Range enteringRange, int actionID){
		Range returnRange = new Range(enteringRange);
		for(int i=0; i<handRangeSize; i++){
			double[] weights = getAverageStrategy(i);
			returnRange.setValue(	gameAbstraction.ranges[player].idLUT[i],
					returnRange.getValue(gameAbstraction.ranges[player].idLUT[i])*weights[actionID]);
		}
		return returnRange;
	}
}
