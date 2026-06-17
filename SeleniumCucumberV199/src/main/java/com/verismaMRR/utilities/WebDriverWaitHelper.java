package com.verismaMRR.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverWaitHelper {
	private WebDriver driver;
	private WebDriverWait wait;
	public WebDriverWaitHelper(WebDriver driver,long timeOuts) {
		this.driver=driver;
		wait=new WebDriverWait(this.driver,Duration.ofSeconds(timeOuts));
	}
	public void waitUntilVisibilityOfElement(WebElement element,long timeOuts) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}
