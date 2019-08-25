package com.cfuture.cfuture.restclient;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RestClient {

	@Async
	public String getDetails1() {
		System.out.println("rest client details 1 invoked");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "details - one";
	}
	
	@Async
	public String getDetails2() throws ExecutionException {
		System.out.println("rest client details 2 invoked");
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "details - two";
	}
	
	@Async
	public Double getDetails3() {
		System.out.println("rest client details 3 invoked");
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 1000.00;
	}
	
}
