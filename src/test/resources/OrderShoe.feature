@tag
Feature: Order shoe from amazon

  @tag1 @regression @smoke
  Scenario: Select shoe
    Given Open amazon site
    When User search for "shoes"
    Then Search results displayed for shoes
    When User pick an item which has color in it
    And User click on the different color 
    Then Item details page should be displayed with selected color
    When User change the shoe size to "8"
    Then Size label should be updated to "8"
    And Price should also be updated on the right pannel for the selected size "8"
    When User click on Add to Cart button
    Then shopping card should be displayed
    And The price should be same as in detail page
    When User go back to the details page
    And User click on Add to Cart button
    Then shopping card should be displayed
    And The price should be adjusted for 2 items
    
    

  
