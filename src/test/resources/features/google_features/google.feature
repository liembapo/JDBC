Feature: Google feature verifications
  Agile Story: User stories, acceptance criteria
  Basically we can pass here any additional information related to this feature file
  #comments are done using hashtag sign in feature file

  @googleTest
  Scenario: Google title verification

    When User is on google homepage
    Then User should see title contains Google
    When User type wooden spoon on search box
    When User click the search button
    Then User should see wooden spoon title
