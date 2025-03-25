Feature: Login to RahulShettyAcademy Ecommerce Website

  As a registered user,
  I want to log in to the RahulShettyAcademy Ecommerce Website,
  So that I can access my account and validate its functionality.

  @Smoke
  Scenario: Successful Login with Valid Credentials
    Given The user navigates to the login page of RahulShettyAcademy at "https://rahulshettyacademy.com/client"
    When The user enters a valid email ID "amitkumar.inbay@gmail.com"
    And The user enters the correct password "Test@123"
    And The user clicks on the login button
    Then The user should be successfully logged in and redirected to the homepage
