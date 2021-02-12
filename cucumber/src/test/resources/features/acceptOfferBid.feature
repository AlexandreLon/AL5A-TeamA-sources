# Created by alexandre at 11/02/2021
Feature: Accept bid offer
  Patrick accept John's offer

  Scenario: Patrick accept John's offer
    Given Patrick create a mishap of type REPLACING and priority MEDIUM
    And John as supplier
    And John outbid 3000 today
    When Patrick accept John's offer
    Then Offer is accepted