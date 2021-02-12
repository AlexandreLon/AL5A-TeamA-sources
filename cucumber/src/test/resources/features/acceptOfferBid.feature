# Created by alexandre at 11/02/2021
Feature: Accept bid offer
  Patrick accept John's offer

  Scenario: Patrick accept John's offer
    Given Patrick creates a mishap of type REPLACING and priority MEDIUM
    And John as supplier
    When John outbids 3000 today
    Then The bid has an offer
    When Patrick accepts John's offer
    Then Offer is accepted