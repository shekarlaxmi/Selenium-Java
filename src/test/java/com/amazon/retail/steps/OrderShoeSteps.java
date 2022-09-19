package com.amazon.retail.steps;

import java.text.DecimalFormat;

import org.junit.Assert;

import com.amazon.retail.pages.ItemDetailPage;
import com.amazon.retail.pages.NavigationBar;
import com.amazon.retail.pages.SearchResultsPage;
import com.amazon.retail.pages.ShoppingCartPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class OrderShoeSteps {

	NavigationBar nav = new NavigationBar(Hooks.getDriver());
	SearchResultsPage results = new SearchResultsPage(Hooks.getDriver());
	ItemDetailPage details = new ItemDetailPage(Hooks.getDriver());
	ShoppingCartPage cart = new ShoppingCartPage(Hooks.getDriver());
	
	String colorSelected;
	String itemPriceInDetailPage;
	
	@Given("Open amazon site")
	public void openApplication() {
		Hooks.getDriver().get("https://www.amazon.com/");
	}
	
	@When("User search for {string}")
	public void user_search_for(String string) {
		nav.enterSearchText(string);
		nav.clickSearchButton();
	}
	
	@Then("Search results displayed for shoes")
	public void search_results_displayed_for_shoes() {
	    Assert.assertTrue(results.areSearchResultsDisplayed());
	}
	
	@When("User pick an item which has color in it")
	public void user_pick_an_item_which_has_color_in_it() {
		results.selectAnItemWithColorInIt();
	}
	
	@When("User click on the different color")
	public void user_click_on_the_different_color() {
		colorSelected = results.clickOnDifferentColor();
	}
	@Then("Item details page should be displayed with selected color")
	public void item_details_page_should_be_displayed_with_selected_color() {
		Assert.assertTrue("Details page is not displayed", details.isPageDisplayed());
		Assert.assertEquals(colorSelected, details.getItemColor());
	}
	@When("User change the shoe size to {string}")
	public void user_change_the_shoe_size(String size) {
		details.selectItemSize(size);
	}
	@Then("Size label should be updated to {string}")
	public void size_label_should_be_updated(String size) {
		Assert.assertEquals(size, details.getItemSize());
	}
	
	@Then("Price should also be updated on the right pannel for the selected size {string}")
	public void price_should_also_be_updated_on_the_right_pannel(String size) {
		itemPriceInDetailPage = details.getItemPrice();
		Assert.assertEquals(details.getThePriceOfSelectedSize(size), itemPriceInDetailPage);
	}
	
	@When("User click on Add to Cart button")
	public void user_click_on_add_to_cart_button() {
		details.clickAddToCartButton();
	}
	
	@Then("shopping card should be displayed")
	public void shopping_card_should_be_displayed() {
	  Assert.assertTrue(cart.isPageDisplayed());
	}
	@Then("The price should be same as in detail page")
	public void the_price_should_be_same_as_in_detail_page() {
		Assert.assertEquals(itemPriceInDetailPage, cart.getCartTotalAmount());
	}
	@When("User go back to the details page")
	public void user_go_back_to_the_details_page() {
	   Hooks.getDriver().navigate().back();
	}

	@Then("The price should be adjusted for {int} items")
	public void the_price_should_be_adjusted_for_items(Integer int1) {
		double value = Double.parseDouble(itemPriceInDetailPage.replaceAll("\\$", ""))*int1;
		DecimalFormat f = new DecimalFormat("##.00");
		Assert.assertEquals("$"+f.format(value), cart.getCartTotalAmount());
	}
	
}
