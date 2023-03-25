Feature: Car service test cases.

  Background:
    Then Check if I am on home page

  Scenario Outline: Compare trims info on different pages.
    When I go to header and select Research & reviews tab
    Then Check if I am on reviews page
    When I select car info: '<maker>' '<model>' '<year>', click search and store it as 'First car'
    Then Check if I am on car Info page
    When I select trim '1'
    Then Check if I am on Trim info Page
    When I store info about car as 'First car'

    When I go to header and select Research & reviews tab
    Then Check if I am on reviews page
    When I select car info: '<maker>' '<model>' '<year>', click search and store it as 'Second car'
    Then Check if I am on car Info page
    When I select trim '1'
    Then Check if I am on Trim info Page
    When I store info about car as 'Second car'

    When I go to compare page from footer
    Then Check if am on Compare page
    When I fill fields stored as 'First car' and 'Second car' and click compare
    Then Check if I am on Compare Result page
    When I take '1' car and store it as 'First car from compare'
    And  I take '2' car and store it as 'Second car from compare'

    Then I check if trim info 'First car' and 'First car from compare' are matched
    And  I check if trim info 'Second car' and 'Second car from compare' are matched
    Examples:
      | maker      | model  | year |
      | Volkswagen | Passat | 2016 |
      | Volkswagen | Golf   | 2017 |

  Scenario Outline: Compare new car price and used car price:
    When I go to header and select Research & reviews tab
    Then Check if I am on reviews page
    When I select car info: '<maker>' '<model>' '<year>' and click search
    Then Check if I am on car Info page
    When I save trim name on position '1' as 'Trim name'
    And  I save trim price on position '1' as 'New car price'
    When I go to header and select Cars for Sale tab
    Then Check if I am on Cars for Sale page
    When I search for: '<maker>', '<model>', '<used>', '<zipcode>', '<distance>', '<price>'
    Then Check if I am on Search Result page
    And  From filter menu, I select year '<year>'
    And  From filter menu, I select trim stored as 'Trim name'
    Then Check if content is available
    When I get first card, get its price and store it as 'Used car price'
    Then I check that 'Used car price' lower then 'New car price'
    Examples:
      | maker      | model  | year | used | zipcode | distance | price        |
      | Volkswagen | Passat | 2016 | Used | 10001   | 20 miles | No max price |
      | Audi       | Q7     | 2022 | Used | 10001   | 20 miles | No max price |


