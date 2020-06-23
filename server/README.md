# API Server Application

## local run

```$sh
docker run -d --name postgres -e POSTGRES_DB=test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:alpine && \
./gradlew bootRun
```

## Deploy

```$sh
./gradlew bootBuildImage --imageName=${app}/web && \
docker push registry.heroku.com/${app}/web && \
heroku container:release -a ${app} web
```
