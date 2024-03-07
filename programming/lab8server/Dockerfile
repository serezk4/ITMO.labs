# Используем образ JDK 21
FROM openjdk:21-jdk-slim as build

# Устанавливаем рабочую директорию
WORKDIR /workspace/app

# Копируем Gradle и исходный код
COPY gradlew .
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

# Даем права на выполнение для gradlew
RUN chmod +x ./gradlew

# Собираем приложение
RUN ./gradlew build -x test

# Запуск собранного приложения
FROM openjdk:21-jdk-slim
COPY --from=build /workspace/app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
