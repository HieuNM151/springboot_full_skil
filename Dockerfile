# Sử dụng Eclipse Temurin thay cho OpenJDK cũ
FROM eclipse-temurin:17-jdk-alpine

ARG FILE_JAR=target/*.jar

# Dùng COPY thay cho ADD để an toàn hơn (ADD thường dùng cho tải file từ URL)
COPY ${FILE_JAR} api-service.jar

ENTRYPOINT ["java","-jar","/api-service.jar"]

EXPOSE 8080