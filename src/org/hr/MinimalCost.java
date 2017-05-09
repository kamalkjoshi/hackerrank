package org.hr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinimalCost {

	static class SetUnit {
		Set<Integer> set;
		int representative;

		SetUnit(Set<Integer> values, int representative) {
			this.set = values;
			this.representative = representative;
		}

		public boolean contains(int element) {
			return set.contains(element);
		}

		public String toString() {
			return "[" + representative + "," + set + "]";
		}
		
		public int size(){
			return set.size();
		}
	}

	private List<SetUnit> disjointSets;

	public MinimalCost() {
		disjointSets = new ArrayList<>();
	}

	public void createSet(int element) {
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		Set<Integer> set = new HashSet<Integer>();
		set.add(element);
		map.put(element, set);
		disjointSets.add(new SetUnit(set, element));
	}

	public void union(int first, int second) {
		int firstRep = findSet(first);
		int secondRep = findSet(second);

		Set<Integer> firstSet = null;
		Set<Integer> secondSet = null;

		for (int index = 0; index < disjointSets.size(); index++) {
			SetUnit unit = disjointSets.get(index);
			if (unit.contains(firstRep)) {
				firstSet = unit.set;
			} else if (unit.contains(secondRep)) {
				secondSet = unit.set;
			}
		}
		// First set will be the final set
		if (firstSet != null && secondSet != null)
			firstSet.addAll(secondSet);

		for (int index = 0; index < disjointSets.size(); index++) {
			SetUnit unit = disjointSets.get(index);
			// Update map
			if (unit.contains(firstRep)) {
				unit.representative = firstRep;
				unit.set = firstSet;
			} else if (unit.contains(secondRep)) {
				// Remove merged set
				unit = null;
				disjointSets.remove(index);
			}
		}

		return;
	}

	/**
	 * Return representative value from set.
	 */
	public int findSet(int element) {
		for (int index = 0; index < disjointSets.size(); index++) {
			SetUnit unit = disjointSets.get(index);
			if (unit.contains(element)) {
				return unit.representative;
			}
		}
		return -1;
	}

	public int getNumberofDisjointSets() {
		return disjointSets.size();
	}

	public static void main(String[] args) {

		String[] pairs = new String[]{"8 1", "5 8", "7 3", "8 6"};
		int n = 8;
		testMinimalCost(n, pairs);

	}
	
	static void testMinimalCost(int n, String[] pairs){
		MinimalCost obj = new MinimalCost();

		// Create single value set for all numbers.
		for (int i = 1; i <= n; i++) {
			obj.createSet(i);
		}

		System.out.println("ELEMENT : REPRESENTATIVE KEY");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + "\t:\t" + obj.findSet(i));
		}

		// Prepare current mappings
		for (String pair : pairs) {
			int first = Integer.valueOf(pair.split(" ")[0]);
			int second = Integer.valueOf(pair.split(" ")[1]);
			obj.union(first, second);
		}

		System.out.println(
				"\nThe Representative keys after performing the union operation\n");
		System.out.println(Arrays.toString(pairs));

		System.out.println("ELEMENT : REPRESENTATIVE KEY");
		for (int i = 1; i <= n; i++) {
			System.out.println(i + "\t:\t" + obj.findSet(i));
		}

		System.out.println("\nThe number of disjoint set : "
				+ obj.getNumberofDisjointSets());
		System.out.println(obj.disjointSets);
		System.out.println(minimalCost(obj));

	}

	static int minimalCost(MinimalCost set) {
		List<SetUnit> disjointSet = set.disjointSets;		
		int cost = disjointSet.stream().map(unit ->  Math.ceil(Math.sqrt(unit.size()))).mapToInt(k -> k.intValue()).sum();
		return cost;
	}
}
