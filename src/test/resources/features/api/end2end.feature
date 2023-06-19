@e2e
Feature: End to end flow for the pet store apis

  Scenario: End to end flow for the pet store apis - User -> Pet -> Order
    When we hit the user endpoint with the following details
      | username | firstName | lastName | email                         | password | phone       |
      | petlover | James     | Scott    | James.Scott@dummydomain.co.uk | Ym9vc3Q= | 07999999999 |
    Then the user should be created successfully
    When we hit the pet endpoint with the following details
      | categoryName | petName | photoUrls                                                                      | tagsName                      | status    |
      | Dog          | Mars    | https://www.thecrossovertrainer.com/wp-content/uploads/2013/03/dog-on-mars.jpg | Siberian Husky; Black & White | available |
    Then the pet should be created successfully
    When we hit the store endpoint with the following details
      | quantity | shipDate | status | complete |
      | 1        | TODAY    | placed | true     |
    Then the order should be placed successfully