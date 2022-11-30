
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of purchasing/Submitting  the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." message is dispalyed on ConfirmationPage

      Examples: 
      | name                    | password      | productName |
      | poojakotian97@gmail.com | Pooja@7859    | ZARA COAT 3 |
     