package com.amazon.retail.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ItemDetailPage extends BasePage{
	WebDriver driver;
	
	public ItemDetailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[@id='title']")
	WebElement itemTitle;
	
	@FindBy(xpath = "//div[contains(@id,'title-color_name') or @id='variation_color_name']/*/*[2]")
	WebElement lblItemColor;
	
	@FindBy(xpath = "//span[contains(@id, 'text-size_name') or contains(@id,'selected_size_name')]")
	WebElement lblItemSize;
	
	@FindBy(xpath = "(//div[@class='a-box-group']//span[@class='a-offscreen'])[1]/..")
	WebElement lblItemPrice;
	
	@FindBy(xpath = "//div[contains(@id,'content-size_name')]//li//span[contains(@class,'a-button-text')]")
	List<WebElement> itemSizeAndCorrespondingPrice;
	
	@FindBy(name = "dropdown_selected_size_name")
	WebElement selectSize;
	
	@FindBy(id = "tp_price_block_total_price_ww")
	WebElement priceOfSelectedSize;
	
	@FindBy(id = "add-to-cart-button")
	WebElement btnAddToCart;
	
	public boolean isPageDisplayed() {
		return isElementDisplayed(itemTitle);
	}
	
	public String getItemColor() {
		return lblItemColor.getText().trim();
	}
	
	public String getItemSize() {
		return lblItemSize.getText().trim();
	}
	
	public String getItemPrice() {
		System.out.println(driver.findElement(By.xpath("(//div[@class='a-box-group']//span[@class='a-offscreen'])[1]/..")).getText().replaceAll("\n", "."));
		return lblItemPrice.getText().trim().replaceAll("\n", ".");
	}
	
	public void selectItemSize(String size) {
		if(itemSizeAndCorrespondingPrice.size()>0) {
			for(WebElement sizeElement:itemSizeAndCorrespondingPrice) {
				if(sizeElement.findElement(By.xpath("./div[1]/span")).getText().trim().equals(size)) {
					sizeElement.findElement(By.xpath("./parent::span/parent::span")).click();
					break;
				}
			}
		}else {
			Select sel = new Select(selectSize);
			sel.selectByVisibleText(size);
		}
		
	}
	
	public String getThePriceOfSelectedSize(String size) {
		if(itemSizeAndCorrespondingPrice.size()>0) {
			for(WebElement sizeElement:itemSizeAndCorrespondingPrice) {
				if(sizeElement.findElement(By.xpath("./div[1]/span")).getText().trim().equals(size)) {
					System.out.println(sizeElement.findElement(By.xpath("./div[2]/div/span/span")).getText().trim());
					return sizeElement.findElement(By.xpath("./div[2]/div/span/span")).getText().trim();
				}
			}
		}else {
			System.out.println(priceOfSelectedSize.findElement(By.xpath("./span[2]")).getText().trim().replaceAll("\n", "."));
			return priceOfSelectedSize.getText().trim().replaceAll("\n", ".");
		}
		
		return null;
	}
	
	public void clickAddToCartButton() {
		btnAddToCart.click();
	}
}
