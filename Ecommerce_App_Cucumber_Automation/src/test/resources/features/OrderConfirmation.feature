Feature: Validate order placing functionality

@MultipleOrders @WebTest
Scenario Outline: Validate placing multiple orders functionality and confirmation message

Given User is on ecommerce login page and login with correct username and password
|username            |user1@gmail.com  |
|password            |uspassword       |
When User selects multiple orders "<orders>" from product catalog page and navigate to orders page
And User validate actual selected orders from orders page against the list of expected orders
Then User navigate to checkout page
And User verify the order status and fetch order Ids

Examples:
|orders                        |
|ADIDAS ORIGINAL,IPHONE 13 PRO |
|ADIDAS ORIGINAL               |


@SingleOrder @WebTest
Scenario Outline: Validate placing single order functionality and confirmation message

Given User is on ecommerce login page and login with correct username and password
|username            |user1@gmail.com  |
|password            |uspassword       |
When User selects multiple orders "<orders>" from product catalog page and navigate to orders page
And User validate actual selected orders from orders page against the list of expected orders
Then User navigate to checkout page
And User verify the order status and fetch order Ids

Examples:
|orders         |
|ADIDAS ORIGINAL|
