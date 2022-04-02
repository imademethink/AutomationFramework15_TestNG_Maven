Feature: Demo of Cucumber integration with TestNG

@simple
Scenario: Login happy path case
  Given User navigates to login screen
  When User attempts to login with valid credentials
  Then Login should be successful


@empty
Scenario: Login happy path case - empty
  Given User navigates to login screen empty
  When User attempts to login with valid credentials empty
  Then Login should be successful empty

@empty2 @empty @tag1
Scenario: Multiple Tags cases - empty2
  Given This is empty2

@empty3 @empty @tag2
Scenario: Multiple Tags cases - empty3
  Given This is empty3
