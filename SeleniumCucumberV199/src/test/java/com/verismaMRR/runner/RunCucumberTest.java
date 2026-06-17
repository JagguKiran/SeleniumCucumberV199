package com.verismaMRR.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/features",
		glue="com.verismaMRR.stepDefinitions",
		dryRun=false,
		monochrome=false,
		plugin = {
					 "pretty", 
					 "html:target/cucumber-reports/cucumber.html",
					 "json:target/cucumber-reports/cucumber.json",
					 "junit:target/cucumber-reports/cucumber.xml"
			},tags="@T1"
)
public class RunCucumberTest {

}
