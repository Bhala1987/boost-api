# Getting started with REST API testing with Serenity and Cucumber

This is a technical test project for Serenity with Cucumber and RestAssured using Java programming language & Maven
software project/build/dependencies management tool.

## Installation:

1. Oracle Java 17 / OpenJDK 17
2. Apache Maven 3.9.1 or latest
3. Git 2.40.0 or latest

## Built/Implementation:

1. Taken the test project from the provided
2. Removed the gradle related files
3. Using only Maven related files like pom.xml
4. Written the positive & negative test cases/scenarios in the cucumber feature file using gherkin format (GWT - Given,
   When & Then) as in /src/test/resources/features/api/*.feature
5. Implemented the step definitions for each step in the feature file on the stepdef class under /src/test/java
6. Implemented the methods on the *API class for hitting the endpoint and for assertions
7. Configured the serenity.conf file under the /src/test/resources directory where I have configured different
   environment uris in case
8. Written the Response based on the fields of the response with Getters & Setters of the Lombok plugin

## How to run this test project?

#### Locally:

1. mvn clean verify -Dcucumber.filter.tags=@e2e -DrerunFailingTestsCount=2
   (OR)
   mvn clean verify
2. Once ran successfully without any issues, we should be able to see the test report under the
   /target/site/serenity/index.html in a web browser
3. Then we can examine the passed & failed tests with summary of statistics of the tests & execution time

#### CI/CD in GitHub Actions Workflow:

1. Whenever there is any push or any pull request to the main branch, then the CI/CD workflow happens as action as in
   maven.yml file on GitHub
2. Once ran, we should be able to see the report artifacts
3. Can download the Artifacts which will be downloaded as zip file
4. Unzip and extract the files
5. Double-click on the index.html file to open in a browser to view the Serenity Test Report finally!!!