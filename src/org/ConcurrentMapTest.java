package com.sd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapTest {

	public static void main(String[] args) throws Exception {
		ConcurrentMapTest test = new ConcurrentMapTest();
		test.givenHashMap_whenSumParallel_thenError();
		test.givenConcurrentMap_whenSumParallel_thenCorrect();
		System.out.println("----------------------------------------");
		test.givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster();
	}

	public void givenHashMap_whenSumParallel_thenError() throws Exception {
		Map<String, Integer> map = new HashMap<>();
		List<Integer> sumList = parallelSum100(map, 100);
		System.out.println("Verify HashMap");
		verify(sumList);
	}

	private List<Integer> parallelSum100(Map<String, Integer> map, int executionTimes) throws InterruptedException {
		List<Integer> sumList = new ArrayList<>(1000);
		for (int i = 0; i < executionTimes; i++) {
			// Add test value to map.
			map.put("test", 0);
			ExecutorService executorService = Executors.newFixedThreadPool(4);
			for (int j = 0; j < 10; j++) {
				executorService.execute(() -> {
					for (int k = 0; k < 10; k++)
						map.computeIfPresent("test", (key, value) -> value + 1);
				});
			}
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.SECONDS);
			sumList.add(map.get("test"));
		}
		return sumList;
	}

	private void verify(List<Integer> sumList) {
		System.out.println("Distinct " + sumList.stream().distinct().count());
		long not100Count = sumList.stream().filter(num -> num != 100).count();
		System.out.println("Not100Count:" + not100Count);
	}

	public void givenConcurrentMap_whenSumParallel_thenCorrect() throws Exception {
		Map<String, Integer> map = new ConcurrentHashMap<>();
		List<Integer> sumList = parallelSum100(map, 1000);
		System.out.println("Verify Concurrent HashMap");
		verify(sumList);
	}

	public void givenMaps_whenGetPut500KTimes_thenConcurrentMapFaster() throws Exception {
		Map<String, Object> hashtable = new Hashtable<>();
		Map<String, Object> synchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
		Map<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

		long hashtableAvgRuntime = timeElapseForGetPut(hashtable);
		long syncHashMapAvgRuntime = timeElapseForGetPut(synchronizedHashMap);
		long concurrentHashMapAvgRuntime = timeElapseForGetPut(concurrentHashMap);

		System.out.println("hashtableAvgRuntime - " + hashtableAvgRuntime +" nanoseconds");
		System.out.println("syncHashMapAvgRuntime - " + syncHashMapAvgRuntime+" nanoseconds");
		System.out.println("concurrentHashMapAvgRuntime - " + concurrentHashMapAvgRuntime+" nanoseconds");
	}

	private long timeElapseForGetPut(Map<String, Object> map) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(4);
		long startTime = System.nanoTime();
		for (int i = 0; i < 4; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 500_000; j++) {
					int value = ThreadLocalRandom.current().nextInt(10000);
					String key = String.valueOf(value);
					map.put(key, value);
					map.get(key);
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
		return (System.nanoTime() - startTime) / 500_000;
	}

}
