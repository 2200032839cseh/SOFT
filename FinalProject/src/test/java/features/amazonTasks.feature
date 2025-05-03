Feature: Amazon Task Automation

  Scenario: Verify the title of the Amazon page
    Given User is on the Amazon homepage
    Then The title should be "Amazon.in"

  Scenario: Verify the Product_id displays the correct product
    Given User searches for product with ID "IPHONE 15 Pro"
    Then Product should be displayed in the results

  Scenario: Search for a category
    Given User is on the Amazon homepage
    When User searches for "Books"
    Then Search results for "Books" should be displayed

  Scenario: Check the scrolling bar functionality
    Given User is on the Amazon homepage
    When User scrolls down the page
    Then Page should scroll successfully
