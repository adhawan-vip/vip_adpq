# vip_adpq
Our prototype is located at: https://vip-adpq.herokuapp.com/#/

Admin Credentials: admin/admin
User Credentials: user/user
Author Credentials: author/author
Reviewer Credentials: reviewer/reviewer

The prototype also allows new user account creation via the admin account or via self-registration.  Self-Registration will auto-assign the role of a "User".

The easiest way to create a new user is to use the Administration UI.  Log in using the admin credentials listed above.  
Once logged in, navigate to Administration -> User Management.  Click on "Create New User" and complete the required fields.
** _ By default, when an admin creates an account the password is the same as the user name. _ **

## Architectural Approach
In order to accelerate development on the prototype, the VIP team quickly organized around a proven, Java based, open source technology stack.  Our framework
of choice, JHipster is an industry leading development accelerator which packages and integrates several modern frameworks together including:
> Spring, Spring Boot, Spring Security as core

> AngularJS for UI

> Node.JS for glue code

> Liquibase for DB scripting including DDL and DML Scripts

> maven for packaging and dependency management

> heroku for deployment

> TravisCI for Continuous Integration

> Docker for containerized database deployment

Additionally, we used:
> Github for code versioning and management

> Swagger to document our API

> MySQL as the open source database

> H2 as the in-memory database
## Development Process
We used the following instructions to allow our developers (we call them Application Engineers or AEs) to quickly setup 
their local development environments.

Before you can build this project, you must install and configure the following dependencies on your machine:

1. Node.js: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.
2. Yarn: We use Yarn to manage Node dependencies.
   Depending on your system, you can install Yarn either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools.
You will only need to run this command when dependencies change in [package.json](package.json).

`yarn install`

Once Node and Yarn are installed, do the following

`yarn global add yo`

`yarn global add generator-jhipster`

`jhipster`

Run the following commands in two separate terminals to ensure that your browser
auto-refreshes when files change on your hard drive.

`./mvnw`
`yarn start`


## API Documentation
Our API is documented using Swagger.  Swagger is based on the OpenAPI specification.  Our API documentation can be found here: https://vip-adpq.herokuapp.com/#/docs

Please use login credentials: admin/admin to log in and view the API.

## Building for production

To optimize the vip_adpq application for production, run:

    mvnw -Pprod clean package

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Testing

To launch the application's tests, run:

    ./mvnw clean test

### Client tests

Client based unit tests are run by [Karma][] and written with [Jasmine][]. They're located in [src/test/javascript/](src/test/javascript/) and can be run with:

    yarn test
