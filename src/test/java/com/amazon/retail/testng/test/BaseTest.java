package com.amazon.retail.testng.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.amazon.retail.utils.WebDriverFactory;

public class BaseTest {

	WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void beforeTestCase() {
		driver = WebDriverFactory.getDriver("chrome");
		driver.get("https://www.amazon.in/");
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDownTest() {
		driver.quit();
	}
}
