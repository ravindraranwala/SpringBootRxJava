# SpringBootRxJava
This application implements a Spring Boot micro service using Rx-Java.
All the external invocations are asynchronously done using RxJava components and programatic devices.
This explains you how to bridge the gap between RxJava and Spring Boot while integrating them together.
First check out the project into your local file system. Then move into the JavaRxDemo directory (cd ./JavaRxDemo)
Then exevute the mvn clean install command to build the project.
Once it is successfully built, enter this command while being at the same directory.  mvn spring-boot:run
The above command will run the micro service project after deploying it to an embedded servlet container.
Now to get the swagger UI hit this url in your address bar. http://localhost:8080/swagger-ui.html#!/
You will be shown with all the REST methods exposed by the service with their definitions. 
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
Alternatively you can just hit the following url in your browser address bar and see the same result. http://localhost:8080/api/currencyconverter/rates?symbol=USD,CGBP
