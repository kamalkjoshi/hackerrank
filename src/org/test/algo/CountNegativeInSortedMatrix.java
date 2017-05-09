package org.test.algo;

/**
 * Count Negative Numbers in a Column-Wise and Row-Wise Sorted Matrix
 */
public class CountNegativeInSortedMatrix {

	public static void main(String[] args) {
		testCountNegativeInSortedMatrix();
	}

	private static void testCountNegativeInSortedMatrix() {
		//@formatter:off
		int[][] sortedArray = new int[][]{
				{-3, -2, -1, 1}, 
				{-2,  2,  3, 4},
				{ 4,  5,  7, 8}
			};
		//@formatter:on
		System.out.println(countNegativeInSortedMatrix(sortedArray));

		//@formatter:off
		sortedArray = new int[][]{
				{1, 2, 3, 4}, 
				{2, 3, 4, 5},
				{3, 4, 5, 6}
			};
		//@formatter:on
		System.out.println(countNegativeInSortedMatrix(sortedArray));

		//@formatter:off
		sortedArray = new int[][]{
				{-6,-5,-4,-3}, 
				{-5,-4,-3,-2},
				{-4,-3,-2,-1}
			};
		//@formatter:on
		System.out.println(countNegativeInSortedMatrix(sortedArray));
	}

	static int countNegativeInSortedMatrix(int[][] array) {
		int count = 0;
		int m = array[0].length;
		int n = array.length;
		int i = 0, j = m - 1;
		while (true) {
			// boundary
			if (i >= n || i < 0 || j < 0 || j >= m) {
				break;
			}
			int x = array[i][j]; // right top
			if (x < 0) {
				// If negative all values must be negative. Check values below
				count = count + (j + 1);
				i++;
			} else {
				j--;
			}

		}
		return count;
	}
}
