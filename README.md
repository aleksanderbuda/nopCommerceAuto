## 📌 Selenium Test Automation Project for nopCommerce

This project demonstrates a robust Selenium-based test automation framework developed for the nopCommerce open-source eCommerce platform. Designed with maintainability, scalability, and reusability in mind, it employs industry best practices such as the Page Object Model (POM), TestNG, and Cucumber. Ideal for modern automation pipelines with Docker, Allure reporting, and Jenkins CI/CD integration.

### 🚀 Key Features
* **Allure Reporting**: Beautiful, interactive test reports with logs, screenshots, and historical trends.
* **TestNG Framework**: Harness the power of TestNG for grouping, prioritization, assertions, parallelism, and more.
* **Event Listener Integration**: Centralized logging and custom actions via TestNG ITestListener.
* **Retry Analyzer**: Automatic retry mechanism on test failure to enhance stability.
* **Screenshot on Failure**: Captures screenshots automatically when a test fails.
* **Data-Driven Login Tests**: Uses TestNG @DataProvider to validate login functionality with multiple credential sets.
* **Page Object Model (POM)**: Promotes clean code, reuse, and easy test maintenance.
* **Parallel Test Execution**: Boosts efficiency by running tests in parallel threads.
* **Cucumber BDD Support**: Write tests in Gherkin syntax for improved readability and collaboration.
* **Docker + Jenkins Ready**: Easily containerize the full test suite and integrate into any CI/CD pipeline.

 ____
### 📦 Setup Checklist
To ensure smooth execution of this Selenium project, please complete the following:

#### 1. Install Essentials

* **Java JDK**: Version 8 or higher
* **Maven**: For managing project dependencies and build
* **Docker**: Install Docker

#### 2. Optional
* **Jenkins**: For CI/CD automation
* **IDE**: For development and running tests locally

### ⚙️ Project Structure & Configuration
Dependencies are handled via pom.xml. Maven will download required libraries on first build.

Test Config is managed via the config.properties file inside src/main/resources. You can define the browser type, execution mode, and other settings.

### 🧪 Test Execution
* **Run Tests from IDE**
* **Open your IDE and import this as a Maven project.**
* **Right-click on a test class or test method and select Run.**

Run Tests via TestNG XML
Use regression.xml or parallel.xml located in the root/testng-xml directory for full suite or parallel execution:

bash
mvn clean test -DsuiteXmlFile=testng/parallel.xml
Run from Command Line
bash
mvn clean test
🔁 Retry Logic
Tests that fail due to flaky issues will be retried automatically using a custom RetryAnalyzer.

📸 Failure Handling
Every failed test captures a screenshot which is then attached to the Allure Report for faster debugging.

🌐 Jenkins Integration (CI/CD)
Pre-requisites
Jenkins installed on Windows

Required plugins: Git, Maven, Docker, Allure, TestNG, etc.

Docker must run locally to host the nopCommerce application.

Jenkins Job Configuration
Job Type: Freestyle or Pipeline

Repository URL: [Your repo here]

Branches to Build: */main

Build Steps:

Maven version: 3.9.8

Goals: clean test

Post-build Action: Generate Allure report (requires Allure plugin)

✅ Tests must run on a Windows node that has access to the locally hosted nopCommerce Docker container.

🐳 Docker (Optional)
You can fully containerize both the test framework and the nopCommerce application to allow for isolated and repeatable test execution. Future updates will include a docker-compose.yml setup.

💬 Cucumber Support
Behavior-driven development is supported via Cucumber. Write scenarios using Gherkin syntax for better collaboration with non-technical stakeholders.

🧰 Technologies Used

Tool / Framework	Purpose
Java	Core programming language
Selenium WebDriver	UI automation
TestNG	Test framework
Cucumber	BDD syntax support
Allure	Reporting
Docker	Containerization
Maven	Dependency management
Jenkins	CI/CD automation
