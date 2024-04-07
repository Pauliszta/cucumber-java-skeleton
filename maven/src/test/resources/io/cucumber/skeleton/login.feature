Feature: Login

  Scenario: Correct password
    Given I open the browser
    And I am on website
    When I type login "tomsmith"
    And I type password "SuperSecretPassword!"
    And I click login button
    Then I am logged in

  Scenario: Incorrect password
    Given I open the browser
    And I am on website
    When I type login "tomsmith"
    And I type password "incorrectpassword"
    And I click login button
    Then I am not logged in
