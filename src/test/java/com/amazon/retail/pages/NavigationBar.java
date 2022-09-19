package com.amazon.retail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {
	
	WebDriver driver;
	public NavigationBar(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "twotabsearchtextbox")
	WebElement txtSearchBox;
	
	@FindBy(id = "nav-search-submit-button")
	WebElement btnSearch;
	
	public void enterSearchText(String text) {
		txtSearchBox.sendKeys(text);
	}
	
	public void clickSearchButton() {
		btnSearch.click();
	}
}
