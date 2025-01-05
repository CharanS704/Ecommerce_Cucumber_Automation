Feature: Validate the Store Api is heathly and able to fetch and modify resource

   Background:
   Given User collects base connection details and prepares request

  @PostAPIValidation
  Scenario Outline: Validate able to add a new place Id via Store API
    Given Request payload is available and the shoot a POST request is sent to API
    Then Validate the response status code is <statusCode>
    And Validate response message indicates the data is successfully saved
    
    Examples:
    |statusCode |
    |200        |
    
  @GetAPIValidation
  Scenario Outline: Validate able to fetch the details for place Id
    Given User prepare the url along with place Id "<placeId>" and shoot a GET request
    Then Validate the response status code is <statusCode>
    Examples:
    |statusCode |placeId                          |         
    |200        |e1eea708cb7138293317a7e3f55a8268 |
    
    
  @PutAPIValidation
  Scenario Outline: Validate able to update existing data from the database
    Given User prepare a request payload to update record with place Id "<placeId>" with updated address "<address>" and shoot a PUT request
    Then Validate the response status code is <statusCode>
    And Validate response message indicates the data is successfully updated
    Examples:
    
    |placeId                                 |statusCode |address        |
    |e1eea708cb7138293317a7e3f55a8268        |200        |Updated Address|    
    
    
  @DeleteAPIValidation
  Scenario Outline: Validate able to delete existing data from the database
    Given User prepare a request payload to delete record with place Id "<placeId>" and shoot a DELETE request
    Then Validate the response status code is <statusCode>
    And Validate response message indicates the data is successfully deleted
    Examples:
    
    |placeId                                 |statusCode |
    |e1eea708cb7138293317a7e3f55a8268        |200        |
    
    



