Feature: Create Mishap

    Mishap creation

    Scenario: Create Mishap
    Given A "wagon breakdown" mishap
    When I create a mishap of type REPLACING and priority MEDIUM
    And I get all tasks
    Then I have a "wagon breakdown" mishap in tasks
    And The task "wagon breakdown" is waiting for bid closure

    When I finish the "wagon breakdown" task
    And I get all tasks again
    Then the mishap named "wagon breakdown" is finished