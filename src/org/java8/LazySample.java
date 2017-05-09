package org.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * from venkat's talk
 *
 */

public class LazySample {
	
	public static int expensiveComputation(int input) {
		System.out.println("called expensive computation");
		return input;
	}

	static void testLazyEvluation() {
		int somevalue = 4;

		System.out.println("Short circuit");
		if (somevalue > 5 && expensiveComputation(5) > 0)
			System.out.println("");
		else
			System.out.println("");

		System.out.println("Not so efficient");
		int temp = expensiveComputation(5);
		if (somevalue > 5 && temp > 0)
			System.out.println("");
		else
			System.out.println("");

		System.out.println("Lazy with Lambda");
		Supplier<Integer> temps = () -> expensiveComputation(5);
		if (somevalue > 5 && temps.get() > 0)
			System.out.println("");
		else
			System.out.println("");
	}

	public static void main(String[] args) {
		testInfiniteStreams();
	}
	
	static void testInfiniteStreams() {
		System.out.println(Stream.iterate(1, e -> e + 1).filter(e -> e % 2 == 0)
				.map(e -> e * 2).limit(100).reduce(0, Integer::sum));
	}
	
	static void testLazyCollections() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 4, 6, 7, 8, 9, 10);

		System.out.println("Notice the order of evaluation:");
		System.out.println(
				numbers.stream().filter(LazySample::isGT3).filter(LazySample::isEven)
						.map(LazySample::doubleIt).findFirst().orElse(0));

		System.out.println("Evaluation is Lazy:");
		numbers.stream().filter(LazySample::isGT3).filter(LazySample::isEven)
				.map(LazySample::doubleIt);
		System.out.println(
				"No computation was done since we did not ask for the result");
	}

	public static boolean isGT3(int number) {
		System.out.println("isGT3 " + number);
		return number > 3;
	}

	public static boolean isEven(int number) {
		System.out.println("isEven " + number);
		return number % 2 == 0;
	}

	public static int doubleIt(int number) {
		System.out.println("doubleIt " + number);
		return number * 2;
	}
}