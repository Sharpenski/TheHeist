package Exercises;

import java.util.Arrays;

public class G4G1 {
	
	static Integer[][] matrix = { {0,1,0,1,0}, 
							    {0,1,0,1,1}, 
								{1,1,0,1,0}
						    };
	static final int R = 3, C = 5;
	static Integer[][] hist = new Integer[R+1][C+1]; //slightly bigger to allow for swapping
	
	public static int columnSwapper() {
		for(int i = 0; i < C; i++) {
			hist[0][i] = matrix[0][i];
			for(int j = 1; j < R; j++) 
				hist[j][i] = (matrix[j][i] == 0) ? 0: hist[j-1][i]+1;
		}
		
		for(int i = 0; i < R; i++) {
			int[] count = new int[R+1];
			count[0] = 0;
			for(int j = 0; j < C; j++) {
				count[hist[i][j]]++;
			}

			int col_no = 0;
			for(int j = R; j >= 0; j--) {
				if(count[j] > 0) {
					for(int k = 0; k < count[j]; k++) {
						hist[i][col_no] = j;
						col_no++;
					}
				}
			}
		}
		
		int curr_area = 0, max_area = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				curr_area = (j+1) * hist[i][j];
				if(curr_area > max_area) {
					max_area = curr_area;
				}
			}
		}
		
		return max_area;
	}
	
	public static <T> void printArrayofArrays(T[][] arrays) {
		int i = 0;
		for(T[] array : arrays) {
			System.out.println("Row " + i + ": " + Arrays.toString(array));
			i++;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		System.out.println(G4G1.columnSwapper());
	}

}
