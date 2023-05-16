Feature: Report Portal Widget Tests

  Background:
    Given I am on the Report Portal login page
    When I login with valid credentials
    Then I should be logged in and see the All Dashboards page
    Given I select corresponding test account
    When I click on Dashboard tab
    Then I should be located on DEMO DASHBOARD page

  Scenario Outline: Verify buttons are present
    Then I should see the "<buttonName>" button with correct text

    Examples:
      | buttonName        |
      | Add new widget    |
      | Add shared widget |
      | Edit              |
      | Full screen       |
      | Delete            |


  Scenario Outline: Verify widget titles exist
    Then I should see the "<titleName>" widget title

    Examples:
      | titleName                |
      | PASSING RATE SUMMARY     |
      | LAUNCH STATISTICS BAR    |
      | INVESTIGATED PERCENTAGE OF LAUNCHES    |
      | LAUNCH STATISTICS AREA   |
      | OVERALL STATISTICS PANEL |

  Scenario Outline: Add shared widgets to the dashboard
    When I add shared "<widgetName>" to the dashboard
    Then I should see the "<widgetName>" widget added and displayed

    Examples:
      | widgetName      |
      | DEMO_FILTER_999 |
      | DEMO_FILTER_127 |
      | DEMO_FILTER_546 |
      | DEMO_FILTER_773 |
      | DEMO_FILTER_279 |
