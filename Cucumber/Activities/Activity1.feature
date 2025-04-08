@activity1
Feature: Activity to test the TS home page

  Scenario: TS home page
    Given User is on TS Home Page
    When they click on the About Us link
    Then redirect to About page