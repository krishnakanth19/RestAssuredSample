Feature:
  Verify different get and post
  Scenario: Verify authors post
    Given I perform get operation for "/posts"
    Then I should see the author name as "typicode"

  Scenario: Verify authors posts collection
    Given I perform get operation for "/post"
    Then I should see the author names

  Scenario: Verify path params
    Given I perform get operation for "/post"
    Then I should see the author name using path param

  Scenario: Verify query params
    Given I perform get operation for "/post"
    Then I should see the author name using query param

  Scenario: Verify post
    Given I perform post operation "/posts"

  Scenario: Verify post using profile
    Given I perform post operation for "/posts/{profileNo}/profile"
    |name|profileNo|
    |Chow|3    |
    Then I should verify body has name as "Chow"

  Scenario: verify delete operation after post
    Given perform post operation "/posts"
    |id|title|author|
    |4 | rest-api|KK|
    And perform delete operation"/posts/{postId}"
    |postId|
    |4     |
    And perform get operation "/posts/{postId}"
    |postId|
    |4|
    Then verify that post is not present