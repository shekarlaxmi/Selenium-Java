package com.amazon.retail.steps;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.amazon.retail.utils.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Scenario scenario;

	@Before()
	public void setUp(Scenario s) {
		driver.set(WebDriverFactory.getDriver("chrome"));
		this.scenario = s;
	}

	@After()
	public void cleanUp() {
		if (scenario.isFailed()) {
			TakesScreenshot screen = (TakesScreenshot) driver.get();
			byte[] data = screen.getScreenshotAs(OutputType.BYTES);
			scenario.attach(data, "image/png", scenario.getName());
		}
		driver.get().quit();
		driver.remove();
	}
	
	public static WebDriver getDriver() {
		return driver.get();
	}
}
