# HELLO-APP

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


### How to run postgres via docker

```
$ docker run --name hello-app-db -p 5432:5432 -e POSTGRES_PASSWORD=123456 -d postgres
```