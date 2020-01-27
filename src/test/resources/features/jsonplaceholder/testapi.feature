Feature: Test Rest API using JsonPlaceHolder

#Background:
    #Given the stage is set for the user

  Scenario: Rest POST example
    Given post data
    When success code is 201
    Then assert for name
