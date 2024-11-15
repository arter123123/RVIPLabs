# Используем официальный образ JDK 17 от Oracle
FROM openjdk:21-oracle

# Копируем собранный JAR файл в контейнер
ADD target/RVIPLabs-0.0.1-SNAPSHOT.jar /app/RvipLabs-0.0.1-SNAPSHOT.jar

# Указываем команду для запуска приложения с ограничением памяти и активным профилем "dev"
CMD ["java", "-Xmx200m", "-jar", "-Dspring.profiles.active=dev", "/app/RvipLabs-0.0.1-SNAPSHOT.jar"]

# Открываем порт 8080 для доступа к приложению
EXPOSE 8080