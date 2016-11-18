# Spring MVC Sample Application
Sample Spring MVC Application with Spring Boot, Rest APIs and JWT Authentication that can work with a Single Page Applications written in frameworks like AngularJS, React JS etc

## What does it contain?
1. It contains a simple Entity **User** with following details
    * Name
    * Email
    * Date of Birth
    * User Type - Enum of Teacher/Student
    * Password
2. Simple UserRepository to handle User entity along a single UserService service to handle user related operations
3. It has following REST end points to serve the application
    * /register - Registering new User
    * /login - User login using email/password
    * /profile - GET user profile and PUT to edit the profile
4. All REST apis are contained in a single controller **RestApiController**
5. JWT authentication Filter **PreAuthFilter** to check for JWT token for routes which need authenticated user to access (except /register and /login routes)


## How to run the application?
1. It is a standard maven application with spring-boot-starter-web as the starting point.
    * Compile application commandline with `mvn compile`
    * Package the application into single jar file using `mvn package`
    * Run the single jar file as `java -jar target/<jar-file-name>.jar`. It starts the application in port 8080 on your machine.


## Which database does it use?
1. It uses **HSQLDB** as the database now. So upon restart of the application data gets lost.
2. I couldn't get **H2** running as the database due to some bug in spring auto configuration of entity manager. Refer [here](http://stackoverflow.com/questions/26548505/org-hibernate-hibernateexception-access-to-dialectresolutioninfo-cannot-be-null), [here](https://groups.google.com/forum/#!topic/google-appengine-stackoverflow/R-90OZcuMWg), [here](https://github.com/spring-projects/spring-boot/issues/4012) for more details. But finally moved to HSQLDB for timebeing

## Can I use Mysql for this application?
YES for sure! I will integrate the Mysql soon.

## Which JWT Library is used for JWT authentication?
I am using [jjwt](https://github.com/jwtk/jjwt/tree/0.7.0) for JWT authentication.
