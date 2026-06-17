package com.verismaMRR.stepDefinitions;

import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.verismaMRR.pageEvent.LoginPageEvent;
import com.verismaMRR.pageObject.LoginPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageStepDefinition extends BaseStepDefinition{
		private LoginPage loginPage;
		@Before
		public void beforeScanario() {
			log=LogManager.getLogger(this);
			configProp=new Properties();
			try {
				configProp.load(new FileReader(new File(configpath)));
			}catch(Exception e){};
			createReport("kiran_kumar_uppin");
			setExtentTest("SDA_121_Cucumber");
			logger=getExtentTest();
		}
		@Given("user launches respective browser")
		public void user_launches_respective_browser() {
			log.info("Chrome Options Instantiation");
			ChromeOptions options=new ChromeOptions();
			log.info("Chrome Options setting or adding arguments");
			options.addArguments("--remote-access-origin=*");
			options.addArguments("--disable-notifications");
			options.addArguments("--headless=new");
			log.info("Merging Desired Capabilities with Chrome Options");
			DesiredCapabilities dc=new DesiredCapabilities();
			dc.setCapability(ChromeOptions.CAPABILITY,options);options.merge(dc);WebDriverManager.chromedriver().setup();
			log.info("Instantiating Chrome Browser");
			drivers.set(new ChromeDriver(options));
			logger.info("Launching Browser");
			driver=drivers.get();			
			loginPage=new LoginPage(driver);
			wait=explicitWait(driver,100);
			logger.info("Maximization of Driver");
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
		}
		@When("user naviage to URL")
		public void user_naviage_to_url(DataTable dataTable) {
			List<String> urls=dataTable.asList(String.class);
			logger.info("Navigating to Application");
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
			for(String url:urls) {
				System.out.println("KIRANKUMARUPPIN "+url);
				driver.get(url);}
		}
		@And("user enters Email Id and Password")
		public void user_enters_email_id_and_password(DataTable dataTable) {
			List<Map<String, String>> users = dataTable.asMaps(String.class,String.class);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageEvent.USERNAME)));
			for(Map<String, String> user:users) {
				System.out.println("KIRANKUMARUPPIN "+user.get("username")+" :: "+user.get("password"));
				loginPage.sendUserName(user.get("username"));
				loginPage.sendPassword(user.get("password"));
				
			}
			logger.pass("Entered UserName and Password");
		}
		@And("user enters Email Id as {string} and Password as {string}")
		public void userEnterEmailIdsAndPassword(String emailId,String password){
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageEvent.USERNAME)));
			loginPage.sendUserName(emailId);
			loginPage.sendPassword(password);
			logger.info("USERNAME "+emailId);
			logger.info("PASSWORD "+password);
		}
		@And("click on login button")
		public void click_on_login_button() {
		   loginPage.clickOnLoginButton();
		   logger.pass("Clicked on Login Button");
		}
		
		@Then("Home Page Title should contain {string}")
		public void page_title_should_contain(String title) {
			boolean isError=loginPage.errorMessage();
			System.out.println("KIRAN KUMAR UPPIN "+isError);
			if(isError){
				//driver.switchTo().alert().accept();
				removeThreadValue();
				driver.quit();
				logger.fail("Invalid Credentials");
				generateReport();
				Assert.assertEquals(isError,true);
			}else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageEvent.HEADERINFO)));
				String headerInfo=loginPage.homePageHeaderInfo();
				if(headerInfo.contains(title))logger.info("Navigated to Home Page");
			}
		}
		@When("userclick on logout button Nav")
		public void userClickNavBar() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageEvent.LogoutButtonNav)));
			loginPage.clickOnLogoutButtonNav();
			logger.pass("Clicked on Logout Button Navigation Bar");
		}
		@And("userclick on logout button")
		public void userclick_on_logout_button() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoginPageEvent.LOGOUTBUTTON)));
			loginPage.clickOnLogoutButton();
			logger.pass("Clicked on Logout Button");
		}
		@Then("Login Page Title should contain {string}")
		public void login_page_title_should_contain(String title) {
			String loginTitle=this.driver.getTitle();
			if(loginTitle.contains(title))logger.info("Navigated to Login Page");
			else Assert.fail();
		}
		@And("close browser")
		public void close_browser() {
			removeThreadValue();
			driver.quit();
			logger.pass("Quit Browser");
		}
		@After
		public void tearDown() {
			removeExtentTest();
			generateReport();
		}
}
