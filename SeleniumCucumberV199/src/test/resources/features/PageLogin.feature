Feature: Page Login Feature

Background:
Given user launches respective browser 
When user naviage to URL 
	|https://opensource-demo.orangehrmlive.com/|
	
@T1 @Smoke
Scenario: Page Login to Application
And user enters Email Id and Password  
	|username|password|
	|Admin|admin123|
And click on login button 
Then Home Page Title should contain "Dashboard"
When userclick on logout button Nav 
And userclick on logout button
Then Login Page Title should contain "OrangeHRM"  
And close browser

#@T2 @Regression
#Scenario Outline: Page Login Data Driven Approach 
#And user enters Email Id as "<email>" and Password as "<password>"  
#And click on login button 
#Then Home Page Title should contain "Dashboard"
#When userclick on logout button Nav 
#And userclick on logout button
#Then Login Page Title should contain "OrangeHRM"  
#And close browser
#Examples:
#	|email|password|
#	|Admin|admin123|
#	|Kiran|kumar|
	