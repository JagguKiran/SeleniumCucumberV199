package com.verismaMRR.pageObject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.verismaMRR.pageEvent.LoginPageEvent;

public class LoginPage extends BasePage{
	private WebDriver driver;
	// Declaring necessary elements present in LoginPage
	@FindBy(how=How.XPATH,using=LoginPageEvent.USERNAME) 
	private WebElement userName;
	@FindBy(how=How.XPATH,using=LoginPageEvent.PASSWORD) 
	private WebElement password;
	@FindBy(how=How.XPATH,using=LoginPageEvent.LOGINCLICK) 
	private WebElement loginButton;
	@FindBy(how=How.XPATH,using=LoginPageEvent.ERRORMESSAGE) 
	private WebElement errorMessage;
	@FindBy(how=How.XPATH,using=LoginPageEvent.LogoutButtonNav) 
	private WebElement logoutButtonNav;
	@FindBy(how=How.XPATH,using=LoginPageEvent.LOGOUTBUTTON) 
	private WebElement logoutButton;
	@FindBy(how=How.XPATH,using=LoginPageEvent.HEADERINFO) 
	private WebElement headerInfo;
	
	//Constructor
	public LoginPage(WebDriver driver) {
		super(driver);	
		this.driver=driver;
	}
	//Performing Actions on this elements 
	public void sendUserName(String name) {
		//Input Validation
		if(name==null||name.trim().equals(""))new IllegalArgumentException("User Name shouldn't point to null or empty string");
		//Business Validation 
		/*
		String exp="[a-zA-Z0-9]+[A-Z0-9a-z]*@[([.][a-zA-Z0-9]+)]*";
		Pattern p=Pattern.compile(exp);
		Matcher m=p.matcher(name);
		if(m.groupCount()!=1)throw new IllegalArgumentException("Invalid Email Id");
		//Perform Actual Action 
		*/
		userName.clear();
		userName.sendKeys(name);
	}
	public void sendPassword(String name) {
		//Input Validation
		if(name==null||name.trim().equals(""))new IllegalArgumentException("Password shouldn't point to null or empty string");
		//Business Validation 
		if(name.length()<3&&name.length()>10)throw new IllegalArgumentException("Invalid Password");
		//Perform Actual Action
		password.clear();
		password.sendKeys(name);
	}
	public void clickOnLoginButton() {
		Actions actions=new Actions(this.driver,Duration.ofSeconds(10));
		actions.click(loginButton).perform();
	}
	public void clickOnLogoutButtonNav() {
		Actions actions=new Actions(this.driver,Duration.ofSeconds(10));
		actions.click(logoutButtonNav).perform();
	}
	public void clickOnLogoutButton() {
		Actions actions=new Actions(this.driver,Duration.ofSeconds(10));
		actions.click(logoutButton).perform();
	}
	public String homePageHeaderInfo() {
		System.out.println(headerInfo.getText());
		return headerInfo.getText();
	}
	public boolean errorMessage() {
		try {
		if(this.driver.getPageSource().contains("Invalid credentials"))return true;
		else return false;}catch(Exception e) {return true;}
	}
}
