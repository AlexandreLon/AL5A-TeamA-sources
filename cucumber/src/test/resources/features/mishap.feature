Feature: Create Mishap

    Mishap creation

    Scenario: Create Mishap
    Given A "wagon breakdown" mishap
    When I create a mishap with type "Breakdown" for it
    And I get all tasks with mishap
    Then I have a "wagon breakdown" mishap in tasks
    And The task "wagon breakdown" is pending

    When I put "wagon breakdown" done
    And I get all tasks with done
    Then I have a "wagon breakdown" mishap done