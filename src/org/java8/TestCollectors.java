package org.java8;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollectors {

	public static void main(String[] args) throws Exception {
		testShiftOperators();
		// testCollectors();	
	}

	static void testCollectors() {
		List<Integer> integers = Arrays
				.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		List<Integer> odd = integers.stream().filter(k -> k % 2 == 0)
				.collect(Collectors.toList());
		// return new CollectorImpl<>((
		// Supplier<List<T>>) ArrayList::new, //Supplier
		// List::add, // Accumulator
		// (left, right) -> { left.addAll(right); return left; }, //combiner
		// CH_ID); //charactersitics

		System.out.println(odd);
		long count = integers.stream().filter(k -> k % 2 == 0)
				.collect(Collectors.counting());
		// 0L = identity, e -> 1L = mapper , Long::sum = op
		// return new CollectorImpl<>(
		// boxSupplier(identity), // supplier --> new Object[] {identity}
		// (a, t) -> { a[0] = op.apply(a[0], mapper.apply(t)); }, // Accumulator
		// (a, b) -> { a[0] = op.apply(a[0], b[0]); return a; },// combiner
		// a -> a[0], CH_NOID);

		System.out.println(count);
	}

	
	static void testShiftOperators() {
		int number = 64;
		System.out.println(number + ">>>1 " + (number >>> 1));
		System.out.println(number + ">>1 " + (number >> 1));
		System.out.println(number + ">>>7 " + (number >>> 7));
		System.out.println(number + ">>7 " + (number >> 7));

		System.out.println(number + "<<1 " + (number << 1));
		System.out.println(number + "<<7 " + (number << 7));
	}	

}