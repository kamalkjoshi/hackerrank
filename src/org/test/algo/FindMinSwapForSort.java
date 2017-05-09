package org.test.algo;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FindMinSwapForSort {

	public static void main(String[] args) {
		testFindMinSwapsToSort();
	}

	static void testFindMinSwapsToSort() {
		System.out.println(
				findMinSwapsToSort(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
		System.out.println("==============================================");
		System.out.println(
				findMinSwapsToSort(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1}));
		System.out.println("==============================================");
		System.out.println(findMinSwapsToSort(
				new int[]{18, 13, 12, 19, 16, 15, 14, 17, 11}));
	}

	/**
	 * Finds the minimum number of swaps to sort given array in increasing
	 * order.
	 * 
	 * @param ints
	 *            array of <strong>non-negative distinct</strong> integers.
	 *            input array will be overwritten during the call!
	 * @return min no of swaps
	 */
	public static int findMinSwapsToSort(int[] ints) {
		final Map<Integer, Integer> map = IntStream.range(0, ints.length)
				.mapToObj(i -> i)
				.collect(Collectors.toMap(k -> ints[k], k -> k));

		Arrays.sort(ints);

		IntStream.range(0, ints.length).mapToObj(i -> i)
				.forEach(i -> ints[i] = map.get(ints[i]));

		System.out
				.println("Indices for sorted array: " + Arrays.toString(ints));

		int swaps = 0;
		for (int i = 0; i < ints.length; i++) {
			int val = ints[i];
			if (val < 0)
				continue;
			while (val != i) {
				int new_val = ints[val];
				ints[val] = -1;
				val = new_val;
				swaps++;
			}
			ints[i] = -1;
		}
		return swaps;
	}

}
