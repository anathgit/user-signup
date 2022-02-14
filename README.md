Beta test user sign up APIs 
===================================================================
##Description
These are CRUD APIs that would allow Beta test users to sign up,get the details for
signed up users etc.

This application is designed keeping in mind API/Contract first approach and thus helps in 
parallel development of the consumer based on the Contract.

Application has two maven modules user-signup-api and user-signup-service to allow modularity.

The user-signup-api module defines the API using Open API standards.
The user-signup-service module contains the Controller/Service/DAO layer

In memory DB is defined under the resources folder in the service module in schema.sql

The application uses in-memory database but could use a relational/non-relational database based on
how scalabale and available we anticipate the application to grow in future.

The APIs for now are not secured but we need to add proper scope to each of the endpoint so that
only registered clients with necessary permission can only invoke these APIs

Also the application can be containrized with additinal data services needed.



Authors / Contributors
----------------------
Anath Chatterjee

### Language and Frameroks 
* JDK 1.8
* Spring Boot 2.6.3

### Build testuser-signup from user-signup directory
```
mvn clean install
```
### move from the target directory and run as below
```
mv /user-signup/user-signup-service/target/user-signup-service-0.0.1-SNAPSHOT.jar .
java -jar user-signup-service-0.0.1-SNAPSHOT.jar
```

### Sample create user Request
```
curl --location --request POST 'http://localhost:8080/v1/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"email":"testuser@gmail.com",
"first_name":"TEST",
"last_name":"USER",
"instagram_username":"test_user",
"twitter_username":"test_user",
"development_environment":"Java/AWS",
"address":{
"state":"CA",
"city":"San Jose"
}
}'
```

### Sample get user Request
```
curl --location --request GET 'http://localhost:8080/v1/users/2c4b725d-fd99-474b-8d02-945502775ceb' \
--header 'Accept: application/json'
```