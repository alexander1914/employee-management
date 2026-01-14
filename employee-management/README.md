**Employee Management**: is an application to learn about RESTFull api to explain One-to-many-relationship.

## Definitions tech about this application

JDK 25, Spring boot 4.0.0, JPA, RESTFull, Swagger 3.0

### Set up environment to start application
Following these steps to configure this spring boot your machine

* [Set Up Oracle DB for JPA](https://medium.com/@ishinihettiarachchiuv/how-to-connect-a-spring-boot-project-and-oracle-db-locally-and-create-tables-using-the-jpa-f0e1891ef470)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.5.6/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Oracle DB with Docker](https://medium.com/xp-inc/dica-r%C3%A1pida-criando-base-de-dados-oracle-vers%C3%A3o-21-3-0-no-docker-357b05754b84)

### Spring Data JPA 
Spring Data JPA is a framework that simplifies database access in Spring Boot applications by providing an abstraction 
layer over the **Java Persistence API (JPA)**. 
It enables seamless integration with relational databases using **Object-Relational Mapping (ORM)**, 
eliminating the need for boilerplate SQL queries.

With Spring Data JPA, developers no longer need to:

* Manually write DAO implementations.
* Manage EntityManager directly.
* Write repetitive CRUD queries

**Data Transfer Object (DTO)**: is an object that carries data between processes. 
When you're working with a remote interface, each call is expensive. 
As a result, you need to reduce the number of calls.