# VOIS Task

## Installation

clone the Project using
```bash
git clone https://github.com/abdelnaby1/VOIS_Task.git
```
###### Dependencies installation
After cloning the project you need to install all the dependencies\
if you are using `IntelliJ` go to the maven icon and click the refresh button
## Task Structure

##
### Part 1 -  which includes automation script for Task1 and Task2
will be found inside the src folder and to run it using the maven build tool\
use the following command
```bash
mvn clean test -Ppart1
```
and to see your report will be inside reports folder,\
it's a Extent Report.\
-Ppart1 is a profile id that is mapped to run the testng.xml file
##
### Part 2 - test cases for the required scenarios
will be found inside the testCases.txt file which contains a url to google sheet of test cases

##
### Part 3 - Bonus Task
will be found inside the src/test/java/tests/Apis folder\
to run the automation script,use the following command
```bash
mvn clean test -Papi
```
-Papi is a profile id that is mapped to run the api.xml file

##
## Thank you waiting for your feedback