

@tag
Feature: Title of your feature
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page 
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed.
   
     Examples: 
      | name                    | password    |
      | poojakotian97@gmail.com | Pooja@785   | 
     