# Knowledge Management Tool - VIP's Working Prototype
Our prototype is located [here](https://vip-adpq.herokuapp.com/#/).  Four roles types are supported and capabilities of each role is included on the landing page of the prototype (See "How to get around").

Credentials (username/password) for each role:
* Admin: admin/admin
* User: user/user
* Author: author/author
* Approver: approver/approver

We have included a video demo of core functionality located [here](TODO).

In addition, we have included a response to the core requirements/guidelines provided by CDT [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/Requirements.MD).

# Technical Approach Narrative
## Requirement
*Vendors shall write a brief description/narrative, no greater than 2,000 words, of the Technical Approach used to create their Working Prototype and place this description/narrative in the README.md file located in the root directory of its GitHub repository. This 2,000 word limit applies to the Technical Approach description/narrative only.*
## How We Met the Requirement
TeamVIP handpicked a small and versatile core team to develop the Knowledge Management Tool prototype. While most of our team was co-located in Sacramento, CA, we also had a developer located at our Reston, VA office to demonstrate our ability to collaborate using open source tools and within different time zones. See our team in action: [photo](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/team_vip_photos/Colocation%20Collaboration.jpg?raw=true).  We further augmented our team with a broad user base with different backgrounds, experience, and expertise; this team was dispersed across the United States and from different industries to give us a broad perspective. We chose this structure to demonstrate our ability to work as a cohesive group, and avoid potential groupthink or shortfalls of a small team by having a diverse user community to provide continuous feedback. 

From a technical perspective, we defaulted to a [modern, open stack](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/TechStack.MD) which would be compliant with the RFI requirements as well as our own desire to develop a cutting edge prototype.  We implemented a fully automated deployment pipeline from Github -> Travis-CI -> SonarCloud -> Docker -> Heroku.  Our pipeline performed several industry standard functions including validating the build using unit tests (JUnit and Karma), as well as running code quality checks (via Sonar).  We architected our application to use open source technologies including Spring Boot and Angular.  To further beautify our application we used the PrimeNG UI/UX component framework and enhanced the look and feel of our application.  We used Quill.js as a WYSIWYG editor, used dropwizard's metrics library to perform in-app monitoring of the JVM and we used Liquibase to version, control and ensure that our DB Schema stayed in sync with our code.  We used Heroku as our PaaS provided to allow for automated deployments.

In order to make sure everything works just so, we had to perform the following integrations:
1. GitHub to Travis-CI: trigger automated builds and unit tests upon every check in to ensure that our code was always buildable.
2. Travis-CI to SonarCloud: Run code quality checks and collect metrics about the health of our code.
3. Travis-CI to Heroku: Every build results in a deployment.

Since this is a prototype, we used a single branch within GitHub across all three developers.  This allowed us to reduce the overhead of constantly addressing pull requests.  Our single code branch remained build-able almost 100% of the time!

* a.  We assigned a single Product Owner - Eric Scully who worked with the remainder of our Scrum team to build the prototype.
* b.  TeamVIP assembled a multidisciplinary team including:
  * Product Manager - Eric Scully
  * Technical Architect - Ankur Dhawan
  * Frontend Web Developer – Dmytro Kudriashov
  * Backend Web Developer - Lynda Wacht
  * Business Analyst – Ameet Joshi
  * Interaction Designer / User Research/ Usability Tester - John Carmean
  * Writer / Content Designer / Content Strategist - Meredith Ginsbach, Ned Dana
  * Visual Designer - JP Mattingly
  * Delivery Manager - Hank Chin
  * Agile Coach - Ean Darbo
* c.  TeamVIP included our UX stakeholders, John Carmean and JP Mattingly from day one to establish the vision and needs for the prototype. In addition, we interviewed 3 different users/user groups with unique backgrounds and experiences to develop a baseline and sought their continuous feedback during our design and development. Our interviews and feedback received are catalogued [here](https://github.com/adhawan-vip/vip_adpq/blob/master/UserCentricDesign.md).
* d.  TeamVIP utilized the following User Centric Design techniques/tools:
  * i.  Research Interviews: One-on-one sessions with users were a fundamental component of our human-centered design process. By engaging with a diverse group of individuals including State employees involved with Knowledge Management, Sales teams from Knowledge Management Solutions and other user groups, we conceived of and designed a digital portal, focusing on the most important outcomes.
  * ii. Concept Validation: With an overall concept of the service in mind, we presented high-level sketches and drawings to users. We solicited feedback early and often, spotting necessary changes soon and pivoting quickly in response. Concept validation helped make the design process efficient, iterative and accurate.
  * iii.    Usability tests with focused user groups.
  * iv. Research: TeamVIP and our user groups set out to see what features/functions are available (and not available) in today’s learning/knowledge management marketplace. Example sites and tools include: Wikipedia, Meridian KS, Blackboard, Quora, Confluence, Rally, SharePoint, Google, OpenSesame and more. 
* e.  TeamVIP used GitHub repository. Our GitHub repository is located [here](https://github.com/adhawan-vip/vip_adpq) and code commits can be found [here](https://github.com/adhawan-vip/vip_adpq/commits/master).
* f.  TeamVIP used Swagger. Our Swagger API documentation is located [here](https://vip-adpq.herokuapp.com/#/docs) and can be accessed using credentials admin/admin.
* g.  TeamVIP used HTML and CSS in a manner that complies with the ADA and WCAG 2.0. Additionally, we tested our prototype for 508 compliance using open source tools and remediated any issues that would block a screen reader from using our prototype.
* h.  TeamVIP used several openly available UI/UX frameworks during its build. Our system is based upon the openly available bootstrap libraries which allow us to design and build industry standard responsive websites. We took advantage of the PrimeNG framework which is a UI/UX pattern library. This open source library allows us access to 70+ components ranging from input controls such as the Quill.js WYSIWYG editor to charts and overlays that support our site.
* i.  TeamVIP performed usability tests with the following individuals:
  * John Carmean
  * JP Mattingly
  * Jonna Ward
  * Nishant Agrawal
  * David Teater
  * Ned Dana
  * Meredith Ginsbach
* j.  TeamVIP used an Agile approach towards in developing this prototype. We embraced Scrum as our development methodology and performed work in the following Sprints:
  * i.  Sprint 1: Setup Base Environment
  * ii. Sprint 2: Core Functions
  * iii.    Sprint 3: Security and Workflows
  * iv. Sprint 4: Enhanced Features
  * Our [product backlog](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/Basecamp%20Product%20Backlog.pdf) and sprint [retrospective notes](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/PQVP%20Sprint%20Retrospectives.pdf) can be found in the GitHub document repository. 
* k.  TeamVIP’s prototype was developed with multiple devices in mind and continuously tested to ensure it presents a responsive design regardless of the device. We tested our prototype against a variety of devices including iPads/iPhones/Android Phones etc. Screenshots of each device with our prototype are [here](https://s3-us-west-1.amazonaws.com/909technologies/multi-device.PNG).
* l.  TeamVIP used several modern and open-source technologies in development of our prototype. Please see [this link](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/TechStack.MD) for our modern and open-source technologies used.
* m.  TeamVIP used Heroku as our Infrastructure provider at the PaaS level.
* n.  Our team used Karma and Junit for automated testing. We developed 175+ automated unit tests for our code which are integrated into our build. At the end of each commit, unit tests are run automatically by Sonarcloud as shown [here](https://sonarcloud.io/dashboard?id=com.trustvip%3Avip-adpq).
* o.  We used Travis-CI for continuous integration. Our Travis-CI configuration allows us to promote code to Heroku if all quality gates are met. An example of the automated tests/quality gates checks is demonstrated [here](https://sonarcloud.io/dashboard?id=com.trustvip%3Avip-adpq).
* p.  We used GitHub as our code repository, combined with BaseCamp, for configuration management. We leveraged GitHub to manage code commits, as well as triggered builds and deployments from all check-ins and used various quality gates including unit tests, code quality checks and docker based deployments to ensure quality. Quality gates are part of our Sonarcloud setup and are run as part of each build
* q.  One compelling reason for choosing Heroku as our PaaS provider was their built in continuous monitoring for all applications deployed on their platform. We used their continuous monitoring platform for our prototype, with the images [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/monitoring.JPG?raw=true) as an example of the data/metrics being monitored.
* r.  Our build process ends with a containerized deployment via docker. Once the docker image is built, our build process tags the built image with an appropriate tag so that it can be pushed out to the docker registry hosted on Heroku. Finally, our build process uses the docker push command to push the built docker image and deploy it to the Heroku PaaS platform. A full log of a sample build including output from docker is located [here](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242). [This link](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242#L4552) shows docker output with the labeled images and push to the registry on Heroku.
* s.  We used [these instructions/documentation](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/a-t.MD#how-we-met-the-requirement-18) to allow our developers to quickly setup their local development environments.
* t.  TeamVIP’s prototype and underlying platforms are openly licensed and free of charge.


## Requirement
*Documentation must show code flow from client UI, to JavaScript library, to REST service to database, pointing to code in the GitHub repository.*
## How We Met the Requirement
An example of the code flow is provided [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/CodeFlow.MD).

## Requirement
*In addition to the description/narrative above, the Vendor must demonstrate that they followed the US Digital Services Playbook (visit https://playbook.cio.gov or see Attachment 2: US Digital Services Playbook) by providing evidence in the repository.*

## How We Met the Requirement
Our core usergroup was essential to our process from the very beginning and throughout the development. We closely followed the US Digital Services playbook to ensure the proper care and steps were taken to improve our prototype. We did continuous checks (daily scrum calls) to ensure we were aligning ourselves with the plays appropriately. How we aligned with each of the plays in the US Digital Services Playbook is located [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/Playbook.MD). 


## Requirement

The README.md file should also make reference to the following: (a-t)

## How We Met the Requirement
TeamVIP has included the response to each of the RFI Technical Narrative Requirements A-T above. For the full list including the description of each requirement, please [click here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/a-t.MD)
