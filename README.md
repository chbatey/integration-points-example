Integration Points Example
==========================

In a micro/mini/some what medium service architecture nearly every application you write tends to depend on other HTTP services, databases, third party libraries, etc.

To be reliable you need to handle the failure of your dependencies by either failing quickly or having a fallback response.

Netflix have a great library, Hystrix, for wrapping depenencies with their own thread pool, timeout and circuit breaker.

Yammer have built on that with Tenacity, which is a library for integrating Hystrix with Dropwizard. And lastly Breakerbox, which enables you to configure the thread pools, timeouts and thresholds for enabling the circuit breakers at runtime.

Sound complicated? I am not surprised, here's a simpleish example.

### The scenario

An integrating example application tht calls out to three dependencies.

<img src="https://raw.githubusercontent.com/chbatey/integration-points-example/master/images/Integration-Points-Architecture.png">

The dependencies are:

* A User service
* A Device service
* A PIN checker service

Imagine that the application was checking that a user is authorised to do something on a device given the PIN they entered.

The three dependencies are mocked using wiremock and are on the following URLs:
 
* /user/chris - retruns a description of a user
* /device/chris-iphone - returns a description of a device
* /pincheck - returns a string true or false

Each callout from the integrating application uses a TenacityCommand, and all the depdencies are registered with Tenacity. This allows them to be monitored and configured by Breakerbox.

### Running the example

The example is made up of three processes:

* The actual application
* An instance of wiremock instance that mocks all three depdencies
* An instance of Breakerbox that monitors and can be used to configure the integration points
