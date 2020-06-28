# API Server Application

## local run

### without docker-compose

```$sh
docker run -d --name postgres -e POSTGRES_DB=test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -p 5432:5432 postgres:alpine && \
cd server
./gradlew bootRun
```

### within docker-compose

```$sh
cd server
./gradlew bootBuildImage --imageName=registry.heroku.com/openjdk-finder/web && \
cd ../test && \
docker-compose up -d
```

## Deploy

```$sh
cd server
./gradlew bootBuildImage --imageName=${app}/web && \
docker push registry.heroku.com/${app}/web && \
heroku container:release -a ${app} web
```
