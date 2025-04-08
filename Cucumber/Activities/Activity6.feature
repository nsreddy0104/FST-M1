@activity6
Feature: Data driven test with DataTable

@TasksDataTable
Scenario: Testing the To-Do app
  Given user is on the to-do list page
  When user adds the following tasks
    | task1 |
    | task2 |
    | task3 |
  Then verify results