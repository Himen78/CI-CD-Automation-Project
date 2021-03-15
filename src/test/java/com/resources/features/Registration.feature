@UserRegistration
Feature: Application Registration

  Scenario: User Registration
    Given User is on landing a home page
    When User navigate to sign up page and enter the required details for sign up
    Then User is successfully registered
    And Navigate to Home page