#DataDrivenTestAutomation
 Production-ready data driven test automation based on TestNG and Selenium Java

##UI/API Test Automation Framework

This test automation framework is based on a hybrid model that contains both UI and API test cases. It focuses on easy test development through abstraction all of the low-level Selenium codes into a single class called UIActions. This easy test development allows even the most new and inexperienced tester to be able to start automating test cases without in-depth knowledge of Selenium.

Dependencies-
This automation framework has these dependencies following external libraries:
- rest-assured: used for sending and processing API requests and responses
- selenium-java: for browser automation through Java code
- webdrivermanager: for managing WebDriver executable files through Java code
- testng: for representing, organizing and managing test cases through Java code
- extentreports: for generating HTML test reports
- json-path: used for extracting data from the JSON format
- javafaker: for generating randomized Java test data on the fly.

Framework Project Structure Diagram
|-data                           #  all test data is stored here
|-images                         #  all images are stored here
|-reports                        #  all generated test execution reports are here 
|-src
   |---test
         |----java               #  all java source files are stored in this folder, including the following packages
                |-[+]base        #  all the base classes will be stored here
                |-[+]pages       #  all the page object classes will be stored here using page object model
                |-[+]testcase    #  all test classes will be stored here
                |-[+]utility     #  all the utility classes will be stored here  
|-.gitignore                     #  git ignore config file 
|-pom.xml                        #  project object model file for the maven software
|-READMD.md                      #  the file that is currently being viewed
|-testNG.xml                     #  TestNG configuration files for the test structures and groupings

######insert screenshot of project here

####Pre-requisites:
#####Download and install the following:
- Chrome or Firefox browser ( for viewing the report )
- JDK v1.8 +
- Apache Maven v3.0+
- Git v2.0+

###Inner Working of the Framework
This diagram shows the internal structure of the framework. 
Multiple parts of the code work together to bring successful automated test executions.

######insert diagram on inner working framework

###Set-up Instructions
This framework contains multiple test contexts such as smoke, regression, e2e etc. in the CI pipeline. 
Please refer to the following diagram for recommended test triggering points.

###How to run Tests
All tests are triggered through maven commands. This framework supports multiple different types of test executions such as smoke, regression, and end-to-end and is tested in different environments such as QA, Staging, and UAT. Please download the project and use the command line or terminal to navigate to the root directory of this project. Once you are in the root directory, please execute one of the following commands:

#####Executing specific tests
If you would like to execute a specific test which is stated on testNG.xml file Choose from the following command:

#####For invoking UI Smoke Test

mvn test -Dtestof="UISmokeTest"

#####For invoking API Smoke Test

mvn test -Dtestof="APISmokeTest"

###How to view the report
All the test execution reports are available as an HTML report in the following folder: 

 report
   |--[HTML] reports

Please navigate to this report file and open the report with your preferred browser.

#####insert screenshot of report here
   
   