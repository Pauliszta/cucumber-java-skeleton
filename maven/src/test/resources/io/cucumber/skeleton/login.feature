Feature: Login

  Scenario: Correct password
    Given I am on website
    When I type login "tomsmith"
    And I type password "SuperSecretPassword!"
    And I click login button
    Then I am logged in

  Scenario: Incorrect password
    Given I am on website
    When I type login "tomsmith"
    And I type password "incorrectpassword"
    And I click login button
    Then I am not logged in


  Scenario Outline: Check credentials
    Given I am on website
    When I type "<login>" as login and "<password>" as password
#    When I type login "<login>"
#    And I type password "<password>"
#    And I click login button
    Then <expectedResult>
    Examples:
      | login    | password             | expectedResult     |
      | tomsmith | SuperSecretPassword! | I am logged in     |
      | tomsmith | incorrectpassword    | I am not logged in |


