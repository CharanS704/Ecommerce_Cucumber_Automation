Feature: Validate the ItemDetails database is up and able to retrieve data

@DBValidation
Scenario Outline: Validate if able to establish connection with the database and process records from table
Given User collects DB name, table name, username and password
Then User establish connection with the database
And User fetch record from the table with id "<ID>"
Examples:
|ID|
|1,2,3|

