package org;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class JavaDeathMath {

	public static void main(String[] args) {
		// testBestFriend();
		// testPersonStudent();
		// testParseRegex();
		// testDotheThing();
		// testStrToInt();
		testCompute();
	}
	
	static void testCompute(){
		Map<String,List<String>> collection = new TreeMap<>();
	
		System.out.println(collection.compute("foo", (k, v) -> {
			if (v == null)
				return new ArrayList<String>();
			else {
				v.add("bar");
				return v;
			}
		}));
		System.out.println(collection.compute("foo", (k, v) -> {
			if (v == null)
				return new ArrayList<String>();
			else {
				v.add("ber");
				return v;
			}
		}));
		//	System.out.println( collection.compute( "foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List)v).add( "bar" ) ) );	
		//	System.out.println( collection.compute( "foo", (k, v) -> (v == null) ? new ArrayList<Object>() : ((List)v).add( "ber" ) ) );
	}
	
	
	public static List<Integer> filterUnderThreshold(final List<Integer> values, final int threshold) {
		List<Integer> returnValues = new ArrayList<>(values);
		for (int i = 0; i < returnValues.size(); i++) {
			if (returnValues.get(i) >= threshold) {
				returnValues.remove(i);
			}
		}
		return returnValues;
	}
	
	
	
	private int num = 0;

	public static void testStrToInt() {
		JavaDeathMath m1 = new JavaDeathMath();
		System.out.print( m1.strToInt("10") );
		System.out.println( m1.strToInt(m1.num + "1") );
	}

	private int strToInt( String num ) {
		this.num = Integer.parseInt(num);
		return this.num;
	}
	
	
	

	// Sorted order from treeset	
	private static final int A = 3;

	public static void doTheThing(final Collection<? extends Object> c) {
		c.remove(A);
		System.out.println(c);
	}
	public static void testDotheThing() {
		TreeSet<Integer> c2 = new TreeSet<Integer>();
		c2.add(3);
		c2.add(2);
		c2.add(1);
		c2.add(4);
		doTheThing(new ArrayList<Integer>(Collections.unmodifiableSet(c2)));
	}

//	static void testFilterNotMatchingRegex(){
//		
//	}
//	
//	private static List<String> filterNotMatchingRegex(final List<String> list, final Pattern pattern) {
//		List<String> returnList = new ArrayList<>(list);
//		ListIterator<String> listIterator = returnList.listIterator();
//		while (listIterator.hasNext()) {
//			String next = listIterator.next();
//			if (pattern.matcher(next).matches()) {
//				listIterator.remove();
//			}
//		}
//		return returnList;
//	}

	static void testParseRegex() {
		System.out.println("Value: "+parsePriceTotalOfProducts(
				"bought 9 products for 120 per piece"));
		System.out.println("Value: "+parsePriceTotalOfProducts(
				"A 9 P 10 X"));
	}

	static void testBestFriend() {
		Person p1 = new Person("p1");
		Person p2 = new Person("p2");
		Person p3 = new Person("p3");
		Person p4 = new Person("p4");

		p1.bestFriend = p2;
		p2.bestFriend = p3;
		p4.bestFriend = p3;
		p3.bestFriend = p1;

		System.out.println(getBestFriendsClique(p1).stream()
				.map(k -> k.getString()).collect(Collectors.toList()));
	}

	// Potetial OOM
	private static Set<Person> getBestFriendsClique(Person person) {
		Set<Person> result = new HashSet<>();
		result.add(person);
		while ((person.bestFriend != null) && (result.add(person.bestFriend))) {
			person = person.bestFriend;
		}
		return result;
	}

	static class Person {
		Person() {

		}

		Person(String name) {
			this.name = name;
		}

		String name;
		Person bestFriend;

		@Override
		public String toString() {
			return name;
		}

		public String getString() {
			return name + "->" + bestFriend.name;
		}
	}

	static void testPersonStudent() {
		Person p1 = new Person();
		Person p2 = new Student();
		p1.name = "Ricki";
		p2.name = "Maru";
		System.out.println(p1.toString() + p2);
		System.out.println(p1.toString() + p2.name);
	}

	static class Student extends Person {
		public String name;

		@Override
		public String toString() {
			return name;// super.name;
		}
	}

	// example input: 'bought 9 products for 120 per piece'
	// this method assumes that prices are input as integers, so '1265'
	// instead of '1.265,00'
	static BigInteger parsePriceTotalOfProducts(final String input) {
		Pattern pattern = Pattern.compile("^.*?(\\d+).+?(\\d+).*$");
		Matcher matcher = pattern.matcher(input);
		// matcher find needs to be used as well
		if (matcher.find()) {
			int number = Integer.parseInt(matcher.group(1));
			int costPerProduct = Integer.parseInt(matcher.group(2));
			return BigInteger.valueOf((long) number * costPerProduct);
		}
		return null;
	}

}
