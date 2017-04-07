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
 
* use postgres docker image for testing
```
$ docker run --name hello-db -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres
```

* use jenkins docker image for testing CI pipeline alias _Jenkinsfile_
```
$ docker run -p 8091:8080 -p 50000:50000 jenkins
```


### Testing with curl
```
curl localhost:8090/api/hello
curl localhost:8090/api/hello/random
curl -X POST -H "Content-Type: application/json" -d '{"message":"unauthorized"}' localhost:8090/api/hello
curl -X POST -H "Content-Type: application/json" -d '{"message":"hello"}' localhost:8090/api/hello -u admin:admin
```