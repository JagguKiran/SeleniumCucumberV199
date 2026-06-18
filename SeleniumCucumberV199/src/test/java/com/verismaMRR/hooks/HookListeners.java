package com.verismaMRR.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class HookListeners {
	@Before(order=1)
	public void setUpForLogger(Scenario sc) {
		System.out.println("===============BEFORE LOGGER ANNOTATION===========================");
		System.out.println(sc.getId());
		System.out.println(sc.getName());
		System.out.println(sc.isFailed());
		System.out.println(sc.getStatus());
	}
	@Before("@Regression") 
	public void setUpForReport(Scenario sc) {
		System.out.println("===============BEFORE REPORT ANNOTATION===========================");
		System.out.println(sc.getId());
		System.out.println(sc.getName());
		System.out.println(sc.isFailed());
		System.out.println(sc.getStatus());
	}
	@After(order=1) 
	public void tearDownForLogger(Scenario sc) {
		System.out.println("===============AFTER LOGGER ANNOTATION===========================");
		System.out.println(sc.getId());
		System.out.println(sc.getName());
		System.out.println(sc.isFailed());
		System.out.println(sc.getStatus());
	}
	@After("@Regression") 
	public void tearDownForForReport(Scenario sc) {
		System.out.println("===============AFTER REPORT ANNOTATION===========================");
		System.out.println(sc.getId());
		System.out.println(sc.getName());
		System.out.println(sc.isFailed());
		System.out.println(sc.getStatus());
	}


}
