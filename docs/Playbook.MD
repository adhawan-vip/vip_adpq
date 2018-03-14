# Following the Playbook

## Play 1 - Understand what people need
Our project began with our users in mind. In parallel to establishing the tech stack and building the prototype code structure, our team immediately engaged users to document potential needs and expectations of the end product; going above and beyond the high-level system requirements (CDT Guidelines). Our user-centric design included the following tools/techniques:
* Creation of standardized User Interview Questionnaires (including different user roles, i.e. author, approver, admin). These interview questionnaires we designed to engage the user and to gather open and honest feedback as opposed to leading responses in a certain direction.
* Focused user group discussions on key roles and how the tool would be used in a live agency setting, this helped identify potential pitfalls or gap in our prototype in a real world application.
* Usability tests with focused user groups
* Research of existing knowledge/learning management tools, platforms, and products.
* Sprint reviews with extended audiences to gain additional perspectives and feedback

Involving different users with multiple techniques ensured our prototype was addressing our user’s needs as development progressed, and was a value input for our product backlog. Our backlog was prioritized and managed through daily scrum calls.

Our interviewees included the following to gather a well-rounded perspective:
* Jennifer Salazar, a former State employee with over 10 years supporting the State of CA.
* Jeremy Piontek & Hardian Muljadi, a Product Designers at a SaaS software company (Learning Management System).
* John Carmean, a Vice President of Marketing and Communications at a SaaS software company.

## Play 2 - Address the whole experience, from start to finish
Our choice of users took into account the different perspectives that different users have, such as our choice to include a long-term state employee with anaylst and management experience vs. the product team at a SaaS Learning Management Software Company. We interviewed our users early on to understand what their visions and expectations were for the knowledge management tool. We used iterative development to address the continuous feedback from our focused user groups, and held sprint restrospectives and usability tests to ensure the prototype was meeting the needs and requirements of our different user types.

## Play 3 - Make it simple and intuitive
Simplicity and ease of use was core to our product. We noted from our user centric design techniques (interviews, usability tests, etc.) that eventual adoption of the prototype would be heavily tied to the ability for a broad user base to use the tool without guidance or direction.  We utilized industry leading frameworks and technologies such as the open source Twitter Bootstrap library, Facebook's Node.js and other standard open source frameworks used in private industry.

## Play 4 - Build the service using agile and iterative practices
TeamVIP shipped a functioning MVP within 4 weeks.  We utilized daily scrum meetings, documenting our discussions and including users in the process via notes and updates.

We conducted usability tests and weekly sprint reviews open to a broad team.

We utilized open source tools and technologies for backlog elaboration and discussion.

We consistently reviewed the core goals of the project to ensure our product was being developed with our original goals in mind, but inclusive of iterative feedback from users.


## Play 5 - Structure budgets and contracts to support delivery

TeamVIP had an aggressive timeline to create a functioning prototype. We immediately set out to find free and open source tools and technologies to use in the development of our prototype, prioritizing and comparing the features and functionalities of each tool through research and discovery.  

## Play 6 - Assign one leader and hold that person accountable

We assigned a Product Owner responsible for the prototype.

## Play 7 - Bring in experienced teams

We utilized a high-performing team with key roles from the agile labor categories.

## Play 8 - Choose a modern technology stack

TeamVIP used a modern technology stack as shown [here](https://github.com/adhawan-vip/vip_adpq/blob/master/docs/TechStack.MD).

## Play 9 - Deploy in a flexible hosting environment
TeamVIP used a flexible hosting environment at our hosting vendor Heroku.  We used docker based deployments to allow for easy migration of hosting vendors, should the need arise.

## Play 10 - Automate testing and deployments
TeamVIP used automated testing and deployments.  Our team used Karma and Junit for automated testing.  We developed 175+ automated unit tests for our code which are integrated into our build.  At the end of each commit, unit tests are run automatically by Sonarcloud as shown [here](https://sonarcloud.io/dashboard?id=com.trustvip%3Avip-adpq).

Our deployments were automated using Travis-CI for continuous integration and continuous deployment as shown [here](https://travis-ci.org/adhawan-vip/vip_adpq).  

A full log of a sample build including output from docker is located [here](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242).  [This link](https://travis-ci.org/adhawan-vip/vip_adpq/jobs/352387242#L4552) shows docker output with the labeled images and push to the registry on Heroku.


## Play 11 - Manage security and privacy through reusable processes

We used the following reusable processes to account for security and privacy:
1. We used a standard security framework within our prototype (Spring Security).  Rather than try to re-invent the wheel, we adopted this industry leading framework to help ensure security/privacy functions.
2. We implemented role-based security, allowing users to view only the information relevant to their job functions.
3. We limited the amount of personal information collected from users and only collected information when absolutely necessary.

## Play 12 - Use data to drive decisions
Our prototype had a limited user base.  Even so, we collected data on which navigation paths were most popular across our application and worked to simplify access to those paths.  Additionally, we collected system usage metrics to determine appropriate sizing and monitored response times to ensure a smooth user experience.

## Play 13 - Default to open
TeamVIP used free and open source technologies extensively, and whenever possible.  This allows us to provide our prototype as open source.
