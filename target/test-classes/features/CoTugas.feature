# Feature:    Purchase a product from saucedemo

# Background: Buyer landed to website saucedemo
# Given       Buyer landing to saucedemo

# Scenario Outline: Create Order Saucedemo
#     Given   Buyer login to the saucedemo username <username> and password <password>
#     When    Buyer add a saucedemo product <productname> to the cart
#     And     Buyer checkout saucedemo product <productname>
#     And     Buyer insert <Firstname> <Lastname> <PostalCode> for shipping
#     And     Buyer will see checkout overview <productname>
#     Then    Buyer will see message confirmation page Thank you for your order!

#     Examples:
#     |username       |password        |productname           |Firstname    |Lastname    | PostalCode    |
#     |standard_user  |secret_sauce    |Sauce Labs Backpack    |Jhon         |Wick         | 12345         |