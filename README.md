# How to run in Local Environment

## Requirements
- Maven 3.8.x
- Java 8 or higher

## Recommended IDE
- IntelliJ Community version - https://www.jetbrains.com/idea/download

## Checkout the code
Repository: https://github.com/jreysanmig/ubank-assessment


## Run Tests
Open a terminal or cmd at the project root

The basic command to run all the tests in default config with report:
```shell
mvn clean test site
```
To run specific test group according to UI tests only:
```shell
mvn clean test site -Dgroups="UI"
```
To run specific test group according to API tests only:
```shell
mvn clean test site -Dgroups="API"
```
To run tests with test/staging environment (test is default if not specified):
```shell
mvn clean test site -Denv="test"
mvn clean test site -Denv="staging"
```
To run test on specific browser (chrome is default if not specified):
```shell
mvn clean test site -Dbrowser="chrome"
mvn clean test site -Dbrowser="firefox"
mvn clean test site -Dbrowser="edge"
```

## Report
After running the tests, the report will be generated here and can be viewed with any browser:
```shell
{basedir}/target/site/ubank-test-report.html
```

## Configuration
.properties file to configure the baseURLs used in each environment can be found in:
```shell
{basedir}src/test/resources/configs
```

## Drivers
Drivers in this project may not be compatible with the local machine

In that case, download the driver based on the browser version of the local machine:
- chrome - https://chromedriver.chromium.org/downloads
- firefox - https://github.com/mozilla/geckodriver/releases
- edge - https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/

Replace the driver files in this project location:
```shell
{basedir}src/test/resources/drivers
```

## Notes
- Some tests in UI and API are purposely coded to fail to demonstrate a more realistic use of the report
