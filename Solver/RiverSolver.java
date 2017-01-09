package Solver;


import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import GUI.GameTreeViewerNode;
import GUI.RiverFrame;

public class RiverSolver implements Serializable {
	Random random = new Random();
	GameAbstraction gameAbstraction;
	Action root;
	static RiverFrame mf;
	Range[] ranges;
	int counter = 10000;
	JTreeSelector jt;
	String ev;
	JTree tree;
	
	public static void main(String args[]) throws ClassNotFoundException, IOException{
		mf = new RiverFrame();
		
		
		
		mf.setVisible(true);
		mf.drawGUI();
		

	}
	public RiverSolver(JTreeSelector jt){
		this.jt = jt;
	}
	
    public void train(int iterations) {
        double util = 0;
        for (int i = 0; i < iterations; i++) {
/*        	if(i == iterations/1000){
                //resetStrategySum();
        	}*/
            int[] hands = gameAbstraction.sampleHands();

            util += cfr(hands, root, 1, 1, 0);
            
            if(i==counter || i+1==iterations){
            	counter+=10000;
            	jt.progress.setText(""+((double)(i+1)/(double)iterations)*100+"%");
            }
        }
        counter=10000;
        System.out.println("Average game value: " + util / iterations);
     
        ev = "ev: "+util/iterations;
    }
    
    public double cfr(int[] hands, Action action, double p0, double p1, int player){
        int opponent = 1 - player;
        if(action.type == Action.Type.FOLD){
        	return action.value;
        } else if (action.type == Action.Type.SHOWDOWN){
        	//No comments
        	return Eval.getRank(gameAbstraction.board[0],
        						gameAbstraction.board[1],
        						gameAbstraction.board[2],
        						gameAbstraction.board[3],
        						gameAbstraction.board[4],
        						Range.firstHoleCard[gameAbstraction.ranges[player].idLUT[hands[player]]],
        						Range.secondHoleCard[gameAbstraction.ranges[player].idLUT[hands[player]]])
        						>
        						Eval.getRank(gameAbstraction.board[0],
								gameAbstraction.board[1],
								gameAbstraction.board[2],
    							gameAbstraction.board[3],
    							gameAbstraction.board[4],
    							Range.firstHoleCard[gameAbstraction.ranges[opponent].idLUT[hands[opponent]]],
    							Range.secondHoleCard[gameAbstraction.ranges[opponent].idLUT[hands[opponent]]])
    							?
    							action.value : -action.value;
        }
        double[] strategy = action.getStrategy(player == 0 ? p0 : p1, hands[player]);
        double[] util = new double[action.nActions];
        double nodeUtil = 0;
        Action[] successors = action.getSuccessors();
        for (int a = 0; a < action.nActions; a++) {
            util[a] = player == 0 
                ? - cfr(hands, successors[a], p0 * strategy[a], p1, opponent)
                : - cfr(hands, successors[a], p0, p1 * strategy[a], opponent);
            nodeUtil += strategy[a] * util[a];
        }

        for (int a = 0; a < action.nActions; a++) {
            double regret = util[a] - nodeUtil;
            action.regretSum[hands[player]][a] += (player == 0 ? p1 : p0) * regret;
        }

        return nodeUtil;
    }
	
    public void initialize(Range r1, Range r2, String flop,double[] stackSizes,double[] betSizes,double potSize,int iterations ){
    	
    	
		ranges = new Range[2];
		ranges[0]=r1; ranges[1]=r2;
		Eval.initialize();
		
		int[] board = Range.handToInt(flop);
		SimplifiedRange[] ranges = {new SimplifiedRange(r1, board), new SimplifiedRange(r2, board)};
		gameAbstraction = new GameAbstraction(potSize, stackSizes, betSizes, board, ranges);
		Action.setGameAbstraction(gameAbstraction);
		System.out.println("Iterations: "+iterations);

	}
	
	public JTree getJTree(){
		

		Range[] unblockedRanges = {Range.detectBlockers(ranges[0], gameAbstraction.getBoard()), Range.detectBlockers(ranges[1], gameAbstraction.getBoard()) };
		DefaultMutableTreeNode treeRoot = new DefaultMutableTreeNode(new GameTreeViewerNode(unblockedRanges[0], "oop"));
		treeRoot = root.buildJTree(treeRoot, unblockedRanges);
		DefaultTreeModel model = new DefaultTreeModel(treeRoot);
		JTree returnTree = new JTree(model);
		return returnTree;
	}
}
