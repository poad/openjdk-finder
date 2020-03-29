# API Server Application

## Deploy

```$sh
./gradlew -Djib.to.image=registry.heroku.com/${app}/web jibDockerBuild && \
docker push registry.heroku.com/${app}/web && \
heroku container:release -a ${app} web
```
