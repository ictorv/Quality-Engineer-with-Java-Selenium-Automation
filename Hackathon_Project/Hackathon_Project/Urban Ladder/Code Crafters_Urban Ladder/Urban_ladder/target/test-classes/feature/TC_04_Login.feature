Feature: Login Functionality Validation
 
  Scenario: Validate login using credentials from external Excel
    Given User loads login data
    When User logs in with the following credentials:
      | email              | password     | scenario |
      |                    | Tohordai@123 | missing_email |
      | tohordai@gmail.com |              | missing_password |
      | tohordai@gmail.com | WrongPass123 | invalid_login |
      | tohordai@gmail.com | Tohordai@123 | valid_login |
 