# Coding guidelines

## Architecture & Design Principles

- Follow general coding best practices
- SOLID principles
- DRY (Don't Repeat Yourself)
- KISS (Keep It Simple, Stupid)

### 1. Separation of Concerns
-   Maintain clear separation between test logic and page interactions
-   Test classes should focus on test scenarios, test data, and assertions
-   Page objects should handle element interactions and page-specific logic
    - Page classes should not contain assertions
    - Methods in page classes should only return data, and not web elements, or other ui focused objects
-   Complex/long business flows should be abstracted into separate service/flow classes
-   Keep configuration separate from test implementation

### 2. Code Reusability
-   Create a base layer of common utilities and helpers
-   Implement reusable components for common UI elements
-   Abstract common test flows into shared methods
-   Maintain a library of reusable test data generators

- Do not commit any sensitive information to the repository
    - This mainly includes tokens, keys, passwords, etc.
    - Use environment variables to store sensitive information
        - Example: `String apiKey = System.getenv("API_KEY");` instead of hardcoding
    - Use `.gitignore` to exclude sensitive files from being committed

## Core Testing Principles

### 0. Test Relevance
-   Write tests that are meaningful and provide value
    -   Focus on testing critical and high-risk areas first
    -   Keep trivial or low-priority tests for last
-   Automate tests that are repetitive and time-consuming to execute manually
-   Avoid scenarios that are not repeatable or are not feasible to automate
-   Test scenarios should match real-world user behavior
    -   Use the right use account, role, policy, etc. for the scenario
    -   Don't test with admin accounts unless the test is specifically for admin functionality

### 1. Test Independence
-   Implement proper test groups
    -  Group tests based on functionality, priority, and the role/policy being used in the scenario
-   Each test should be completely independent and self-contained
    -   Design tests with parallel execution in mind
-   Tests should not depend on other tests' execution or state
    -   Avoid sharing state between tests unless there is literally no other way
-   Clean up test data and state after each test
    - Take advantage of the API or direct sql queries to set up or clean up test data as needed to make things more maintainable and reduce runtime
      - Example: `apiClient.deleteTestUser(userId);` or `dbUtils.executeQuery("DELETE FROM users WHERE test_flag = true");`
### 2. Test Reliability
-   Tests should produce consistent results when run multiple times
-   Avoid flaky tests that sometimes pass and sometimes fail
    - Run any new tests multiple times to identify flaky tests
    - Investigate and fix flaky tests immediately
        - Or, leave a comment, at the very least, if it cannot be fixed immediately
-   Handle dynamic elements and async operations properly
    - Implement proper wait strategies
    - Never use static and hard-coded waits
    - Implement custom wait conditions if needed
    - To Include proper error handling and recovery mechanisms

### 3. Test Maintainability
-   Follow the Page Object Model (POM) pattern
-   Keep locators centralized and well-maintained
-   Use meaningful names for methods and variables
-   Document complex logic or test scenarios and assumptions with clear comments
-   Keep tests focused and avoid testing multiple features in one test
-   Include screenshots on failure
-   Log relevant information for debugging

### 4. Test Data Management
-   Externalize test data from test scripts
    - Use TestNG data providers for parameterized tests
    - Maintain test data in easily manageable formats like JSON
    - Generate fake/dummy test data using libraries like JavaFaker
-   Never use production data in automated tests
