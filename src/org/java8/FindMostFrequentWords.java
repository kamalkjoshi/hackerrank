package org.java8;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FindMostFrequentWords {
	
	public static void main(String[] args) throws Exception {
		testFindMostFrequentWords();
	}
	
	static void testFindMostFrequentWords()
			throws IOException, URISyntaxException {

		URI uri = ClassLoader.getSystemResource("selectionsort.txt").toURI();
		System.out.println("Uri: " + uri);
		Path path = Paths.get(uri);
		if (path.toFile().exists()) {
			System.out.println(findMostFrequentWords(path, 10));
		} else {
			System.out.println("Invalid Path: " + path);
		}
	}
	private static boolean isValidWord(String w) {
		return w != null && w.matches("\\w*");
	}

	//@formatter:off
	static List<String> findMostFrequentWords(Path path, int  noOfWords) throws IOException{
		
		List<String> words = Files.lines(path).parallel()
				   .flatMap(line->Arrays.asList(line.split("\\b")).stream()) // Stream of words
				   .filter(w-> isValidWord(w))// Filter non word e.g. , : ( etc
				   .collect(groupingBy(w->w,counting())) // Collection in Map of  word  frequency
				   .entrySet().stream() 
				//   .peek(System.out::println)
				   .sorted(comparing(Map.Entry<String,Long>::getValue).reversed()) // sort map entry set by frequency
				   .limit(noOfWords)
				   .map(Map.Entry::getKey)
				   .collect(toList());
		return words;
	}
	//@formatter:on

}
