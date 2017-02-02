package org.papeta.riversolver.algorithm;

import java.util.Arrays;

public class GameTreeCreator {
	enum ActionType {NONE, CHECK, BET, FOLD, CALL};
	public static final double COMMITMENT_TRESHOLD = 0.5;
	public static final double ZERO = 0.0000001;
	
	public static Action createGameTree(GameAbstraction gameAbstraction){
		Action.setGameAbstraction(gameAbstraction);
		return new Action("Root-", createSuccessors("Root-", gameAbstraction.potSize, gameAbstraction.stackSizes, gameAbstraction.betSizes, ActionType.NONE, 0), 0);
	}
	
	private static Action[] createSuccessors(String history, double potSize, double[] stackSizes, double[] betSizes, ActionType lastAction, int player){
		int index = 0;
		Action[] ret;

		//Find all valid bet sizes
		double[] validBets = getValidBets(potSize, stackSizes, betSizes, player);		

		//Check if one player is all in
		if(!(stackSizes[1-player]<ZERO)){
			ret = new Action[validBets.length+(lastAction==ActionType.BET ? 2 : 1)];
		} else {
			ret = new Action[(lastAction==ActionType.BET ? 2 : 1)];
		}

		//Decide what new nodes to add depending on last action
		if(lastAction == ActionType.CHECK){
			/* return (showdown, bet1, bet2, ...)
			 */
			ret[0]=new Action(history+"["+player+"]"+"check-", potSize/2, true, 1-player);	/*FIX showdown!!*/
			index++; 				/*Added check*/			
		}
		else if(lastAction == ActionType.NONE){
			/* return (check, bet1, bet2, ...)
			 */
			ret[0]=new Action(history+"["+player+"]"+"check-",createSuccessors(history+"["+player+"]"+"check-",Action.gameAbstraction.potSize, stackSizes, betSizes, ActionType.CHECK, 1-player), 1-player);
			index++; 				/*Added check*/
		}
		else if(lastAction == ActionType.BET){
			/* Last action is bet
			 * return (fold, call, raise1, raise2)
			 */
			ret[0]=new Action(history+"["+player+"]"+"fold-",Action.gameAbstraction.stackSizes[player]-stackSizes[player]+Action.gameAbstraction.potSize/2, false, 1-player);	/* fold */	
			ret[1]=new Action(history+"["+player+"]"+"call-",(potSize+stackSizes[player]-stackSizes[1-player])/2, true, 1-player);	/* showdown */

			index+=2;
		}
		else{
			System.out.println("SOMETHING BROKE :SS:S:S:S");
		}
		if(!(stackSizes[1-player]<ZERO)){
			for(double bet : validBets){
				double[] newStackSizes = {stackSizes[0]+(player==0?-bet:0), stackSizes[1]+(player==1?-bet:0)};
				ret[index]=new Action(history+"["+player+"]"+"bet("+bet+")-",createSuccessors(history+"["+player+"]"+"bet("+bet+")-",potSize+bet, newStackSizes, betSizes, ActionType.BET, 1-player), 1-player);
				index++;
			}
		}

		return ret;
	}

	private static double[] getValidBets(double potSize, double[] stackSizes, double[] betSizes, int player){
		boolean allInAdded = false;
		double[] tmp = new double[betSizes.length];
		int index=0;
		for(double bet : betSizes){
			if((bet * (stackSizes[player]-stackSizes[1-player]+potSize) + (stackSizes[player]-stackSizes[1-player]) ) > COMMITMENT_TRESHOLD*stackSizes[player]){
				if(allInAdded)
					continue;
				tmp[index]=stackSizes[player];
				allInAdded = true;
			} else {
				tmp[index]=bet * (stackSizes[player]-stackSizes[1-player]+potSize) + (stackSizes[player]-stackSizes[1-player]);
			}
			index++;
		}
		return Arrays.copyOf(tmp, index);
	}
}
