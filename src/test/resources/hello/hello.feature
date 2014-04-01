Feature: Hello

  Scenario: I don't know why you say goodbye, I say hello
    When I go to the hello page
    Then I expect to see "Hello, World!"

    When I follow "say goodbye"
    Then I expect to see "Goodbye, Jimbo!"

    When I follow "say hello"
    Then I expect to see "Hello, Jimbo!"
