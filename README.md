# Household Information

### We use maven as a build tool. Hence, the following command will generate the jar in target folder

### Install maven by following instructions here
https://mkyong.com/maven/how-to-install-maven-in-windows/


`mvn clean package`

### To execute with default input csv

`java -cp target/household-0.0.1.jar com.expeditors.household.HouseholdApplication`

### To execute with csv custom input

`java -cp target/household-0.0.1.jar com.expeditors.household.HouseholdApplication /custom/file/path`

### Run all unit tests

`mvn clean test` 

### Note: Assumptions regarding the format of the input data

1. Data will be presented in a text file which is comma separated.
2. Each line of the text file will represent 1 person in the household.
3. Every person will be present in a new line in the text file.
4. Every line will contain strings in double quotes and strings will be separated by a comma.
5. Every line will represent 1 person in the following format:- 
   "first name","last name","street address, apartment number","city","state","age" 
6. Note that age is also input as string in the given file.
