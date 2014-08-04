Integration Points Example
==========================

In a micro/mini/somewhat-medium service architecture nearly every application you write tends to depend on other HTTP services, databases, third party libraries, etc.

To be reliable you need to handle the failure of your dependencies either by failing quickly or having a fallback strategy.

Netflix have a great library, [Hystrix](https://github.com/Netflix/Hystrix), for wrapping dependencies with their own thread pool, timeout and [circuit breaker](http://martinfowler.com/bliki/CircuitBreaker.html).

Yammer have built on that with [Tenacity](https://github.com/yammer/tenacity), a library for integrating Hystrix with Dropwizard. Lastly you can add [Breakerbox](https://github.com/yammer/breakerbox), which enables you to dynamically configure the thread pools, timeouts and thresholds that trigger the circuit breakers.

Sound complicated? I am not surprised, so here's a simpleish example.

### The scenario

This example features a simple web application which calls out to three other HTTP services.

<img src="https://raw.githubusercontent.com/chbatey/integration-points-example/master/images/Integration-Points-Architecture.png" />

The dependencies are:

* A User service
* A Device service
* A PIN checker service

In the real world this application could be checking that a user is authorised to do something on a device given the PIN they entered.

The three dependencies are mocked using wiremock and are on the following URLs:
 
* /user/chris - returns a description of a user
* /device/chris-iphone - returns a description of a device
* /pincheck - returns a string true or false

Each callout from the integrating application uses a TenacityCommand and all the dependencies are registered with Tenacity which allows them to be monitored and configured by Breakerbox.

### Running the example

Before you get started, clone this project and have a terminal ready with three tabs or windows. The only system dependencies are Java and Maven.

The example is made up of three processes:

* The integrating application
* An instance of wiremock that mocks all three dependencies
* An instance of breakerbox that monitors and can be used to configure the integration points

#### Starting Wiremock (the dependencies)

The bundled wiremock is configured to run on port 9090. To start it run:


```
cd wiremock
./startWiremock.sh
```

You should see output like:


```
2014-08-02 15:36:00.000:INFO::Started DelayableSocketConnector@0.0.0.0:9090
```

You can verify that it is up by going to [http://localhost:9090/__admin](http://localhost:9090/__admin). This page shows all the default wiremock responses for the three dependencies.


#### Starting the actual application

By default the application runs on port 7070 and has a single endpoint: /integrate

Firstly, build the application with the following command:

```
mvn clean package
```

Then to run the application:

```
java -jar ./target/integration-example-1.0-SNAPSHOT.jar server integration-example.yml
```

Alternatively if you are using an IDE then just run the main class: IntegrationApplication with the same arguments.

You will see a stacktrace; don't worry about this. This is because our application polls breaker box for configuration updates, but we have not yet started breakerbox.

#### Running Breakerbox

Like for wiremock there is a helper script to start breakerbox. It will run on port 8080.


```
cd breakerbox
./runBreakerbox.sh
```

Then go to (http://localhost:8080)[http://localhost:8080]

Because the application has not yet published any statistics, you should see a dashboard with "Loading...". 

To see some data go to Dashboard -> Breakerbox. This is breakerbox monitoring itself, how weird!

Now let's go to our Dashboard -> Integration Example.

Then hit [http://localhost:7070/integrate](http://localhost:7070/integrate) and the dashboard should come to life.

<img src="https://raw.githubusercontent.com/chbatey/integration-points-example/master/images/dashboard.png" />

For informaton on what the dashboard is showing, go to the Hystrix website.
