Feature: Smartbear login feature verifications
  Agile Story: When user is on the login page of SmartBear app user should
  be able to login using correct test data
  A/C:
  1- Only authenticated user should be able to login
  Link: http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
  Authenticated test data:
  Username: Test
  Password: tester
  2- User should see "Welcome, Tester!" displayed when logged in.

  @positiveLoginSB1
  Scenario:

    Given user on smart bear homepage
    When  user input correct credential
    Then  user should see "Welcome, Tester!" displayed when logged in.

  @negativeLoginSB
    Scenario:
      Given user on smart bear homepage
      When  user input incorrect credential
      Then  error message should be appear.