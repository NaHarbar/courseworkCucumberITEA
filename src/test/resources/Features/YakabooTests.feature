Feature: Yakaboo Book order
  As a user, I want to order a book from my wishlist.

  Scenario: Add book to wishlist
    Given I launch chrome browser
    And I open yakaboo.com homepage
    And I login to my account
    And I select readers choice category
    And I open any book from the list
    And I click add to wishlist
    When I open my wishlist
    Then My book in wishlist
    And I close browser


  Scenario: Order a book from wishlist
    Given I launch chrome browser
    And I open yakaboo.com homepage
    And I login to my account
    And I clear my basket
    And I open my wishlist
    And I select any book from the wishlist
    And I add the book to basket
    And I open my basket
    And I check that book in my basket
    When I checkout my order
    And Order processing section displayed with my personal info
    And I fill out delivery form
    And I fill out payment form
    And I leave a comment for my order
    Then Payment button becomes available
    And I close browser