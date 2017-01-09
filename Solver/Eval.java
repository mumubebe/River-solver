package Solver;


import java.io.*;
import java.util.Arrays;

/**
 * 
 * 
 * http://forumserver.twoplustwo.com/showthreaded.php?Cat=0&Number=9765615&page=
 * 0&vc=1
 * http://forumserver.twoplustwo.com/showthreaded.php?Cat=0&Number=9774228
 * &page=0&vc=1
 * 
 * To evaluate 6 card hand make the last card a zero.
 * 
 * 
 * 
 */
public class Eval {

	private static final int HAND_RANKS_SIZE = 32487834;

	private static final int[] cardConversion={
		2, 6, 10, 14, 18, 22, 26, 30, 34, 38, 42, 46, 50,  	//Hearts
		1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49,	//Diamonds
		0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48,	//Spades
		3, 7, 11, 15, 19, 23, 27, 31, 35, 39, 43, 47, 51,  	//Hearts
	};

	public static int[] handRanks = new int[HAND_RANKS_SIZE]; // array to hold
																// hand rank
																// lookup table

	private static final String HAND_RANKS_FILE = "handRanks.ser";
	static int[] offsets = new int[] { 0, 1277, 4137, 4995, 5853, 5863, 7140,
			7296, 7452 };

	public static void initialize() {
		try {
			System.out.println("Loading evaluation tables ...");
			File f = new File(HAND_RANKS_FILE);
			if (!f.exists()) {
				System.out.println("Evaluation tables do not exist");
			}
			long t = System.currentTimeMillis();
			ObjectInputStream s = new ObjectInputStream(new FileInputStream(
					HAND_RANKS_FILE));
			handRanks = (int[]) s.readObject();
			t = System.currentTimeMillis() - t;
			System.out.println("Evaluation tables loaded in " + t / 1000.0
					+ " seconds");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getRank(int[] cards) {
		for(int i=0;i<cards.length;i++){
			cards[i]=cardConversion[cards[i]];
		}
		int rank = 53;
		for (int i = 0; i < 7; i++) {
			int c = cards[i] + 1;
			rank = handRanks[c + rank];
		}
		int type = (rank >>> 12) - 1;
		rank = rank & 0xFFF;

		return offsets[type] + rank - 1;
	}

	public static int getRank(int c1, int c2, int c3, int c4, int c5, int c6, int c7) {
		c1=cardConversion[c1];
		c2=cardConversion[c2];
		c3=cardConversion[c3];
		c4=cardConversion[c4];
		c5=cardConversion[c5];
		c6=cardConversion[c6];
		c7=cardConversion[c7];

		int rank = 53;
		int c = c1 + 1;
		rank = handRanks[c + rank];
		c = c2 + 1;
		rank = handRanks[c + rank];
		c = c3 + 1;
		rank = handRanks[c + rank];
		c = c4 + 1;
		rank = handRanks[c + rank];
		c = c5 + 1;
		rank = handRanks[c + rank];
		c = c6 + 1;
		rank = handRanks[c + rank];
		c = c7 + 1;
		rank = handRanks[c + rank];

		int type = (rank >>> 12) - 1;
		rank = rank & 0xFFF;

		return offsets[type] + rank - 1;
	}
}

