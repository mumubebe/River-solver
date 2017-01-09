package Solver;


import java.util.Arrays;
import java.util.Random;

public class GameAbstraction {
	public double[] stackSizes;
	public double potSize;
	public double[] betSizes;
	int[] board;
	//Range[] ranges;
	SimplifiedRange[] ranges;
	Random random;
	
	public GameAbstraction(double potSize, double[] stackSizes, double[] betSizes, int[] board, SimplifiedRange[] ranges){
		this.stackSizes=stackSizes;
		this.potSize=potSize;
		this.betSizes=betSizes;
		
		this.board=board;
		this.ranges=ranges;
		random = new Random();
	}
	
	public int[] sampleHands2(){
		int[] hands = new int[2];
		int sampleFirst;
		do{
			sampleFirst = random.nextInt(2);
			hands[sampleFirst] = random.nextInt(ranges[sampleFirst].lutSize);
			hands[1-sampleFirst] = random.nextInt(ranges[1-sampleFirst].lutSize);
		} while (Range.detectBlockers(ranges[sampleFirst].idLUT[hands[sampleFirst]], ranges[1-sampleFirst].idLUT[hands[1-sampleFirst]]));
		return hands;
	}
	
	public int[] sampleHands(){
		int[] hands = new int[2];
		int sampleFirst = random.nextInt(2);
		do{
			hands[sampleFirst]=ranges[sampleFirst].sampleHand();
			hands[1-sampleFirst]=ranges[1-sampleFirst].sampleHand();
		} while (Range.detectBlockers(ranges[sampleFirst].idLUT[hands[sampleFirst]], ranges[1-sampleFirst].idLUT[hands[1-sampleFirst]]));
		return hands;
	}
	
	public int[] getBoard(){
		return board;
	}
}
