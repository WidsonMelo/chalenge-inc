# Chalenge Inc
Chalenge Inc is a company responsible for creating media applications and is studying the development of a new application so that its users can exchange information about the films they have watched and those they still want to watch. This project consumes an Api to describe films.

## Main Features
* Create, read, edit and delete user records.
* Create, read, edit and delete movie records.
* Create, read, edit and delete wish list records.
* Create, read, edit and delete evaluation movies records.
* Filter evaluations that are in private status.
* Filter evaluations that are in public status.
* Filter evaluations performed by a single user.
* Assign a rating to a movie.
* Edit anywere information

## Tecnologies
* MySQL 8.0.21
* Spring Boot 2.3.3.RELEASE
* JDK 1.8.0_261
* Swagger 3

## Notes
* The file for configuring the database and other definitions is in the directory below:
```
\src\main\resources\aplication.properties
```

* The login MySql is: root
* The password MySql is: 123456
* For any other note, examine the "application.properties" file and also the "pom.xml" file.

## Instalation
* To start the project on your computer, follow the instructions below:
Download this project in .zip format.
```
Clone or Download > Download ZIP
```

* Extract this project to your workspace folder.
In the eclipse, import the project as a MAVEN project following the steps below:
```
File > Import >  Maven > Existing Maven Projects (next) > Finish
```

* Navigate to the directory that contains the extracted project folder (browse).
* After importing the project, Maven will download the necessary dependencies and this will take a few minutes, according to information in the lower left bar of the eclipse (Progress).
* To start the application, run the main class for this project with "Run as Java application" if using Eclipse IDE or "Run as Spring Boot App" if using Spring Tool Suite.

## Swagger
* Swagger was used to make all requests, as shown in the figure below.
![TESTE](/XXX.png = 500x347)

* To view the complete Swagger documentation, you need to access "http://localhost:9002/swagger-ui/index.html" when it is started.
* Tge port 9002 can be changed according to the application.properties file.

## Built With
* [Spring Tool Suite 4](https://spring.io/tools) - IDE used.
* [Swagger](https://swagger.io/) - To document this API.
* [Postman](https://www.postman.com/) - To test all requests.

## Autor
**Widson Gomes de Melo**
* [Linkedin](https://www.linkedin.com/in/widsonmelo/) - www.linkedin.com/in/widsonmelo

## Licence
* This project is licensed under the Apache License - see the [LICENSE.md](LICENSE) file for details.