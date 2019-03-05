Feature: Delete posts based on postId
  Scenario: delete posts based on post Id
    Given perform post operations using body "/posts"
    |id|title|author|
    |6 |Selenium|Puppy|
    And perform delete operation for post "/posts/{postId}"
    |postId|
    |6     |
    And perform get operation for "/posts/{postId}"
    |postId|
    |6     |
    Then verify that post not exist "Puppy"