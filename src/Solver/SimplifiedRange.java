package Solver;


import java.util.Arrays;
import java.util.Random;

public class SimplifiedRange{
	public int[] idLUT;
	public double[] weightLUT;
	public int lutSize;
	public double normalizationWeight;
	private Random random = new Random();
	
	public int sampleHand(){
		double sample = random.nextDouble()*normalizationWeight;
		for(int i=0; i<lutSize; i++){
			sample-=weightLUT[i];
			if(sample<0){
				return i;
			}
		}
		System.out.println("fml");
		return -1;
	}
	
	public SimplifiedRange(Range range, int[] board){
		idLUT=new int[range.length()];
		weightLUT=new double[range.length()];
		normalizationWeight=0;
		int index=0;
		for(int[] hand : range){
			if(!Range.detectBlockers(hand, board)){
				idLUT[index]=Range.getID(hand[0], hand[1]);
				weightLUT[index]=range.getValue(Range.getID(hand[0], hand[1]));
				normalizationWeight+=weightLUT[index];
				index++;
			}
		}
		idLUT=Arrays.copyOf(idLUT, index);
		weightLUT=Arrays.copyOf(weightLUT, index);
		lutSize=index;
	}
}
