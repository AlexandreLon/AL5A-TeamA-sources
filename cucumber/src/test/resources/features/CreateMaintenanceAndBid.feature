Feature: Create a maintenance and a bid

#  Background:

  Scenario: Manu creates a new bid

    When Manu creates a new maintenance "Rail servicing" for "21-03-2021"
    Then a new maintenance is created with the name "Rail servicing"
    And its realizationDate is not defined
    And a new bid "Rail servicing" is created and visible by Superman
    And the desired date associated is "21-03-2021"