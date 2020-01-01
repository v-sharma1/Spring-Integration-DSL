# Spring-Integration-DSL
Spring Integration demo using Java DSL

The application has in-memory database H2. 

The application boots up with list of authors maintained in database.

A POST RESTful web service allows us to add more authors in the database.

We decouple the flow between the Rest Controller and Service class by passing the data of new author through SI Channels.
The new author is bundled into a payload and sent over SI channels until it reaches the service class which acts as Service Activator
