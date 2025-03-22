Feature:    Purchase a product from the online store

Background: Buyer landed to website
Given       Buyer landing to ecommerce

# Scenario:   Create Order Positive Case
#     Given   Buyer login to the website
#     When    Buyer add a product to the cart
#     And     Buyer checkout product
#     And     Buyer place order
#     Then    Buyer will see message is displayed on confirmation page

Scenario Outline: Create Order Positive Case
    Given   Buyer login to the website email <email> and password <password>
    When    Buyer add a product <product_name> to the cart
    And     Buyer checkout product <product_name>
    And     Buyer place order <destination>
    Then    Buyer will see message is displayed on confirmation page THANKYOU FOR THE ORDER.

    Examples:
    |email                      |password        |product_name    |destination  |
    |aotomation@mailnesia.com   |jPF.TLurtbM@Yk5 |ZARA COAT 3     |Indonesia    |
    