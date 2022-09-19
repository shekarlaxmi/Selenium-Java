package com.amazon.retail.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultsPage {

	WebDriver driver;
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@data-component-type='s-search-result']//div[contains(@class,'a-spacing-small')]")
	List<WebElement> searchResults;
	
	private By colorCirclesLocator = By.xpath("./div/span[@data-component-type='s-color-swatch']/div/div[contains(@class, 'circle')]");
	
	private WebElement itemChoosen;
	
	private String choosenColor = null;
	
	public boolean areSearchResultsDisplayed() {
		if(searchResults.size()>0)
			return true;
		else
			return false;
	}
	
	public void selectAnItemWithColorInIt() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		for(WebElement result:searchResults) {
			if(result.findElements(colorCirclesLocator).size()>1) {
				itemChoosen = result;
				break;
			}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public String clickOnDifferentColor() {
		List<WebElement> itemColors = itemChoosen.findElements(colorCirclesLocator);
		for(WebElement color:itemColors) {
			if(!color.getAttribute("class").contains("selected")) {
				WebElement colorLink = color.findElement(By.xpath("./span/div/a"));
				choosenColor = colorLink.getAttribute("aria-label").trim();
				color.click();
				break;
			}
		}
		return choosenColor;
	}
	
}
