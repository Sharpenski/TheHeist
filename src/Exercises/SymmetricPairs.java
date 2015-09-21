package Exercises;

import java.util.HashMap;

public class SymmetricPairs {
	
	public static void findSym(int[][] pairs) {
		HashMap<Integer,Integer> temp = new HashMap<Integer,Integer>();

		for(int i = 0; i < pairs.length; i++) {
			//System.out.println(temp);
			
			int first = pairs[i][0];
			int sec = pairs[i][1];
			
			//Remember we're looking for symmetric pairs so key will be the second number
			Integer val = temp.get(sec);
			//System.out.println(val);
			
			if(val != null && val == first) {
				System.out.println("(" + sec + ", " + first + ")");
			} else {
				temp.put(first, sec);
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] pairs = {{1,2},{2,1},{1,2},{3,4},{4,3}};
		SymmetricPairs.findSym(pairs);
	}

}
