
# Hero Maker

Backend technical test for [atSitemas](https://www.atsistemas.com/es)

## Build Setup

```bash

# Install
./mvnw install

# Genereate docker image
./mvnw  docker:build
```

## Configuration

| Property              | Type   | Default     | Description                                                                                                                |
|-----------------------|--------|-------------|----------------------------------------------------------------------------------------------------------------------------|
| server.port           | int    | 8080        | Port to serve.                                                                                                             |
| users.start.list      | String | admin-admin | Determines which users are created at startup. The format {username-password}. You can add more separating them with one , |
| token.expiration.time | long   | 60          | Token expiration time in minutes.                                                                                          |

> To change the DB configuration see [Spring](https://docs.spring.io/spring-boot/docs/current/reference/html/appendix-application-properties.html#common-application-properties-data)
>
> To change the configuration in the docker container you can mount the **volume** in **/config** 


## API Documentation

Hero Maker includes [Swagger](https://swagger.io/) as documentation you can consult it by navigating to **/docs/swagger**


