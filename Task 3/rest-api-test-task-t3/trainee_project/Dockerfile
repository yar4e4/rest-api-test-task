# Используем базовый образ с Java
FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем jar-файл в контейнер
COPY target/product-api-1.0-SNAPSHOT.jar /app/app.jar

# Указываем порт, на котором работает приложение
EXPOSE 8080

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]