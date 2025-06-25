## 🛍️ Project Name: Finology Automation
**Domain**: FinTech

---

## 📁 Project Structure

```
📦 Finology
├── 📁 cucumberReports/                                  # Cucumber BDD report ( HTML format and JSON format)
├── 📁 excelReport/                                      # Summary Excel reports of test case
├── 📁 extentReports/                                    # ExtentReports and Screenshots of test case
├── 📁 log4j/                                            # Log4J log output directory
│   ├── 📁 htmlLog/                                      # HTML log outputs
│   └── 📁 textLog/                                      # Plain text log outputs
├── 📁 src/
│   ├── 📁️ main/
│   │   ├── 📁 java/
│   │   │   └── 📁 in/finology/
│   │   │       ├── 📁 pages/                            # Page Object classes for UI interaction
│   │   │       │   ├── 📄 AccountDashboardPage.java
│   │   │       │   ├── 📄 AccountSubscriptionPage.java
│   │   │       │   ├── 📄 GoogleSignInPage.java
│   │   │       │   ├── 📄 HomePage.java
│   │   │       │   ├── 📄 LoginAuthPage.java
│   │   │       │   ├── 📄 LoginPage.java
│   │   │       │   ├── 📄 OTPVerificationPage.java
│   │   │       │   └── 📄 SignUpPage.java
│   │   │       ├── 📁 screenshot/                       # Classes for screenshot handling
│   │   │       │   └── 📄 Screenshot.java
│   │   │       └── 📁 utils/                            # Utility and helper classes
│   │   │           ├── 📁 driverFactory/                # WebDriver management
│   │   │           │   └── 📄 DriverFactory.java
│   │   │           ├── 📁 reporting/                    # Reporting utilities
│   │   │           │   ├── 📄 ExcelReports.java
│   │   │           │   └── 📄 ExtentReportsClass.java
│   │   │           ├── 📄 Base.java
│   │   │           ├── 📄 CommonMethods.java
│   │   │           ├── 📄 CustomAsserts.java
│   │   │           ├── 📄 DataFromExcel.java
│   │   │           ├── 📄 Main.java
│   │   │           ├── 📄 ObjectCreator.java
│   │   │           └── 📄 ObjectManager.java
│   │   └── 📁 resources/
│   │       ├── 📊 Data.xlsx                             # Input data generated during execution of test cases
│   │       └── 🖼️ Error_while_capturing_screenshot.png  # Error sample image
│   └── 📁 test/
│       ├── 📁 java/
│       │   └── 📁 in/finology/
│       │       ├── 📁 hooks/                            # Cucumber hook definitions (@Before, @After)
│       │       │   └── 📄 Hooks.java
│       │       ├── 📁 stepDefns/                        # Step definition files for feature steps
│       │       │   ├── 📄 AccountDashboard_Steps.java
│       │       │   ├── 📄 Login_Steps.java
│       │       │   ├── 📄 LoginAuth_Steps.java
│       │       │   ├── 📄 OTPVerification_Steps.java
│       │       │   └── 📄 SignUp_Steps.java
│       │       └── 🚀 testRunner/                       # TestNG/Cucumber runners
│       │           └── 📄 TestRunner.java
│       └── 📁 resources/
│           ├── 📁 features/                             # Gherkin .feature files for BDD scenarios
│           │   ├── 📃 accountdashboard.feature
│           │   ├── 📃 login.feature
│           │   └── 📃 signUp.feature
│           ├── 🛠️ log4j2.properties                     # Logging configuration file
│           └── 🧪 testng.xml                            # TestNG suite configuration
├── ⚙️ build.gradle                                      # Gradle build script
├── ⚙️ settings.gradle                                   # Gradle settings
└── 📘 README.md                                         # Project documentation
```