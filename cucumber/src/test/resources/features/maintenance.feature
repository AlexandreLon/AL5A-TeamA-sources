Feature: Create Maintenance

    Maintenance creation

    Scenario: Create Maintenance
    Given A "rail replacement" to do
    When I create a maintenance with type REPLACING for it
    And I get all tasks with maintenance
    Then I have a "rail replacement" maintenance in tasks