# Data Conversion API

This is a small POC for converting JSON to Custom Typed-XML Format.

### Build Steps

1. Checkout the github project.
2. Run **mvn spring-boot:run**
3. The service will run on http://localhost:8080/json/convert2xml

### Test Steps

1. One should be able to test by posting a valid json in the request body and hitting the above URL using a http utility.
2. To test via Swagger, goto http://localhost:8080/swagger-ui.html#!/JSON_Conversion_Controller/convert2XMLUsingPOST 
3. Paste the JSON you want to convert and click on **Try it out!**
4. The response should appear in the Response Body section
5. To test via maven, run **mvn test**. It will run tests for valid and invalid JSON inputs.
