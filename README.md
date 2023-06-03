## Dear colleague,

I would like to present my realization of the test technical task.
In that task I realized an application as it was described
in the SSTaskBE.pdf.

#### Frontend

In order to realize the application, I used a Spring Boot framework with
combination of Angular 16 frontend. In source repository, you can find separate module in the **Frontend** folder,
where you can evaluate Angular project and source codes.

All UI files already prepared and placed in needs places, but if you are going to build new
UI interface and update it in the main project, please use predefined command:
```
cd Frontend
npm run build_static
```
Don't forget to execute **nmp install** command before. After execute **build_static**  all
compiled files will be prepared and placed to resource/static folder in main project.

#### Backend

As backend was selected Spring boot framework, because of technical task requirements. I was
using Spring boot 3.0.6 version. Application created by classical schema, because it fit very well
for that kind of application. It has three layers.

- First layer use spring rest controllers to realise API for frontend app communication.
- Second layer is service, used to organize bussiness logic.
- Third layer is persistent layer pointed to work with mysql database.

Backend application use H2 database.


In project was added **OpenAPI** plugin. It'll be available after application started by link:
http://localhost:8080/swagger-ui/index.html

#### Tests
I used the "JUnit 5" framework to create tests for that application. I used "BaseSpringContainer.class"
as basic class for tests. All test grouped in the test suit - SmokeSuitTest.

## Start application

PLEASE ! Use **jvm 18** and late on your machine !

For your convenience I added bash script **runme.sh** to start application.

To do it manually, please follow next instructions.

- Start spring boot application
```
gradle bootRun
```
- Use h2 console for debugging

http://localhost:8080/h2-console

- Run tests from command line
```
- gradle -Dtest.single=**/SmokeSuitTest* clean test
```

- Prepare container for docker
```
./gradlew bootBuildImage --imageName=myorg/myapp
```

- point your browser on page: http://localhost:8080/

I hope for your positive feedback. Thank you for this interesting task.

Sincerely, Roman Zolotarev.


