package org.test.algo;
/**
 * 
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 *
 */
public class MaximumSubarray {

	public static void main(String[] args) {
		System.out.println("Maximum sum: " + findMaximumSumInSubArray(
				new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
		
		System.out.println("Maximum sum: " + findMaximumSumInSubArray(
				new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4}));
		
		System.out.println("Maximum sum: " + findMaximumSumInSubArray(
				new int[]{2, -1, 3, -4, 1, -2, -1, 5, -4}));
	}

	static int findMaximumSumInSubArray(int[] array) {
		int maxSum = 0;
		int maxEndingAtI = 0;
		for (int atI : array) {
			maxEndingAtI = maxEndingAtI + atI;
			if (maxEndingAtI < 0) {
				maxEndingAtI = 0;
			}
			if (maxSum < maxEndingAtI) {
				maxSum = maxEndingAtI;
			}
		}
		return maxSum;
	}
}
