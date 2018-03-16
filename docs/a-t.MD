# Requirements A-T

## Requirement

*a. Assigned one (1) leader and gave that person authority and responsibility and held that person accountable for the quality of the prototype submitted;*

## How We Met the Requirement

We assigned a single Product Owner - Eric Scully who worked with the remainder of our Scrum team to build the prototype.

## Requirement

*b. Assembled a multidisciplinary and collaborative team that includes, at a minimum, five (5) of the labor categories as identified in Attachment B: PQVP AD-DS Labor Category Descriptions;*
## How We Met the Requirement
VIP assembled a multidisciplinary team including:

- Product Manager - Eric Scully
- Technical Architect - Ankur Dhawran
- Frontend Web Developer – Dmytro Kudriashov
- Backend Web Developer - Lynda Wacht
- Business Analyst – Ameet Joshi
- Interaction Designer / User Research/ Usability Tester - John Carmean
- Writer / Content Designer / Content Strategist - Meredith Ginsbach, Ned Dana
- Visual Designer - JP Mattingly
- Delivery Manager - Hank Chin
- Agile Coach - Ean Darbo

## Requirement

*c. Understood what people needed, by including people in the prototype development and design process;*
## How We Met the Requirement
VIP included our UX stakeholders, John Carmean and JP Mattingly from day one to establish the vision and needs for the prototype. In addition, we interviewed 3 different users/user groups with unique backgrounds and experiences to develop a baseline and sought their continuous feedback during our design and development.  Our interviews and feedback received are catalogued [here](https://github.com/adhawan-vip/vip_adpq/blob/master/UserCentricDesign.md)
## Requirement

*d. Used at least a minimum of three (3) “user-centric design” techniques and/or tools;*

## How We Met the Requirement
VIP utilized the following User Centric Design techniques/tools:

1. Research Interviews: One-on-one sessions with users were a fundamental component of our human-centered design process. By engaging with a diverse group of individuals including State employees involved with Knowledge Management, Sales teams from Knowledge Management Solutions and other user groups, we conceived of and designed a digital portal, focusing on the most important outcomes.

2. Concept Validation: With an overall concept of the service in mind, we presented high-level sketches and drawings to users. We solicited feedback early and often, spotting necessary changes soon and pivoting quickly in response. Concept validation helped make the design process efficient, iterative and accurate.

3. Usability tests with focused user groups.

## Requirement
*e. Used GitHub to document code commits;*
## How We Met the Requirement
Our GitHub repository is located [here](https://github.com/adhawan-vip/vip_adpq) and code commits can be found [here](https://github.com/adhawan-vip/vip_adpq/commits/master).

## Requirement
*f. Used Swagger to document the RESTful API, and provided a link to the Swagger API;*
## How We Met the Requirement
Our Swagger API documentation is located [here](https://vip-adpq.herokuapp.com/#/docs) and can be accessed using credentials admin/admin.


## Requirement
*g. Complied with Section 508 of the Americans with Disabilities Act and WCAG 2.0;*

## How We Met the Requirement

We used HTML and CSS in a manner that complies with the ADA and WCAG 2.0.  Additionally, we tested our prototype for 508 compliance using open source tools and remediated any issues that would block a screen reader from using our prototype.

## Requirement
*h. Created or used a design style guide and/or a pattern library;*
## How We Met the Requirement
VIP used several openly available UI/UX frameworks during its build.  Our system is based upon the openly available bootstrap libraries which allow us to design and build industry standard responsive websites.  We took advantage of the [PrimeNG](http://primefaces.org/primeng/) framework which is a UI/UX pattern library.  This open source library allows us access to 70+ components ranging from input controls such as the Quill.js WYSIWYG editor to charts and overlays that support our site.


## Requirement
*i. Performed usability tests with people;*
## How We Met the Requirement
VIP performed usability tests with the following individuals:
- John Carmean
- JP Mattingly
- Jonna Ward
- Nishant Agrawal
- David Teater
- Ned Dana
- Meredith Ginsbach


## Requirement
*j. Used an iterative approach, where feedback informed subsequent work or versions of the prototype;*
## How We Met the Requirement
VIP used an Agile approach towards in developing this prototype.  We embraced Scrum as our development methodology and performed work in the following Sprints:

1.  **Sprint 1: Setup base environment** – During this Sprint, we identified base technology stack compatible with CDT requirements.  For example, we used primarily open source technologies such as Spring and Angular to perform our work.  Additionally, we adopted the maven and webpack build systems for the back-end and front-end applications respectively.  We adopted Liquibase for our database management system, and MySQL as the database engine.  We identified PrimeNG as the UI/UX framework we wanted to leverage and used jHipster to put it all together.  We conducted research interviews during this Sprint and started populating a product backlog. We utilized Basecamp to manage our product backlog and as a collaboration tool.  Lastly, we identified non-functional requirements put forth by the CDT that should be part of our product backlog (such as Swagger API integration, User Role management etc.). See our team in action: [photo](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/team_vip_photos/Product%20Planning.jpg?raw=true).
2.  **Sprint 2: Core Functions:** Based on the items in our product backlog and discussions between the Scrum team and the product owner, we identified the core entities we wanted to build in our system.  We designed a data model using the open source JDL studio and used it to generate JDL files which could then be converted into code.  We completed the initial development of the system which included CRUD operations to the database, setting up base entities such as an Article, Related Documents, and Tasks within our system.  We also implemented various data fields that were necessary for the prototype to function.  We identified various field-level validations and implemented them with appropriate error messages.  At the end of this Sprint, we demonstrated this core application to our user base.  We received valuable input which resulted in creation of additional product backlog items.  We chose to include several of these items in the very next Sprint.
3. **Sprint 3: Security and Workflows:**  This sprint focused on user role creation and implementation of a workflow.  Based on input from our user group, we decided to create four user roles (Author, Reviewer, User, and Administrator).  Our workflow implementation allows Authors to create articles, Reviewers to review and/or publish articles, Users to view published articles and Administrators full access to the system.  During this Sprint we implemented various functions that each role should be able to perform including any field-level security for each role (such as the ability only for Users to provide ratings to articles).
4.  **Sprint 4:  Enhanced Features:** With the base system stable, it was time to build some features that our user community really wanted.  During this Sprint we added automatic conversion of articles to PDF files, organized the UI to be more user friendly, and added the ability for users to share articles with others via email.  We were able to re-use the base email system that was previously implemented for user registration.

Our [product backlog](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/Basecamp%20Product%20Backlog.pdf) and sprint [retrospective notes](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/PQVP%20Sprint%20Retrospectives.pdf) can be found in the GitHub document repository. 

See our team in action: [photo](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/team_vip_photos/Scrum%20Team_Sprint%20Review.jpg?raw=true).

## Requirement
*k. Created a prototype that works on multiple devices, and presents a responsive design;*
## How We Met the Requirement
Our prototype was developed with multiple devices in mind and continuously tested to ensure it presents a responsive design regardless of the device.  We tested our prototype against a variety of devices including iPads/iPhones/Android Phones etc.  Screenshots of each device with our prototype are [here](https://s3-us-west-1.amazonaws.com/909technologies/multi-device.PNG).  See our team in action: [photo](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/team_vip_photos/Backlog%20Elaboration_Accessibility.jpg?raw=true)

## Requirement
*l. Used at least five (5) modern and open-source technologies, regardless of architectural layer (frontend, backend, etc.);*
## How We Met the Requirement
VIP used several modern and open-source technologies in development of our prototype. A sample of the technologies used includes:

1.  Spring Security: We used Spring Security for authentication/authorization.  See our team in action: [photo](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/team_vip_photos/Prototype%20Security%20Mapping.jpg?raw=true).
2.  Spring Boot –: We used Spring Boot as a wrapper around the Spring framework to get going as quickly as possible with as little Spring configuration as possible.
3.  Angular -:  Angular combines declarative templates, dependency injection, and other really awesome features in keeping with our mindset of using best-of-breed tools.
4.  PrimeNG –:  wWe used PrimeNG as a UX framework for our prototype.  PrimeNG provides 70+ reusable Angular components to aid in creation of responsive beautiful applications.
5.  Maven/Webpack: we used Maven and Webpack as packaging systems for resolving dependencies with third party libraries and frameworks and to generate a single application that could then be packaged and deployed.
6.  Liquibase: We used Liquibase to manage our database schema without having to create DDL/DML scripts ourselves.  Liquibase helped us keep our schema in sync with our application code as part of a single deployment.
7.  Travis-CI: we used travis as a continuous integration tool.  Upon each checkin, Travis-CI kicked off a build and provided real-time notifications of build failures.  Our continuous integration pipeline executed unit tests for both Java and JavaScript code.
8.  Junit/Karma:  We used Junit and Karma as our test harnesses for server-side and client code respectively.  Overall we wrote 175+ unit tests to help enhance application quality.
9.  Sonarcloud: We used Sonarcloud to monitor code quality including code coverage via unit tests and to perform code reviews.  We remediated several issues identified by Sonarcloud through its PMD ruleset.
10. MySQL: We used MySQL as a database management system due to its open source nature and wide adoption.  MySQL was a natural fit with the remainder of the tech stack that we chose.
11. GitHub: We used GitHub as our code repository.  As of this writing, our team was well on its way to almost 100 code check-ins through our development activities.


## Requirement
*m. Deployed the prototype on an Infrastructure as a Service (IaaS) or Platform as Service (PaaS) provider, and indicated which provider they used;*
## How We Met the Requirement
We used [Heroku](http://www.heroku.com) as our Infrastructure provider at the PaaS level.

## Requirement
*n. Developed automated unit tests for their code;*
## How We Met the Requirement
As described above, our team used Karma and Junit for automated testing.  We developed 175+ automated unit tests for our code which are integrated into our build.  At the end of each commit, unit tests are run automatically by Sonarcloud as shown [here](https://sonarcloud.io/dashboard?id=com.trustvip%3Avip-adpq)

## Requirement
*o. Setup or used a continuous integration system to automate the running of tests and continuously deployed their code to their IaaS or PaaS provider;*
## How We Met the Requirement
We used Travis-CI for continuous integration.  Our travisTravis-ci CI configuration allows us to promote code to Heroku if all quality gates are met. An example of the automated tests/quality gates checks is demonstrated [here](https://travis-ci.org/adhawan-vip/vip_adpq)

## Requirement
*p. Setup or used configuration management;*
## How We Met the Requirement#
We used GitHub as our code repository, combined with BaseCamp, for configuration management. We leveraged  GitHub to manage code commits, as well as triggered builds and deployments from all check-ins and used various quality gates including unit tests, code quality checks and docker based deployments to ensure quality. Quality gates are part of our [Sonarcloud]((https://sonarcloud.io/dashboard?id=com.trustvip%3Avip-adpq)) setup and are run as part of [each build](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242#L2742)

## Requirement
*q. Setup or used continuous monitoring;*
## How We Met the Requirement
One compelling reason for choosing Heroku as our PaaS provider was their built in continuous monitoring for all applications deployed on their platform.  We used their continuous monitoring platform for our prototype, with the images [here](https://s3-us-west-1.amazonaws.com/909technologies/monitoring.JPG) as an example of the data/metrics being monitored.

## Requirement
*r. Deployed their software in an open source container, such as Docker (i.e., utilized operating-system-level virtualization);*
## How We Met the Requirement
Our build process ends with a containerized deployment via docker.  Once the docker image is built, our build process tags the built image with an appropriate tag so that it can be pushed out to the docker registry hosted on Heroku.  Finally, our build process uses the docker push command to push the built docker image and deploy it to the Heroku PaaS platform as shown below.
 
A full log of a sample build including output from docker is located [here](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242).  [This link](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242#L4552) shows docker output with the labeled images and push to the registry on Heroku.

## Requirement
*s. Provided sufficient documentation to install and run their prototype on another machine; and*
## How We Met the Requirement
We used the following instructions to allow our developers to quickly setup their local development environments.

Before you can build this project, you must install and configure the following dependencies on your machine:

1. Node.js: We use Node to run a development web server and build the project. Depending on your system, you can install Node either from source or as a pre-packaged bundle.
2. Yarn: We use Yarn to manage Node dependencies. Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.
After installing Node, you should be able to run the following command to install development tools. You will only need to run this command when dependencies change in package.json.

`yarn install`

Once Node and Yarn are installed, do the following

`yarn global add yo`

`yarn global add generator-jhipster`

`jhipster`

Run the following commands in two separate terminals to ensure that your browser auto-refreshes when files change on your hard drive.

`mvnw`

and separately

`yarn start`

## Requirement
*t. Prototype and underlying platforms used to create and run the prototype are openly licensed and free of charge.*
## How We Met the Requirement
VIP’s prototype and underlying platforms are openly licensed and free of charge under the [Apache License 2.0](https://github.com/adhawan-vip/vip_adpq/blob/master/LICENSE).