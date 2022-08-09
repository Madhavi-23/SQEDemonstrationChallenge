## Demo Challenge

#### Instructions
1. Complete the project setup as listed below
2. Complete the Excerise
3. Email a synopsis of your work and the link to your git repo containing the completed exercise to: sqedemonstrationchallenge@nbcuni.com


#### Expectations
We will be evaluating
1. Naming conventions
2. Code readability
3. Code encapsulation
4. Code structure and organization
5. Quality of test cases
6. Variety  of testing types (examples: boundary, happy path, negative, etc) 


#### Technologies
1. Java
2. Selenium
3. TestNG
4. Any other technologies you see fit.
5. Please do not use a BDD framework.

#### Project Setup
1. Clone this project to your git account in a public repo
2. Setup the project in your IDE
3. Open the index.html file from src/test/resource/files in a browser
4. Copy the url from the browser and update the url value in src/test/resource/config.properties to be the copied url.
5. In src/test/resources update the config.properties file platform for your OS.
6. From command line run mvn clean install -U -DskipTests
7. Make sure you can run the DemoTest and chrome launches.  You may need to update the chromedriver in /src/test/resources/chromedriver/ to the version that works with your browser
   https://chromedriver.chromium.org/


#### Exercise
1. Use the site at the index.html
2. There are helper locators provided for you in the src/test/resource/files/locators.txt file.
3. In the Test Cases section below:
  - List all of the test cases you think are necessary to test the sample page
  - Note any defects or issues observed
4. Code up a few examples of:
  - At least one happy path case placing an order
  - At least one error case
5. When complete please check your code into your public git repo

#### Test Cases

 1. Validate Pizza type options shown on the form.
 2. Validate topping  options shown on the form.
 3. Ensure order is placed successfully when all the inputs are specified and a payment instrument is provided. Try both Credit Card and Cash Payment options. Ensure cost is computed correctly to 2 decimal place.
 4. Ensure an order cannot be placed when 
      a. No pizza type is selected.
      b. Topping options do not match the pizza type selection.
      c. Quantity is not specified.
      d. Payment instrument is not chosen.
      e. Name and Phone fields are not provided.
 5. Ensure user can enter a quantity that ranges between 1 and 99999 (in the absence of requirements, i am taking this from the definition of quantity text boxes maxlength). Any thing else should result in a failure.
 6. Ensure users can only order whole pizzas.
 7. Ensure users can select only one payment instrument.
 8. Ensure cost is computed accurately. Check for rounding errors.
 


 #### Defects

 1. Reset does not reset toppings.
 2. Pizza Type selection, does not reflect options made available for toppings. For instance, toppings can be selected, even for a Pizza which does not come with toppings. And in cases where pizza type allows for topings, user is allowed to place an order even with specifying the topics. No errors or warnings.
 3. No error when placing an order, with both payment options selected. This ihould not be allowed in the first place.
 4. No error thrown when Pizza Type is not selected.
 5. No format errors for email and phone text fields.
 6. No errors when quantity is not specified.
 7. No errors when fractional quantity is specified.
 8. No errors when negative quantity is specified.
 9. No errors when placing an order with out selecting a payment instrument.
 10 The dialog box does not have a visual que of whether it is informational / Error or Warning.

