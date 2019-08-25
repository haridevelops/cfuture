package com.cfuture.cfuture.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cfuture.cfuture.restclient.RestClient;

@Service
public class ServiceImpl {

	@Autowired
	public RestClient restClient;
	
	public Map<String, Object> retrieveDetails() {
		System.out.println("retreiving details 1");
		CompletableFuture<String> details1 = CompletableFuture.supplyAsync(() -> this.restClient.getDetails1());
		
		System.out.println("retreiving details 2");
		CompletableFuture<String> details2 = CompletableFuture.supplyAsync(() -> {
			try {
				return this.restClient.getDetails2();
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		});
		
		System.out.println("retreiving details 3");
		CompletableFuture<Double> details3 = CompletableFuture.supplyAsync(() -> this.restClient.getDetails3());
		
		/** ****************************************************************************************************** */
		
		List<Object> combinedFutureScalable = Stream
				.of(details1, details2, details3)
				.map(CompletableFuture::join) //this blocks like get method, but waits for all collective cf's
				.collect(Collectors.toList());
		
		System.out.println("transform logic");
		
		Map<String, Object> logic = transformLogic((String) combinedFutureScalable.get(0), (String) combinedFutureScalable.get(1), (Double) combinedFutureScalable.get(2));
		
		return logic;
		
		// below 2 are used for combining 2 rest clients
//		System.out.println("performing logics from details 1 and details 2");		
//		CompletableFuture<List<String>> combinedFuture = details1
//				.thenCombine(details2, (details1Res, details2Res) -> {
//					ArrayList<String> list = new ArrayList<String>();
//					
//					System.out.println("inisde transformation logics");
//					list.add(details1Res);
//					list.add(details2Res);
//					
//					return list;
//				});
//		System.out.println("after logic ");
//		List<String> list;
//		try {
//			System.out.println("before get");
//			list = combinedFuture.get();
//			System.out.println("after get");
//		} catch (InterruptedException | ExecutionException e) {
//			throw new RuntimeException(e);
//		}
//		
//		return list;
	}

	private Map<String, Object> transformLogic(String details1, String details2, Double d) {
		System.out.println(details1);
		System.out.println(details2);
		System.out.println(d);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("String1", details1);
		map.put("String2", details2);
		map.put("Double", d);
		return map;
	}
	
}
