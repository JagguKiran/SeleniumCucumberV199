package com.verismaMRR.stepDefinitions;

import java.awt.Desktop;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseStepDefinition {
	ThreadLocal<WebDriver> drivers=new ThreadLocal<>();
	ThreadLocal<ExtentTest> loggers=new ThreadLocal<>();
	WebDriver driver;
	String path=System.getProperty("user.dir")+"/ExtentReports/";
	ExtentReports reports;
	ExtentTest logger;
	WebDriverWait wait;
	public Logger log;
	public Properties configProp;
	public String configpath=System.getProperty("user.dir")+"/src/test/resources/config.properties";
	public void setDrivers(){
		String name=configProp.getProperty("browser");
		log.info(" Browser Type = "+name.toUpperCase());
		DesiredCapabilities dc=new DesiredCapabilities();
		name=name.toLowerCase();
		switch(name) {
			case "chrome":
						  log.info("Chrome Options Instantiation");
						  ChromeOptions options=new ChromeOptions();
						  log.info("Chrome Options setting or adding arguments");
						  options.addArguments("--remote-access-origin=*");
						  options.addArguments("--disable-notifications");
						  log.info("Merging Desired Capabilities with Chrome Options");
						  dc=new DesiredCapabilities();
						  dc.setCapability(ChromeOptions.CAPABILITY,options);options.merge(dc);WebDriverManager.chromedriver().setup();
						  log.info("Instantiating Chrome Browser");
						  drivers.set(new ChromeDriver(options));
						  break;
		case "edge":
						log.info("Edge Options Instantiation");
						EdgeOptions edgeoptions=new EdgeOptions();
						log.info("Edge Options setting or adding arguments");
						edgeoptions.addArguments("--remote-access-origin=*");
						edgeoptions.addArguments("--disable-notifications");
						log.info("Merging Desired Capabilities with Edge Options");
						dc.setCapability(EdgeOptions.CAPABILITY,edgeoptions);
						edgeoptions.merge(dc);
						//WebDriverManager.edgedriver().setup();
						WebDriverManager.edgedriver().setup();
						log.info("Instantiating Edge Browser");
						drivers.set(new EdgeDriver(edgeoptions));
						break;
		default:
						log.info("Chrome Options Instantiation");
						ChromeOptions coptions=new ChromeOptions();
						log.info("Chrome Options setting or adding arguments");
						coptions.addArguments("--remote-access-origin=*");
						coptions.addArguments("--disable-notifications");
						log.info("Merging Desired Capabilities with Chrome Options");
						dc=new DesiredCapabilities();
						dc.setCapability(ChromeOptions.CAPABILITY,coptions);coptions.merge(dc);WebDriverManager.chromedriver().setup();
						log.info("Instantiating Chrome Browser");
						drivers.set(new ChromeDriver(coptions));
		}
	}
	public WebDriver getDrivers(){return drivers.get();}
	public void removeThreadValue(){drivers.remove();}
	public void createReport(String name){
		path+=name.toUpperCase()+(int)(Math.random()*100000)+".html";
		reports=new ExtentReports();
		reports.setSystemInfo("USER",System.getProperty("user.name"));
		reports.setSystemInfo("HOME",System.getProperty("user.home"));
		reports.setSystemInfo("DIRECTORY",System.getProperty("user.dir"));
		reports.setSystemInfo("ENVIRONMENT","QAT");
		reports.setSystemInfo("Platform",System.getProperty("os.name"));
		reports.setSystemInfo("VERSION",System.getProperty("os.version"));
		reports.setSystemInfo("Platform",System.getProperty("os.arch"));
		ExtentSparkReporter spark=new ExtentSparkReporter(new File(path));
		spark.config().setDocumentTitle(name.toUpperCase());
		spark.config().setReportName(name.toUpperCase());
		reports.attachReporter(spark);
	}
	public void setExtentTest(String name){loggers.set(reports.createTest(name));}
	public ExtentTest getExtentTest(){return loggers.get();}
	public void removeExtentTest() {loggers.remove();}
	public void generateReport(){reports.flush();try{Desktop.getDesktop().browse(new File(path).toURI());}catch(Exception e){}}
	public String currentDate(){return DateTimeFormatter.ofPattern("dd_MM_yyyy").format(LocalDateTime.now());}
	public WebDriverWait explicitWait(WebDriver driver,int timeout) {
		wait=new WebDriverWait(driver,Duration.ofSeconds(timeout));
		wait.ignoring(StaleElementReferenceException.class);
		return wait;	
	}
}
