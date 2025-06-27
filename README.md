# 💰💻 Finology Automation
**Domain**: FinTech

This repository contains a **Behavior-Driven Development (BDD)** test suite for the Finology fintech platform, automating critical user flows (signup, login, dashboard) on the website.
Selenium WebDriver is used to control browsers and execute tests. Test scenarios are defined in plain-language Gherkin using Cucumber, making them readable to both technical and non-technical stakeholders.
The project leverages TestNG for test orchestration, a Page Object Model for organizing page interactions, and various reporting/logging tools to produce detailed test reports and logs.

---

## 🧪 Test Coverage

* Signup/Registration
* Login Authentication
* Account Dashboard Basic Navigation

---

## 🛠️ Tech Stack

| **Component**         | **Description**                                                                                                    |
|-----------------------|--------------------------------------------------------------------------------------------------------------------|
| **Gradle**            | Build automation and dependency management tool                                                                    |
| **Java**              | Core programming language used to implement test logic and framework structure                                     |                      |                                                                                                                  |
| **Selenium**          | Web automation tool for simulating user actions                                                                    |
| **TestNG**            | Testing framework supporting assertions, grouping, reporting, and parallel runs                                    |
| **Cucumber BDD**      | Test scenarios written in Gherkin syntax for readable, collaborative test cases                                    |
| **Page Object Model** | Scalable architecture using Page Factory and AjaxElementLocatorFactory for lazy element loading.                   |                      |                                                                                                                  |
| **ExtentReports**     | Reporting framework support the html report, test cases logs and results, attaching screenshots to the test case   |
| **Cucumber Reports**  | Scenario and step-wise HTML reports                                                                                |
| **Excel Summary**     | Consolidated test execution statistics in Excel format                                                             |
| **Log4j**             | Logging framework used to track and log execution details                                                          |

---

## 🔑⭐ Key Features

### 🔀 Parallel Test Execution

* Enables high-speed execution using TestNG with ThreadLocal for thread-safe WebDriver instances and class objects.
* Java Semaphore restricts active thread count using the configurable `maxParallelThreadAllowed` parameter.
* All concurrency is controlled via testng.xml and the @DataProvider(parallel = true) configuration in the Test Runner class.

### 🌐 Multiple-Browser Support
- Supports running tests on Chrome, Edge, Firefox, and Safari.
- Set the desired browser in the testng.xml file.

### 📸 Screenshot on Failure
- Automatically captures a screenshot when any test step or test case fails or test case passes and attach the screenshot to that step in the Extent Report.
- Have proper exceptions message and line at which a test step fails with screenshots.
- Useful for debugging and tracking test failures.
- Extent Report and Screenshots are saved in the specified directory (e.g., extent_reports/).

---  

## 📁 Project Structure

```
📦 Finology
├── 📁 cucumberReports/                                  # Cucumber BDD report ( HTML and JSON format)
├── 📁 excelReport/                                      # Excel Summary Reports
├── 📁 extentReports/                                    # Extent HTML Reports and Screenshots
├── 📁 log4j/                                            # Log4J log output ( HTML and Plain Text format)
├── 📁 src/
│   ├── 📁️ main/
│   │   ├── 📁 java/
│   │   │   └── 📁 in/finology/
│   │   │       ├── 📁 pages/                            # Page Object classes for UI interaction
│   │   │       ├── 📁 screenshot/                       # Classes for screenshot handling
│   │   │       └── 📁 utils/                            # Utility and helper classes
│   │   │           ├── 📁 driverFactory/                # WebDriver management
│   │   │           └── 📁 reporting/                    # Reporting utilities
│   │   └── 📁 resources/
│   │       ├── 📊 Data.xlsx                             # Input data generated during execution of test cases
│   │       └── 🖼️ Error_while_capturing_screenshot.png  # Error sample image
│   └── 📁 test/
│       ├── 📁 java/
│       │   └── 📁 in/finology/
│       │       ├── 📁 hooks/                            # Cucumber hook definitions (@Before, @After)
│       │       ├── 📁 stepDefns/                        # Step definition files for feature steps
│       │       └── 📁 testRunner/                       # TestNG/Cucumber runners
│       └── 📁 resources/
│           ├── 📁 features/                             # Gherkin .feature files for BDD scenarios
│           ├── 🛠️ log4j2.properties                     # Logging configuration file
│           └── 🧪 testng.xml                            # TestNG suite configuration
├── ⚙️ build.gradle                                      # Gradle build script
├── ⚙️ settings.gradle                                   # Gradle settings
└── 📘 README.md                                         # Project documentation
```
---

## 🚀 Getting Started

### 🔧 Prerequisites

* IDE
* Java 14 or higher
* Browsers: Chrome, Edge, Firefox, or Safari
* Gradle or use gradlew

### 📦 Setup & Run

1. **Clone the repository:**
   `git clone https://github.com/Lakesh-Sahu/Finology_Automation.git`
   cd `Finology_Automation`

2. **Configure parameters in `testng.xml`:**
   ```
   <parameter name="browserName" value="Chrome"/>
   <parameter name="maxParallelThreadAllowed" value="4"/>
   <parameter name="isFair" value="true"/>
   ```

3. **Run Tests using Gradle:**
   `./gradlew clean test`

---

## 📋 Test Configuration
- **TestNG:** TestNG configuration can be managed via the testng.xml file for specific testRunner, specific browser and maximum parallel thread count.
- **TestRunner:** A dedicated runner class integrates Cucumber with TestNG, coordinating feature execution, managing hooks (setup/teardown), and enabling advanced configurations like parallel scenario execution.

## 📊 Report
- **Extent Reports:** Managed by ExtentReports and stored in the extentReports/ directory. 
- Each test method and assertion logs detailed status such as pass, fail, skip, and warning for efficient debugging and traceability. 

- **Cucumber Reports:** Scenario-wise and step-wise execution reports in HTML format, located in the cucumberReports/ directory. 
- These reports provide a clear overview of each feature's test flow. 

- **Excel Summary:** Consolidated execution statistics for each test scenario (pass/fail/skip) exported to Excel format. 
- Reports are stored in the excelReport/ directory for quick offline analysis.

## 📝 Logs
- **Log4j Logs:** All runtime logs are handled by Log4j and saved in the log4j/ directory.
- Each log captures timestamped debug, info, and error-level events for detailed test execution traceability and troubleshooting support.

## 📌 Design Pattern
- **Page Object Model (POM):** The framework adheres to the POM design pattern, enhancing the readability, maintainability, and reusability of the codebase.
- Each web page is modeled as a dedicated Java class that encapsulates its elements and interactions, promoting modular and scalable test design.