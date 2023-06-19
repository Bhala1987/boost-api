@user
Feature: User

  Scenario: Login user
    When we hit the user endpoint for login with the following details
      | username   | password |
      | faffasffas | ZmFzZg== |
    Then the user should be logged in successfully

  Scenario: Get non-existent user
    When we hit the user endpoint for user 'invalinjiou89yohbjk'
    Then the 'User not found' error message should be thrown
