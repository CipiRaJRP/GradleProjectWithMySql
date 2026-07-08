Feature: Catalog checkout
   As a shopper
   I want to find products,add them to my cart, and complete an order
   so that the retail purchase journey is covered in shared language

Background:
  Given the catalog is open

@smoke
Scenario: Buy a Single product end to end
  When I opened a Product "headphones"
  And I add a first result in CartBadge
  Then the CartBadge shows 1
  When I open a Cart
  Then It shows the cart line 1
  When I place an Order
  Then It shows the message Confirmed

@regression
Scenario Outline: Buy a Product <product> end to end
  When I opened a Product <product>
  And I add a first result in CartBadge
  Then the CartBadge shows <count>
  When I open a Cart
  Then It shows the cart line <count>
  When I place an Order
  Then It shows the message Confirmed

Examples:
   | product        | count  |
   | "headphones"   | 1      |
   | "skin"         | 1      |
   | "water"        | 1      |

@exercise
Scenario: A fresh cart to be Opened
 When I open a Cart
 Then It shows the cart line 0


