# Abstract Integration Tests (AEM bundle)

This is an example of how to integrate an external service with a mock and a production implementation. Both implementations are fully working so you could switch between both implementations during development. 

Purpose of this project is only to demonstrate that general work pattern regarding external services, mock implementations and keeping them in sync with an abstract integration test. 

## General Setup:

Both service implementations are tested with the same test [AbstractSearchServiceIntegrationTest](bundle/src/test/java/df/example/ext/impl/AbstractSearchServiceIntegrationTest.java):

There are two inherited implementations: 

### Mock Implementation

The mock implementation [SearchServiceMockImpl](bundle/src/main/java/df/example/ext/impl/SearchServiceMockImpl.java) is tested with [SearchServiceMockIntegrationTest](bundle/src/test/java/df/example/ext/impl/SearchServiceMockIntegrationTest.java). Maven will find this test as a normal unit test and will run the test in the test phase (mvn test).

### Production Implementation

The production implementation [SearchServiceImpl](bundle/src/main/java/df/example/ext/impl/SearchServiceImpl.java) is tested with [SearchServiceIntegrationTest](bundle/src/test/java/df/example/ext/impl/SearchServiceIntegrationTest.java). This test is marked as an implementation test and will not be executed in the normal test phase. It's getting executed in the integration test phase (mvn verify).

### Service exposure

The Service is exposed to other application parts with a wrapper implementation [PublicSearchServiceImpl](bundle/src/main/java/df/example/ext/impl/PublicSearchServiceImpl.java). Of course this is optional and could also be done with Service Configuration for different run modes. 

### Test Servlet

There is a [Servlet](bundle/src/main/java/df/example/ext/servlets/GithubProjects.java) as a test usage of the service. It is available at /bin/example/github-projects?search=aem. The "search" param is optional.

************

General Info
========
This example is implemented as an AEM bundle, but the concept of integrating the service with the mock implementation and the abstract test works in any setup of course.

Building
--------

This project uses Maven for building. Common commands:

From the root directory, run ``mvn -PautoInstallPackage clean install`` to build the bundle and content package and install to a CQ instance.

From the bundle directory, run ``mvn -PautoInstallBundle clean install`` to build *just* the bundle and install to a CQ instance.

Using with VLT
--------------

To use vlt with this project, first build and install the package to your local CQ instance as described above. Then cd to `content/src/main/content/jcr_root` and run

    vlt --credentials admin:admin checkout -f ../META-INF/vault/filter.xml --force http://localhost:4502/crx

Once the working copy is created, you can use the normal ``vlt up`` and ``vlt ci`` commands.

Specifying CRX Host/Port
------------------------

The CRX host and port can be specified on the command line with:
mvn -Dcrx.host=otherhost -Dcrx.port=5502 <goals>


