# =======================
# 1️⃣ BUILD STAGE
# =======================
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# copy pom trước để cache dependency
COPY pom.xml .
RUN mvn dependency:go-offline

# copy source
COPY src ./src

# build jar
RUN mvn clean package -DskipTests


# =======================
# 2️⃣ RUN STAGE
# =======================
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
