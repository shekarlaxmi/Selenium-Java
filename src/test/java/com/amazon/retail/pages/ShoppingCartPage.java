package com.amazon.retail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage{

	WebDriver driver;
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='a-price sw-subtotal-amount']/span[2]")
	WebElement cartTotalPrice;
	
	public boolean isPageDisplayed() {
		return isElementDisplayed(cartTotalPrice);
	}
	
	public String getCartTotalAmount() {
		System.out.println(cartTotalPrice.getText().trim().replaceAll("\n", "."));
		return cartTotalPrice.getText().trim().replaceAll("\n", ".");
	}
}
