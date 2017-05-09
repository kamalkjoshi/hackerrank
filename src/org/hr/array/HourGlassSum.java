package org.hr.array;

import java.util.Scanner;

public class HourGlassSum {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		int[][] array = new int[6][6];
		Scanner scanner = new Scanner(System.in);
		for (int row = 0; row < 6; row++) {
			String line = scanner.nextLine();
			String[] cols = line.split(" ");
			for (int col = 0; col < 6; col++) {
				int value = 0;
				try {
					value = Integer.parseInt(cols[col]);
				} catch (Exception e) {
					System.err.println(e);
				}
				array[row][col] = value;
			}
		}		
		System.out.println(maxHourGlassSum(array));
		scanner.close();
	}

	static int maxHourGlassSum(int[][] array) {
		int maxsum = Integer.MIN_VALUE;
		// We are in 6by6 matrix so limit would be at 4
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				//@formatter:off
				int sum = array[row][col] + array[row][col + 1]	+ array[row][col + 2] 
						  + array[row + 1][col + 1]
						+ array[row + 2][col] + array[row + 2][col + 1]	+ array[row + 2][col + 2];
				//@formatter:on
				if (sum > maxsum) {
					maxsum = sum;
				}
			}
		}
		return maxsum;
	}
}
