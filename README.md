# ElectricityFarm

Rest API to calculate capacities of electricity farms by time ranges.

### Technology Description
Each point of tech stack for current project was chosen according to requirements or 
for simpler development, maintainability, and better scalability.

- Java, Springboot, and Postgres - typical stack for Java web application
- Liquibase - database filling and management by changesets
- JUnit5
- TestContainer - library for setting up the database for integration tests at the moment when tests are started
- DBRider - tool for easier checking database datasets while integration testing
- Swagger - tool for generating API docs

### How to run the app
1. Install docker if you don't have it.
2. Run database with docker-compose
   ``docker-compose -f docker-compose-postgresql.yml up ``
3. Run the app from `Application.class`
4. For easier testing examples of http requests are prepared in the file `examples.http` in the project root

### How to run the tests
1. Create start JUnit config which run all tests in the project

TODO: fill and clean integration db before each test and after  

### How to check API doc
1. Api docs are already generated to file `./api_docs_swagger/index.html`. To check the documentation open this file in browser

To regenerate api docs script should be executed:

`bash ./api_docs_swagger/generate_api_docs.sh`

### Not implemented but in plan
1. JWT request authentication
2. Fill integration DB by DBRider
3. Cover by tests all sensitive cases