# SpringBootRxJava
This application implements a Spring Boot micro service using Rx-Java.
All the external invocations are asynchronously done using RxJava components and programatic devices.
This explains you how to bridge the gap between RxJava and Spring Boot while integrating them together.


First check out the project into your local file system. 
Then move into the JavaRxDemo directory (cd ./JavaRxDemo)
Then exevute the mvn clean install command to build the project.
Once it is successfully built, enter this command while being at the same directory.  mvn spring-boot:run
The above command will run the micro service project after deploying it to an embedded servlet container.
Now to get the swagger UI hit this url in your address bar. http://localhost:8080/swagger-ui.html#!/
You will be shown with all the REST methods exposed by the service with their definitions. 

There are two different usecases implemented by this application.

Usecase1: Calling an external Currency Conversion API asynchronously to get the currency rates for given set of currencies.

This application gives you currency rates for the currencies you enter compared with a sensible base currency, which is Euro in this case.
Give following as the input query parameters in swagger ui. 'USD,GBP'
You should get the following as the response.
{
  "base": "EUR",
  "date": "2016-12-20",
  "rates": {
    "GBP": 0.83978,
    "USD": 1.0364
  }
}
Alternatively you can just hit the following url in your browser address bar and see the same result. http://localhost:8080/api/currencyconverter/rates?symbol=USD,GBP



Usecase 2: Create a Student entity and retrieve a Student entity given the name.
This demonstrates calling a MongoDB database using RxJava asynchronously within the SpringBoot microservice.
I assume that you have a MongoDB instance up and running in your local machine on default port. For some reason if you have a
different configuration in your MongoDB instance to which you are planning to connect change the following peoperties in the application.properties
file located under $PROJECT_ROOT/src/main/resources directory.

spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=studentdb

Start the micoservice and check out the swagger uri for the service definition as mentioned above.
Now to create a new Student in our system POST the following JSON payload aiming at the url http://localhost:8080/api/students

{
  "age": 28,
  "gpa": 3.7,
  "name": "Will",
  "stream": "Science"
}

You get the following response, and the status code.
"SUCCESS"
HTTP_SC: 200


Then to fetch a student given the name send the following GET request.
http://localhost:8080/api/students?name=Christina

You may get the response as below.
{
  "name": "Christina",
  "age": 28,
  "gpa": 3.7,
  "stream": "Arts",
  "id": "586c98eff7df1a16e849c398"
}

HTTP_SC: 200
