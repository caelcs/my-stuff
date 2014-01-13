MyStuff
========

##Overview

A way to expose a REST API mixing Java and Scala using JAXRS.

##Build and Run
The application supports to run in two modes, local and live.
I will call the path to the project MYSTUFF_HOME to a better understanding.

To compile and generate all the artifacts:
	
	cd /MYSTUFF_HOME
	mvn clean install

To run the application against local or live configuration you will need to setup an env variable.

###Local Setup
The local setup means that the application will use a HSQLDB in Memory Server Mode as Database. We need these steps running in a separate console for each of one.

Console 1: This will startup a HSQLDB in mem Server Mode

	cd /MYSTUFF_HOME
	mvn exec:java

Console 2: This will startup the CAS Server and will connect to the HSQLDB In-Memory Server. Creating/droping all the entities necesary.

	cd /MYSTUFF_HOME/cas-server
	mvn jetty:run-war -Denv=local

Console 3:This will startup My-Stuff-Server and will connect to the HSQLDB In-Memory Server and need a Cas Server instance already running. Will only update entities.

	cd /MYSTUFF_HOME/my-stuff-server
	mvn jetty:run-war -Denv=local

###Live Setup
The live setup means that the application will use a MySql as Database. We need these steps running in a separate console for each of one.

Console 2: This will startup the CAS Server and will connect to MySql Server.

	cd /MYSTUFF_HOME/cas-server
	mvn jetty:run-war -Denv=live

Console 3:This will startup My-Stuff-Server and will connect to MySql Server and need a Cas Server instance already running. Will only update entities.

	cd /MYSTUFF_HOME/my-stuff-server
	mvn jetty:run-war -Denv=live
