sportEvent
==========

Build application:
- in command line go to the project folder: cd .../pa165-sportEvent-POM
- mvn clean install 


Run web application:
- in command line go to the folder: cd .../pa165-sportEvent-Presentation 
- write command:  mvn tomcat7:run
- web-browser:  http://localhost:8080/pa165/


Run desktop application:
-in command line go to the folder: cd .../pa165-sportEvent-Client 
- then: mvn exec:exec 

Authentication and authorization:
class Sportsman has fields: username, password and user role (ROLE_USER or ROLE_ADMIN)
ROLE_ADMIN  -  could add Sport, Event, Sportsman
ROLE_USER - could register to events

For SOAP you should add to table Sportsman row with data: "rest", "rest", "ROLE_ADMIN"  (username, password and user role)
Users with ROLE_ADMIN you cant see in GUI or WEB application.

