# HELLO-APP

[![Build Status](https://travis-ci.org/thradec/hello-app.svg?branch=master)](https://travis-ci.org/thradec/hello-app)

Private simple playground application.


### How to build and test

```
$./mvnw clean verify [-Pprofile]
```

available profiles:
* `db.h2` activated by default
* `db.postgres` requires postgres database (see _application-db.postgres.properties_)



### How to run

* with maven
```
$ ./mvnw spring-boot:run
```

* with java
```
$ java -jar target/hello-app-0.0.1-SNAPSHOT.jar
```

* with docker
```
$ ./mvnw clean package docker:build
$ docker run -d -p 8090:8090 -t hello-app
```


### How to ...
 
* use postgres docker image for testing
```
$ docker run --name hello-app-db -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres
```

* use jenkins docker image for testing CI pipeline alias _Jenkinsfile_
```
$ docker run -p 8091:8080 -p 50000:50000 jenkins
```