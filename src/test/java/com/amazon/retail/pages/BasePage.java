package com.amazon.retail.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

	WebDriver driver;
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isElementDisplayed(WebElement element) {
		try {
			return element.isDisplayed();
		}catch(NoSuchElementException n) {
			return false;
		}
	}
}
