Feature: Car service test cases.

  Background:
    Given Go to home page
    Then Check if I am on home page.

  Scenario Outline: Compare trims info on different pages.
    When Go to header and select Research & reviews tab.
    Then Check if I am on reviews page.
    When Select car info: '<maker>' '<model>' '<year>' and click search. Store it as 'First car'
    Then Check if I am on car Info page.
    When Select trim 1.
    Then Check if I am on Trim info Page.
    When Store info about car as 'First car'.

    When Go to header and select Research & reviews tab.
    Then Check if I am on reviews page.
    When Select car info: '<maker>' '<model>' '<year>' and click search. Store it as 'Second car'
    Then Check if I am on car Info page.
    When Select trim 1.
    Then Check if I am on Trim info Page.
    When Store info about car as 'Second car'.

    When Go to compare page from footer.
    Then Check if am on Compare page.
    When Fill fields with 'First car' and 'Second car' and click compare.
    Then Check if I am on Compare Result page.
    When Take 1 car and store it as 'First car from compare'.
    And Take 2 car and store it as 'Second car from compare'.

    Then Check if trim info 'First car>' and 'First car from compare' are matched.
    And Check if trim info 'Second car>' and 'Second car from compare' are matched.
    Examples:
      | maker      | model  | year |
      | Volkswagen | Passat | 2016 |
      | Volkswagen | Golf   | 2017 |

  Scenario Outline: Compare new car price and used car price:
    When Go to header and select Research & reviews tab.
    Then Check if I am on reviews page.
    When Select car info: '<maker>' '<model>' '<year>' and click search.
    Then Check if I am on car Info page.
    When Save trim name on position 1 as 'Trim name'.
    And Save trim price on position 1 as 'New car price'.
    When Go to header and select Cars for Sale tab.
    Then Check if I am on Cars for Sale page.
    When Search for: '<model>', '<maker>', '<used>', '<zipcode>', '<distance>', '<price>'.
    Then Check if I am on Search Result page.
    And From filter menu, select year '<year>'.
    And From filter menu, select trim stored as 'Trim name'.
    Then Check if content is available.
    When Get first card, get its price and store it as 'Used car price'.
    Then Check that 'Used car price' lower then 'New car price'.
    Examples:
      | maker      | model  | year | used | zipcode | distance | price        |
      | Volkswagen | Passat | 2016 | Used | 10001   | 20 Miles | No max price |
      | Audi       | Q7     | 2022 | Used | 10001   | 20 Miles | No max price |


