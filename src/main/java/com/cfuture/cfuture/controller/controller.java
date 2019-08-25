package com.cfuture.cfuture.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cfuture.cfuture.service.ServiceImpl;

@CrossOrigin
@RestController
@RequestMapping(path ="/api/v1")
public class controller {

	@Autowired
	private ServiceImpl service;
	
	@GetMapping("/async/details")
	public Map<String, Object> retrieveDetailsInAsync() {
		return this.service.retrieveDetails();
	}
	
}
