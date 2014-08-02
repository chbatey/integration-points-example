Integration Points Example
==========================

In a micro/mini/some what medium service architecture nearly every application you write tends to depend on other HTTP services, databases, third party libraries, etc.

To be reliable you need to handle the failure of your dependencies by either failing quickly or having a fallback response.

Netflix have a great library, Hystrix, for wrapping depenencies with their own thread pool, timeout and circuit breaker.

Yammer have built on that with Tenacity, which is a library for integrating Hystrix with Dropwizard. And lastly Breakerbox, which enables you to configure the thread pools, timeouts and thresholds for enabling the circuit breakers at runtime.

Sound complicated? I am not surprised.

Here is a simple example application that uses all of the above to monitor three integration points.
