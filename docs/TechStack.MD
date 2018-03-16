# Technology Stack for our ADPQ Prototype
VIP used several modern and open-source technologies in development of our prototype. An application-level architecture diagram showing the various layers and components of our application is located [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/Application%20Architecture.pdf).  A sample of the technologies used includes:

1.  Spring Security: We used Spring Security for authentication/authorization.
2.  Spring Boot: We used Spring Boot as a wrapper around the Spring framework to get going as quickly as possible with as little Spring configuration as possible.
3.  Angular:  Angular combines declarative templates, dependency injection, and other really awesome features in keeping with our mindset of using best-of-breed tools.
4.  PrimeNG:  wWe used PrimeNG as a UX framework for our prototype.  PrimeNG provides 70+ reusable Angular components to aid in creation of responsive beautiful applications.
5.  Maven/Webpack: we used Maven and Webpack as packaging systems for resolving dependencies with third party libraries and frameworks and to generate a single application that could then be packaged and deployed.
6.  Liquibase: We used Liquibase to manage our database schema without having to create DDL/DML scripts ourselves.  Liquibase helped us keep our schema in sync with our application code as part of a single deployment.
7.  Travis-CI: we used travis as a continuous integration tool.  Upon each checkin, Travis-CI kicked off a build and provided real-time notifications of build failures.  Our continuous integration pipeline executed unit tests for both Java and JavaScript code.
8.  Junit/Karma:  We used Junit and Karma as our test harnesses for server-side and client code respectively.  Overall we wrote 175+ unit tests to help enhance application quality.
9.  Sonarcloud: We used Sonarcloud to monitor code quality including code coverage via unit tests and to perform code reviews.  We remediated several issues identified by Sonarcloud through its PMD ruleset.
10. MySQL: We used MySQL as a database management system due to its open source nature and wide adoption.  MySQL was a natural fit with the remainder of the tech stack that we chose.
11. GitHub: We used GitHub as our code repository.  As of this writing, our team was well on its way to almost 100 code check-ins through our development activities.
12. Docker: We used Docker as a container for our deployment.  Our docker image is built via our continuous integration pipeline which includes the following flow: GitHub (Code Check-In) -> Travis CI (runs JUnit/Karma tests) -> Sonarcloud (runs code quality/code coverage checks) -> Docker (creates deployment image) -> Heroku (deployed!)