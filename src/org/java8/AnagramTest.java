package org.java8;

import java.util.HashMap;
import java.util.Map;

public class AnagramTest {

	public static void main(String[] args) {
		containsAnagram("coding interview questions", "weivretni");
		containsAnagram("coding interview questions", "wwww");
		containsAnagram("aaa interview questions", "abc");
	}

	static void print(String msg) {
		System.out.println(msg);
	}

	static boolean containsAnagram(String text, String anagram) {
		print("Text length: " + text.length() + " anagram length: "
				+ anagram.length());
		char[] chars = text.toCharArray();
		int len = anagram.length();
		int iterations = 0;
		for (int i1 = 0; i1 < chars.length - len; i1++) {
			int i2 = i1 + len - 1;
			boolean result = isAnagram(chars, i1, i2, anagram);
			iterations++;
			if (result) {
				print("Anagram found at (i1: " + i1 + " & i2: " + i2
						+ ") in iterations " + iterations);
				return result;
			}
		}
		print("No Anagram found for {" + anagram + "} in string {" + text
				+ "} in iterations " + iterations);
		return false;
	}

	/**
	 * 
	 * Compare elements from each charsequence and put them in map one can
	 * increment and other can decrement, Result should be 0 for anagram case
	 * 
	 */
	static boolean isAnagram(char[] s1, int start, int end, String s2) {
		if (s2.length() != (end - start) + 1) {
			return false;
		}
		Map<Character, Integer> map = new HashMap<>();
		for (int i = start, j = 0; i <= end && j < s2.length(); i++) {
			// Put char counter from first string.
			map.compute(s1[i], (k, v) -> v == null ? 1 : v++);
			// Update char counter from second string.
			map.compute(s2.charAt(j), (k, v) -> v == null ? -1 : v--);
		}
		return map.values().stream().filter(v -> v != 0).count() == 0;
	}
}
