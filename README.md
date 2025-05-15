# Template for Java with Playwright, TestNG and Allure

This is a template project for setting up a Playwright framework with Java, Maven and TestNG following the Page Object Model (POM) design pattern. It provides a starting point for automated UI testing using Playwright.

## Getting Started

### Installation

1. Clone this repo
2. Navigate to the project directory
3. Install dependencies
    ```bash
    mvn clean install
    ```
4. Create a config file for your app in src/test/resources/config

### Running Tests

To run the tests, use the following Maven command:

```bash
mvn test
```

This project is set up to generate Allure reports for better visualization of test results. After running the tests, you can generate the Allure report using the following command:

```bash
allure serve allure-results
```
