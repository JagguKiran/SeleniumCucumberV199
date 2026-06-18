package com.verismaMRR.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;

public class HookListeners {
	@Before 
	public void setUp() {
		System.out.println("===============BEFORE ANNOTATION===========================");
	}
	@After 
	public void tearDown() {
		System.out.println("===============AFTER ANNOTATION===========================");
	}
	@BeforeStep 
	public void precondition() {
		System.out.println("===============BEFORE STEP ANNOTATION===========================");
	}
	@AfterStep 
	public void postcondition() {
		System.out.println("===============AFTER STEP ANNOTATION===========================");
	}
}
