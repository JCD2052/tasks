Feature: Car service test cases.
  Scenario: Compare trims info on different pages.
    When Go to header and select Research & reviews tab.
    Then Check if I am on reviews page.
    And Select trim 1 for some car and store info about it with name 'First car'.
    When From this (research) page go to header and select Research & reviews tab.
    Then Check if I am on reviews page.
    And Select trim 1 for some car and store info about it with name 'Second car'.
    When Go to footer menu and go to Compare page.
    Then Check if I am on compare page.
    When Start to compare 'First car' and 'Second Car'.
    When Store info about car with position 1 as 'First Received Car'.
    And Store info about car with position 2 as 'Second Received Car'.
    Then Check whether 'First car' info from Research Page matches with 'First Received Car' info from Compare Page.
    And Check whether 'Second car' info from Research Page matches with 'Second Received Car' info from Compare Page.
