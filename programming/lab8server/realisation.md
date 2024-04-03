# Важные `тыки`

[`Dockerfile`](Dockerfile) + [`compose.yml`](compose.yaml)

### Компонент локализации

[`локазлизация`](src/main/java/com/serezka/server/localization)

### Компонент авторизации

[`бдшка`](src/main/java/com/serezka/server/authorization/database)
[`контроллер`](src/main/java/com/serezka/server/authorization/controller/AuthenticationController.java)
[`конфигурация`](src/main/java/com/serezka/server/authorization/config/SecurityConfig.java)
[`сервис авторизации`](src/main/java/com/serezka/server/authorization/service/AuthenticationService.java)
[`validator`](src/main/java/com/serezka/server/authorization/filter/JwtAuthenticationFilter.java)

### Компонент коллекции
    
[`бдшка`](src/main/java/com/serezka/server/collection/database)
[`контроллер коллекции`](src/main/java/com/serezka/server/collection/controller/CollectionController.java)
[`контроллер executor'a`](src/main/java/com/serezka/server/collection/controller/ExecutionController.java)
[`handler`](src/main/java/com/serezka/server/collection/execution/handler/Handler.java)
[`command`](src/main/java/com/serezka/server/collection/execution/commands/Command.java)