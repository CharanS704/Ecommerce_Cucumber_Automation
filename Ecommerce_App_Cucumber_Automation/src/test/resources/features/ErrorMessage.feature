@ErrorMessage
Feature: Validate Error Message functionality

@WebTest
Scenario: Validate error message when provided incorrect username and password

Given User is on ecommerce login page and login with incorrect username and password
|username            |charan@gmail.com  |
|password            |Charan1234            |
Then User validate error message on login page



