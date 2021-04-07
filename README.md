# tweetsmanager-service
Service to manage tweets

# First
You have to add the credentials to access the service in the twitter4j.properties configuration file where you can find how to obtain them here 
https://developer.twitter.com/en/docs/twitter-api/getting-started/getting-access-to-the-twitter-api

# Second
Then you have to build the config server with the configuration of this service, bootstrapping the configuration and adding the commented dependencies.

# Third
Then you have to install the eureka server to register and locate the service.

# Endpoints
- GET /api/tweets
- GET /api/tweets/{id}
- POST /api/tweets/{id}
- PATH /api/tweets/{id} 
- GET /api/hashtags
- GET /api/users
