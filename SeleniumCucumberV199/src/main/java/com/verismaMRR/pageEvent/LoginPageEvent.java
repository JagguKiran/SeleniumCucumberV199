package com.verismaMRR.pageEvent;

public interface LoginPageEvent {
	//Login and Logout XPATH
	String USERNAME="//input[@name='username']";
	String PASSWORD="//input[@name='password']";
	String LOGINCLICK="//button[@type='submit']";
	String ERRORMESSAGE="//p";
	String LogoutButtonNav="//span/img[@alt='profile picture']";
	String LOGOUTBUTTON="//a[contains(.,'Logout')]";
	String HEADERINFO="//h6[contains(.,'Dashboard')]";
}
