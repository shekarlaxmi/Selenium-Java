package com.amazon.retail.testng.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.retail.pages.NavigationBar;
import com.amazon.retail.pages.SearchResultsPage;

public class ShoeSearch extends BaseTest{

	@Test
	public void searchForShoeAndValidate() {
		NavigationBar nav = new NavigationBar(driver);
		nav.enterSearchText("shoes");
		nav.clickSearchButton();
		SearchResultsPage results = new SearchResultsPage(driver);
		Assert.assertTrue(results.areSearchResultsDisplayed());
		results.selectAnItemWithColorInIt();
		results.clickOnDifferentColor();
	}
}
