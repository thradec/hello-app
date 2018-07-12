# HELLO-APP

[![Build Status](https://travis-ci.org/thradec/hello-app.svg?branch=master)](https://travis-ci.org/thradec/hello-app)
[![Sonar Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=cz.thradec.hello%3Ahello-parent&metric=alert_status)](https://sonarcloud.io)

Private simple playground application.


### How to build and test

```
$./mvnw clean verify [-Pprofile]
```

available profiles:
* `db.h2` activated by default
* `db.postgres` requires postgres database (see _application-db.postgres.properties_)
* `ssl` enable SSL, the application will be available on _https://localhost:8443/_
* `docker` build docker image



### How to run hello-app

* with maven
```
$ ./mvnw -f hello-app/pom.xml spring-boot:run [-Pprofile]
```

* with java
```
$ java -jar hello-app/target/hello-app-0.0.1-SNAPSHOT.jar
```

* with docker (image is built with _docker_ profile)
```
$ docker run -d -p 8090:8090 -t hello-app
```


### How to ...
 
* use **postgres** docker image for testing
```
$ docker run --name hello-db -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres
```

* use **jenkins** for testing CI pipeline alias _Jenkinsfile_
    * run jenkins docker image with pre installed blue ocean plugin
      ```
      $ docker run -p 8091:8080 jenkinsci/blueocean
      ```
    * open browser http://dockerip:8099/blue/ (admin password is printed in console)

* use **[sonarcloud.io](https://sonarcloud.io/organizations/thradec-github/projects)**
  ```
   $ ./mvnw clean \
            verify \
            sonar:sonar \
                -Dsonar.organization=thradec-github \
                -Dsonar.host.url=https://sonarcloud.io \
                -Dsonar.login=[SECRET_TOKEN]
  ```



### Testing with CURL
```
curl localhost:8090/actuator/health
curl localhost:8090/api/hello
curl localhost:8090/api/hello/random
curl -X POST -H "Content-Type: application/json" -d '{"message":"unauthorized"}' localhost:8090/api/hello
curl -X POST -H "Content-Type: application/json" -d '{"message":"hello"}' localhost:8090/api/hello -u admin:admin
```


### Documentation of REST API
```
http://localhost:8090/v2/api-docs
http://localhost:8090/swagger-ui.html
```